import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.TextAnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

import defecto.NoDetector;

@SuppressWarnings("deprecation")
public class NegationAnalyzer {

	//Clase que representa al programa

	//Tabla para almacenar de cada oraci�n las anotaciones que tienen asociadas para facilitar posteriormente
	//la b�squeda
	//La clave es el id de la oraci�n y el valor almacenado es una lista de las anotaciones que hay en esa oraci�n
	private static HashMap<Integer,List<NoDetector>> anotaciones;

	public NegationAnalyzer() {
		try {

			File descriptor = new File("D:\\EclipseWorkspace\\UIMANegDetection\\desc\\NoDetectorAnnotator.xml");
			File inputFile = new File("D:\\Politecnica\\MasterIngInf\\4Semestre\\TFM\\PruebasAnotadorNegacion\\data\\Prueba1.txt");


			XMLInputSource in = new XMLInputSource(descriptor);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

			TextAnalysisEngine tae = UIMAFramework.produceTAE(specifier);
			JCas tCas = tae.newJCas();

			FileInputStream fis = new FileInputStream(inputFile);
			byte [] contents = new byte[(int)inputFile.length()];
			fis.read(contents);
			fis.close();
			String document = new String(contents);

			anotaciones = new HashMap<Integer,List<NoDetector>>();

			tCas.setDocumentText(document);
			tae.process(tCas);

			storeAnnotations(tCas);

			tae.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidXMLException e) {
			e.printStackTrace();
		} catch (ResourceInitializationException e) {
			e.printStackTrace();
		} catch (AnalysisEngineProcessException e) {
			e.printStackTrace();
		}
	}//end Constructor

	private static void storeAnnotations(JCas jcas) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("En StoreAnnotations");
		Type tipo = jcas.getTypeSystem().getType("defecto.NoDetector");
		FSIterator<Annotation> iter = jcas.getAnnotationIndex(tipo).iterator();
		List<NoDetector> anotacionesEncontradas = new ArrayList<NoDetector>();
		while(iter.hasNext()) {
			anotacionesEncontradas.add((NoDetector)iter.next());
		}
		iter.moveToFirst();
		while(iter.isValid()) {
			FeatureStructure fs = iter.get();
			NoDetector annot = (NoDetector)fs; //Esta es la anotaci�n que quiero a�adir
			//System.out.println("Anotaci�n (Covered Text) > " + annot.getCoveredText());
			System.out.println("1)Comienzo de anotaci�n " + annot.getBegin() + " hasta " + annot.getEnd() + " ID de la oraci�n "+ annot.getIdOracion());
			//System.out.println("Oraci�n " + annot.getOracionString() );
			//anotaciones.add(index, element);
			List<NoDetector> aux = anotaciones.get(annot.getIdOracion()); //Estas son las anotaciones de la oraci�n
			if(aux==null) {
				//Creo una nueva lista
				List<NoDetector> nuevaLista = new ArrayList<NoDetector>();
				nuevaLista.add(annot);
				anotaciones.put(annot.getIdOracion(),nuevaLista);
			}else {
				//No puedo evitar que se cree una anotaci�n que est� contenida en otra de mayor tama�o, pues al estar basado en un
				//diccionario va a anotarlo. Ej: sin signos de. Va a anotar 3 veces (sin signos de, sin signos y sin). Y es parcialmente
				//correcto. Me quedo con la de mayor tama�o
				//Para evitar que me anote varias veces una palabra que ya est� contenida en un conjunto de palabras
				//de las cuales ya tengo la anotaci�n tengo que filtrar
				boolean add = false;
				for(NoDetector nD: aux) { 
					//nD es la anotaci�n que ya tengo
					//annot es la que quiero a�adir o no
					if(nD.getBegin() == annot.getBegin()) { //deben de comenzar en el mismo caracter
						int lenNd = nD.getEnd() - nD.getBegin();
						System.out.println("Longitud lenNd = " + lenNd );
						int lenAnnot = annot.getEnd() - annot.getBegin();
						System.out.println("Longitud lenAnnot = " + lenAnnot);
						System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
						//if(nD.getEnd() < annot.getEnd()) { //este caso se corresponder�a con nD = sin y annot = sin signos de
						if(lenNd > lenAnnot) {	//Debo eliminar las anotaci�n que tengo (nD) 
							aux.remove(nD);
							
							System.out.println("&&&ELIMINADO&&&");
							add = true; //Indico que tengo que a�adir la nueva annot (sin signos de)
							//break;
							aux.add(annot);
						}
					}
				}
				/*if(add) {
					aux.add(annot);
				}*/
				anotaciones.put(annot.getIdOracion(),aux);
			}
			iter.moveToNext();
		}
		Collection<List<NoDetector>>anAux = anotaciones.values();
		Iterator<List<NoDetector>> it = anAux.iterator();
		System.out.println("###########################");
		System.out.println("Despues de filtrar");
		while(it.hasNext()) {
			List<NoDetector> listaAux = it.next();
			for(NoDetector annot : listaAux) {
				System.out.println("Comienzo de anotaci�n " + annot.getBegin() + " hasta " + annot.getEnd() + " ID de la oraci�n "+ annot.getIdOracion());
				System.out.println("Oraci�n " + annot.getOracionString() );
			}
		}
	}

	public boolean isNegated(String concept, int sentenceId) {
		//Primero obtener la oraci�n y las anotaciones
		//Segundo ver si est� el concepto en la oraci�n
		//
		List<NoDetector> anotacionesSentence = anotaciones.get(sentenceId);
		boolean res = false;
		boolean encontrado = false;
		if(anotacionesSentence!=null) {//Caso en el que existan anotaciones en la oraci�n con ese id
			if(!anotacionesSentence.isEmpty()) {//Caso en el que existan anotaciones en esa oraci�n
				for(NoDetector nD: anotacionesSentence) {
					//Obtengo la oraci�n asociada a la anotaci�n
					String sentence = nD.getOracionString();
					System.out.println("##########################");
					//Busco si encuentro el concept
					int indice = sentence.indexOf(concept);
					if(indice == -1) { //No se ha encontrado el concepto en la oraci�n
						encontrado = false;
						break;
					}else { //Se ha encontrado el concepto en la oraci�n
						encontrado = true;
						break;
					}
				}
				if(!encontrado) {
					//No existe el concepto en esa frase
					System.out.println("No se ha encontrado el concepto " + concept +" en la oracion " + sentenceId);
					res = false;
				}else {
					res = true;
				}
			}else {//Caso en el que no existan anotaciones en esa oraci�n
				System.out.println("No existen anotaciones en la oraci�n con id " + sentenceId);
				res = false;
			}
		}else {
			System.out.println("No existen anotaciones en la oraci�n con id " + sentenceId);
			res = false;
		}
		return res;
	}

	//Yo recibo un XMI ya con el POS 
	//No tengo que hacer el POS
	public static void main(String [] args) {
		NegationAnalyzer nA = new NegationAnalyzer();
		System.out.println(nA.isNegated("VIH",2));
	}

}

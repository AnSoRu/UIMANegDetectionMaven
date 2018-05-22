import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.TextAnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
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
	private static HashMap<Integer,List<NoDetector>> anotacionesFinales;

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
			anotacionesFinales = new HashMap<Integer,List<NoDetector>>();

			tCas.setDocumentText(document);
			tae.process(tCas);

			tae.collectionProcessComplete();

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

		while(iter.hasNext()) {
			NoDetector anotacion = (NoDetector) iter.next();
			int idOracion = anotacion.getIdOracion();
			if(anotaciones.get(idOracion)!=null) {
				List<NoDetector> listaAux = anotaciones.get(idOracion);
				listaAux.add(anotacion);
				anotaciones.put(idOracion,listaAux);
			}else {
				List<NoDetector> listaAux = new ArrayList<NoDetector>();
				listaAux.add(anotacion);
				anotaciones.put(idOracion,listaAux);
			}
		}
		iter.moveToFirst();
		//Hasta aqui es correcto
		System.out.println("El tamaño de las anotaciones es " + anotaciones.size());
		Collection<List<NoDetector>>anAux = anotaciones.values();
		Iterator<List<NoDetector>> it = anAux.iterator();
		System.out.println("###########################");
		System.out.println("Filtrando");

		//List<Integer> longitudes = new ArrayList<Integer>();
		//      Comienzo Longitud
		HashMap<Integer,Integer> longitudesMap = new HashMap<Integer,Integer>(); //El primer elemento es la posicion de comienzo de la anotacion, el segundo es la longitud
		HashMap<Integer,NoDetector> unicaAnotacion = new HashMap<Integer,NoDetector>();
		while(it.hasNext()) {
			List<NoDetector> listaAux = it.next();
			int idOracion = -1;
			System.out.println("El tamaño de la lista es " + listaAux.size());
			for(NoDetector nd: listaAux) {
				int longitud = nd.getEnd() - nd.getBegin() + 1;
				int comienzo = nd.getBegin();
				idOracion = nd.getIdOracion();
				Integer longAux = longitudesMap.get(comienzo);
				if(longAux!=null) {
					if(longitud > longAux.intValue()) {
						longitudesMap.replace(comienzo, longitud);
						unicaAnotacion.replace(comienzo,nd);
					}
				}else {
					longitudesMap.put(comienzo,longitud);
					unicaAnotacion.put(comienzo,nd);
				}
			}

			//Si empiezan en la misma posicion
			//En longitudes tengo las longitudes de las anotaciones por orden
			//En unicaAnotacion tengo las anotaciones
			Set<Integer> keySet = unicaAnotacion.keySet();
			List<NoDetector> anF = anotacionesFinales.get(idOracion);
			if(anF!=null) {
				for(Integer iAux: keySet) {
					NoDetector nDAux = unicaAnotacion.get(iAux);
					anF.add(nDAux);
				}
			}else {
				List<NoDetector> nuevaLista = new ArrayList<NoDetector>();
				for(Integer iAux: keySet) {
					NoDetector nDAux = unicaAnotacion.get(iAux);
					nuevaLista.add(nDAux);
				}
				anotacionesFinales.put(idOracion,nuevaLista);
			}


			longitudesMap.clear();
			unicaAnotacion.clear();
			/*idOracion = annot.getIdOracion();
				System.out.println("Comienzo de anotaci�n " + annot.getBegin() + " hasta " + annot.getEnd() + " ID de la oraci�n "+ annot.getIdOracion());
				System.out.println("Oraci�n " + annot.getOracionString() );
				int longitud = annot.getBegin() - annot.getEnd();
				idAnotacion++;*/
		}
		Set<Integer> idsOraciones = anotacionesFinales.keySet();
		for(Integer id: idsOraciones) {
			List<NoDetector> anotacionesDeOracion = anotacionesFinales.get(id);
			if(!anotacionesDeOracion.isEmpty()) {
				for(NoDetector nD: anotacionesDeOracion) {
					System.out.println("Comienzo de anotacion " + nD.getBegin() + " hasta " + nD.getEnd() + " ID de la oracion " + nD.getIdOracion());
				}
			}else {
				System.out.println("La oracion " + id + " no contiene anotaciones");
			}
		}
	}

	public boolean isNegated(String concept, int sentenceId) {
		//Primero obtener la oraci�n y las anotaciones
		//Segundo ver si est� el concepto en la oraci�n
		//
		List<NoDetector> anotacionesSentence = anotacionesFinales.get(sentenceId);
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
		System.out.println(nA.isNegated("x",2));
	}

}

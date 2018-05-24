package annotators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import auxiliar.Oracion;
import defecto.NoDetector;
import es.upm.ctb.midas.clikes.tokenization.Sentence;
import es.upm.ctb.midas.clikes.tokenization.Token;


/**
 * Clase que implementa el comportamiento del anotador de negación.
 * @author Angel Soler Ruiz
 * @version 1.0
 */
public class NoDetectorAnnotator extends JCasAnnotator_ImplBase {

	private StringMapResource_impl mMap;
	private HashMap<Integer,NoDetector> mapAux;
	private List<String> listaPalabras;
	static String[] SENTENCE;
	private List<Oracion> oraciones;
	private List<Token> tokensANegar;

	/**
	 * Método en el que se inicializan:
	 * - La lista de palabras local a partir de la lectura del archivo Dictionary.txt
	 * - El hashmap que utiliza para evitar que se anote más de una anotacion que comience en la misma posicion
	 * @param aContext Contexto UIMA @see {@link UimaContext}
	 * @throws ResourceInitializationException si no se puede inicializar el "Dictionary"
	 */
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {

			mMap = (StringMapResource_impl)getContext().getResourceObject("Dictionary");
			mapAux = new HashMap<Integer,NoDetector>();
			listaPalabras = mMap.getLista();
			oraciones = new ArrayList<Oracion>();
			tokensANegar = new ArrayList<Token>();

		} catch (ResourceAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iter = jCas.getAnnotationIndex(Sentence.type).iterator();

		List<Sentence> listaSentences  = new ArrayList<Sentence>();

		while(iter.isValid()) {
			Sentence sentence = (Sentence) iter.get();
			listaSentences.add(sentence);
			iter.moveToNext();
		}
		try {
			int i = 0;

			//FSArray tokensSentencia;

			while(i < listaSentences.size()) {

				Sentence s = listaSentences.get(i);

				String textoDeSentence = s.getCoveredText(); 		
				System.out.println("LA SENTENCE ES -> " + textoDeSentence);
				System.out.println("LA SENTENCE COMIENZA EN -> " + s.getBegin());
				System.out.println("LA SENTENCE TERMINA EN -> " + s.getEnd());

				for(String sAux : listaPalabras) {
					System.out.println("Evaluando si contiene " + sAux);
					List<Integer> contenida = isContained(textoDeSentence, sAux);
					System.out.println("Lo que hay en contenida es ");
					for(Integer iC: contenida) {
						System.out.println(iC);
					}
					int tam = contenida.size();
					if(tam>0) {
						System.out.println("La lista tiene tamaño " + tam);
						int aux = 0;
						while(aux<tam) {
							int inicio = 0;
							int fin = 0;
							inicio = contenida.get(aux) + s.getBegin();
							fin = inicio + sAux.length();

							//Antes de crear la anotacion miro a ver si tengo más anotaciones que comienzan en la misma posicion y que sea 
							//una anotacion cuyo texto este contenido en la "supuesta" anotacion que quiero añadir
							//Ej: Si existiera la anotacion Sin sintomas de, no debería de anotar Sin pues esta contenida en una mayor y comienza en la misma posicion
							NoDetector hashNoDetector = mapAux.get(inicio);
							if(hashNoDetector!=null) {  
								//En el caso en el que haya una anotacion, siempre va a ser la que tiene mas longitud
								//Debo de comparar el caracter en donde termina cada una
								//Solo voy a sustituir la que tengo en mi hash si el nuevo fin que he encontrado es > que lo que tengo almacenado
								if(fin > hashNoDetector.getEnd()) {
									NoDetector annotation = new NoDetector(jCas);
									annotation.setBegin(inicio);
									annotation.setEnd(fin);

									mapAux.replace(inicio,annotation);
								}								
							}else { //Caso en el que no haya ninguna anotacion previamente guardada

								NoDetector annotation = new NoDetector(jCas);
								annotation.setBegin(inicio);
								annotation.setEnd(fin);

								//Ahora no voy a añadirlo a los indexes
								//annotation.addToIndexes();

								//Guardo la anotacion
								mapAux.put(inicio,annotation);
							}
							aux++;
						}//Fin while(aux<tam)
					}//Fin if tam > 0
				}//Fin for listaPalabras
				i++;
			}//Fin i < listaSentences.size()

			//Anotar el termino al que supuestamente niega
			i = 0;
			Set<Integer> claves = mapAux.keySet();
			Iterator<Integer> itClaves = claves.iterator();
			while(i < listaSentences.size()) {

				Sentence sAux = listaSentences.get(i);
				if(sAux!=null) {
					//Obtengo los tokens de la sentence
					//Cuidado con los simbolos que no son palabras

					//Obtengo los anotadores de negacion comprendidos entre el begin y el end de la oracion
					//Coger el primer token cuyo begin sea > que el final del anotador negado 
					int inicioSentence = sAux.getBegin();
					int finSentence = sAux.getEnd();
					List<NoDetector> anotacionesSentence = negacionesSentence(inicioSentence,finSentence);
					FSArray tokens = sAux.getTokens();
					//Token supuestoNegado = null;
					if(!anotacionesSentence.isEmpty()) {
						for(NoDetector nD : anotacionesSentence) {
							int j = 0;
							boolean addedToken = false;
							while(j < tokens.size() && !addedToken) {
								Token tAux = (Token) tokens.get(j);
								if(tAux.getBegin()>nD.getEnd()) {
									//Cojo el primer token cuyo begin sea mayor que el final de mi anotacion
									if (!Pattern.matches("\\p{Punct}", tAux.getCoveredText())) {
										//Caso en el que no sea un signo de puntuacion
										tokensANegar.add(tAux);
										addedToken = true;
									}
								}
								j++;
							}
						}
					}
				}

				i++;
			}//Fin while 2
			//Aqui debo recorrer las anotaciones y añadirlas a los indices
			if(!tokensANegar.isEmpty()) {
				for(Token t: tokensANegar) {
					NoDetector palabraNegada = new NoDetector(jCas);
					palabraNegada.setBegin(t.getBegin());
					palabraNegada.setEnd(t.getEnd());
					palabraNegada.addToIndexes();		
				}
			}

			//NoDetector nDAux = mapAux.get(itClaves.next());
			//No voy a añadir la palabra que indica negacion voy a añadir la palabra a la que supuestamente niega

			//nDAux.addToIndexes();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		super.collectionProcessComplete();
		System.out.println("@@@@@@@Terminado");
	}

	public List<Oracion> getOraciones(){
		return this.oraciones;
	}

	/**
	 * @param source
	 * @param subItem
	 * @return List<Integer> que indica la posicion en donde comienza cada palabra
	 */
	private static List<Integer> isContained(String source, String subItem) {
		String pattern = "\\b" + subItem + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		List<Integer> result = new ArrayList<Integer>();
		while(m.find()) {
			result.add(m.start());
		}
		return result;
	}

	//Devuelve los anotadores contenidos en la oracion comprendida entre beginSentence y endSentence
	private List<NoDetector> negacionesSentence(int beginSentence,int endSentence){
		List<NoDetector> result = new ArrayList<NoDetector>();
		while(beginSentence<endSentence) {
			NoDetector nAux = mapAux.get(beginSentence);
			if(nAux!=null) {
				result.add(nAux);
			}
			beginSentence++;
		}
		return result;
	}

}
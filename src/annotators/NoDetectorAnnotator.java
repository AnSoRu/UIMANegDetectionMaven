package annotators;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import auxiliar.OneSentenceText;
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

	//private Pattern noPattern = Pattern.compile("\\b(No|no).\\p{Punct}\\b");
	//private Pattern noPattern = Pattern.compile("(No|no|ni|Tampoco|tampoco|Nunca|nunca|Sin|sin|Ning�n|ning�n)[\\s][a-zA-Z\\s]*[^\\p{Punct}]");

	//Fijarse en esta direccion
	//http://sujitpal.blogspot.com.es/2011/06/uima-analysis-engine-for-keyword.html

	//private Set<Pattern> patternSet;

	//Map
	private StringMapResource_impl mMap;
	//private Map<String,String> mapAux;
	private List<String> listaPalabras;
	static String[] SENTENCE;
	private List<Oracion> oraciones;
	//private List<Integer> longitudOraciones; //Longitud acumulada

	//ParsePosition pp = new ParsePosition(0);

	// ****************************************
	// * Static vars holding break iterators
	// ****************************************
	static final BreakIterator sentenceBreak = BreakIterator.getSentenceInstance(Locale.US);

	/**
	 * Método en el que se inicializan:
	 * - La lista de palabras local a partir de la lectura del archivo Dictionary.txt
	 * @param aContext Contexto UIMA @see {@link UimaContext}
	 * @throws ResourceInitializationException si no se puede inicializar el "Dictionary"
	 */
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			/*SharedSetResource res = (SharedSetResource)aContext.getResourceObject("");
			patternSet = new HashSet<Pattern>();
			for(String patternString : res.getConfig()) {
				patternSet.add(Pattern.compile(patternString));
			}*/
			mMap = (StringMapResource_impl)getContext().getResourceObject("Dictionary");
			//mapAux = mMap.getMap();
			listaPalabras = mMap.getLista();
			oraciones = new ArrayList<Oracion>();

		} catch (ResourceAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		/*byte[] docText = null;
		docText = jCas.getDocumentText().getBytes();*/

		FSIterator<Annotation> iter = jCas.getAnnotationIndex(Sentence.type).iterator();

		List<Sentence> listaSentences  = new ArrayList<Sentence>();

		while(iter.isValid()) {
			Sentence sentence = (Sentence) iter.get();
			listaSentences.add(sentence);
			iter.moveToNext();
		}
		try {
			int i = 0;
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

							NoDetector annotation = new NoDetector(jCas);
							annotation.setBegin(inicio);
							annotation.setEnd(fin);
							annotation.addToIndexes();

							System.out.println("Anotado desde " + inicio + " hasta " + fin );
							
							aux++;
						}
					}
				}
				i++;
			}
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
	 * @return
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

}
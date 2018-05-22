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
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import auxiliar.OneSentenceText;
import auxiliar.Oracion;
import defecto.NoDetector;


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
	private OneSentenceText oST;

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
			//longitudOraciones = new ArrayList<Integer>();
			oST = new OneSentenceText();
		} catch (ResourceAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		byte[] docText = null;
		docText = jCas.getDocumentText().getBytes();
		String docTextAux = null;
		docTextAux = new String(docText,Charsets.UTF_8);
		try {
			//Se eliminan los saltos de l�nea retornos de carro y tabuladores

			/*File temp = File.createTempFile("tempfile",".txt");
			FileOutputStream fio = new FileOutputStream(temp);
			fio.write(docText.getBytes());
			fio.close();*/

			//Este es el texto sobre el que voy a realizar anotaciones
			//No me importa la parte visual

			String oneSentence = oST.toOneSentenceText(docTextAux); 
			System.out.println("??????????????????????????????????");
			System.out.println("La transformada es ");
			System.out.println(oneSentence);
			//En la aplicaci�n de visualizaci�n a partir de ahora no van a coincidir la anotaci�n justo encima del texto correspondiente
			//pues estoy transformando el texto de entrada para que sea m�s r�pida la b�squeda, quitando los retornos de carro y saltos de l�nea.
			//Puesto que estoy dependiendo al 100% de la posici�n de los caracteres en el texto para poder anotar, transformo todo el texto
			//en una sola l�nea
			//En definitiva el texto sobre el que anoto no contiene ni retornos de carro 
			//No es el objetivo de este proyecto que visualmente se vea la anotaci�n.

			int posAux = 0;
			//StringTokenizer tokenizer = new StringTokenizer(docText,"\t\r\n.<>/?\";:[{]}\\|=+()!", false);
			//StringTokenizer tokenizer = new StringTokenizer(docText,"(?<=[.!?])\\s*", false);
			//StringTokenizer tokenizer = new StringTokenizer(docText,".", false);
			StringTokenizer tokenizer = new StringTokenizer(oneSentence,".",false);
			int idOracionAux = 0;
			while(tokenizer.hasMoreTokens()) {
				System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIII");
				String oracionAux = tokenizer.nextToken();
				System.out.println("SE HA ENCONTRADO LA ORACI�N " + oracionAux);
				System.out.println("ID DE LA ORACI�N -> " + idOracionAux);
				int inicioOracion = oneSentence.indexOf(oracionAux);
				int finOracion = inicioOracion + oracionAux.length();
				System.out.println("Comienza en " + inicioOracion);
				System.out.println("Termina en " + finOracion);
				Oracion oAux = new Oracion(idOracionAux, oracionAux,inicioOracion,finOracion);
				oraciones.add(oAux);
				idOracionAux++;
			}
			System.out.println("############################");
			System.out.println("Comenzando bucle while");
			int i = 0;
			while(i < oraciones.size()) {
				//String token = tokenizer.nextToken();
				Oracion oAux = oraciones.get(i);
				String token = oAux.getOracion();
				System.out.println("##############################");
				System.out.println("El token es ");
				System.out.println(token);
				System.out.println("La longitud es " + token.length());
			
				for(String sAux : listaPalabras) {
					System.out.println("Evaluando si contiene " + sAux);
					List<Integer> contenida = isContained(token, sAux);
					
					int tam = contenida.size();
					int aux = 0;
					while(aux<tam) {
						int idOracion = oAux.getId();
						int inicio = 0;
						int fin = 0;
						inicio = contenida.get(aux);
						fin = inicio + sAux.length() - 1;
					
						System.out.println("La oracion contiene " + sAux);
						System.out.println("Comienza en : " + inicio);
						///
						posAux = token.indexOf(sAux);		
						
						NoDetector annotation = new NoDetector(jCas);
						annotation.setBegin(inicio);
						annotation.setEnd(fin);
						annotation.setIdOracion(idOracion);
						annotation.setOracionString(token);
						annotation.setLongitud(annotation.getEnd()-annotation.getBegin());

						System.out.println("Se ha creado una anotaci�n en la oraci�n " + idOracion);
						System.out.println("La anotacion comienza en " + inicio);
						System.out.println("La anotaci�n termina en " + fin);

						annotation.addToIndexes();
												
						aux++;
					}
				}
				posAux = posAux + token.length();
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
	
	
	
	/*public static void main(String [] args) {
		NoDetectorAnnotator nda = new NoDetectorAnnotator();
		String buscar = "no";
		for(Integer aux : nda.isContained("No HTA, no DM, no DL",buscar)) {
			System.out.println(aux);
			System.out.println("Inicio " + aux);
			int fin = aux + (buscar.length()-1);
			System.out.println("Fin " + fin);
		}
	}*/
}
package annotators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import defecto.NoDetector;

public class NegatedSentenceAnnotator extends JCasAnnotator_ImplBase {
	
	private Map<Integer,String> listadoOraciones;
	private Integer id;
	
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		this.listadoOraciones = new HashMap<>();
		this.id = 0;		
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		System.out.println("-------------------------------------------------");
		System.out.println("Procesando el NegatedSentenceAnnotator");
		String docText = jCas.getDocumentText();

		StringTokenizer tokenizer = new StringTokenizer(docText,"\t\n\r.<.>/?\";:[{]}\\|=+()!", true);
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			System.out.println("Hay oraci�n " + token);
			listadoOraciones.put(id, token);
			id++;
		}
		
		//Obtenemos los �ndices de las anotaciones producidas por el "NoDetectorAnnotator"
		FSIndex<NoDetector> noIndex = jCas.getAnnotationIndex(NoDetector.type);
		if(noIndex.size()>0) {
			System.out.println("#######################");
			System.out.println("Hay cosas");
		}else {
			System.out.println("No hay na de na");
		}

		//Tengo que ver el �ndice de cada anotaci�n
		Iterator<NoDetector> noIter = noIndex.iterator();
		while(noIter.hasNext()) {
			NoDetector annotation = noIter.next();
			System.out.println("###########");
			System.out.println("Texto cubierto");
			System.out.println(annotation.getCoveredText());
			int inicio = annotation.getBegin();
			int fin = annotation.getEnd();
			System.out.println("Inicio " + inicio);
			System.out.println("Fin " + fin);
		}

	}

}

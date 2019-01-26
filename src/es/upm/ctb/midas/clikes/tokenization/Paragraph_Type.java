
/* First created by JCasGen Wed May 23 18:00:46 CEST 2018 */
package es.upm.ctb.midas.clikes.tokenization;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed May 23 18:00:46 CEST 2018
 * @generated */
public class Paragraph_Type extends Annotation_Type {
  /** @generated */
  public final static int typeIndexID = Paragraph.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("es.upm.ctb.midas.clikes.tokenization.Paragraph");
 
  /** @generated */
  final Feature casFeat_sentences;
  /** @generated */
  final int     casFeatCode_sentences;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentences(int addr) {
        if (featOkTst && casFeat_sentences == null)
      jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentences);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentences(int addr, int v) {
        if (featOkTst && casFeat_sentences == null)
      jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentences, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getSentences(int addr, int i) {
        if (featOkTst && casFeat_sentences == null)
      jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setSentences(int addr, int i, int v) {
        if (featOkTst && casFeat_sentences == null)
      jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentences), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Paragraph_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentences = jcas.getRequiredFeatureDE(casType, "sentences", "uima.cas.FSArray", featOkTst);
    casFeatCode_sentences  = (null == casFeat_sentences) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentences).getCode();

  }
}



    
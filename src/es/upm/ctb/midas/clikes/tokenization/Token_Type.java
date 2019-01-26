
/* First created by JCasGen Wed May 23 18:00:46 CEST 2018 */
package es.upm.ctb.midas.clikes.tokenization;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Single token annotation
 * Updated by JCasGen Wed May 23 18:00:46 CEST 2018
 * @generated */
public class Token_Type extends Annotation_Type {
  /** @generated */
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("es.upm.ctb.midas.clikes.tokenization.Token");
 
  /** @generated */
  final Feature casFeat_tokenType;
  /** @generated */
  final int     casFeatCode_tokenType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTokenType(int addr) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "es.upm.ctb.midas.clikes.tokenization.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tokenType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenType(int addr, String v) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "es.upm.ctb.midas.clikes.tokenization.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_tokenType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_stems;
  /** @generated */
  final int     casFeatCode_stems;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getStems(int addr) {
        if (featOkTst && casFeat_stems == null)
      jcas.throwFeatMissing("stems", "es.upm.ctb.midas.clikes.tokenization.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_stems);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setStems(int addr, int v) {
        if (featOkTst && casFeat_stems == null)
      jcas.throwFeatMissing("stems", "es.upm.ctb.midas.clikes.tokenization.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_stems, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "es.upm.ctb.midas.clikes.tokenization.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_pos);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPos(int addr, int v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "es.upm.ctb.midas.clikes.tokenization.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_info;
  /** @generated */
  final int     casFeatCode_info;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getInfo(int addr) {
        if (featOkTst && casFeat_info == null)
      jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    return ll_cas.ll_getRefValue(addr, casFeatCode_info);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setInfo(int addr, int v) {
        if (featOkTst && casFeat_info == null)
      jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    ll_cas.ll_setRefValue(addr, casFeatCode_info, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getInfo(int addr, int i) {
        if (featOkTst && casFeat_info == null)
      jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_info), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_info), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_info), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setInfo(int addr, int i, int v) {
        if (featOkTst && casFeat_info == null)
      jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_info), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_info), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_info), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tokenType = jcas.getRequiredFeatureDE(casType, "tokenType", "uima.cas.String", featOkTst);
    casFeatCode_tokenType  = (null == casFeat_tokenType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenType).getCode();

 
    casFeat_stems = jcas.getRequiredFeatureDE(casType, "stems", "uima.cas.NonEmptyFSList", featOkTst);
    casFeatCode_stems  = (null == casFeat_stems) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_stems).getCode();

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.tcas.Annotation", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_info = jcas.getRequiredFeatureDE(casType, "info", "uima.cas.FSArray", featOkTst);
    casFeatCode_info  = (null == casFeat_info) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_info).getCode();

  }
}



    
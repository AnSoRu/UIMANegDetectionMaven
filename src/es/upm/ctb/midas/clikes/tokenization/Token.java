

/* First created by JCasGen Wed May 23 18:00:46 CEST 2018 */
package es.upm.ctb.midas.clikes.tokenization;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.NonEmptyFSList;


/** Single token annotation
 * Updated by JCasGen Wed May 23 18:00:46 CEST 2018
 * XML source: D:/EclipseWorkspace/UIMANegDetection/desc/NoDetectorAnnotator.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Token(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: tokenType

  /** getter for tokenType - gets token type
   * @generated
   * @return value of the feature 
   */
  public String getTokenType() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "es.upm.ctb.midas.clikes.tokenization.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_tokenType);}
    
  /** setter for tokenType - sets token type 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenType(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_tokenType, v);}    
   
    
  //*--------------*
  //* Feature: stems

  /** getter for stems - gets 
   * @generated
   * @return value of the feature 
   */
  public NonEmptyFSList getStems() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stems == null)
      jcasType.jcas.throwFeatMissing("stems", "es.upm.ctb.midas.clikes.tokenization.Token");
    return (NonEmptyFSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_stems)));}
    
  /** setter for stems - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStems(NonEmptyFSList v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stems == null)
      jcasType.jcas.throwFeatMissing("stems", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.ll_cas.ll_setRefValue(addr, ((Token_Type)jcasType).casFeatCode_stems, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: pos

  /** getter for pos - gets Part-of-Speech
   * @generated
   * @return value of the feature 
   */
  public Annotation getPos() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "es.upm.ctb.midas.clikes.tokenization.Token");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_pos)));}
    
  /** setter for pos - sets Part-of-Speech 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPos(Annotation v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.ll_cas.ll_setRefValue(addr, ((Token_Type)jcasType).casFeatCode_pos, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: info

  /** getter for info - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getInfo() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_info == null)
      jcasType.jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_info)));}
    
  /** setter for info - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setInfo(FSArray v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_info == null)
      jcasType.jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.ll_cas.ll_setRefValue(addr, ((Token_Type)jcasType).casFeatCode_info, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for info - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Annotation getInfo(int i) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_info == null)
      jcasType.jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_info), i);
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_info), i)));}

  /** indexed setter for info - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setInfo(int i, Annotation v) { 
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_info == null)
      jcasType.jcas.throwFeatMissing("info", "es.upm.ctb.midas.clikes.tokenization.Token");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_info), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_info), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    
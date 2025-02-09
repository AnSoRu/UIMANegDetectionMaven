

/* First created by JCasGen Wed May 23 18:00:46 CEST 2018 */
package es.upm.ctb.midas.clikes.tokenization;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 23 18:00:46 CEST 2018
 * XML source: D:/EclipseWorkspace/UIMANegDetection/desc/NoDetectorAnnotator.xml
 * @generated */
public class Paragraph extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Paragraph.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Paragraph() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Paragraph(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Paragraph(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Paragraph(JCas jcas, int begin, int end) {
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
  //* Feature: sentences

  /** getter for sentences - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getSentences() {
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_sentences == null)
      jcasType.jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences)));}
    
  /** setter for sentences - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentences(FSArray v) {
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_sentences == null)
      jcasType.jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    jcasType.ll_cas.ll_setRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for sentences - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Sentence getSentences(int i) {
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_sentences == null)
      jcasType.jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences), i);
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences), i)));}

  /** indexed setter for sentences - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setSentences(int i, Sentence v) { 
    if (Paragraph_Type.featOkTst && ((Paragraph_Type)jcasType).casFeat_sentences == null)
      jcasType.jcas.throwFeatMissing("sentences", "es.upm.ctb.midas.clikes.tokenization.Paragraph");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Paragraph_Type)jcasType).casFeatCode_sentences), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    
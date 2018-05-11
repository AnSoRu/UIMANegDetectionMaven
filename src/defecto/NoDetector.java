package defecto;


/* First created by JCasGen Tue Apr 03 22:41:57 CEST 2018 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Apr 23 18:01:36 CEST 2018
 * XML source: D:/EclipseWorkspace/UIMANegDetection/desc/SimpleNoRecognizer.xml
 * @generated */
public class NoDetector extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NoDetector.class);
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
  protected NoDetector() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NoDetector(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NoDetector(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NoDetector(JCas jcas, int begin, int end) {
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
  //* Feature: idOracion

  /** getter for idOracion - gets 
   * @generated
   * @return value of the feature 
   */
  public int getIdOracion() {
    if (NoDetector_Type.featOkTst && ((NoDetector_Type)jcasType).casFeat_idOracion == null)
      jcasType.jcas.throwFeatMissing("idOracion", "defecto.NoDetector");
    return jcasType.ll_cas.ll_getIntValue(addr, ((NoDetector_Type)jcasType).casFeatCode_idOracion);}
    
  /** setter for idOracion - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIdOracion(int v) {
    if (NoDetector_Type.featOkTst && ((NoDetector_Type)jcasType).casFeat_idOracion == null)
      jcasType.jcas.throwFeatMissing("idOracion", "defecto.NoDetector");
    jcasType.ll_cas.ll_setIntValue(addr, ((NoDetector_Type)jcasType).casFeatCode_idOracion, v);}    
   
    
  //*--------------*
  //* Feature: oracionString

  /** getter for oracionString - gets 
   * @generated
   * @return value of the feature 
   */
  public String getOracionString() {
    if (NoDetector_Type.featOkTst && ((NoDetector_Type)jcasType).casFeat_oracionString == null)
      jcasType.jcas.throwFeatMissing("oracionString", "defecto.NoDetector");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NoDetector_Type)jcasType).casFeatCode_oracionString);}
    
  /** setter for oracionString - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setOracionString(String v) {
    if (NoDetector_Type.featOkTst && ((NoDetector_Type)jcasType).casFeat_oracionString == null)
      jcasType.jcas.throwFeatMissing("oracionString", "defecto.NoDetector");
    jcasType.ll_cas.ll_setStringValue(addr, ((NoDetector_Type)jcasType).casFeatCode_oracionString, v);}    
  }

    
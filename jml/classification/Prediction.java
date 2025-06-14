/*    */ package jml.classification;
/*    */ 
/*    */ import jml.data.Data;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Prediction
/*    */   extends Data
/*    */   implements Cloneable
/*    */ {
/* 40 */   protected double confidence = 1.0D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Prediction(Object _data, int _label, double _confidence) {
/* 48 */     super(_data, _label);
/* 49 */     this.confidence = _confidence;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Prediction(Data original) {
/* 57 */     super(original);
/* 58 */     if (original instanceof Prediction) {
/* 59 */       this.confidence = ((Prediction)original).confidence;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object clone() {
/* 68 */     return new Prediction(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getConfidence() {
/* 75 */     return this.confidence;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConfidence(double _confidence) {
/* 81 */     this.confidence = _confidence;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(boolean PRINT_LABEL) {
/* 90 */     String text = super.toString(PRINT_LABEL);
/* 91 */     text = text + " " + this.confidence;
/* 92 */     return text;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\Prediction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
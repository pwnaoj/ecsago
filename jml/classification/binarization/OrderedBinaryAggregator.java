/*    */ package jml.classification.binarization;
/*    */ 
/*    */ import jml.classification.Aggregator;
/*    */ import jml.classification.Prediction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrderedBinaryAggregator
/*    */   extends Aggregator
/*    */ {
/*    */   public boolean is_negative(double conf) {
/* 50 */     return (conf == 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Prediction apply(Object data, double[] confidence) {
/* 60 */     int k = 0;
/* 61 */     for (; k < confidence.length && is_negative(confidence[k]); k++);
/* 62 */     if (k < confidence.length) return new Prediction(data, k, confidence[k]); 
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\OrderedBinaryAggregator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.fuzzy.operators;
/*    */ 
/*    */ import jml.classification.binarization.OrderedBinaryAggregator;
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
/*    */ public class ThresholdOrderedBinaryAggregator
/*    */   extends OrderedBinaryAggregator
/*    */ {
/* 42 */   protected double threshold = 0.5D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ThresholdOrderedBinaryAggregator() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ThresholdOrderedBinaryAggregator(double _threshold) {
/* 55 */     this.threshold = _threshold;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean is_negative(double conf) {
/* 64 */     return (conf < this.threshold);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\operators\ThresholdOrderedBinaryAggregator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
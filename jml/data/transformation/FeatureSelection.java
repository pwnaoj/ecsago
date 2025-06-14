/*    */ package jml.data.transformation;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.basics.Cloner;
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
/*    */ 
/*    */ public class FeatureSelection
/*    */   extends DataTransformation
/*    */ {
/* 42 */   protected int[] selectedFeatures = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FeatureSelection(int[] _selectedFeatures) {
/* 49 */     this.selectedFeatures = _selectedFeatures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data apply(Data data) {
/* 59 */     int n = this.selectedFeatures.length;
/* 60 */     double[] rec = new double[n];
/* 61 */     RealVector x = (RealVector)data.get();
/* 62 */     for (int i = 0; i < n; ) { rec[i] = x.get(this.selectedFeatures[i]); i++; }
/* 63 */      return new Data(new RealVector(rec), data.getLabel());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data inverse(Data data) {
/* 72 */     return (Data)Cloner.clone(data);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\FeatureSelection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MissedValuesFill
/*    */   extends DataTransformation
/*    */ {
/*    */   public abstract double fill(int paramInt);
/*    */   
/*    */   public Data apply(Data data) {
/* 53 */     RealVector x = (RealVector)data.get();
/* 54 */     double[] rec = x.get().full_values();
/* 55 */     int n = x.dimension();
/* 56 */     for (int i = 0; i < n; i++) {
/* 57 */       if (rec[i] == -1.0E108D) {
/* 58 */         rec[i] = fill(i);
/*    */       }
/*    */     } 
/* 61 */     return new Data(new RealVector(rec), data.getLabel());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data inverse(Data data) {
/* 70 */     return (Data)Cloner.clone(data);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\MissedValuesFill.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
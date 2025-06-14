/*    */ package jml.evolution.util;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.transformation.Normalization;
/*    */ import jml.util.IntUtil;
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
/*    */ public class BinaryToRealVectorPhenotype
/*    */   extends BinaryToIntArrayPhenotype
/*    */ {
/*    */   double max;
/* 45 */   protected Normalization space = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BinaryToRealVectorPhenotype(int _int_size, boolean _grayCode, Normalization _space) {
/* 52 */     super(_int_size, _grayCode);
/* 53 */     this.max = (1 << Math.min(this.int_size, IntUtil.INTEGER_SIZE - 1));
/* 54 */     this.max = Math.abs(this.max);
/* 55 */     this.space = _space;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object get(Object genome) {
/* 64 */     int[] y = (int[])super.get(genome);
/* 65 */     int n = y.length;
/* 66 */     double[] x = new double[n];
/* 67 */     for (int i = 0; i < n; i++) {
/* 68 */       x[i] = Math.abs(y[i]) / this.max;
/*    */     }
/*    */ 
/*    */     
/* 72 */     if (this.space != null) {
/* 73 */       this.space.apply(x);
/*    */     }
/* 75 */     return new RealVector(x);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object set(Object thing) {
/* 84 */     RealVector rv = (RealVector)thing;
/* 85 */     int n = rv.dimension();
/* 86 */     double[] x = new double[n];
/* 87 */     for (int i = 0; i < n; i++) {
/* 88 */       x[i] = rv.get(i);
/*    */     }
/* 90 */     int[] y = new int[n];
/* 91 */     if (this.space != null) {
/* 92 */       this.space.inverse(x);
/*    */     }
/* 94 */     for (int j = 0; j < n; j++) {
/* 95 */       y[j] = (int)(x[j] * this.max);
/*    */     }
/* 97 */     return super.set(y);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolutio\\util\BinaryToRealVectorPhenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
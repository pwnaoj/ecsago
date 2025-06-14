/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Weight
/*    */ {
/*    */   public boolean binarized = false;
/* 16 */   public double sumWeight = 0.0D;
/* 17 */   public double sumWeidis = 0.0D;
/*    */   
/* 19 */   public double sumWeidisSqr = 0.0D;
/*    */   
/*    */   public Weight(boolean _binarized) {
/* 22 */     this.binarized = _binarized;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getWeight(double d, Set set) {
/* 27 */     return set.evaluate(d);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 31 */     this.sumWeight = 0.0D;
/* 32 */     this.sumWeidis = 0.0D;
/* 33 */     this.sumWeidisSqr = 0.0D;
/*    */   }
/*    */   
/*    */   public abstract void apply(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, Set[] paramArrayOfSet, Set paramSet);
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\Weight.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
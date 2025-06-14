/*    */ package unc.restriction;
/*    */ 
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OriginalMatingRestriction
/*    */   extends SameNiche
/*    */ {
/* 15 */   protected double minFitness = 0.0D;
/* 16 */   protected double thFitness = 0.0D;
/*    */   
/*    */   public OriginalMatingRestriction(double _K, int _soft, double _minFitness, QuasiMetric _metric) {
/* 19 */     super(_K, _soft, _metric);
/* 20 */     this.minFitness = _minFitness;
/* 21 */     this.thFitness = _minFitness;
/*    */   }
/*    */   
/*    */   public OriginalMatingRestriction(double _K, int _soft, double _minFitness, double _T, QuasiMetric _metric) {
/* 25 */     super(_K, _soft, _T, _metric);
/* 26 */     this.minFitness = _minFitness;
/* 27 */     this.thFitness = _minFitness;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 34 */     boolean samecluster = super.satisfy(c1, c2);
/* 35 */     if (!samecluster && c1.getFitness() >= this.minFitness && c2.getFitness() >= this.minFitness)
/*    */     {
/* 37 */       return false;
/*    */     }
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMinFitness(double _minFitness) {
/* 44 */     this.minFitness = _minFitness * this.thFitness;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\OriginalMatingRestriction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
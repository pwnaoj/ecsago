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
/*    */ public class OriginalReplaceRestriction
/*    */   extends SameNiche
/*    */ {
/*    */   protected double minFitness;
/*    */   
/*    */   public OriginalReplaceRestriction(double _K, int _soft, QuasiMetric _metric) {
/* 18 */     super(_K, _soft, _metric);
/*    */   }
/*    */   
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 22 */     return (super.satisfy(c1, c2) || c1.getFitness() > c2.getFitness());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMinFitness(double _minFitness) {
/* 31 */     this.minFitness = _minFitness;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\OriginalReplaceRestriction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
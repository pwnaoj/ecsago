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
/*    */ public class SharingRestriction
/*    */   extends Restriction
/*    */ {
/*    */   protected double sigma;
/*    */   protected double PF;
/*    */   protected boolean climbSigma = false;
/* 18 */   protected QuasiMetric metric = null;
/*    */ 
/*    */   
/*    */   public SharingRestriction(double _sigma, double _PF, QuasiMetric _metric) {
/* 22 */     this.sigma = _sigma;
/* 23 */     this.PF = _PF;
/* 24 */     this.metric = _metric;
/*    */   }
/*    */   
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 28 */     double distCenters = this.metric.distance(c1.getCenter(), c2.getCenter());
/* 29 */     return (distCenters <= this.PF * this.sigma * this.PF * this.sigma);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\SharingRestriction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
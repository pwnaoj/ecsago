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
/*    */ 
/*    */ 
/*    */ public class DistanceRestriction
/*    */   extends Restriction
/*    */ {
/* 17 */   protected QuasiMetric metric = null;
/* 18 */   protected double distance = 0.0D;
/*    */   
/*    */   public DistanceRestriction(QuasiMetric _metric, double _distance) {
/* 21 */     this.metric = _metric;
/* 22 */     this.distance = _distance;
/*    */   }
/*    */   
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 26 */     return (this.metric.distance(c1.getCenter(), c2.getCenter()) < this.distance);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\DistanceRestriction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
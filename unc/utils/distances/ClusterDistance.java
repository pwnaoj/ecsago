/*    */ package unc.utils.distances;
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
/*    */ public class ClusterDistance
/*    */   extends QuasiMetric
/*    */ {
/*    */   protected QuasiMetric metric;
/*    */   
/*    */   public ClusterDistance(QuasiMetric _metric) {
/* 19 */     this.metric = _metric;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double distance(Object one, Object two) {
/* 31 */     if (one instanceof Cluster) one = ((Cluster)one).getCenter(); 
/* 32 */     if (two instanceof Cluster) two = ((Cluster)two).getCenter(); 
/* 33 */     return this.metric.distance(one, two);
/*    */   }
/*    */   
/*    */   public static QuasiMetric generate(QuasiMetric metric) {
/* 37 */     if (metric instanceof ClusterDistance) {
/* 38 */       return metric;
/*    */     }
/* 40 */     return new ClusterDistance(metric);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\distances\ClusterDistance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
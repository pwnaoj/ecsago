/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.data.Data;
/*    */ import jml.evolution.Fitness;
/*    */ import jml.evolution.Individual;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.utils.DistanceSize;
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
/*    */ public abstract class ClusterFitness
/*    */   extends Fitness
/*    */ {
/*    */   protected DistanceSize data;
/*    */   protected QuasiMetric metric;
/*    */   
/*    */   public ClusterFitness(DistanceSize _data, QuasiMetric _metric) {
/* 28 */     this.metric = _metric;
/* 29 */     this.data = _data;
/*    */   }
/*    */   public void setData(DistanceSize _data) {
/* 32 */     this.data = _data;
/*    */   } public DistanceSize getData() {
/* 34 */     return this.data;
/*    */   }
/*    */   public static Object get(Object d) {
/* 37 */     return ((Data)d).get();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateDistances(Cluster x) {
/* 45 */     x.normalization(0);
/* 46 */     this.data.calculate(x, this.metric);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract double evaluate(Cluster paramCluster);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double evaluate(Individual individual) {
/* 60 */     Cluster c = (Cluster)individual.getThing();
/* 61 */     double f = evaluate(c);
/* 62 */     c.setFitness(f);
/* 63 */     return f;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\ClusterFitness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
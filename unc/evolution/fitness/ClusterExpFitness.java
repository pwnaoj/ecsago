/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.sets.SqrGaussianSet;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClusterExpFitness
/*    */   extends ClusterFitness
/*    */ {
/*    */   protected Weight w;
/*    */   
/*    */   public ClusterExpFitness(DistanceSize _data, QuasiMetric _metric, Weight _w) {
/* 32 */     super(_data, _metric);
/* 33 */     this.w = _w;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double calculateSumWeight(Set set) {
/* 42 */     this.w.apply(this.data.getDistance(), this.data.getSize(), this.data.getSet(), set);
/* 43 */     return this.w.sumWeight;
/*    */   }
/*    */   
/*    */   public double getSigma(Cluster cluster) {
/* 47 */     return ((SqrGaussianSet)cluster.getSet()).getSqrSigma();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double evaluate(Cluster ind) {
/* 57 */     double fitness, sigma = getSigma(ind);
/*    */     
/* 59 */     Object center = ind.getCenter();
/*    */ 
/*    */     
/* 62 */     calculateDistances(ind);
/*    */ 
/*    */     
/* 65 */     double sumWeight = calculateSumWeight(ind.getSet());
/*    */ 
/*    */ 
/*    */     
/* 69 */     if (sigma == 0.0D) { fitness = 0.0D; }
/* 70 */     else { fitness = sumWeight / sigma; }
/*    */ 
/*    */     
/* 73 */     return fitness;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\ClusterExpFitness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
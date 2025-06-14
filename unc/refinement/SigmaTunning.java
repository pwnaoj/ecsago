/*    */ package unc.refinement;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.evolution.fitness.Weight;
/*    */ import unc.sets.SqrGaussianSet;
/*    */ import unc.utils.DistanceSize;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SigmaTunning
/*    */   extends ClusterTunning
/*    */ {
/*    */   protected QuasiMetric metric;
/*    */   protected DistanceSize data;
/*    */   protected double min_sigma;
/*    */   protected double max_sigma;
/*    */   protected Weight w;
/*    */   
/*    */   public SigmaTunning(DistanceSize _data, Weight _w, QuasiMetric _metric, double _min_sigma, double _max_sigma) {
/* 26 */     this.metric = _metric;
/* 27 */     this.data = _data;
/* 28 */     this.w = _w;
/* 29 */     this.min_sigma = _min_sigma;
/* 30 */     this.max_sigma = _max_sigma;
/*    */   }
/*    */   
/*    */   public void setData(DistanceSize _data) {
/* 34 */     this.data = _data;
/*    */   }
/*    */   
/*    */   public double getSigma(Cluster cluster) {
/* 38 */     return ((SqrGaussianSet)cluster.getSet()).getSqrSigma();
/*    */   }
/*    */   
/*    */   public void setSigma(Cluster cluster, double sigma) {
/* 42 */     ((SqrGaussianSet)cluster.getSet()).setSqrSigma(sigma);
/*    */   }
/*    */   
/*    */   public void tune(Cluster cluster) {
/* 46 */     this.data.calculate(cluster, this.metric);
/* 47 */     double[] distances = this.data.getDistance();
/* 48 */     double[] size = this.data.getSize();
/* 49 */     Set[] sets = this.data.getSet();
/*    */     
/* 51 */     this.w.apply(distances, size, sets, cluster.getSet());
/*    */     
/* 53 */     double sigma = getSigma(cluster);
/* 54 */     if (this.debug) System.out.print(this.w.sumWeight + " : " + sigma);
/*    */     
/* 56 */     if (this.w.sumWeight == 0.0D) { sigma = 0.0D; }
/* 57 */     else { sigma = this.w.sumWeidis / this.w.sumWeight; }
/* 58 */      if (this.debug) System.out.println("-->" + sigma);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     if (sigma > this.max_sigma) { sigma = this.max_sigma; }
/* 65 */     else if (sigma < this.min_sigma) { sigma = this.min_sigma; }
/*    */ 
/*    */ 
/*    */     
/* 69 */     setSigma(cluster, sigma);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\refinement\SigmaTunning.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
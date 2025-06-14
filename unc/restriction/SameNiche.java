/*    */ package unc.restriction;
/*    */ 
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.sets.SqrGaussianSet;
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
/*    */ public class SameNiche
/*    */   extends Restriction
/*    */ {
/*    */   public static final int FIRST_CLUSTER = 0;
/*    */   public static final int SECOND_CLUSTER = 1;
/*    */   public static final int BOTH_CLUSTER = 2;
/*    */   public static final int AT_LEAST_ONE_CLUSTER = 3;
/* 31 */   protected int soft = 3;
/* 32 */   protected double threshold = 1.0D;
/*    */   protected double K;
/* 34 */   protected QuasiMetric metric = null;
/*    */   
/*    */   public SameNiche(double _K, int _soft, QuasiMetric _metric) {
/* 37 */     this.K = _K;
/* 38 */     this.soft = _soft;
/* 39 */     this.metric = _metric;
/*    */   }
/*    */   
/*    */   public SameNiche(double _K, int _soft, double _threshold, QuasiMetric _metric) {
/* 43 */     this.K = _K;
/* 44 */     this.soft = _soft;
/* 45 */     this.threshold = _threshold;
/* 46 */     this.metric = _metric;
/*    */   }
/*    */   
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 50 */     double d = this.metric.distance(c1.getCenter(), c2.getCenter());
/* 51 */     SqrGaussianSet set1 = (SqrGaussianSet)c1.getSet();
/* 52 */     SqrGaussianSet set2 = (SqrGaussianSet)c2.getSet();
/* 53 */     double sqr_sigma1 = set1.getSqrSigma();
/* 54 */     double sqr_sigma2 = set2.getSqrSigma();
/*    */ 
/*    */     
/* 57 */     if (d <= this.threshold);
/*    */ 
/*    */ 
/*    */     
/* 61 */     switch (this.soft) {
/*    */       case 0:
/* 63 */         return (d <= this.threshold * this.K * sqr_sigma1);
/*    */       case 1:
/* 65 */         return (d <= this.threshold * this.K * sqr_sigma2);
/*    */       case 2:
/* 67 */         return (d <= this.threshold * this.K * Math.min(sqr_sigma1, sqr_sigma2));
/*    */       case 3:
/* 69 */         return (d <= this.threshold * this.K * Math.max(sqr_sigma1, sqr_sigma2));
/*    */     } 
/* 71 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\SameNiche.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
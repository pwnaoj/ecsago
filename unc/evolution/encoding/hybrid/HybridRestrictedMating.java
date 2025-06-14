/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.basics.Cloner;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import jml.fuzzy.Set;
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
/*    */ public class HybridRestrictedMating
/*    */   extends HybridArityTwo
/*    */ {
/*    */   double sigma_factor;
/*    */   QuasiMetric metric;
/*    */   
/*    */   public HybridRestrictedMating(Environment env, ArityTwo inner, double _sigma_factor, QuasiMetric _metric) {
/* 27 */     super(env, inner);
/* 28 */     this.sigma_factor = _sigma_factor;
/* 29 */     this.metric = _metric;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj1, Object obj2) {
/* 39 */     Cluster c1 = (Cluster)obj1;
/* 40 */     Cluster c2 = (Cluster)obj2;
/* 41 */     double dist = this.metric.distance(c1.getCenter(), c2.getCenter());
/*    */     
/* 43 */     double sigma1 = ((SqrGaussianSet)c1.getSet()).getSqrSigma();
/* 44 */     double sigma2 = ((SqrGaussianSet)c2.getSet()).getSqrSigma();
/* 45 */     if (dist < this.sigma_factor * Math.max(sigma1, sigma2))
/*    */     {
/* 47 */       return super.apply(c1, c2);
/*    */     }
/* 49 */     c2.setCenter(c1.getCenter());
/* 50 */     c2.setSet((Set)Cloner.clone(c1.getSet()));
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridRestrictedMating.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
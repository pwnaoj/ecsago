/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import jml.evolution.real.RealVectorLimits;
/*    */ import jml.evolution.real.operators.GaussianMutation;
/*    */ import jml.fuzzy.sets.GaussianSet;
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
/*    */ 
/*    */ 
/*    */ public class HybridGaussianMutation
/*    */   extends HybridArityOne
/*    */ {
/*    */   public HybridGaussianMutation(Environment env, RealVectorLimits _limits) {
/* 24 */     super(env, (ArityOne)new GaussianMutation(env, _limits, 0.0D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 34 */     Cluster c = (Cluster)obj;
/* 35 */     double sigma = ((GaussianSet)c.getSet()).getSigma();
/* 36 */     ((GaussianMutation)this.oper).setSigma(sigma);
/* 37 */     return super.apply(obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridGaussianMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
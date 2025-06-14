/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.real.RealVectorLimits;
/*    */ import jml.evolution.real.operators.GaussianMutation;
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
/*    */ public class UNCGaussianMutation
/*    */   extends GaussianMutation
/*    */ {
/*    */   public UNCGaussianMutation(Environment env, RealVectorLimits _limits) {
/* 25 */     super(env, _limits, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Individual obj) {
/* 35 */     Cluster c = (Cluster)obj.getThing(this.environment);
/* 36 */     double xsigma = ((SqrGaussianSet)c.getSet()).getSigma();
/* 37 */     xsigma /= 4.0D;
/* 38 */     this.sigma = new RealVector(this.limits.min.dimension(), xsigma);
/* 39 */     return apply(obj.getGenome());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\UNCGaussianMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
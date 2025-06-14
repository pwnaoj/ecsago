/*    */ package jml.evolution.real;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Genotype;
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
/*    */ public class RealVectorGenotype
/*    */   extends Genotype
/*    */ {
/*    */   protected RealVectorLimits real_limits;
/*    */   protected GenomeLimits limits;
/*    */   
/*    */   public RealVectorGenotype(GenomeLimits _limits, RealVectorLimits _real_limits) {
/* 42 */     this.limits = _limits;
/* 43 */     this.real_limits = _real_limits;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object newInstance() {
/* 51 */     int n = this.limits.random_genes_number();
/* 52 */     return new RealVector(n, this.real_limits.min, this.real_limits.max);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size(Object genome) {
/* 60 */     return ((RealVector)genome).dimension();
/*    */   }
/*    */   
/* 63 */   public RealVectorLimits getRealLimits() { return this.real_limits; } public GenomeLimits getLimits() {
/* 64 */     return this.limits;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\RealVectorGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
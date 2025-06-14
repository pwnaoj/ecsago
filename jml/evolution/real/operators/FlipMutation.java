/*    */ package jml.evolution.real.operators;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import jml.evolution.real.RealVectorLimits;
/*    */ import jml.random.UniformNumberGenerator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlipMutation
/*    */   extends ArityOne
/*    */ {
/*    */   RealVectorLimits limits;
/*    */   
/*    */   public FlipMutation(Environment _environment, RealVectorLimits _limits) {
/* 48 */     super(_environment);
/* 49 */     this.limits = _limits;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object gen) {
/* 60 */     RealVector genome = (RealVector)gen;
/* 61 */     int pos = -1;
/*    */     
/* 63 */     try { UniformNumberGenerator s = new UniformNumberGenerator(genome.dimension());
/* 64 */       pos = s.newInt();
/* 65 */       double x = genome.get(pos);
/* 66 */       double min = this.limits.min.get(pos);
/* 67 */       double max = this.limits.max.get(pos);
/* 68 */       genome.set(pos, min + max - x); }
/* 69 */     catch (Exception e) { System.err.println("[Flip Mutation]" + e.getMessage()); }
/* 70 */      return new Integer(pos);
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\FlipMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
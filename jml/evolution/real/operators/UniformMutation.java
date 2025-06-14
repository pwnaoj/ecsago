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
/*    */ 
/*    */ public class UniformMutation
/*    */   extends ArityOne
/*    */ {
/* 46 */   protected UniformNumberGenerator g = null;
/*    */ 
/*    */   
/*    */   protected RealVectorLimits limits;
/*    */ 
/*    */   
/*    */   public UniformMutation(Environment _environment, RealVectorLimits _limits) {
/* 53 */     super(_environment);
/* 54 */     this.limits = _limits;
/* 55 */     this.g = new UniformNumberGenerator(0.0D, 1.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object gen) {
/* 65 */     RealVector genome = (RealVector)gen;
/* 66 */     RealVector min = this.limits.min;
/* 67 */     RealVector max = this.limits.max;
/* 68 */     int pos = -1;
/*    */     
/* 70 */     try { UniformNumberGenerator s = new UniformNumberGenerator(genome.dimension());
/* 71 */       pos = s.newInt();
/* 72 */       double x = genome.get(pos);
/* 73 */       this.g.set(min.get(pos), max.get(pos));
/* 74 */       genome.set(pos, this.g.newDouble()); }
/* 75 */     catch (Exception e) { System.err.println("[Guassian Mutation]" + e.getMessage()); }
/* 76 */      return new Integer(pos);
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\UniformMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
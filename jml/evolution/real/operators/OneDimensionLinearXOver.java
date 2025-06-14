/*    */ package jml.evolution.real.operators;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import jml.random.NumberGenerator;
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
/*    */ public class OneDimensionLinearXOver
/*    */   extends ArityTwo
/*    */ {
/*    */   public OneDimensionLinearXOver(Environment _environment) {
/* 47 */     super(_environment);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OneDimensionLinearXOver(Environment _environment, Selection _selection) {
/* 57 */     super(_environment, _selection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object c1, Object c2) {
/* 68 */     RealVector one = (RealVector)c1;
/* 69 */     RealVector two = (RealVector)c2;
/*    */     
/* 71 */     int min = Math.min(one.dimension(), two.dimension());
/*    */     
/* 73 */     UniformNumberGenerator g = new UniformNumberGenerator(min);
/* 74 */     int pos = g.newInt();
/*    */     
/* 76 */     double alpha = NumberGenerator.random();
/* 77 */     double alpha_1 = 1.0D - alpha;
/*    */     
/* 79 */     double tx = one.get(pos);
/* 80 */     double ty = two.get(pos);
/* 81 */     one.set(pos, alpha * tx + alpha_1 * ty);
/* 82 */     two.set(pos, alpha_1 * tx + alpha * ty);
/* 83 */     return new Double(alpha);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\OneDimensionLinearXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
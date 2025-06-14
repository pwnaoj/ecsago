/*    */ package jml.evolution.real.operators;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.operators.ArityTwo;
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
/*    */ 
/*    */ 
/*    */ public class OneDimensionSimpleXOver
/*    */   extends ArityTwo
/*    */ {
/*    */   public OneDimensionSimpleXOver(Environment _environment) {
/* 49 */     super(_environment);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OneDimensionSimpleXOver(Environment _environment, Selection _selection) {
/* 59 */     super(_environment, _selection);
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
/* 70 */     RealVector one = (RealVector)c1;
/* 71 */     RealVector two = (RealVector)c2;
/*    */     
/* 73 */     int min = Math.min(one.dimension(), two.dimension());
/* 74 */     UniformNumberGenerator g = new UniformNumberGenerator(min);
/* 75 */     int pos = g.newInt();
/* 76 */     double t = one.get(pos);
/* 77 */     one.set(pos, two.get(pos));
/* 78 */     two.set(pos, t);
/* 79 */     return new Integer(pos);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\OneDimensionSimpleXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
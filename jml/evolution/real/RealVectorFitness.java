/*    */ package jml.evolution.real;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.evolution.Fitness;
/*    */ import jml.evolution.Individual;
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
/*    */ 
/*    */ 
/*    */ public abstract class RealVectorFitness
/*    */   extends Fitness
/*    */ {
/*    */   public abstract double eval(RealVector paramRealVector);
/*    */   
/*    */   public double evaluate(Individual obj) {
/* 51 */     return eval((RealVector)obj.getThing());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\RealVectorFitness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
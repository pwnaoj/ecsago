/*    */ package jml.evolution;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Fitness
/*    */ {
/*    */   protected boolean non_stationary = false;
/*    */   
/*    */   public abstract double evaluate(Individual paramIndividual);
/*    */   
/*    */   public boolean isNonStationary() {
/* 51 */     return this.non_stationary;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Fitness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.evolution;
/*    */ 
/*    */ import jml.basics.Result;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Transformation
/*    */ {
/*    */   public void init() {}
/*    */   
/*    */   public abstract Population apply(Population paramPopulation);
/*    */   
/*    */   public Result statistics(Population population) {
/* 57 */     return population.statistics();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Transformation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
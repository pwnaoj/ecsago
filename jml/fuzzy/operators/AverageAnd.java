/*    */ package jml.fuzzy.operators;
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
/*    */ public class AverageAnd
/*    */   extends And
/*    */ {
/*    */   public double evaluate(double x, double y) {
/* 47 */     return (x != 0.0D && y != 0.0D) ? (2.0D * x * y / (x + y)) : 0.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\operators\AverageAnd.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
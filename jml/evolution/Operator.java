/*    */ package jml.evolution;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Operator
/*    */ {
/*    */   protected Environment environment;
/*    */   
/*    */   public Operator(Environment _environment) {
/* 45 */     this.environment = _environment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Vector apply(Population paramPopulation, int paramInt);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract int getArity();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Environment getEnvironment() {
/* 63 */     return this.environment;
/*    */   }
/*    */   public void setEnvironment(Environment _environment) {
/* 66 */     this.environment = _environment;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Operator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
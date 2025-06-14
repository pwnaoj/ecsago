/*    */ package unc.refinement;
/*    */ 
/*    */ import jml.basics.Algorithm;
/*    */ import jml.basics.Result;
/*    */ import unc.Prototype;
/*    */ 
/*    */ 
/*    */ public abstract class Refinement
/*    */   extends Algorithm
/*    */ {
/*    */   public abstract Prototype iteration(Prototype paramPrototype);
/*    */   
/*    */   public abstract Prototype apply(Prototype paramPrototype);
/*    */   
/*    */   public void init() {}
/*    */   
/*    */   public Result getResult() {
/* 18 */     return null;
/*    */   }
/*    */   
/*    */   public void run() {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\refinement\Refinement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
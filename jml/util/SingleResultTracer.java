/*    */ package jml.util;
/*    */ 
/*    */ import jml.basics.Algorithm;
/*    */ import jml.basics.Result;
/*    */ import jml.basics.Tracer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleResultTracer
/*    */   implements Tracer
/*    */ {
/* 19 */   protected Result result = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(Algorithm alg, Object obj) {
/* 24 */     this.result = (Result)obj;
/*    */   }
/*    */   public Result getStat() {
/* 27 */     return this.result;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\SingleResultTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
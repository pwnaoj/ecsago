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
/*    */ public class IterativeTracer
/*    */   implements Tracer
/*    */ {
/* 19 */   protected IterativeAlgorithmStatistics trace_statistics = null;
/*    */   
/*    */   public IterativeTracer() {
/* 22 */     this.trace_statistics = new IterativeAlgorithmStatistics();
/*    */   }
/*    */   
/*    */   public void add(Algorithm alg, Object obj) {
/* 26 */     this.trace_statistics.add((Result)obj);
/*    */   }
/*    */   public Result getStat() {
/* 29 */     return this.trace_statistics;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\IterativeTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
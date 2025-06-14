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
/*    */ public class SimpleConsoleTracer
/*    */   implements Tracer
/*    */ {
/*    */   public void add(Algorithm algorithm, Object obj) {
/* 48 */     System.out.println(obj.toString());
/*    */   }
/*    */   public Result getStat() {
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\SimpleConsoleTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
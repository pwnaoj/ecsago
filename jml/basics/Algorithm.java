/*    */ package jml.basics;
/*    */ 
/*    */ import java.util.Enumeration;
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
/*    */ public abstract class Algorithm
/*    */   extends Thread
/*    */ {
/*    */   public Object input;
/*    */   public boolean done = false;
/*    */   public boolean continueFlag = true;
/* 60 */   public Vector tracer = new Vector();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(Object _input) {
/* 66 */     this.input = _input;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Object output();
/*    */ 
/*    */ 
/*    */   
/*    */   public void addToTrace() {
/* 77 */     if (this.tracer.size() > 0) addToTrace(output());
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addTracer(Tracer t) {
/* 86 */     this.tracer.add(t);
/*    */   }
/*    */   
/*    */   public void setTracers(Vector _tracer) {
/* 90 */     this.tracer = _tracer;
/*    */   }
/*    */   
/*    */   public void addToTrace(Object obj) {
/* 94 */     Enumeration iter = this.tracer.elements();
/* 95 */     while (iter.hasMoreElements()) {
/* 96 */       Tracer t = iter.nextElement();
/* 97 */       t.add(this, obj);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\basics\Algorithm.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
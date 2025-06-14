/*    */ package jml.util;
/*    */ 
/*    */ import jml.basics.Algorithm;
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
/*    */ public class Experiment
/*    */   extends Algorithm
/*    */ {
/* 38 */   protected Result[] stat = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   protected Algorithm algorithm = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   protected int n = 1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Experiment(int _n, Algorithm _algorithm) {
/* 59 */     this.algorithm = _algorithm;
/* 60 */     this.n = _n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init_experiment(int k) {
/* 69 */     this.algorithm.tracer = this.tracer;
/* 70 */     this.algorithm.init(this.input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 78 */     boolean flag = (this.n > 0);
/* 79 */     if (flag) {
/* 80 */       this.stat = new Result[this.n];
/* 81 */       for (int i = 0; flag && i < this.n; i++) {
/* 82 */         init_experiment(i);
/* 83 */         this.algorithm.run();
/* 84 */         this.stat[i] = (Result)this.algorithm.output();
/* 85 */         flag = (this.stat[i] != null);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object output() {
/* 91 */     return new ExperimentResults(this.stat);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\Experiment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
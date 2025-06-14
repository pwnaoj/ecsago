/*     */ package jml.util;
/*     */ 
/*     */ import jml.basics.Algorithm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class IterativeAlgorithm
/*     */   extends Algorithm
/*     */ {
/*  44 */   protected Predicate condition = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected long delay = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithm(Predicate _condition, long _delay) {
/*  58 */     this.condition = _condition;
/*  59 */     this.delay = _delay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithm(Predicate _condition) {
/*  68 */     this.condition = _condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithm() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  81 */     if (this.condition != null) this.condition.init();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void iteration();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithmStatistics createTraceObject() {
/*  96 */     return new IterativeAlgorithmStatistics();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 105 */     if (this.condition != null) {
/* 106 */       addToTrace();
/* 107 */       while (this.condition.evaluate() && this.continueFlag) {
/* 108 */         if (this.delay > 0L)
/* 109 */           try { this; sleep(this.delay); } catch (Exception e) { e.printStackTrace(); }
/*     */            
/* 111 */         iteration();
/* 112 */         addToTrace();
/*     */       } 
/*     */     } 
/* 115 */     this.done = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate getCondition() {
/* 122 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCondition(Predicate _condition) {
/* 129 */     this.condition = _condition;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\IterativeAlgorithm.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.util;
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
/*     */ public class ForLoopCondition
/*     */   extends Predicate
/*     */   implements Cloneable
/*     */ {
/*  39 */   protected int iter = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected int start = -1;
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected int end = 0;
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected int inc = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ForLoopCondition(int _start, int _end, int _inc) {
/*  61 */     this.start = _start;
/*  62 */     this.end = _end;
/*  63 */     this.inc = _inc;
/*  64 */     this.iter = this.start - this.inc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ForLoopCondition(int _start, int _end) {
/*  73 */     this.start = _start;
/*  74 */     this.iter = this.start - this.inc;
/*  75 */     this.end = _end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ForLoopCondition(int _end) {
/*  84 */     this.end = _end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  92 */     return new ForLoopCondition(this.start, this.end, this.inc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int _start, int _end, int _inc) {
/* 100 */     this.start = _start;
/* 101 */     this.end = _end;
/* 102 */     this.inc = _inc;
/* 103 */     this.iter = this.start - this.inc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean evaluate() {
/* 112 */     this.iter += this.inc;
/* 113 */     return (this.iter < this.end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 120 */     this.iter = this.start - this.inc;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\ForLoopCondition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
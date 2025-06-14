/*     */ package jml.fuzzy;
/*     */ 
/*     */ import jml.algebra.RealVector;
/*     */ import jml.fuzzy.operators.Not;
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
/*     */ public class Atomic
/*     */ {
/*     */   public Variable variable;
/*     */   public boolean positive;
/*     */   public Set set;
/*     */   
/*     */   public Atomic(Variable _variable, boolean _positive, Set _set) {
/*  64 */     this.variable = _variable;
/*  65 */     this.positive = _positive;
/*  66 */     this.set = _set;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(RealVector data, Not not) {
/*  76 */     double val = -1.0D;
/*  77 */     if (this.variable != null && this.set != null) {
/*  78 */       val = this.set.evaluate(data.get(this.variable.position));
/*  79 */       if (!this.positive) val = not.evaluate(val); 
/*     */     } 
/*  81 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     String text = "";
/*  90 */     if (this.variable != null && this.set != null) {
/*  91 */       text = this.variable.id + " is ";
/*  92 */       if (!this.positive) text = text + "not "; 
/*  93 */       text = text + this.set.id;
/*     */     } 
/*  95 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double volume() {
/* 107 */     double v = 0.0D;
/* 108 */     if (this.set != null)
/* 109 */       if (this.positive) { v = this.set.getSize(); }
/* 110 */       else { v = 1.0D - this.set.getSize(); }
/*     */        
/* 112 */     return v;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Atomic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.util;
/*     */ 
/*     */ import jml.basics.Cloner;
/*     */ import jml.basics.Result;
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
/*     */ public class ExperimentResults
/*     */   extends Result
/*     */   implements Cloneable
/*     */ {
/*  43 */   protected Result average = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected Result std_deviation = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected Result[] stat = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExperimentResults(Result[] _stat) {
/*  60 */     this.stat = _stat;
/*  61 */     if (this.stat != null) {
/*  62 */       this.average = Result.average(this.stat);
/*  63 */       this.std_deviation = Result.std_deviation(this.stat, this.average);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExperimentResults(Result _average, Result _std_deviation) {
/*  73 */     this.average = _average;
/*  74 */     this.std_deviation = _std_deviation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  83 */     Result avg = null;
/*  84 */     if (this.average != null) avg = (Result)Cloner.clone(this.average); 
/*  85 */     Result vrc = null;
/*  86 */     if (this.std_deviation != null) vrc = (Result)Cloner.clone(this.std_deviation); 
/*  87 */     return new ExperimentResults(avg, vrc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(Result _other) {
/*  95 */     if (_other instanceof ExperimentResults) {
/*  96 */       ExperimentResults other = (ExperimentResults)_other;
/*  97 */       if (this.average != null) this.average.sum(other.average); 
/*  98 */       if (this.std_deviation != null) this.std_deviation.sum(other.std_deviation);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(Result _other) {
/* 107 */     if (_other instanceof ExperimentResults) {
/* 108 */       ExperimentResults other = (ExperimentResults)_other;
/* 109 */       if (this.average != null) this.average.substract(other.average); 
/* 110 */       if (this.std_deviation != null) this.std_deviation.substract(other.std_deviation);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void divide(double n) {
/* 119 */     if (this.average != null) this.average.divide(n); 
/* 120 */     if (this.std_deviation != null) this.std_deviation.divide(n);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void square() {
/* 127 */     if (this.average != null) this.average.square(); 
/* 128 */     if (this.std_deviation != null) this.std_deviation.square();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sqrt() {
/* 135 */     if (this.average != null) this.average.sqrt(); 
/* 136 */     if (this.std_deviation != null) this.std_deviation.sqrt();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 144 */     StringBuffer sb = new StringBuffer();
/* 145 */     sb.append(this.average.toString());
/* 146 */     sb.append(this.std_deviation.toString());
/* 147 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result[] getStat() {
/* 154 */     return this.stat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Result getAvg() {
/* 160 */     return this.average;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\ExperimentResults.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
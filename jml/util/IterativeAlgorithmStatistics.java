/*     */ package jml.util;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public class IterativeAlgorithmStatistics
/*     */   extends Result
/*     */   implements Cloneable
/*     */ {
/*  42 */   protected Vector statistics = new Vector();
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithmStatistics() {}
/*     */ 
/*     */   
/*     */   public IterativeAlgorithmStatistics(Vector _statistics) {
/*  50 */     this.statistics = _statistics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IterativeAlgorithmStatistics(IterativeAlgorithmStatistics source) {
/*  58 */     if (source != null && source.statistics != null) {
/*  59 */       this.statistics = new Vector();
/*  60 */       Enumeration iter = source.statistics.elements();
/*  61 */       while (iter.hasMoreElements()) {
/*  62 */         Result r = iter.nextElement();
/*  63 */         this.statistics.add((Result)Cloner.clone(r));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create() {
/*  72 */     return new IterativeAlgorithmStatistics();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  78 */     return new IterativeAlgorithmStatistics(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Result r) {
/*  85 */     this.statistics.add(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFirst(Result r) {
/*  93 */     this.statistics.add(0, r);
/*     */   }
/*     */ 
/*     */   
/*     */   public Result getIterationResult(int i) {
/*  98 */     return this.statistics.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuffer sb = new StringBuffer();
/* 107 */     if (this.statistics != null) {
/* 108 */       int i = 0;
/* 109 */       Enumeration iter = this.statistics.elements();
/* 110 */       while (iter.hasMoreElements()) {
/* 111 */         Result r = iter.nextElement();
/* 112 */         sb.append(i);
/* 113 */         sb.append(r.toString());
/* 114 */         sb.append('\n');
/* 115 */         i++;
/*     */       } 
/*     */     } 
/* 118 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(Result _other) {
/* 127 */     if (_other instanceof IterativeAlgorithmStatistics) {
/* 128 */       IterativeAlgorithmStatistics other = (IterativeAlgorithmStatistics)_other;
/* 129 */       if (this.statistics != null) {
/* 130 */         int s = this.statistics.size();
/* 131 */         int s2 = other.statistics.size();
/* 132 */         Enumeration iter = this.statistics.elements();
/* 133 */         Enumeration iter2 = other.statistics.elements();
/* 134 */         Result r = null;
/* 135 */         Result r2 = null;
/* 136 */         while (iter.hasMoreElements() && iter2.hasMoreElements()) {
/* 137 */           r = iter.nextElement();
/* 138 */           r2 = iter2.nextElement();
/* 139 */           r.sum(r2);
/*     */         } 
/* 141 */         if (s < s2) {
/* 142 */           while (iter2.hasMoreElements()) {
/* 143 */             r2 = iter2.nextElement();
/* 144 */             Result r3 = (Result)Cloner.clone(r);
/* 145 */             r3.sum(r2);
/* 146 */             this.statistics.add(r3);
/*     */           } 
/*     */         } else {
/* 149 */           while (iter.hasMoreElements()) {
/* 150 */             r = iter.nextElement();
/* 151 */             r.sum(r2);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(Result _other) {
/* 163 */     if (_other instanceof IterativeAlgorithmStatistics) {
/* 164 */       IterativeAlgorithmStatistics other = (IterativeAlgorithmStatistics)_other;
/* 165 */       if (this.statistics != null) {
/* 166 */         int s = this.statistics.size();
/* 167 */         int s2 = other.statistics.size();
/* 168 */         Enumeration iter = this.statistics.elements();
/* 169 */         Enumeration iter2 = other.statistics.elements();
/* 170 */         Result r = null;
/* 171 */         Result r2 = null;
/* 172 */         while (iter.hasMoreElements() && iter2.hasMoreElements()) {
/* 173 */           r = iter.nextElement();
/* 174 */           r2 = iter2.nextElement();
/* 175 */           r.substract(r2);
/*     */         } 
/* 177 */         if (s < s2) {
/* 178 */           while (iter2.hasMoreElements()) {
/* 179 */             r2 = iter2.nextElement();
/* 180 */             Result r3 = (Result)Cloner.clone(r);
/* 181 */             r3.substract(r2);
/* 182 */             this.statistics.add(r3);
/*     */           } 
/*     */         } else {
/* 185 */           while (iter.hasMoreElements()) {
/* 186 */             r = iter.nextElement();
/* 187 */             r.substract(r2);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void divide(double n) {
/* 199 */     if (this.statistics != null) {
/* 200 */       Enumeration iter = this.statistics.elements();
/* 201 */       while (iter.hasMoreElements()) {
/* 202 */         Result r = iter.nextElement();
/* 203 */         r.divide(n);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void square() {
/* 212 */     if (this.statistics != null) {
/* 213 */       Enumeration iter = this.statistics.elements();
/* 214 */       while (iter.hasMoreElements()) {
/* 215 */         Result r = iter.nextElement();
/* 216 */         r.square();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sqrt() {
/* 225 */     if (this.statistics != null) {
/* 226 */       Enumeration iter = this.statistics.elements();
/* 227 */       while (iter.hasMoreElements()) {
/* 228 */         Result r = iter.nextElement();
/* 229 */         r.sqrt();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 239 */     return this.statistics.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\IterativeAlgorithmStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
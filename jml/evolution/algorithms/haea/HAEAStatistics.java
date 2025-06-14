/*     */ package jml.evolution.algorithms.haea;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.basics.Result;
/*     */ import jml.evolution.PopulationStatistics;
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
/*     */ public class HAEAStatistics
/*     */   extends PopulationStatistics
/*     */   implements Cloneable
/*     */ {
/*  46 */   protected double[] opers = null;
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected double[] avg_opers = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HAEAStatistics(int opers_number, PopulationStatistics popStat) {
/*  58 */     super(popStat);
/*  59 */     this.opers = new double[opers_number];
/*  60 */     this.avg_opers = new double[opers_number];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HAEAStatistics(Vector rates, PopulationStatistics popStat) {
/*  70 */     super(popStat);
/*  71 */     if (rates != null && rates.size() > 0) {
/*  72 */       int bestIndex = -1;
/*  73 */       if (popStat != null) bestIndex = popStat.getBestIndex(); 
/*  74 */       int opers_number = ((RealVector)rates.get(0)).dimension();
/*  75 */       this.opers = new double[opers_number];
/*  76 */       this.avg_opers = new double[opers_number];
/*  77 */       for (int j = 0; j < opers_number; j++) {
/*  78 */         if (bestIndex != -1) {
/*  79 */           this.opers[j] = ((RealVector)rates.get(bestIndex)).get(j);
/*     */         } else {
/*  81 */           this.opers[j] = 0.0D;
/*     */         } 
/*  83 */         this.avg_opers[j] = 0.0D;
/*  84 */         for (int i = 0; i < rates.size(); i++) {
/*  85 */           this.avg_opers[j] = this.avg_opers[j] + ((RealVector)rates.get(i)).get(j);
/*     */         }
/*  87 */         this.avg_opers[j] = this.avg_opers[j] / rates.size();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HAEAStatistics(PopulationStatistics source) {
/*  97 */     super(source);
/*  98 */     if (source instanceof HAEAStatistics && ((HAEAStatistics)source).opers != null) {
/*  99 */       HAEAStatistics hsource = (HAEAStatistics)source;
/* 100 */       this.opers = new double[hsource.opers.length];
/* 101 */       this.avg_opers = new double[hsource.avg_opers.length];
/* 102 */       for (int i = 0; i < this.opers.length; i++) {
/* 103 */         this.opers[i] = hsource.opers[i];
/* 104 */         this.avg_opers[i] = hsource.avg_opers[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create() {
/* 114 */     int n = 0;
/* 115 */     if (this.opers != null) n = this.opers.length; 
/* 116 */     return (Result)new HAEAStatistics(n, (PopulationStatistics)super.create());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 123 */     return new HAEAStatistics(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     StringBuffer sb = new StringBuffer();
/* 131 */     sb.append(super.toString());
/* 132 */     if (this.opers != null) {
/* 133 */       int i; for (i = 0; i < this.opers.length; i++) {
/* 134 */         sb.append(' ');
/* 135 */         sb.append(this.opers[i]);
/*     */       } 
/* 137 */       for (i = 0; i < this.avg_opers.length; i++) {
/* 138 */         sb.append(' ');
/* 139 */         sb.append(this.avg_opers[i]);
/*     */       } 
/*     */     } 
/* 142 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(Result _other) {
/* 150 */     super.sum(_other);
/* 151 */     if (_other instanceof HAEAStatistics) {
/* 152 */       HAEAStatistics other = (HAEAStatistics)_other;
/* 153 */       if (this.opers != null) {
/* 154 */         int i; for (i = 0; i < this.opers.length; i++) {
/* 155 */           this.opers[i] = this.opers[i] + other.opers[i];
/*     */         }
/* 157 */         for (i = 0; i < this.avg_opers.length; i++) {
/* 158 */           this.avg_opers[i] = this.avg_opers[i] + other.avg_opers[i];
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
/* 169 */     super.substract(_other);
/* 170 */     if (_other instanceof HAEAStatistics) {
/* 171 */       HAEAStatistics other = (HAEAStatistics)_other;
/* 172 */       if (this.opers != null) {
/* 173 */         int i; for (i = 0; i < this.opers.length; i++) {
/* 174 */           this.opers[i] = this.opers[i] - other.opers[i];
/*     */         }
/* 176 */         for (i = 0; i < this.avg_opers.length; i++) {
/* 177 */           this.avg_opers[i] = this.avg_opers[i] - other.avg_opers[i];
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
/* 188 */     super.divide(n);
/* 189 */     if (this.opers != null) {
/* 190 */       int i; for (i = 0; i < this.opers.length; i++) {
/* 191 */         this.opers[i] = this.opers[i] / n;
/*     */       }
/* 193 */       for (i = 0; i < this.avg_opers.length; i++) {
/* 194 */         this.avg_opers[i] = this.avg_opers[i] / n;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void square() {
/* 203 */     super.square();
/* 204 */     if (this.opers != null) {
/* 205 */       int i; for (i = 0; i < this.opers.length; i++) {
/* 206 */         this.opers[i] = this.opers[i] * this.opers[i];
/*     */       }
/* 208 */       for (i = 0; i < this.avg_opers.length; i++) {
/* 209 */         this.avg_opers[i] = this.avg_opers[i] * this.avg_opers[i];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sqrt() {
/* 218 */     super.sqrt();
/* 219 */     if (this.opers != null) {
/* 220 */       int i; for (i = 0; i < this.opers.length; i++) {
/* 221 */         this.opers[i] = Math.sqrt(this.opers[i]);
/*     */       }
/* 223 */       for (i = 0; i < this.avg_opers.length; i++)
/* 224 */         this.avg_opers[i] = Math.sqrt(this.avg_opers[i]); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\haea\HAEAStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
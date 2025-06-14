/*     */ package jml.data.transformation;
/*     */ 
/*     */ import jml.algebra.RealVector;
/*     */ import jml.data.Data;
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
/*     */ public class Normalization
/*     */   extends DataTransformation
/*     */ {
/*  41 */   protected double[] min = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected double[] max = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected double[] new_min = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected double[] new_max = null;
/*     */   
/*  59 */   private double[] new_length = null;
/*  60 */   private double[] length = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Normalization(double[] _min, double[] _max) {
/*  69 */     this.min = _min;
/*  70 */     this.max = _max;
/*  71 */     if (this.min != null && this.max != null) {
/*  72 */       int n = this.min.length;
/*  73 */       this.length = new double[n];
/*  74 */       for (int i = 0; i < n; i++) {
/*  75 */         this.length[i] = this.max[i] - this.min[i];
/*     */       }
/*     */     } 
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
/*     */   public Normalization(double[] _min, double[] _max, double[] _new_min, double[] _new_max) {
/*  89 */     this.min = _min;
/*  90 */     this.max = _max;
/*  91 */     this.new_min = _new_min;
/*  92 */     this.new_max = _new_max;
/*  93 */     if (this.min != null && this.max != null && this.new_min != null && this.new_max != null) {
/*  94 */       int n = this.min.length;
/*  95 */       this.length = new double[n];
/*  96 */       this.new_length = new double[n];
/*  97 */       for (int i = 0; i < n; i++) {
/*  98 */         this.length[i] = this.max[i] - this.min[i];
/*  99 */         this.new_length[i] = this.new_max[i] - this.new_min[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(double[] x) {
/* 109 */     int n = x.length;
/* 110 */     if (this.new_length != null) {
/* 111 */       for (int i = 0; i < n; i++) {
/* 112 */         if (x[i] != -1.0E108D && this.max[i] > this.min[i]) {
/* 113 */           x[i] = this.new_min[i] + this.new_length[i] * (x[i] - this.min[i]) / this.length[i];
/*     */         }
/*     */       } 
/*     */     } else {
/* 117 */       for (int i = 0; i < n; i++) {
/* 118 */         if (x[i] != -1.0E108D && this.max[i] > this.min[i]) {
/* 119 */           x[i] = (x[i] - this.min[i]) / this.length[i];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data apply(Data data) {
/* 132 */     RealVector x = (RealVector)data.get();
/* 133 */     double[] c = x.get().full_values();
/* 134 */     apply(c);
/* 135 */     return new Data(new RealVector(c), data.getLabel());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inverse(double[] x) {
/* 143 */     int n = x.length;
/* 144 */     if (this.new_length != null) {
/* 145 */       for (int i = 0; i < n; i++) {
/* 146 */         if (x[i] != -1.0E108D && this.max[i] > this.min[i]) {
/* 147 */           x[i] = this.min[i] + this.length[i] * (x[i] - this.new_min[i]) / this.new_length[i];
/*     */         }
/*     */       } 
/*     */     } else {
/* 151 */       for (int i = 0; i < n; i++) {
/* 152 */         if (x[i] != -1.0E108D && this.max[i] > this.min[i]) {
/* 153 */           x[i] = this.min[i] + this.length[i] * x[i];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data inverse(Data data) {
/* 166 */     RealVector x = (RealVector)data.get();
/* 167 */     double[] c = x.get().full_values();
/* 168 */     inverse(c);
/* 169 */     return new Data(new RealVector(c), data.getLabel());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\Normalization.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
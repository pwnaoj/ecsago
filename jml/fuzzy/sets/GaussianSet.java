/*     */ package jml.fuzzy.sets;
/*     */ 
/*     */ import jml.fuzzy.Set;
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
/*     */ public class GaussianSet
/*     */   extends Set
/*     */   implements Cloneable
/*     */ {
/*  39 */   protected static double[] cumulative_area = null;
/*  40 */   protected static int CUMULATIVE_ARA_SAMPLES = 10001;
/*  41 */   protected static double max_sample = 10.0D;
/*     */   
/*     */   static {
/*  44 */     GaussianSet set = new GaussianSet("", 0.0D, 1.0D);
/*  45 */     double[][] x = set.sample(CUMULATIVE_ARA_SAMPLES, 0.0D, max_sample);
/*  46 */     cumulative_area = new double[CUMULATIVE_ARA_SAMPLES];
/*  47 */     cumulative_area[0] = 0.0D;
/*  48 */     double size = 0.0D;
/*  49 */     for (int i = 0; i < (x[0]).length - 1; i++) {
/*  50 */       size += (x[1][i + 1] + x[1][i]) * (x[0][i + 1] - x[0][i]) / 2.0D;
/*  51 */       cumulative_area[i + 1] = size;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected double mean = 0.0D;
/*     */ 
/*     */ 
/*     */   
/*  62 */   protected double sigma = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianSet(String _id, double _mean, double _sigma) {
/*  72 */     super(_id);
/*  73 */     this.mean = _mean;
/*  74 */     this.sigma = _sigma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  85 */     if (xml_format) {
/*  86 */       text = "mean=\"" + this.mean + "\"  sigma=\"" + this.sigma + "\"";
/*     */     } else {
/*  88 */       text = this.mean + " " + this.sigma;
/*     */     } 
/*  90 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double x) {
/*  99 */     x = normalize(x);
/* 100 */     return Math.exp(-x * x / 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/* 108 */     double[] A = new double[2];
/* 109 */     A[0] = this.mean;
/* 110 */     A[1] = this.sigma;
/* 111 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/* 119 */     double[] A = new double[2];
/* 120 */     A[0] = 0.0D;
/* 121 */     A[1] = 1.0D;
/* 122 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSize() {
/* 132 */     return this.sigma * Math.sqrt(6.283185307179586D);
/*     */   }
/*     */   
/*     */   public double normalize(double x) {
/* 136 */     return (x - this.mean) / this.sigma;
/*     */   }
/*     */   
/*     */   protected double getNormalizeCumulativeArea(double x) {
/* 140 */     x = normalize(x);
/*     */     
/* 142 */     double sign = 1.0D;
/* 143 */     if (x < 0.0D) sign = -1.0D; 
/* 144 */     int index = Math.min(CUMULATIVE_ARA_SAMPLES - 1, (int)(sign * x * CUMULATIVE_ARA_SAMPLES / 10.0D + 0.5D));
/*     */     
/* 146 */     return sign * cumulative_area[index];
/*     */   }
/*     */   
/*     */   public double getSize(double start, double end) {
/* 150 */     if (start >= end) return 0.0D; 
/* 151 */     double a_start = getNormalizeCumulativeArea(start);
/* 152 */     double a_end = getNormalizeCumulativeArea(end);
/* 153 */     return this.sigma * (a_end - a_start);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] x, double[] y) {
/* 162 */     this.mean = x[0];
/* 163 */     this.sigma = x[1];
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 167 */     return new GaussianSet(this.id, this.mean, this.sigma);
/*     */   }
/*     */   
/* 170 */   public void setSigma(double _sigma) { this.sigma = _sigma; } public double getSigma() {
/* 171 */     return this.sigma;
/*     */   }
/*     */   
/*     */   public static void main(String[] argv) {
/* 175 */     GaussianSet set = new GaussianSet("id", 0.0D, 1.0D);
/* 176 */     System.out.print("Size:=" + (set.getSize(-1.0D, 1.0D) / set.getSize()));
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\sets\GaussianSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
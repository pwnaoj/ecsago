/*     */ package unc.sets;
/*     */ 
/*     */ import jml.fuzzy.sets.GaussianSet;
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
/*     */ public class SqrGaussianSet
/*     */   extends GaussianSet
/*     */   implements Cloneable
/*     */ {
/*  39 */   protected double sqr_sigma = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SqrGaussianSet(String _id, double _sqr_sigma) {
/*  47 */     super(_id, 0.0D, Math.sqrt(_sqr_sigma));
/*  48 */     this.sqr_sigma = _sqr_sigma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  58 */     if (xml_format) {
/*  59 */       text = "sqr_sigma=\"" + this.sqr_sigma + "\"";
/*     */     } else {
/*  61 */       text = "" + this.sqr_sigma;
/*     */     } 
/*  63 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double x) {
/*  72 */     return Math.exp(-0.5D * x / this.sqr_sigma);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/*  80 */     double[] A = new double[1];
/*  81 */     A[0] = this.sqr_sigma;
/*  82 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/*  90 */     double[] A = new double[1];
/*  91 */     A[0] = 1.0D;
/*  92 */     return A;
/*     */   }
/*     */   
/*     */   public double getSize(double start, double end) {
/*  96 */     if (start >= end || start < 0.0D) return 0.0D; 
/*  97 */     return super.getSize(Math.sqrt(start), Math.sqrt(end));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] x, double[] y) {
/* 106 */     this.sqr_sigma = x[0];
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 110 */     return new SqrGaussianSet(this.id, this.sqr_sigma);
/*     */   }
/*     */   
/*     */   public void setSigma(double _sigma) {
/* 114 */     super.setSigma(_sigma);
/* 115 */     this.sqr_sigma = this.sigma * this.sigma;
/*     */   }
/*     */   
/*     */   public void setSqrSigma(double _sqr_sigma) {
/* 119 */     this.sqr_sigma = _sqr_sigma;
/* 120 */     super.setSigma(Math.sqrt(this.sqr_sigma));
/*     */   }
/*     */   public double getSqrSigma() {
/* 123 */     return this.sqr_sigma;
/*     */   }
/*     */   public static void main(String[] argv) {
/* 126 */     SqrGaussianSet set = new SqrGaussianSet("id", 1.0D);
/* 127 */     System.out.print("Size:=" + (set.getSize(0.0D, 1.0D) / set.getSize()));
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\sets\SqrGaussianSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
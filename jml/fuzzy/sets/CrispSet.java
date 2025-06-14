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
/*     */ public class CrispSet
/*     */   extends Set
/*     */   implements Cloneable
/*     */ {
/*     */   public double min;
/*     */   public double max;
/*     */   
/*     */   public CrispSet(String _id, double _min, double _max) {
/*  55 */     super(_id);
/*  56 */     this.min = _min;
/*  57 */     this.max = _max;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  67 */     if (xml_format) {
/*  68 */       text = "min=\"" + this.min + "\" max=\"" + this.max + "\"";
/*     */     } else {
/*  70 */       text = this.min + " " + this.max;
/*     */     } 
/*  72 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double x) {
/*  82 */     double v = 0.0D;
/*  83 */     if (this.min < x && x <= this.max) v = 1.0D; 
/*  84 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/*  92 */     double[] A = new double[4];
/*  93 */     A[0] = this.min;
/*  94 */     A[1] = this.min;
/*  95 */     A[2] = this.max;
/*  96 */     A[3] = this.max;
/*  97 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/* 105 */     double[] A = new double[4];
/* 106 */     A[0] = 0.0D;
/* 107 */     A[1] = 1.0D;
/* 108 */     A[2] = 1.0D;
/* 109 */     A[3] = 0.0D;
/* 110 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] x, double[] y) {
/* 120 */     this.min = x[0];
/* 121 */     this.max = x[1];
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 125 */     return new CrispSet(this.id, this.min, this.max);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\sets\CrispSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
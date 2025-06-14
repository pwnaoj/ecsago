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
/*     */ 
/*     */ 
/*     */ public class TriangularSet
/*     */   extends Set
/*     */   implements Cloneable
/*     */ {
/*     */   public double min;
/*     */   public double max;
/*     */   public double half;
/*     */   
/*     */   public TriangularSet(String _id, double _min, double _max) {
/*  58 */     super(_id);
/*  59 */     this.min = _min;
/*  60 */     this.max = _max;
/*  61 */     this.half = (this.max + this.min) / 2.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TriangularSet(String _id, double _min, double _half, double _max) {
/*  71 */     super(_id);
/*  72 */     this.min = _min;
/*  73 */     this.max = _max;
/*  74 */     this.half = _half;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  84 */     if (xml_format) {
/*  85 */       text = "min=\"" + this.min + "\" half=\"" + this.half + "\" max=\"" + this.max + "\"";
/*     */     } else {
/*     */       
/*  88 */       text = this.min + " " + this.half + " " + this.max;
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
/*  99 */     double v = 0.0D;
/* 100 */     if (this.min < x && x < this.max) {
/* 101 */       if (x < this.half) {
/* 102 */         v = (x - this.min) / (this.half - this.min);
/*     */       }
/* 104 */       else if (x > this.half) {
/* 105 */         v = (this.max - x) / (this.max - this.half);
/*     */       } else {
/* 107 */         v = 1.0D;
/*     */       } 
/*     */     }
/*     */     
/* 111 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/* 119 */     double[] A = new double[3];
/* 120 */     A[0] = this.min;
/* 121 */     A[1] = this.half;
/* 122 */     A[2] = this.max;
/* 123 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/* 131 */     double[] A = new double[3];
/* 132 */     A[0] = 0.0D;
/* 133 */     A[1] = 1.0D;
/* 134 */     A[2] = 0.0D;
/* 135 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] x, double[] y) {
/* 145 */     this.min = x[0];
/* 146 */     this.half = x[1];
/* 147 */     this.max = x[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 152 */     return new TriangularSet(this.id, this.min, this.half, this.max);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\sets\TriangularSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
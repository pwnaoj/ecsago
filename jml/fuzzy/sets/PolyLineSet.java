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
/*     */ public class PolyLineSet
/*     */   extends Set
/*     */   implements Cloneable
/*     */ {
/*     */   public double[] x;
/*     */   public double[] y;
/*     */   
/*     */   public PolyLineSet(String _id, double[] _x, double[] _y) {
/*  54 */     super(_id);
/*  55 */     this.x = _x;
/*  56 */     this.y = _y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  66 */     if (xml_format) {
/*  67 */       text = "n=\"" + this.x.length + "\" points=\"";
/*  68 */       StringBuffer sb = new StringBuffer();
/*  69 */       for (int i = 0; i < this.x.length; i++) {
/*  70 */         sb.append(" " + this.x[i] + " " + this.y[i]);
/*     */       }
/*  72 */       text = text + sb.toString() + "\"";
/*     */     } else {
/*  74 */       text = "" + this.x.length;
/*  75 */       StringBuffer sb = new StringBuffer();
/*  76 */       for (int i = 0; i < this.x.length; i++) {
/*  77 */         sb.append(" " + this.x[i] + " " + this.y[i]);
/*     */       }
/*  79 */       text = text + sb.toString();
/*     */     } 
/*  81 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double _x) {
/*  90 */     double v = 0.0D;
/*  91 */     int i = 0;
/*  92 */     for (; i < this.x.length && _x > this.x[i]; i++);
/*  93 */     if (i > 0 && i < this.x.length) {
/*  94 */       v = this.y[i - 1] + (this.y[i] - this.y[i - 1]) * (_x - this.x[i - 1]) / (this.x[i] - this.x[i - 1]);
/*     */     } else {
/*  96 */       if (_x <= this.x[0]) return this.y[0]; 
/*  97 */       return this.y[this.y.length - 1];
/*     */     } 
/*  99 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/* 107 */     double[] cx = new double[this.x.length];
/* 108 */     for (int i = 0; i < this.x.length; ) { cx[i] = this.x[i]; i++; }
/* 109 */      return cx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/* 117 */     double[] cy = new double[this.y.length];
/* 118 */     for (int i = 0; i < this.y.length; ) { cy[i] = this.y[i]; i++; }
/* 119 */      return cy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] _x, double[] _y) {
/* 129 */     this.x = _x;
/* 130 */     this.y = _y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] sample(int S) {
/* 139 */     double[][] X = new double[2][this.x.length];
/* 140 */     for (int i = 0; i < this.x.length; i++) {
/* 141 */       X[0][i] = this.x[i];
/* 142 */       X[1][i] = this.y[i];
/*     */     } 
/* 144 */     return X;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 148 */     return new PolyLineSet(this.id, getX(), getY());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\sets\PolyLineSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
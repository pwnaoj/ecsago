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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TrapezoidSet
/*     */   extends Set
/*     */   implements Cloneable
/*     */ {
/*     */   public double min_top;
/*     */   public double min_bottom;
/*     */   public double max_top;
/*     */   public double max_bottom;
/*     */   
/*     */   public TrapezoidSet(String _id, double minB, double minT, double maxT, double maxB) {
/*  63 */     super(_id);
/*  64 */     this.min_top = minT;
/*  65 */     this.max_top = maxT;
/*  66 */     this.min_bottom = minB;
/*  67 */     this.max_bottom = maxB;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String parameters(boolean xml_format) {
/*     */     String text;
/*  77 */     if (xml_format) {
/*  78 */       text = "min_bottom=\"" + this.min_bottom + "\" min_top=\"" + this.min_top + "\" max_top=\"" + this.max_top + "\" max_bottom=\"" + this.max_bottom + "\"";
/*     */     } else {
/*     */       
/*  81 */       text = this.min_bottom + " " + this.min_top + " " + this.max_top + " " + this.max_bottom;
/*     */     } 
/*  83 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double x) {
/*  92 */     double v = 0.0D;
/*  93 */     if (this.min_bottom <= x && x < this.min_top) {
/*  94 */       v = (x - this.min_bottom) / (this.min_top - this.min_bottom);
/*     */     }
/*  96 */     else if (this.min_top <= x && x <= this.max_top) {
/*  97 */       v = 1.0D;
/*     */     }
/*  99 */     else if (this.max_top < x && x <= this.max_bottom) {
/* 100 */       v = (this.max_bottom - x) / (this.max_bottom - this.max_top);
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getX() {
/* 112 */     double[] A = new double[4];
/* 113 */     A[0] = this.min_bottom;
/* 114 */     A[1] = this.min_top;
/* 115 */     A[2] = this.max_top;
/* 116 */     A[3] = this.max_bottom;
/* 117 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getY() {
/* 125 */     double[] A = new double[4];
/* 126 */     A[0] = 0.0D;
/* 127 */     A[1] = 1.0D;
/* 128 */     A[2] = 1.0D;
/* 129 */     A[3] = 0.0D;
/* 130 */     return A;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoints(double[] x, double[] y) {
/* 139 */     this.min_bottom = x[0];
/* 140 */     this.min_top = x[1];
/* 141 */     this.max_top = x[2];
/* 142 */     this.max_bottom = x[3];
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 146 */     return new TrapezoidSet(this.id, this.min_bottom, this.min_top, this.max_bottom, this.max_top);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\sets\TrapezoidSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.algebra;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.random.UniformNumberGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RealVector
/*     */   implements Cloneable
/*     */ {
/*  42 */   protected Components components = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealVector(int n) {
/*  49 */     if (n < 0) n = 0; 
/*  50 */     this.components = new SparseComponents(n, new Vector());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealVector(int n, double val) {
/*  60 */     if (n < 0) n = 0; 
/*  61 */     double[] c = new double[n];
/*  62 */     for (int i = 0; i < n; ) { c[i] = val; i++; }
/*  63 */      this.components = new FullComponents(c);
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
/*     */   public RealVector(int n, RealVector min, RealVector max) {
/*  75 */     if (n < 0) n = 0; 
/*  76 */     double[] c = new double[n];
/*  77 */     UniformNumberGenerator g = new UniformNumberGenerator();
/*  78 */     if (min != null) {
/*  79 */       for (int i = 0; i < n; i++) {
/*  80 */         c[i] = min.get(i) + g.newDouble() * (max.get(i) - min.get(i));
/*     */       }
/*     */     } else {
/*  83 */       for (int i = 0; i < n; ) { c[i] = g.newDouble(); i++; }
/*     */     
/*  85 */     }  this.components = new FullComponents(c);
/*     */   }
/*     */   
/*     */   public RealVector(Components _components) {
/*  89 */     this.components = _components;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealVector(RealVector source) {
/*  97 */     this.components = (Components)Cloner.clone(source.components);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealVector(double[] _components) {
/* 106 */     this.components = new FullComponents(_components);
/*     */   }
/*     */   public Object clone() {
/* 109 */     return new RealVector(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dimension() {
/* 116 */     return this.components.dimension();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(RealVector y) {
/* 125 */     this.components = this.components.sum(y.components);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(RealVector y) {
/* 134 */     this.components = this.components.substract(y.components);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(double x) {
/* 143 */     this.components = this.components.substract(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(double x) {
/* 152 */     this.components = this.components.sum(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void multiply(double x) {
/* 161 */     this.components = this.components.multiply(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void direct_product(RealVector y) {
/* 170 */     this.components = this.components.direct_product(y.components);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void divide(double x) {
/* 179 */     this.components = this.components.divide(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pow(double x) {
/* 188 */     this.components = this.components.pow(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double get(int i) {
/* 197 */     return this.components.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int i, double x) {
/* 206 */     this.components.set(i, x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components get() {
/* 213 */     return this.components;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Components _components) {
/* 219 */     this.components = _components;
/*     */   }
/*     */   
/* 222 */   public double max() { return this.components.max(); }
/* 223 */   public double min() { return this.components.min(); } public double summation() {
/* 224 */     return this.components.summation();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 231 */     return this.components.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\RealVector.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
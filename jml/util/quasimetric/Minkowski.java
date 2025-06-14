/*     */ package jml.util.quasimetric;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Minkowski
/*     */   extends QuasiMetric
/*     */ {
/*     */   protected double p;
/*     */   
/*     */   public Minkowski(double _p) {
/*  49 */     this.p = _p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distance(Object x, Object y) {
/*  60 */     double s = 0.0D;
/*  61 */     Components rx = ((RealVector)x).get();
/*  62 */     Components ry = ((RealVector)y).get();
/*  63 */     Vector vx = rx.sparce_values();
/*  64 */     Vector vy = ry.sparce_values();
/*  65 */     if (vx == null || vy == null) {
/*  66 */       int n = rx.dimension();
/*  67 */       for (int i = 0; i < n; i++) {
/*  68 */         s += Math.pow(Math.abs(rx.get(i) - ry.get(i)), this.p);
/*     */       }
/*     */     } else {
/*  71 */       Vector v = new Vector();
/*  72 */       SparseValue a = null;
/*  73 */       SparseValue b = null;
/*  74 */       Enumeration iter1 = vx.elements();
/*  75 */       Enumeration iter2 = vy.elements();
/*  76 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/*  77 */         if (a == null) a = iter1.nextElement(); 
/*  78 */         if (b == null) b = iter2.nextElement(); 
/*  79 */         if (a.index == b.index) {
/*  80 */           s += Math.pow(Math.abs(a.value - b.value), this.p);
/*  81 */           a = null;
/*  82 */           b = null; continue;
/*     */         } 
/*  84 */         if (a.index < b.index) {
/*  85 */           s += Math.pow(Math.abs(a.value), this.p);
/*  86 */           a = null; continue;
/*     */         } 
/*  88 */         s += Math.pow(Math.abs(b.value), this.p);
/*  89 */         b = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (a != null) {
/*  95 */         s += Math.pow(Math.abs(a.value), this.p);
/*     */       }
/*     */       
/*  98 */       if (b != null) {
/*  99 */         s += Math.pow(Math.abs(b.value), this.p);
/*     */       }
/*     */       
/* 102 */       while (iter1.hasMoreElements()) {
/* 103 */         a = iter1.nextElement();
/* 104 */         s += Math.pow(Math.abs(a.value), this.p);
/*     */       } 
/* 106 */       while (iter2.hasMoreElements()) {
/* 107 */         b = iter2.nextElement();
/* 108 */         s += Math.pow(Math.abs(b.value), this.p);
/*     */       } 
/*     */     } 
/* 111 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double coeficient() {
/* 119 */     return this.p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double max_0_1(int n) {
/* 128 */     return n;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\quasimetric\Minkowski.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
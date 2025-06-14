/*     */ package unc.utils.distances;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseValue;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cosine
/*     */   extends QuasiMetric
/*     */ {
/*     */   public double distance(Object x, Object y) {
/*  37 */     RealVector rx = (RealVector)x;
/*  38 */     RealVector ry = (RealVector)y;
/*  39 */     Components cx = rx.get();
/*  40 */     Components cy = ry.get();
/*  41 */     Vector sx = cx.sparce_values();
/*  42 */     Vector sy = cy.sparce_values();
/*  43 */     double num = 0.0D;
/*  44 */     double xv = 0.0D;
/*  45 */     double yv = 0.0D;
/*     */ 
/*     */     
/*  48 */     double dis = 0.0D;
/*  49 */     if (sx == null || sy == null) {
/*     */       
/*  51 */       for (int i = 0; i < rx.dimension(); i++) {
/*  52 */         double xx = rx.get(i);
/*  53 */         double yy = ry.get(i);
/*  54 */         num += xx * yy;
/*  55 */         xv += xx * xx;
/*  56 */         yv += yy * yy;
/*     */       } 
/*     */     } else {
/*  59 */       Vector vx = new Vector();
/*  60 */       Vector vy = new Vector();
/*  61 */       SparseValue ax = null;
/*  62 */       SparseValue by = null;
/*  63 */       Enumeration iter1 = sx.elements();
/*  64 */       Enumeration iter2 = sy.elements();
/*  65 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/*  66 */         if (ax == null) ax = iter1.nextElement(); 
/*  67 */         if (by == null) by = iter2.nextElement(); 
/*  68 */         if (ax.index == by.index) {
/*  69 */           double xx = ax.value;
/*  70 */           double yy = by.value;
/*  71 */           num += xx * yy;
/*  72 */           xv += xx * xx;
/*  73 */           yv += yy * yy;
/*  74 */           ax = null;
/*  75 */           by = null; continue;
/*     */         } 
/*  77 */         if (ax.index < by.index) {
/*  78 */           xv += ax.value * ax.value;
/*  79 */           ax = null; continue;
/*     */         } 
/*  81 */         yv += by.value * by.value;
/*  82 */         by = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  87 */       if (ax != null) {
/*  88 */         xv += ax.value * ax.value;
/*     */       }
/*     */       
/*  91 */       if (by != null) {
/*  92 */         yv += by.value * by.value;
/*     */       }
/*     */       
/*  95 */       while (iter1.hasMoreElements()) {
/*  96 */         ax = iter1.nextElement();
/*  97 */         xv += ax.value * ax.value;
/*     */       } 
/*  99 */       while (iter2.hasMoreElements()) {
/* 100 */         by = iter2.nextElement();
/* 101 */         yv += by.value * by.value;
/*     */       } 
/*     */     } 
/* 104 */     double d = 1.0D - num / Math.sqrt(xv * yv);
/* 105 */     return d * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double max_0_1(int n) {
/* 114 */     return 1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\distances\Cosine.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
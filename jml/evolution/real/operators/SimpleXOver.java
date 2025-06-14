/*     */ package jml.evolution.real.operators;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseValue;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.random.UniformNumberGenerator;
/*     */ import jml.util.sort.Sort;
/*     */ import jml.util.sort.SortableObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleXOver
/*     */   extends ArityTwo
/*     */ {
/*     */   public SimpleXOver(Environment _environment) {
/*  55 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleXOver(Environment _environment, Selection _selection) {
/*  65 */     super(_environment, _selection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object c1, Object c2) {
/*  76 */     RealVector one = (RealVector)c1;
/*  77 */     RealVector two = (RealVector)c2;
/*     */     
/*  79 */     int min = Math.min(one.dimension(), two.dimension());
/*  80 */     UniformNumberGenerator g = new UniformNumberGenerator((min - 1));
/*  81 */     int pos = g.newInt() + 1;
/*  82 */     Components x = one.get();
/*  83 */     Components y = two.get();
/*  84 */     Vector sx = x.sparce_values();
/*  85 */     Vector sy = y.sparce_values();
/*  86 */     if (sx == null || sy == null) {
/*     */       
/*  88 */       for (int i = 0; i < pos; i++) {
/*  89 */         double t = x.get(i);
/*  90 */         x.set(i, y.get(i));
/*  91 */         y.set(i, t);
/*     */       } 
/*     */     } else {
/*  94 */       int px = Sort.findLow2High(sx, (SortableObject)new SparseValue(pos, 0.0D));
/*  95 */       int py = Sort.findLow2High(sy, (SortableObject)new SparseValue(pos, 0.0D));
/*  96 */       Vector vx = new Vector();
/*  97 */       Vector vy = new Vector();
/*  98 */       int n = sx.size(); int i;
/*  99 */       for (i = n - 1; i >= px; i--) {
/* 100 */         vx.add(sx.get(i));
/* 101 */         sx.remove(i);
/*     */       } 
/* 103 */       n = sy.size();
/* 104 */       for (i = n - 1; i >= py; i--) {
/* 105 */         vy.add(sy.get(i));
/* 106 */         sy.remove(i);
/*     */       } 
/* 108 */       n = vy.size();
/* 109 */       for (i = n - 1; i >= 0; i--) {
/* 110 */         sx.add(vy.get(i));
/* 111 */         vy.remove(i);
/*     */       } 
/* 113 */       n = vx.size();
/* 114 */       for (i = n - 1; i >= 0; i--) {
/* 115 */         sy.add(vx.get(i));
/* 116 */         vx.remove(i);
/*     */       } 
/*     */     } 
/* 119 */     return new Integer(pos);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\SimpleXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
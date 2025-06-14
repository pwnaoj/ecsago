/*     */ package jml.evolution.real.operators;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseComponents;
/*     */ import jml.algebra.SparseValue;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.random.NumberGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinearXOver
/*     */   extends ArityTwo
/*     */ {
/*     */   public LinearXOver(Environment _environment) {
/*  56 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearXOver(Environment _environment, Selection _selection) {
/*  66 */     super(_environment, _selection);
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
/*  77 */     RealVector one = (RealVector)c1;
/*  78 */     RealVector two = (RealVector)c2;
/*     */     
/*  80 */     double alpha = NumberGenerator.random();
/*     */     
/*  82 */     double alpha_1 = NumberGenerator.random();
/*  83 */     double neg_alpha = 1.0D - alpha;
/*  84 */     double neg_alpha_1 = 1.0D - alpha_1;
/*     */ 
/*     */     
/*  87 */     Components x = one.get();
/*  88 */     Components y = two.get();
/*  89 */     Vector sx = x.sparce_values();
/*  90 */     Vector sy = y.sparce_values();
/*  91 */     if (sx == null || sy == null) {
/*  92 */       int min = Math.min(one.dimension(), two.dimension());
/*  93 */       for (int i = 0; i < min; i++) {
/*  94 */         double tx = one.get(i);
/*  95 */         double ty = two.get(i);
/*  96 */         one.set(i, alpha * tx + neg_alpha * ty);
/*  97 */         two.set(i, alpha_1 * tx + neg_alpha_1 * ty);
/*     */       } 
/*     */     } else {
/* 100 */       Vector vx = new Vector();
/* 101 */       Vector vy = new Vector();
/* 102 */       SparseValue ax = null;
/* 103 */       SparseValue by = null;
/* 104 */       Enumeration iter1 = sx.elements();
/* 105 */       Enumeration iter2 = sy.elements();
/* 106 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 107 */         if (ax == null) ax = iter1.nextElement(); 
/* 108 */         if (by == null) by = iter2.nextElement(); 
/* 109 */         if (ax.index == by.index) {
/* 110 */           double tx = ax.value;
/* 111 */           double d1 = by.value;
/* 112 */           ax.value = alpha * tx + neg_alpha * d1;
/* 113 */           by.value = alpha_1 * tx + neg_alpha_1 * d1;
/* 114 */           vx.add(ax);
/* 115 */           vy.add(by);
/* 116 */           ax = null;
/* 117 */           by = null; continue;
/*     */         } 
/* 119 */         if (ax.index < by.index) {
/* 120 */           double tx = ax.value;
/* 121 */           ax.value = alpha * tx;
/* 122 */           vx.add(ax);
/* 123 */           vy.add(new SparseValue(ax.index, alpha_1 * tx));
/* 124 */           ax = null; continue;
/*     */         } 
/* 126 */         double ty = by.value;
/* 127 */         by.value = neg_alpha_1 * ty;
/* 128 */         vx.add(new SparseValue(by.index, neg_alpha * ty));
/* 129 */         vy.add(by);
/* 130 */         by = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 135 */       if (ax != null) {
/* 136 */         double tx = ax.value;
/* 137 */         ax.value = alpha * tx;
/* 138 */         vx.add(ax);
/* 139 */         vy.add(new SparseValue(ax.index, alpha_1 * tx));
/*     */       } 
/*     */       
/* 142 */       if (by != null) {
/* 143 */         double ty = by.value;
/* 144 */         by.value = neg_alpha_1 * ty;
/* 145 */         vx.add(new SparseValue(by.index, neg_alpha * ty));
/* 146 */         vy.add(by);
/*     */       } 
/*     */       
/* 149 */       while (iter1.hasMoreElements()) {
/* 150 */         ax = iter1.nextElement();
/* 151 */         double tx = ax.value;
/* 152 */         ax.value = alpha * tx;
/* 153 */         by = new SparseValue(ax.index, alpha_1 * tx);
/* 154 */         vx.add(ax);
/* 155 */         vy.add(by);
/*     */       } 
/*     */       
/* 158 */       while (iter2.hasMoreElements()) {
/* 159 */         by = iter2.nextElement();
/* 160 */         double ty = by.value;
/* 161 */         ax = new SparseValue(by.index, neg_alpha * ty);
/* 162 */         by.value = neg_alpha_1 * ty;
/* 163 */         vx.add(ax);
/* 164 */         vy.add(by);
/*     */       } 
/* 166 */       one.set((Components)new SparseComponents(one.dimension(), vx));
/* 167 */       two.set((Components)new SparseComponents(two.dimension(), vy));
/*     */     } 
/*     */     
/* 170 */     return new Double(alpha);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\LinearXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
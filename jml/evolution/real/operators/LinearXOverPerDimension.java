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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinearXOverPerDimension
/*     */   extends ArityTwo
/*     */ {
/*     */   public LinearXOverPerDimension(Environment _environment) {
/*  59 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearXOverPerDimension(Environment _environment, Selection _selection) {
/*  69 */     super(_environment, _selection);
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
/*  80 */     RealVector one = (RealVector)c1;
/*  81 */     RealVector two = (RealVector)c2;
/*     */ 
/*     */ 
/*     */     
/*  85 */     Components x = one.get();
/*  86 */     Components y = two.get();
/*  87 */     Vector sx = x.sparce_values();
/*  88 */     Vector sy = y.sparce_values();
/*  89 */     if (sx == null || sy == null) {
/*  90 */       int min = Math.min(one.dimension(), two.dimension());
/*  91 */       for (int i = 0; i < min; i++) {
/*  92 */         double a = NumberGenerator.random();
/*  93 */         double a_1 = 1.0D - a;
/*  94 */         double tx = one.get(i);
/*  95 */         double ty = two.get(i);
/*  96 */         one.set(i, a * tx + a_1 * ty);
/*  97 */         two.set(i, a_1 * tx + a * ty);
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
/* 109 */         double a = NumberGenerator.random();
/* 110 */         double a_1 = 1.0D - a;
/* 111 */         if (ax.index == by.index) {
/* 112 */           double tx = ax.value;
/* 113 */           double d1 = by.value;
/* 114 */           ax.value = a * tx + a_1 * d1;
/* 115 */           by.value = a_1 * tx + a * d1;
/* 116 */           vx.add(ax);
/* 117 */           vy.add(by);
/* 118 */           ax = null;
/* 119 */           by = null; continue;
/*     */         } 
/* 121 */         if (ax.index < by.index) {
/* 122 */           double tx = ax.value;
/* 123 */           ax.value = a * tx;
/* 124 */           vx.add(ax);
/* 125 */           vy.add(new SparseValue(ax.index, a_1 * tx));
/* 126 */           ax = null; continue;
/*     */         } 
/* 128 */         double ty = by.value;
/* 129 */         by.value = a * ty;
/* 130 */         vx.add(new SparseValue(by.index, a_1 * ty));
/* 131 */         vy.add(by);
/* 132 */         by = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (ax != null) {
/* 138 */         double a = NumberGenerator.random();
/* 139 */         double a_1 = 1.0D - a;
/* 140 */         double tx = ax.value;
/* 141 */         ax.value = a * tx;
/* 142 */         vx.add(ax);
/* 143 */         vy.add(new SparseValue(ax.index, a_1 * tx));
/*     */       } 
/*     */       
/* 146 */       if (by != null) {
/* 147 */         double a = NumberGenerator.random();
/* 148 */         double a_1 = 1.0D - a;
/* 149 */         double ty = by.value;
/* 150 */         by.value = a * ty;
/* 151 */         vx.add(new SparseValue(by.index, a_1 * ty));
/* 152 */         vy.add(by);
/*     */       } 
/*     */       
/* 155 */       while (iter1.hasMoreElements()) {
/* 156 */         double a = NumberGenerator.random();
/* 157 */         double a_1 = 1.0D - a;
/* 158 */         ax = iter1.nextElement();
/* 159 */         double tx = ax.value;
/* 160 */         ax.value = a * tx;
/* 161 */         by = new SparseValue(ax.index, a_1 * tx);
/* 162 */         vx.add(ax);
/* 163 */         vy.add(by);
/*     */       } 
/*     */       
/* 166 */       while (iter2.hasMoreElements()) {
/* 167 */         double a = NumberGenerator.random();
/* 168 */         double a_1 = 1.0D - a;
/* 169 */         by = iter2.nextElement();
/* 170 */         double tx = 0.0D;
/* 171 */         double ty = by.value;
/* 172 */         ax = new SparseValue(by.index, a_1 * ty);
/* 173 */         by.value = a * ty;
/* 174 */         vx.add(ax);
/* 175 */         vy.add(by);
/*     */       } 
/*     */       
/* 178 */       one.set((Components)new SparseComponents(one.dimension(), vx));
/* 179 */       two.set((Components)new SparseComponents(two.dimension(), vy));
/*     */     } 
/* 181 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\LinearXOverPerDimension.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ public class GeneXOver
/*     */   extends ArityTwo
/*     */ {
/*     */   public GeneXOver(Environment _environment) {
/*  59 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneXOver(Environment _environment, Selection _selection) {
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
/*     */     
/*  86 */     Components x = one.get();
/*  87 */     Components y = two.get();
/*  88 */     Vector sx = x.sparce_values();
/*  89 */     Vector sy = y.sparce_values();
/*  90 */     if (sx == null || sy == null) {
/*  91 */       int min = Math.min(one.dimension(), two.dimension());
/*  92 */       for (int i = 0; i < min; i++) {
/*  93 */         double a = NumberGenerator.random();
/*  94 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/*  95 */          double a_1 = 1.0D - a;
/*  96 */         double tx = one.get(i);
/*  97 */         double ty = two.get(i);
/*  98 */         one.set(i, a * tx + a_1 * ty);
/*  99 */         two.set(i, a_1 * tx + a * ty);
/*     */       } 
/*     */     } else {
/* 102 */       Vector vx = new Vector();
/* 103 */       Vector vy = new Vector();
/* 104 */       SparseValue ax = null;
/* 105 */       SparseValue by = null;
/* 106 */       Enumeration iter1 = sx.elements();
/* 107 */       Enumeration iter2 = sy.elements();
/* 108 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 109 */         if (ax == null) ax = iter1.nextElement(); 
/* 110 */         if (by == null) by = iter2.nextElement(); 
/* 111 */         double a = NumberGenerator.random();
/* 112 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/* 113 */          double a_1 = 1.0D - a;
/* 114 */         if (ax.index == by.index) {
/* 115 */           double tx = ax.value;
/* 116 */           double d1 = by.value;
/* 117 */           ax.value = a * tx + a_1 * d1;
/* 118 */           by.value = a_1 * tx + a * d1;
/* 119 */           vx.add(ax);
/* 120 */           vy.add(by);
/* 121 */           ax = null;
/* 122 */           by = null; continue;
/*     */         } 
/* 124 */         if (ax.index < by.index) {
/* 125 */           double tx = ax.value;
/* 126 */           ax.value = a * tx;
/* 127 */           vx.add(ax);
/* 128 */           vy.add(new SparseValue(ax.index, a_1 * tx));
/* 129 */           ax = null; continue;
/*     */         } 
/* 131 */         double ty = by.value;
/* 132 */         by.value = a * ty;
/* 133 */         vx.add(new SparseValue(by.index, a_1 * ty));
/* 134 */         vy.add(by);
/* 135 */         by = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 140 */       if (ax != null) {
/* 141 */         double a = NumberGenerator.random();
/* 142 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/* 143 */          double a_1 = 1.0D - a;
/* 144 */         double tx = ax.value;
/* 145 */         ax.value = a * tx;
/* 146 */         vx.add(ax);
/* 147 */         vy.add(new SparseValue(ax.index, a_1 * tx));
/*     */       } 
/*     */       
/* 150 */       if (by != null) {
/* 151 */         double a = NumberGenerator.random();
/* 152 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/* 153 */          double a_1 = 1.0D - a;
/* 154 */         double ty = by.value;
/* 155 */         by.value = a * ty;
/* 156 */         vx.add(new SparseValue(by.index, a_1 * ty));
/* 157 */         vy.add(by);
/*     */       } 
/*     */       
/* 160 */       while (iter1.hasMoreElements()) {
/* 161 */         double a = NumberGenerator.random();
/* 162 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/* 163 */          double a_1 = 1.0D - a;
/* 164 */         ax = iter1.nextElement();
/* 165 */         double tx = ax.value;
/* 166 */         ax.value = a * tx;
/* 167 */         by = new SparseValue(ax.index, a_1 * tx);
/* 168 */         vx.add(ax);
/* 169 */         vy.add(by);
/*     */       } 
/*     */       
/* 172 */       while (iter2.hasMoreElements()) {
/* 173 */         double a = NumberGenerator.random();
/* 174 */         if (a < 0.5D) { a = 0.0D; } else { a = 1.0D; }
/* 175 */          double a_1 = 1.0D - a;
/* 176 */         by = iter2.nextElement();
/* 177 */         double tx = 0.0D;
/* 178 */         double ty = by.value;
/* 179 */         ax = new SparseValue(by.index, a_1 * ty);
/* 180 */         by.value = a * ty;
/* 181 */         vx.add(ax);
/* 182 */         vy.add(by);
/*     */       } 
/*     */       
/* 185 */       one.set((Components)new SparseComponents(one.dimension(), vx));
/* 186 */       two.set((Components)new SparseComponents(two.dimension(), vy));
/*     */     } 
/* 188 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\GeneXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.random;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NumberGenerator
/*     */ {
/*  42 */   protected static Random g = new Random();
/*     */ 
/*     */ 
/*     */   
/*     */   public static double random() {
/*  47 */     return g.nextDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int newInt();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double newDouble();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean newBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getVectorInteger(int m) {
/*  73 */     Vector v = null;
/*  74 */     if (m > 0) {
/*  75 */       v = new Vector();
/*  76 */       for (int i = 0; i < m; i++) {
/*  77 */         v.add(new Integer(newInt()));
/*     */       }
/*     */     } 
/*  80 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getVectorDouble(int m) {
/*  89 */     Vector v = null;
/*  90 */     if (m > 0) {
/*  91 */       v = new Vector();
/*  92 */       for (int i = 0; i < m; i++) {
/*  93 */         v.add(new Double(newDouble()));
/*     */       }
/*     */     } 
/*  96 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getVectorBoolean(int m) {
/* 105 */     Vector v = null;
/* 106 */     if (m > 0) {
/* 107 */       v = new Vector();
/* 108 */       for (int i = 0; i < m; i++) {
/* 109 */         v.add(new Boolean(newBoolean()));
/*     */       }
/*     */     } 
/* 112 */     return v;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\random\NumberGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
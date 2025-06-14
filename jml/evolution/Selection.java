/*     */ package jml.evolution;
/*     */ 
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
/*     */ public abstract class Selection
/*     */   extends Operator
/*     */ {
/*  39 */   protected int n = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean includeX = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selection(Environment _environment, int _n, boolean _includeX) {
/*  52 */     super(_environment);
/*  53 */     this.n = _n;
/*  54 */     this.includeX = _includeX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selection(Environment _environment, int _n) {
/*  62 */     super(_environment);
/*  63 */     this.n = _n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Vector choose(Population paramPopulation, int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector choose(Population population) {
/*  77 */     return choose(population, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector apply(Population population, int x) {
/*  87 */     Vector sel = null;
/*  88 */     if (population != null) {
/*  89 */       if (this.includeX) {
/*  90 */         sel = choose(population, x);
/*     */       } else {
/*  92 */         sel = choose(population);
/*     */       } 
/*     */     }
/*  95 */     return sel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int _n) {
/* 102 */     this.n = _n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArity() {
/* 108 */     return this.n;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Selection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.evolution.selections;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.random.WeightedNumberGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Elitism
/*     */   extends Selection
/*     */ {
/*  47 */   protected double elite_percentage = 0.1D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected double cull_percentage = 0.1D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Elitism(Environment _environment, int _n, boolean _includeX, double _elite_percentage, double _cull_percentage) {
/*  64 */     super(_environment, _n, _includeX);
/*  65 */     this.elite_percentage = _elite_percentage;
/*  66 */     this.cull_percentage = _cull_percentage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector choose(Population population, int x) {
/*  75 */     Vector sel = null;
/*  76 */     if (population != null) {
/*  77 */       if (this.n == 1) {
/*  78 */         sel = new Vector();
/*  79 */         if (this.includeX) {
/*  80 */           sel.add(population.get(x));
/*     */         } else {
/*  82 */           population.sort();
/*     */           
/*  84 */           sel.add(population.get(0));
/*     */         } 
/*     */       } else {
/*  87 */         int k = (int)(population.size() * (1.0D - this.cull_percentage));
/*  88 */         double[] weight = new double[k];
/*  89 */         double total = (k * (k + 1)) / 2.0D;
/*  90 */         for (int i = 0; i < k; ) { weight[i] = (k - i) / total; i++; }
/*  91 */          WeightedNumberGenerator generator = new WeightedNumberGenerator(weight);
/*  92 */         int[] sort_index = new int[population.size()]; int j;
/*  93 */         for (j = 0; j < sort_index.length; ) { sort_index[j] = j; j++; }
/*  94 */          for (j = 0; j < sort_index.length - 1; j++) {
/*  95 */           for (int i1 = j + 1; i1 < sort_index.length; i1++) {
/*  96 */             if (population.get(sort_index[j]).getFitness() < population.get(sort_index[i1]).getFitness()) {
/*     */               
/*  98 */               int temp = sort_index[j];
/*  99 */               sort_index[j] = sort_index[i1];
/* 100 */               sort_index[i1] = temp;
/*     */             } 
/*     */           } 
/*     */         } 
/* 104 */         int m = (int)(population.size() * this.elite_percentage);
/* 105 */         boolean flag = false;
/* 106 */         sel = new Vector(); int n;
/* 107 */         for (n = 0; n < this.n && n < m; n++) {
/* 108 */           if (sort_index[n] == x) flag = true; 
/* 109 */           sel.add(population.get(sort_index[n]));
/*     */         } 
/* 111 */         for (n = m; n < this.n; n++) {
/* 112 */           int index = sort_index[generator.newInt()];
/* 113 */           if (index == x) flag = true; 
/* 114 */           sel.add(population.get(index));
/*     */         } 
/* 116 */         if (!flag && x >= 0 && x < population.size()) {
/* 117 */           sel.remove(sel.size() - 1);
/* 118 */           sel.add(population.get(x));
/*     */         } 
/*     */       } 
/*     */     }
/* 122 */     return sel;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\selections\Elitism.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
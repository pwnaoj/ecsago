/*     */ package jml.evolution.selections;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.random.Partition;
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
/*     */ public class Tournament
/*     */   extends Selection
/*     */ {
/*  46 */   protected int m = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tournament(Environment _environment, int _n, boolean _includeX, int _m) {
/*  57 */     super(_environment, _n, _includeX);
/*  58 */     this.m = _m;
/*     */   }
/*     */   
/*     */   protected void iteration(Population p) {
/*  62 */     Vector v = new Vector();
/*  63 */     int n = p.size();
/*  64 */     Partition.permutation(p.individuals);
/*  65 */     if (n % 2 == 1) {
/*  66 */       n--;
/*  67 */       v.add(p.get(n));
/*     */     } 
/*  69 */     for (int i = 0; i < n; i += 2) {
/*  70 */       if (p.get(i).getFitness() > p.get(i + 1).getFitness()) {
/*  71 */         v.add(p.get(i));
/*     */       } else {
/*  73 */         v.add(p.get(i + 1));
/*     */       } 
/*     */     } 
/*  76 */     p.setIndividuals(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Individual choose_one(UniformNumberGenerator generator, Population population) {
/*  86 */     Vector v = new Vector();
/*  87 */     for (int i = 0; i < this.m; i++) {
/*  88 */       v.add(population.get(generator.newInt()));
/*     */     }
/*  90 */     Population p = new Population(population.getEnvironment(), v);
/*  91 */     while (p.size() > 1) {
/*  92 */       iteration(p);
/*     */     }
/*  94 */     return p.get(0);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector choose(Population population, int x) {
/* 109 */     Vector sel = null;
/* 110 */     if (population != null) {
/* 111 */       sel = new Vector();
/* 112 */       UniformNumberGenerator g = new UniformNumberGenerator(population.size());
/* 113 */       int arity = this.n;
/* 114 */       if (x >= 0 && x < population.size()) {
/* 115 */         sel.add(population.get(x));
/* 116 */         arity--;
/*     */       } 
/* 118 */       for (int i = 0; i < arity; i++)
/*     */       {
/*     */         
/* 121 */         sel.add(choose_one(g, population));
/*     */       }
/*     */     } 
/* 124 */     return sel;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\selections\Tournament.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
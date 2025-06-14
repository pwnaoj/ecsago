/*     */ package jml.evolution;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.util.sort.MergeSort;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Population
/*     */ {
/*  42 */   protected Environment environment = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public Vector individuals = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population(Environment _environment, Vector _individuals) {
/*  56 */     this.individuals = _individuals;
/*  57 */     this.environment = _environment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population(Environment _environment, int size) {
/*  68 */     this.environment = _environment;
/*  69 */     Genotype genotype = this.environment.getGenotype();
/*  70 */     for (int i = 0; i < size; i++) {
/*  71 */       Individual ind = new Individual(genotype.newInstance());
/*  72 */       ind.evalFitness(this.environment);
/*  73 */       this.individuals.add(ind);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  81 */     if (this.individuals != null && this.individuals.size() > 0) {
/*  82 */       int size = this.individuals.size();
/*  83 */       Population p = new Population(this.environment, size);
/*  84 */       this.individuals = p.individuals;
/*     */     } 
/*     */   }
/*     */   public Vector get() {
/*  88 */     return this.individuals;
/*     */   } public void clear() {
/*  90 */     if (this.individuals != null) this.individuals.clear(); 
/*     */   } public Environment getEnvironment() {
/*  92 */     return this.environment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PopulationStatistics statistics() {
/*  99 */     PopulationStatistics stat = null;
/* 100 */     if (this.individuals.size() > 0) {
/* 101 */       stat = new PopulationStatistics(this);
/*     */     }
/* 103 */     return stat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 111 */     return this.individuals.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual get(int index) {
/* 120 */     Individual c = null; 
/* 121 */     try { c = this.individuals.elementAt(index); }
/* 122 */     catch (Exception e) { System.err.println("[Population]" + e.getMessage()); }
/* 123 */      return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sort() {
/* 130 */     MergeSort sort = new MergeSort();
/* 131 */     sort.apply(this.individuals, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void evalFitness() {
/* 138 */     Enumeration iter = this.individuals.elements();
/* 139 */     while (iter.hasMoreElements()) {
/* 140 */       Individual ind = iter.nextElement();
/* 141 */       ind.evalFitness(this.environment);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndividuals(Vector _individuals) {
/* 150 */     this.individuals.clear();
/* 151 */     this.individuals = _individuals;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Population.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
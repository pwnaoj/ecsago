/*     */ package jml.evolution;
/*     */ 
/*     */ import jml.util.IterativeAlgorithm;
/*     */ import jml.util.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EvolutionaryAlgorithm
/*     */   extends IterativeAlgorithm
/*     */ {
/*     */   protected boolean initPopulation = true;
/*  45 */   protected Population population = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected Transformation transformation = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EvolutionaryAlgorithm(Population _population, Transformation _transformation, Predicate _condition) {
/*  64 */     super(_condition);
/*  65 */     this.population = _population;
/*  66 */     this.transformation = _transformation;
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
/*     */   public EvolutionaryAlgorithm(Population _population, Transformation _transformation) {
/*  78 */     this.population = _population;
/*  79 */     this.transformation = _transformation;
/*  80 */     this.population.evalFitness();
/*     */   }
/*     */   
/*     */   public void initializePopulation(boolean _init_population) {
/*  84 */     this.initPopulation = _init_population;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Object obj) {
/*  91 */     init();
/*  92 */     if (this.population != null && this.initPopulation) this.population.init(); 
/*  93 */     if (this.transformation != null) this.transformation.init();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void iteration() {
/* 101 */     Population new_population = this.transformation.apply(this.population);
/* 102 */     this.population.setIndividuals(new_population.individuals);
/*     */   }
/*     */   
/*     */   public Object output() {
/* 106 */     return this.transformation.statistics(this.population);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 114 */     if (this.population != null && this.transformation != null) {
/* 115 */       super.run();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual get(int i) {
/* 124 */     Individual c = null;
/* 125 */     if (this.population != null) c = this.population.get(i); 
/* 126 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population getPopulation() {
/* 133 */     return this.population;
/*     */   } public void setPopulation(Population _population) {
/* 135 */     this.population = _population;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\EvolutionaryAlgorithm.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
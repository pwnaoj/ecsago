/*    */ package jml.evolution;
/*    */ 
/*    */ public class Environment {
/*    */   protected Genotype genotype;
/*    */   protected Phenotype phenotype;
/*    */   protected Fitness fitness;
/*    */   
/*    */   public Environment(Genotype _genotype, Phenotype _phenotype, Fitness _fitness) {
/*  9 */     this.genotype = _genotype;
/* 10 */     this.phenotype = _phenotype;
/* 11 */     this.fitness = _fitness;
/*    */   }
/*    */   
/*    */   public Environment(Genotype _genotype, Fitness _fitness) {
/* 15 */     this.genotype = _genotype;
/* 16 */     this.phenotype = new Phenotype();
/* 17 */     this.fitness = _fitness;
/*    */   }
/*    */   
/* 20 */   public Genotype getGenotype() { return this.genotype; }
/* 21 */   public Phenotype getPhenotype() { return this.phenotype; } public Fitness getFitness() {
/* 22 */     return this.fitness;
/*    */   }
/*    */   public double evaluate(Individual ind) {
/* 25 */     return this.fitness.evaluate(ind);
/*    */   }
/*    */   public void setGenotype(Genotype _genotype) {
/* 28 */     this.genotype = _genotype;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Environment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
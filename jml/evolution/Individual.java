/*     */ package jml.evolution;
/*     */ 
/*     */ import jml.basics.Cloner;
/*     */ import jml.util.sort.SortableObject;
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
/*     */ public class Individual
/*     */   extends SortableObject
/*     */   implements Cloneable
/*     */ {
/*     */   protected Object genome;
/*  46 */   protected Object thing = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected double fitness = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual(Object _genome) {
/*  57 */     this.genome = _genome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual(Object _genome, Object _thing, double _fitness) {
/*  64 */     this.genome = _genome;
/*  65 */     this.thing = _thing;
/*  66 */     this.fitness = _fitness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual(Individual source) {
/*  74 */     if (source != null) {
/*  75 */       this.fitness = source.fitness;
/*  76 */       this.genome = Cloner.clone(source.genome);
/*  77 */       this.thing = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  86 */     return new Individual(this);
/*     */   }
/*     */   
/*     */   public Object getThing(Environment env) {
/*  90 */     if (this.thing == null) {
/*  91 */       this.thing = env.getPhenotype().get(this.genome);
/*     */     }
/*  93 */     return this.thing;
/*     */   }
/*     */   public Object getThing() {
/*  96 */     return this.thing;
/*     */   } public Object getGenome() {
/*  98 */     return this.genome;
/*     */   }
/*     */   public String toString() {
/* 101 */     return (this.thing != null) ? this.thing.toString() : this.genome.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFitness() {
/* 109 */     return this.fitness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFitness(double _fitness) {
/* 116 */     this.fitness = _fitness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evalFitness(Environment env) {
/* 123 */     Fitness f = env.getFitness();
/* 124 */     getThing(env);
/* 125 */     this.fitness = f.evaluate(this);
/* 126 */     return this.fitness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFeasible() {
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lessThan(SortableObject x) {
/* 142 */     if (x instanceof Individual) {
/* 143 */       return (getFitness() < ((Individual)x).getFitness());
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalTo(SortableObject x) {
/* 154 */     if (x instanceof Individual) {
/* 155 */       return (getFitness() == ((Individual)x).getFitness());
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\Individual.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
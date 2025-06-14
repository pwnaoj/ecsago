/*     */ package jml.evolution.algorithms;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.evolution.binary.operators.Mutation;
/*     */ import jml.evolution.binary.operators.XOver;
/*     */ import jml.evolution.operators.ArityOne;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.evolution.util.IndividualMetric;
/*     */ import jml.random.NumberGenerator;
/*     */ import jml.random.Partition;
/*     */ import jml.util.quasimetric.QuasiMetric;
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
/*     */ public class DeterministicCrowding
/*     */   extends Transformation
/*     */ {
/*  49 */   protected QuasiMetric metric = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected double xoverProbability = 1.0D;
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected double mutationProbability = 0.1D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArityOne mutation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArityTwo xover;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeterministicCrowding(Environment environment, QuasiMetric _metric, double mProb, double xoverProb) {
/*  80 */     this.metric = IndividualMetric.generate(environment, _metric);
/*  81 */     this.xover = (ArityTwo)new XOver(environment, new Sequence(null, 2, true));
/*  82 */     this.mutation = (ArityOne)new Mutation(environment, mProb);
/*  83 */     this.mutationProbability = 1.0D;
/*  84 */     this.xoverProbability = xoverProb;
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
/*     */   public DeterministicCrowding(QuasiMetric _metric, ArityOne _mutation, ArityTwo _xover, double mProb, double xoverProb) {
/*  99 */     this.xover = _xover;
/* 100 */     this.mutation = _mutation;
/* 101 */     this.mutationProbability = mProb;
/* 102 */     this.xoverProbability = xoverProb;
/* 103 */     this.metric = IndividualMetric.generate(this.xover.getEnvironment(), _metric);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/* 113 */     Environment env = population.getEnvironment();
/* 114 */     int n = population.size();
/* 115 */     Partition.permutation(population.individuals);
/* 116 */     Vector ind = new Vector();
/*     */     
/* 118 */     for (int i = 0; i < n; i += 2) {
/* 119 */       Individual P1 = population.get(i);
/* 120 */       Individual P2 = population.get(i + 1);
/* 121 */       Individual C1 = (Individual)Cloner.clone(P1);
/* 122 */       Individual C2 = (Individual)Cloner.clone(P2);
/*     */       
/* 124 */       if (NumberGenerator.random() < this.xoverProbability) {
/* 125 */         this.xover.apply(C1.getGenome(), C2.getGenome());
/*     */       }
/*     */       
/* 128 */       if (NumberGenerator.random() < this.mutationProbability) {
/* 129 */         this.mutation.apply(C1.getGenome());
/* 130 */         this.mutation.apply(C2.getGenome());
/*     */       } 
/*     */       
/* 133 */       C1.evalFitness(env);
/* 134 */       C2.evalFitness(env);
/*     */       
/* 136 */       if (this.metric.distance(P1, C2) + this.metric.distance(P2, C1) <= this.metric.distance(P1, C1) + this.metric.distance(P2, C2)) {
/*     */         
/* 138 */         Individual temp = C1;
/* 139 */         C1 = C2;
/* 140 */         C2 = temp;
/*     */       } 
/* 142 */       if (C1.getFitness() < P1.getFitness()) C1 = P1; 
/* 143 */       if (C2.getFitness() < P2.getFitness()) C2 = P2; 
/* 144 */       ind.add(C1);
/* 145 */       ind.add(C2);
/*     */     } 
/* 147 */     return new Population(env, ind);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\DeterministicCrowding.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package unc.evolution.transformation;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.algorithms.DeterministicCrowding;
/*     */ import jml.evolution.operators.ArityOne;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.random.NumberGenerator;
/*     */ import jml.random.Partition;
/*     */ import jml.random.UniformNumberGenerator;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.Cluster;
/*     */ import unc.refinement.ClusterTunning;
/*     */ import unc.restriction.Restriction;
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
/*     */ public class ClassicUNC
/*     */   extends DeterministicCrowding
/*     */ {
/*     */   protected ClusterTunning tunning;
/*     */   protected Restriction mating_restriction;
/*     */   
/*     */   public ClassicUNC(QuasiMetric _metric, ArityOne _mutation, ArityTwo _xover, double mProb, double xoverProb, ClusterTunning _tunning, Restriction _mating_restriction) {
/*  40 */     super(_metric, _mutation, _xover, mProb, xoverProb);
/*  41 */     this.tunning = _tunning;
/*  42 */     this.mating_restriction = _mating_restriction;
/*     */   }
/*     */   
/*     */   protected Individual tune(Individual ind, Environment env) {
/*  46 */     if (this.tunning != null) {
/*  47 */       Cluster c = (Cluster)ind.getThing(env);
/*  48 */       this.tunning.tune(c);
/*  49 */       Object obj = env.getPhenotype().set(c);
/*  50 */       ind = new Individual(obj, c, ind.getFitness());
/*     */     } 
/*  52 */     ind.evalFitness(env);
/*  53 */     return ind;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/*  63 */     UniformNumberGenerator gen = new UniformNumberGenerator(population.size());
/*  64 */     Environment env = population.getEnvironment();
/*  65 */     int n = population.size();
/*  66 */     Partition.permutation(population.individuals);
/*  67 */     Vector ind = new Vector();
/*     */     
/*  69 */     for (int i = 0; i < n; i += 2) {
/*  70 */       Individual P1 = population.get(i);
/*  71 */       Individual P2 = population.get(i + 1);
/*     */       
/*  73 */       P1 = (Individual)Cloner.clone(P1);
/*  74 */       P2 = (Individual)Cloner.clone(P2);
/*  75 */       Individual C1 = (Individual)Cloner.clone(P1);
/*  76 */       Individual C2 = (Individual)Cloner.clone(P2);
/*     */ 
/*     */ 
/*     */       
/*  80 */       if (NumberGenerator.random() < this.xoverProbability && this.mating_restriction.satisfy((Cluster)P1.getThing(env), (Cluster)P2.getThing(env)))
/*     */       {
/*  82 */         this.xover.apply(C1, C2);
/*     */       }
/*     */       
/*  85 */       if (NumberGenerator.random() < this.mutationProbability) {
/*  86 */         this.mutation.apply(C1);
/*  87 */         this.mutation.apply(C2);
/*     */       } 
/*     */ 
/*     */       
/*  91 */       if (P1.getFitness() == 0.0D) {
/*  92 */         if (P2.getFitness() > 0.0D) { P1 = (Individual)P2.clone(); }
/*  93 */         else { P1 = (Individual)population.get(gen.newInt()).clone(); }
/*     */       
/*     */       }
/*  96 */       if (P2.getFitness() == 0.0D) {
/*  97 */         if (P1.getFitness() > 0.0D) { P2 = (Individual)P1.clone(); }
/*  98 */         else { P2 = (Individual)population.get(gen.newInt()).clone(); }
/*     */       
/*     */       }
/*     */       
/* 102 */       P1 = tune(P1, env);
/* 103 */       P2 = tune(P2, env);
/* 104 */       C1 = tune(C1, env);
/* 105 */       C2 = tune(C2, env);
/*     */       
/* 107 */       if (this.metric.distance(P1, C2) + this.metric.distance(P2, C1) <= this.metric.distance(P1, C1) + this.metric.distance(P2, C2)) {
/*     */         
/* 109 */         Individual temp = C1;
/* 110 */         C1 = C2;
/* 111 */         C2 = temp;
/*     */       } 
/* 113 */       if (C1.getFitness() < P1.getFitness()) C1 = P1; 
/* 114 */       if (C2.getFitness() < P2.getFitness()) C2 = P2; 
/* 115 */       ind.add(C1);
/* 116 */       ind.add(C2);
/*     */     } 
/* 118 */     return new Population(env, ind);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\transformation\ClassicUNC.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
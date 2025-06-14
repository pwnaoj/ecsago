/*     */ package unc.evolution.transformation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.basics.Result;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.algorithms.haea.HAEA;
/*     */ import jml.evolution.algorithms.haea.HAEAStatistics;
/*     */ import unc.Cluster;
/*     */ import unc.refinement.ClusterTunning;
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
/*     */ public class ECSAGO
/*     */   extends HAEA
/*     */ {
/*     */   public ClusterTunning tunning;
/*     */   
/*     */   public ECSAGO(Operator[] _operators, ClusterTunning _tunning) {
/*  34 */     super(_operators);
/*  35 */     this.tunning = _tunning;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ECSAGO(Operator[] _operators, Selection _selection, ClusterTunning _tunning) {
/*  44 */     super(_operators, _selection);
/*  45 */     this.tunning = _tunning;
/*     */   }
/*     */   
/*     */   protected Individual tune(Individual ind, Environment env) {
/*  49 */     if (this.tunning != null) {
/*  50 */       Cluster c = (Cluster)ind.getThing(env);
/*  51 */       this.tunning.tune(c);
/*  52 */       Object obj = env.getPhenotype().set(c);
/*  53 */       ind = new Individual(obj, c, ind.getFitness());
/*     */     } 
/*  55 */     return ind;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/*  64 */     Population newPopulation = null;
/*  65 */     if (this.operators != null) {
/*  66 */       Environment env = population.getEnvironment();
/*  67 */       updateRatesSize(population);
/*  68 */       Vector newRates = new Vector();
/*  69 */       Vector newInd = new Vector();
/*  70 */       double avgFitness = 0.0D;
/*  71 */       for (int i = 0; i < population.size(); i++) {
/*  72 */         RealVector x = this.rates.get(i);
/*  73 */         int oper = selectOperator(x);
/*  74 */         Operator o = this.operators[oper];
/*  75 */         Vector v = o.apply(population, i);
/*  76 */         Vector v1 = new Vector();
/*  77 */         Enumeration iter = v.elements();
/*  78 */         while (iter.hasMoreElements()) {
/*  79 */           Individual ind = iter.nextElement();
/*  80 */           ind = tune(ind, env);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  88 */           v1.add(ind);
/*     */         } 
/*  90 */         v = v1;
/*     */         
/*  92 */         Individual parent = population.get(i);
/*  93 */         Individual par = (Individual)Cloner.clone(parent);
/*  94 */         par = tune(par, env);
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
/* 109 */         v.add(0, par);
/*     */         
/* 111 */         Population p = new Population(env, v);
/*     */         
/* 113 */         v = this.selection.choose(p);
/* 114 */         double pf = par.getFitness();
/* 115 */         iter = v.elements();
/* 116 */         while (iter.hasMoreElements()) {
/* 117 */           Individual child = iter.nextElement();
/* 118 */           RealVector y = (RealVector)Cloner.clone(x);
/* 119 */           double f = child.getFitness();
/* 120 */           avgFitness += f;
/* 121 */           if (pf < child.getFitness()) {
/* 122 */             increase(y, oper);
/*     */           } else {
/* 124 */             decrease(y, oper);
/*     */           } 
/* 126 */           newRates.add(y);
/* 127 */           newInd.add(child);
/*     */         } 
/*     */       } 
/* 130 */       newPopulation = new Population(env, newInd);
/* 131 */       this.rates = newRates;
/*     */     } 
/* 133 */     return newPopulation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result statistics(Population population) {
/* 143 */     updateRatesSize(population);
/* 144 */     return (Result)new HAEAStatistics(this.rates, population.statistics());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\transformation\ECSAGO.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
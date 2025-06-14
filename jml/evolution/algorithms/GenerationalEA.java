/*     */ package jml.evolution.algorithms;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.evolution.binary.operators.Mutation;
/*     */ import jml.evolution.binary.operators.XOver;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.random.NumberGenerator;
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
/*     */ public class GenerationalEA
/*     */   extends Transformation
/*     */ {
/*  47 */   protected double[] operator_probabilities = null;
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Operator[] operators = null;
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
/*     */   public GenerationalEA(Selection selection, double mProb, double xoverProb) {
/*  63 */     Environment env = selection.getEnvironment();
/*  64 */     this.operators = new Operator[3];
/*  65 */     this.operators[0] = (Operator)selection;
/*  66 */     this.operators[1] = (Operator)new XOver(env, new Sequence(env, 2, true));
/*  67 */     this.operators[2] = (Operator)new Mutation(env, mProb);
/*     */     
/*  69 */     this.operator_probabilities = new double[3];
/*  70 */     this.operator_probabilities[0] = 1.0D;
/*  71 */     this.operator_probabilities[1] = xoverProb;
/*  72 */     this.operator_probabilities[2] = 1.0D;
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
/*     */   public GenerationalEA(Selection selection, Operator mutation, ArityTwo xover, double mProb, double xoverProb) {
/*  87 */     Environment env = selection.getEnvironment();
/*  88 */     this.operators = new Operator[3];
/*  89 */     this.operators[0] = (Operator)selection;
/*  90 */     this.operators[1] = (Operator)xover;
/*  91 */     xover.setSelection(new Sequence(env, 2, true));
/*  92 */     this.operators[2] = mutation;
/*     */     
/*  94 */     this.operator_probabilities = new double[3];
/*  95 */     this.operator_probabilities[0] = 1.0D;
/*  96 */     this.operator_probabilities[1] = xoverProb;
/*  97 */     this.operator_probabilities[2] = mProb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerationalEA(double[] _operator_probabilities, Operator[] _operators) {
/* 107 */     this.operators = _operators;
/* 108 */     this.operator_probabilities = _operator_probabilities;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/* 118 */     if (this.operators != null) {
/* 119 */       for (int i = 0; i < this.operators.length; i++) {
/* 120 */         int n = this.operators[i].getArity();
/* 121 */         int m = population.size() / n;
/* 122 */         Vector ind = new Vector();
/* 123 */         int k = 0;
/* 124 */         for (int j = 0; j < m; j++) {
/* 125 */           if (NumberGenerator.random() < this.operator_probabilities[i]) {
/* 126 */             Vector subInd = this.operators[i].apply(population, k);
/* 127 */             Enumeration iter = subInd.elements();
/* 128 */             while (iter.hasMoreElements()) {
/* 129 */               ind.add(iter.nextElement());
/*     */             }
/*     */           } else {
/* 132 */             for (int h = 0; h < n; h++) {
/* 133 */               ind.add(population.get(k + h));
/*     */             }
/*     */           } 
/* 136 */           k += n;
/*     */         } 
/* 138 */         population = new Population(population.getEnvironment(), ind);
/*     */       } 
/*     */     }
/* 141 */     return population;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\GenerationalEA.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
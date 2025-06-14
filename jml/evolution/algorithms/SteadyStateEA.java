/*     */ package jml.evolution.algorithms;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.binary.operators.XOver;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.evolution.selections.Tournament;
/*     */ import jml.random.NumberGenerator;
/*     */ import jml.util.sort.Sort;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SteadyStateEA
/*     */   extends GenerationalEA
/*     */ {
/*     */   boolean need_sort = true;
/*     */   
/*     */   public SteadyStateEA(Selection selection, double mProb, double xoverProb) {
/*  61 */     super(selection, mProb, xoverProb);
/*  62 */     Environment env = selection.getEnvironment();
/*  63 */     this.operators[1] = (Operator)new XOver(env, (Selection)new Tournament(env, 2, true, 2));
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
/*     */   public SteadyStateEA(Selection selection, Operator mutation, ArityTwo xover, double mProb, double xoverProb) {
/*  78 */     super(selection, mutation, xover, mProb, xoverProb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteadyStateEA(double[] _operator_probabilities, Operator[] _operators) {
/*  88 */     super(_operator_probabilities, _operators);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  95 */     this.need_sort = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/* 105 */     Vector v = new Vector();
/* 106 */     for (int i = 0; i < population.size(); i++) {
/* 107 */       v.add(Cloner.clone(population.get(i)));
/*     */     }
/* 109 */     Environment env = population.getEnvironment();
/* 110 */     population = new Population(env, v);
/* 111 */     if (this.need_sort) {
/* 112 */       population.sort();
/* 113 */       this.need_sort = false;
/*     */     } 
/* 115 */     for (int j = 0; j < population.size(); j++) {
/* 116 */       Vector children = population.individuals;
/* 117 */       for (int k = 0; k < this.operators.length; k++) {
/* 118 */         if (NumberGenerator.random() < this.operator_probabilities[k]) {
/* 119 */           children = this.operators[k].apply(new Population(env, children), 0);
/*     */         }
/*     */       } 
/* 122 */       population.individuals.remove(population.individuals.size() - 1);
/* 123 */       int index = Sort.findHigh2Low(population.individuals, children.get(0));
/*     */       
/* 125 */       population.individuals.add(index, children.get(0));
/*     */     } 
/* 127 */     return population;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\SteadyStateEA.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
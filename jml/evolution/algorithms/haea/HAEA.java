/*     */ package jml.evolution.algorithms.haea;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.FullComponents;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.basics.Result;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.evolution.selections.Elitism;
/*     */ import jml.random.NumberGenerator;
/*     */ import jml.random.WeightedNumberGenerator;
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
/*     */ public class HAEA
/*     */   extends Transformation
/*     */ {
/*  51 */   protected Operator[] operators = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   protected Selection selection = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected Vector rates = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HAEA(Operator[] _operators) {
/*  69 */     this.operators = _operators;
/*  70 */     if (this.operators != null && this.operators.length > 0) {
/*  71 */       this.selection = (Selection)new Elitism(this.operators[0].getEnvironment(), 1, false, 1.0D, 0.0D);
/*     */     } else {
/*  73 */       this.selection = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HAEA(Operator[] _operators, Selection _selection) {
/*  83 */     this.operators = _operators;
/*  84 */     this.selection = _selection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  91 */     int n = this.operators.length;
/*  92 */     for (int i = 0; i < this.rates.size(); i++) {
/*  93 */       double[] tempRates = new double[n];
/*  94 */       for (int j = 0; j < n; ) { tempRates[j] = NumberGenerator.random(); j++; }
/*  95 */        normalize(tempRates);
/*  96 */       this.rates.add(new RealVector(tempRates));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void normalize(double[] r) {
/* 106 */     double total = 0.0D;
/* 107 */     int n = r.length; int i;
/* 108 */     for (i = 0; i < n; ) { total += r[i]; i++; }
/* 109 */      for (i = 0; i < n; ) { r[i] = r[i] / total; i++; }
/*     */   
/*     */   }
/*     */   double[] get(RealVector x) {
/* 113 */     return ((FullComponents)x.get()).get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int selectOperator(RealVector x) {
/* 121 */     WeightedNumberGenerator g = new WeightedNumberGenerator(get(x));
/* 122 */     return g.newInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void decrease(RealVector x, int oper) {
/* 131 */     double[] c = get(x);
/* 132 */     c[oper] = c[oper] * (1.0D - NumberGenerator.random());
/* 133 */     normalize(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void increase(RealVector x, int oper) {
/* 142 */     double[] c = get(x);
/* 143 */     c[oper] = c[oper] * (1.0D + NumberGenerator.random());
/* 144 */     normalize(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void increase(RealVector x, int oper, double delta) {
/* 154 */     double[] c = get(x);
/* 155 */     c[oper] = c[oper] * (1.0D + delta);
/* 156 */     normalize(c);
/*     */   }
/*     */   
/*     */   public void updateRatesSize(Population population) {
/* 160 */     int psize = population.size();
/* 161 */     int rsize = this.rates.size();
/* 162 */     if (psize != rsize) {
/* 163 */       int n = this.operators.length;
/* 164 */       if (psize > rsize) {
/* 165 */         for (int i = rsize; i < psize; i++) {
/* 166 */           double[] tempRates = new double[n];
/* 167 */           for (int j = 0; j < n; ) { tempRates[j] = NumberGenerator.random(); j++; }
/* 168 */            normalize(tempRates);
/* 169 */           this.rates.add(new RealVector(tempRates));
/*     */         } 
/*     */       } else {
/* 172 */         for (int i = rsize - 1; i >= psize; i--) {
/* 173 */           this.rates.remove(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Population apply(Population population) {
/* 185 */     Population newPopulation = null;
/* 186 */     if (this.operators != null) {
/* 187 */       Environment env = population.getEnvironment();
/* 188 */       updateRatesSize(population);
/* 189 */       Vector newRates = new Vector();
/* 190 */       Vector newInd = new Vector();
/* 191 */       double maxFitness = -1.0E8D;
/* 192 */       double minFitness = 1.0E8D;
/* 193 */       double avgFitness = 0.0D;
/* 194 */       for (int i = 0; i < population.size(); i++) {
/* 195 */         RealVector x = this.rates.get(i);
/* 196 */         int oper = selectOperator(x);
/* 197 */         Operator o = this.operators[oper];
/* 198 */         Vector v = o.apply(population, i);
/* 199 */         Enumeration iter = v.elements();
/* 200 */         while (iter.hasMoreElements()) {
/* 201 */           ((Individual)iter.nextElement()).evalFitness(env);
/*     */         }
/*     */         
/* 204 */         Individual parent = population.get(i);
/* 205 */         Individual par = (Individual)Cloner.clone(parent);
/* 206 */         v.add(0, par);
/*     */         
/* 208 */         Population p = new Population(env, v);
/*     */         
/* 210 */         v = this.selection.choose(p);
/* 211 */         double pf = parent.getFitness();
/* 212 */         iter = v.elements();
/* 213 */         while (iter.hasMoreElements()) {
/* 214 */           Individual child = iter.nextElement();
/* 215 */           RealVector y = (RealVector)Cloner.clone(x);
/* 216 */           double f = child.getFitness();
/*     */ 
/*     */ 
/*     */           
/* 220 */           avgFitness += f;
/* 221 */           if (pf < child.getFitness()) {
/* 222 */             increase(y, oper);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 227 */             decrease(y, oper);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 233 */           newRates.add(y);
/* 234 */           newInd.add(child);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 244 */       newPopulation = new Population(env, newInd);
/* 245 */       this.rates = newRates;
/*     */     } 
/* 247 */     return newPopulation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result statistics(Population population) {
/* 257 */     updateRatesSize(population);
/* 258 */     return (Result)new HAEAStatistics(this.rates, population.statistics());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\haea\HAEA.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
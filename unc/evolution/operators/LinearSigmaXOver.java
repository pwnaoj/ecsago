/*     */ package unc.evolution.operators;
/*     */ 
/*     */ import jml.algebra.RealVector;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.util.quasimetric.Minkowski;
/*     */ import unc.Cluster;
/*     */ import unc.sets.SqrGaussianSet;
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
/*     */ public class LinearSigmaXOver
/*     */   extends ArityTwo
/*     */ {
/*  28 */   double sigma = 0.0D;
/*  29 */   double sigma2 = 0.0D;
/*     */   public LinearSigmaXOver(Environment env) {
/*  31 */     super(env);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinearSigmaXOver(Environment env, Selection _selection) {
/*  40 */     super(env, _selection);
/*     */   }
/*     */   
/*     */   public double getSigma(Individual obj) {
/*  44 */     Cluster c = (Cluster)obj.getThing(this.environment);
/*  45 */     return ((SqrGaussianSet)c.getSet()).getSqrSigma();
/*     */   }
/*     */   
/*     */   public Object apply(Individual one, Individual two) {
/*  49 */     this.sigma = getSigma(one);
/*  50 */     this.sigma2 = getSigma(two);
/*     */     
/*  52 */     return super.apply(one, two);
/*     */   }
/*     */   
/*     */   public Object apply(Object objone, Object objtwo) {
/*  56 */     RealVector one = (RealVector)objone;
/*  57 */     RealVector two = (RealVector)objtwo;
/*  58 */     RealVector oneC = new RealVector(one);
/*  59 */     RealVector twoC = new RealVector(two);
/*  60 */     Minkowski minkowski = new Minkowski(2.0D);
/*  61 */     double dist = minkowski.distance(one, two);
/*  62 */     double alpha = 0.5D;
/*  63 */     double neg_alpha = 0.5D;
/*  64 */     int dim = one.dimension();
/*  65 */     if (dist < Math.max(this.sigma, this.sigma2) * 13.8D) {
/*  66 */       alpha = Math.random();
/*  67 */       neg_alpha = 1.0D - alpha;
/*  68 */       for (int i = 0; i < dim; i++) {
/*  69 */         double tx = oneC.get(i);
/*  70 */         double ty = twoC.get(i);
/*  71 */         one.set(i, alpha * tx + neg_alpha * ty);
/*  72 */         two.set(i, alpha * tx + neg_alpha * ty);
/*     */       } 
/*     */     } else {
/*  75 */       int iter = 0;
/*     */       do {
/*  77 */         for (int j = 0; j < dim; j++) {
/*  78 */           double tx = oneC.get(j);
/*  79 */           double ty = twoC.get(j);
/*  80 */           one.set(j, alpha * tx + neg_alpha * ty);
/*  81 */           two.set(j, alpha * tx + neg_alpha * ty);
/*     */         } 
/*  83 */         dist = minkowski.distance(one, oneC);
/*  84 */         iter++;
/*  85 */         neg_alpha /= 2.0D;
/*  86 */         alpha = 1.0D - neg_alpha;
/*     */       }
/*  88 */       while (iter < 10 && dist > this.sigma * 3.4D);
/*  89 */       neg_alpha *= Math.random();
/*  90 */       alpha = 1.0D - neg_alpha;
/*  91 */       for (int i = 0; i < dim; i++) {
/*  92 */         double tx = oneC.get(i);
/*  93 */         double ty = twoC.get(i);
/*  94 */         one.set(i, alpha * tx + neg_alpha * ty);
/*  95 */         two.set(i, alpha * tx + neg_alpha * ty);
/*     */       } 
/*     */     } 
/*  98 */     return new Double(alpha);
/*     */   }
/*     */   
/*     */   public Object apply2(RealVector one, RealVector two) {
/* 102 */     double alpha = 0.5D;
/* 103 */     double neg_alpha = 0.5D;
/* 104 */     int dim = one.dimension();
/* 105 */     RealVector oneC = new RealVector(one);
/* 106 */     RealVector twoC = new RealVector(two);
/* 107 */     Minkowski minkowski = new Minkowski(2.0D);
/* 108 */     double dist = minkowski.distance(one, two);
/* 109 */     int iter = 0;
/*     */     do {
/* 111 */       for (int j = 0; j < dim; j++) {
/* 112 */         double tx = oneC.get(j);
/* 113 */         double ty = twoC.get(j);
/* 114 */         one.set(j, alpha * tx + neg_alpha * ty);
/* 115 */         two.set(j, alpha * tx + neg_alpha * ty);
/*     */       } 
/* 117 */       dist = minkowski.distance(one, oneC);
/* 118 */       iter++;
/* 119 */       neg_alpha /= 2.0D;
/* 120 */       alpha = 1.0D - neg_alpha;
/* 121 */     } while (iter < 10 && dist > this.sigma * 3.4D);
/* 122 */     neg_alpha *= Math.random();
/* 123 */     alpha = 1.0D - neg_alpha;
/* 124 */     for (int i = 0; i < dim; i++) {
/* 125 */       double tx = oneC.get(i);
/* 126 */       double ty = twoC.get(i);
/* 127 */       one.set(i, alpha * tx + neg_alpha * ty);
/* 128 */       two.set(i, alpha * tx + neg_alpha * ty);
/*     */     } 
/* 130 */     return new Double(alpha);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\LinearSigmaXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
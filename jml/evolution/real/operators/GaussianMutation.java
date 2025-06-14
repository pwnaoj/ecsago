/*     */ package jml.evolution.real.operators;
/*     */ 
/*     */ import jml.algebra.RealVector;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.operators.ArityOne;
/*     */ import jml.evolution.real.RealVectorLimits;
/*     */ import jml.random.GaussianNumberGenerator;
/*     */ import jml.random.UniformNumberGenerator;
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
/*     */ public class GaussianMutation
/*     */   extends ArityOne
/*     */ {
/*     */   protected RealVectorLimits limits;
/*  50 */   protected GaussianNumberGenerator g = null;
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected RealVector sigma = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianMutation(Environment _environment, RealVectorLimits _limits, double _sigma) {
/*  63 */     super(_environment);
/*  64 */     this.limits = _limits;
/*  65 */     this.sigma = new RealVector(this.limits.min.dimension(), _sigma);
/*  66 */     this.g = new GaussianNumberGenerator(0.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianMutation(Environment _environment, RealVectorLimits _limits, RealVector _sigma) {
/*  75 */     super(_environment);
/*  76 */     this.limits = _limits;
/*  77 */     this.sigma = _sigma;
/*  78 */     this.g = new GaussianNumberGenerator(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public void setSigma(double _sigma) {
/*  82 */     this.sigma = new RealVector(this.sigma.dimension(), _sigma);
/*     */   }
/*     */   
/*     */   public void setSigma(RealVector _sigma) {
/*  86 */     this.sigma = _sigma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object gen) {
/*  96 */     RealVector genome = (RealVector)gen;
/*  97 */     RealVector min = this.limits.min;
/*  98 */     RealVector max = this.limits.max;
/*  99 */     int pos = -1;
/*     */     
/* 101 */     try { UniformNumberGenerator s = new UniformNumberGenerator(genome.dimension());
/* 102 */       pos = s.newInt();
/* 103 */       double x = genome.get(pos);
/* 104 */       this.g.setSigma(this.sigma.get(pos));
/* 105 */       double y = this.g.newDouble();
/* 106 */       x += y;
/* 107 */       if (x < min.get(pos)) { x = min.get(pos); }
/* 108 */       else if (x > max.get(pos)) { x = max.get(pos); }
/* 109 */        genome.set(pos, x); }
/* 110 */     catch (Exception e) { System.err.println("[Guassian Mutation]" + e.getMessage()); }
/* 111 */      return new Integer(pos);
/*     */   }
/*     */   
/*     */   public static void main(String[] argv) {}
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\operators\GaussianMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
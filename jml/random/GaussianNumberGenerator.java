/*     */ package jml.random;
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
/*     */ public class GaussianNumberGenerator
/*     */   extends NumberGenerator
/*     */ {
/*  39 */   double miu = 0.0D;
/*     */ 
/*     */ 
/*     */   
/*  43 */   double sigma = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean only_right = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianNumberGenerator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianNumberGenerator(double _miu, double _sigma) {
/*  61 */     this.miu = _miu;
/*  62 */     this.sigma = _sigma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianNumberGenerator(double _miu, double _sigma, boolean _only_right) {
/*  72 */     this.only_right = _only_right;
/*  73 */     this.miu = _miu;
/*  74 */     this.sigma = _sigma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int newInt() {
/*  81 */     return (int)newDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double newDouble() {
/*  88 */     double v = this.sigma * g.nextGaussian();
/*  89 */     if (this.only_right) v = Math.abs(v); 
/*  90 */     return this.miu + v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean newBoolean() {
/*  97 */     return g.nextBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSigma(double _sigma) {
/* 103 */     this.sigma = _sigma;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\random\GaussianNumberGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
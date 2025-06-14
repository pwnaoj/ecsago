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
/*     */ public class WeightedNumberGenerator
/*     */   extends NumberGenerator
/*     */ {
/*  39 */   double[] weight = null;
/*     */ 
/*     */ 
/*     */   
/*  43 */   double[] object = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedNumberGenerator(double[] _weight) {
/*  52 */     this.weight = _weight;
/*  53 */     if (this.weight != null) {
/*  54 */       this.object = new double[this.weight.length];
/*  55 */       for (int i = 0; i < this.weight.length; i++) {
/*  56 */         this.object[i] = i;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedNumberGenerator(double[] _weight, double[] _object) {
/*  67 */     this.weight = _weight;
/*  68 */     this.object = _object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedNumberGenerator(double[] _weight, int[] _object) {
/*  77 */     this.weight = _weight;
/*  78 */     this.object = new double[_object.length];
/*  79 */     for (int i = 0; i < this.object.length; i++) {
/*  80 */       this.object[i] = _object[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWeights(double[] _weight) {
/*  90 */     this.weight = _weight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void normalize() {
/*  98 */     if (this.weight != null) {
/*  99 */       double sum = 0.0D; int i;
/* 100 */       for (i = 0; i < this.weight.length; ) { sum += this.weight[i]; i++; }
/* 101 */        for (i = 0; i < this.weight.length; ) { this.weight[i] = this.weight[i] / sum; i++; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int newInt() {
/* 109 */     return (int)newDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double newDouble() {
/* 116 */     double x = WeightedNumberGenerator.g.nextDouble();
/* 117 */     int i = 0;
/* 118 */     while (i < this.weight.length && x >= this.weight[i]) {
/* 119 */       x -= this.weight[i];
/* 120 */       i++;
/*     */     } 
/* 122 */     if (i == this.weight.length) {
/* 123 */       UniformNumberGenerator g = new UniformNumberGenerator(this.weight.length);
/* 124 */       i = g.newInt();
/*     */     } 
/* 126 */     return this.object[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean newBoolean() {
/* 134 */     return (newInt() % 2 == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\random\WeightedNumberGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
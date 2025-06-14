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
/*     */ 
/*     */ public class UniformNumberGenerator
/*     */   extends NumberGenerator
/*     */ {
/*  40 */   protected double min = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected double length = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniformNumberGenerator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniformNumberGenerator(double n) {
/*  59 */     this.min = 0.0D;
/*  60 */     this.length = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UniformNumberGenerator(double min_val, double max_val) {
/*  70 */     this.min = min_val;
/*  71 */     this.length = max_val - min_val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(double min_val, double max_val) {
/*  81 */     this.min = min_val;
/*  82 */     this.length = max_val - min_val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int newInt() {
/*  89 */     return (int)newDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double newDouble() {
/*  95 */     return this.min + this.length * g.nextDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean newBoolean() {
/* 101 */     return g.nextBoolean();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\random\UniformNumberGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.basics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Result
/*     */ {
/*     */   public abstract void sum(Result paramResult);
/*     */   
/*     */   public abstract void substract(Result paramResult);
/*     */   
/*     */   public abstract void divide(double paramDouble);
/*     */   
/*     */   public abstract void square();
/*     */   
/*     */   public abstract void sqrt();
/*     */   
/*     */   public static Result average(Result[] x) {
/* 140 */     Result avg = null;
/* 141 */     if (x != null && x.length > 0) {
/* 142 */       avg = (Result)Cloner.clone(x[0]);
/* 143 */       int n = x.length;
/* 144 */       for (int i = 1; i < n; i++) {
/* 145 */         avg.sum(x[i]);
/*     */       }
/* 147 */       avg.divide(n);
/*     */     } 
/* 149 */     return avg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Result variance(Result[] x) {
/* 160 */     return variance(x, average(x));
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
/*     */   public static Result variance(Result[] x, Result avg) {
/* 172 */     Result variance = null;
/* 173 */     if (x != null && x.length > 0) {
/* 174 */       variance = (Result)Cloner.clone(x[0]);
/* 175 */       variance.substract(avg);
/* 176 */       variance.square();
/* 177 */       int n = x.length;
/* 178 */       for (int i = 1; i < n; i++) {
/* 179 */         Result c = (Result)Cloner.clone(x[i]);
/* 180 */         c.substract(avg);
/* 181 */         c.square();
/* 182 */         variance.sum(c);
/*     */       } 
/* 184 */       variance.divide((n - 1));
/*     */     } 
/* 186 */     return variance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Result std_deviation(Result[] x) {
/* 197 */     return std_deviation(x, average(x));
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
/*     */   public static Result std_deviation(Result[] x, Result avg) {
/* 209 */     Result r = variance(x, avg);
/* 210 */     r.sqrt();
/* 211 */     return r;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\basics\Result.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
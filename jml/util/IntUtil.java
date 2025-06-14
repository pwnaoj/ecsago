/*     */ package jml.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntUtil
/*     */ {
/*     */   public static int MIN_INT;
/*     */   public static int MAX_INT;
/*  51 */   public static int LOWEST_BIT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public static int HIGHEST_BIT = Integer.MIN_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static int INTEGER_SIZE = 32;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static int MOD_MASK = 31;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static int DIV_MASK = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static int ONE_BITS = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  84 */     LOWEST_BIT = 1;
/*  85 */     ONE_BITS = -1;
/*  86 */     INTEGER_SIZE = 0;
/*  87 */     int val = 1;
/*  88 */     while (val != 0) {
/*  89 */       val <<= 1;
/*  90 */       INTEGER_SIZE++;
/*     */     } 
/*     */     
/*  93 */     MOD_MASK = INTEGER_SIZE - 1;
/*     */     
/*  95 */     MIN_INT = 1 << MOD_MASK;
/*  96 */     MAX_INT = MIN_INT - 1;
/*     */     
/*  98 */     HIGHEST_BIT = 1 << MOD_MASK;
/*  99 */     DIV_MASK = -1;
/* 100 */     val = INTEGER_SIZE;
/* 101 */     while (val > 0) {
/* 102 */       val >>>= 1;
/* 103 */       DIV_MASK++;
/*     */     } 
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
/*     */   public static int binaryToGray(int num) {
/* 116 */     return num >>> 1 ^ num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBitsNumber(int n) {
/* 124 */     return (int)(Math.log(n) / Math.log(2.0D) + 0.9999D);
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
/*     */   public static int grayToBinary(int num) {
/* 136 */     int k = INTEGER_SIZE >>> 1;
/* 137 */     int temp = num;
/* 138 */     while (k > 0) {
/* 139 */       temp ^= temp >>> k;
/* 140 */       k >>>= 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     return temp;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\IntUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.structures;
/*     */ 
/*     */ import jml.util.IntUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitArrayConverter
/*     */ {
/*     */   public static boolean useGrayCode = false;
/*     */   
/*     */   public static int[] getIntArray(BitArray A, int int_size, boolean _useGray) {
/*  39 */     int n = A.size() / int_size;
/*  40 */     int[] intVal = new int[n];
/*  41 */     if (int_size == IntUtil.INTEGER_SIZE)
/*  42 */     { for (int i = 0; i < n; i++) {
/*  43 */         if (_useGray) { intVal[i] = IntUtil.grayToBinary(A.data[i]); }
/*  44 */         else { intVal[i] = A.data[i]; }
/*     */       
/*     */       }  }
/*  47 */     else { int start = 0;
/*  48 */       for (int i = 0; i < n; i++) {
/*  49 */         intVal[i] = getNumber(A, start, int_size);
/*  50 */         start += int_size;
/*     */       }  }
/*     */     
/*  53 */     return intVal;
/*     */   }
/*     */   
/*     */   public static int[] getIntArray(BitArray A) {
/*  57 */     return getIntArray(A, IntUtil.INTEGER_SIZE, useGrayCode);
/*     */   }
/*     */   
/*     */   public static void setIntArray(BitArray A, int[] intVal, int int_size, boolean _useGray) {
/*  61 */     int n = intVal.length;
/*  62 */     if (int_size == IntUtil.INTEGER_SIZE) {
/*  63 */       if (A.data.length < n) {
/*  64 */         A.data = new int[n];
/*     */       }
/*  66 */       for (int i = 0; i < n; i++) {
/*  67 */         if (_useGray) {
/*  68 */           A.data[i] = IntUtil.binaryToGray(intVal[i]);
/*     */         } else {
/*     */           
/*  71 */           A.data[i] = intVal[i];
/*     */         } 
/*     */       } 
/*  74 */       A.n = n * IntUtil.INTEGER_SIZE;
/*     */     } else {
/*  76 */       n = n * int_size / IntUtil.INTEGER_SIZE + 1;
/*  77 */       A.data = new int[n];
/*  78 */       int start = 0;
/*  79 */       for (int i = 0; i < n; i++) {
/*  80 */         setNumber(A, start, int_size, intVal[i]);
/*  81 */         start += int_size;
/*     */       } 
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
/*     */   public static int getNumber(BitArray A, int start, int length) {
/*  95 */     BitArray B = A.subBitArray(start, start + length);
/*  96 */     return B.getInt(0) >>> IntUtil.INTEGER_SIZE - length;
/*     */   }
/*     */   
/*     */   public static void setNumber(BitArray A, int start, int length, int number) {
/* 100 */     int n = start + length - 1;
/* 101 */     for (int i = 0; i < length; i++) {
/* 102 */       A.set(n, ((number & 0x1) == 1));
/* 103 */       number >>>= 1;
/* 104 */       n--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] argv) {
/* 109 */     BitArray A = new BitArray(32, true);
/* 110 */     System.out.println(A);
/* 111 */     BitArray B = A.subBitArray(5, 8);
/* 112 */     System.out.println(B);
/* 113 */     System.out.println(getNumber(A, 5, 3));
/* 114 */     System.out.println(B.getInt(0));
/* 115 */     setNumber(A, 5, 3, 6);
/* 116 */     System.out.println(A);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\structures\BitArrayConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
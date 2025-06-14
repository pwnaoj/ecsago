/*    */ package jml.util.quasimetric;
/*    */ 
/*    */ import jml.structures.BitArray;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hamming
/*    */   extends QuasiMetric
/*    */ {
/*    */   public double distance(Object x, Object y) {
/* 50 */     BitArray bx = (BitArray)x;
/* 51 */     BitArray by = (BitArray)y;
/* 52 */     BitArray ans = new BitArray(bx);
/* 53 */     ans.xor(by);
/* 54 */     int n = bx.size();
/* 55 */     int m = by.size();
/* 56 */     int counter = Math.abs(n - m);
/* 57 */     for (int i = 0; i < ans.size(); i++) {
/* 58 */       if (ans.get(i)) counter++; 
/*    */     } 
/* 60 */     return counter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double max_0_1(int n) {
/* 69 */     return n;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\quasimetric\Hamming.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
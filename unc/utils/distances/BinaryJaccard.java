/*    */ package unc.utils.distances;
/*    */ 
/*    */ import jml.structures.BitArray;
/*    */ import jml.util.quasimetric.QuasiMetric;
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
/*    */ public class BinaryJaccard
/*    */   extends QuasiMetric
/*    */ {
/*    */   public double distance(Object x, Object y) {
/* 30 */     double sim = similarity(x, y);
/* 31 */     if (sim > 1.0D) {
/* 32 */       System.out.println("similarity= " + sim);
/*    */     }
/* 34 */     return (1.0D - sim) * (1.0D - sim);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double similarity(Object x, Object y) {
/* 46 */     BitArray cx = (BitArray)x;
/* 47 */     BitArray cy = (BitArray)y;
/* 48 */     int n = cx.size();
/* 49 */     int count_x = 0;
/* 50 */     int count_y = 0;
/* 51 */     int count_xy = 0;
/* 52 */     for (int i = 0; i < n; i++) {
/* 53 */       if (cx.get(i)) {
/* 54 */         count_x++;
/* 55 */         if (cy.get(i)) {
/* 56 */           count_y++;
/* 57 */           count_xy++;
/*    */         }
/*    */       
/* 60 */       } else if (cy.get(i)) {
/* 61 */         count_y++;
/*    */       } 
/*    */     } 
/*    */     
/* 65 */     return count_xy / (count_x + count_y - count_xy);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double max_0_1(int n) {
/* 74 */     return 1.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\distances\BinaryJaccard.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
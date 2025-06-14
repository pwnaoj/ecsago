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
/*    */ public class BinaryCosine
/*    */   extends QuasiMetric
/*    */ {
/*    */   public double distance(Object x, Object y) {
/* 28 */     double sim = similarity(x, y);
/* 29 */     if (sim > 1.0D) {
/* 30 */       System.out.println("similarity= " + sim);
/*    */     }
/* 32 */     return (1.0D - sim) * (1.0D - sim);
/*    */   }
/*    */   
/*    */   public double similarity(Object x, Object y) {
/* 36 */     BitArray cx = (BitArray)x;
/* 37 */     BitArray cy = (BitArray)y;
/* 38 */     int n = cx.size();
/* 39 */     int count_x = 0;
/* 40 */     int count_y = 0;
/* 41 */     int count_xy = 0;
/* 42 */     for (int i = 0; i < n; i++) {
/* 43 */       if (cx.get(i)) {
/* 44 */         count_x++;
/* 45 */         if (cy.get(i)) {
/* 46 */           count_y++;
/* 47 */           count_xy++;
/*    */         }
/*    */       
/* 50 */       } else if (cy.get(i)) {
/* 51 */         count_y++;
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return count_xy / Math.sqrt((count_x * count_y));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double max_0_1(int n) {
/* 64 */     return 1.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\distances\BinaryCosine.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
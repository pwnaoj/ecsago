/*    */ package unc.evolution.encoding.sparse.binary.distances;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.structures.SparceBitArray;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import jml.util.sort.SortableInt;
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
/*    */ public class SparseBinaryJaccard
/*    */   extends QuasiMetric
/*    */ {
/*    */   public double distance(Object x, Object y) {
/* 35 */     double sim = similarity(x, y);
/* 36 */     return (1.0D - sim) * (1.0D - sim);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double similarity(Object x, Object y) {
/* 47 */     SparceBitArray cx = (SparceBitArray)x;
/* 48 */     SparceBitArray cy = (SparceBitArray)y;
/* 49 */     Vector sx = cx.get();
/* 50 */     Vector sy = cy.get();
/* 51 */     int num = 0;
/*    */ 
/*    */     
/* 54 */     SortableInt ax = null;
/* 55 */     SortableInt by = null;
/* 56 */     Enumeration iter1 = sx.elements();
/* 57 */     Enumeration iter2 = sy.elements();
/* 58 */     while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 59 */       if (ax == null) ax = iter1.nextElement(); 
/* 60 */       if (by == null) by = iter2.nextElement(); 
/* 61 */       if (ax.value == by.value) {
/* 62 */         num++;
/* 63 */         ax = null;
/* 64 */         by = null; continue;
/*    */       } 
/* 66 */       if (ax.value < by.value) {
/* 67 */         ax = null; continue;
/*    */       } 
/* 69 */       by = null;
/*    */     } 
/*    */ 
/*    */     
/* 73 */     return num / (sx.size() + sy.size() - num);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double max_0_1(int n) {
/* 82 */     return 1.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\distances\SparseBinaryJaccard.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
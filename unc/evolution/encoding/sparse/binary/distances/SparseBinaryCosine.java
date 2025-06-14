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
/*    */ public class SparseBinaryCosine
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
/*    */   public double similarity(Object x, Object y) {
/* 38 */     SparceBitArray cx = (SparceBitArray)x;
/* 39 */     SparceBitArray cy = (SparceBitArray)y;
/* 40 */     Vector sx = cx.get();
/* 41 */     Vector sy = cy.get();
/* 42 */     int num = 0;
/*    */ 
/*    */     
/* 45 */     SortableInt ax = null;
/* 46 */     SortableInt by = null;
/* 47 */     Enumeration iter1 = sx.elements();
/* 48 */     Enumeration iter2 = sy.elements();
/* 49 */     while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 50 */       if (ax == null) ax = iter1.nextElement(); 
/* 51 */       if (by == null) by = iter2.nextElement(); 
/* 52 */       if (ax.value == by.value) {
/* 53 */         num++;
/* 54 */         ax = null;
/* 55 */         by = null; continue;
/*    */       } 
/* 57 */       if (ax.value < by.value) {
/* 58 */         ax = null; continue;
/*    */       } 
/* 60 */       by = null;
/*    */     } 
/*    */ 
/*    */     
/* 64 */     return num / Math.sqrt((sx.size() * sy.size()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double max_0_1(int n) {
/* 73 */     return 1.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\distances\SparseBinaryCosine.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
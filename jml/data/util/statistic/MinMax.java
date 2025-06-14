/*    */ package jml.data.util.statistic;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
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
/*    */ public class MinMax
/*    */   extends DataSourceStatistic
/*    */ {
/* 45 */   public double[] min = null;
/*    */ 
/*    */ 
/*    */   
/* 49 */   public double[] max = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(DataSource source) {
/* 56 */     Enumeration iter = source.elements();
/* 57 */     if (iter.hasMoreElements()) {
/* 58 */       Data d = iter.nextElement();
/* 59 */       RealVector x = (RealVector)d.get();
/* 60 */       int n = x.dimension();
/* 61 */       this.min = new double[n];
/* 62 */       this.max = new double[n];
/* 63 */       for (int i = 0; i < n; i++) {
/* 64 */         this.max[i] = x.get(i); this.min[i] = x.get(i);
/*    */       } 
/*    */       
/* 67 */       while (iter.hasMoreElements()) {
/* 68 */         d = iter.nextElement();
/* 69 */         x = (RealVector)d.get();
/* 70 */         for (int j = 0; j < n; j++) {
/* 71 */           double y = x.get(j);
/* 72 */           if (this.min[j] > y) {
/* 73 */             this.min[j] = y;
/*    */           }
/* 75 */           else if (this.max[j] < y) {
/* 76 */             this.max[j] = y;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\statistic\MinMax.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
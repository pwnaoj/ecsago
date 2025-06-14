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
/*    */ 
/*    */ public class Average
/*    */   extends DataSourceStatistic
/*    */ {
/* 46 */   public double[] avg = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   protected int[] m = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(DataSource source) {
/* 58 */     Enumeration iter = source.elements();
/* 59 */     Data d = iter.nextElement();
/* 60 */     RealVector x = (RealVector)d.get();
/* 61 */     int n = x.dimension();
/* 62 */     this.avg = new double[n];
/* 63 */     this.m = new int[n];
/*    */     int i;
/* 65 */     for (i = 0; i < n; i++) {
/* 66 */       double y = x.get(i);
/* 67 */       if (y != -1.0E108D) {
/* 68 */         this.avg[i] = y;
/* 69 */         this.m[i] = 1;
/*    */       } else {
/* 71 */         this.avg[i] = 0.0D;
/* 72 */         this.m[i] = 0;
/*    */       } 
/*    */     } 
/*    */     
/* 76 */     while (iter.hasMoreElements()) {
/* 77 */       d = iter.nextElement();
/* 78 */       x = (RealVector)d.get();
/* 79 */       for (i = 0; i < n; i++) {
/* 80 */         double y = x.get(i);
/* 81 */         if (y != -1.0E108D) {
/* 82 */           this.avg[i] = this.avg[i] + y;
/* 83 */           this.m[i] = this.m[i] + 1;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     for (i = 0; i < n; i++) {
/* 89 */       if (this.m[i] > 0) this.avg[i] = this.avg[i] / this.m[i]; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\statistic\Average.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
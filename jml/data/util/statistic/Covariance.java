/*    */ package jml.data.util.statistic;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import jml.algebra.RealMatrix;
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
/*    */ public class Covariance
/*    */   extends Average
/*    */ {
/* 47 */   public RealMatrix covariance = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(DataSource source) {
/* 54 */     super.generate(source);
/*    */     
/* 56 */     int n = this.avg.length;
/* 57 */     this.covariance = new RealMatrix(n);
/*    */ 
/*    */ 
/*    */     
/* 61 */     Enumeration iter = source.elements();
/* 62 */     while (iter.hasMoreElements()) {
/* 63 */       Data d = iter.nextElement();
/* 64 */       RealVector r = (RealVector)d.get();
/* 65 */       for (int j = 0; j < n; j++) {
/* 66 */         double x = r.get(j);
/* 67 */         if (x != -1.0E108D) {
/* 68 */           x -= this.avg[j];
/* 69 */           for (int k = j; k < n; k++) {
/* 70 */             double y = r.get(k);
/* 71 */             if (y != -1.0E108D) {
/* 72 */               y -= this.avg[k];
/* 73 */               this.covariance.data[j][k] = this.covariance.data[j][k] + x * y;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 79 */     for (int i = 0; i < n; i++) {
/* 80 */       for (int j = i; j < n; j++) {
/* 81 */         if (this.m[i] > 0 && this.m[j] > 0) {
/* 82 */           this.covariance.data[i][j] = this.covariance.data[i][j] / Math.sqrt((this.m[i] - 1) * (this.m[j] - 1));
/*    */         } else {
/* 84 */           this.covariance.data[i][j] = 0.0D;
/*    */         } 
/* 86 */         this.covariance.data[j][i] = this.covariance.data[i][j];
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\statistic\Covariance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
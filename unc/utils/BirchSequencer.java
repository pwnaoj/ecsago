/*    */ package unc.utils;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.data.sources.VectorDataSource;
/*    */ import jml.data.util.DataSourceUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BirchSequencer
/*    */ {
/*    */   public static void main(String[] argv) {
/* 22 */     DataSource source = DataSourceUtils.load_old_format("C:/cygwin/home/eleon/DataSets/Birch/C_Birch_norm.dat", -1);
/*    */     
/* 24 */     Vector v = new Vector();
/* 25 */     double[] ylimits = new double[11];
/* 26 */     ylimits[0] = -0.1D;
/* 27 */     ylimits[10] = 1.1D;
/* 28 */     double ydelta = 0.091D;
/* 29 */     double yborder = 0.045D;
/* 30 */     double[] xlimits = new double[11];
/* 31 */     xlimits[0] = -0.1D;
/* 32 */     xlimits[10] = 1.1D;
/* 33 */     double xdelta = 0.091D;
/* 34 */     double xborder = 0.04D;
/* 35 */     for (int i = 1; i < 10; i++) {
/* 36 */       xlimits[i] = xborder + xdelta * i;
/* 37 */       ylimits[i] = yborder + ydelta * i;
/*    */     } 
/* 39 */     int full = 0;
/* 40 */     for (int j = 0; j < 10; j++) {
/* 41 */       for (int k = 0; k < 10; k++) {
/* 42 */         int m = 0;
/* 43 */         Enumeration iter = source.elements();
/* 44 */         while (iter.hasMoreElements()) {
/* 45 */           Data d = iter.nextElement();
/* 46 */           RealVector r = (RealVector)d.get();
/* 47 */           if (xlimits[j] <= r.get(0) && r.get(0) < xlimits[j + 1] && ylimits[k] <= r.get(1) && r.get(1) < ylimits[k + 1]) {
/*    */             
/* 49 */             v.add(d);
/* 50 */             m++;
/*    */           } 
/*    */         } 
/* 53 */         System.out.print(m + ",");
/* 54 */         full += m;
/*    */       } 
/*    */     } 
/* 57 */     System.out.print(full);
/* 58 */     VectorDataSource vs = new VectorDataSource(v);
/* 59 */     vs.save("C:/cygwin/home/eleon/DataSets/Birch/C_Birch_norm_sort.dat", false);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\BirchSequencer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
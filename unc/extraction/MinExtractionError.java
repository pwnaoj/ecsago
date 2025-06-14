/*    */ package unc.extraction;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.Prototype;
/*    */ import unc.sets.SqrGaussianSet;
/*    */ import unc.utils.DistanceSize;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MinExtractionError
/*    */   extends Extraction
/*    */ {
/*    */   protected DistanceSize data;
/*    */   protected double threshold;
/*    */   protected QuasiMetric metric;
/*    */   
/*    */   public MinExtractionError(DistanceSize _data, double _threshold, QuasiMetric _metric) {
/* 25 */     this.data = _data;
/* 26 */     this.threshold = _threshold;
/* 27 */     this.metric = _metric;
/*    */   }
/*    */   
/*    */   public Prototype apply(Prototype pop) {
/* 31 */     int n = pop.size();
/* 32 */     double[][] distances = new double[n][];
/* 33 */     boolean[] represented = null;
/* 34 */     int k = 0;
/* 35 */     Enumeration iter = pop.elements();
/* 36 */     while (iter.hasMoreElements()) {
/* 37 */       Cluster ind = iter.nextElement();
/* 38 */       this.data.calculate(ind, this.metric);
/* 39 */       double[] d = this.data.getDistance();
/* 40 */       distances[k] = new double[d.length];
/* 41 */       if (represented == null) {
/* 42 */         represented = new boolean[d.length];
/* 43 */         for (int i1 = 0; i1 < d.length; ) { represented[i1] = false; i1++; }
/*    */       
/* 45 */       }  for (int j = 0; j < d.length; j++) {
/* 46 */         distances[k][j] = d[j];
/* 47 */         represented[j] = represented[j] | ((ind.getSet().evaluate(d[j]) >= this.threshold));
/*    */       } 
/* 49 */       k++;
/*    */     } 
/* 51 */     double[] size = this.data.getSize();
/* 52 */     int m = size.length;
/* 53 */     double best_error = 1.0E8D;
/* 54 */     double error = 1.0E8D;
/* 55 */     int error_index = k;
/* 56 */     k = n + 1;
/*    */     
/*    */     do {
/* 59 */       k--;
/* 60 */       double[] cluster_size = new double[k];
/* 61 */       double[] cluster_distances_sum = new double[k]; int j;
/* 62 */       for (j = 0; j < k; j++) {
/* 63 */         cluster_size[j] = 0.0D;
/* 64 */         cluster_distances_sum[j] = 0.0D;
/*    */       } 
/* 66 */       for (j = 0; j < m; j++) {
/* 67 */         if (represented[j]) {
/* 68 */           int index = 0;
/* 69 */           double d = distances[0][j];
/* 70 */           for (int i1 = 1; i1 < k; i1++) {
/* 71 */             if (d > distances[i1][j]) {
/* 72 */               d = distances[i1][j];
/* 73 */               index = i1;
/*    */             } 
/*    */           } 
/* 76 */           cluster_size[index] = cluster_size[index] + size[j];
/* 77 */           cluster_distances_sum[index] = cluster_distances_sum[index] + d * size[j];
/*    */         } 
/*    */       } 
/*    */       
/* 81 */       error = 0.0D;
/* 82 */       for (j = 0; j < k; j++) {
/* 83 */         Cluster c = (Cluster)pop.get(j);
/* 84 */         SqrGaussianSet set = (SqrGaussianSet)c.getSet();
/* 85 */         error += Math.abs(set.getSqrSigma() - cluster_distances_sum[j] / cluster_size[j]);
/*    */       } 
/*    */       
/* 88 */       System.out.println(k + ":" + error);
/* 89 */       if (error >= best_error)
/* 90 */         continue;  best_error = error;
/* 91 */       error_index = k;
/*    */     }
/* 93 */     while (k > 1);
/*    */     
/* 95 */     Vector extracted = new Vector();
/* 96 */     for (int i = 0; i < error_index; i++) {
/* 97 */       extracted.add(pop.get(i));
/*    */     }
/* 99 */     return new Prototype(extracted);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\extraction\MinExtractionError.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.preprocessing;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.algebra.SparseValue;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.data.sources.FileDataSource;
/*    */ 
/*    */ public class Hitech {
/*    */   public Hitech(String _name) {
/* 12 */     this.name = _name;
/*    */   }
/*    */   protected String name;
/*    */   public void max_normalization(DataSource source) {
/* 16 */     Enumeration iter = source.elements();
/* 17 */     while (iter.hasMoreElements()) {
/* 18 */       Data d = iter.nextElement();
/* 19 */       RealVector rv = (RealVector)d.get();
/* 20 */       rv.divide(rv.max());
/*    */     } 
/*    */   }
/*    */   public void max_normalization() {
/* 24 */     DataSource source = (new FileDataSource(this.name, -1)).optimize();
/* 25 */     max_normalization(source);
/* 26 */     source.save(this.name + "-max.txt", false);
/*    */   }
/*    */   
/*    */   public void sum_normalization(DataSource source) {
/* 30 */     Enumeration iter = source.elements();
/* 31 */     while (iter.hasMoreElements()) {
/* 32 */       Data d = iter.nextElement();
/* 33 */       RealVector rv = (RealVector)d.get();
/* 34 */       rv.divide(rv.summation());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void sum_normalization() {
/* 39 */     DataSource source = (new FileDataSource(this.name, -1)).optimize();
/* 40 */     sum_normalization(source);
/* 41 */     source.save(this.name + "-sum.txt", false);
/*    */   }
/*    */   
/*    */   public void tfifd_normalization(DataSource source) {
/* 45 */     int m = source.size();
/* 46 */     int n = ((RealVector)source.get(0).get()).dimension();
/* 47 */     double[] df = new double[n];
/* 48 */     for (int i = 0; i < n; ) { df[i] = 0.0D; i++; }
/* 49 */      Enumeration iter = source.elements();
/* 50 */     while (iter.hasMoreElements()) {
/* 51 */       Data d = iter.nextElement();
/* 52 */       Vector v = ((RealVector)d.get()).get().sparce_values();
/* 53 */       Enumeration iter2 = v.elements();
/* 54 */       while (iter2.hasMoreElements()) {
/* 55 */         SparseValue sv = iter2.nextElement();
/* 56 */         df[sv.index] = df[sv.index] + sv.value;
/*    */       } 
/*    */     } 
/* 59 */     double[] idf = new double[n];
/* 60 */     double log_10 = Math.log(10.0D);
/* 61 */     for (int j = 0; j < n; ) { idf[j] = Math.log(m / df[j]) / log_10; j++; }
/* 62 */      iter = source.elements();
/* 63 */     while (iter.hasMoreElements()) {
/* 64 */       Data d = iter.nextElement();
/* 65 */       Vector v = ((RealVector)d.get()).get().sparce_values();
/* 66 */       Enumeration iter2 = v.elements();
/* 67 */       while (iter2.hasMoreElements()) {
/* 68 */         SparseValue sv = iter2.nextElement();
/* 69 */         sv.value *= idf[sv.index];
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void tfifd_normalization() {
/* 75 */     DataSource source = (new FileDataSource(this.name, -1)).optimize();
/* 76 */     tfifd_normalization(source);
/* 77 */     source.save(this.name + "-tfifd.txt", false);
/*    */   }
/*    */   
/*    */   public void tfifd_max_normalization() {
/* 81 */     DataSource source = (new FileDataSource(this.name, -1)).optimize();
/* 82 */     tfifd_normalization(source);
/* 83 */     max_normalization(source);
/* 84 */     source.save(this.name + "-tfifd-max.txt", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] argv) {
/* 89 */     Hitech h = new Hitech(argv[0]);
/* 90 */     h.max_normalization();
/* 91 */     h.sum_normalization();
/* 92 */     h.tfifd_max_normalization();
/* 93 */     h.tfifd_normalization();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\preprocessing\Hitech.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
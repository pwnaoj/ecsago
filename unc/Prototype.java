/*     */ package unc;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.generators.DataGenerator;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import jml.util.sort.MergeSort;
/*     */ import unc.utils.ClusterDataGenerator;
/*     */ import unc.utils.SetGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Prototype
/*     */ {
/*  25 */   Vector data = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Prototype(Vector _Clusters) {
/*  32 */     this.data = _Clusters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Prototype(DataSource source) {
/*  40 */     Enumeration iter = source.elements();
/*  41 */     while (iter.hasMoreElements()) {
/*  42 */       this.data.add(((Data)iter.nextElement()).get());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Prototype(String predFile, boolean binary, boolean sparce, SetGenerator set_gen) {
/*  49 */     this((new FileDataSource(predFile, -1, (DataGenerator)new ClusterDataGenerator(0, binary, sparce, set_gen))).optimize());
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int i) {
/*  54 */     return this.data.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sort() {
/*  61 */     MergeSort sort = new MergeSort();
/*  62 */     sort.apply(this.data, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double avg() {
/*  70 */     double sumFitness = 0.0D;
/*  71 */     for (int i = 0; i < this.data.size(); i++) {
/*  72 */       sumFitness += ((Cluster)this.data.get(i)).fitness;
/*     */     }
/*  74 */     return sumFitness / this.data.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  82 */     String cs = "";
/*  83 */     for (int c = 0; c < this.data.size(); c++) {
/*  84 */       cs = cs + this.data.elementAt(c).toString() + "\n";
/*     */     }
/*  86 */     return cs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int clusteredCrispMembership(Object x, double K, QuasiMetric metric) {
/*  96 */     int c = 0;
/*  97 */     while (c < this.data.size() && ((Cluster)this.data.get(c)).crispMembership(x, K, metric) == 0) {
/*  98 */       c++;
/*     */     }
/* 100 */     if (c == this.data.size()) {
/* 101 */       return 0;
/*     */     }
/* 103 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double clusteredFuzzyMembership(Object x, QuasiMetric metric) {
/* 114 */     int c = 0;
/* 115 */     double val = 0.0D;
/*     */     
/* 117 */     while (c < this.data.size()) {
/* 118 */       double newVal = ((Cluster)this.data.get(c)).fuzzyMembership(x, metric);
/* 119 */       if (newVal > val) {
/* 120 */         val = newVal;
/*     */       }
/* 122 */       c++;
/*     */     } 
/* 124 */     return val;
/*     */   }
/*     */   public Enumeration elements() {
/* 127 */     return this.data.elements();
/*     */   } public int size() {
/* 129 */     return this.data.size();
/*     */   }
/*     */   public void set(Vector c) {
/* 132 */     this.data = c;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\Prototype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
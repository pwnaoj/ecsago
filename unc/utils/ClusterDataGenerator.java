/*    */ package unc.utils;
/*    */ 
/*    */ import jml.data.Data;
/*    */ import jml.data.generators.DataGenerator;
/*    */ import jml.data.generators.FullDataGenerator;
/*    */ import jml.data.generators.SparseBitArrayDataGenerator;
/*    */ import jml.data.generators.SparseDataGenerator;
/*    */ import jml.fuzzy.Set;
/*    */ import unc.Cluster;
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
/*    */ public class ClusterDataGenerator
/*    */   extends DataGenerator
/*    */ {
/* 31 */   protected DataGenerator gen = null;
/*    */   
/* 33 */   protected SetGenerator set_generator = null;
/*    */ 
/*    */   
/*    */   public ClusterDataGenerator(int _dimension, boolean binary, boolean sparce, SetGenerator _set_generator) {
/* 37 */     super(_dimension, -1);
/* 38 */     this.set_generator = _set_generator;
/* 39 */     if (binary) {
/* 40 */       if (sparce) {
/* 41 */         this.gen = (DataGenerator)new SparseBitArrayDataGenerator(this.dimension, -1);
/*    */       }
/*    */     }
/* 44 */     else if (sparce) {
/* 45 */       this.gen = (DataGenerator)new SparseDataGenerator(this.dimension, -1);
/*    */     } else {
/* 47 */       this.gen = (DataGenerator)new FullDataGenerator(this.dimension, -1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ClusterDataGenerator() {
/* 54 */     super(0, -1);
/*    */   }
/*    */   
/*    */   public void set(DataGenerator g) {
/* 58 */     this.gen = g;
/*    */   }
/*    */   
/*    */   public Data get(double[] d) {
/* 62 */     int label = 0;
/*    */     
/* 64 */     int sm = this.set_generator.parameters();
/* 65 */     int m = d.length - sm - 1;
/* 66 */     double[] d1 = new double[m];
/* 67 */     for (int i = 0; i < m; i++) {
/* 68 */       d1[i] = d[i];
/*    */     }
/*    */     
/* 71 */     double[] p = new double[sm];
/* 72 */     for (int j = 0; j < sm; ) { p[j] = d[m + j]; j++; }
/* 73 */      Set set = this.set_generator.get(p);
/*    */     
/* 75 */     double fitness = d[m + sm];
/*    */     
/* 77 */     Data data = this.gen.get(d1);
/* 78 */     Cluster cluster = new Cluster(data.get(), set, fitness);
/* 79 */     return new Data(cluster, label);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\ClusterDataGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
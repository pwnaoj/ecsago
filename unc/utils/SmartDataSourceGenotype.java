/*     */ package unc.utils;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Phenotype;
/*     */ import jml.random.UniformNumberGenerator;
/*     */ import jml.util.quasimetric.QuasiMetric;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SmartDataSourceGenotype
/*     */   extends Genotype
/*     */ {
/*  27 */   protected DataSource original_source = null;
/*  28 */   protected Vector source = null;
/*     */   protected UniformNumberGenerator ng;
/*     */   protected Phenotype phenotype;
/*     */   protected int n;
/*     */   protected QuasiMetric metric;
/*  33 */   protected int counter = 0;
/*     */ 
/*     */   
/*     */   public SmartDataSourceGenotype(DataSource _source, Phenotype _phenotype, int _n, QuasiMetric _metric) {
/*  37 */     this.phenotype = _phenotype;
/*  38 */     this.original_source = _source;
/*  39 */     this.n = this.original_source.size() / _n;
/*  40 */     this.metric = _metric;
/*  41 */     copy_source();
/*     */   }
/*     */   
/*     */   protected void copy_source() {
/*  45 */     this.source = new Vector();
/*  46 */     Enumeration iter = this.original_source.elements();
/*  47 */     while (iter.hasMoreElements()) {
/*  48 */       this.source.add(iter.nextElement());
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object get() {
/*  53 */     this.ng = new UniformNumberGenerator(this.source.size());
/*  54 */     int index = this.ng.newInt();
/*  55 */     Object obj = ((Data)this.source.get(index)).get();
/*  56 */     this.source.remove(index);
/*  57 */     double[] distances = new double[this.n - 1];
/*  58 */     int[] indices = new int[this.n - 1]; int j;
/*  59 */     for (j = 0; j < distances.length; j++) {
/*  60 */       distances[j] = this.metric.distance(obj, ((Data)this.source.get(j)).get());
/*  61 */       indices[j] = j;
/*     */     } 
/*  63 */     for (j = this.n; j < this.source.size(); j++) {
/*  64 */       int k = j;
/*  65 */       double d = this.metric.distance(obj, ((Data)this.source.get(j)).get());
/*  66 */       for (int m = 0; m < this.n - 1; m++) {
/*  67 */         if (d < distances[m]) {
/*  68 */           double td = distances[m];
/*  69 */           distances[m] = d;
/*  70 */           d = td;
/*  71 */           int ti = indices[m];
/*  72 */           indices[m] = k;
/*  73 */           k = ti;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     for (int i = 0; i < this.n - 1; i++) {
/*  79 */       for (int k = i + 1; k < this.n - 1; k++) {
/*  80 */         if (indices[i] < indices[k]) {
/*  81 */           int ti = indices[i];
/*  82 */           indices[i] = indices[k];
/*  83 */           indices[k] = ti;
/*     */         } 
/*     */       } 
/*  86 */       this.source.remove(indices[i]);
/*     */     } 
/*     */     
/*  89 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object newInstance() {
/*  99 */     return this.phenotype.set(get());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Object object) {
/* 110 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\SmartDataSourceGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
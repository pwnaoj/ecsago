/*     */ package unc.utils;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.Cluster;
/*     */ import unc.Prototype;
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
/*     */ public class DistanceSize
/*     */ {
/*  24 */   public DataSource data = null;
/*  25 */   public Prototype prototype = null;
/*     */   protected double[] distance;
/*     */   protected double[] size;
/*     */   protected Set[] set;
/*     */   
/*     */   public DistanceSize(DataSource _data) {
/*  31 */     set(_data, null);
/*     */   }
/*     */   
/*     */   public DistanceSize(DataSource _data, Prototype _prototype) {
/*  35 */     set(_data, _prototype);
/*     */   }
/*     */   
/*     */   public void set(DataSource _data, Prototype _prototype) {
/*  39 */     this.data = _data;
/*  40 */     this.prototype = _prototype;
/*  41 */     resize();
/*     */   }
/*     */   
/*     */   protected void resize() {
/*  45 */     int ds = this.data.size();
/*  46 */     int s = ds;
/*  47 */     if (this.prototype != null) s += this.prototype.size(); 
/*  48 */     this.distance = new double[s];
/*  49 */     this.size = new double[s];
/*     */     int i;
/*  51 */     for (i = 0; i < ds; ) { this.size[i] = 1.0D; i++; }
/*  52 */      if (this.prototype != null) {
/*  53 */       for (i = 0; i < this.prototype.size(); i++) {
/*  54 */         this.size[ds + i] = ((Cluster)this.prototype.get(i)).size;
/*     */       }
/*     */     }
/*  57 */     this.set = new Set[s];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calculate(Cluster x, QuasiMetric metric) {
/*  67 */     if (x.distances == null) {
/*  68 */       Object center = x.getCenter();
/*  69 */       int j = 0;
/*  70 */       Enumeration iter = this.data.elements();
/*  71 */       while (iter.hasMoreElements()) {
/*  72 */         Data d = iter.nextElement();
/*  73 */         this.distance[j] = metric.distance(center, d.get());
/*  74 */         this.set[j] = null;
/*  75 */         j++;
/*     */       } 
/*     */       
/*  78 */       if (this.prototype != null) {
/*  79 */         iter = this.prototype.elements();
/*  80 */         while (iter.hasMoreElements()) {
/*  81 */           Cluster c = (Cluster)iter.nextElement();
/*  82 */           this.distance[j] = metric.distance(center, c.getCenter());
/*  83 */           this.set[j] = c.getSet();
/*  84 */           j++;
/*     */         } 
/*     */       } 
/*  87 */       x.distances = (double[])this.distance.clone();
/*     */     } else {
/*  89 */       this.distance = x.distances;
/*     */     } 
/*     */   }
/*     */   public double[] getSize() {
/*  93 */     return this.size;
/*     */   } public double[] getDistance() {
/*  95 */     return this.distance;
/*     */   } public Set[] getSet() {
/*  97 */     return this.set;
/*     */   }
/*     */   public double samples() {
/* 100 */     int s = this.size.length;
/* 101 */     double samp = 0.0D;
/* 102 */     for (int i = 0; i < s; i++) {
/* 103 */       if (this.size[i] != 1.0D) {
/* 104 */         System.out.println("size[" + i + "]=" + this.size[i]);
/*     */       }
/* 106 */       samp += this.size[i];
/*     */     } 
/* 108 */     System.out.println("data samples = " + samp);
/* 109 */     return samp;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\DistanceSize.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
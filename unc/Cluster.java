/*     */ package unc;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseValue;
/*     */ import jml.basics.Cloner;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import jml.util.sort.SortableObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cluster
/*     */   extends SortableObject
/*     */   implements Cloneable
/*     */ {
/*  24 */   public double[] distances = null;
/*     */   
/*     */   protected Set set;
/*     */   
/*     */   protected Object center;
/*     */   
/*  30 */   protected double fitness = 0.0D;
/*     */   
/*  32 */   public double size = 1.0D;
/*     */   
/*     */   public Cluster(Object _center, Set _set) {
/*  35 */     this.center = _center;
/*  36 */     this.set = _set;
/*     */   }
/*     */   
/*     */   public Cluster(Object _center, Set _set, double _fitness) {
/*  40 */     this.center = _center;
/*  41 */     this.set = _set;
/*  42 */     this.fitness = _fitness;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  46 */     return new Cluster(Cloner.clone(this.center), (Set)Cloner.clone(this.set), this.fitness);
/*     */   }
/*     */   public Set getSet() {
/*  49 */     return this.set;
/*     */   } public void setSet(Set _set) {
/*  51 */     this.set = _set;
/*     */   } public Object getCenter() {
/*  53 */     return this.center;
/*     */   } public void setCenter(Object _center) {
/*  55 */     this.center = _center;
/*     */   } public double getFitness() {
/*  57 */     return this.fitness;
/*     */   } public void setFitness(double _fitness) {
/*  59 */     this.fitness = _fitness;
/*     */   }
/*     */   public void normalization(int sparse) {
/*  62 */     if (sparse == 1) {
/*     */       
/*  64 */       RealVector m = (RealVector)this.center;
/*  65 */       Components cm = m.get();
/*  66 */       Vector sm = cm.sparce_values();
/*  67 */       double frequencies = 0.0D;
/*     */       
/*  69 */       Enumeration iter = sm.elements();
/*  70 */       Enumeration iter2 = sm.elements();
/*  71 */       while (iter.hasMoreElements()) {
/*  72 */         SparseValue am = iter.nextElement();
/*  73 */         frequencies += am.value * am.value;
/*     */       } 
/*  75 */       double value = Math.sqrt(frequencies);
/*  76 */       while (iter2.hasMoreElements()) {
/*  77 */         SparseValue am = iter2.nextElement();
/*  78 */         am.value /= value;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int crispMembership(Object x, double K, QuasiMetric metric) {
/*  91 */     if (this.set.evaluate(metric.distance(x, this.center)) >= K) return 1; 
/*  92 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double fuzzyMembership(Object x, QuasiMetric metric) {
/* 102 */     return this.set.evaluate(metric.distance(x, this.center));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuffer sb = new StringBuffer();
/* 107 */     sb.append(this.center.toString());
/* 108 */     sb.append(" ");
/* 109 */     sb.append(this.set.parameters(false));
/* 110 */     sb.append(" ");
/* 111 */     sb.append(this.fitness);
/* 112 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalTo(SortableObject other) {
/* 117 */     boolean flag = other instanceof Cluster;
/* 118 */     return (flag && ((Cluster)other).fitness == this.fitness);
/*     */   }
/*     */   
/*     */   public boolean lessThan(SortableObject other) {
/* 122 */     boolean flag = other instanceof Cluster;
/* 123 */     return (flag && ((Cluster)other).fitness > this.fitness);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\Cluster.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
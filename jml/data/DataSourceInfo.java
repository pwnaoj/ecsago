/*     */ package jml.data;
/*     */ 
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ public class DataSourceInfo
/*     */ {
/*  46 */   protected int m = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   Vector features = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   CategoricAttribute class_feature = null;
/*     */   
/*     */   private static final int MIN = 0;
/*     */   private static final int MAX = 1;
/*     */   private static final int AVG = 2;
/*     */   private static final int STD = 3;
/*     */   private static final int MED = 4;
/*     */   
/*     */   public DataSourceInfo(int size, Vector _features, CategoricAttribute _class_feature) {
/*  65 */     this.m = size;
/*  66 */     this.features = _features;
/*  67 */     this.class_feature = _class_feature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSourceInfo(DataSourceInfo source) {
/*  75 */     this.m = source.m;
/*  76 */     this.features = new Vector();
/*  77 */     for (int i = 0; i < source.dimension(); i++) {
/*  78 */       this.features.add(source.features.get(i));
/*     */     }
/*  80 */     this.class_feature = source.class_feature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dimension() {
/*  88 */     return this.features.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  94 */     return this.m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int classes() {
/* 101 */     return (this.class_feature != null) ? this.class_feature.size() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getFeatures() {
/* 108 */     return this.features;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numberOfClasses() {
/* 117 */     return this.class_feature.size();
/*     */   }
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
/*     */   private double[] getValue(int index) {
/* 132 */     int n = dimension();
/* 133 */     double[] val = new double[n];
/* 134 */     for (int i = 0; i < n; i++) {
/* 135 */       if (this.features.get(i) instanceof NumericAttribute) {
/* 136 */         switch (index) {
/*     */           case 0:
/* 138 */             val[i] = ((NumericAttribute)this.features.get(i)).min;
/*     */             break;
/*     */           case 1:
/* 141 */             val[i] = ((NumericAttribute)this.features.get(i)).max;
/*     */             break;
/*     */           case 2:
/* 144 */             val[i] = ((NumericAttribute)this.features.get(i)).average;
/*     */             break;
/*     */           case 3:
/* 147 */             val[i] = ((NumericAttribute)this.features.get(i)).std_deviation;
/*     */             break;
/*     */           case 4:
/* 150 */             val[i] = ((NumericAttribute)this.features.get(i)).median;
/*     */             break;
/*     */           default:
/* 153 */             val[i] = -1.0E108D;
/*     */             break;
/*     */         } 
/*     */       } else {
/* 157 */         val[i] = -1.0E108D;
/*     */       } 
/*     */     } 
/* 160 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getMin() {
/* 168 */     return getValue(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getMax() {
/* 176 */     return getValue(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getAverage() {
/* 184 */     return getValue(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getStdDeviation() {
/* 192 */     return getValue(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getMedian() {
/* 199 */     return getValue(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setValue(int index, double[] val) {
/* 206 */     int n = dimension();
/* 207 */     for (int i = 0; i < n; i++) {
/* 208 */       if (this.features.get(i) instanceof NumericAttribute) {
/* 209 */         switch (index) {
/*     */           case 0:
/* 211 */             ((NumericAttribute)this.features.get(i)).min = val[i];
/*     */             break;
/*     */           case 1:
/* 214 */             ((NumericAttribute)this.features.get(i)).max = val[i];
/*     */             break;
/*     */           case 2:
/* 217 */             ((NumericAttribute)this.features.get(i)).average = val[i];
/*     */             break;
/*     */           case 3:
/* 220 */             ((NumericAttribute)this.features.get(i)).std_deviation = val[i];
/*     */             break;
/*     */           case 4:
/* 223 */             ((NumericAttribute)this.features.get(i)).median = val[i];
/*     */             break;
/*     */         } 
/*     */       } else {
/* 227 */         val[i] = -1.0E108D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMin(double[] val) {
/* 237 */     setValue(0, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMax(double[] val) {
/* 245 */     setValue(1, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAverage(double[] val) {
/* 252 */     setValue(2, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStdDeviation(double[] val) {
/* 259 */     setValue(3, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMedian(double[] val) {
/* 266 */     setValue(4, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 274 */     this.m = size;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\DataSourceInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
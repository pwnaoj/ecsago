/*     */ package jml.classification.binarization;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealMatrix;
/*     */ import jml.basics.Result;
/*     */ import jml.classification.Classifier;
/*     */ import jml.classification.Prediction;
/*     */ import jml.classification.ROC;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
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
/*     */ public class BinaryClassifier
/*     */   extends Classifier
/*     */ {
/*  47 */   public static double ROC_EPSILON = 0.01D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryClassifier(BinaryRecognizer _recognizer) {
/*  55 */     super(_recognizer, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryClassifier(BinaryRecognizer _recognizer, int _MOVING_AVERAGE) {
/*  66 */     super(_recognizer, null, _MOVING_AVERAGE);
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
/*     */   public Prediction predict(Object data) {
/*  79 */     double[] conf = this.recognizer.confidence(data);
/*  80 */     return new Prediction(data, 1, conf[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vector getLabels(DataSource source) {
/*  89 */     Vector v = new Vector();
/*  90 */     Enumeration iter = source.elements();
/*  91 */     while (iter.hasMoreElements()) {
/*  92 */       Data d = iter.nextElement();
/*  93 */       v.add(new Integer(d.getLabel()));
/*     */     } 
/*  95 */     return v;
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
/*     */   
/*     */   public static double[] FP_TP(Vector source, double confidence, Vector positive_level) {
/* 111 */     int tp = 0;
/* 112 */     int fp = 0;
/* 113 */     int total_positive = 0;
/* 114 */     int total_negative = 0;
/* 115 */     double[] point = new double[3];
/* 116 */     point[2] = confidence;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     Enumeration v_iter = positive_level.elements();
/* 122 */     Enumeration iter = source.elements();
/* 123 */     while (iter.hasMoreElements()) {
/* 124 */       Integer d = iter.nextElement();
/* 125 */       Prediction p = v_iter.nextElement();
/*     */       
/* 127 */       if (d.intValue() != 0) {
/* 128 */         total_positive++;
/* 129 */         if (p.getConfidence() >= confidence) tp++;  continue;
/*     */       } 
/* 131 */       total_negative++;
/* 132 */       if (p.getConfidence() >= confidence) fp++;
/*     */     
/*     */     } 
/*     */     
/* 136 */     if (fp != 0) { point[0] = fp / total_negative; }
/* 137 */     else { point[0] = 0.0D; }
/*     */     
/* 139 */     if (tp != 0) { point[1] = tp / total_positive; }
/* 140 */     else { point[1] = 0.0D; }
/*     */     
/* 142 */     return point;
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
/*     */   public double[] FP_TP(DataSource source) {
/* 154 */     Vector pred = classify(source);
/* 155 */     Vector real = getLabels(source);
/* 156 */     return FP_TP(real, 0.0D, pred);
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
/*     */   public static void generateROC(Vector source, Vector positive_level, ROC r, double a, double b) {
/* 171 */     if (Math.abs(b - a) > ROC_EPSILON) {
/* 172 */       double m = (a + b) / 2.0D;
/* 173 */       double[] p = FP_TP(source, m, positive_level);
/* 174 */       r.add(p);
/* 175 */       int pos = r.search(p[0]);
/* 176 */       if (pos < r.size() - 1) {
/* 177 */         double[] q = r.get(pos + 1);
/* 178 */         if (Math.abs(p[1] - q[1]) > ROC_EPSILON) {
/* 179 */           generateROC(source, positive_level, r, m, b);
/*     */         }
/*     */       } 
/* 182 */       pos = r.search(p[0]);
/* 183 */       if (pos > 0) {
/* 184 */         double[] q = r.get(pos - 1);
/* 185 */         if (Math.abs(p[1] - q[1]) > ROC_EPSILON) {
/* 186 */           generateROC(source, positive_level, r, a, m);
/*     */         }
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
/*     */   
/*     */   public static ROC generateROC(Vector source, Vector prediction) {
/* 201 */     ROC r = new ROC();
/* 202 */     generateROC(source, prediction, r, 0.0D, 1.0D);
/* 203 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ROC generateROC(DataSource source) {
/* 213 */     return generateROC(getLabels(source), classify(source));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result statistics(DataSource source) {
/* 222 */     return (Result)new BinaryStatistics(this, confusionMatrix(source), generateROC(source));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class BinaryStatistics
/*     */     extends Classifier.Statistics
/*     */     implements Cloneable
/*     */   {
/*     */     public int ROC_SAMPLING_SIZE;
/*     */ 
/*     */ 
/*     */     
/*     */     public ROC roc;
/*     */ 
/*     */ 
/*     */     
/*     */     private final BinaryClassifier this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BinaryStatistics(BinaryClassifier this$0, RealMatrix c, ROC _roc) {
/* 246 */       super(this$0, c); this.this$0 = this$0; this.ROC_SAMPLING_SIZE = 1001; this.roc = null;
/* 247 */       this.confusion = c;
/* 248 */       this.roc = _roc;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BinaryStatistics(BinaryClassifier this$0, BinaryStatistics source) {
/* 256 */       super(this$0, new RealMatrix(source.confusion)); this.this$0 = this$0; this.ROC_SAMPLING_SIZE = 1001; this.roc = null;
/* 257 */       this.roc = new ROC(source.roc);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 265 */       return new BinaryStatistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 272 */       super.sum(_other);
/* 273 */       if (_other instanceof BinaryStatistics) {
/* 274 */         BinaryStatistics other = (BinaryStatistics)_other;
/* 275 */         ROC r1 = this.roc.sampling(this.ROC_SAMPLING_SIZE);
/* 276 */         ROC r2 = other.roc.sampling(this.ROC_SAMPLING_SIZE);
/* 277 */         for (int i = 0; i < this.ROC_SAMPLING_SIZE; i++) {
/* 278 */           double[] p = r1.points.elementAt(i);
/* 279 */           double[] q = r2.points.elementAt(i);
/* 280 */           p[1] = p[1] + q[1];
/* 281 */           p[2] = p[2] + q[2];
/*     */         } 
/* 283 */         this.roc = r1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void substract(Result _other) {
/* 292 */       super.substract(_other);
/* 293 */       if (_other instanceof BinaryStatistics) {
/* 294 */         BinaryStatistics other = (BinaryStatistics)_other;
/* 295 */         ROC r1 = this.roc.sampling(this.ROC_SAMPLING_SIZE);
/* 296 */         ROC r2 = other.roc.sampling(this.ROC_SAMPLING_SIZE);
/* 297 */         for (int i = 0; i < this.ROC_SAMPLING_SIZE; i++) {
/* 298 */           double[] p = r1.points.elementAt(i);
/* 299 */           double[] q = r2.points.elementAt(i);
/* 300 */           p[1] = p[1] - q[1];
/* 301 */           p[2] = p[2] - q[2];
/*     */         } 
/* 303 */         this.roc = r1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void divide(double n) {
/* 312 */       super.divide(n);
/* 313 */       for (int i = 0; i < this.roc.points.size(); i++) {
/* 314 */         double[] p = this.roc.points.elementAt(i);
/* 315 */         p[1] = p[1] / n;
/* 316 */         p[2] = p[2] / n;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void square() {
/* 324 */       super.square();
/* 325 */       for (int i = 0; i < this.roc.points.size(); i++) {
/* 326 */         double[] p = this.roc.points.elementAt(i);
/* 327 */         p[1] = p[1] * p[1];
/* 328 */         p[2] = p[2] * p[2];
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 337 */       StringBuffer sb = new StringBuffer();
/* 338 */       sb.append(super.toString());
/* 339 */       sb.append("\n[ROC]\n");
/* 340 */       sb.append(this.roc.toString());
/* 341 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\BinaryClassifier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
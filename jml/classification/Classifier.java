/*     */ package jml.classification;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealMatrix;
/*     */ import jml.basics.Result;
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
/*     */ public class Classifier
/*     */ {
/*     */   public Recognizer recognizer;
/*     */   public Aggregator aggregator;
/*  60 */   public int MOVING_AVERAGE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Classifier(Recognizer _recognizer, Aggregator _aggregator) {
/*  69 */     this.recognizer = _recognizer;
/*  70 */     this.aggregator = _aggregator;
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
/*     */   public Classifier(Recognizer _recognizer, Aggregator _aggregator, int _MOVING_AVERAGE) {
/*  83 */     this.recognizer = _recognizer;
/*  84 */     this.aggregator = _aggregator;
/*  85 */     this.MOVING_AVERAGE = _MOVING_AVERAGE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int classesNumber() {
/*  92 */     return this.recognizer.classesNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Prediction predict(Object data) {
/* 100 */     return this.aggregator.apply(data, this.recognizer.confidence(data));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] confidence(Object data) {
/* 110 */     return this.recognizer.confidence(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector classify(DataSource source) {
/* 119 */     Vector v = new Vector();
/* 120 */     Enumeration iter = source.elements();
/* 121 */     while (iter.hasMoreElements()) {
/* 122 */       v.add(predict(((Data)iter.nextElement()).get()));
/*     */     }
/* 124 */     if (this.MOVING_AVERAGE > 0) {
/* 125 */       moving_average(v, this.MOVING_AVERAGE);
/*     */     }
/* 127 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moving_average(Vector v, int n) {
/* 136 */     Vector v2 = new Vector();
/*     */ 
/*     */     
/* 139 */     double sum = 0.0D; int i;
/* 140 */     for (i = 0; i < n; i++) {
/* 141 */       Prediction p = v.elementAt(i);
/* 142 */       sum += p.getConfidence();
/* 143 */       Prediction q = new Prediction(p.get(), p.getLabel(), sum / (i + 1));
/* 144 */       v2.add(q);
/*     */     } 
/* 146 */     for (i = n; i < v.size(); i++) {
/* 147 */       Prediction p = v.elementAt(i - n);
/* 148 */       sum -= p.getConfidence();
/* 149 */       p = v.elementAt(i);
/* 150 */       sum += p.getConfidence();
/* 151 */       Prediction q = new Prediction(p.get(), p.getLabel(), sum / n);
/* 152 */       v2.add(q);
/*     */     } 
/*     */     
/* 155 */     for (i = 0; i < v.size(); i++) {
/* 156 */       v.set(i, v2.elementAt(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RealMatrix confusionMatrix(int n, DataSource source, Vector prediction) {
/* 167 */     RealMatrix m = null;
/* 168 */     if (source != null && n > 1) {
/* 169 */       m = new RealMatrix(n, n);
/*     */       
/* 171 */       Enumeration iter = source.elements();
/* 172 */       Enumeration iter2 = prediction.elements();
/* 173 */       while (iter.hasMoreElements() && iter2.hasMoreElements()) {
/* 174 */         Data d = iter.nextElement();
/* 175 */         int p = ((Integer)iter2.nextElement()).intValue();
/* 176 */         if (d.getLabel() >= 0) {
/* 177 */           m.data[d.getLabel()][p] = m.data[d.getLabel()][p] + 1.0D;
/*     */         }
/*     */       } 
/*     */     } 
/* 181 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double calculateAccuracy(RealMatrix c) {
/* 190 */     double acc = c.diagonal_sum();
/* 191 */     double full = 0.0D;
/* 192 */     for (int i = 0; i < c.rows(); i++) {
/* 193 */       full += c.row_sum(i);
/*     */     }
/* 195 */     if (full > 0.0D) { acc /= full; } else { acc = 0.0D; }
/* 196 */      return acc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealMatrix confusionMatrix(DataSource source) {
/* 207 */     RealMatrix m = null;
/* 208 */     int n = classesNumber();
/* 209 */     if (n > 0 && source != null) {
/* 210 */       m = new RealMatrix(n, n);
/*     */ 
/*     */ 
/*     */       
/* 214 */       Enumeration iter = source.elements();
/* 215 */       while (iter.hasMoreElements()) {
/* 216 */         Data d = iter.nextElement();
/* 217 */         if (d.getLabel() >= 0) {
/* 218 */           Prediction p = predict(d.get());
/* 219 */           if (p != null) {
/* 220 */             if (n == 2) {
/* 221 */               if (p.getConfidence() > 0.5D) {
/* 222 */                 m.data[d.getLabel()][p.getLabel()] = m.data[d.getLabel()][p.getLabel()] + 1.0D;
/*     */                 continue;
/*     */               } 
/* 225 */               m.data[d.getLabel()][1 - p.getLabel()] = m.data[d.getLabel()][1 - p.getLabel()] + 1.0D;
/*     */               
/*     */               continue;
/*     */             } 
/* 229 */             m.data[d.getLabel()][p.getLabel()] = m.data[d.getLabel()][p.getLabel()] + 1.0D;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result statistics(DataSource source) {
/* 249 */     return new Statistics(this, confusionMatrix(source));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 257 */     StringBuffer sb = new StringBuffer();
/* 258 */     sb.append("[Recognizer]\n");
/* 259 */     sb.append(this.recognizer.toString());
/* 260 */     sb.append("[Aggregation]\n");
/* 261 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Statistics
/*     */     extends Result
/*     */     implements Cloneable
/*     */   {
/*     */     protected RealMatrix confusion;
/*     */ 
/*     */ 
/*     */     
/*     */     protected double accuracy;
/*     */ 
/*     */     
/*     */     private final Classifier this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(Classifier this$0, RealMatrix c) {
/* 283 */       this.this$0 = this$0; this.confusion = null; this.accuracy = 0.0D;
/* 284 */       this.confusion = c;
/* 285 */       this.accuracy = Classifier.calculateAccuracy(c);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(Classifier this$0, Statistics source) {
/* 292 */       this.this$0 = this$0; this.confusion = null; this.accuracy = 0.0D;
/* 293 */       this.confusion = new RealMatrix(source.confusion);
/* 294 */       this.accuracy = source.accuracy;
/*     */     }
/*     */     
/*     */     public RealMatrix getConfusionMatrix() {
/* 298 */       return this.confusion;
/*     */     } public double getAccuracy() {
/* 300 */       return this.accuracy;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 306 */       return new Statistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 313 */       if (_other instanceof Statistics) {
/* 314 */         Statistics other = (Statistics)_other;
/* 315 */         this.confusion.sum(other.confusion);
/* 316 */         this.accuracy += other.accuracy;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void substract(Result _other) {
/* 325 */       if (_other instanceof Statistics) {
/* 326 */         Statistics other = (Statistics)_other;
/* 327 */         this.confusion.substract(other.confusion);
/* 328 */         this.accuracy -= other.accuracy;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void divide(double n) {
/* 337 */       this.confusion.divide(n);
/* 338 */       this.accuracy /= n;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void square() {
/* 345 */       this.confusion.elements_square();
/* 346 */       this.accuracy *= this.accuracy;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sqrt() {
/* 353 */       this.confusion.elements_sqrt();
/* 354 */       this.accuracy = Math.sqrt(this.accuracy);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 363 */       StringBuffer sb = new StringBuffer();
/* 364 */       sb.append("[Confusion Matrix]\n");
/* 365 */       sb.append(this.confusion.toString());
/* 366 */       sb.append("[Accuracy]\n");
/* 367 */       double acc = this.accuracy;
/* 368 */       sb.append("" + acc + "\n");
/* 369 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\Classifier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
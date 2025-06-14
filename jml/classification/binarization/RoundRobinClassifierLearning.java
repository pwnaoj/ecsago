/*     */ package jml.classification.binarization;
/*     */ 
/*     */ import jml.basics.Cloner;
/*     */ import jml.basics.Result;
/*     */ import jml.classification.Aggregator;
/*     */ import jml.classification.Classifier;
/*     */ import jml.classification.ClassifierLearning;
/*     */ import jml.classification.Recognizer;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.util.BinaryShellDataSource;
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
/*     */ public class RoundRobinClassifierLearning
/*     */   extends ClassifierLearning
/*     */ {
/*  49 */   protected ClassifierLearning basic_learner = null;
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected Aggregator aggregator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   protected Result[][] basic_learner_statistics = (Result[][])null;
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
/*     */   public RoundRobinClassifierLearning(DataSource training, DataSource testing, ClassifierLearning _basic_learner, int classes_number, Aggregator _aggregator) {
/*  75 */     super(training, testing);
/*  76 */     this.basic_learner = _basic_learner;
/*  77 */     this.aggregator = _aggregator;
/*     */     
/*  79 */     this.basic_learner_statistics = new Result[classes_number][classes_number];
/*  80 */     setSets(training, testing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  87 */     if (this.basic_learner_statistics != null) {
/*  88 */       int length = this.basic_learner_statistics.length;
/*  89 */       this.basic_learner_statistics = new Result[length][length];
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
/*     */   public Result create(Classifier classifier, Result tested) {
/* 101 */     return (Result)new Statistics(this, classifier, tested, this.basic_learner_statistics);
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
/*     */   public DataSource getBinaryDataSource(DataSource source, int i, int j) {
/* 113 */     int n = this.basic_learner_statistics.length;
/* 114 */     int[] class_type = new int[n];
/* 115 */     for (int k = 0; k < n; ) { class_type[k] = 0; k++; }
/* 116 */      class_type[i] = -1;
/* 117 */     class_type[j] = 1;
/* 118 */     return (DataSource)new BinaryShellDataSource(source, class_type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Classifier training() {
/* 126 */     int n = this.basic_learner_statistics.length;
/* 127 */     int m = n - 1;
/* 128 */     Recognizer[][] binary_recognizer = new Recognizer[n][n];
/* 129 */     System.out.println("n:" + n);
/*     */ 
/*     */     
/* 132 */     for (int i = 0; i < m && this.continueFlag; i++) {
/* 133 */       for (int j = i + 1; j < n && this.continueFlag; j++) {
/* 134 */         DataSource binary_training = getBinaryDataSource(this.training_set, i, j);
/* 135 */         DataSource binary_testing = getBinaryDataSource(this.testing_set, i, j);
/* 136 */         this.basic_learner.setSets(binary_training, binary_testing);
/* 137 */         this.basic_learner.run();
/* 138 */         ClassifierLearning.Statistics stat = (ClassifierLearning.Statistics)this.basic_learner.output();
/*     */         
/* 140 */         this.basic_learner_statistics[i][j] = (Result)stat;
/* 141 */         binary_recognizer[i][j] = (stat.getClassifier()).recognizer;
/*     */       } 
/*     */     } 
/* 144 */     return new Classifier(new RoundRobinRecognizer(binary_recognizer), this.aggregator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Statistics
/*     */     extends ClassifierLearning.Statistics
/*     */     implements Cloneable
/*     */   {
/*     */     protected Result[][] basic_learner_statistics;
/*     */ 
/*     */ 
/*     */     
/*     */     private final RoundRobinClassifierLearning this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(RoundRobinClassifierLearning this$0, Classifier _classifier, Result _tested, Result[][] _basic_learner_statistics) {
/* 164 */       super(this$0, _classifier, _tested); this.this$0 = this$0; this.basic_learner_statistics = (Result[][])null;
/* 165 */       this.basic_learner_statistics = _basic_learner_statistics;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(RoundRobinClassifierLearning this$0, Statistics stat) {
/* 173 */       super(this$0, stat); this.this$0 = this$0; this.basic_learner_statistics = (Result[][])null;
/* 174 */       if (stat.basic_learner_statistics != null) {
/*     */ 
/*     */         
/* 177 */         int n = stat.basic_learner_statistics.length;
/* 178 */         int m = n - 1;
/* 179 */         this.basic_learner_statistics = new Result[n][n];
/* 180 */         for (int i = 0; i < m; i++) {
/* 181 */           for (int j = i + 1; j < n; j++) {
/* 182 */             this.basic_learner_statistics[i][j] = (Result)Cloner.clone(stat.basic_learner_statistics[i][j]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 193 */       return new Statistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 200 */       super.sum(_other);
/* 201 */       if (_other instanceof Statistics) {
/* 202 */         Statistics other = (Statistics)_other;
/* 203 */         if (this.basic_learner_statistics != null) {
/*     */ 
/*     */           
/* 206 */           int n = other.basic_learner_statistics.length;
/* 207 */           int m = n - 1;
/* 208 */           for (int i = 0; i < m; i++) {
/* 209 */             for (int j = i + 1; j < n; j++) {
/* 210 */               this.basic_learner_statistics[i][j].sum(other.basic_learner_statistics[i][j]);
/*     */             }
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
/*     */     public void substract(Result _other) {
/* 223 */       super.substract(_other);
/* 224 */       if (_other instanceof Statistics) {
/* 225 */         Statistics other = (Statistics)_other;
/* 226 */         if (this.basic_learner_statistics != null) {
/*     */ 
/*     */           
/* 229 */           int n = other.basic_learner_statistics.length;
/* 230 */           int m = n - 1;
/* 231 */           for (int i = 0; i < m; i++) {
/* 232 */             for (int j = i + 1; j < n; j++) {
/* 233 */               this.basic_learner_statistics[i][j].substract(other.basic_learner_statistics[i][j]);
/*     */             }
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
/*     */     public void divide(double n) {
/* 246 */       super.divide(n);
/* 247 */       if (this.basic_learner_statistics != null) {
/*     */ 
/*     */         
/* 250 */         int k = this.basic_learner_statistics.length;
/* 251 */         int m = k - 1;
/* 252 */         for (int i = 0; i < m; i++) {
/* 253 */           for (int j = i + 1; j < k; j++) {
/* 254 */             this.basic_learner_statistics[i][j].divide(n);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void square() {
/* 264 */       super.square();
/* 265 */       if (this.basic_learner_statistics != null) {
/*     */ 
/*     */         
/* 268 */         int n = this.basic_learner_statistics.length;
/* 269 */         int m = n - 1;
/* 270 */         for (int i = 0; i < m; i++) {
/* 271 */           for (int j = i + 1; j < n; j++) {
/* 272 */             this.basic_learner_statistics[i][j].square();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 283 */       StringBuffer sb = new StringBuffer();
/* 284 */       sb.append(super.toString());
/* 285 */       if (this.basic_learner_statistics != null) {
/*     */ 
/*     */         
/* 288 */         int n = this.basic_learner_statistics.length;
/* 289 */         int m = n - 1;
/* 290 */         for (int i = 0; i < m; i++) {
/* 291 */           for (int j = i + 1; j < n; j++) {
/* 292 */             sb.append("[" + i + "," + j + "]\n");
/* 293 */             sb.append(this.basic_learner_statistics[i][j].toString());
/*     */           } 
/*     */         } 
/*     */       } 
/* 297 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\RoundRobinClassifierLearning.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
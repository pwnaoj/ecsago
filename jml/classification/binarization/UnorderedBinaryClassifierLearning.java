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
/*     */ public class UnorderedBinaryClassifierLearning
/*     */   extends ClassifierLearning
/*     */ {
/*  48 */   protected ClassifierLearning basic_learner = null;
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected Aggregator aggregator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected Result[] basic_learner_statistics = null;
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
/*     */   public UnorderedBinaryClassifierLearning(DataSource training, DataSource testing, ClassifierLearning _basic_learner, int classes_number, Aggregator _aggregator) {
/*  74 */     super(training, testing);
/*  75 */     this.basic_learner = _basic_learner;
/*  76 */     this.aggregator = _aggregator;
/*  77 */     this.basic_learner_statistics = new Result[classes_number];
/*  78 */     setSets(training, testing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  85 */     if (this.basic_learner_statistics != null) {
/*  86 */       this.basic_learner_statistics = new Result[this.basic_learner_statistics.length];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create(Classifier classifier, Result tested) {
/*  97 */     return (Result)new Statistics(this, classifier, tested, this.basic_learner_statistics);
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
/*     */   public DataSource getBinaryDataSource(DataSource source, int i) {
/* 109 */     int n = this.basic_learner_statistics.length;
/* 110 */     int[] class_type = new int[n];
/* 111 */     for (int k = 0; k < class_type.length; k++) {
/* 112 */       class_type[k] = 1;
/*     */     }
/* 114 */     class_type[i] = -1;
/* 115 */     return (DataSource)new BinaryShellDataSource(source, class_type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Classifier training() {
/* 123 */     int n = this.basic_learner_statistics.length;
/* 124 */     if (n == 2) {
/* 125 */       this.basic_learner.setSets(this.training_set, this.testing_set);
/* 126 */       this.basic_learner.run();
/* 127 */       ClassifierLearning.Statistics stat = (ClassifierLearning.Statistics)this.basic_learner.output();
/*     */       
/* 129 */       this.basic_learner_statistics[0] = (Result)stat;
/* 130 */       return stat.getClassifier();
/*     */     } 
/* 132 */     Recognizer[] binary_recognizer = new Recognizer[n];
/* 133 */     for (int i = 0; i < n && this.continueFlag; i++) {
/* 134 */       DataSource binary_training = getBinaryDataSource(this.training_set, i);
/* 135 */       DataSource binary_testing = getBinaryDataSource(this.testing_set, i);
/* 136 */       this.basic_learner.setSets(binary_training, binary_testing);
/* 137 */       this.basic_learner.run();
/* 138 */       ClassifierLearning.Statistics stat = (ClassifierLearning.Statistics)this.basic_learner.output();
/*     */       
/* 140 */       this.basic_learner_statistics[i] = (Result)stat;
/* 141 */       binary_recognizer[i] = (stat.getClassifier()).recognizer;
/*     */     } 
/* 143 */     return new Classifier(new UnorderedBinaryRecognizer(binary_recognizer), this.aggregator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Statistics
/*     */     extends ClassifierLearning.Statistics
/*     */     implements Cloneable
/*     */   {
/*     */     protected Result[] basic_learner_statistics;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final UnorderedBinaryClassifierLearning this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(UnorderedBinaryClassifierLearning this$0, Classifier _classifier, Result _tested, Result[] _basic_learner_statistics) {
/* 165 */       super(this$0, _classifier, _tested); this.this$0 = this$0; this.basic_learner_statistics = null;
/* 166 */       this.basic_learner_statistics = _basic_learner_statistics;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(UnorderedBinaryClassifierLearning this$0, Statistics stat) {
/* 174 */       super(this$0, stat); this.this$0 = this$0; this.basic_learner_statistics = null;
/* 175 */       if (stat.basic_learner_statistics != null) {
/* 176 */         int n = stat.basic_learner_statistics.length;
/* 177 */         this.basic_learner_statistics = new Result[n];
/* 178 */         if (n == 2) n--; 
/* 179 */         for (int i = 0; i < n; i++) {
/* 180 */           this.basic_learner_statistics[i] = (Result)Cloner.clone(stat.basic_learner_statistics[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 190 */       return new Statistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 197 */       super.sum(_other);
/* 198 */       if (_other instanceof Statistics) {
/* 199 */         Statistics other = (Statistics)_other;
/* 200 */         if (this.basic_learner_statistics != null) {
/* 201 */           int n = this.basic_learner_statistics.length;
/* 202 */           if (n == 2) n--; 
/* 203 */           for (int i = 0; i < n; i++) {
/* 204 */             this.basic_learner_statistics[i].sum(other.basic_learner_statistics[i]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void substract(Result _other) {
/* 215 */       super.substract(_other);
/* 216 */       if (_other instanceof Statistics) {
/* 217 */         Statistics other = (Statistics)_other;
/* 218 */         if (this.basic_learner_statistics != null) {
/* 219 */           int n = this.basic_learner_statistics.length;
/* 220 */           if (n == 2) n--; 
/* 221 */           for (int i = 0; i < n; i++) {
/* 222 */             this.basic_learner_statistics[i].substract(other.basic_learner_statistics[i]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void divide(double n) {
/* 233 */       super.divide(n);
/* 234 */       if (this.basic_learner_statistics != null) {
/* 235 */         int m = this.basic_learner_statistics.length;
/* 236 */         if (m == 2) m--; 
/* 237 */         for (int i = 0; i < m; i++) {
/* 238 */           this.basic_learner_statistics[i].divide(n);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void square() {
/* 247 */       super.square();
/* 248 */       if (this.basic_learner_statistics != null) {
/* 249 */         int n = this.basic_learner_statistics.length;
/* 250 */         if (n == 2) n--; 
/* 251 */         for (int i = 0; i < n; i++) {
/* 252 */           this.basic_learner_statistics[i].square();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 262 */       StringBuffer sb = new StringBuffer();
/* 263 */       sb.append(super.toString());
/* 264 */       if (this.basic_learner_statistics != null) {
/* 265 */         int n = this.basic_learner_statistics.length;
/* 266 */         if (n == 2) n--; 
/* 267 */         for (int i = 0; i < n; i++) {
/* 268 */           sb.append("[" + i + "]\n");
/* 269 */           sb.append(this.basic_learner_statistics[i].toString());
/*     */         } 
/*     */       } 
/* 272 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\UnorderedBinaryClassifierLearning.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
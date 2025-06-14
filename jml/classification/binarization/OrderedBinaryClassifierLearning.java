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
/*     */ public class OrderedBinaryClassifierLearning
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
/*     */   public OrderedBinaryClassifierLearning(DataSource training, DataSource testing, ClassifierLearning _basic_learner, int classes_number, Aggregator _aggregator) {
/*  74 */     super(training, testing);
/*  75 */     this.basic_learner = _basic_learner;
/*  76 */     this.aggregator = _aggregator;
/*  77 */     this.basic_learner_statistics = new Result[classes_number - 1];
/*  78 */     setSets(training, testing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create(Classifier classifier, Result tested) {
/*  88 */     return (Result)new Statistics(this, classifier, tested, this.basic_learner_statistics);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource getBinaryDataSource(DataSource source, int i) {
/* 108 */     int n = this.basic_learner_statistics.length + 1;
/*     */     
/* 110 */     int[] class_type = new int[n]; int k;
/* 111 */     for (k = 0; k <= i; k++) {
/* 112 */       class_type[k] = 1;
/*     */     }
/* 114 */     for (k = i + 1; k < class_type.length; k++) {
/* 115 */       class_type[k] = -1;
/*     */     }
/* 117 */     return (DataSource)new BinaryShellDataSource(source, class_type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Classifier training() {
/* 132 */     int n = this.basic_learner_statistics.length;
/* 133 */     Recognizer[] binary_recognizer = new Recognizer[n];
/* 134 */     for (int i = 0; i < n && this.continueFlag; i++) {
/* 135 */       DataSource binary_training = getBinaryDataSource(this.training_set, i);
/* 136 */       DataSource binary_testing = getBinaryDataSource(this.testing_set, i);
/* 137 */       this.basic_learner.setSets(binary_training, binary_testing);
/* 138 */       this.basic_learner.run();
/* 139 */       ClassifierLearning.Statistics stat = (ClassifierLearning.Statistics)this.basic_learner.output();
/*     */       
/* 141 */       this.basic_learner_statistics[i] = (Result)stat;
/* 142 */       binary_recognizer[i] = (stat.getClassifier()).recognizer;
/*     */     } 
/* 144 */     return new Classifier(new OrderedBinaryRecognizer(binary_recognizer), this.aggregator);
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
/*     */     private final OrderedBinaryClassifierLearning this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(OrderedBinaryClassifierLearning this$0, Classifier _classifier, Result _tested, Result[] _basic_learner_statistics) {
/* 165 */       super(this$0, _classifier, _tested); this.this$0 = this$0; this.basic_learner_statistics = null;
/* 166 */       this.basic_learner_statistics = _basic_learner_statistics;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(OrderedBinaryClassifierLearning this$0, Statistics stat) {
/* 174 */       super(this$0, stat); this.this$0 = this$0; this.basic_learner_statistics = null;
/* 175 */       if (stat.basic_learner_statistics != null) {
/* 176 */         int n = stat.basic_learner_statistics.length;
/* 177 */         this.basic_learner_statistics = new Result[n];
/* 178 */         for (int i = 0; i < n; i++) {
/* 179 */           this.basic_learner_statistics[i] = (Result)Cloner.clone(stat.basic_learner_statistics[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 189 */       return new Statistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 196 */       super.sum(_other);
/* 197 */       if (_other instanceof Statistics) {
/* 198 */         Statistics other = (Statistics)_other;
/* 199 */         if (this.basic_learner_statistics != null) {
/* 200 */           int n = this.basic_learner_statistics.length;
/* 201 */           for (int i = 0; i < n; i++) {
/* 202 */             this.basic_learner_statistics[i].sum(other.basic_learner_statistics[i]);
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
/* 213 */       super.substract(_other);
/* 214 */       if (_other instanceof Statistics) {
/* 215 */         Statistics other = (Statistics)_other;
/* 216 */         if (this.basic_learner_statistics != null) {
/* 217 */           int n = this.basic_learner_statistics.length;
/* 218 */           for (int i = 0; i < n; i++) {
/* 219 */             this.basic_learner_statistics[i].substract(other.basic_learner_statistics[i]);
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
/* 230 */       super.divide(n);
/* 231 */       if (this.basic_learner_statistics != null) {
/* 232 */         int m = this.basic_learner_statistics.length;
/* 233 */         for (int i = 0; i < m; i++) {
/* 234 */           this.basic_learner_statistics[i].divide(n);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void square() {
/* 243 */       super.square();
/* 244 */       if (this.basic_learner_statistics != null) {
/* 245 */         int n = this.basic_learner_statistics.length;
/* 246 */         for (int i = 0; i < n; i++) {
/* 247 */           this.basic_learner_statistics[i].square();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 257 */       StringBuffer sb = new StringBuffer();
/* 258 */       sb.append(super.toString());
/* 259 */       if (this.basic_learner_statistics != null) {
/* 260 */         int n = this.basic_learner_statistics.length;
/* 261 */         for (int i = 0; i < n; i++) {
/* 262 */           sb.append("[" + i + "]\n");
/* 263 */           sb.append(this.basic_learner_statistics[i].toString());
/*     */         } 
/*     */       } 
/* 266 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\OrderedBinaryClassifierLearning.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
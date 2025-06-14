/*     */ package jml.classification;
/*     */ 
/*     */ import jml.basics.Algorithm;
/*     */ import jml.basics.Cloner;
/*     */ import jml.basics.Result;
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
/*     */ public abstract class ClassifierLearning
/*     */   extends Algorithm
/*     */ {
/*  41 */   public Classifier classifier = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected DataSource training_set = null;
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected DataSource testing_set = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassifierLearning(DataSource training, DataSource testing) {
/*  59 */     this.training_set = training;
/*  60 */     this.testing_set = testing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSets(DataSource training, DataSource testing) {
/*  69 */     this.training_set = training;
/*  70 */     this.testing_set = testing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Classifier training();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create(Classifier classifier, Result tested) {
/*  86 */     return new Statistics(this, classifier, tested);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  95 */     if (this.continueFlag) {
/*  96 */       this.classifier = training();
/*  97 */       if (this.continueFlag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object output() {
/* 103 */     Result result = null;
/* 104 */     if (this.classifier != null) {
/* 105 */       Result tested = null;
/* 106 */       if (this.testing_set != null) {
/* 107 */         tested = this.classifier.statistics(this.testing_set);
/*     */       }
/* 109 */       if (this.continueFlag) {
/* 110 */         result = create(this.classifier, tested);
/*     */       }
/*     */     } 
/* 113 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Statistics
/*     */     extends Result
/*     */     implements Cloneable
/*     */   {
/*     */     protected Classifier classifier;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Result tested;
/*     */ 
/*     */ 
/*     */     
/*     */     private final ClassifierLearning this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(ClassifierLearning this$0, Classifier _classifier, Result _tested) {
/* 136 */       this.this$0 = this$0; this.classifier = null; this.tested = null;
/* 137 */       this.classifier = _classifier;
/* 138 */       this.tested = _tested;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Statistics(ClassifierLearning this$0, Statistics source) {
/* 145 */       this.this$0 = this$0; this.classifier = null; this.tested = null;
/* 146 */       this.classifier = source.classifier;
/* 147 */       this.tested = (Result)Cloner.clone(source.tested);
/*     */     }
/*     */     public Result getTest() {
/* 150 */       return this.tested;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object clone() {
/* 156 */       return new Statistics(this.this$0, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void sum(Result _other) {
/* 163 */       if (_other instanceof Statistics) {
/* 164 */         Statistics other = (Statistics)_other;
/* 165 */         this.tested.sum(other.tested);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void substract(Result _other) {
/* 174 */       if (_other instanceof Statistics) {
/* 175 */         Statistics other = (Statistics)_other;
/* 176 */         this.tested.substract(other.tested);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void divide(double n) {
/* 184 */       this.tested.divide(n);
/*     */     }
/*     */ 
/*     */     
/*     */     public void square() {
/* 189 */       this.tested.square();
/*     */     }
/*     */ 
/*     */     
/*     */     public void sqrt() {
/* 194 */       this.tested.sqrt();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 201 */       String text = "[Classifier]" + this.classifier.toString();
/* 202 */       if (this.tested != null) {
/* 203 */         text = text + "[Testing]\n" + this.tested.toString();
/*     */       }
/* 205 */       return text;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Classifier getClassifier() {
/* 212 */       return this.classifier;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\ClassifierLearning.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
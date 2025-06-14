/*     */ package jml.classification.binarization;
/*     */ 
/*     */ import jml.classification.Aggregator;
/*     */ import jml.classification.Prediction;
/*     */ import jml.classification.Recognizer;
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
/*     */ public class BinaryRecognizerShell
/*     */   extends BinaryRecognizer
/*     */ {
/*  42 */   protected Recognizer recognizer = null;
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected boolean[] is_positive = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Aggregator aggregator = null;
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
/*     */   public BinaryRecognizerShell(Recognizer _recognizer, int[] positive, Aggregator _aggregator) {
/*  63 */     this.recognizer = _recognizer;
/*  64 */     this.is_positive = new boolean[this.recognizer.classesNumber()];
/*     */     int i;
/*  66 */     for (i = 0; i < this.is_positive.length; i++) {
/*  67 */       this.is_positive[i] = false;
/*     */     }
/*  69 */     for (i = 0; i < positive.length; i++) {
/*  70 */       this.is_positive[positive[i]] = true;
/*     */     }
/*     */     
/*  73 */     this.aggregator = _aggregator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecognizer(Recognizer _recognizer) {
/*  81 */     this.recognizer = _recognizer;
/*  82 */     if (this.recognizer != null) {
/*  83 */       boolean[] _is_positive = new boolean[this.recognizer.classesNumber()]; int i;
/*  84 */       for (i = 0; i < _is_positive.length; i++) {
/*  85 */         _is_positive[i] = false;
/*     */       }
/*  87 */       for (i = 0; i < this.is_positive.length; i++) {
/*  88 */         _is_positive[i] = this.is_positive[i];
/*     */       }
/*  90 */       this.is_positive = _is_positive;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] confidence(Object data) {
/* 101 */     double[] conf = new double[2];
/* 102 */     double[] val = this.recognizer.confidence(data);
/* 103 */     Prediction p = this.aggregator.apply(data, val);
/*     */     
/* 105 */     conf[p.getLabel()] = p.getConfidence();
/* 106 */     conf[1 - p.getLabel()] = 1.0D - p.getConfidence();
/*     */     
/* 108 */     return conf;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\BinaryRecognizerShell.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
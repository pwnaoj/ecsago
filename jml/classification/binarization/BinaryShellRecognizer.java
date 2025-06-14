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
/*     */ public class BinaryShellRecognizer
/*     */   extends BinaryRecognizer
/*     */ {
/*  42 */   protected Recognizer recognizer = null;
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected int[] class_type = null;
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
/*     */ 
/*     */   
/*     */   public BinaryShellRecognizer(Recognizer _recognizer, int[] _class_type, Aggregator _aggregator) {
/*  65 */     this.recognizer = _recognizer;
/*  66 */     this.class_type = _class_type;
/*  67 */     this.aggregator = _aggregator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecognizer(Recognizer _recognizer) {
/*  75 */     this.recognizer = _recognizer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] confidence(Object data) {
/*  85 */     double[] conf = new double[2];
/*  86 */     double[] val = this.recognizer.confidence(data);
/*  87 */     for (int i = 0; i < this.class_type.length; i++) {
/*  88 */       if (this.class_type[i] == 0) val[i] = 0.0D; 
/*     */     } 
/*  90 */     Prediction p = this.aggregator.apply(data, val);
/*  91 */     if (this.class_type[p.getLabel()] < 0) {
/*  92 */       conf[0] = p.getConfidence();
/*  93 */       conf[1] = 1.0D - p.getConfidence();
/*     */     }
/*  95 */     else if (this.class_type[p.getLabel()] > 0) {
/*  96 */       conf[1] = p.getConfidence();
/*  97 */       conf[0] = 1.0D - p.getConfidence();
/*     */     } else {
/*  99 */       conf[1] = 0.5D;
/* 100 */       conf[0] = 0.5D;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return conf;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\BinaryShellRecognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
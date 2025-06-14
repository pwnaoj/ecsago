/*    */ package jml.classification.binarization;
/*    */ 
/*    */ import jml.classification.Recognizer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrderedBinaryRecognizer
/*    */   extends Recognizer
/*    */ {
/*    */   protected Recognizer[] binary_recognizers;
/*    */   
/*    */   public OrderedBinaryRecognizer(Recognizer[] _binary_recognizers) {
/* 58 */     this.binary_recognizers = _binary_recognizers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int classesNumber() {
/* 65 */     return this.binary_recognizers.length + 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] confidence(Object data) {
/* 74 */     int n = classesNumber();
/* 75 */     int m = n - 1;
/* 76 */     double[] conf = new double[n];
/* 77 */     for (int i = 0; i < m; i++) {
/* 78 */       conf[i] = this.binary_recognizers[i].confidence(data)[1];
/*    */     }
/* 80 */     conf[m] = 1.0D;
/* 81 */     return conf;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\OrderedBinaryRecognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
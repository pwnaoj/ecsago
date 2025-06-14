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
/*    */ public class UnorderedBinaryRecognizer
/*    */   extends Recognizer
/*    */ {
/*    */   protected Recognizer[] binary_recognizers;
/*    */   
/*    */   public UnorderedBinaryRecognizer(Recognizer[] _binary_recognizers) {
/* 57 */     this.binary_recognizers = _binary_recognizers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int classesNumber() {
/* 64 */     return this.binary_recognizers.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] confidence(Object data) {
/* 73 */     int n = classesNumber();
/* 74 */     double[] conf = new double[n];
/* 75 */     for (int i = 0; i < n; i++) {
/* 76 */       conf[i] = this.binary_recognizers[i].confidence(data)[0];
/*    */     }
/* 78 */     return conf;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\UnorderedBinaryRecognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ public class RoundRobinRecognizer
/*    */   extends Recognizer
/*    */ {
/*    */   protected Recognizer[][] binary_recognizers;
/*    */   
/*    */   public RoundRobinRecognizer(Recognizer[][] _binary_recognizers) {
/* 53 */     this.binary_recognizers = _binary_recognizers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int classesNumber() {
/* 61 */     return this.binary_recognizers.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] confidence(Object data) {
/* 70 */     int n = classesNumber();
/* 71 */     int m = n - 1;
/*    */     
/* 73 */     double[] conf = new double[n]; int i;
/* 74 */     for (i = 0; i < n; ) { conf[i] = 0.0D; i++; }
/* 75 */      for (i = 0; i < m; i++) {
/* 76 */       for (int k = i + 1; k < n; k++) {
/* 77 */         double[] temp_conf = this.binary_recognizers[i][k].confidence(data);
/* 78 */         conf[i] = conf[i] + temp_conf[0];
/* 79 */         conf[k] = conf[k] + temp_conf[1];
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 84 */     double total = 0.0D; int j;
/* 85 */     for (j = 0; j < conf.length; ) { total += conf[j]; j++; }
/* 86 */      if (total != 0.0D) for (j = 0; j < conf.length; ) { conf[j] = conf[j] / total; j++; }
/* 87 */         return conf;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\binarization\RoundRobinRecognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.data.generators;
/*    */ 
/*    */ import jml.algebra.Components;
/*    */ import jml.algebra.FullComponents;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
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
/*    */ public class FullDataGenerator
/*    */   extends DataGenerator
/*    */ {
/*    */   public FullDataGenerator(int _dimension, int _label_pos) {
/* 22 */     super(_dimension, _label_pos);
/*    */   }
/*    */   public Data get(double[] d) {
/* 25 */     double[] newd = d;
/* 26 */     int label = 0;
/* 27 */     int n = d.length;
/* 28 */     if (this.label_pos >= 0) {
/* 29 */       n--;
/* 30 */       label = (int)d[Math.min(this.label_pos, n)];
/* 31 */       newd = new double[n];
/* 32 */       for (int i = 0; i < n; i++) {
/* 33 */         if (i < this.label_pos) {
/* 34 */           newd[i] = d[i];
/*    */         } else {
/* 36 */           newd[i] = d[i + 1];
/*    */         } 
/*    */       } 
/*    */     } 
/* 40 */     return new Data(new RealVector((Components)new FullComponents(newd)), label);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\generators\FullDataGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
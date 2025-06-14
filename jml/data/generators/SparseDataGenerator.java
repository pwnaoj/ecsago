/*    */ package jml.data.generators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.algebra.Components;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.algebra.SparseComponents;
/*    */ import jml.data.Data;
/*    */ 
/*    */ public class SparseDataGenerator
/*    */   extends DataGenerator {
/*    */   public SparseDataGenerator(int _dimension, int _label_pos) {
/* 12 */     super(_dimension, _label_pos);
/* 13 */     if (this.label_pos > 0) this.label_pos = 1; 
/*    */   }
/*    */   
/*    */   public Data get(double[] d) {
/* 17 */     int start = 0;
/* 18 */     int label = 0;
/* 19 */     int n = d.length;
/* 20 */     if (this.label_pos >= 0) {
/* 21 */       n--;
/* 22 */       if (this.label_pos == 1) {
/* 23 */         label = (int)d[n];
/*    */       } else {
/* 25 */         label = (int)d[0];
/* 26 */         start = 1;
/*    */       } 
/*    */     } 
/* 29 */     SparseComponents comp = new SparseComponents(this.dimension, new Vector());
/* 30 */     for (int i = start; i < n; i += 2) {
/* 31 */       comp.set((int)d[i], d[i + 1]);
/*    */     }
/* 33 */     return new Data(new RealVector((Components)comp), label);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\generators\SparseDataGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
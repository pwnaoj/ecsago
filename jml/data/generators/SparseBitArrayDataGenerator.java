/*    */ package jml.data.generators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.data.Data;
/*    */ import jml.structures.SparceBitArray;
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
/*    */ public class SparseBitArrayDataGenerator
/*    */   extends DataGenerator
/*    */ {
/*    */   public SparseBitArrayDataGenerator(int _dimension, int _label_pos) {
/* 20 */     super(_dimension, _label_pos);
/* 21 */     if (this.label_pos > 0) this.label_pos = 1; 
/*    */   }
/*    */   
/*    */   public SparseBitArrayDataGenerator() {
/* 25 */     super(0, -1);
/*    */   }
/*    */   
/*    */   public Data get(double[] d) {
/* 29 */     int start = 0;
/* 30 */     int label = 0;
/* 31 */     int n = d.length;
/* 32 */     if (this.label_pos >= 0) {
/* 33 */       n--;
/* 34 */       if (this.label_pos == 1) {
/* 35 */         label = (int)d[n];
/*    */       } else {
/* 37 */         label = (int)d[0];
/* 38 */         start = 1;
/*    */       } 
/*    */     } 
/* 41 */     SparceBitArray comp = new SparceBitArray(new Vector(), this.dimension);
/* 42 */     for (int i = start; i < n; i++) {
/* 43 */       comp.set((int)d[i], true);
/*    */     }
/* 45 */     return new Data(comp, label);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\generators\SparseBitArrayDataGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
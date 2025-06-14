/*    */ package jml.data.generators;
/*    */ 
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
/*    */ public abstract class DataGenerator
/*    */ {
/*    */   protected int dimension;
/*    */   protected int label_pos;
/*    */   
/*    */   public DataGenerator(int _dimension, int _label_pos) {
/* 20 */     this.dimension = _dimension;
/* 21 */     this.label_pos = _label_pos;
/*    */   }
/*    */   
/*    */   public void set(DataGenerator g) {
/* 25 */     this.dimension = g.dimension;
/* 26 */     this.label_pos = g.label_pos;
/*    */   }
/*    */   
/*    */   public abstract Data get(double[] paramArrayOfdouble);
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\generators\DataGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
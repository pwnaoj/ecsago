/*    */ package jml.data.transformation;
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
/*    */ public class CombinedDataTransformation
/*    */   extends DataTransformation
/*    */ {
/*    */   DataTransformation first;
/*    */   DataTransformation second;
/*    */   
/*    */   public CombinedDataTransformation(DataTransformation _first, DataTransformation _second) {
/* 51 */     this.first = _first;
/* 52 */     this.second = _second;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data apply(Data data) {
/* 61 */     return this.second.apply(this.first.apply(data));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data inverse(Data data) {
/* 71 */     return this.first.inverse(this.second.inverse(data));
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\CombinedDataTransformation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
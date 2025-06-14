/*    */ package unc.evolution.encoding.sparse.binary.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
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
/*    */ public class SparseBitMutation
/*    */   extends ArityOne
/*    */ {
/* 22 */   protected SparseBitAdd add = null;
/* 23 */   protected SparseBitDel del = null;
/*    */   
/*    */   public SparseBitMutation(Environment _environment, int max, int min) {
/* 26 */     super(_environment);
/* 27 */     this.add = new SparseBitAdd(_environment, max);
/* 28 */     this.del = new SparseBitDel(_environment, min);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 38 */     this.del.apply(obj);
/* 39 */     return this.add.apply(obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\operators\SparseBitMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
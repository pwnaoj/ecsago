/*    */ package unc.evolution.encoding.sparse.binary.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import jml.structures.SparceBitArray;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SparseBitAdd
/*    */   extends ArityOne
/*    */ {
/* 17 */   int MAX_SIZE = 15;
/*    */   
/*    */   public SparseBitAdd(Environment _environment, int _max_size) {
/* 20 */     super(_environment);
/* 21 */     this.MAX_SIZE = _max_size;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 30 */     SparceBitArray A = (SparceBitArray)obj;
/* 31 */     int size = A.size();
/* 32 */     int index = 0;
/* 33 */     if (A.get().size() < this.MAX_SIZE)
/*    */       while (true) {
/* 35 */         index = (int)(size * Math.random());
/*    */         
/* 37 */         if (!A.get(index)) {
/* 38 */           A.flip(index); break;
/*    */         } 
/* 40 */       }   return new Integer(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\operators\SparseBitAdd.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
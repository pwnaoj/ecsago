/*    */ package unc.evolution.encoding.sparse.binary.operators;
/*    */ 
/*    */ import java.util.Vector;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SparseBitDel
/*    */   extends ArityOne
/*    */ {
/* 21 */   int MIN_SIZE = 5;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SparseBitDel(Environment _environment, int _min_size) {
/* 28 */     super(_environment);
/* 29 */     this.MIN_SIZE = _min_size;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 38 */     SparceBitArray genome = (SparceBitArray)obj;
/* 39 */     Vector v = genome.get();
/* 40 */     int size = v.size();
/* 41 */     if (size > this.MIN_SIZE) {
/* 42 */       int index = (int)(size * Math.random());
/* 43 */       Object x = v.get(index);
/* 44 */       v.remove(index);
/* 45 */       return x;
/*    */     } 
/* 47 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\operators\SparseBitDel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
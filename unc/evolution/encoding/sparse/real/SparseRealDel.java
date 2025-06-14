/*    */ package unc.evolution.encoding.sparse.real;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.algebra.Components;
/*    */ import jml.algebra.RealVector;
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
/*    */ public class SparseRealDel
/*    */   extends ArityOne
/*    */ {
/* 23 */   int MIN_SIZE = 5;
/*    */   
/*    */   public SparseRealDel(Environment _environment, int _min_size) {
/* 26 */     super(_environment);
/* 27 */     this.MIN_SIZE = _min_size;
/*    */   }
/*    */   
/*    */   public Object apply(Object obj) {
/* 31 */     RealVector rx = (RealVector)obj;
/* 32 */     Components cx = rx.get();
/* 33 */     Vector v = cx.sparce_values();
/* 34 */     int size = v.size();
/* 35 */     if (size > this.MIN_SIZE) {
/* 36 */       int index = (int)(size * Math.random());
/* 37 */       Object x = v.get(index);
/* 38 */       v.remove(index);
/* 39 */       return x;
/*    */     } 
/* 41 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\real\SparseRealDel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.evolution.operators;
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
/*    */ public class SparseRealDel
/*    */   extends ArityOne
/*    */ {
/* 19 */   int MIN_SIZE = 5;
/*    */   
/*    */   public SparseRealDel(Environment _env, int _min_size) {
/* 22 */     super(_env);
/* 23 */     this.MIN_SIZE = _min_size;
/*    */   }
/*    */   
/*    */   public Object apply(Object obj) {
/* 27 */     RealVector rx = (RealVector)obj;
/* 28 */     Components cx = rx.get();
/* 29 */     Vector v = cx.sparce_values();
/* 30 */     int size = v.size();
/* 31 */     if (size > this.MIN_SIZE) {
/* 32 */       int index = (int)(size * Math.random());
/* 33 */       Object x = v.get(index);
/* 34 */       v.remove(index);
/* 35 */       return x;
/*    */     } 
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\SparseRealDel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
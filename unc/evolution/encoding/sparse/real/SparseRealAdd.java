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
/*    */ public class SparseRealAdd
/*    */   extends ArityOne
/*    */ {
/* 21 */   int MAX_SIZE = 15;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SparseRealAdd(Environment _environment, int _max_size) {
/* 27 */     super(_environment);
/* 28 */     this.MAX_SIZE = _max_size;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 37 */     RealVector rx = (RealVector)obj;
/* 38 */     Components cx = rx.get();
/* 39 */     Vector sx = cx.sparce_values();
/* 40 */     int index = 0;
/* 41 */     double value = Math.random();
/*    */     
/* 43 */     if (sx.size() < this.MAX_SIZE)
/*    */       while (true) {
/* 45 */         index = (int)(rx.dimension() * Math.random());
/* 46 */         if (cx.get(index) == 0.0D) {
/* 47 */           cx.set(index, value); break;
/*    */         } 
/* 49 */       }   return new Integer(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\real\SparseRealAdd.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
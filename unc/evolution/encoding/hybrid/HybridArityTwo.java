/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import unc.Cluster;
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
/*    */ public class HybridArityTwo
/*    */   extends ArityTwo
/*    */ {
/*    */   public ArityTwo oper;
/*    */   
/*    */   public HybridArityTwo(Environment _environment, ArityTwo _oper) {
/* 27 */     super(_environment, _oper.getSelection());
/* 28 */     this.oper = _oper;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj1, Object obj2) {
/* 37 */     Cluster one = (Cluster)obj1;
/* 38 */     Cluster two = (Cluster)obj2;
/* 39 */     return this.oper.apply(one.getCenter(), two.getCenter());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridArityTwo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
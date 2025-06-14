/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import unc.Cluster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HybridArityOne
/*    */   extends ArityOne
/*    */ {
/*    */   ArityOne oper;
/*    */   
/*    */   public HybridArityOne(Environment _environment, ArityOne _oper) {
/* 20 */     super(_environment);
/* 21 */     this.oper = _oper;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 30 */     Cluster genome = (Cluster)obj;
/* 31 */     return this.oper.apply(genome.getCenter());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridArityOne.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
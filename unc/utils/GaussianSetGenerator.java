/*    */ package unc.utils;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ import unc.sets.SqrGaussianSet;
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
/*    */ public class GaussianSetGenerator
/*    */   extends SetGenerator
/*    */ {
/*    */   public Set get(double[] d) {
/* 29 */     return (Set)new SqrGaussianSet("cluster", d[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int parameters() {
/* 38 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\GaussianSetGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
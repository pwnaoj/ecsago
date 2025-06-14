/*    */ package unc.restriction;
/*    */ 
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
/*    */ public class FitnessRestriction
/*    */   extends Restriction
/*    */ {
/*    */   public boolean satisfy(Cluster c1, Cluster c2) {
/* 18 */     return (c1.getFitness() > c2.getFitness());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\restriction\FitnessRestriction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
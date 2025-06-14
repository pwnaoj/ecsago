/*    */ package unc.extraction;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import unc.Cluster;
/*    */ import unc.Prototype;
/*    */ import unc.restriction.SameNiche;
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
/*    */ public class NicheExtraction
/*    */   extends Extraction
/*    */ {
/* 21 */   protected SameNiche restriction = null;
/*    */   
/*    */   public NicheExtraction(SameNiche _restriction) {
/* 24 */     this.restriction = _restriction;
/*    */   }
/*    */   
/*    */   public Prototype apply(Prototype pop) {
/* 28 */     Vector finalClusters = new Vector();
/* 29 */     Enumeration iter = pop.elements();
/* 30 */     while (iter.hasMoreElements()) {
/* 31 */       Cluster ind = iter.nextElement();
/* 32 */       boolean addInd = true;
/* 33 */       int j = 0;
/* 34 */       while (addInd == true && j < finalClusters.size()) {
/* 35 */         Cluster ind2 = finalClusters.get(j);
/* 36 */         addInd = !this.restriction.satisfy(ind2, ind);
/* 37 */         j++;
/*    */       } 
/* 39 */       if (addInd) {
/* 40 */         finalClusters.add(ind);
/*    */       }
/*    */     } 
/* 43 */     return new Prototype(finalClusters);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\extraction\NicheExtraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
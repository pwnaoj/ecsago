/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.basics.Cloner;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.fuzzy.Set;
/*    */ import unc.Cluster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HybridGenotype
/*    */   extends Genotype
/*    */ {
/*    */   protected Set set;
/*    */   protected Genotype center;
/*    */   
/*    */   public HybridGenotype(Set _set, Genotype _center) {
/* 22 */     this.set = _set;
/* 23 */     this.center = _center;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object newInstance() {
/* 33 */     Object obj = this.center.newInstance();
/* 34 */     System.out.println(obj.toString());
/* 35 */     return new Cluster(obj, (Set)Cloner.clone(this.set));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size(Object genome) {
/* 46 */     return this.center.size(((Cluster)genome).getCenter());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
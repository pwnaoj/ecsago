/*    */ package unc.evolution.encoding.hybrid;
/*    */ 
/*    */ import jml.basics.Cloner;
/*    */ import jml.evolution.Phenotype;
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
/*    */ public class HybridPhenotype
/*    */   extends Phenotype
/*    */ {
/* 18 */   protected Phenotype phen = null;
/*    */   
/*    */   public HybridPhenotype(Set _default_set) {
/* 21 */     this.default_set = _default_set;
/*    */   }
/*    */   protected Set default_set;
/*    */   public HybridPhenotype(Phenotype _phen, Set _default_set) {
/* 25 */     this.phen = _phen;
/* 26 */     this.default_set = _default_set;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object get(Object genome) {
/* 35 */     Cluster g = (Cluster)genome;
/* 36 */     if (this.phen != null) {
/* 37 */       return new Cluster(this.phen.get(g.getCenter()), g.getSet(), g.getFitness());
/*    */     }
/* 39 */     return genome;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object set(Object thing) {
/* 49 */     if (thing instanceof Cluster) {
/* 50 */       Cluster c = (Cluster)thing;
/* 51 */       if (this.phen != null) {
/* 52 */         return new Cluster(this.phen.set(c.getCenter()), (Set)Cloner.clone(c.getSet()), c.getFitness());
/*    */       }
/*    */       
/* 55 */       return thing;
/*    */     } 
/*    */     
/* 58 */     if (this.phen != null) {
/* 59 */       return new Cluster(this.phen.set(thing), (Set)Cloner.clone(this.default_set));
/*    */     }
/* 61 */     return new Cluster(thing, (Set)Cloner.clone(this.default_set));
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\hybrid\HybridPhenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
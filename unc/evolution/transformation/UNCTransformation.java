/*    */ package unc.evolution.transformation;
/*    */ 
/*    */ import jml.basics.Result;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Transformation;
/*    */ import unc.Cluster;
/*    */ import unc.refinement.ClusterTunning;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UNCTransformation
/*    */   extends Transformation
/*    */ {
/*    */   public ClusterTunning tunning;
/*    */   public Transformation embedded;
/*    */   
/*    */   public UNCTransformation(Transformation _embedded, ClusterTunning _tunning) {
/* 22 */     this.tunning = _tunning;
/* 23 */     this.embedded = _embedded;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() {
/* 30 */     this.embedded.init();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Population apply(Population population) {
/* 41 */     population = this.embedded.apply(population);
/* 42 */     Environment env = population.getEnvironment();
/* 43 */     if (this.tunning != null) {
/* 44 */       for (int i = 0; i < population.size(); i++) {
/* 45 */         Individual ind = population.get(i);
/* 46 */         Cluster c = (Cluster)ind.getThing(env);
/* 47 */         this.tunning.tune(c);
/* 48 */         Object obj = env.getPhenotype().set(c);
/* 49 */         ind = new Individual(obj, c, ind.getFitness());
/* 50 */         ind.evalFitness(env);
/* 51 */         population.individuals.set(i, ind);
/*    */       } 
/*    */     }
/* 54 */     return population;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Result statistics(Population population) {
/* 63 */     return this.embedded.statistics(population);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\transformation\UNCTransformation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
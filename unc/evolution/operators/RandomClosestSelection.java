/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Selection;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ import jml.util.quasimetric.QuasiMetric;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomClosestSelection
/*    */   extends Selection
/*    */ {
/*    */   public QuasiMetric metric;
/* 39 */   int sample_size = 3;
/*    */   
/*    */   public RandomClosestSelection(Environment env, int _sample_size, QuasiMetric _metric) {
/* 42 */     super(env, 1);
/* 43 */     this.metric = _metric;
/* 44 */     this.sample_size = _sample_size;
/*    */   }
/*    */   
/*    */   public Vector choose(Population population, int indMate1) {
/* 48 */     Cluster mate1 = (Cluster)population.get(indMate1).getThing();
/*    */     
/* 50 */     double MaxFitness = 0.0D;
/* 51 */     double minDist = 1.0E8D;
/* 52 */     int bestmate = 0;
/* 53 */     Vector sel = null;
/* 54 */     if (population != null) {
/* 55 */       int popSize = population.size();
/* 56 */       UniformNumberGenerator g = new UniformNumberGenerator((popSize - 1));
/* 57 */       sel = new Vector();
/* 58 */       int mate = g.newInt();
/* 59 */       if (mate >= indMate1) mate++; 
/* 60 */       Cluster c = (Cluster)population.get(mate).getThing();
/* 61 */       double distance = this.metric.distance(mate1.getCenter(), c.getCenter());
/*    */       
/* 63 */       for (int i = 1; i < this.sample_size; i++) {
/* 64 */         int tmate = g.newInt();
/* 65 */         if (tmate >= indMate1) tmate++; 
/* 66 */         c = (Cluster)population.get(tmate).getThing();
/* 67 */         double d = this.metric.distance(mate1.getCenter(), c.getCenter());
/* 68 */         if (d < distance) {
/* 69 */           mate = tmate;
/* 70 */           distance = d;
/*    */         } 
/*    */       } 
/* 73 */       sel.add(population.get(indMate1));
/* 74 */       sel.add(population.get(mate));
/*    */     } 
/* 76 */     return sel;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\RandomClosestSelection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.evolution.algorithms.haea;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.util.IndividualMetric;
/*    */ import jml.util.quasimetric.QuasiMetric;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DCHaea
/*    */   extends Selection
/*    */ {
/* 44 */   protected QuasiMetric metric = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public DCHaea(Environment _environment, QuasiMetric _metric) {
/* 49 */     super(_environment, 1);
/* 50 */     this.metric = IndividualMetric.generate(_environment, _metric);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector choose(Population population, int x) {
/* 59 */     Vector v = new Vector();
/* 60 */     if (x >= 0 && x < population.size()) {
/* 61 */       v.add(population.get(x));
/*    */     } else {
/* 63 */       int m = population.size();
/*    */       
/* 65 */       Individual p = population.get(0);
/*    */       
/* 67 */       Individual c = population.get(1);
/* 68 */       double d = this.metric.distance(p, c);
/* 69 */       for (int i = 2; i < m; i++) {
/* 70 */         double d2 = this.metric.distance(p, population.get(i));
/* 71 */         if (d2 < d) {
/* 72 */           d = d2;
/* 73 */           c = population.get(i);
/*    */         } 
/*    */       } 
/*    */       
/* 77 */       if (can_replace(c, p)) { v.add(c); }
/* 78 */       else { v.add(p); }
/*    */     
/* 80 */     }  return v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean can_replace(Individual child, Individual parent) {
/* 92 */     return (child.evalFitness(this.environment) > parent.evalFitness(this.environment));
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\haea\DCHaea.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
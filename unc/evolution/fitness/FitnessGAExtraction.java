/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.evolution.Fitness;
/*    */ import jml.evolution.Individual;
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
/*    */ public class FitnessGAExtraction
/*    */   extends Fitness
/*    */ {
/* 20 */   protected DataSource data = null;
/* 21 */   protected QuasiMetric metric = null;
/* 22 */   protected double sigma = 0.0D;
/*    */   
/*    */   public FitnessGAExtraction(DataSource _data, QuasiMetric _metric, double _sigma) {
/* 25 */     this.metric = _metric;
/* 26 */     this.data = _data;
/* 27 */     this.sigma = _sigma;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object get(int k) {
/* 34 */     Data d = this.data.get(k);
/*    */ 
/*    */     
/* 37 */     return d.get();
/*    */   }
/*    */   
/*    */   public double evaluate(Individual obj) {
/* 41 */     double sum = 0.0D;
/*    */     
/* 43 */     for (int j = 0; j < this.data.size(); j++) {
/* 44 */       double dis = this.metric.distance(obj, get(j));
/* 45 */       sum += Math.exp(-0.5D * dis / this.sigma);
/*    */     } 
/* 47 */     return sum - 1.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\FitnessGAExtraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
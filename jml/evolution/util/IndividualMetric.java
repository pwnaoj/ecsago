/*    */ package jml.evolution.util;
/*    */ 
/*    */ import jml.evolution.Environment;
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
/*    */ 
/*    */ 
/*    */ public class IndividualMetric
/*    */   extends QuasiMetric
/*    */ {
/*    */   protected Environment env;
/*    */   protected QuasiMetric metric;
/*    */   
/*    */   public IndividualMetric(Environment _env, QuasiMetric _metric) {
/* 24 */     this.metric = _metric;
/* 25 */     this.env = _env;
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
/*    */   public double distance(Object one, Object two) {
/* 37 */     return this.metric.distance(((Individual)one).getThing(this.env), ((Individual)two).getThing(this.env));
/*    */   }
/*    */   
/*    */   public static QuasiMetric generate(Environment environment, QuasiMetric metric) {
/* 41 */     if (metric instanceof IndividualMetric) {
/* 42 */       return metric;
/*    */     }
/* 44 */     return new IndividualMetric(environment, metric);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolutio\\util\IndividualMetric.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
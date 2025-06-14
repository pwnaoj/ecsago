/*    */ package jml.evolution.algorithms.haea;
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
/*    */ public class DC_MaxDistance
/*    */   extends DCHaea
/*    */ {
/*    */   protected double max_distance;
/*    */   
/*    */   public DC_MaxDistance(Environment _environment, QuasiMetric _metric, double _max_distance) {
/* 51 */     super(_environment, _metric);
/* 52 */     this.max_distance = _max_distance;
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
/* 64 */     return (super.can_replace(child, parent) && this.metric.distance(parent, child) <= this.max_distance);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\haea\DC_MaxDistance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
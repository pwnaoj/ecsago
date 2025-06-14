/*    */ package unc;
/*    */ 
/*    */ import jml.random.Partition;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.evolution.fitness.Weight;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IncrementalInfo
/*    */ {
/* 17 */   protected Partition partition = null;
/* 18 */   protected double memoryFactor = 0.0D;
/* 19 */   protected QuasiMetric metric = null;
/* 20 */   protected Weight weight = null;
/*    */   
/*    */   public IncrementalInfo(Partition _partition, double _memoryFactor, QuasiMetric _metric, Weight _weight) {
/* 23 */     this.partition = _partition;
/* 24 */     this.memoryFactor = _memoryFactor;
/* 25 */     this.metric = _metric;
/* 26 */     this.weight = _weight;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\IncrementalInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
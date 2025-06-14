/*    */ package jml.classification;
/*    */ 
/*    */ import jml.data.DataSource;
/*    */ import jml.data.sources.PartitionDataSource;
/*    */ import jml.random.Partition;
/*    */ import jml.util.Experiment;
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
/*    */ 
/*    */ public class FoldingExperiment
/*    */   extends Experiment
/*    */ {
/* 50 */   protected Partition partition = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   DataSource source = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FoldingExperiment(int _n, ClassifierLearning _algorithm, DataSource _source) {
/* 66 */     super(_n, _algorithm);
/* 67 */     this.source = _source;
/* 68 */     this.partition = new Partition(this.source.size(), this.n, true);
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
/*    */   
/*    */   public FoldingExperiment(int _n, ClassifierLearning _algorithm, DataSource _source, Partition _partition) {
/* 81 */     super(_n, _algorithm);
/* 82 */     this.source = _source;
/* 83 */     this.partition = _partition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init_experiment(int k) {
/* 91 */     if (this.partition != null) {
/* 92 */       ClassifierLearning alg = (ClassifierLearning)this.algorithm;
/* 93 */       alg.setSets((DataSource)new PartitionDataSource(this.source, this.partition, k, false), (DataSource)new PartitionDataSource(this.source, this.partition, k, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\FoldingExperiment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
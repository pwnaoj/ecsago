/*    */ package jml.data.sources;
/*    */ 
/*    */ import jml.data.DataSource;
/*    */ import jml.random.Partition;
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
/*    */ public class PermutationDataSource
/*    */   extends SamplingDataSource
/*    */ {
/*    */   public PermutationDataSource(DataSource _source) {
/* 48 */     super(_source);
/* 49 */     this.m = this.source.size();
/* 50 */     this.index = Partition.permutation(this.m);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\PermutationDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
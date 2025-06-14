/*    */ package jml.evolution.binary;
/*    */ 
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.structures.BitArray;
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
/*    */ public class BinaryGenotype
/*    */   extends Genotype
/*    */ {
/*    */   protected GenomeLimits limits;
/*    */   
/*    */   public BinaryGenotype(GenomeLimits _limits) {
/* 41 */     this.limits = _limits;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object newInstance() {
/* 49 */     int n = this.limits.random_genes_number();
/* 50 */     return new BitArray(n * this.limits.gene_size, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size(Object genome) {
/* 58 */     return ((BitArray)genome).size() / this.limits.gene_size;
/*    */   }
/*    */   public GenomeLimits getLimits() {
/* 61 */     return this.limits;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\BinaryGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
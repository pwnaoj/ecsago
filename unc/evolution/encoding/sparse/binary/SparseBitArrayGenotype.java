/*    */ package unc.evolution.encoding.sparse.binary;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ import jml.structures.SparceBitArray;
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
/*    */ public class SparseBitArrayGenotype
/*    */   extends Genotype
/*    */ {
/* 22 */   protected int n = 0;
/*    */   
/*    */   protected GenomeLimits limits;
/*    */   
/*    */   public SparseBitArrayGenotype(int _n, GenomeLimits _limits) {
/* 27 */     this.limits = _limits;
/* 28 */     this.n = _n;
/*    */   }
/*    */   
/*    */   public int size(Object obj) {
/* 32 */     return ((SparceBitArray)obj).get().size();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object newInstance() {
/* 37 */     SparceBitArray array = new SparceBitArray(new Vector(), this.n);
/* 38 */     int m = this.limits.random_genes_number();
/* 39 */     UniformNumberGenerator g = new UniformNumberGenerator(this.n);
/* 40 */     for (int i = 0; i < m; i++) {
/* 41 */       array.set(g.newInt(), true);
/*    */     }
/* 43 */     return array;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\SparseBitArrayGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.binary.operators.XOver;
/*    */ import jml.random.UniformNumberGenerator;
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
/*    */ public class IndependentDimXOver
/*    */   extends XOver
/*    */ {
/*    */   public IndependentDimXOver(Environment env) {
/* 23 */     super(env);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IndependentDimXOver(Environment env, Selection _selection) {
/* 31 */     super(env, _selection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(BitArray child1, BitArray child2, GenomeLimits limits) {
/* 41 */     int sizeDim = limits.gene_size;
/* 42 */     int dim = child1.size() / sizeDim;
/* 43 */     int cross_over_point = 0;
/* 44 */     UniformNumberGenerator g = new UniformNumberGenerator(sizeDim);
/*    */     
/* 46 */     for (int i = 0; i < dim; i++) {
/* 47 */       cross_over_point = i * sizeDim + g.newInt();
/*    */       
/* 49 */       apply(child1, child2, cross_over_point);
/*    */     } 
/* 51 */     return new Integer(cross_over_point);
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\IndependentDimXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
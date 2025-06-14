/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.binary.operators.Mutation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IndependentDimMutation
/*    */   extends Mutation
/*    */ {
/*    */   public IndependentDimMutation(Environment env, double _bit_mutation_rate) {
/* 25 */     super(env, _bit_mutation_rate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(BitArray genome, GenomeLimits limits) {
/* 34 */     int dim = genome.size() / limits.gene_size;
/* 35 */     int pos = -1;
/* 36 */     int interv = 0;
/*    */ 
/*    */     
/* 39 */     try { UniformNumberGenerator g = new UniformNumberGenerator(limits.gene_size);
/* 40 */       for (int i = 0; i < dim; i++) {
/* 41 */         double r = Math.random();
/* 42 */         if (r <= this.bit_mutation_rate) {
/* 43 */           pos = g.newInt() + interv;
/* 44 */           genome.not(pos);
/* 45 */           interv += limits.gene_size;
/*    */         } 
/*    */       }  }
/* 48 */     catch (Exception e) { System.err.println("[IndMutation]" + e.getMessage()); }
/* 49 */      return new Integer(pos);
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\IndependentDimMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
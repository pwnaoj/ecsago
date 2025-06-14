/*    */ package jml.evolution.binary.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.evolution.binary.BinaryGenotype;
/*    */ import jml.evolution.operators.ArityOne;
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
/*    */ public class SingleBitMutation
/*    */   extends ArityOne
/*    */ {
/*    */   public SingleBitMutation(Environment _environment) {
/* 48 */     super(_environment);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object gen) {
/* 57 */     BitArray genome = (BitArray)gen;
/* 58 */     int pos = -1;
/*    */     
/* 60 */     try { UniformNumberGenerator g = new UniformNumberGenerator(genome.size());
/* 61 */       pos = g.newInt();
/* 62 */       genome.not(pos); }
/* 63 */     catch (Exception e) { System.err.println("[Mutation]" + e.getMessage()); }
/* 64 */      return new Integer(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] argv) {
/* 71 */     System.out.println("*** Generating a genome of 20 genes randomly ***");
/* 72 */     BitArray genome = new BitArray(21, true);
/* 73 */     GenomeLimits limits = new GenomeLimits(21, 27, 3);
/* 74 */     System.out.println(genome.toString());
/*    */     
/* 76 */     SingleBitMutation mutation = new SingleBitMutation(new Environment((Genotype)new BinaryGenotype(limits), null));
/*    */     
/* 78 */     System.out.println("*** Applying the mutation ***");
/* 79 */     Object pos = mutation.apply(genome);
/* 80 */     System.out.println("Position " + pos.toString());
/*    */     
/* 82 */     System.out.println("*** Genome ***");
/* 83 */     System.out.println(genome.toString());
/*    */     
/* 85 */     System.out.println("*** Applying the mutation ***");
/* 86 */     pos = mutation.apply(genome);
/* 87 */     System.out.println("Position " + pos.toString());
/*    */     
/* 89 */     System.out.println("*** Genome ***");
/* 90 */     System.out.println(genome.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\SingleBitMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
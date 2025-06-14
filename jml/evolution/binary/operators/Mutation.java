/*     */ package jml.evolution.binary.operators;
/*     */ 
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.GenomeLimits;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.binary.BinaryGenotype;
/*     */ import jml.evolution.operators.ArityOne;
/*     */ import jml.random.UniformNumberGenerator;
/*     */ import jml.structures.BitArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mutation
/*     */   extends ArityOne
/*     */ {
/*  45 */   protected double bit_mutation_rate = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mutation(Environment _environment) {
/*  52 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mutation(Environment _environment, double _bit_mutation_rate) {
/*  60 */     super(_environment);
/*  61 */     this.bit_mutation_rate = _bit_mutation_rate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object gen) {
/*  70 */     BitArray genome = (BitArray)gen;
/*  71 */     int count = 0;
/*     */     
/*  73 */     try { double rate = this.bit_mutation_rate;
/*  74 */       if (this.bit_mutation_rate == 0.0D) rate = 1.0D / genome.size(); 
/*  75 */       UniformNumberGenerator g = new UniformNumberGenerator();
/*  76 */       for (int i = 0; i < genome.size(); i++) {
/*  77 */         if (g.newDouble() < rate) {
/*  78 */           genome.not(i);
/*  79 */           count++;
/*     */         } 
/*     */       }  }
/*  82 */     catch (Exception e) { System.err.println("[Mutation]" + e.getMessage()); }
/*  83 */      return new Integer(count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/*  90 */     System.out.println("*** Generating a genome of 20 genes randomly ***");
/*  91 */     BitArray genome = new BitArray(21, true);
/*  92 */     GenomeLimits limits = new GenomeLimits(21, 27, 3);
/*  93 */     System.out.println(genome.toString());
/*     */     
/*  95 */     Mutation mutation = new Mutation(new Environment((Genotype)new BinaryGenotype(limits), null), 0.01D);
/*     */     
/*  97 */     System.out.println("*** Applying the mutation ***");
/*  98 */     Object pos = mutation.apply(genome);
/*  99 */     System.out.println("Number of mutated bits " + pos.toString());
/*     */     
/* 101 */     System.out.println("*** Genome ***");
/* 102 */     System.out.println(genome.toString());
/*     */     
/* 104 */     System.out.println("*** Applying the mutation ***");
/* 105 */     pos = mutation.apply(genome);
/* 106 */     System.out.println("Number of mutated bits: " + pos.toString());
/*     */     
/* 108 */     System.out.println("*** Genome ***");
/* 109 */     System.out.println(genome.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\Mutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DelGen
/*     */   extends ArityOne
/*     */ {
/*     */   protected boolean del_last_gene = true;
/*     */   
/*     */   public DelGen(Environment _environment) {
/*  53 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DelGen(Environment _environment, boolean _del_last_gene) {
/*  63 */     super(_environment);
/*  64 */     this.del_last_gene = _del_last_gene;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object gen) {
/*  72 */     BitArray genome = (BitArray)gen;
/*  73 */     GenomeLimits limits = ((BinaryGenotype)this.environment.getGenotype()).getLimits();
/*  74 */     int gene_size = limits.gene_size;
/*  75 */     if (genome.size() >= (limits.min_genes + 1) * gene_size) {
/*  76 */       if (this.del_last_gene) {
/*  77 */         genome.del(gene_size);
/*  78 */         return new Integer(gene_size);
/*     */       } 
/*  80 */       UniformNumberGenerator g = new UniformNumberGenerator((genome.size() / gene_size));
/*     */       
/*  82 */       int k = g.newInt();
/*  83 */       BitArray gene = null;
/*  84 */       gene = genome.subBitArray((k + 1) * gene_size);
/*  85 */       genome.del(genome.size() - k * gene_size);
/*  86 */       genome.add(gene);
/*  87 */       return new Integer(k);
/*     */     } 
/*  89 */     return "No gene was deleted";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/*  96 */     System.out.println("*** Generating a genome of 21 genes randomly ***");
/*  97 */     BitArray genome = new BitArray(27, true);
/*  98 */     GenomeLimits limits = new GenomeLimits(1, 9, 3);
/*  99 */     System.out.println(genome.toString());
/*     */     
/* 101 */     System.out.println("*** Generating a Deletion Gen operation with gen length of 7 ***");
/* 102 */     DelGen del = new DelGen(new Environment((Genotype)new BinaryGenotype(limits), null));
/*     */     
/* 104 */     System.out.println("*** Applying the deletion ***");
/*     */     
/* 106 */     Object gene = del.apply(genome);
/*     */     
/* 108 */     System.out.println("*** Genome ***");
/* 109 */     System.out.println(genome.toString());
/* 110 */     System.out.println(gene.toString());
/*     */     
/* 112 */     System.out.println("*** Applying the deletion ***");
/* 113 */     gene = del.apply(genome);
/*     */     
/* 115 */     System.out.println("*** Genome ***");
/* 116 */     System.out.println(genome.toString());
/* 117 */     System.out.println(gene.toString());
/*     */     
/* 119 */     System.out.println("*** Applying the deletion ***");
/* 120 */     gene = del.apply(genome);
/*     */     
/* 122 */     System.out.println("*** Genome ***");
/* 123 */     System.out.println(genome.toString());
/* 124 */     System.out.println(gene.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\DelGen.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
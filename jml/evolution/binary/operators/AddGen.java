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
/*     */ public class AddGen
/*     */   extends ArityOne
/*     */ {
/*     */   protected boolean append = true;
/*     */   
/*     */   public AddGen(Environment _environment) {
/*  50 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AddGen(Environment _environment, boolean _append) {
/*  59 */     super(_environment);
/*  60 */     this.append = _append;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object gen) {
/*  68 */     BitArray genome = (BitArray)gen;
/*  69 */     GenomeLimits limits = ((BinaryGenotype)this.environment.getGenotype()).getLimits();
/*  70 */     int gene_size = limits.gene_size;
/*  71 */     if (genome.size() < gene_size * limits.max_genes) {
/*  72 */       BitArray gene = new BitArray(limits.gene_size, true);
/*  73 */       if (this.append) {
/*  74 */         genome.add(gene);
/*     */       } else {
/*  76 */         int size = genome.size() / gene_size;
/*  77 */         UniformNumberGenerator g = new UniformNumberGenerator((size + 1));
/*  78 */         int k = g.newInt();
/*  79 */         if (k == size) {
/*  80 */           genome.add(gene);
/*     */         } else {
/*  82 */           BitArray right = genome.subBitArray(k * gene_size);
/*  83 */           genome.del(genome.size() - k * gene_size);
/*  84 */           genome.add(gene);
/*  85 */           genome.add(right);
/*     */         } 
/*     */       } 
/*  88 */       return gene;
/*  89 */     }  return "No gene was added";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/*  96 */     System.out.println("*** Generating a genome of 21 genes randomly ***");
/*  97 */     BitArray genome = new BitArray(21, true);
/*  98 */     System.out.println(genome.toString());
/*     */     
/* 100 */     System.out.println("*** Generating a Addition Gen operation with gen length of 3 ***");
/* 101 */     GenomeLimits limits = new GenomeLimits(21, 27, 3);
/* 102 */     AddGen add = new AddGen(new Environment((Genotype)new BinaryGenotype(limits), null));
/* 103 */     System.out.println("*** Applying the addition ***");
/*     */     
/* 105 */     Object gene = add.apply(genome);
/*     */     
/* 107 */     System.out.println("*** Genome ***");
/* 108 */     System.out.println(genome.toString());
/* 109 */     System.out.println(gene.toString());
/*     */     
/* 111 */     System.out.println("*** Applying the addition ***");
/* 112 */     gene = add.apply(genome);
/* 113 */     System.out.println(gene.toString());
/*     */     
/* 115 */     System.out.println("*** Applying the addition ***");
/* 116 */     gene = add.apply(genome);
/* 117 */     System.out.println(gene.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\AddGen.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
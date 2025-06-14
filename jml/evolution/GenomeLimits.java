/*     */ package jml.evolution;
/*     */ 
/*     */ import jml.random.UniformNumberGenerator;
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
/*     */ public class GenomeLimits
/*     */ {
/*  36 */   protected UniformNumberGenerator g = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public int min_genes = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public int max_genes = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public int gene_size = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenomeLimits(int _min_genes, int _max_genes, int _gene_size) {
/*  60 */     this.gene_size = _gene_size;
/*  61 */     this.min_genes = _min_genes;
/*  62 */     this.max_genes = _max_genes;
/*  63 */     if (this.min_genes < this.max_genes) {
/*  64 */       this.g = new UniformNumberGenerator((this.max_genes - this.min_genes + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenomeLimits(int _min_genes, int _max_genes) {
/*  75 */     this.min_genes = _min_genes;
/*  76 */     this.max_genes = _max_genes;
/*  77 */     if (this.min_genes < this.max_genes) {
/*  78 */       this.g = new UniformNumberGenerator((this.max_genes - this.min_genes + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenomeLimits(int n) {
/*  88 */     this.min_genes = n;
/*  89 */     this.max_genes = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenomeLimits(GenomeLimits source) {
/*  98 */     this.gene_size = source.gene_size;
/*  99 */     this.min_genes = source.min_genes;
/* 100 */     this.max_genes = source.max_genes;
/* 101 */     this.g = source.g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int random_genes_number() {
/* 109 */     if (this.g != null) return this.min_genes + this.g.newInt(); 
/* 110 */     return this.min_genes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     StringBuffer sb = new StringBuffer();
/* 119 */     sb.append("[genes number] " + this.gene_size + "\n");
/* 120 */     sb.append("[min] " + this.min_genes + "\n");
/* 121 */     sb.append("[max] " + this.max_genes + "\n");
/* 122 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\GenomeLimits.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
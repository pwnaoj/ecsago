/*    */ package unc.evolution.encoding.messy;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.GenomeLimits;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessyGenotype
/*    */   extends Genotype
/*    */ {
/*    */   public static final int BINARY = 0;
/*    */   public static final int INTEGER = 1;
/*    */   public static final int REAL = 2;
/* 21 */   protected int type = 0;
/* 22 */   protected int n = 0;
/*    */   
/*    */   protected GenomeLimits limits;
/*    */   
/*    */   public MessyGenotype(int _n, int _type, GenomeLimits _limits) {
/* 27 */     this.limits = _limits;
/* 28 */     this.type = _type;
/* 29 */     this.n = _n;
/*    */   }
/*    */   
/*    */   public Object newInstance() {
/* 33 */     int m = this.limits.random_genes_number();
/* 34 */     UniformNumberGenerator g = new UniformNumberGenerator();
/* 35 */     Vector genome = new Vector();
/*    */ 
/*    */     
/* 38 */     for (int i = 0; i < m; i++) {
/* 39 */       MessyGene gene; int locus = (int)(this.n * g.newDouble());
/* 40 */       if (this.type == 0) {
/* 41 */         gene = new MessyGene(locus, g.newBoolean());
/*    */       } else {
/* 43 */         gene = new MessyGene(locus, g.newDouble());
/*    */       } 
/* 45 */       genome.add(gene.copy());
/*    */     } 
/* 47 */     return genome;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size(Object genome) {
/* 55 */     return ((Vector)genome).size();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\messy\MessyGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
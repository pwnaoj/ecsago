/*    */ package unc.evolution.encoding.messy;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import jml.random.UniformNumberGenerator;
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
/*    */ public class MessyMutation
/*    */   extends ArityOne
/*    */ {
/* 20 */   protected double gene_mutation_rate = 0.0D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MessyMutation(Environment env) {
/* 27 */     super(env);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MessyMutation(Environment env, double _gene_mutation_rate) {
/* 35 */     super(env);
/* 36 */     this.gene_mutation_rate = _gene_mutation_rate;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object obj) {
/* 45 */     MessyGenotype genotype = (MessyGenotype)this.environment.getGenotype();
/*    */     
/* 47 */     Vector g_one = (Vector)obj;
/* 48 */     int n = genotype.n;
/* 49 */     int count = 0;
/*    */     
/* 51 */     try { double rate = this.gene_mutation_rate;
/* 52 */       if (this.gene_mutation_rate == 0.0D) rate = 1.0D / g_one.size(); 
/* 53 */       UniformNumberGenerator g = new UniformNumberGenerator();
/* 54 */       for (int i = 0; i < g_one.size(); i++) {
/* 55 */         MessyGene gene = g_one.get(i);
/* 56 */         if (g.newDouble() < rate) {
/* 57 */           if (g.newBoolean()) {
/* 58 */             gene.locus = (int)(n * g.newDouble());
/*    */           } else {
/* 60 */             switch (genotype.type) {
/*    */               case 0:
/* 62 */                 gene.boolValue = !gene.boolValue;
/*    */                 break;
/*    */               case 2:
/* 65 */                 gene.realValue = g.newDouble();
/*    */                 break;
/*    */             } 
/*    */           } 
/* 69 */           count++;
/*    */         } 
/*    */       }  }
/* 72 */     catch (Exception e) { System.err.println("[Mutation]" + e.getMessage()); }
/* 73 */      return new Integer(count);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\messy\MessyMutation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.utils;
/*    */ 
/*    */ import jml.data.DataSource;
/*    */ import jml.evolution.Genotype;
/*    */ import jml.evolution.Phenotype;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataSourceGenotype
/*    */   extends Genotype
/*    */ {
/* 23 */   protected DataSource source = null;
/*    */   protected UniformNumberGenerator ng;
/*    */   protected Phenotype phenotype;
/*    */   
/*    */   public DataSourceGenotype(DataSource _source, Phenotype _phenotype) {
/* 28 */     this.phenotype = _phenotype;
/* 29 */     this.source = _source;
/* 30 */     this.ng = new UniformNumberGenerator(this.source.size());
/*    */   }
/*    */   
/*    */   protected Object get() {
/* 34 */     return this.source.get(this.ng.newInt()).get();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object newInstance() {
/* 44 */     return this.phenotype.set(get());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int size(Object object) {
/* 55 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\DataSourceGenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.evolution.encoding.messy;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.algebra.SparseComponents;
/*    */ import jml.algebra.SparseValue;
/*    */ import jml.evolution.Phenotype;
/*    */ import jml.random.Partition;
/*    */ import jml.structures.SparceBitArray;
/*    */ import jml.util.sort.SortableInt;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessyPhenotype
/*    */   extends Phenotype
/*    */ {
/*    */   protected int dimension;
/*    */   protected int type;
/*    */   
/*    */   public MessyPhenotype(int _dimension, int _type) {
/* 25 */     this.dimension = _dimension;
/* 26 */     this.type = _type;
/*    */   } public Object get(Object genome) {
/*    */     SparceBitArray bit_array;
/*    */     SparseComponents sparce_rv;
/* 30 */     Object obj = null;
/* 31 */     Vector gen = (Vector)genome;
/* 32 */     int n = this.dimension;
/* 33 */     Enumeration iter = gen.elements();
/* 34 */     Vector indices = new Vector();
/* 35 */     switch (this.type) {
/*    */       case 0:
/* 37 */         bit_array = new SparceBitArray(indices, n);
/* 38 */         while (iter.hasMoreElements()) {
/* 39 */           MessyGene gene = iter.nextElement();
/* 40 */           bit_array.set(gene.locus, gene.boolValue);
/*    */         } 
/* 42 */         obj = bit_array;
/*    */         break;
/*    */       case 2:
/* 45 */         sparce_rv = new SparseComponents(n, indices);
/* 46 */         while (iter.hasMoreElements()) {
/* 47 */           MessyGene gene = iter.nextElement();
/* 48 */           sparce_rv.set(gene.locus, gene.realValue);
/*    */         } 
/* 50 */         obj = sparce_rv;
/*    */         break;
/*    */     } 
/* 53 */     return obj;
/*    */   }
/*    */   
/*    */   public Object generate(SparceBitArray bit_array) {
/* 57 */     Vector g = new Vector();
/* 58 */     Enumeration iter = bit_array.get().elements();
/* 59 */     while (iter.hasMoreElements()) {
/* 60 */       SortableInt si = iter.nextElement();
/* 61 */       g.add(new MessyGene(si.value, true));
/*    */     } 
/* 63 */     Partition.permutation(g);
/*    */ 
/*    */ 
/*    */     
/* 67 */     return g;
/*    */   }
/*    */   
/*    */   public Object generate(RealVector rv) {
/* 71 */     Vector g = new Vector();
/* 72 */     Enumeration iter = rv.get().sparce_values().elements();
/* 73 */     while (iter.hasMoreElements()) {
/* 74 */       SparseValue sv = iter.nextElement();
/* 75 */       g.add(new MessyGene(sv.index, sv.value));
/*    */     } 
/* 77 */     Partition.permutation(g);
/*    */ 
/*    */ 
/*    */     
/* 81 */     return g;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object set(Object thing) {
/* 86 */     Object obj = null;
/* 87 */     switch (this.type) {
/*    */       case 0:
/* 89 */         generate((SparceBitArray)thing);
/*    */         break;
/*    */       case 2:
/* 92 */         generate((RealVector)thing);
/*    */         break;
/*    */     } 
/* 95 */     return obj;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\messy\MessyPhenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
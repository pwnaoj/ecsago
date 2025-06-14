/*     */ package jml.evolution.binary.operators;
/*     */ 
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.binary.BinaryGenotype;
/*     */ import jml.evolution.operators.ArityTwo;
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
/*     */ public class Join
/*     */   extends ArityTwo
/*     */ {
/*     */   public Join(Environment _environment) {
/*  47 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Join(Environment _environment, Selection _selection) {
/*  56 */     super(_environment, _selection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object c1, Object c2) {
/*  66 */     BitArray child1 = (BitArray)c1;
/*  67 */     BitArray child2 = (BitArray)c2;
/*  68 */     child1.add(child2);
/*  69 */     return new Integer(child2.size());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/*  77 */     System.out.println("*** Generating a genome of 20 genes randomly ***");
/*  78 */     BitArray parent1 = new BitArray(20, true);
/*  79 */     System.out.println(parent1.toString());
/*     */     
/*  81 */     System.out.println("*** Generating a genome of 10 genes randomly ***");
/*  82 */     BitArray parent2 = new BitArray(10, true);
/*  83 */     System.out.println(parent2.toString());
/*     */     
/*  85 */     Join xover = new Join(new Environment((Genotype)new BinaryGenotype(null), null));
/*     */     
/*  87 */     System.out.println("*** Applying the croosover ***");
/*  88 */     Object pos = xover.apply(parent1, parent2);
/*  89 */     System.out.println("Position " + pos);
/*     */     
/*  91 */     System.out.println("*** Child 1 ***");
/*  92 */     System.out.println(parent1.toString());
/*  93 */     System.out.println("*** Child 2 ***");
/*  94 */     System.out.println(parent2.toString());
/*     */     
/*  96 */     System.out.println("*** Applying the croosover with parent2, parent1 ***");
/*  97 */     pos = xover.apply(parent2, parent1);
/*  98 */     System.out.println("Position " + pos);
/*     */     
/* 100 */     System.out.println("*** Child 1 ***");
/* 101 */     System.out.println(parent1.toString());
/* 102 */     System.out.println("*** Child 2 ***");
/* 103 */     System.out.println(parent2.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\Join.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
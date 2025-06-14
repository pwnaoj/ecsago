/*     */ package jml.evolution.binary.operators;
/*     */ 
/*     */ import jml.basics.Cloner;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.binary.BinaryGenotype;
/*     */ import jml.evolution.operators.ArityTwo;
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
/*     */ public class XOver
/*     */   extends ArityTwo
/*     */ {
/*     */   protected int cross_over_point;
/*     */   
/*     */   public XOver(Environment _environment) {
/*  52 */     super(_environment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XOver(Environment _environment, Selection _selection) {
/*  61 */     super(_environment, _selection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(BitArray child1, BitArray child2, int xoverPoint) {
/*  73 */     BitArray child1_1 = (BitArray)Cloner.clone(child1);
/*  74 */     BitArray child2_1 = (BitArray)Cloner.clone(child2);
/*     */     
/*  76 */     this.cross_over_point = xoverPoint;
/*     */     
/*  78 */     child1.leftSetToZero(this.cross_over_point);
/*  79 */     child2.leftSetToZero(this.cross_over_point);
/*  80 */     child1_1.rightSetToZero(this.cross_over_point);
/*  81 */     child2_1.rightSetToZero(this.cross_over_point);
/*  82 */     child1.or(child2_1);
/*  83 */     child2.or(child1_1);
/*  84 */     return new Integer(this.cross_over_point);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Object c1, Object c2) {
/*  93 */     BitArray child1 = (BitArray)c1;
/*  94 */     BitArray child2 = (BitArray)c2;
/*     */     
/*  96 */     UniformNumberGenerator g = new UniformNumberGenerator(Math.min(child1.size(), child2.size()));
/*     */     
/*  98 */     return apply(child1, child2, g.newInt());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 105 */     System.out.println("*** Generating a genome of 20 genes randomly ***");
/* 106 */     BitArray parent1 = new BitArray(20, true);
/* 107 */     System.out.println(parent1.toString());
/*     */     
/* 109 */     System.out.println("*** Generating a genome of 10 genes randomly ***");
/* 110 */     BitArray parent2 = new BitArray(10, true);
/* 111 */     System.out.println(parent2.toString());
/*     */     
/* 113 */     XOver xover = new XOver(new Environment((Genotype)new BinaryGenotype(null), null));
/*     */     
/* 115 */     System.out.println("*** Applying the croosover ***");
/* 116 */     Object pos = xover.apply(parent1, parent2);
/* 117 */     System.out.println("Position " + pos);
/*     */     
/* 119 */     System.out.println("*** Child 1 ***");
/* 120 */     System.out.println(parent1.toString());
/* 121 */     System.out.println("*** Child 2 ***");
/* 122 */     System.out.println(parent2.toString());
/*     */     
/* 124 */     System.out.println("*** Applying the croosover with parent2, parent1 ***");
/* 125 */     pos = xover.apply(parent2, parent1);
/* 126 */     System.out.println("Position " + pos);
/*     */     
/* 128 */     System.out.println("*** Child 1 ***");
/* 129 */     System.out.println(parent1.toString());
/* 130 */     System.out.println("*** Child 2 ***");
/* 131 */     System.out.println(parent2.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\XOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.evolution.binary.operators;
/*    */ 
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.operators.ArityOne;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ import jml.structures.BitArray;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Transposition
/*    */   extends ArityOne
/*    */ {
/*    */   public Transposition(Environment _environment) {
/* 45 */     super(_environment);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object _genome) {
/* 56 */     BitArray genome = (BitArray)_genome;
/*    */     
/* 58 */     UniformNumberGenerator gen = new UniformNumberGenerator(genome.size());
/* 59 */     int start = gen.newInt();
/* 60 */     int end = gen.newInt();
/*    */     
/* 62 */     if (start > end) {
/* 63 */       int t = start;
/* 64 */       start = end;
/* 65 */       end = t;
/*    */     } 
/*    */ 
/*    */     
/* 69 */     while (start < end) {
/* 70 */       boolean tr = genome.get(start);
/* 71 */       genome.set(start, genome.get(end));
/* 72 */       genome.set(end, tr);
/* 73 */       start++;
/* 74 */       end--;
/*    */     } 
/*    */ 
/*    */     
/* 78 */     return new Double(start);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\operators\Transposition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.evolution.binary;
/*    */ 
/*    */ import jml.evolution.Fitness;
/*    */ import jml.evolution.Individual;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BinaryFitness
/*    */   extends Fitness
/*    */ {
/*    */   public abstract double eval(BitArray paramBitArray);
/*    */   
/*    */   public double evaluate(Individual obj) {
/* 51 */     return eval((BitArray)obj.getThing());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\binary\BinaryFitness.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
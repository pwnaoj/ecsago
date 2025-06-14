/*    */ package jml.evolution.util;
/*    */ 
/*    */ import jml.evolution.Phenotype;
/*    */ import jml.structures.BitArray;
/*    */ import jml.structures.BitArrayConverter;
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
/*    */ public class BinaryToIntArrayPhenotype
/*    */   extends Phenotype
/*    */ {
/*    */   protected boolean grayCode;
/*    */   protected int int_size;
/*    */   
/*    */   public BinaryToIntArrayPhenotype(int _int_size, boolean _grayCode) {
/* 50 */     this.grayCode = _grayCode;
/* 51 */     this.int_size = _int_size;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object get(Object genome) {
/* 61 */     return BitArrayConverter.getIntArray((BitArray)genome, this.int_size, this.grayCode);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object set(Object thing) {
/* 70 */     BitArray A = new BitArray(1, false);
/* 71 */     BitArrayConverter.setIntArray(A, (int[])thing, this.int_size, this.grayCode);
/* 72 */     return A;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolutio\\util\BinaryToIntArrayPhenotype.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
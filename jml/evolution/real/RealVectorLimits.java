/*    */ package jml.evolution.real;
/*    */ 
/*    */ import jml.algebra.RealVector;
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
/*    */ public class RealVectorLimits
/*    */ {
/* 41 */   public RealVector min = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public RealVector max = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RealVectorLimits(RealVector _min, RealVector _max) {
/* 54 */     this.min = _min;
/* 55 */     this.max = _max;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RealVectorLimits(RealVectorLimits source) {
/* 64 */     this.min = source.min;
/* 65 */     this.max = source.max;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     StringBuffer sb = new StringBuffer();
/* 74 */     if (this.min != null && this.max != null) {
/* 75 */       sb.append("[min] " + this.min.toString() + "\n");
/* 76 */       sb.append("[max] " + this.max.toString() + "\n");
/*    */     } 
/* 78 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\real\RealVectorLimits.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.fuzzy;
/*    */ 
/*    */ import jml.util.quasimetric.QuasiMetric;
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
/*    */ public class RuleDistance
/*    */   extends QuasiMetric
/*    */ {
/*    */   public double d(Expression x, Expression y) {
/* 43 */     if (y != null) {
/* 44 */       if (x != null) {
/* 45 */         return x.distance(y);
/*    */       }
/* 47 */       return 0.0D;
/*    */     } 
/*    */     
/* 50 */     return 1.0E8D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double distance(Object x, Object y) {
/* 61 */     Rule rx = (Rule)x;
/* 62 */     Rule ry = (Rule)y;
/* 63 */     if (rx != null && ry != null) {
/* 64 */       return d(rx.condition, ry.condition);
/*    */     }
/* 66 */     return 1.0E8D;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\RuleDistance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
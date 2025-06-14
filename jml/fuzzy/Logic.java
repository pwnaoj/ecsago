/*    */ package jml.fuzzy;
/*    */ 
/*    */ import jml.fuzzy.operators.And;
/*    */ import jml.fuzzy.operators.DeFuzzy;
/*    */ import jml.fuzzy.operators.MaxDeFuzzy;
/*    */ import jml.fuzzy.operators.MaxOr;
/*    */ import jml.fuzzy.operators.MinAnd;
/*    */ import jml.fuzzy.operators.Not;
/*    */ import jml.fuzzy.operators.Or;
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
/*    */ 
/*    */ public class Logic
/*    */ {
/*    */   public Not not;
/*    */   public And and;
/*    */   public Or or;
/*    */   public DeFuzzy defuzzy;
/*    */   
/*    */   public Logic() {
/* 58 */     this.not = new Not();
/* 59 */     this.and = (And)new MinAnd();
/* 60 */     this.or = (Or)new MaxOr();
/* 61 */     this.defuzzy = (DeFuzzy)new MaxDeFuzzy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Logic(Not _not, And _and, Or _or, DeFuzzy _defuzzy) {
/* 72 */     this.not = _not;
/* 73 */     this.and = _and;
/* 74 */     this.or = _or;
/* 75 */     this.defuzzy = _defuzzy;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Logic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
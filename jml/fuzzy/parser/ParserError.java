/*    */ package jml.fuzzy.parser;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParserError
/*    */ {
/*    */   public int pos;
/*    */   public String text;
/*    */   
/*    */   public ParserError(int p, String t) {
/* 51 */     this.pos = p;
/* 52 */     this.text = t;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     return "Error in pos " + this.pos + " : " + this.text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(int row, int column) {
/* 68 */     return "Error in row " + (row + 1) + ", column" + (column + 1) + " : " + this.text;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\parser\ParserError.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
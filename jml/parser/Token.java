/*    */ package jml.parser;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Token
/*    */ {
/*    */   public static final int TT_NUMBER = 1;
/*    */   public static final int TT_WORD = 2;
/*    */   public static final int TT_EOF = 3;
/*    */   public static final int TT_ERROR = -1;
/* 56 */   public int type = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double nval;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String sval;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Token(double _nval) {
/* 73 */     this.type = 1;
/* 74 */     this.nval = _nval;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Token(String _sval) {
/* 82 */     this.type = 2;
/* 83 */     this.sval = _sval;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Token() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Token(boolean eof) {
/* 96 */     this.type = 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\parser\Token.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
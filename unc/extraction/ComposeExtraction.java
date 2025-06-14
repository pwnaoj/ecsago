/*    */ package unc.extraction;
/*    */ 
/*    */ import unc.Prototype;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComposeExtraction
/*    */   extends Extraction
/*    */ {
/*    */   public Extraction a;
/*    */   public Extraction b;
/*    */   
/*    */   public ComposeExtraction(Extraction _a, Extraction _b) {
/* 19 */     this.a = _a;
/* 20 */     this.b = _b;
/*    */   }
/*    */   
/*    */   public Prototype apply(Prototype pop) {
/* 24 */     if (this.a == null) {
/* 25 */       return this.b.apply(pop);
/*    */     }
/* 27 */     return this.b.apply(this.a.apply(pop));
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\extraction\ComposeExtraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
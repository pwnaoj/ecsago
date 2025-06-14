/*    */ package jml.data;
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
/*    */ public abstract class Attribute
/*    */ {
/* 39 */   protected String label = "x";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final double MISSING_VALUE = -1.0E108D;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Attribute(String _label) {
/* 50 */     this.label = _label;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract String toXML();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLabel() {
/* 64 */     return this.label;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\Attribute.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.util.sort;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SortableInt
/*    */   extends SortableObject
/*    */   implements Cloneable
/*    */ {
/*    */   public int value;
/*    */   
/*    */   public SortableInt(int _value) {
/* 21 */     this.value = _value;
/*    */   }
/*    */   
/*    */   public boolean equalTo(SortableObject parm1) {
/* 25 */     return (this.value == ((SortableInt)parm1).value);
/*    */   }
/*    */   
/*    */   public Object clone() {
/* 29 */     return new SortableInt(this.value);
/*    */   }
/*    */   
/*    */   public boolean lessThan(SortableObject parm1) {
/* 33 */     return (this.value < ((SortableInt)parm1).value);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\sort\SortableInt.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
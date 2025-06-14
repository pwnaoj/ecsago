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
/*    */ public abstract class SortableObject
/*    */ {
/*    */   public abstract boolean lessThan(SortableObject paramSortableObject);
/*    */   
/*    */   public abstract boolean equalTo(SortableObject paramSortableObject);
/*    */   
/*    */   public boolean greaterThan(SortableObject x) {
/* 54 */     return (!lessThan(x) && differentTo(x));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean differentTo(SortableObject x) {
/* 62 */     return !equalTo(x);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\sort\SortableObject.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
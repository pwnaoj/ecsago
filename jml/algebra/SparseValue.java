/*    */ package jml.algebra;
/*    */ 
/*    */ import jml.util.sort.SortableObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SparseValue
/*    */   extends SortableObject
/*    */   implements Cloneable
/*    */ {
/*    */   public int index;
/*    */   public double value;
/*    */   
/*    */   public SparseValue(int _index, double _value) {
/* 20 */     this.index = _index;
/* 21 */     this.value = _value;
/*    */   }
/*    */   
/*    */   public Object clone() {
/* 25 */     return new SparseValue(this.index, this.value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean lessThan(SortableObject x) {
/* 34 */     return (this.index < ((SparseValue)x).index);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equalTo(SortableObject x) {
/* 43 */     return (this.index == ((SparseValue)x).index);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return "" + this.index + " " + this.value;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\SparseValue.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
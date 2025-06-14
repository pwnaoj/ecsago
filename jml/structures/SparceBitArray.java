/*    */ package jml.structures;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.basics.Cloner;
/*    */ import jml.util.sort.Sort;
/*    */ import jml.util.sort.SortableInt;
/*    */ import jml.util.sort.SortableObject;
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
/*    */ public class SparceBitArray
/*    */   implements Cloneable
/*    */ {
/*    */   protected Vector indices;
/*    */   protected int size;
/*    */   
/*    */   public SparceBitArray(Vector _indices, int _size) {
/* 26 */     this.indices = _indices;
/* 27 */     this.size = _size;
/*    */   }
/*    */   
/*    */   public boolean get(int i) {
/* 31 */     SortableInt si = new SortableInt(i);
/* 32 */     int index = Sort.findLow2High(this.indices, (SortableObject)si);
/* 33 */     return (index < this.indices.size() && ((SortableInt)this.indices.get(index)).value == i);
/*    */   }
/*    */   
/*    */   public void set(int i, boolean val) {
/* 37 */     SortableInt si = new SortableInt(i);
/* 38 */     int index = Sort.findLow2High(this.indices, (SortableObject)si);
/* 39 */     if (val) {
/* 40 */       if (index >= this.indices.size() || si.differentTo((SortableObject)this.indices.get(index))) {
/* 41 */         this.indices.add(index, si);
/*    */       }
/*    */     }
/* 44 */     else if (index < this.indices.size() && ((SortableInt)this.indices.get(index)).value == i) {
/* 45 */       this.indices.remove(index);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void flip(int i) {
/* 51 */     SortableInt si = new SortableInt(i);
/* 52 */     int index = Sort.findLow2High(this.indices, (SortableObject)si);
/* 53 */     if (index < this.indices.size() && ((SortableInt)this.indices.get(index)).value == i) {
/* 54 */       this.indices.remove(index);
/*    */     } else {
/* 56 */       this.indices.add(index, si);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Vector get() {
/* 61 */     return this.indices;
/*    */   }
/*    */   public void set(Vector _indices) {
/* 64 */     this.indices = _indices;
/*    */   }
/*    */   public Object clone() {
/* 67 */     Vector a = Cloner.clone(this.indices);
/* 68 */     return new SparceBitArray(a, this.size);
/*    */   }
/*    */   
/*    */   public int size() {
/* 72 */     return this.size;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     StringBuffer sb = new StringBuffer();
/* 77 */     Enumeration iter = this.indices.elements();
/* 78 */     while (iter.hasMoreElements()) {
/* 79 */       sb.append(((SortableInt)iter.nextElement()).value);
/* 80 */       sb.append(" ");
/*    */     } 
/*    */     
/* 83 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\structures\SparceBitArray.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
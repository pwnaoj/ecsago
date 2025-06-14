/*    */ package unc.evolution.encoding.sparse.binary.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import jml.structures.SparceBitArray;
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
/*    */ public class SparseBitXOver
/*    */   extends ArityTwo
/*    */ {
/*    */   public SparseBitXOver(Environment _environment) {
/* 25 */     super(_environment);
/*    */   }
/*    */   
/*    */   public SparseBitXOver(Environment _environment, Selection _selection) {
/* 29 */     super(_environment, _selection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object one, Object two) {
/* 37 */     SparceBitArray genome1 = (SparceBitArray)one;
/* 38 */     SparceBitArray genome2 = (SparceBitArray)two;
/*    */     
/* 40 */     Vector sx = genome1.get();
/* 41 */     Vector sy = genome2.get();
/* 42 */     int size = genome1.size();
/* 43 */     int index = (int)(size * Math.random());
/* 44 */     int px = Sort.findLow2High(sx, (SortableObject)new SortableInt(index));
/* 45 */     int py = Sort.findLow2High(sy, (SortableObject)new SortableInt(index));
/*    */     
/* 47 */     Vector vx = new Vector();
/* 48 */     Vector vy = new Vector();
/* 49 */     int n = sx.size(); int i;
/* 50 */     for (i = n - 1; i >= px; i--) {
/* 51 */       vx.add(sx.get(i));
/* 52 */       sx.remove(i);
/*    */     } 
/* 54 */     n = sy.size();
/* 55 */     for (i = n - 1; i >= py; i--) {
/* 56 */       vy.add(sy.get(i));
/* 57 */       sy.remove(i);
/*    */     } 
/* 59 */     n = vy.size();
/* 60 */     for (i = n - 1; i >= 0; i--) {
/* 61 */       sx.add(vy.get(i));
/* 62 */       vy.remove(i);
/*    */     } 
/* 64 */     n = vx.size();
/* 65 */     for (i = n - 1; i >= 0; i--) {
/* 66 */       sy.add(vx.get(i));
/* 67 */       vx.remove(i);
/*    */     } 
/* 69 */     System.out.println("Applying crossover operator");
/* 70 */     return new Integer(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\operators\SparseBitXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
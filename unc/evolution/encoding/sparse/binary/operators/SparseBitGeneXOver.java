/*    */ package unc.evolution.encoding.sparse.binary.operators;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import jml.random.NumberGenerator;
/*    */ import jml.structures.SparceBitArray;
/*    */ import jml.util.sort.SortableInt;
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
/*    */ public class SparseBitGeneXOver
/*    */   extends ArityTwo
/*    */ {
/* 24 */   double swapProbability = 0.5D;
/*    */   public SparseBitGeneXOver(Environment _environment) {
/* 26 */     super(_environment);
/*    */   }
/*    */   
/*    */   public SparseBitGeneXOver(Environment _environment, Selection _selection) {
/* 30 */     super(_environment, _selection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object one, Object two) {
/* 38 */     SparceBitArray genome1 = (SparceBitArray)one;
/* 39 */     SparceBitArray genome2 = (SparceBitArray)two;
/* 40 */     Vector sx = genome1.get();
/* 41 */     Vector sy = genome2.get();
/* 42 */     Vector vx = new Vector();
/* 43 */     Vector vy = new Vector();
/*    */     
/* 45 */     boolean swap = false;
/* 46 */     SortableInt ax = null;
/* 47 */     SortableInt by = null;
/* 48 */     Enumeration iter1 = sx.elements();
/* 49 */     Enumeration iter2 = sy.elements();
/* 50 */     while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 51 */       if (ax == null) ax = iter1.nextElement(); 
/* 52 */       if (by == null) by = iter2.nextElement(); 
/* 53 */       swap = (NumberGenerator.random() < this.swapProbability);
/* 54 */       if (ax.value == by.value) {
/* 55 */         if (swap) {
/* 56 */           vx.add(by);
/* 57 */           vy.add(ax);
/*    */         } else {
/* 59 */           vx.add(ax);
/* 60 */           vy.add(by);
/*    */         } 
/* 62 */         ax = null;
/* 63 */         by = null; continue;
/*    */       } 
/* 65 */       if (ax.value < by.value) {
/* 66 */         if (swap) { vy.add(ax); } else { vx.add(ax); }
/* 67 */          ax = null; continue;
/*    */       } 
/* 69 */       if (swap) { vx.add(by); } else { vy.add(by); }
/* 70 */        by = null;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 75 */     if (ax != null) {
/* 76 */       if (NumberGenerator.random() < this.swapProbability) { vy.add(ax); } else { vx.add(ax); }
/*    */     
/*    */     }
/* 79 */     if (by != null) {
/* 80 */       if (NumberGenerator.random() < this.swapProbability) { vx.add(by); } else { vy.add(by); }
/*    */     
/*    */     }
/* 83 */     while (iter1.hasMoreElements()) {
/* 84 */       ax = iter1.nextElement();
/* 85 */       if (NumberGenerator.random() < this.swapProbability) { vy.add(ax); continue; }  vx.add(ax);
/*    */     } 
/*    */     
/* 88 */     while (iter2.hasMoreElements()) {
/* 89 */       by = iter2.nextElement();
/* 90 */       if (NumberGenerator.random() < this.swapProbability) { vx.add(by); continue; }  vy.add(by);
/*    */     } 
/*    */     
/* 93 */     genome1.set(vx);
/* 94 */     genome2.set(vy);
/* 95 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\sparse\binary\operators\SparseBitGeneXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
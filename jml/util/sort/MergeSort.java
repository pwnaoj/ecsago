/*    */ package jml.util.sort;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.random.UniformNumberGenerator;
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
/*    */ public class MergeSort
/*    */   extends Sort
/*    */ {
/*    */   public void apply(Vector A, boolean low2high) {
/* 46 */     if (A != null && A.size() > 0) {
/* 47 */       apply(A, low2high, 0, A.size() - 1, type(A.get(0)));
/*    */     }
/*    */   }
/*    */   
/*    */   private void apply(Vector A, boolean low2high, int start, int end, int t) {
/* 52 */     int n = end - start + 1;
/* 53 */     if (n >= 4) {
/* 54 */       int k = start;
/* 55 */       int n2 = n / 2;
/* 56 */       int med = start + n2;
/* 57 */       int med1 = med - 1;
/* 58 */       apply(A, low2high, start, med1, t);
/* 59 */       apply(A, low2high, med, end, t);
/* 60 */       while (k <= med1 && med <= end) {
/* 61 */         if (compare(A.get(med), A.get(k), t, low2high)) {
/* 62 */           Object x = A.get(med);
/* 63 */           A.remove(med);
/* 64 */           A.add(k, x);
/* 65 */           med1++;
/* 66 */           med++; continue;
/*    */         } 
/* 68 */         k++;
/*    */       } 
/*    */     } else {
/*    */       
/* 72 */       for (int i = start; i < end; i++) {
/* 73 */         for (int j = i + 1; j <= end; j++) {
/* 74 */           if (compare(A.get(j), A.get(i), t, low2high)) {
/* 75 */             Object x = A.get(i);
/* 76 */             A.set(i, A.get(j));
/* 77 */             A.set(j, x);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] argv) {
/* 86 */     Vector v = new Vector();
/* 87 */     UniformNumberGenerator g = new UniformNumberGenerator(100.0D); int i;
/* 88 */     for (i = 0; i < 1000; ) { v.add(new SortableInt(g.newInt())); i++; }
/* 89 */      for (i = 0; i < 1000; i++) {
/* 90 */       System.out.print(" " + ((SortableInt)v.get(i)).value);
/*    */     }
/* 92 */     System.out.println();
/* 93 */     MergeSort sort = new MergeSort();
/* 94 */     sort.apply(v, true);
/* 95 */     for (int j = 0; j < 1000; j++) {
/* 96 */       System.out.print(" " + ((SortableInt)v.get(j)).value);
/*    */     }
/* 98 */     System.out.println();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\sort\MergeSort.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
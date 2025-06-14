/*     */ package jml.util.sort;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Sort
/*     */ {
/*     */   public static final int INTEGER = 1;
/*     */   public static final int DOUBLE = 2;
/*     */   public static final int STRING = 3;
/*     */   public static final int SORTABLEOBJECT = 4;
/*     */   
/*     */   public abstract void apply(Vector paramVector, boolean paramBoolean);
/*     */   
/*     */   protected static int type(Object x) {
/*  56 */     int t = 0;
/*  57 */     if (x instanceof Integer) {
/*  58 */       t = 1;
/*     */     }
/*  60 */     else if (x instanceof Double) {
/*  61 */       t = 2;
/*     */     }
/*  63 */     else if (x instanceof String) {
/*  64 */       t = 3;
/*     */     }
/*  66 */     else if (x instanceof SortableObject) {
/*  67 */       t = 4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  72 */     return t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean lessThan(Object x, Object y, int type) {
/*  83 */     switch (type) {
/*     */       case 1:
/*  85 */         return (((Integer)x).intValue() < ((Integer)y).intValue());
/*     */       case 2:
/*  87 */         return (((Double)x).intValue() < ((Double)y).intValue());
/*     */       case 3:
/*  89 */         return (((String)x).compareTo(y) < 0);
/*     */       case 4:
/*  91 */         return ((SortableObject)x).lessThan((SortableObject)y);
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean compare(Object x, Object y, int type, boolean low2high) {
/*  98 */     return !(lessThan(x, y, type) ^ low2high);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int findHigh2Low(Vector sorted, SortableObject x) {
/* 109 */     int a = 0;
/* 110 */     int b = sorted.size() - 1;
/* 111 */     if (sorted.size() == 0 || x.greaterThan(sorted.get(a)) || x.equalTo(sorted.get(a)))
/* 112 */       return 0; 
/* 113 */     if (x.lessThan(sorted.get(b))) return b + 1; 
/* 114 */     if (x.equalTo(sorted.get(b))) return b; 
/* 115 */     while (a + 1 < b) {
/* 116 */       int m = (a + b) / 2;
/* 117 */       if (x.equalTo(sorted.get(m))) return m; 
/* 118 */       if (x.greaterThan(sorted.get(m))) {
/* 119 */         b = m; continue;
/*     */       } 
/* 121 */       a = m;
/*     */     } 
/*     */     
/* 124 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int findLow2High(Vector sorted, SortableObject x) {
/* 135 */     int a = 0;
/* 136 */     int b = sorted.size() - 1;
/* 137 */     if (sorted.size() == 0 || x.lessThan(sorted.get(a)) || x.equalTo(sorted.get(a)))
/* 138 */       return 0; 
/* 139 */     if (x.greaterThan(sorted.get(b))) return b + 1; 
/* 140 */     if (x.equalTo(sorted.get(b))) return b; 
/* 141 */     while (a + 1 < b) {
/* 142 */       int m = (a + b) / 2;
/* 143 */       if (x.equalTo(sorted.get(m))) return m; 
/* 144 */       if (x.lessThan(sorted.get(m))) {
/* 145 */         b = m; continue;
/*     */       } 
/* 147 */       a = m;
/*     */     } 
/*     */     
/* 150 */     return b;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\sort\Sort.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
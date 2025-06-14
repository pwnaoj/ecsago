/*     */ package jml.structures;
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
/*     */ public class DisjointSets
/*     */ {
/*  41 */   public int[] p = null;
/*     */ 
/*     */ 
/*     */   
/*  45 */   public int[] rank = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DisjointSets(int n) {
/*  53 */     this.p = new int[n];
/*  54 */     this.rank = new int[n];
/*  55 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  63 */     for (int i = 0; i < this.p.length; i++) {
/*  64 */       this.p[i] = i;
/*  65 */       this.rank[i] = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int find(int x) {
/*  75 */     if (this.p[x] != x) {
/*  76 */       this.p[x] = find(this.p[x]);
/*     */     }
/*  78 */     return this.p[x];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int union(int x, int y) {
/*  89 */     return link(find(x), find(y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int link(int x, int y) {
/*  99 */     if (x != y) {
/* 100 */       if (this.rank[x] > this.rank[y])
/* 101 */       { int t = x;
/* 102 */         x = y;
/* 103 */         y = t; }
/*     */       
/* 105 */       else if (this.rank[x] == this.rank[y]) { this.rank[y] = this.rank[y] + 1; }
/*     */       
/* 107 */       this.p[x] = y;
/*     */     } 
/* 109 */     return y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] get() {
/* 117 */     for (int i = 0; i < this.p.length; ) { find(this.p[i]); i++; }
/* 118 */      return this.p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     StringBuffer sb = new StringBuffer();
/* 127 */     get();
/* 128 */     for (int i = 0; i < this.p.length; i++) {
/* 129 */       sb.append(this.p[i]);
/* 130 */       sb.append("\n");
/*     */     } 
/* 132 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\structures\DisjointSets.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightCardinality
/*    */   extends Weight
/*    */ {
/*    */   public int[] top;
/*    */   protected int c;
/*    */   
/*    */   public WeightCardinality(int cardinality) {
/* 17 */     super(true);
/* 18 */     this.top = new int[cardinality];
/* 19 */     this.c = cardinality;
/*    */   }
/*    */   
/*    */   public int posMaxTopDistance(double[] distances) {
/* 23 */     int max = 0;
/* 24 */     for (int i = 1; i < this.c; i++) {
/* 25 */       if (distances[this.top[i]] > distances[this.top[max]]) {
/* 26 */         max = i;
/*    */       }
/*    */     } 
/* 29 */     return max;
/*    */   }
/*    */   
/*    */   public double maxTopDistance(double[] distances) {
/* 33 */     int max = posMaxTopDistance(distances);
/* 34 */     return distances[this.top[max]];
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateTopDistances(double[] distances) {
/* 39 */     this.top[0] = 0; int i;
/* 40 */     for (i = 1; i < this.c; i++) {
/* 41 */       this.top[i] = i;
/*    */     }
/*    */     
/* 44 */     for (i = this.c; i < distances.length; i++) {
/* 45 */       int posMax = posMaxTopDistance(distances);
/* 46 */       if (distances[i] < distances[this.top[posMax]]) {
/* 47 */         this.top[posMax] = i;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void apply(double[] distances, double[] size, Set[] sets, Set set) {
/* 53 */     reset();
/* 54 */     calculateTopDistances(distances);
/* 55 */     this.sumWeight = 0.0D;
/* 56 */     int i = 0;
/* 57 */     while (i < this.c && this.sumWeight < this.c) {
/* 58 */       this.sumWeight += size[this.top[i]];
/* 59 */       this.sumWeidis += distances[this.top[i]] * size[this.top[i]];
/* 60 */       i++;
/*    */     } 
/*    */   }
/*    */   
/*    */   public int[] topDistances() {
/* 65 */     return this.top;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 69 */     double[] distances = { 8.0D, 5.0D, 9.0D, 1.0D, 3.0D, 5.0D, 2.0D, 10.0D, 15.0D, 7.0D, 4.0D, 11.0D, 6.0D, 13.0D, 12.0D, 14.0D };
/* 70 */     double[] size = { 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 2.0D, 2.0D, 2.0D, 2.0D };
/* 71 */     int c = 5;
/* 72 */     double sigma = 0.0D;
/* 73 */     WeightCardinality a = new WeightCardinality(c);
/* 74 */     a.apply(distances, size, (Set[])null, (Set)null);
/* 75 */     for (int i = 0; i < a.top.length; i++)
/* 76 */       System.out.println(a.top[i] + " " + distances[a.top[i]]); 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\WeightCardinality.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
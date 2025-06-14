/*    */ package unc.evolution.fitness;
/*    */ 
/*    */ import jml.fuzzy.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightCardinalityDistance
/*    */   extends WeightCardinality
/*    */ {
/*    */   public double[][] top_distances;
/*    */   protected Weight sigma;
/*    */   
/*    */   public WeightCardinalityDistance(int _cardinality, Weight _sigma) {
/* 18 */     super(_cardinality);
/* 19 */     this.sigma = _sigma;
/* 20 */     this.top_distances = new double[2][this.c];
/*    */   }
/*    */   
/*    */   public void apply(double[] distances, double[] size, Set[] sets, Set set) {
/* 24 */     reset();
/* 25 */     calculateTopDistances(distances);
/* 26 */     double[] top_distances = null;
/* 27 */     this.sigma.apply(top_distances, size, sets, set);
/* 28 */     this.sumWeight = this.sigma.sumWeight;
/* 29 */     this.sumWeidis = this.sigma.sumWeidis;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\WeightCardinalityDistance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
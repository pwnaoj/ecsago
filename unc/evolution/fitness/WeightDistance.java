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
/*    */ public class WeightDistance
/*    */   extends Weight
/*    */ {
/* 14 */   public double T = 0.0D;
/*    */   
/*    */   public WeightDistance(double _T, boolean _binarized) {
/* 17 */     super(_binarized);
/* 18 */     this.T = _T;
/*    */   }
/*    */   
/*    */   public void apply(double[] distances, double[] size, Set[] sets, Set set) {
/* 22 */     reset();
/* 23 */     int n = distances.length;
/* 24 */     for (int j = 0; j < n; j++) {
/* 25 */       if (distances[j] < this.T)
/* 26 */         if (this.binarized) {
/* 27 */           this.sumWeight++;
/* 28 */           this.sumWeidis += distances[j] * size[j];
/* 29 */           this.sumWeidisSqr += distances[j] * distances[j] * size[j];
/*    */         } else {
/* 31 */           double weight = getWeight(distances[j], set);
/* 32 */           this.sumWeight += weight;
/* 33 */           this.sumWeidis += distances[j] * size[j] * weight;
/* 34 */           this.sumWeidisSqr += distances[j] * distances[j] * size[j] * weight;
/*    */         }  
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\WeightDistance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
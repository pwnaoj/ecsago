/*    */ package unc.extraction;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import unc.Cluster;
/*    */ import unc.Prototype;
/*    */ import unc.utils.DistanceSize;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FitnessExtraction
/*    */   extends Extraction
/*    */ {
/* 20 */   public double threshold = 0.0D;
/*    */   protected int type;
/* 22 */   protected DistanceSize data = null;
/*    */   
/*    */   public static final int ABSOLUTE_VALUE = 0;
/*    */   
/*    */   public static final int PROPORTION_AVG = 1;
/*    */   public static final int PROPORTION_MAX = 2;
/*    */   public static final int PROPORTION_MEDIAN = 3;
/*    */   public static final int MINIMUM_DENSITY = 4;
/*    */   
/*    */   public FitnessExtraction(double _threshold, DistanceSize _data) {
/* 32 */     this.data = _data;
/* 33 */     this.threshold = _threshold;
/* 34 */     this.type = 4;
/*    */   }
/*    */   
/*    */   public FitnessExtraction(double _threshold, int _type) {
/* 38 */     this.threshold = _threshold;
/* 39 */     this.type = _type;
/* 40 */     if (this.type == 4) this.type = 0; 
/*    */   }
/*    */   
/*    */   public Prototype apply(Prototype pop) {
/* 44 */     double best, min_value = 0.0D;
/* 45 */     switch (this.type) { case 0:
/* 46 */         min_value = this.threshold; break;
/*    */       case 2:
/* 48 */         best = ((Cluster)pop.get(0)).getFitness();
/* 49 */         min_value = this.threshold * best; break;
/*    */       case 1:
/* 51 */         min_value = this.threshold * pop.avg(); break;
/* 52 */       case 3: min_value = this.threshold * ((Cluster)pop.get(pop.size() / 2)).getFitness(); break;
/* 53 */       case 4: min_value = this.data.samples() * this.threshold; break; }
/*    */     
/* 55 */     Vector extracted = new Vector();
/* 56 */     Enumeration iter = pop.elements();
/* 57 */     while (iter.hasMoreElements()) {
/* 58 */       Cluster ind = iter.nextElement();
/* 59 */       if (ind.getFitness() > min_value) {
/* 60 */         extracted.add(ind);
/*    */       }
/*    */     } 
/* 63 */     return new Prototype(extracted);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\extraction\FitnessExtraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
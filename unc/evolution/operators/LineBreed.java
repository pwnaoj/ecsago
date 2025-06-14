/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Selection;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Cluster;
/*    */ import unc.sets.SqrGaussianSet;
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
/*    */ public class LineBreed
/*    */   extends Selection
/*    */ {
/*    */   public double ProxF;
/*    */   public double SigmaShare;
/*    */   public double NicheRadius;
/*    */   public boolean climbSigma;
/*    */   public double SigmaFactor;
/*    */   public double[] scaledSigma;
/*    */   public QuasiMetric metric;
/*    */   
/*    */   public LineBreed(Environment env, double _ProxF, double _SigmaShare, int n, boolean c, boolean _climbSigma, double _SigmaFactor, double[] _scaledSigma, QuasiMetric _metric) {
/* 48 */     super(env, n, c);
/* 49 */     this.ProxF = _ProxF;
/* 50 */     this.SigmaShare = _SigmaShare;
/* 51 */     this.NicheRadius = this.ProxF * this.SigmaShare;
/* 52 */     this.climbSigma = _climbSigma;
/* 53 */     this.SigmaFactor = _SigmaFactor;
/* 54 */     this.scaledSigma = _scaledSigma;
/* 55 */     this.metric = _metric;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vector choose(Population population, Individual mate1) {
/* 60 */     double MaxFitness = 0.0D;
/* 61 */     double minDist = 1.0E8D;
/* 62 */     int bestmate = 0;
/* 63 */     Vector sel = null;
/* 64 */     if (population != null) {
/* 65 */       int popSize = population.size();
/* 66 */       sel = new Vector();
/*    */       
/* 68 */       if (this.climbSigma) {
/* 69 */         Cluster c = (Cluster)mate1.getThing();
/* 70 */         double sigma = ((SqrGaussianSet)c.getSet()).getSqrSigma();
/* 71 */         this.NicheRadius = Math.sqrt(sigma * this.SigmaFactor);
/*    */       } 
/* 73 */       for (int i = 0; i < popSize; i++) {
/* 74 */         Individual mate2 = population.get(i);
/* 75 */         double dist = this.metric.distance(mate1, mate2);
/* 76 */         dist = Math.sqrt(dist);
/* 77 */         if (mate1 != mate2 && dist <= this.NicheRadius && this.scaledSigma[i] >= MaxFitness) {
/* 78 */           MaxFitness = this.scaledSigma[i];
/* 79 */           bestmate = i;
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 86 */       sel.add(population.get(bestmate));
/*    */     } 
/* 88 */     return sel;
/*    */   }
/*    */   
/*    */   public Vector choose(Population population, int indMate1) {
/* 92 */     Individual mate1 = population.get(indMate1);
/* 93 */     return choose(population, mate1);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\LineBreed.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
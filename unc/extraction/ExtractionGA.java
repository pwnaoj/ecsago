/*    */ package unc.extraction;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.basics.Cloner;
/*    */ import jml.data.DataSource;
/*    */ import jml.data.sources.VectorDataSource;
/*    */ import jml.evolution.EvolutionaryAlgorithm;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.util.quasimetric.QuasiMetric;
/*    */ import unc.Prototype;
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
/*    */ public class ExtractionGA
/*    */   extends Extraction
/*    */ {
/* 27 */   public EvolutionaryAlgorithm ea = null;
/* 28 */   public Population pop = null;
/* 29 */   public Individual protInd = null;
/* 30 */   public QuasiMetric metric = null;
/*    */ 
/*    */   
/*    */   public int indSize;
/*    */ 
/*    */   
/*    */   public int popSize;
/*    */ 
/*    */   
/*    */   public int generations;
/*    */   
/*    */   public double mProb;
/*    */   
/*    */   public double xoverProb;
/*    */   
/*    */   public double sigma;
/*    */ 
/*    */   
/*    */   public ExtractionGA(int _size, QuasiMetric _metric, double _sigma, double _mProb, double _xoverProb, int _generations) {
/* 49 */     this.indSize = _size;
/* 50 */     this.popSize = this.indSize / 10;
/* 51 */     this.generations = _generations;
/* 52 */     this.mProb = _mProb;
/* 53 */     this.xoverProb = _xoverProb;
/* 54 */     this.sigma = _sigma;
/*    */   }
/*    */   
/*    */   public DataSource toDataSource(Population finalPop) {
/* 58 */     Vector data = new Vector();
/* 59 */     for (int j = 0; j < finalPop.size(); j++) {
/* 60 */       data.add(Cloner.clone(finalPop.get(j)));
/*    */     }
/* 62 */     VectorDataSource d = new VectorDataSource(data);
/* 63 */     return (DataSource)d;
/*    */   }
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
/*    */   public Prototype apply(Prototype finalPop) {
/* 83 */     Vector extracted = new Vector();
/*    */     
/* 85 */     return new Prototype(extracted);
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\extraction\ExtractionGA.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
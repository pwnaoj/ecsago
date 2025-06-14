/*     */ package unc.utils.test;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.basics.Tracer;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.evolution.Fitness;
/*     */ import jml.evolution.GenomeLimits;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Phenotype;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.evolution.algorithms.haea.DCHaea;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.evolution.real.RealVectorGenotype;
/*     */ import jml.evolution.real.RealVectorLimits;
/*     */ import jml.evolution.real.operators.LinearXOverPerDimension;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.util.quasimetric.Minkowski;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.evolution.encoding.hybrid.HybridGaussianMutation;
/*     */ import unc.evolution.encoding.hybrid.HybridGenotype;
/*     */ import unc.evolution.encoding.hybrid.HybridPhenotype;
/*     */ import unc.evolution.encoding.hybrid.HybridRestrictedMating;
/*     */ import unc.evolution.fitness.ClusterExpFitness;
/*     */ import unc.evolution.fitness.Weight;
/*     */ import unc.evolution.fitness.WeightMembership;
/*     */ import unc.evolution.transformation.ECSAGO;
/*     */ import unc.extraction.ComposeExtraction;
/*     */ import unc.extraction.Extraction;
/*     */ import unc.extraction.FitnessExtraction;
/*     */ import unc.extraction.MinExtractionError;
/*     */ import unc.refinement.ClusterTunning;
/*     */ import unc.refinement.MDE;
/*     */ import unc.refinement.Refinement;
/*     */ import unc.refinement.SigmaTunning;
/*     */ import unc.sets.SqrGaussianSet;
/*     */ import unc.utils.DistanceSize;
/*     */ import unc.utils.tracers.UNCFileTracer;
/*     */ 
/*     */ public class UNCTestOne extends UNCTest {
/*  42 */   protected double max_sigma = 1.0D;
/*  43 */   protected double min_sigma = 0.1D;
/*     */ 
/*     */   
/*  46 */   protected double sigmaFactor = 2.5128624D;
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
/*  59 */   protected double T = 0.8D;
/*     */   protected boolean binarized = true;
/*  61 */   protected double minFitnessExt = 0.0D;
/*     */   
/*  63 */   protected int typeExt = 0;
/*  64 */   protected int ref_iter = 10;
/*     */ 
/*     */   
/*  67 */   protected RealVector min = null;
/*  68 */   protected RealVector max = null;
/*     */ 
/*     */   
/*     */   public UNCTestOne(DataSource _data, int _MAX_ITER, int _POP_SIZE) {
/*  72 */     super(_data, _MAX_ITER, _POP_SIZE);
/*     */   }
/*     */   
/*     */   public void setDataSet(DistanceSize _data) {
/*  76 */     super.setDataSet(_data);
/*  77 */     getClusterTunning();
/*  78 */     if (this.clusterTunning != null && this.clusterTunning instanceof SigmaTunning)
/*  79 */       ((SigmaTunning)this.clusterTunning).setData(this.data); 
/*     */   }
/*     */   
/*     */   public void setMinFitnessExt(double _min) {
/*  83 */     this.minFitnessExt = _min;
/*     */   }
/*     */   public int getDimension() {
/*  86 */     RealVector rv = (RealVector)this.data.data.get(0).get();
/*  87 */     return rv.dimension();
/*     */   }
/*     */   
/*     */   public QuasiMetric getBasicMetric() {
/*  91 */     if (this.basicMetric == null) this.basicMetric = (QuasiMetric)new Minkowski(2.0D); 
/*  92 */     return this.basicMetric;
/*     */   }
/*     */   
/*     */   public void getMinMax() {
/*  96 */     if (this.min == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 101 */       int n = getDimension();
/* 102 */       this.min = new RealVector(n, 0.0D);
/* 103 */       this.max = new RealVector(n, 1.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set getDefaultSet() {
/* 108 */     if (this.defaultSet == null) {
/* 109 */       getMinMax();
/* 110 */       int n = getDimension();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       this.max_sigma = n;
/*     */ 
/*     */       
/* 120 */       this.max_sigma /= 40.0D;
/*     */       
/* 122 */       this.min_sigma = this.max_sigma / 100.0D;
/* 123 */       System.out.println("min sigma=" + this.min_sigma);
/* 124 */       this.defaultSet = (Set)new SqrGaussianSet("cluster", this.max_sigma / 10.0D);
/*     */     } 
/* 126 */     return this.defaultSet;
/*     */   }
/*     */   
/*     */   public Weight getWeight() {
/* 130 */     return (Weight)new WeightMembership(this.T, this.binarized);
/*     */   }
/*     */   
/*     */   public Fitness getFitness() {
/* 134 */     return (Fitness)new ClusterExpFitness(this.data, getMetric(), getWeight());
/*     */   }
/*     */   
/*     */   public Genotype getBasicGenotype() {
/* 138 */     getMinMax();
/* 139 */     int n = getDimension();
/* 140 */     GenomeLimits limits = new GenomeLimits(n);
/* 141 */     return (Genotype)new RealVectorGenotype(limits, new RealVectorLimits(this.min, this.max));
/*     */   }
/*     */   
/*     */   public Genotype getGenotype() {
/* 145 */     return (Genotype)new HybridGenotype(getDefaultSet(), getBasicGenotype());
/*     */   }
/*     */   
/*     */   public Phenotype getPhenotype() {
/* 149 */     return (Phenotype)new HybridPhenotype(getDefaultSet());
/*     */   }
/*     */   
/*     */   public Operator[] getOperators() {
/* 153 */     Operator[] opers = new Operator[2];
/* 154 */     getEnvironment();
/* 155 */     opers[0] = (Operator)new HybridGaussianMutation(this.env, new RealVectorLimits(this.min, this.max));
/*     */ 
/*     */     
/* 158 */     opers[1] = (Operator)new HybridRestrictedMating(this.env, (ArityTwo)new LinearXOverPerDimension(this.env), this.sigmaFactor, getBasicMetric());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     return opers;
/*     */   }
/*     */   
/*     */   public Transformation getTransformation() {
/* 171 */     return (Transformation)new ECSAGO(getOperators(), (Selection)new DCHaea(getEnvironment(), getMetric()), getClusterTunning());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ClusterTunning getClusterTunning() {
/* 177 */     if (this.clusterTunning == null) {
/* 178 */       getDefaultSet();
/* 179 */       this.clusterTunning = (ClusterTunning)new SigmaTunning(this.data, getWeight(), getMetric(), this.min_sigma, this.max_sigma);
/*     */     } 
/*     */     
/* 182 */     return this.clusterTunning;
/*     */   }
/*     */ 
/*     */   
/*     */   public Extraction getFitnessExtraction() {
/* 187 */     this.minFitnessExt = this.data.samples() / this.max_sigma / this.sigmaFactor / 4.0D;
/* 188 */     return (Extraction)new FitnessExtraction(this.minFitnessExt, this.typeExt);
/*     */   }
/*     */   
/*     */   public Extraction getNicheExtraction() {
/* 192 */     return (Extraction)new NicheExtraction(new SameNiche(this.sigmaFactor, 3, getMetric()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Extraction getExtraction() {
/* 200 */     return (Extraction)new ComposeExtraction(getFitnessExtraction(), (Extraction)new ComposeExtraction(getNicheExtraction(), (Extraction)new MinExtractionError(this.data, this.T, getMetric())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Refinement getRefinement() {
/* 208 */     return (Refinement)new MDE(getMetric(), this.data.data, this.sigmaFactor, this.ref_iter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 215 */     String path_file = "c:/cygwin/home/eleon/DataSets/synthetic4/";
/* 216 */     String file = "cluster-10-1.txt";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     String output_path = "results/";
/* 223 */     int runs = 1;
/* 224 */     FileDataSource source = new FileDataSource(path_file + file, -1);
/* 225 */     UNCTestOne test = new UNCTestOne(source.optimize(), 30, 100);
/*     */     
/* 227 */     UNCFileTracer t = new UNCFileTracer(new int[] { 0, 10, 20, 30, 40, 50 }, new int[] { 5, 10 }, output_path + "/" + file);
/* 228 */     test.addTracer((Tracer)t);
/*     */     
/* 230 */     for (int r = 0; r < runs; r++) {
/* 231 */       t.setRun(r);
/* 232 */       test.run();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\test\UNCTestOne.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package unc.utils.test;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import jml.basics.Tracer;
/*     */ import jml.data.DataSource;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.EvolutionaryAlgorithm;
/*     */ import jml.evolution.Fitness;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Phenotype;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.util.ForLoopCondition;
/*     */ import jml.util.Predicate;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.UNC;
/*     */ import unc.evolution.fitness.ClusterFitness;
/*     */ import unc.extraction.Extraction;
/*     */ import unc.refinement.ClusterTunning;
/*     */ import unc.refinement.Refinement;
/*     */ import unc.utils.DistanceSize;
/*     */ import unc.utils.PopulationLoader;
/*     */ import unc.utils.distances.ClusterDistance;
/*     */ import unc.utils.tracers.UNCTracer;
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
/*     */ public abstract class UNCTest
/*     */   extends UNC
/*     */ {
/*     */   protected DistanceSize data;
/*  41 */   protected Environment env = null;
/*  42 */   protected QuasiMetric basicMetric = null;
/*  43 */   protected Set defaultSet = null;
/*  44 */   protected Fitness fitness = null;
/*  45 */   protected Genotype genotype = null;
/*  46 */   protected Phenotype phenotype = null;
/*  47 */   protected Transformation transformation = null;
/*  48 */   protected ClusterTunning clusterTunning = null;
/*  49 */   protected int MAX_ITER = 0;
/*  50 */   protected int POP_SIZE = 0;
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected Population extra_population = null;
/*     */   
/*     */   protected UNCTest() {}
/*     */   
/*     */   public UNCTest(DataSource _data, int _MAX_ITER, int _POP_SIZE) {
/*  59 */     this(new DistanceSize(_data), _MAX_ITER, _POP_SIZE);
/*     */   } public abstract QuasiMetric getBasicMetric(); public abstract Set getDefaultSet(); public abstract Fitness getFitness(); public abstract Genotype getGenotype();
/*     */   public abstract Phenotype getPhenotype();
/*     */   public UNCTest(DistanceSize _data, int _MAX_ITER, int _POP_SIZE) {
/*  63 */     this.data = _data;
/*  64 */     this.MAX_ITER = _MAX_ITER;
/*  65 */     this.POP_SIZE = _POP_SIZE;
/*  66 */     create();
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract Transformation getTransformation();
/*     */ 
/*     */   
/*     */   public abstract ClusterTunning getClusterTunning();
/*     */   
/*     */   public abstract Extraction getExtraction();
/*     */   
/*     */   public abstract Refinement getRefinement();
/*     */   
/*     */   public void setDataSet(DistanceSize _data) {
/*  80 */     this.data = _data;
/*  81 */     if (this.env != null) {
/*  82 */       ClusterFitness f = (ClusterFitness)this.env.getFitness();
/*  83 */       f.setData(this.data);
/*     */     } 
/*     */   }
/*     */   public DistanceSize getData() {
/*  87 */     return this.data;
/*     */   }
/*     */   public QuasiMetric getMetric() {
/*  90 */     return (QuasiMetric)new ClusterDistance(getBasicMetric());
/*     */   }
/*     */   
/*     */   public Environment getEnvironment() {
/*  94 */     if (this.env == null) {
/*  95 */       this.fitness = getFitness();
/*  96 */       this.genotype = getGenotype();
/*  97 */       this.phenotype = getPhenotype();
/*  98 */       this.env = new Environment(this.genotype, this.phenotype, this.fitness);
/*     */     } 
/* 100 */     return this.env;
/*     */   }
/*     */   
/*     */   public Predicate getCondition() {
/* 104 */     return (Predicate)new ForLoopCondition(this.MAX_ITER);
/*     */   }
/*     */   
/*     */   public Population getPopulation(Environment env) {
/* 108 */     Population population = PopulationLoader.create(env, this.data.data, this.POP_SIZE, getMetric());
/* 109 */     if (this.extra_population != null) {
/* 110 */       population = PopulationLoader.merge(population, this.extra_population);
/*     */     }
/* 112 */     return population;
/*     */   }
/*     */   
/*     */   public EvolutionaryAlgorithm getEA() {
/* 116 */     if (this.EA == null) {
/* 117 */       this.env = getEnvironment();
/*     */       
/* 119 */       Population p = getPopulation(this.env);
/* 120 */       this.transformation = getTransformation();
/* 121 */       Predicate c = getCondition();
/*     */       
/* 123 */       this.EA = new EvolutionaryAlgorithm(p, this.transformation, c);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return this.EA;
/*     */   }
/*     */   
/*     */   public void create() {
/* 132 */     this.EA = getEA();
/* 133 */     this.extraction = getExtraction();
/* 134 */     this.refinement = getRefinement();
/*     */   }
/*     */   
/*     */   public void run() {
/* 138 */     if (this.tracer != null) {
/* 139 */       Enumeration iter = this.tracer.elements();
/* 140 */       while (iter.hasMoreElements()) {
/* 141 */         Tracer t = iter.nextElement();
/* 142 */         if (t instanceof UNCTracer) {
/* 143 */           ((UNCTracer)t).reset();
/*     */         }
/*     */       } 
/*     */     } 
/* 147 */     super.run();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\test\UNCTest.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
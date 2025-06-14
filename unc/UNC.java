/*     */ package unc;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import jml.basics.Algorithm;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.PartitionDataSource;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.EvolutionaryAlgorithm;
/*     */ import jml.evolution.Population;
/*     */ import unc.evolution.fitness.ClusterFitness;
/*     */ import unc.extraction.ComposeExtraction;
/*     */ import unc.extraction.Extraction;
/*     */ import unc.extraction.FitnessExtraction;
/*     */ import unc.refinement.Refinement;
/*     */ import unc.utils.DistanceSize;
/*     */ import unc.utils.PopulationLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UNC
/*     */   extends Algorithm
/*     */ {
/*  25 */   protected EvolutionaryAlgorithm EA = null;
/*  26 */   protected Extraction extraction = null;
/*  27 */   protected Refinement refinement = null;
/*  28 */   protected Prototype prototype = null;
/*     */ 
/*     */   
/*  31 */   protected IncrementalInfo incremental = null;
/*     */   
/*     */   protected UNC() {}
/*     */   
/*     */   public UNC(EvolutionaryAlgorithm _EA, Extraction _extraction, Refinement _refinement) {
/*  36 */     this.EA = _EA;
/*  37 */     this.extraction = _extraction;
/*  38 */     this.refinement = _refinement;
/*     */   }
/*     */ 
/*     */   
/*     */   public UNC(EvolutionaryAlgorithm _EA, Extraction _extraction, Refinement _refinement, IncrementalInfo _incremental) {
/*  43 */     this.EA = _EA;
/*  44 */     this.extraction = _extraction;
/*  45 */     this.refinement = _refinement;
/*  46 */     this.incremental = _incremental;
/*     */   }
/*     */   
/*     */   public Prototype evolve() {
/*  50 */     this.EA.tracer = this.tracer;
/*  51 */     this.EA.run();
/*  52 */     Population p = this.EA.getPopulation();
/*  53 */     return PopulationLoader.generate(p);
/*     */   }
/*     */   
/*     */   public Prototype extract(Prototype prot) {
/*  57 */     if (this.extraction != null) {
/*  58 */       prot = this.extraction.apply(prot);
/*  59 */       addToTrace(prot);
/*     */     } 
/*  61 */     return prot;
/*     */   }
/*     */   
/*     */   public Prototype refine(Prototype prot) {
/*  65 */     if (this.refinement != null) {
/*  66 */       this.refinement.tracer = this.tracer;
/*  67 */       prot = this.refinement.apply(prot);
/*     */     } 
/*  69 */     return prot;
/*     */   }
/*     */   
/*     */   public void setClusterSize(DistanceSize data) {
/*  73 */     Enumeration iter = this.prototype.elements();
/*  74 */     while (iter.hasMoreElements()) {
/*  75 */       Cluster c = iter.nextElement();
/*  76 */       data.calculate(c, this.incremental.metric);
/*  77 */       this.incremental.weight.apply(data.getDistance(), data.getSize(), data.getSet(), c.getSet());
/*  78 */       c.size = this.incremental.weight.sumWeight * this.incremental.memoryFactor;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected double setFitnessExtValue(Extraction ext, double val) {
/*  84 */     double x = 0.0D;
/*  85 */     if (ext instanceof FitnessExtraction) {
/*  86 */       FitnessExtraction fext = (FitnessExtraction)ext;
/*  87 */       x = fext.threshold;
/*  88 */       fext.threshold = val;
/*     */     }
/*  90 */     else if (ext instanceof ComposeExtraction) {
/*  91 */       x = setFitnessExtValue(((ComposeExtraction)ext).a, val);
/*  92 */       double y = setFitnessExtValue(((ComposeExtraction)ext).b, val);
/*  93 */       x = Math.max(x, y);
/*     */     } 
/*     */     
/*  96 */     return x;
/*     */   }
/*     */   
/*     */   public void run() {
/* 100 */     if (this.incremental != null) {
/* 101 */       double fitext = setFitnessExtValue(this.extraction, 0.0D);
/* 102 */       setFitnessExtValue(this.extraction, fitext / 2.0D);
/* 103 */       this.EA.initializePopulation(false);
/* 104 */       int n = this.incremental.partition.size();
/* 105 */       Population pop = this.EA.getPopulation();
/* 106 */       int POP_SIZE = pop.size();
/* 107 */       Environment env = pop.getEnvironment();
/* 108 */       ClusterFitness fitness = (ClusterFitness)env.getFitness();
/* 109 */       DistanceSize ds = fitness.getData();
/* 110 */       DataSource data = ds.data;
/* 111 */       for (int i = 0; i < n; i++) {
/* 112 */         addToTrace(new Integer(i));
/* 113 */         PartitionDataSource p_data = new PartitionDataSource(data, this.incremental.partition, i, true);
/* 114 */         ds.set((DataSource)p_data, this.prototype);
/*     */         
/* 116 */         addToTrace(p_data);
/* 117 */         pop = PopulationLoader.create(env, (DataSource)p_data, POP_SIZE, this.incremental.metric);
/* 118 */         if (this.prototype != null) {
/* 119 */           Population extra = PopulationLoader.create(env, this.prototype);
/* 120 */           pop = PopulationLoader.merge(pop, extra);
/*     */         } 
/* 122 */         this.EA.setPopulation(pop);
/* 123 */         this.EA.init();
/* 124 */         this.prototype = extract(evolve());
/* 125 */         setClusterSize(ds);
/*     */       } 
/* 127 */       setFitnessExtValue(this.extraction, fitext);
/* 128 */       this.prototype = extract(this.prototype);
/*     */     } else {
/*     */       
/* 131 */       this.prototype = refine(extract(evolve()));
/*     */     } 
/*     */   }
/*     */   public Object output() {
/* 135 */     return this.prototype;
/*     */   } public EvolutionaryAlgorithm getEA() {
/* 137 */     return this.EA;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\UNC.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
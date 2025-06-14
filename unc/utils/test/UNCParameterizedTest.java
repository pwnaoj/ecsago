/*     */ package unc.utils.test;
/*     */ import java.io.BufferedReader;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.evolution.GenomeLimits;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Phenotype;
/*     */ import jml.evolution.Transformation;
/*     */ import jml.evolution.binary.operators.SingleBitMutation;
/*     */ import jml.evolution.operators.ArityOne;
/*     */ import jml.evolution.operators.ArityTwo;
/*     */ import jml.evolution.real.RealVectorLimits;
/*     */ import jml.evolution.util.BinaryToRealVectorPhenotype;
/*     */ import jml.random.Partition;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.evolution.encoding.hybrid.HybridArityOne;
/*     */ import unc.evolution.encoding.hybrid.HybridArityTwo;
/*     */ import unc.evolution.encoding.sparse.binary.operators.SparseBitAdd;
/*     */ import unc.evolution.encoding.sparse.binary.operators.SparseBitDel;
/*     */ import unc.evolution.fitness.Weight;
/*     */ import unc.extraction.Extraction;
/*     */ import unc.refinement.ClusterTunning;
/*     */ import unc.utils.ParameterParser;
/*     */ import unc.utils.tracers.UNCFileTracer;
/*     */ 
/*     */ public class UNCParameterizedTest extends UNCTest {
/*     */   protected boolean sparse;
/*     */   protected boolean real;
/*     */   protected boolean realEncoding;
/*     */   protected boolean gray;
/*     */   protected Hashtable table;
/*     */   protected double data_set_sigma;
/*     */   protected double max_sigma;
/*     */   protected double min_sigma;
/*     */   protected RealVector min;
/*     */   protected RealVector max;
/*     */   public static final String DATA = "data";
/*     */   public static final String POP = "pop_size";
/*     */   public static final String ITER = "iterations";
/*     */   public static final String OPER = "operator_";
/*     */   public static final String NAME = "name";
/*     */   public static final String SIGMA = "sigma";
/*     */   public static final String SIGMA_FACTOR = "K";
/*     */   public static final String THRESHOLD = "threshold";
/*     */   public static final String WEIGHT = "weight";
/*     */   public static final String XOVER = "xover";
/*     */   public static final String MUTATION = "mutation";
/*     */   public static final String METRIC = "metric";
/*     */   public static final String MAX_LENGTH = "max_length";
/*     */   public static final String MIN_LENGTH = "min_length";
/*     */   public static final String BINARIZED = "binarized";
/*     */   
/*     */   public UNCParameterizedTest(Hashtable _table) {
/*     */     FileDataSource source;
/*  58 */     this.sparse = false;
/*  59 */     this.real = false;
/*  60 */     this.realEncoding = true;
/*  61 */     this.gray = false;
/*  62 */     this.table = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.min = null;
/*  69 */     this.max = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     this.table = _table;
/* 116 */     Object dataObj = this.table.get("data");
/*     */     
/* 118 */     if (dataObj instanceof String) {
/* 119 */       source = new FileDataSource((String)dataObj, -1);
/*     */     } else {
/* 121 */       source = new FileDataSource((InputStream)dataObj, -1);
/*     */     } 
/* 123 */     this.sparse = source.sparse();
/* 124 */     this.real = source.real();
/* 125 */     this.data = new DistanceSize(source.optimize());
/* 126 */     this.POP_SIZE = Integer.parseInt((String)this.table.get("pop_size"));
/* 127 */     this.MAX_ITER = Integer.parseInt((String)this.table.get("iterations"));
/* 128 */     create();
/*     */   }
/*     */   public static final String CARDINALITY = "cardinality"; public static final String FITNESS = "fitness"; public static final String DEFAULT_SET = "default_set"; public static final String MAX_SIGMA = "max_sigma"; public static final String MIN_SIGMA = "min_sigma"; public static final String INIT_SIGMA = "initial_sigma"; public static final String TUNNING = "tunning"; public static final String TYPE = "type"; public static final String EXTRACTION = "extraction"; public static final String NICHE = "niche"; public static final String COMPOSE = "compose"; public static final String MINERROR = "min_error"; public static final String REFINEMENT = "refinement"; public static final String INCREMENTAL = "incremental"; public static final String GROUPS = "groups"; public static final String RANDOMIZED = "randomized"; public static final String MEMORY_FACTOR = "memory_factor"; public static final String EVOLUTION = "evolution"; public static final String ENCODING = "encoding"; public static final String HAEA = "haea"; public static final String UNC = "unc"; public static final String ECSAGO = "ecsago"; public static final String TRANSFORMATION = "transformation"; public static final String PROBABILITY = "probability"; public static final String RUNNER = "runner"; public static final String TRACER = "tracer";
/*     */   public void error(String text) {
/* 132 */     System.out.println("Option " + text + " Not implemented yet");
/*     */   }
/*     */   
/*     */   public void setDataSet(DistanceSize _data) {
/* 136 */     super.setDataSet(_data);
/* 137 */     getClusterTunning();
/* 138 */     if (this.clusterTunning != null && this.clusterTunning instanceof SigmaTunning) {
/* 139 */       ((SigmaTunning)this.clusterTunning).setData(this.data);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 144 */     Object obj = this.data.data.get(0).get();
/* 145 */     if (this.real) {
/* 146 */       return ((RealVector)obj).dimension();
/*     */     }
/* 148 */     if (this.sparse) {
/* 149 */       return ((SparceBitArray)obj).size();
/*     */     }
/* 151 */     return ((BitArray)obj).size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QuasiMetric getBasicMetric() {
/* 157 */     if (this.basicMetric == null) {
/* 158 */       String name = (String)this.table.get("metric");
/* 159 */       if (this.real)
/* 160 */       { if (name.equals("Euclidean")) { this.basicMetric = (QuasiMetric)new Minkowski(2.0D); }
/* 161 */         else if (name.equals("Minkowski")) { this.basicMetric = (QuasiMetric)new Minkowski(2.0D); }
/* 162 */         else if (name.equals("Jaccard")) { this.basicMetric = (QuasiMetric)new Jaccard(); }
/* 163 */         else if (name.equals("Cosine")) { this.basicMetric = (QuasiMetric)new Cosine(); }
/* 164 */         else { error(name); }
/*     */          }
/* 166 */       else if (this.sparse)
/* 167 */       { if (name.equals("Jaccard")) { this.basicMetric = (QuasiMetric)new SparseBinaryJaccard(); }
/* 168 */         else if (name.equals("Cosine")) { this.basicMetric = (QuasiMetric)new SparseBinaryCosine(); }
/*     */          }
/* 170 */       else if (name.equals("Jaccard")) { this.basicMetric = (QuasiMetric)new BinaryJaccard(); }
/* 171 */       else if (name.equals("Cosine")) { this.basicMetric = (QuasiMetric)new BinaryCosine(); }
/*     */     
/*     */     } 
/*     */     
/* 175 */     return this.basicMetric;
/*     */   }
/*     */   
/*     */   public void getMinMax() {
/* 179 */     if (this.min == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 184 */       int n = getDimension();
/* 185 */       this.min = new RealVector(n, 0.0D);
/* 186 */       this.max = new RealVector(n, 1.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set getDefaultSet() {
/* 191 */     if (this.defaultSet == null) {
/* 192 */       getMinMax();
/* 193 */       int n = getDimension();
/* 194 */       Hashtable t = (Hashtable)this.table.get("default_set");
/* 195 */       this.data_set_sigma = getBasicMetric().max_0_1(n) / 2.0D;
/* 196 */       this.max_sigma = this.data_set_sigma * Double.parseDouble((String)t.get("max_sigma"));
/* 197 */       this.min_sigma = this.data_set_sigma * Double.parseDouble((String)t.get("min_sigma"));
/* 198 */       double init_sigma = this.data_set_sigma * Double.parseDouble((String)t.get("initial_sigma"));
/*     */ 
/*     */ 
/*     */       
/* 202 */       this.defaultSet = (Set)new SqrGaussianSet("cluster", init_sigma);
/*     */     } 
/* 204 */     return this.defaultSet;
/*     */   }
/*     */   
/*     */   public Weight getWeight() {
/* 208 */     Hashtable t = (Hashtable)this.table.get("weight");
/* 209 */     String name = (String)t.get("name");
/* 210 */     boolean binarized = Boolean.valueOf((String)t.get("binarized")).booleanValue();
/*     */     
/* 212 */     if (name.equals("Membership")) {
/* 213 */       double T = Double.parseDouble((String)t.get("threshold"));
/* 214 */       return (Weight)new WeightMembership(T, binarized);
/*     */     } 
/*     */     
/* 217 */     if (name.equals("Cardinality")) {
/* 218 */       int C = Integer.parseInt((String)t.get("cardinality"));
/* 219 */       return (Weight)new WeightCardinality(C);
/*     */     } 
/*     */     
/* 222 */     if (name.equals("Distance")) {
/* 223 */       double T = Double.parseDouble((String)t.get("threshold"));
/* 224 */       return (Weight)new WeightDistance(T, binarized);
/*     */     } 
/*     */     
/* 227 */     if (name.equals("CardinalityDistance")) {
/* 228 */       int C = Integer.parseInt((String)t.get("cardinality"));
/* 229 */       double T = Double.parseDouble((String)t.get("threshold"));
/* 230 */       return (Weight)new WeightCardinalityDistance(C, (Weight)new WeightDistance(T, binarized));
/*     */     } 
/*     */     
/* 233 */     error(name);
/* 234 */     return null;
/*     */   }
/*     */   
/*     */   public Fitness getFitness() {
/* 238 */     if (this.fitness == null) {
/* 239 */       Hashtable tf = (Hashtable)this.table.get("fitness");
/* 240 */       String name = (String)tf.get("name");
/* 241 */       if (name.equals("ClusterExp")) {
/* 242 */         this.fitness = (Fitness)new ClusterExpFitness(this.data, getMetric(), getWeight());
/*     */       } else {
/* 244 */         error(name);
/*     */       } 
/*     */     } 
/* 247 */     return this.fitness;
/*     */   }
/*     */   public Genotype getBasicGenotype() {
/*     */     GenomeLimits limits;
/* 251 */     getMinMax();
/* 252 */     int n = getDimension();
/*     */     
/* 254 */     if (this.sparse) {
/* 255 */       int min = Integer.parseInt((String)this.table.get("min_length"));
/* 256 */       int max = Integer.parseInt((String)this.table.get("max_length"));
/* 257 */       limits = new GenomeLimits(min, max);
/*     */     } else {
/* 259 */       limits = new GenomeLimits(n);
/*     */     } 
/* 261 */     if (this.real) {
/* 262 */       String enc = (String)this.table.get("encoding");
/* 263 */       this.realEncoding = (enc == null || enc.equals("real"));
/* 264 */       if (this.realEncoding) {
/* 265 */         return (Genotype)new RealVectorGenotype(limits, new RealVectorLimits(this.min, this.max));
/*     */       }
/* 267 */       this.gray = enc.equals("gray");
/* 268 */       return (Genotype)new BinaryGenotype(new GenomeLimits(n, n, IntUtil.INTEGER_SIZE));
/*     */     } 
/*     */     
/* 271 */     if (this.sparse) {
/* 272 */       return (Genotype)new SparseBitArrayGenotype(n, limits);
/*     */     }
/* 274 */     error("FullBinary");
/* 275 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Genotype getGenotype() {
/* 281 */     if (this.genotype == null)
/* 282 */       this.genotype = (Genotype)new HybridGenotype(getDefaultSet(), getBasicGenotype()); 
/* 283 */     return this.genotype;
/*     */   }
/*     */   
/*     */   public Phenotype getPhenotype() {
/* 287 */     if (this.phenotype == null) {
/* 288 */       if (this.realEncoding) {
/* 289 */         this.phenotype = (Phenotype)new HybridPhenotype(getDefaultSet());
/*     */       } else {
/* 291 */         BinaryToRealVectorPhenotype binaryToRealVectorPhenotype = new BinaryToRealVectorPhenotype(IntUtil.INTEGER_SIZE, this.gray, null);
/*     */         
/* 293 */         this.phenotype = (Phenotype)new HybridPhenotype((Phenotype)binaryToRealVectorPhenotype, getDefaultSet());
/*     */       } 
/*     */     }
/* 296 */     return this.phenotype;
/*     */   }
/*     */   public Operator load_operator(Hashtable t) {
/*     */     HybridArityTwo hybridArityTwo;
/* 300 */     Operator oper = null;
/* 301 */     String name = (String)t.get("name");
/* 302 */     if (this.real) {
/* 303 */       RealVectorLimits limits = new RealVectorLimits(this.min, this.max);
/* 304 */       if (this.realEncoding) {
/* 305 */         if (name.equals("HybridGaussianMutation")) {
/* 306 */           return (Operator)new HybridGaussianMutation(this.env, limits);
/*     */         }
/* 308 */         if (name.equals("GaussianMutation")) {
/* 309 */           HybridArityOne hybridArityOne; double sigma = 0.1D; 
/* 310 */           try { sigma = Double.parseDouble((String)t.get("sigma")); } catch (Exception exc) {}
/* 311 */           GaussianMutation gaussianMutation = new GaussianMutation(this.env, limits, sigma);
/* 312 */           if (this.env.getGenotype() instanceof HybridGenotype) {
/* 313 */             hybridArityOne = new HybridArityOne(this.env, (ArityOne)gaussianMutation);
/*     */           }
/* 315 */           return (Operator)hybridArityOne;
/*     */         } 
/*     */         
/* 318 */         if (name.equals("HybridRestrictedMating")) {
/* 319 */           double K = Double.parseDouble((String)t.get("K"));
/* 320 */           oper = load_operator((Hashtable)t.get("xover"));
/* 321 */           ArityTwo arityTwo = ((HybridArityTwo)oper).oper;
/* 322 */           return (Operator)new HybridRestrictedMating(this.env, arityTwo, K, getBasicMetric());
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 327 */         if (name.equals("LinearXOverPerDimension") || name.equals("LCD")) {
/* 328 */           LinearXOverPerDimension linearXOverPerDimension = new LinearXOverPerDimension(this.env);
/* 329 */           if (this.env.getGenotype() instanceof HybridGenotype) {
/* 330 */             hybridArityTwo = new HybridArityTwo(this.env, (ArityTwo)linearXOverPerDimension);
/*     */           }
/* 332 */           return (Operator)hybridArityTwo;
/*     */         } 
/*     */         
/* 335 */         if (name.equals("LinearXOver") || name.equals("LC")) {
/* 336 */           LinearXOver linearXOver = new LinearXOver(this.env);
/* 337 */           if (this.env.getGenotype() instanceof HybridGenotype) {
/* 338 */             hybridArityTwo = new HybridArityTwo(this.env, (ArityTwo)linearXOver);
/*     */           }
/* 340 */           return (Operator)hybridArityTwo;
/*     */         } 
/*     */         
/* 343 */         error(name);
/*     */       } else {
/* 345 */         int bits = this.min.dimension() * IntUtil.INTEGER_SIZE;
/* 346 */         if (name.equals("BitMutation")) {
/* 347 */           HybridArityOne hybridArityOne; SingleBitMutation singleBitMutation = new SingleBitMutation(this.env);
/* 348 */           if (this.env.getGenotype() instanceof HybridGenotype) {
/* 349 */             hybridArityOne = new HybridArityOne(this.env, (ArityOne)singleBitMutation);
/*     */           }
/* 351 */           return (Operator)hybridArityOne;
/*     */         } 
/* 353 */         if (name.equals("BitXOver") || name.equals("SimpleXover")) {
/* 354 */           XOver xOver = new XOver(this.env);
/* 355 */           if (this.env.getGenotype() instanceof HybridGenotype) {
/* 356 */             hybridArityTwo = new HybridArityTwo(this.env, (ArityTwo)xOver);
/*     */           }
/* 358 */           return (Operator)hybridArityTwo;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 363 */     } else if (this.sparse) {
/* 364 */       if (name.equals("BitAdd")) {
/* 365 */         HybridArityOne hybridArityOne; int max = Integer.parseInt((String)t.get("max_length"));
/* 366 */         SparseBitAdd sparseBitAdd = new SparseBitAdd(this.env, max);
/* 367 */         if (this.env.getGenotype() instanceof HybridGenotype) {
/* 368 */           hybridArityOne = new HybridArityOne(this.env, (ArityOne)sparseBitAdd);
/*     */         }
/* 370 */         return (Operator)hybridArityOne;
/*     */       } 
/* 372 */       if (name.equals("BitDel")) {
/* 373 */         HybridArityOne hybridArityOne; int min = Integer.parseInt((String)t.get("min_length"));
/* 374 */         SparseBitDel sparseBitDel = new SparseBitDel(this.env, min);
/* 375 */         if (this.env.getGenotype() instanceof HybridGenotype) {
/* 376 */           hybridArityOne = new HybridArityOne(this.env, (ArityOne)sparseBitDel);
/*     */         }
/* 378 */         return (Operator)hybridArityOne;
/*     */       } 
/* 380 */       if (name.equals("BitMutation")) {
/* 381 */         HybridArityOne hybridArityOne; int min = Integer.parseInt((String)t.get("min_length"));
/* 382 */         int max = Integer.parseInt((String)t.get("max_length"));
/* 383 */         SparseBitMutation sparseBitMutation = new SparseBitMutation(this.env, min, max);
/* 384 */         if (this.env.getGenotype() instanceof HybridGenotype) {
/* 385 */           hybridArityOne = new HybridArityOne(this.env, (ArityOne)sparseBitMutation);
/*     */         }
/* 387 */         return (Operator)hybridArityOne;
/*     */       } 
/* 389 */       if (name.equals("BitGeneXOver")) {
/* 390 */         SparseBitGeneXOver sparseBitGeneXOver = new SparseBitGeneXOver(this.env);
/* 391 */         if (this.env.getGenotype() instanceof HybridGenotype) {
/* 392 */           hybridArityTwo = new HybridArityTwo(this.env, (ArityTwo)sparseBitGeneXOver);
/*     */         }
/* 394 */         return (Operator)hybridArityTwo;
/*     */       } 
/* 396 */       if (name.equals("BitXOver")) {
/* 397 */         SparseBitXOver sparseBitXOver = new SparseBitXOver(this.env);
/* 398 */         if (this.env.getGenotype() instanceof HybridGenotype) {
/* 399 */           hybridArityTwo = new HybridArityTwo(this.env, (ArityTwo)sparseBitXOver);
/*     */         }
/* 401 */         return (Operator)hybridArityTwo;
/*     */       } 
/*     */     } else {
/* 404 */       error("FullBinary Operator " + name);
/*     */     } 
/*     */     
/* 407 */     return (Operator)hybridArityTwo;
/*     */   }
/*     */   
/*     */   public ArityOne getMutation() {
/* 411 */     Hashtable t = (Hashtable)this.table.get("mutation");
/* 412 */     Operator o = load_operator(t);
/* 413 */     return (ArityOne)o;
/*     */   }
/*     */   
/*     */   public ArityTwo getXOver() {
/* 417 */     Hashtable t = (Hashtable)this.table.get("xover");
/* 418 */     Operator o = load_operator(t);
/* 419 */     return (ArityTwo)o;
/*     */   }
/*     */   
/*     */   public Operator[] getOperators() {
/* 423 */     getEnvironment();
/* 424 */     int i = 0;
/* 425 */     Vector v = new Vector();
/* 426 */     String oper = "operator_" + i;
/* 427 */     while (this.table.get(oper) != null) {
/* 428 */       Hashtable t = (Hashtable)this.table.get(oper);
/* 429 */       Operator o = load_operator(t);
/* 430 */       v.add(o);
/* 431 */       i++;
/* 432 */       oper = "operator_" + i;
/*     */     } 
/* 434 */     Operator[] opers = new Operator[v.size()];
/* 435 */     for (i = 0; i < v.size(); i++) {
/* 436 */       opers[i] = v.get(i);
/*     */     }
/*     */     
/* 439 */     return opers;
/*     */   }
/*     */ 
/*     */   
/*     */   public Transformation getTransformation() {
/* 444 */     if (this.transformation == null) {
/* 445 */       Environment env = getEnvironment();
/* 446 */       QuasiMetric metric = getMetric();
/* 447 */       ClusterTunning tunning = getClusterTunning();
/* 448 */       Hashtable t = (Hashtable)this.table.get("transformation");
/* 449 */       String name = (String)t.get("name");
/* 450 */       Hashtable temp = this.table;
/* 451 */       this.table = t;
/* 452 */       if (name.equals("ecsago")) {
/*     */         
/* 454 */         this.transformation = (Transformation)new ECSAGO(getOperators(), (Selection)new DCHaea(env, metric), tunning);
/*     */       
/*     */       }
/* 457 */       else if (name.equals("haea")) {
/*     */         
/* 459 */         HAEA embedded = new HAEA(getOperators(), (Selection)new DCHaea(env, metric));
/*     */         
/* 461 */         this.transformation = (Transformation)new UNCTransformation((Transformation)embedded, tunning);
/*     */       } else {
/*     */         
/* 464 */         double mut_prob = Double.parseDouble((String)t.get("mutation_probability"));
/* 465 */         double xov_prob = Double.parseDouble((String)t.get("xover_probability"));
/* 466 */         double K = Double.parseDouble((String)t.get("sigma"));
/* 467 */         OriginalMatingRestriction rest = new OriginalMatingRestriction(K, 3, 0.1D, getMetric());
/*     */         
/* 469 */         this.transformation = (Transformation)new ClassicUNC(metric, getMutation(), getXOver(), mut_prob, xov_prob, tunning, (Restriction)rest);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 474 */       this.table = temp;
/*     */     } 
/* 476 */     return this.transformation;
/*     */   }
/*     */   
/*     */   public EvolutionaryAlgorithm getEA() {
/* 480 */     if (this.EA == null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 485 */       this.EA = super.getEA();
/*     */     }
/*     */     
/* 488 */     return this.EA;
/*     */   }
/*     */   
/*     */   public ClusterTunning getClusterTunning() {
/* 492 */     if (this.clusterTunning == null) {
/* 493 */       getDefaultSet();
/* 494 */       Object obj = this.table.get("tunning");
/* 495 */       if (obj != null) {
/* 496 */         String tune = (String)obj;
/* 497 */         if (tune.equals("Sigma")) {
/* 498 */           this.clusterTunning = (ClusterTunning)new SigmaTunning(this.data, getWeight(), getMetric(), this.min_sigma, this.max_sigma);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 506 */     return this.clusterTunning;
/*     */   }
/*     */   public Extraction getFitnessExtraction(Hashtable t) {
/*     */     int type;
/* 510 */     String name = (String)t.get("type");
/* 511 */     double threshold = Double.parseDouble((String)t.get("threshold"));
/*     */     
/* 513 */     if (name.equals("absolute")) { type = 0; }
/* 514 */     else if (name.equals("average")) { type = 1; }
/* 515 */     else if (name.equals("maximum")) { type = 2; }
/* 516 */     else if (name.equals("median")) { type = 3; }
/* 517 */     else if (name.equals("density")) { type = 4; } else { error(name); type = 0; }
/* 518 */      if (type == 4) {
/* 519 */       threshold /= this.data_set_sigma;
/* 520 */       return (Extraction)new FitnessExtraction(threshold, this.data);
/*     */     } 
/* 522 */     return (Extraction)new FitnessExtraction(threshold, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public Extraction getNicheExtraction(Hashtable t) {
/* 527 */     double K = Double.parseDouble((String)t.get("K"));
/* 528 */     SameNiche rest = new SameNiche(K, 3, getMetric());
/* 529 */     return (Extraction)new NicheExtraction(rest);
/*     */   }
/*     */   
/*     */   public Extraction getComposeExtraction(Hashtable t) {
/* 533 */     Hashtable t1 = (Hashtable)t.get("first");
/* 534 */     Hashtable t2 = (Hashtable)t.get("second");
/* 535 */     Extraction first = getExtraction(t1);
/* 536 */     Extraction second = getExtraction(t2);
/* 537 */     return (Extraction)new ComposeExtraction(first, second);
/*     */   }
/*     */   
/*     */   public Extraction getMinErrorExtraction(Hashtable t) {
/* 541 */     double threshold = Integer.parseInt((String)t.get("threshold"));
/* 542 */     return (Extraction)new MinExtractionError(this.data, threshold, getMetric());
/*     */   }
/*     */   
/*     */   public Extraction getExtraction(Hashtable t) {
/* 546 */     String name = (String)t.get("name");
/* 547 */     if (name.equals("fitness")) return getFitnessExtraction(t); 
/* 548 */     if (name.equals("niche")) return getNicheExtraction(t); 
/* 549 */     if (name.equals("compose")) return getComposeExtraction(t); 
/* 550 */     if (name.equals("min_error")) return getMinErrorExtraction(t); 
/* 551 */     error(name);
/* 552 */     return null;
/*     */   }
/*     */   
/*     */   public Extraction getExtraction() {
/* 556 */     if (this.extraction == null)
/* 557 */       this.extraction = getExtraction((Hashtable)this.table.get("extraction")); 
/* 558 */     return this.extraction;
/*     */   }
/*     */   
/*     */   public Refinement getRefinement() {
/* 562 */     if (this.refinement == null) {
/* 563 */       Object obj = this.table.get("refinement");
/* 564 */       if (obj != null) {
/* 565 */         Hashtable t = (Hashtable)obj;
/* 566 */         String name = (String)t.get("name");
/* 567 */         if (name.equals("MDE")) {
/* 568 */           double K = Double.parseDouble((String)t.get("K"));
/* 569 */           int iters = Integer.parseInt((String)t.get("iterations"));
/* 570 */           this.refinement = (Refinement)new MDE(getMetric(), this.data.data, K, iters);
/*     */         } else {
/* 572 */           error(name);
/*     */         } 
/*     */       } 
/* 575 */     }  return this.refinement;
/*     */   }
/*     */   
/*     */   public void create() {
/* 579 */     Object obj = this.table.get("incremental");
/* 580 */     if (obj != null) {
/* 581 */       Partition partition; Hashtable t = (Hashtable)obj;
/* 582 */       int[] groups = ParameterParser.get(t, "groups");
/* 583 */       double memFactor = ParameterParser.getDouble(t, "memory_factor");
/* 584 */       boolean randomized = ParameterParser.getBoolean(t, "randomized");
/*     */       
/* 586 */       if (groups.length == 1) {
/* 587 */         partition = new Partition(this.data.data.size(), groups[0], randomized);
/*     */       } else {
/* 589 */         partition = new Partition(groups, randomized);
/*     */       } 
/* 591 */       this.EA = getEA();
/* 592 */       this.extraction = getExtraction();
/* 593 */       this.refinement = null;
/* 594 */       this.incremental = new IncrementalInfo(partition, memFactor, getMetric(), getWeight());
/*     */     } else {
/* 596 */       super.create();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 603 */     Hashtable table = ParameterParser.get(argv[0]);
/* 604 */     if (table == null) {
/* 605 */       String file = argv[0];
/* 606 */       String cmd = null;
/*     */       try {
/* 608 */         BufferedReader is = new BufferedReader(new FileReader(file));
/* 609 */         StringBuffer sb = new StringBuffer();
/* 610 */         String line = is.readLine();
/* 611 */         while (line != null) {
/* 612 */           sb.append(line);
/* 613 */           line = is.readLine();
/*     */         } 
/* 615 */         is.close();
/* 616 */         cmd = sb.toString();
/* 617 */       } catch (Exception e) {
/* 618 */         e.printStackTrace();
/*     */       } 
/* 620 */       table = ParameterParser.get(cmd);
/*     */     } 
/*     */ 
/*     */     
/* 624 */     Hashtable trun = ParameterParser.getHash(table, "runner");
/* 625 */     Hashtable ttrace = ParameterParser.getHash(table, "tracer");
/*     */     
/* 627 */     System.out.println("--------------------------------");
/* 628 */     System.out.println(table);
/* 629 */     System.out.println("--------------------------------");
/*     */     
/* 631 */     UNCParameterizedTest test = new UNCParameterizedTest(trun);
/*     */ 
/*     */     
/* 634 */     UNCFileTracer t = null;
/* 635 */     if (ttrace != null) {
/* 636 */       t = new UNCFileTracer(ttrace);
/* 637 */       test.addTracer((Tracer)t);
/*     */     } 
/*     */     
/* 640 */     int runs = 1;
/* 641 */     for (int r = 0; r < runs; r++) {
/* 642 */       if (t != null) t.setRun(r); 
/* 643 */       test.run();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\test\UNCParameterizedTest.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package unc.refinement;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.parser.SimpleStreamTokenizer;
/*     */ import jml.parser.Token;
/*     */ import jml.util.quasimetric.Minkowski;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.Cluster;
/*     */ import unc.Prototype;
/*     */ import unc.sets.SqrGaussianSet;
/*     */ import unc.utils.GaussianSetGenerator;
/*     */ import unc.utils.PopulationLoader;
/*     */ import unc.utils.SetGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MDE
/*     */   extends Refinement
/*     */ {
/*     */   protected QuasiMetric metric;
/*     */   public Vector predicted_centers;
/*     */   public Vector scaled_centers;
/*     */   public double[] sigma;
/*     */   protected double[] min_weight;
/*     */   protected double[] sum_weights;
/*     */   protected double[][] sum_weights_feat;
/*     */   protected double[] sum_weights_dist_sq;
/*     */   protected double[] sum_weights_dist;
/*  43 */   protected double sigmaFactor = 13.8D;
/*     */   
/*     */   protected int numClus;
/*     */   
/*     */   protected int numDim;
/*     */   
/*     */   int[] closestCenters;
/*     */   int[] numVectPerCluster;
/*     */   double[] minDist;
/*     */   double[] memship;
/*     */   double MaxVar;
/*     */   protected DataSource data;
/*     */   protected int ITERS;
/*     */   
/*     */   public MDE(QuasiMetric _metric, DataSource _data, double K, int _ITERS) {
/*  58 */     this.metric = _metric;
/*  59 */     this.sigmaFactor = K;
/*  60 */     this.data = _data;
/*  61 */     this.ITERS = _ITERS;
/*     */   }
/*     */   
/*     */   public MDE(QuasiMetric _metric, Vector predicted, double K) {
/*  65 */     this.metric = _metric;
/*  66 */     this.sigmaFactor = K;
/*  67 */     this.numClus = 0;
/*  68 */     this.predicted_centers = new Vector();
/*  69 */     Enumeration iter = predicted.elements();
/*  70 */     this.sigma = new double[predicted.size()];
/*  71 */     while (iter.hasMoreElements()) {
/*  72 */       Cluster ind = iter.nextElement();
/*  73 */       Object r = ind.getCenter();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       this.predicted_centers.add(Cloner.clone(r));
/*     */       
/*  80 */       this.numClus++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPredictedCenters(SimpleStreamTokenizer tok) {
/*  86 */     Token t = tok.nextToken();
/*  87 */     int n = (int)t.nval;
/*  88 */     this.numClus = n;
/*  89 */     this.predicted_centers = new Vector();
/*  90 */     this.sigma = new double[n];
/*  91 */     for (int i = 0; i < n; i++) {
/*     */ 
/*     */       
/*  94 */       t = tok.nextToken();
/*     */ 
/*     */       
/*  97 */       this.sigma[i] = t.nval;
/*  98 */       tok.nextToken();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readPredictedCenters(String fileName) {
/*     */     try {
/* 104 */       FileReader reader = new FileReader(fileName);
/* 105 */       SimpleStreamTokenizer tok = new SimpleStreamTokenizer(reader);
/* 106 */       readPredictedCenters(tok);
/* 107 */       tok.close();
/*     */     }
/* 109 */     catch (Exception e) {
/* 110 */       e.printStackTrace();
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void assignVectors(DataSource m) {
/* 162 */     int numVect = m.size();
/*     */     
/* 164 */     this.closestCenters = new int[numVect];
/* 165 */     this.minDist = new double[numVect];
/* 166 */     this.numVectPerCluster = new int[this.numClus];
/* 167 */     this.memship = new double[numVect];
/*     */     
/* 169 */     for (int i = 0; i < this.numClus; i++) {
/* 170 */       this.numVectPerCluster[i] = 0;
/*     */     }
/*     */     
/* 173 */     for (int j = 0; j < numVect; j++) {
/* 174 */       Object x = m.get(j).get();
/* 175 */       int k = 0;
/* 176 */       Object c = this.predicted_centers.elementAt(0);
/* 177 */       this.minDist[j] = this.metric.distance(x, c);
/* 178 */       this.closestCenters[j] = 0;
/*     */       
/* 180 */       for (k = 1; k < this.numClus; k++) {
/* 181 */         c = this.predicted_centers.elementAt(k);
/* 182 */         double dist = this.metric.distance(x, c);
/* 183 */         if (dist < this.minDist[j]) {
/* 184 */           this.minDist[j] = dist;
/* 185 */           this.closestCenters[j] = k;
/*     */         } 
/*     */       } 
/*     */       
/* 189 */       this.numVectPerCluster[this.closestCenters[j]] = this.numVectPerCluster[this.closestCenters[j]] + 1;
/*     */       
/* 191 */       this.memship[j] = Math.exp(-0.5D * this.minDist[j] / this.sigma[this.closestCenters[j]]);
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCenters(DataSource m) {
/* 291 */     int numVect = m.size();
/*     */     
/* 293 */     this.numDim = ((RealVector)m.get(0).get()).dimension();
/* 294 */     this.sum_weights = new double[this.numClus];
/* 295 */     this.sum_weights_feat = new double[this.numClus][this.numDim];
/* 296 */     this.sum_weights_dist_sq = new double[this.numClus];
/* 297 */     this.sum_weights_dist = new double[this.numClus];
/*     */ 
/*     */     
/* 300 */     for (int i = 0; i < this.numClus; i++) {
/* 301 */       this.sum_weights[i] = 0.0D;
/* 302 */       this.sum_weights_dist[i] = 0.0D;
/* 303 */       this.sum_weights_dist_sq[i] = 0.0D;
/* 304 */       for (int d = 0; d < this.numDim; d++) {
/* 305 */         this.sum_weights_feat[i][d] = 0.0D;
/*     */       }
/*     */     } 
/* 308 */     for (int j = 0; j < numVect; j++) {
/*     */       
/* 310 */       RealVector rv = (RealVector)m.get(j).get();
/* 311 */       double weight_dist = this.memship[j] * this.minDist[j];
/* 312 */       for (int i1 = 0; i1 < this.numClus; i1++) {
/* 313 */         if (i1 == this.closestCenters[j]) {
/* 314 */           this.sum_weights[i1] = this.sum_weights[i1] + this.memship[j];
/* 315 */           this.sum_weights_dist_sq[i1] = this.sum_weights_dist_sq[i1] + weight_dist * this.minDist[j];
/* 316 */           this.sum_weights_dist[i1] = this.sum_weights_dist[i1] + weight_dist;
/* 317 */           for (int d = 0; d < this.numDim; d++) {
/* 318 */             this.sum_weights_feat[i1][d] = this.sum_weights_feat[i1][d] + this.memship[j] * rv.get(d);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     for (int k = 0; k < this.numClus; k++) {
/* 328 */       double[] u = new double[this.numDim];
/* 329 */       for (int d = 0; d < this.numDim; d++)
/*     */       {
/*     */         
/* 332 */         u[d] = this.sum_weights_feat[k][d] / this.sum_weights[k];
/*     */       }
/*     */       
/* 335 */       this.predicted_centers.setElementAt(new RealVector(u), k);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     for (int n = 0; n < this.numClus; n++) {
/*     */       
/* 344 */       double temp_var = this.sum_weights_dist_sq[n] / 3.0D * this.sum_weights_dist[n];
/*     */       
/* 346 */       this.sigma[n] = temp_var;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String centersToString() {
/* 351 */     StringBuffer sbp = new StringBuffer();
/* 352 */     sbp.append(this.numClus);
/* 353 */     sbp.append("\n");
/* 354 */     for (int m = 0; m < this.predicted_centers.size(); m++) {
/*     */       
/* 356 */       sbp.append(this.predicted_centers.elementAt(m).toString());
/*     */       
/* 358 */       sbp.append(" ");
/*     */ 
/*     */       
/* 361 */       sbp.append(this.sigma[m]);
/*     */       
/* 363 */       sbp.append("\n");
/*     */     } 
/* 365 */     return sbp.toString();
/*     */   }
/*     */   
/*     */   public String scaledcentersToString() {
/* 369 */     StringBuffer sbp = new StringBuffer();
/* 370 */     sbp.append(this.numClus);
/* 371 */     sbp.append("\n");
/* 372 */     for (int m = 0; m < this.scaled_centers.size(); m++) {
/* 373 */       sbp.append(this.scaled_centers.elementAt(m).toString());
/* 374 */       sbp.append(" ");
/* 375 */       sbp.append(this.sigma[m]);
/* 376 */       sbp.append("\n");
/*     */     } 
/* 378 */     return sbp.toString();
/*     */   }
/*     */   
/*     */   public String centersToString2() {
/* 382 */     StringBuffer sbp = new StringBuffer();
/* 383 */     sbp.append(this.numClus);
/* 384 */     sbp.append("\n");
/*     */ 
/*     */     
/* 387 */     sbp.append("\n");
/*     */     
/* 389 */     for (int m = 0; m < this.predicted_centers.size(); m++) {
/*     */       
/* 391 */       sbp.append(this.predicted_centers.elementAt(m).toString());
/*     */ 
/*     */       
/* 394 */       sbp.append("\n");
/*     */ 
/*     */       
/* 397 */       sbp.append(this.sigma[m]);
/*     */       
/* 399 */       sbp.append("\n");
/*     */     } 
/* 401 */     return sbp.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveUpdateClusters(String fileName) {
/*     */     
/* 407 */     try { FileWriter writer = new FileWriter(fileName + ".txt");
/* 408 */       writer.write(centersToString());
/* 409 */       writer.close(); }
/* 410 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */   
/*     */   public void saveScaledUpdateClusters(String fileName) {
/*     */     
/* 416 */     try { FileWriter writer = new FileWriter(fileName + ".txt");
/* 417 */       writer.write(scaledcentersToString());
/* 418 */       writer.close(); }
/* 419 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(DataSource data) {
/* 428 */     assignVectors(data);
/* 429 */     updateCenters(data);
/*     */   }
/*     */   
/*     */   public double getSigma(Cluster cluster) {
/* 433 */     return ((SqrGaussianSet)cluster.getSet()).getSqrSigma();
/*     */   }
/*     */   
/*     */   public void setSigma(Cluster cluster, double sigma) {
/* 437 */     ((SqrGaussianSet)cluster.getSet()).setSqrSigma(sigma);
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(Enumeration iter) {
/* 442 */     Vector dsigma = new Vector();
/* 443 */     this.predicted_centers = new Vector();
/* 444 */     while (iter.hasMoreElements()) {
/* 445 */       Cluster ind = iter.nextElement();
/* 446 */       Object r = ind.getCenter();
/* 447 */       this.predicted_centers.add(Cloner.clone(r));
/* 448 */       dsigma.add(new Double(getSigma(ind)));
/*     */     } 
/* 450 */     this.sigma = new double[dsigma.size()];
/* 451 */     this.numClus = 0;
/* 452 */     iter = (Enumeration)dsigma.elements();
/* 453 */     while (iter.hasMoreElements()) {
/* 454 */       Double d = (Double)iter.nextElement();
/* 455 */       this.sigma[this.numClus] = d.doubleValue();
/* 456 */       this.numClus++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Prototype set() {
/* 461 */     Vector c = new Vector();
/* 462 */     int n = this.sigma.length;
/* 463 */     for (int i = 0; i < n; i++) {
/* 464 */       c.add(new Cluster(this.predicted_centers.get(i), (Set)new SqrGaussianSet("cluster-" + i, this.sigma[i])));
/*     */     }
/*     */     
/* 467 */     return new Prototype(c);
/*     */   }
/*     */   
/*     */   public Prototype iteration(Prototype pop) {
/* 471 */     init(pop.elements());
/* 472 */     assignVectors(this.data);
/* 473 */     updateCenters(this.data);
/* 474 */     return set();
/*     */   }
/*     */   
/*     */   public Prototype apply(Prototype pop) {
/* 478 */     for (int i = 0; i < this.ITERS; i++) {
/* 479 */       pop = iteration(pop);
/*     */ 
/*     */       
/* 482 */       addToTrace(pop);
/*     */     } 
/* 484 */     return pop;
/*     */   }
/*     */   public Object output() {
/* 487 */     return null;
/*     */   }
/*     */   public static void main(String[] args) {
/* 490 */     String prop_file = "/home/eleon/JProjects/clustering_6/results/6_15_2005/cluster-10-1.txt/run-2-pop-ext.txt";
/* 491 */     String file = "/home/eleon/JProjects/clustering_5/datasets/synthetic2/cluster-10-1.txt";
/* 492 */     String namefile = "cluster-10-1.txt";
/* 493 */     DataSource source = (new FileDataSource(file, -1)).optimize();
/* 494 */     MDE ref = new MDE((QuasiMetric)new Minkowski(2.0D), source, 13.8D, 10);
/* 495 */     Vector vp = PopulationLoader.read(prop_file, false, false, (SetGenerator)new GaussianSetGenerator());
/* 496 */     Prototype p = new Prototype(vp);
/* 497 */     Prototype pref = ref.apply(p);
/* 498 */     System.out.println(pref.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\refinement\MDE.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
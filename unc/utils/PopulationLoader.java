/*     */ package unc.utils;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Genotype;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Phenotype;
/*     */ import jml.evolution.Population;
/*     */ import jml.util.quasimetric.QuasiMetric;
/*     */ import unc.Cluster;
/*     */ import unc.Prototype;
/*     */ import unc.evolution.fitness.ClusterFitness;
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
/*     */ public class PopulationLoader
/*     */ {
/*     */   public static Vector read(String file, boolean binary, boolean sparce, SetGenerator set_gen) {
/*  31 */     DataSource data = (new FileDataSource(file, 11, 2, -1, sparce, binary, new ClusterDataGenerator(0, binary, sparce, set_gen))).optimize();
/*     */     
/*  33 */     Vector clusters = new Vector();
/*  34 */     Enumeration iter = data.elements();
/*  35 */     while (iter.hasMoreElements()) {
/*  36 */       clusters.add(((Data)iter.nextElement()).get());
/*     */     }
/*  38 */     return clusters;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Population read(String file, boolean binary, boolean sparce, SetGenerator set_gen, Environment env, int POP_SIZE) {
/*  43 */     DataSource data = (new FileDataSource(file, -1, new ClusterDataGenerator(0, binary, sparce, set_gen))).optimize();
/*     */     
/*  45 */     return create(env, data, POP_SIZE, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Population create(Environment env, DataSource data, int POP_SIZE, QuasiMetric metric) {
/*  50 */     Genotype gen = env.getGenotype();
/*     */ 
/*     */ 
/*     */     
/*  54 */     DataSourceGenotype gen1 = new DataSourceGenotype(data, env.getPhenotype());
/*  55 */     env.setGenotype(gen1);
/*  56 */     Population p = new Population(env, POP_SIZE);
/*  57 */     env.setGenotype(gen);
/*  58 */     return p;
/*     */   }
/*     */   
/*     */   public static Population create(Environment env, Prototype prot) {
/*  62 */     Vector v = new Vector();
/*  63 */     Phenotype phen = env.getPhenotype();
/*  64 */     Enumeration iter = prot.elements();
/*  65 */     while (iter.hasMoreElements()) {
/*  66 */       Cluster c = iter.nextElement();
/*  67 */       c = (Cluster)c.clone();
/*     */       
/*  69 */       c.setFitness(0.0D);
/*  70 */       Individual ind = new Individual(phen.set(c));
/*  71 */       ind.evalFitness(env);
/*  72 */       v.add(ind);
/*     */     } 
/*     */     
/*  75 */     return new Population(env, v);
/*     */   }
/*     */   
/*     */   public static Prototype generate(Population p) {
/*  79 */     Environment env = p.getEnvironment();
/*  80 */     ClusterFitness f = (ClusterFitness)env.getFitness();
/*  81 */     p.sort();
/*  82 */     int n = p.size();
/*  83 */     Vector clusters = new Vector();
/*  84 */     for (int i = 0; i < n; i++) {
/*  85 */       Cluster c = (Cluster)p.get(i).getThing(env);
/*  86 */       c.setFitness(p.get(i).getFitness());
/*     */       
/*  88 */       clusters.add(c);
/*     */     } 
/*  90 */     return new Prototype(clusters);
/*     */   }
/*     */   
/*     */   public static Population merge(Population one, Population two) {
/*  94 */     Vector v = new Vector();
/*  95 */     Enumeration iter = one.individuals.elements();
/*  96 */     while (iter.hasMoreElements()) {
/*  97 */       v.add(iter.nextElement());
/*     */     }
/*  99 */     iter = two.individuals.elements();
/* 100 */     while (iter.hasMoreElements()) {
/* 101 */       v.add(iter.nextElement());
/*     */     }
/* 103 */     return new Population(one.getEnvironment(), v);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\PopulationLoader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
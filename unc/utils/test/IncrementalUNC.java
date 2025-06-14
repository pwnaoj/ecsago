/*     */ package unc.utils.test;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Tracer;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Population;
/*     */ import jml.parser.SimpleStreamTokenizer;
/*     */ import jml.parser.Token;
/*     */ import jml.random.Partition;
/*     */ import unc.IncrementalInfo;
/*     */ import unc.extraction.ComposeExtraction;
/*     */ import unc.extraction.Extraction;
/*     */ import unc.utils.PopulationLoader;
/*     */ import unc.utils.tracers.UNCFileTracer;
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
/*     */ public class IncrementalUNC
/*     */   extends UNCTestOne
/*     */ {
/*     */   protected int n;
/*  39 */   protected double memoryFactor = 1.0D;
/*     */ 
/*     */   
/*  42 */   protected Partition partition = null;
/*     */   
/*     */   public IncrementalUNC(DataSource _data, int _MAX_ITER, int _POP_SIZE, int _n) {
/*  45 */     super(_data, _MAX_ITER, _POP_SIZE);
/*  46 */     this.n = _n;
/*     */   }
/*     */ 
/*     */   
/*     */   public IncrementalUNC(DataSource _data, int _MAX_ITER, int _POP_SIZE, int _n, double _memoryFactor) {
/*  51 */     super(_data, _MAX_ITER, _POP_SIZE);
/*  52 */     this.n = _n;
/*  53 */     this.memoryFactor = _memoryFactor;
/*     */   }
/*     */   
/*     */   public Population getPopulation(Environment env) {
/*  57 */     Vector v = new Vector();
/*  58 */     for (int i = 0; i < this.POP_SIZE; i++) {
/*  59 */       v.add(null);
/*     */     }
/*  61 */     Population population = new Population(env, v);
/*  62 */     if (this.extra_population != null) {
/*  63 */       population = PopulationLoader.merge(population, this.extra_population);
/*     */     }
/*  65 */     return population;
/*     */   }
/*     */   
/*  68 */   public static Vector bIndices = null;
/*  69 */   public static int[] size = new int[] { 1073, 1000, 1022, 1030, 1011, 1003, 1028, 1024, 1012, 1019, 1033, 989, 974, 967, 981, 1004, 977, 972, 1017, 980, 1024, 1001, 1001, 1003, 1011, 960, 992, 973, 981, 1014, 1032, 984, 981, 957, 986, 980, 989, 979, 993, 979, 1058, 974, 983, 1008, 975, 1016, 977, 988, 993, 999, 1035, 997, 986, 971, 979, 995, 986, 987, 971, 994, 1055, 963, 1003, 979, 990, 990, 998, 964, 1001, 992, 1032, 965, 964, 983, 980, 972, 985, 972, 1001, 1002, 1023, 1021, 991, 1012, 993, 996, 989, 1010, 983, 988, 1093, 1020, 1033, 1038, 1039, 1016, 1048, 1018, 1057, 1033 };
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
/*     */   public static void birchIndices() {
/*  82 */     if (bIndices == null) {
/*  83 */       int[] new_size = new int[100];
/*  84 */       bIndices = new Vector();
/*  85 */       Vector cluster = Partition.permutation(100);
/*  86 */       for (int i = 0; i < 100; i++) {
/*  87 */         int index = ((Integer)cluster.get(i)).intValue();
/*  88 */         new_size[i] = size[index];
/*  89 */         int counter = 0; int j;
/*  90 */         for (j = 0; j < index; j++) {
/*  91 */           counter += size[j];
/*     */         }
/*  93 */         for (j = 0; j < size[index]; j++) {
/*  94 */           bIndices.add(new Integer(counter));
/*  95 */           counter++;
/*     */         } 
/*     */       } 
/*  98 */       size = new_size;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Partition birchPartition() {
/* 104 */     int[] p_size = new int[this.n];
/* 105 */     int delta = 100 / this.n;
/* 106 */     for (int i = 0; i < this.n; i++) {
/* 107 */       p_size[i] = 0;
/* 108 */       for (int j = i * delta; j < (i + 1) * delta; j++) {
/* 109 */         p_size[i] = p_size[i] + size[j];
/*     */       }
/* 111 */       System.out.println(p_size[i]);
/*     */     } 
/* 113 */     return new Partition(bIndices, p_size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void create() {
/* 118 */     this.partition = new Partition(this.data.data.size(), this.n, true);
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
/* 129 */     this.EA = getEA();
/* 130 */     this.extraction = getExtraction();
/* 131 */     this.refinement = null;
/* 132 */     this.incremental = new IncrementalInfo(this.partition, this.memoryFactor, getMetric(), getWeight());
/*     */   }
/*     */ 
/*     */   
/*     */   public Extraction getExtraction() {
/* 137 */     return (Extraction)new ComposeExtraction(getFitnessExtraction(), getNicheExtraction());
/*     */   }
/*     */   
/*     */   public Vector readIndices(String file) {
/* 141 */     Vector indices = new Vector();
/*     */     try {
/* 143 */       FileReader reader = new FileReader(file);
/* 144 */       SimpleStreamTokenizer tok = new SimpleStreamTokenizer(reader);
/* 145 */       Token t = tok.nextToken();
/* 146 */       while (t.type != 3) {
/* 147 */         System.out.println("Index: " + t.nval);
/* 148 */         indices.add(new Integer((int)t.nval));
/* 149 */         t = tok.nextToken();
/*     */       }
/*     */     
/* 152 */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/*     */     } 
/* 155 */     return indices;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 159 */     System.out.println("para:" + args[0]);
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
/* 171 */     String file = "/home/jgomez/eliza/datasets/Synthetic4/cluster-10-1.txt";
/* 172 */     String nameFile = "cluster-10-1.txt";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     DataSource source = (new FileDataSource(file, -1)).optimize();
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
/* 206 */     int[] size = { 10 };
/*     */ 
/*     */     
/* 209 */     double[] tw = { 0.2847D };
/* 210 */     for (int i = 0; i < tw.length; i++) {
/* 211 */       IncrementalUNC inctest = new IncrementalUNC(source, 15, 100, 10, 1.0D);
/* 212 */       inctest.T = tw[i];
/*     */ 
/*     */       
/* 215 */       inctest.addTracer((Tracer)new UNCFileTracer(new int[] { 0, 5, 10, 15 }, new int[] { 5, 10 }, "results/" + nameFile, tw[i]));
/*     */       
/* 217 */       inctest.run();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\test\IncrementalUNC.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
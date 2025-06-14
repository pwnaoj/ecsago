/*     */ package unc.validation;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.random.Partition;
/*     */ import unc.Prototype;
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
/*     */ public class ClusteringStatistics
/*     */ {
/*     */   protected Prototype clusters;
/*     */   protected Partition partition;
/*     */   protected int classes;
/*     */   protected int[] real_label;
/*  26 */   protected int[][] confusion = (int[][])null;
/*     */   
/*  28 */   protected double[][] precision = (double[][])null;
/*  29 */   protected double[][] recall = (double[][])null;
/*  30 */   protected double[][] Fmeasure = (double[][])null;
/*  31 */   protected double[] Fscore = null;
/*  32 */   protected double ftotal = 0.0D;
/*     */   
/*  34 */   public double[] entropies = null;
/*  35 */   public double[] purities = null;
/*  36 */   double finalEntropy = 0.0D;
/*  37 */   double finalPurity = 0.0D;
/*     */ 
/*     */   
/*     */   public ClusteringStatistics(Prototype _clusters, Partition _partition, int _classes, int[] _real_label) {
/*  41 */     this.clusters = _clusters;
/*  42 */     this.partition = _partition;
/*  43 */     this.classes = _classes;
/*  44 */     this.real_label = _real_label;
/*  45 */     confusionMatrix();
/*  46 */     getFscore();
/*  47 */     getEntropy();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEmptyClusters() {
/*  53 */     int n = this.partition.size();
/*  54 */     int non_empty = 0;
/*  55 */     for (int i = 0; i < n; i++) {
/*  56 */       if (this.partition.groupSize(i) > 0) non_empty++; 
/*     */     } 
/*  58 */     int[] new_size = new int[non_empty];
/*  59 */     int k = 0;
/*  60 */     Vector v = new Vector();
/*  61 */     for (int j = 0; j < n; j++) {
/*  62 */       int m = this.partition.groupSize(j);
/*  63 */       if (m > 0) {
/*  64 */         v.add(this.clusters.get(j));
/*  65 */         new_size[k] = m;
/*  66 */         k++;
/*     */       } 
/*     */     } 
/*  69 */     this.partition.setGroups(new_size);
/*  70 */     this.clusters.set(v);
/*  71 */     confusionMatrix();
/*  72 */     getFscore();
/*  73 */     getEntropy();
/*     */   }
/*     */   
/*     */   public int[][] confusionMatrix() {
/*  77 */     if (this.confusion == null) {
/*  78 */       int m = this.partition.size();
/*  79 */       this.confusion = new int[m][this.classes];
/*  80 */       for (int i = 0; i < m; i++) {
/*  81 */         for (int j = 0; j < this.classes; j++) {
/*  82 */           this.confusion[i][j] = 0;
/*     */         }
/*  84 */         Enumeration iter = this.partition.getGroup(i);
/*  85 */         while (iter.hasMoreElements()) {
/*  86 */           Integer index = iter.nextElement();
/*  87 */           this.confusion[i][this.real_label[index.intValue()]] = this.confusion[i][this.real_label[index.intValue()]] + 1;
/*     */         } 
/*     */       } 
/*     */     } 
/*  91 */     return this.confusion;
/*     */   }
/*     */   
/*     */   public double[] getFscore() {
/*  95 */     if (this.Fscore == null) {
/*  96 */       this.ftotal = 0.0D;
/*  97 */       confusionMatrix();
/*  98 */       int nmrClusters = this.partition.size();
/*  99 */       this.Fscore = new double[this.classes];
/* 100 */       this.precision = new double[nmrClusters][this.classes];
/* 101 */       this.recall = new double[nmrClusters][this.classes];
/* 102 */       this.Fmeasure = new double[nmrClusters][this.classes];
/*     */ 
/*     */ 
/*     */       
/* 106 */       for (int j = 0; j < this.classes; j++) {
/* 107 */         int size = 0;
/* 108 */         for (int i = 0; i < nmrClusters; i++) {
/* 109 */           size += this.confusion[i][j];
/*     */         }
/* 111 */         double maxFmeasure = 0.0D;
/* 112 */         for (int k = 0; k < nmrClusters; k++) {
/* 113 */           this.recall[k][j] = this.confusion[k][j] / size;
/* 114 */           this.precision[k][j] = this.confusion[k][j] / this.partition.groupSize(k);
/*     */           
/* 116 */           this.Fmeasure[k][j] = 2.0D * this.precision[k][j] * this.recall[k][j] / (this.precision[k][j] + this.recall[k][j]);
/*     */           
/* 118 */           if (this.Fmeasure[k][j] > maxFmeasure) {
/* 119 */             maxFmeasure = this.Fmeasure[k][j];
/*     */           }
/*     */         } 
/* 122 */         this.Fscore[j] = maxFmeasure;
/* 123 */         this.ftotal += size / this.real_label.length * this.Fscore[j];
/*     */       } 
/*     */     } 
/* 126 */     return this.Fscore;
/*     */   }
/*     */   
/*     */   public double[][] getPrecision() {
/* 130 */     if (this.precision == null) {
/* 131 */       getFscore();
/*     */     }
/* 133 */     return this.precision;
/*     */   }
/*     */   
/*     */   public double[][] getRecall() {
/* 137 */     if (this.recall == null) {
/* 138 */       getFscore();
/*     */     }
/* 140 */     return this.recall;
/*     */   }
/*     */   
/*     */   public double getFtotal() {
/* 144 */     if (this.ftotal == 0.0D) getFscore(); 
/* 145 */     return this.ftotal;
/*     */   }
/*     */   
/*     */   public void getEntropy() {
/* 149 */     int n = this.real_label.length;
/* 150 */     int nmrClusters = this.partition.size();
/* 151 */     this.entropies = new double[nmrClusters];
/* 152 */     this.purities = new double[nmrClusters];
/*     */ 
/*     */     
/* 155 */     for (int i = 0; i < nmrClusters; i++) {
/* 156 */       this.entropies[i] = calculateEntropy(this.confusion[i], i);
/* 157 */       this.purities[i] = calculatePurity(this.confusion[i], i);
/* 158 */       double factor = this.partition.groupSize(i) / n;
/* 159 */       this.finalEntropy += this.entropies[i] * factor;
/* 160 */       this.finalPurity += this.purities[i] * factor;
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
/*     */   private double calculateEntropy(int[] row, int rowNum) {
/* 172 */     double sum = 0.0D;
/* 173 */     int nmrClasses = row.length;
/*     */     
/* 175 */     double entropy = -1.0D / Math.log(nmrClasses);
/* 176 */     int m = this.partition.groupSize(rowNum);
/* 177 */     if (m > 0) {
/* 178 */       for (int i = 0; i < nmrClasses; i++) {
/* 179 */         if (row[i] != 0) {
/* 180 */           double x = row[i] / m;
/* 181 */           sum += Math.log(x) * x;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 186 */     entropy *= sum;
/* 187 */     return entropy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double calculatePurity(int[] row, int rowNum) {
/* 197 */     int nmrClasses = row.length;
/* 198 */     int max = 0;
/*     */     
/* 200 */     for (int i = 0; i < nmrClasses; i++) {
/* 201 */       if (row[i] > max) {
/* 202 */         max = row[i];
/*     */       }
/*     */     } 
/*     */     
/* 206 */     return max / this.partition.groupSize(rowNum);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sort(double[] e) {
/* 212 */     for (int i = 0; i < e.length - 1; i++) {
/* 213 */       for (int j = i; j < e.length; j++) {
/* 214 */         if (e[j] > e[i]) {
/* 215 */           double temp = e[i];
/* 216 */           e[i] = e[j];
/* 217 */           e[j] = temp;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMedian(double[] a) {
/* 224 */     double[] e = (double[])a.clone();
/* 225 */     sort(e);
/* 226 */     int size = e.length;
/*     */     
/* 228 */     double median = e[0];
/* 229 */     if (size > 1) {
/* 230 */       if (size % 2 == 1) {
/* 231 */         int pos = (size + 1) / 2;
/* 232 */         median = e[pos - 1];
/*     */       } else {
/* 234 */         int pos = size / 2;
/* 235 */         median = (e[pos - 1] + e[pos]) / 2.0D;
/*     */       } 
/*     */     }
/* 238 */     return median;
/*     */   }
/*     */   
/*     */   public double getAverage(double[] a) {
/* 242 */     int n = a.length;
/* 243 */     double avg = 0.0D;
/* 244 */     for (int i = 0; i < n; i++) {
/* 245 */       avg += a[i];
/*     */     }
/* 247 */     return avg / n;
/*     */   }
/*     */   
/*     */   public double getMin(double[] a) {
/* 251 */     int n = a.length;
/* 252 */     double min = a[0];
/* 253 */     for (int i = 1; i < n; i++) {
/* 254 */       if (a[i] < min) min = a[i]; 
/*     */     } 
/* 256 */     return min;
/*     */   }
/*     */   
/*     */   public double getMax(double[] a) {
/* 260 */     int n = a.length;
/* 261 */     double max = a[0];
/* 262 */     for (int i = 1; i < n; i++) {
/* 263 */       if (a[i] > max) max = a[i]; 
/*     */     } 
/* 265 */     return max;
/*     */   }
/*     */   public double getEntropy(int cluster) {
/* 268 */     return this.entropies[cluster];
/*     */   } public double getMinEntropy() {
/* 270 */     return getMin(this.entropies);
/*     */   } public double getMaxEntropy() {
/* 272 */     return getMax(this.entropies);
/*     */   } public double getAvgEntropy() {
/* 274 */     return getAverage(this.entropies);
/*     */   } public double getMedianEntropy() {
/* 276 */     return getMedian(this.entropies);
/*     */   } public double getFinalEntropy() {
/* 278 */     return this.finalEntropy;
/*     */   } public double getPurity(int cluster) {
/* 280 */     return this.purities[cluster];
/*     */   } public double getMinPurity() {
/* 282 */     return getMin(this.purities);
/*     */   } public double getMaxPurity() {
/* 284 */     return getMax(this.purities);
/*     */   } public double getAvgPurity() {
/* 286 */     return getAverage(this.purities);
/*     */   } public double getMedianPurity() {
/* 288 */     return getMedian(this.purities);
/*     */   } public double getFinalPurity() {
/* 290 */     return this.finalPurity;
/*     */   }
/*     */   
/*     */   public String getEntropyStats() {
/* 294 */     StringBuffer sb = new StringBuffer();
/* 295 */     sb.append("Min Entropy:     " + getMinEntropy());
/* 296 */     sb.append("\n");
/* 297 */     sb.append("Max Entropy:     " + getMaxEntropy());
/* 298 */     sb.append("\n");
/* 299 */     sb.append("Median Entropy:  " + getMedianEntropy());
/* 300 */     sb.append("\n");
/* 301 */     sb.append("Average Entropy: " + getAvgEntropy());
/* 302 */     sb.append("\n");
/* 303 */     sb.append("Final Entropy:   " + getFinalEntropy());
/* 304 */     sb.append("\n");
/* 305 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String getPurityStats() {
/* 309 */     StringBuffer sb = new StringBuffer();
/* 310 */     sb.append("Min Purity:     " + getMinPurity());
/* 311 */     sb.append("\n");
/* 312 */     sb.append("Max Purity:     " + getMaxPurity());
/* 313 */     sb.append("\n");
/* 314 */     sb.append("Median Purity:  " + getMedianPurity());
/* 315 */     sb.append("\n");
/* 316 */     sb.append("Average Purity: " + getAvgPurity());
/* 317 */     sb.append("\n");
/* 318 */     sb.append("Final Purity:   " + getFinalPurity());
/* 319 */     sb.append("\n");
/* 320 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected String getColumn(String text, int column_width) {
/* 324 */     StringBuffer sb = new StringBuffer();
/* 325 */     for (int i = text.length(); i < column_width; ) { sb.append(' '); i++; }
/* 326 */      sb.append(text);
/* 327 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected String getColumn(int val, int column_width) {
/* 331 */     return getColumn("" + val, column_width);
/*     */   }
/*     */   
/*     */   public String getConfusionMatrixStat(int column_width) {
/* 335 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 337 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\validation\ClusteringStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.data.util;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.util.Vector;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.DataSourceInfo;
/*     */ import jml.data.sources.FileDataSource;
/*     */ import jml.parser.SimpleStreamTokenizer;
/*     */ import jml.random.Partition;
/*     */ import jml.util.IntUtil;
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
/*     */ public class DataSourceUtils
/*     */ {
/*     */   public static DataSource load(String fileName) {
/*  33 */     DataFileConfig config = DataFileConfig.getMinimumFileConfig(fileName);
/*  34 */     DataSourceInfo info = config.getInfo();
/*  35 */     FileDataSource source = new FileDataSource(fileName, config.size(), config.dimension(), -1, false, false);
/*     */     
/*  37 */     return source.optimize();
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
/*     */   public static DataSource load(String fileName, DataFileConfig config) {
/*  49 */     FileDataSource source = new FileDataSource(fileName, config.size(), config.dimension(), -1, false, false);
/*     */     
/*  51 */     return source.optimize();
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
/*     */   public static DataSource load_old_format(String fileName, int label_pos) {
/*  72 */     FileDataSource source = new FileDataSource(fileName, label_pos);
/*  73 */     return source.optimize();
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
/*     */   public static DataSource loadDataSourceFromPath(String path, String configFile, String dataFile) {
/*  88 */     DataSource source = null;
/*  89 */     DataFileConfig config = DataFileConfig.read(path + "/" + configFile);
/*  90 */     if (config != null) {
/*  91 */       source = load(path + "/" + dataFile, config);
/*     */     }
/*  93 */     return source;
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
/*     */   public static DataSource loadDataSourceFromPath(String path) {
/* 105 */     return loadDataSourceFromPath(path, "config.txt", "data.txt");
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
/*     */   public static DataSourceInfo importDataSource(String input_file, int number_classes, String output_file) {
/* 117 */     DataSource input = null;
/* 118 */     if (number_classes > 1) { load_old_format(input_file, IntUtil.MAX_INT); }
/* 119 */     else { load_old_format(input_file, -1); }
/* 120 */      input.save(output_file, (number_classes > 1));
/* 121 */     return null;
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
/*     */   public static Vector loadPartitionsFromPath(String path, int number, int size, int groups) {
/* 136 */     Vector v = new Vector();
/*     */     
/* 138 */     try { for (int i = 0; i < number; i++) {
/* 139 */         FileReader reader = new FileReader(path + "/partition-" + i + ".txt");
/* 140 */         Partition p = new Partition(size, groups, new SimpleStreamTokenizer(reader));
/* 141 */         v.add(p);
/* 142 */         reader.close();
/*     */       }  }
/* 144 */     catch (Exception e) { e.printStackTrace(); }
/* 145 */      return v;
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
/*     */   public static Partition createPartition(DataSource source, int m) {
/* 159 */     int n = 2;
/* 160 */     if (n > 1) {
/* 161 */       Vector[] samples_per_class = new Vector[n];
/* 162 */       int min = source.size();
/* 163 */       for (int i = 0; i < n; i++) {
/* 164 */         samples_per_class[i] = source.classSamples(i);
/* 165 */         Partition.permutation(samples_per_class[i]);
/* 166 */         if (min > samples_per_class[i].size()) {
/* 167 */           min = samples_per_class[i].size();
/*     */         }
/*     */       } 
/* 170 */       double[] delta = new double[n];
/* 171 */       for (int j = 0; j < n; j++) {
/* 172 */         delta[j] = samples_per_class[j].size() / min;
/*     */       }
/* 174 */       Vector v = new Vector();
/* 175 */       for (int k = 0; k < min; k++) {
/* 176 */         for (int i1 = 0; i1 < n; i1++) {
/* 177 */           int start = (int)(delta[i1] * k);
/* 178 */           int end = (int)(delta[i1] * (k + 1));
/* 179 */           for (int i2 = start; i2 < end; i2++) {
/* 180 */             v.add(samples_per_class[i1].get(i2));
/*     */           }
/*     */         } 
/*     */       } 
/* 184 */       return new Partition(v, m);
/*     */     } 
/* 186 */     return new Partition(source.size(), m, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\DataSourceUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.data.sources;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.generators.DataGenerator;
/*     */ import jml.data.generators.FullDataGenerator;
/*     */ import jml.data.generators.SparseBitArrayDataGenerator;
/*     */ import jml.data.generators.SparseDataGenerator;
/*     */ import jml.parser.MatrixFileReader;
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
/*     */ public class FileDataSource
/*     */   extends DataSource
/*     */ {
/*  61 */   protected MatrixFileReader reader = null;
/*     */   
/*  63 */   protected int shift = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected DataGenerator g;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileDataSource(InputStream is, int label_pos) {
/*  73 */     this.reader = new MatrixFileReader(is);
/*  74 */     double[] d = this.reader.read(0);
/*  75 */     this.g = analize(d, label_pos);
/*  76 */     this.shift = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileDataSource(String fileName, int label_pos) {
/*  85 */     this.reader = new MatrixFileReader(fileName);
/*  86 */     double[] d = this.reader.read(0);
/*  87 */     this.g = analize(d, label_pos);
/*  88 */     this.shift = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileDataSource(String fileName, int label_pos, DataGenerator _g) {
/*  98 */     this.reader = new MatrixFileReader(fileName);
/*  99 */     double[] d = this.reader.read(0);
/* 100 */     this.g = _g;
/* 101 */     this.g.set(analize(d, label_pos));
/* 102 */     this.shift = 1;
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
/*     */   public FileDataSource(String fileName, int _m, int n, int label_pos, boolean sparseValues, boolean binary) {
/* 114 */     this.m = _m;
/* 115 */     this.g = analize(n, sparseValues, binary, label_pos);
/* 116 */     this.reader = new MatrixFileReader(fileName);
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
/*     */   public FileDataSource(String fileName, int _m, int n, int label_pos, boolean sparseValues, boolean binary, DataGenerator _g) {
/* 129 */     this.m = _m;
/* 130 */     this.g = _g;
/* 131 */     this.g.set(analize(n, sparseValues, binary, label_pos));
/* 132 */     this.reader = new MatrixFileReader(fileName);
/*     */   }
/*     */   public DataGenerator analize(int n, boolean sparse, boolean binary, int label_pos) {
/*     */     FullDataGenerator fullDataGenerator;
/* 136 */     DataGenerator gen = null;
/* 137 */     if (binary) {
/* 138 */       if (sparse) {
/* 139 */         SparseBitArrayDataGenerator sparseBitArrayDataGenerator = new SparseBitArrayDataGenerator(n, label_pos);
/*     */       } else {
/* 141 */         gen = null;
/*     */       }
/*     */     
/* 144 */     } else if (sparse) {
/* 145 */       SparseDataGenerator sparseDataGenerator = new SparseDataGenerator(n, label_pos);
/*     */     } else {
/* 147 */       fullDataGenerator = new FullDataGenerator(n, label_pos);
/*     */     } 
/*     */     
/* 150 */     return (DataGenerator)fullDataGenerator;
/*     */   }
/*     */   
/*     */   public boolean sparse() {
/* 154 */     return (this.g instanceof SparseBitArrayDataGenerator || this.g instanceof SparseDataGenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean real() {
/* 159 */     return (this.g instanceof FullDataGenerator || this.g instanceof SparseDataGenerator);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataGenerator analize(double[] first_line, int label_pos) {
/* 164 */     boolean sparse = false;
/* 165 */     boolean binary = false;
/* 166 */     this.m = (int)first_line[0];
/* 167 */     int n = (int)first_line[1];
/* 168 */     if (first_line.length > 2) {
/* 169 */       sparse = (first_line[2] == 1.0D);
/* 170 */       if (first_line.length > 3) {
/* 171 */         binary = (first_line[3] == 1.0D);
/*     */       }
/*     */     } 
/* 174 */     return analize(n, sparse, binary, label_pos);
/*     */   }
/*     */   
/*     */   public DataSource optimize() {
/* 178 */     if (this.reader.readTotally()) {
/* 179 */       Vector v = new Vector();
/* 180 */       Enumeration iter = elements();
/* 181 */       while (iter.hasMoreElements()) {
/* 182 */         Data d = iter.nextElement();
/* 183 */         v.add(d);
/*     */       } 
/* 185 */       return new VectorDataSource(v);
/*     */     } 
/* 187 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 194 */     this.reader.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int index) {
/* 203 */     return this.g.get(this.reader.read(index + this.shift));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 211 */     return new FileDataSourceEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class FileDataSourceEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected Enumeration buffer_iter;
/*     */     
/*     */     private final FileDataSource this$0;
/*     */ 
/*     */     
/*     */     public FileDataSourceEnumeration(FileDataSource this$0) {
/* 225 */       this.this$0 = this$0; this.buffer_iter = null;
/* 226 */       this.buffer_iter = this$0.reader.elements();
/* 227 */       for (int i = 0; i < this$0.shift; ) { this.buffer_iter.nextElement(); i++; }
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 235 */       return this.buffer_iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 243 */       double[] d = this.buffer_iter.nextElement();
/* 244 */       return this.this$0.g.get(d);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\FileDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
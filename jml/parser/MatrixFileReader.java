/*     */ package jml.parser;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringReader;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MatrixFileReader
/*     */ {
/*     */   protected boolean full;
/*  21 */   protected String fileName = null;
/*     */   
/*  23 */   protected BufferedReader is = null;
/*     */   
/*  25 */   protected SimpleStreamTokenizer tok = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MAX_SIZE = 10000000;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int start;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vector buffer;
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {
/*     */     try {
/*  43 */       if (this.fileName != null)
/*  44 */       { if (this.is != null) this.is.close(); 
/*  45 */         FileReader reader = new FileReader(this.fileName);
/*  46 */         this.is = new BufferedReader(reader);
/*  47 */         this.start = 0;
/*  48 */         this.full = read_buffer(); }
/*  49 */       else { this.is.reset(); } 
/*  50 */     } catch (Exception e) {
/*  51 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getLine() {
/*  56 */     String line = null;
/*     */     try {
/*  58 */       line = this.is.readLine();
/*  59 */     } catch (Exception e) {
/*  60 */       e.printStackTrace();
/*     */     } 
/*  62 */     return line;
/*     */   }
/*     */   
/*     */   protected double[] next() {
/*  66 */     String line = getLine();
/*  67 */     double[] d = null;
/*  68 */     if (line != null) {
/*  69 */       StringReader sr = new StringReader(line);
/*  70 */       SimpleStreamTokenizer tok = new SimpleStreamTokenizer(sr);
/*  71 */       Vector args = tok.getTokens();
/*  72 */       int n = args.size();
/*  73 */       d = new double[n];
/*  74 */       for (int i = 0; i < n; ) { d[i] = ((Token)args.get(i)).nval; i++; }
/*     */     
/*  76 */     }  return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatrixFileReader(String _fileName) {
/*  84 */     this.start = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.buffer = new Vector(); this.fileName = _fileName; reset(); } public MatrixFileReader(InputStream _is) { this.start = 0; this.buffer = new Vector(); try {
/*     */       InputStreamReader reader = new InputStreamReader(_is); this.is = new BufferedReader(reader);
/*     */       this.start = 0;
/*     */       this.full = read_buffer();
/*     */     } catch (Exception e) {
/*     */       e.printStackTrace();
/*     */     }  } protected boolean read_buffer() { double[] d;
/*  96 */     if (this.buffer != null) this.buffer.clear(); 
/*  97 */     Vector v = new Vector();
/*  98 */     int s = 0;
/*     */ 
/*     */     
/*     */     do {
/* 102 */       d = next();
/* 103 */       if (d == null)
/* 104 */         continue;  v.add(d);
/* 105 */       s += d.length;
/*     */     }
/* 107 */     while (d != null && s < 10000000);
/* 108 */     this.buffer = v;
/* 109 */     return (d == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] read(int index) {
/* 120 */     if (index < this.start || index >= this.start + this.buffer.size()) {
/* 121 */       if (index < this.start) { reset(); } else { this.start += this.buffer.size(); }
/* 122 */        read_buffer();
/* 123 */       while (this.buffer.size() > 0 && index >= this.start + this.buffer.size()) {
/* 124 */         this.start += this.buffer.size();
/* 125 */         read_buffer();
/*     */       } 
/*     */     } 
/* 128 */     if (this.buffer.size() <= index - this.start) return null; 
/* 129 */     return this.buffer.get(index - this.start);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     
/* 136 */     try { this.is.close(); }
/* 137 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   } public boolean readTotally() {
/* 140 */     return this.full;
/*     */   } public Enumeration elements() {
/* 142 */     return new MatrixFileReaderEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class MatrixFileReaderEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected Enumeration buffer_iter;
/*     */     
/*     */     private final MatrixFileReader this$0;
/*     */ 
/*     */     
/*     */     public MatrixFileReaderEnumeration(MatrixFileReader this$0) {
/* 156 */       this.this$0 = this$0; this.buffer_iter = null;
/* 157 */       this.buffer_iter = this$0.buffer.elements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 165 */       if (this.buffer_iter.hasMoreElements()) return true;
/*     */       
/* 167 */       this.this$0.read_buffer();
/* 168 */       this.this$0.start += this.this$0.buffer.size();
/* 169 */       return this.buffer_iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 177 */       return this.buffer_iter.nextElement();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\parser\MatrixFileReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
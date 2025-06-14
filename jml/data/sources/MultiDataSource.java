/*     */ package jml.data.sources;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiDataSource
/*     */   extends DataSource
/*     */ {
/*  43 */   protected DataSource[] sources = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiDataSource(DataSource[] _sources) {
/*  52 */     this.sources = _sources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  59 */     for (int i = 0; i < this.sources.length; i++) {
/*  60 */       this.sources[i].close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  69 */     this.m = 0;
/*  70 */     for (int i = 0; i < this.sources.length; i++) {
/*  71 */       this.m += this.sources[i].size();
/*     */     }
/*  73 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   protected class Index
/*     */   {
/*     */     public int source;
/*     */     
/*     */     public int pos;
/*     */     private final MultiDataSource this$0;
/*     */     
/*     */     public Index(MultiDataSource this$0, int m, DataSource[] sources, int index) {
/*  85 */       this.this$0 = this$0; this.source = -1;
/*     */       this.pos = -1;
/*  87 */       if (index >= 0 && index < m) {
/*  88 */         int i = 0;
/*  89 */         while (i < sources.length && index >= sources[i].size()) {
/*  90 */           index -= sources[i].size();
/*  91 */           i++;
/*     */         } 
/*  93 */         this.source = i;
/*  94 */         this.pos = index;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector index) {
/* 105 */     int s = size();
/* 106 */     int m = index.size();
/* 107 */     Vector[] sub_index = new Vector[numberOfDataSources()];
/* 108 */     for (int i = 0; i < sub_index.length; ) { sub_index[i] = new Vector(); i++; }
/* 109 */      Enumeration iter = index.elements();
/* 110 */     while (iter.hasMoreElements()) {
/* 111 */       Integer x = iter.nextElement();
/* 112 */       Index idx = new Index(this, s, this.sources, x.intValue());
/* 113 */       if (idx.source != -1) {
/* 114 */         sub_index[idx.source].add(x);
/*     */       }
/*     */     } 
/* 117 */     DataSource[] sub_sources = new DataSource[sub_index.length];
/* 118 */     for (int j = 0; j < sub_sources.length; j++) {
/* 119 */       sub_sources[j] = this.sources[j].get(sub_index[j]);
/*     */     }
/* 121 */     return new MultiDataSource(sub_sources);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int index) {
/* 130 */     Data rec = null;
/* 131 */     if (index >= 0 && index < size()) {
/* 132 */       int i = 0;
/* 133 */       while (i < this.sources.length && index >= this.sources[i].size()) {
/* 134 */         index -= this.sources[i].size();
/* 135 */         i++;
/*     */       } 
/* 137 */       rec = this.sources[i].get(index);
/*     */     } 
/* 139 */     return rec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 146 */     return new MultiDataSourceEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numberOfDataSources() {
/* 153 */     int k = 0;
/* 154 */     if (this.sources != null) k = this.sources.length; 
/* 155 */     return k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource getSource(int index) {
/* 164 */     DataSource s = null;
/* 165 */     if (this.sources != null && 0 <= index && index < this.sources.length) {
/* 166 */       s = this.sources[index];
/*     */     }
/* 168 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class MultiDataSourceEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected int current_source;
/*     */ 
/*     */     
/*     */     protected Enumeration current_iter;
/*     */ 
/*     */     
/*     */     private final MultiDataSource this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public MultiDataSourceEnumeration(MultiDataSource this$0) {
/* 187 */       this.this$0 = this$0; this.current_source = 0; this.current_iter = null;
/* 188 */       if (this$0.size() > 0) {
/* 189 */         this.current_iter = this$0.getSource(0).elements();
/*     */       } else {
/*     */         
/* 192 */         this.current_iter = (new Vector()).elements();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 202 */       while (!this.current_iter.hasMoreElements() && this.current_source < this.this$0.numberOfDataSources() - 1) {
/* 203 */         this.current_source++;
/* 204 */         this.current_iter = this.this$0.getSource(this.current_source).elements();
/*     */       } 
/* 206 */       return this.current_iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 214 */       return this.current_iter.nextElement();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\MultiDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
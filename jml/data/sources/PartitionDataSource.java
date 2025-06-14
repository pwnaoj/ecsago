/*     */ package jml.data.sources;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.random.Partition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartitionDataSource
/*     */   extends DataSource
/*     */ {
/*  45 */   protected DataSource source = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected Partition index = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected int group = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean use_group = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DataSource aux;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PartitionDataSource(DataSource _source, Partition _index, int _group, boolean _use_group) {
/*  74 */     this.source = _source;
/*  75 */     this.index = _index;
/*  76 */     init(_group, _use_group);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(int _group, boolean _use_group) {
/*  86 */     this.group = _group;
/*  87 */     this.use_group = _use_group;
/*  88 */     Vector v = new Vector();
/*  89 */     PartitionEnumeration iter = (PartitionEnumeration)elements();
/*  90 */     while (iter.hasMoreElements()) {
/*  91 */       iter.nextElement();
/*  92 */       v.add(iter.p_index);
/*     */     } 
/*  94 */     this.aux = new SamplingDataSource(this.source, v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 102 */     if (this.use_group) {
/* 103 */       this.m = this.index.groupSize(this.group);
/*     */     } else {
/* 105 */       this.m = this.source.size() - this.index.groupSize(this.group);
/*     */     } 
/* 107 */     return this.m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 115 */     if (this.source != null) this.source.close();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector index) {
/* 124 */     return this.aux.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int i) {
/* 135 */     return this.aux.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 143 */     return new PartitionEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class PartitionEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected Integer p_index;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Enumeration iter;
/*     */ 
/*     */ 
/*     */     
/*     */     private final PartitionDataSource this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PartitionEnumeration(PartitionDataSource this$0) {
/* 166 */       this.this$0 = this$0; this.iter = null;
/* 167 */       if (this$0.use_group) {
/* 168 */         this.iter = this$0.index.getGroup(this$0.group);
/*     */       } else {
/* 170 */         this.iter = this$0.index.skipGroup(this$0.group);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 179 */       return this.iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 187 */       this.p_index = this.iter.nextElement();
/* 188 */       return this.this$0.source.get(this.p_index.intValue());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\PartitionDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ 
/*     */ public class SamplingDataSource
/*     */   extends DataSource
/*     */ {
/*  44 */   protected DataSource source = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Vector index = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SamplingDataSource(DataSource _source, Vector _index) {
/*  59 */     this.source = _source;
/*  60 */     if (_index != null) {
/*  61 */       this.index = _index;
/*     */     } else {
/*  63 */       this.index = new Vector();
/*     */     } 
/*  65 */     this.m = this.index.size();
/*     */   }
/*     */   
/*     */   protected SamplingDataSource(DataSource _source) {
/*  69 */     this.source = _source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  76 */     if (this.source != null) this.source.close();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector xindex) {
/*  85 */     Vector v = new Vector();
/*  86 */     Enumeration iter = xindex.elements();
/*  87 */     while (iter.hasMoreElements()) {
/*  88 */       Integer i = iter.nextElement();
/*  89 */       i = this.index.elementAt(i.intValue());
/*  90 */       v.add(i);
/*     */     } 
/*  92 */     return this.source.get(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int i) {
/* 101 */     Data d = null;
/* 102 */     if (this.source != null)
/* 103 */       if (this.index != null) {
/* 104 */         if (i >= 0 && i < this.index.size())
/* 105 */           d = this.source.get(((Integer)this.index.elementAt(i)).intValue()); 
/*     */       } else {
/* 107 */         d = this.source.get(i);
/*     */       }  
/* 109 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 117 */     return new SamplingEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class SamplingEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected Enumeration iter;
/*     */ 
/*     */     
/*     */     private final SamplingDataSource this$0;
/*     */ 
/*     */     
/*     */     public SamplingEnumeration(SamplingDataSource this$0) {
/* 132 */       this.this$0 = this$0; this.iter = null;
/* 133 */       this.iter = this$0.index.elements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 141 */       return this.iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 149 */       return this.this$0.source.get(((Integer)this.iter.nextElement()).intValue());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\SamplingDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
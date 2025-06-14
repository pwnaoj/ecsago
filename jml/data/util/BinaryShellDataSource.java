/*     */ package jml.data.util;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.sources.SamplingDataSource;
/*     */ import jml.data.sources.VectorDataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BinaryShellDataSource
/*     */   extends DataSource
/*     */ {
/*     */   protected DataSource real_data_source;
/*     */   protected int[] class_type;
/*     */   
/*     */   public BinaryShellDataSource(DataSource _real_data_source, int[] _class_type) {
/*  70 */     this.real_data_source = _real_data_source;
/*  71 */     this.class_type = _class_type;
/*  72 */     Enumeration iter = this.real_data_source.elements();
/*  73 */     this.m = 0;
/*  74 */     while (iter.hasMoreElements()) {
/*  75 */       Data d = iter.nextElement();
/*  76 */       if (this.class_type[d.getLabel()] != 0) {
/*  77 */         this.m++;
/*     */       }
/*     */     } 
/*  80 */     if (this.m < VectorDataSource.MAX_SIZE) {
/*  81 */       Vector v = new Vector();
/*  82 */       iter = this.real_data_source.elements();
/*  83 */       while (iter.hasMoreElements()) {
/*  84 */         Data d = iter.nextElement();
/*  85 */         if (this.class_type[d.getLabel()] != 0) {
/*  86 */           v.add(d);
/*     */         }
/*     */       } 
/*  89 */       this.real_data_source = (DataSource)new VectorDataSource(v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/*  98 */     return new BinaryShellDataSourceEnumeration(this, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(Data d) {
/* 107 */     d = (Data)Cloner.clone(d);
/* 108 */     int type = this.class_type[d.getLabel()];
/* 109 */     if (type < 0)
/* 110 */     { d.setLabel(0); }
/*     */     
/* 112 */     else if (type > 0) { d.setLabel(1); }
/* 113 */     else { d.setLabel(-1); }
/*     */     
/* 115 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int index) {
/* 124 */     return get(this.real_data_source.get(index));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector index) {
/* 134 */     return (DataSource)new SamplingDataSource(this, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 140 */     this.real_data_source.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class BinaryShellDataSourceEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected BinaryShellDataSource source;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Enumeration inner_iter;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object _next;
/*     */ 
/*     */     
/*     */     private final BinaryShellDataSource this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public BinaryShellDataSourceEnumeration(BinaryShellDataSource this$0, BinaryShellDataSource _source) {
/* 165 */       this.this$0 = this$0; this.source = null; this.inner_iter = null; this._next = null;
/* 166 */       this.source = _source;
/* 167 */       this.inner_iter = this.source.real_data_source.elements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void look_next() {
/* 176 */       this._next = null;
/* 177 */       while (this.inner_iter.hasMoreElements() && this._next == null) {
/* 178 */         Data d = this.inner_iter.nextElement();
/* 179 */         d = (Data)Cloner.clone(d);
/* 180 */         int type = d.getLabel();
/* 181 */         if (this.source.class_type[type] != 0) {
/* 182 */           if (this.source.class_type[type] < 0) {
/* 183 */             d.setLabel(0);
/*     */           } else {
/* 185 */             d.setLabel(1);
/*     */           } 
/* 187 */           this._next = d;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 198 */       look_next();
/* 199 */       return (this._next != null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 207 */       return this._next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\BinaryShellDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
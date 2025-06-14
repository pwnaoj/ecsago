/*     */ package jml.data.transformation;
/*     */ 
/*     */ import java.util.Enumeration;
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
/*     */ public class TransformedDataSource
/*     */   extends DataSource
/*     */ {
/*  41 */   protected DataSource source = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected DataTransformation transformation = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean inverse = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformedDataSource(DataSource _source, DataTransformation _transformation, boolean _inverse) {
/*  60 */     this.source = _source;
/*  61 */     this.inverse = _inverse;
/*  62 */     this.transformation = _transformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  69 */     if (this.source != null) this.source.close();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  76 */     return this.source.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int i) {
/*  84 */     if (this.inverse) return this.transformation.inverse(this.source.get(i)); 
/*  85 */     return this.transformation.apply(this.source.get(i));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/*  93 */     return new TransformedEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class TransformedEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected Enumeration iter;
/*     */     
/*     */     private final TransformedDataSource this$0;
/*     */ 
/*     */     
/*     */     public TransformedEnumeration(TransformedDataSource this$0) {
/* 107 */       this.this$0 = this$0; this.iter = null;
/* 108 */       this.iter = this$0.source.elements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 116 */       return this.iter.hasMoreElements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 124 */       if (this.this$0.inverse) {
/* 125 */         return this.this$0.transformation.inverse(this.iter.nextElement());
/*     */       }
/* 127 */       return this.this$0.transformation.apply(this.iter.nextElement());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\TransformedDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
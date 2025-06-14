/*     */ package jml.data;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.data.sources.SamplingDataSource;
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
/*     */ public abstract class DataSource
/*     */ {
/*     */   protected int m;
/*     */   
/*     */   public DataSource() {
/*  47 */     this.m = 0; } public DataSource(int _m) {
/*  48 */     this.m = _m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector index) {
/*  57 */     return (DataSource)new SamplingDataSource(this, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Data get(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Enumeration elements();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  83 */     return this.m;
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
/*     */   public void save(String fileName, boolean PRINT_LABEL) {
/*     */     
/* 101 */     try { FileWriter file = new FileWriter(fileName);
/* 102 */       Data d = get(0);
/* 103 */       if (d.get() instanceof RealVector) {
/* 104 */         RealVector rv = (RealVector)d.get();
/* 105 */         Components c = rv.get();
/* 106 */         file.write(size() + " " + rv.dimension() + " ");
/* 107 */         if (c instanceof jml.algebra.SparseComponents) {
/* 108 */           file.write("1 0\n");
/*     */         } else {
/* 110 */           file.write("0 0\n");
/*     */         } 
/*     */       } 
/* 113 */       Enumeration iter = elements();
/* 114 */       while (iter.hasMoreElements()) {
/* 115 */         d = iter.nextElement();
/* 116 */         file.write(d.toString(PRINT_LABEL) + "\n");
/*     */       } 
/* 118 */       file.close(); }
/* 119 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector classSamples(int classId) {
/* 128 */     Vector v = new Vector();
/* 129 */     int i = 0;
/* 130 */     Enumeration iter = elements();
/* 131 */     while (iter.hasMoreElements()) {
/* 132 */       Data d = iter.nextElement();
/* 133 */       if (d.getLabel() == classId) v.add(new Integer(i)); 
/* 134 */       i++;
/*     */     } 
/* 136 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource getClass(int classId) {
/* 145 */     return get(classSamples(classId));
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\DataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
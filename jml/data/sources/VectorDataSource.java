/*     */ package jml.data.sources;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
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
/*     */ public class VectorDataSource
/*     */   extends DataSource
/*     */   implements Serializable
/*     */ {
/*  46 */   public static int MAX_SIZE = 30000000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Vector data = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VectorDataSource() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VectorDataSource(Vector _data) {
/*  64 */     super(_data.size());
/*  65 */     this.data = _data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Data get(int index) {
/*  74 */     return this.data.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(Data x) {
/*  85 */     this.data.add(x);
/*  86 */     this.m++;
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  92 */     this.data.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource get(Vector index) {
/* 106 */     Vector v = null;
/* 107 */     if (index != null && index.size() > 0) {
/* 108 */       v = new Vector();
/* 109 */       Enumeration iter = index.elements();
/* 110 */       while (iter.hasMoreElements()) {
/* 111 */         Data d = get(((Integer)iter.nextElement()).intValue());
/* 112 */         if (d != null) {
/* 113 */           v.add(d);
/*     */         }
/*     */       } 
/*     */     } 
/* 117 */     return new VectorDataSource(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 126 */     return this.data.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static VectorDataSource loadBinary(String fileName) {
/*     */     try {
/* 137 */       FileInputStream os = new FileInputStream(fileName);
/* 138 */       ObjectInputStream obj = new ObjectInputStream(os);
/* 139 */       Object newObject = obj.readObject();
/* 140 */       if (newObject instanceof VectorDataSource) {
/* 141 */         return (VectorDataSource)newObject;
/*     */       }
/* 143 */       obj.close();
/* 144 */     } catch (Exception e) {
/* 145 */       e.printStackTrace();
/*     */     } 
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveBinary(String fileName) {
/*     */     try {
/* 156 */       FileOutputStream os = new FileOutputStream(fileName);
/* 157 */       ObjectOutputStream obj = new ObjectOutputStream(os);
/* 158 */       obj.writeObject(this);
/* 159 */       os.close();
/* 160 */     } catch (Exception e) {
/* 161 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\sources\VectorDataSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
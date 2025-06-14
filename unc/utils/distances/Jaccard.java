/*     */ package unc.utils.distances;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.Components;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.algebra.SparseValue;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSource;
/*     */ import jml.data.util.DataSourceUtils;
/*     */ import jml.util.IntUtil;
/*     */ import jml.util.quasimetric.QuasiMetric;
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
/*     */ public class Jaccard
/*     */   extends QuasiMetric
/*     */ {
/*     */   public double distance(Object x, Object y) {
/*  42 */     double sim = similarity(x, y);
/*  43 */     return (1.0D - sim) * (1.0D - sim);
/*     */   }
/*     */   
/*     */   public double similarity(Object x, Object y) {
/*  47 */     RealVector rx = (RealVector)x;
/*  48 */     RealVector ry = (RealVector)y;
/*  49 */     Components cx = rx.get();
/*  50 */     Components cy = ry.get();
/*  51 */     Vector sx = cx.sparce_values();
/*  52 */     Vector sy = cy.sparce_values();
/*  53 */     double num = 0.0D;
/*  54 */     double xv = 0.0D;
/*  55 */     double yv = 0.0D;
/*     */ 
/*     */     
/*  58 */     if (sx == null || sy == null) {
/*  59 */       int min = Math.min(rx.dimension(), ry.dimension());
/*  60 */       for (int i = 0; i < rx.dimension(); i++) {
/*  61 */         double xx = rx.get(i);
/*  62 */         double yy = ry.get(i);
/*  63 */         num += xx * yy;
/*  64 */         xv += xx * xx;
/*  65 */         yv += yy * yy;
/*     */       } 
/*     */     } else {
/*  68 */       Vector vx = new Vector();
/*  69 */       Vector vy = new Vector();
/*  70 */       SparseValue ax = null;
/*  71 */       SparseValue by = null;
/*  72 */       Enumeration iter1 = sx.elements();
/*  73 */       Enumeration iter2 = sy.elements();
/*  74 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/*  75 */         if (ax == null) ax = iter1.nextElement(); 
/*  76 */         if (by == null) by = iter2.nextElement(); 
/*  77 */         if (ax.index == by.index) {
/*  78 */           double xx = ax.value;
/*  79 */           double yy = by.value;
/*  80 */           num += xx * yy;
/*  81 */           xv += xx * xx;
/*  82 */           yv += yy * yy;
/*  83 */           ax = null;
/*  84 */           by = null; continue;
/*     */         } 
/*  86 */         if (ax.index < by.index) {
/*  87 */           xv += ax.value * ax.value;
/*  88 */           ax = null; continue;
/*     */         } 
/*  90 */         yv += by.value * by.value;
/*  91 */         by = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  96 */       if (ax != null) {
/*  97 */         xv += ax.value * ax.value;
/*     */       }
/*     */       
/* 100 */       if (by != null) {
/* 101 */         yv += by.value * by.value;
/*     */       }
/*     */       
/* 104 */       while (iter1.hasMoreElements()) {
/* 105 */         ax = iter1.nextElement();
/* 106 */         xv += ax.value * ax.value;
/*     */       } 
/* 108 */       while (iter2.hasMoreElements()) {
/* 109 */         by = iter2.nextElement();
/* 110 */         yv += by.value * by.value;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return num / (xv + yv - num);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 118 */     Jaccard jaccard = new Jaccard();
/* 119 */     String file = "C:/cygwin/home/eleon/jbproject/clustering/datasets/WebMining/mini-newsgroup/binary.txt";
/* 120 */     int[] count = new int[11];
/* 121 */     for (int i = 0; i < 11; i++) {
/* 122 */       count[i] = 0;
/*     */     }
/*     */     
/* 125 */     DataSource source = DataSourceUtils.load_old_format(file, IntUtil.MAX_INT); int j;
/* 126 */     for (j = 0; j < source.size(); j++) {
/* 127 */       Object x = source.get(j).get();
/* 128 */       for (int m = 0; m < source.size(); m++) {
/* 129 */         Object y = source.get(m).get();
/* 130 */         int range = (int)(jaccard.similarity(x, y) * 10.0D);
/* 131 */         count[range] = count[range] + 1;
/*     */       } 
/*     */     } 
/* 134 */     for (j = 0; j < 11; j++) {
/* 135 */       System.out.println(j + " = " + count[j]);
/*     */     }
/*     */     
/* 138 */     double s = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 142 */     try { FileWriter writer = new FileWriter("C:/cygwin/home/eleon/jbproject/clustering/datasets/WebMining/mini-newsgroup/binary.txt");
/* 143 */       String out = "1969 395\n";
/* 144 */       writer.write(out);
/*     */       
/* 146 */       for (int m = 0; m < 1969; m++) {
/* 147 */         RealVector rv = (RealVector)source.get(m).get();
/* 148 */         for (int n = 0; n < rv.dimension(); n++) {
/* 149 */           if (rv.get(n) > 0.0D) {
/* 150 */             rv.set(n, 1.0D);
/*     */           }
/*     */         } 
/* 153 */         Data data = new Data(rv);
/* 154 */         writer.write(data.toString() + "\n");
/*     */       } 
/* 156 */       writer.close(); }
/* 157 */     catch (Exception e) { e.printStackTrace(); }
/*     */ 
/*     */     
/* 160 */     System.out.println("column 1 =" + s);
/* 161 */     for (int k = 1967; k < 1968; k++) {
/* 162 */       double c = 0.0D;
/* 163 */       RealVector rv = (RealVector)source.get(k).get();
/* 164 */       for (int m = 0; m < rv.dimension(); m++) {
/* 165 */         c += rv.get(m);
/*     */       }
/* 167 */       System.out.println(source.get(k) + " " + c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double max_0_1(int n) {
/* 178 */     return 1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\distances\Jaccard.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
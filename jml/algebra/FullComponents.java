/*     */ package jml.algebra;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FullComponents
/*     */   extends Components
/*     */   implements Cloneable
/*     */ {
/*  18 */   protected double[] val = null;
/*     */   
/*     */   public FullComponents(double[] _val) {
/*  21 */     this.val = _val;
/*     */   }
/*     */   
/*     */   public double get(int index) {
/*  25 */     return this.val[index];
/*     */   }
/*     */   
/*     */   public void set(int index, double x) {
/*  29 */     this.val[index] = x;
/*     */   }
/*     */   public double[] get() {
/*  32 */     return this.val;
/*     */   } public Vector sparce_values() {
/*  34 */     return null;
/*     */   }
/*     */   public double[] full_values() {
/*  37 */     int m = this.val.length;
/*  38 */     double[] xval = new double[m];
/*  39 */     for (int i = 0; i < m; ) { xval[i] = this.val[i]; i++; }
/*  40 */      return xval;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  44 */     return new FullComponents(full_values());
/*     */   }
/*     */   public int dimension() {
/*  47 */     return this.val.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components substract(double x) {
/*  56 */     int n = this.val.length;
/*  57 */     for (int i = 0; i < n; i++) {
/*  58 */       this.val[i] = this.val[i] - x;
/*     */     }
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components sum(double x) {
/*  69 */     int n = this.val.length;
/*  70 */     for (int i = 0; i < n; i++) {
/*  71 */       this.val[i] = this.val[i] + x;
/*     */     }
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components multiply(double x) {
/*  82 */     int n = this.val.length;
/*  83 */     for (int i = 0; i < n; i++) {
/*  84 */       this.val[i] = this.val[i] * x;
/*     */     }
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components divide(double x) {
/*  95 */     int n = this.val.length;
/*  96 */     for (int i = 0; i < n; i++) {
/*  97 */       this.val[i] = this.val[i] / x;
/*     */     }
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components pow(double x) {
/* 108 */     int n = this.val.length;
/* 109 */     for (int i = 0; i < n; i++) {
/* 110 */       this.val[i] = Math.pow(this.val[i], x);
/*     */     }
/* 112 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components sum(Components y) {
/* 121 */     if (y instanceof FullComponents) {
/* 122 */       int n = dimension();
/* 123 */       FullComponents z = (FullComponents)y;
/* 124 */       for (int i = 0; i < n; i++) {
/* 125 */         this.val[i] = this.val[i] + z.val[i];
/*     */       }
/*     */     } else {
/*     */       
/* 129 */       Enumeration iter = y.sparce_values().elements();
/* 130 */       while (iter.hasMoreElements()) {
/* 131 */         SparseValue z = iter.nextElement();
/* 132 */         this.val[z.index] = this.val[z.index] + z.value;
/*     */       } 
/*     */     } 
/* 135 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components substract(Components y) {
/* 144 */     if (y instanceof FullComponents) {
/* 145 */       int n = dimension();
/* 146 */       FullComponents z = (FullComponents)y;
/* 147 */       for (int i = 0; i < n; i++) {
/* 148 */         this.val[i] = this.val[i] - z.val[i];
/*     */       }
/*     */     } else {
/*     */       
/* 152 */       Enumeration iter = y.sparce_values().elements();
/* 153 */       while (iter.hasMoreElements()) {
/* 154 */         SparseValue z = iter.nextElement();
/* 155 */         this.val[z.index] = this.val[z.index] - z.value;
/*     */       } 
/*     */     } 
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components direct_product(Components y) {
/* 167 */     if (y instanceof FullComponents) {
/* 168 */       int n = dimension();
/* 169 */       FullComponents z = (FullComponents)y;
/* 170 */       for (int i = 0; i < n; i++) {
/* 171 */         this.val[i] = this.val[i] * z.val[i];
/*     */       }
/* 173 */       return this;
/*     */     } 
/* 175 */     return ((Components)Cloner.clone(y)).direct_product(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public double max() {
/* 180 */     int n = dimension();
/* 181 */     double x = Double.NaN;
/* 182 */     if (n > 0) {
/* 183 */       x = this.val[0];
/* 184 */       for (int i = 1; i < n; i++) {
/* 185 */         if (x < this.val[i]) x = this.val[i]; 
/*     */       } 
/*     */     } 
/* 188 */     return x;
/*     */   }
/*     */   
/*     */   public double min() {
/* 192 */     int n = dimension();
/* 193 */     double x = Double.NaN;
/* 194 */     if (n > 0) {
/* 195 */       x = this.val[0];
/* 196 */       for (int i = 1; i < n; i++) {
/* 197 */         if (x > this.val[i]) x = this.val[i]; 
/*     */       } 
/*     */     } 
/* 200 */     return x;
/*     */   }
/*     */   
/*     */   public double summation() {
/* 204 */     int n = dimension();
/* 205 */     double x = 0.0D;
/* 206 */     for (int i = 1; i < n; ) { x += this.val[i]; i++; }
/* 207 */      return x;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 211 */     int n = dimension();
/* 212 */     StringBuffer sb = new StringBuffer();
/* 213 */     for (int i = 0; i < n; i++) {
/* 214 */       sb.append(" ");
/* 215 */       sb.append(this.val[i]);
/*     */     } 
/* 217 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\FullComponents.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
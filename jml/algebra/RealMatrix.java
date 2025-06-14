/*     */ package jml.algebra;
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
/*     */ public class RealMatrix
/*     */ {
/*  41 */   protected int n = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected int m = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public double[][] data = (double[][])null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealMatrix(int _n, int _m) {
/*  59 */     if (_n > 0 && _m > 0) {
/*  60 */       this.n = _n;
/*  61 */       this.m = _m;
/*  62 */       this.data = new double[this.n][this.m];
/*  63 */       for (int i = 0; i < this.n; i++) {
/*  64 */         for (int j = 0; j < this.m; j++) {
/*  65 */           this.data[i][j] = 0.0D;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealMatrix(RealMatrix source) {
/*  76 */     if (source != null) {
/*  77 */       this.n = source.n;
/*  78 */       this.m = source.m;
/*  79 */       this.data = new double[this.n][this.m];
/*  80 */       for (int i = 0; i < this.n; i++) {
/*  81 */         for (int j = 0; j < this.m; j++) {
/*  82 */           this.data[i][j] = source.data[i][j];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealMatrix(int _n) {
/*  94 */     if (_n > 0) {
/*  95 */       this.n = _n;
/*  96 */       this.m = _n;
/*  97 */       this.data = new double[this.n][this.n];
/*  98 */       for (int i = 0; i < this.n; i++) {
/*  99 */         for (int j = 0; j < this.m; j++) {
/* 100 */           this.data[i][j] = 0.0D;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealMatrix(RealVector x) {
/* 112 */     this.n = x.dimension();
/* 113 */     this.m = 1;
/* 114 */     this.data = new double[this.n][this.m];
/* 115 */     for (int i = 0; i < this.n; i++) {
/* 116 */       this.data[i][0] = x.get(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int rows() {
/* 124 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int columns() {
/* 130 */     return this.m;
/*     */   }
/*     */   public RealVector getColumn(int i) {
/* 133 */     double[] x = new double[this.n];
/* 134 */     for (int j = 0; j < this.n; j++) {
/* 135 */       x[j] = this.data[j][i];
/*     */     }
/* 137 */     return new RealVector(x);
/*     */   }
/*     */   
/*     */   public RealVector getRow(int i) {
/* 141 */     double[] x = new double[this.m];
/* 142 */     for (int j = 0; j < this.m; j++) {
/* 143 */       x[j] = this.data[i][j];
/*     */     }
/* 145 */     return new RealVector(x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(RealMatrix other) {
/* 154 */     int n1 = Math.min(this.n, other.n);
/* 155 */     int m1 = Math.min(this.m, other.m);
/* 156 */     for (int i = 0; i < n1; i++) {
/* 157 */       for (int j = 0; j < m1; j++) {
/* 158 */         this.data[i][j] = this.data[i][j] + other.data[i][j];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(RealMatrix other) {
/* 169 */     int n1 = Math.min(this.n, other.n);
/* 170 */     int m1 = Math.min(this.m, other.m);
/* 171 */     for (int i = 0; i < n1; i++) {
/* 172 */       for (int j = 0; j < m1; j++) {
/* 173 */         this.data[i][j] = this.data[i][j] - other.data[i][j];
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void multiply(RealMatrix other) {
/* 179 */     if (other != null && other.rows() == columns()) {
/* 180 */       this.n = rows();
/* 181 */       this.m = other.columns();
/* 182 */       int c = columns();
/* 183 */       double[][] new_data = new double[this.n][this.m];
/* 184 */       for (int i = 0; i < this.n; i++) {
/* 185 */         for (int j = 0; j < this.m; j++) {
/* 186 */           double s = 0.0D;
/* 187 */           for (int k = 0; k < c; k++) {
/* 188 */             s += this.data[i][k] * other.data[k][j];
/*     */           }
/* 190 */           new_data[i][j] = s;
/*     */         } 
/*     */       } 
/* 193 */       this.data = new_data;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void transpose() {
/* 201 */     double[][] new_data = new double[this.m][this.n];
/* 202 */     for (int i = 0; i < this.m; i++) {
/* 203 */       for (int j = 0; j < this.n; j++) {
/* 204 */         new_data[i][j] = this.data[j][i];
/*     */       }
/*     */     } 
/* 207 */     this.data = new_data;
/* 208 */     int t = this.n;
/* 209 */     this.n = this.m;
/* 210 */     this.m = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void divide(double x) {
/* 219 */     if (x != 0.0D) {
/* 220 */       for (int i = 0; i < this.n; i++) {
/* 221 */         for (int j = 0; j < this.m; j++) {
/* 222 */           this.data[i][j] = this.data[i][j] / x;
/*     */         }
/*     */       } 
/*     */     } else {
/* 226 */       System.err.println("[RealMatrix]. Undefined division by zero.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void elements_square() {
/* 235 */     for (int i = 0; i < this.n; i++) {
/* 236 */       for (int j = 0; j < this.m; j++) {
/* 237 */         this.data[i][j] = this.data[i][j] * this.data[i][j];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void elements_sqrt() {
/* 247 */     for (int i = 0; i < this.n; i++) {
/* 248 */       for (int j = 0; j < this.m; j++) {
/* 249 */         this.data[i][j] = Math.sqrt(this.data[i][j]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double row_sum(int i) {
/* 260 */     double val = 0.0D;
/* 261 */     if (0 <= i && i < this.n)
/* 262 */       for (int j = 0; j < this.m; ) { val += this.data[i][j]; j++; }
/*     */        
/* 264 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double column_sum(int j) {
/* 273 */     double val = 0.0D;
/* 274 */     if (0 <= j && j < this.m)
/* 275 */       for (int i = 0; i < this.n; ) { val += this.data[i][j]; i++; }
/*     */        
/* 277 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double diagonal_sum() {
/* 286 */     int x = Math.min(this.n, this.m);
/* 287 */     double val = 0.0D;
/* 288 */     for (int i = 0; i < x; ) { val += this.data[i][i]; i++; }
/* 289 */      return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double get(int i, int j) {
/* 299 */     double x = 0.0D;
/* 300 */     if (this.data != null && 0 <= i && i < this.n && 0 <= j && j < this.m) {
/* 301 */       x = this.data[i][j];
/*     */     } else {
/* 303 */       System.err.print("[RealMatrix] Error. Index out of bound");
/*     */     } 
/* 305 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is_square() {
/* 313 */     return (this.n == this.m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is_symmetric() {
/* 321 */     boolean flag = is_square();
/* 322 */     if (flag) {
/* 323 */       for (int i = 0; flag && i < this.n - 1; i++) {
/* 324 */         for (int j = i + 1; flag && j < this.n; j++) {
/* 325 */           flag = (this.data[i][j] == this.data[j][i]);
/*     */         }
/*     */       } 
/*     */     }
/* 329 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is_diagonal() {
/* 337 */     boolean flag = is_symmetric();
/* 338 */     if (flag) {
/* 339 */       for (int i = 0; flag && i < this.n - 1; i++) {
/* 340 */         flag = (this.data[i][i] != 0.0D);
/* 341 */         for (int j = i + 1; flag && j < this.n; j++) {
/* 342 */           flag = (this.data[i][j] == 0.0D);
/*     */         }
/*     */       } 
/*     */     }
/* 346 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealVector solve(RealVector B) {
/* 356 */     RealVector y = new RealVector(B);
/* 357 */     RealVector x = null;
/* 358 */     int n = columns();
/* 359 */     if (B.dimension() == n && n == rows()) {
/* 360 */       RealMatrix M = new RealMatrix(this);
/* 361 */       int[] perm = new int[n]; int i;
/* 362 */       for (i = 0; i < n; i++) {
/* 363 */         perm[i] = i;
/*     */       }
/* 365 */       for (i = 0; i < n; i++) {
/* 366 */         int k = i;
/* 367 */         for (; k < n && Math.abs(M.data[k][k]) <= 1.0E-12D; k++);
/* 368 */         if (k < n) {
/* 369 */           if (k != i) {
/* 370 */             int t = perm[i];
/* 371 */             perm[i] = perm[k];
/* 372 */             perm[k] = t;
/*     */             
/* 374 */             double t2 = y.get(i);
/* 375 */             y.set(i, y.get(k));
/* 376 */             y.set(k, t2);
/* 377 */             for (int m = 0; m < n; m++) {
/* 378 */               t2 = M.data[i][m];
/* 379 */               M.data[i][m] = M.data[k][m];
/* 380 */               M.data[k][m] = t2;
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 388 */           double pivot = M.data[i][i]; int j;
/* 389 */           for (j = 0; j < n; j++) {
/* 390 */             M.data[i][j] = M.data[i][j] / pivot;
/*     */           }
/* 392 */           y.set(i, y.get(i) / pivot);
/* 393 */           for (k = 0; k < n; k++) {
/* 394 */             if (k != i) {
/* 395 */               pivot = M.data[k][i];
/* 396 */               for (j = 0; j < n; j++) {
/* 397 */                 M.data[k][j] = M.data[k][j] - pivot * M.data[i][j];
/*     */               }
/* 399 */               y.set(k, y.get(k) - pivot * y.get(i));
/*     */             }
/*     */           
/*     */           } 
/* 403 */         } else if (Math.abs(y.get(i)) > 1.0E-6D) {
/* 404 */           return null;
/*     */         } 
/*     */       } 
/*     */       
/* 408 */       x = y;
/*     */     } 
/* 410 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 418 */     String text = "" + this.n + " " + this.m + "\n";
/* 419 */     for (int i = 0; i < this.n; i++) {
/* 420 */       for (int j = 0; j < this.m; j++) {
/* 421 */         text = text + " " + this.data[i][j];
/*     */       }
/* 423 */       text = text + "\n";
/*     */     } 
/* 425 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RealMatrix identity(int n) {
/* 434 */     RealMatrix A = new RealMatrix(n);
/* 435 */     for (int i = 0; i < n; i++) {
/* 436 */       A.data[i][i] = 1.0D;
/*     */     }
/* 438 */     return A;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\RealMatrix.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
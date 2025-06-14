/*     */ package jml.algebra;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.util.sort.MergeSort;
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
/*     */ public class Eigen
/*     */ {
/*     */   protected RealVector values;
/*     */   protected RealMatrix vectors;
/*     */   protected RealMatrix A;
/*     */   public double EPSILON;
/*     */   
/*     */   public Eigen(RealMatrix _A) {
/*  47 */     this.EPSILON = 1.0E-6D; this.A = new RealMatrix(_A);
/*     */     evaluate();
/*  49 */   } public RealVector getEigenValues() { return this.values; } public double zero_check(double x) { if (Math.abs(x) < this.EPSILON) {
/*  50 */       return 0.0D;
/*     */     }
/*  52 */     return x; }
/*     */   
/*     */   public RealMatrix getEigenVectors() {
/*     */     return this.vectors;
/*     */   }
/*  57 */   public void evaluate_symmetric() { int n = this.A.rows();
/*  58 */     this.vectors = RealMatrix.identity(n);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     boolean flag = false;
/*     */     do {
/*  69 */       for (int p = 0; p < n - 1; p++) {
/*  70 */         for (int q = p + 1; q < n; q++) {
/*  71 */           if (this.A.data[p][q] != 0.0D) {
/*  72 */             RealMatrix J = Jacobi(p, q);
/*     */             
/*  74 */             double s = J.data[p][q];
/*  75 */             double c = J.data[p][p];
/*  76 */             double ss = s * s;
/*  77 */             double sc = s * c;
/*  78 */             double cc = c * c;
/*  79 */             double App = this.A.data[p][p];
/*  80 */             double Aqq = this.A.data[q][q];
/*  81 */             double Apq = this.A.data[p][q];
/*  82 */             for (int r = 0; r < n; r++) {
/*  83 */               if (r != p && r != q) {
/*  84 */                 double Arp = this.A.data[r][p];
/*  85 */                 double Arq = this.A.data[r][q];
/*  86 */                 this.A.data[r][p] = zero_check(c * Arp - s * Arq);
/*  87 */                 this.A.data[r][q] = zero_check(s * Arp + c * Arq);
/*  88 */                 this.A.data[p][r] = this.A.data[r][p];
/*  89 */                 this.A.data[q][r] = this.A.data[r][q];
/*     */               } 
/*     */             } 
/*  92 */             this.A.data[p][p] = zero_check(cc * App - 2.0D * sc * Apq + ss * Aqq);
/*  93 */             this.A.data[p][q] = zero_check(sc * (App - Aqq) + (cc - ss) * Apq);
/*  94 */             this.A.data[q][q] = zero_check(ss * App + 2.0D * sc * Apq + cc * Aqq);
/*  95 */             this.A.data[q][p] = this.A.data[p][q];
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
/* 107 */             this.vectors.multiply(J);
/*     */           } 
/*     */         } 
/*     */       } 
/* 111 */       flag = this.A.is_diagonal();
/* 112 */     } while (!flag);
/*     */     
/* 114 */     Vector v = new Vector();
/* 115 */     for (int i = 0; i < n; i++) {
/* 116 */       v.add(new EigenPair(this.A.data[i][i], this.vectors.getColumn(i)));
/*     */     }
/* 118 */     MergeSort sort = new MergeSort();
/* 119 */     sort.apply(v, false);
/*     */     
/* 121 */     double[] x = new double[n];
/* 122 */     for (int j = 0; j < n; j++) {
/* 123 */       EigenPair ep = v.get(j);
/* 124 */       x[j] = ep.value; this.A.data[j][j] = ep.value;
/* 125 */       for (int k = 0; k < n; k++) {
/* 126 */         this.vectors.data[k][j] = ep.vector.get(k);
/*     */       }
/*     */     } 
/* 129 */     this.values = new RealVector(x); }
/*     */   protected RealMatrix Jacobi(int p, int q) { RealMatrix J = RealMatrix.identity(this.A.rows()); double tau = (this.A.data[q][q] - this.A.data[p][p]) / 2.0D * this.A.data[p][q]; double tau_root = Math.sqrt(1.0D + tau * tau); double t = 1.0D / (Math.abs(tau) + tau_root); if (tau < 0.0D)
/*     */       t = -t;  double c = 1.0D / Math.sqrt(1.0D + t * t); double s = t * c; J.data[p][p] = c; J.data[q][q] = c; J.data[p][q] = s;
/*     */     J.data[q][p] = -s;
/* 133 */     return J; } public void evaluate() { if (this.A.is_symmetric()) {
/* 134 */       evaluate_symmetric();
/*     */     } else {
/* 136 */       System.out.println("EigenValues-Vectors implemented for symmetric matrices only");
/*     */     }  }
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 141 */     RealMatrix A = new RealMatrix(3);
/* 142 */     A.data[0][0] = 4.0D;
/* 143 */     A.data[1][0] = 7.0D; A.data[0][1] = 7.0D;
/* 144 */     A.data[2][0] = -3.0D; A.data[0][2] = -3.0D;
/* 145 */     A.data[1][1] = 2.0D;
/* 146 */     A.data[2][1] = 10.0D; A.data[1][2] = 10.0D;
/* 147 */     A.data[2][2] = 5.0D;
/* 148 */     Eigen e = new Eigen(A);
/* 149 */     System.out.println("Eigen values");
/* 150 */     System.out.println(e.getEigenValues().toString());
/* 151 */     System.out.println("Eigen vectors");
/* 152 */     System.out.println(e.getEigenVectors().toString());
/* 153 */     RealMatrix Ae = new RealMatrix(A);
/* 154 */     Ae.multiply(e.getEigenVectors());
/* 155 */     System.out.println("===========================");
/* 156 */     System.out.println(A);
/* 157 */     System.out.println(Ae);
/* 158 */     System.out.println("+++++++++++++++++++++++++++++++");
/* 159 */     RealMatrix ee = e.getEigenVectors();
/* 160 */     ee.multiply(e.A);
/* 161 */     System.out.println(ee.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\Eigen.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
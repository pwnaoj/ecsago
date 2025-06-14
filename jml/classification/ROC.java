/*     */ package jml.classification;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public class ROC
/*     */ {
/*  40 */   public Vector points = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ROC(ROC other) {
/*  47 */     this.points = new Vector();
/*  48 */     Enumeration iter = other.points.elements();
/*  49 */     while (iter.hasMoreElements()) {
/*  50 */       double[] p = iter.nextElement();
/*  51 */       double[] q = new double[3];
/*  52 */       q[0] = p[0];
/*  53 */       q[1] = p[1];
/*  54 */       q[2] = p[2];
/*  55 */       this.points.add(q);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ROC(Vector _points) {
/*  64 */     this.points = _points;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ROC() {
/*  71 */     this.points = new Vector();
/*  72 */     double[] p = new double[3];
/*  73 */     p[0] = 0.0D;
/*  74 */     p[1] = 0.0D;
/*  75 */     p[2] = 0.0D;
/*  76 */     this.points.add(p);
/*  77 */     p = new double[3];
/*  78 */     p[0] = 1.0D;
/*  79 */     p[1] = 1.0D;
/*  80 */     p[2] = 1.0D;
/*  81 */     this.points.add(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int search(double x) {
/*  91 */     int a = 0;
/*  92 */     int b = this.points.size() - 1;
/*  93 */     while (a <= b) {
/*  94 */       int m = (a + b) / 2;
/*  95 */       double[] q = this.points.elementAt(m);
/*  96 */       if (Math.abs(q[0] - x) <= 0.001D) {
/*  97 */         a = m;
/*  98 */         b = m - 1; continue;
/*     */       } 
/* 100 */       if (q[0] < x) {
/* 101 */         a = m + 1; continue;
/*     */       } 
/* 103 */       b = m - 1;
/*     */     } 
/*     */ 
/*     */     
/* 107 */     if (a < size() - 1) {
/* 108 */       double[] q1 = this.points.elementAt(a);
/* 109 */       double[] q2 = this.points.elementAt(a + 1);
/* 110 */       if (Math.abs(q2[0] - x) <= Math.abs(q1[0] - x) && Math.abs(q2[0] - x) <= 0.001D) {
/* 111 */         a++;
/*     */       }
/*     */     } 
/* 114 */     return a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(double[] p) {
/* 124 */     int a = search(p[0]);
/* 125 */     double[] q = this.points.elementAt(a);
/* 126 */     if (Math.abs(q[0] - p[0]) <= 0.001D) {
/* 127 */       if (p[1] > q[1]) {
/* 128 */         q[0] = p[0];
/* 129 */         q[1] = p[1];
/* 130 */         q[2] = p[2];
/*     */       }
/* 132 */       else if (p[1] == q[1]) {
/* 133 */         q[2] = Math.max(p[2], q[2]);
/*     */       } 
/*     */     } else {
/*     */       
/* 137 */       this.points.add(a, p);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(double x) {
/* 147 */     double y = 0.0D;
/* 148 */     int a = search(x);
/* 149 */     double[] p = this.points.elementAt(a);
/* 150 */     if (Math.abs(p[0] - x) <= 0.001D) {
/* 151 */       y = p[1];
/*     */     }
/* 153 */     else if (a > 0) {
/* 154 */       double[] q = this.points.elementAt(a - 1);
/* 155 */       y = q[1] + (p[1] - q[1]) * (x - q[0]) / (p[0] - q[0]);
/*     */     } else {
/* 157 */       double[] q = this.points.elementAt(a);
/* 158 */       if (x != 0.0D && q[0] != 0.0D) {
/* 159 */         y = q[1] * x / q[0];
/*     */       }
/*     */     } 
/*     */     
/* 163 */     return y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate_confidence(double x) {
/* 172 */     double y = 0.0D;
/* 173 */     int a = search(x);
/* 174 */     double[] p = this.points.elementAt(a);
/* 175 */     if (Math.abs(p[0] - x) <= 0.001D) {
/* 176 */       y = p[2];
/*     */     }
/* 178 */     else if (a > 0) {
/* 179 */       double[] q = this.points.elementAt(a - 1);
/* 180 */       y = q[2] + (p[2] - q[2]) * (x - q[0]) / (p[0] - q[0]);
/*     */     } else {
/* 182 */       double[] q = this.points.elementAt(a);
/* 183 */       if (x != 0.0D && q[0] != 0.0D) {
/* 184 */         y = q[2] * x / q[0];
/*     */       }
/*     */     } 
/*     */     
/* 188 */     return y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 195 */     return this.points.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] get(int i) {
/* 203 */     return this.points.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 211 */     StringBuffer sb = new StringBuffer();
/* 212 */     for (int i = 0; i < this.points.size(); i++) {
/* 213 */       double[] p = this.points.elementAt(i);
/* 214 */       sb.append(" ");
/* 215 */       sb.append(p[0]);
/* 216 */       sb.append(" ");
/* 217 */       sb.append(p[1]);
/* 218 */       sb.append(" ");
/* 219 */       sb.append(p[2]);
/* 220 */       sb.append("\n");
/*     */     } 
/* 222 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ROC sampling(int n) {
/* 231 */     Vector s = new Vector();
/* 232 */     for (int i = 0; i < n; i++) {
/* 233 */       double[] p = new double[3];
/* 234 */       p[0] = i / (n - 1);
/* 235 */       p[1] = evaluate(p[0]);
/* 236 */       p[2] = evaluate_confidence(p[0]);
/* 237 */       s.add(p);
/*     */     } 
/* 239 */     return new ROC(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 247 */     ROC r = new ROC(); int i;
/* 248 */     for (i = 0; i < 10; i++) {
/* 249 */       double[] p = new double[3];
/* 250 */       p[0] = Math.random();
/* 251 */       p[1] = Math.random();
/* 252 */       p[2] = Math.random();
/* 253 */       r.add(p);
/* 254 */       System.out.println(r.toString());
/*     */     } 
/*     */     
/* 257 */     for (i = 0; i < 10; i++) {
/* 258 */       double x = Math.random();
/* 259 */       System.out.println("(" + x + "," + r.evaluate(x) + ")");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\ROC.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
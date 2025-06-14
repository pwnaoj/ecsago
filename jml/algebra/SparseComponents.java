/*     */ package jml.algebra;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.util.sort.Sort;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SparseComponents
/*     */   extends Components
/*     */   implements Cloneable
/*     */ {
/*  18 */   protected int n = 0;
/*  19 */   protected Vector val = null;
/*     */   
/*     */   public double[] full_values() {
/*  22 */     double[] c = new double[this.n];
/*  23 */     for (int i = 0; i < this.n; i++) {
/*  24 */       c[i] = 0.0D;
/*     */     }
/*     */     
/*  27 */     Enumeration iter = this.val.elements();
/*  28 */     while (iter.hasMoreElements()) {
/*  29 */       SparseValue x = iter.nextElement();
/*  30 */       c[x.index] = x.value;
/*     */     } 
/*  32 */     return c;
/*     */   }
/*     */   
/*     */   protected FullComponents generate_full(double x) {
/*  36 */     double[] c = new double[this.n];
/*  37 */     for (int i = 0; i < this.n; i++) {
/*  38 */       c[i] = x;
/*     */     }
/*  40 */     return new FullComponents(c);
/*     */   }
/*     */   
/*     */   public SparseComponents(int _n, Vector _val) {
/*  44 */     this.n = _n;
/*  45 */     this.val = _val;
/*     */   }
/*     */   
/*     */   public double get(int index) {
/*  49 */     int i = Sort.findLow2High(this.val, new SparseValue(index, 0.0D));
/*  50 */     if (i >= this.val.size()) return 0.0D;
/*     */     
/*  52 */     SparseValue x = this.val.get(i);
/*  53 */     if (x.index == index) return x.value; 
/*  54 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(int index, double x) {
/*  59 */     SparseValue y = new SparseValue(index, x);
/*  60 */     int i = Sort.findLow2High(this.val, y);
/*  61 */     if (i >= this.val.size()) {
/*  62 */       if (x > 0.0D) {
/*  63 */         this.val.add(y);
/*     */       }
/*     */     } else {
/*  66 */       SparseValue z = this.val.get(i);
/*  67 */       if (z.index == index) {
/*  68 */         if (x > 0.0D) {
/*  69 */           z.value = x;
/*     */         } else {
/*  71 */           this.val.remove(i);
/*     */         }
/*     */       
/*  74 */       } else if (x > 0.0D) {
/*  75 */         this.val.add(i, y);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Vector sparce_values() {
/*  81 */     return this.val;
/*     */   }
/*     */   public Object clone() {
/*  84 */     Vector xval = Cloner.clone(this.val);
/*  85 */     return new SparseComponents(this.n, xval);
/*     */   }
/*     */   public int dimension() {
/*  88 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components substract(double x) {
/*  96 */     FullComponents comp = generate_full(-x);
/*     */     
/*  98 */     Enumeration iter = this.val.elements();
/*  99 */     while (iter.hasMoreElements()) {
/* 100 */       SparseValue y = iter.nextElement();
/* 101 */       comp.val[y.index] = comp.val[y.index] + y.value;
/*     */     } 
/* 103 */     return comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components sum(double x) {
/* 112 */     FullComponents comp = generate_full(x);
/*     */     
/* 114 */     Enumeration iter = this.val.elements();
/* 115 */     while (iter.hasMoreElements()) {
/* 116 */       SparseValue y = iter.nextElement();
/* 117 */       comp.val[y.index] = comp.val[y.index] + y.value;
/*     */     } 
/* 119 */     return comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components multiply(double x) {
/* 128 */     Enumeration iter = this.val.elements();
/* 129 */     while (iter.hasMoreElements()) {
/* 130 */       ((SparseValue)iter.nextElement()).value *= x;
/*     */     }
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components divide(double x) {
/* 141 */     Enumeration iter = this.val.elements();
/* 142 */     while (iter.hasMoreElements()) {
/* 143 */       ((SparseValue)iter.nextElement()).value /= x;
/*     */     }
/* 145 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components pow(double x) {
/* 154 */     if (x == 0.0D) {
/* 155 */       return generate_full(1.0D);
/*     */     }
/*     */     
/* 158 */     Enumeration iter = this.val.elements();
/* 159 */     while (iter.hasMoreElements()) {
/* 160 */       SparseValue y = iter.nextElement();
/* 161 */       y.value = Math.pow(y.value, x);
/*     */     } 
/* 163 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components sum(Components y) {
/* 173 */     if (y instanceof FullComponents) {
/* 174 */       int n = dimension();
/* 175 */       return ((Components)Cloner.clone(y)).sum(this);
/*     */     } 
/* 177 */     Vector v = new Vector();
/* 178 */     SparseValue a = null;
/* 179 */     SparseValue b = null;
/* 180 */     Enumeration iter1 = sparce_values().elements();
/* 181 */     Enumeration iter2 = y.sparce_values().elements();
/* 182 */     while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 183 */       if (a == null) a = iter1.nextElement(); 
/* 184 */       if (b == null) b = iter2.nextElement(); 
/* 185 */       if (a.index == b.index) {
/* 186 */         a.value += b.value;
/* 187 */         v.add(a);
/* 188 */         a = null;
/* 189 */         b = null; continue;
/*     */       } 
/* 191 */       if (a.index < b.index) {
/* 192 */         v.add(a);
/* 193 */         a = null; continue;
/*     */       } 
/* 195 */       v.add(Cloner.clone(b));
/* 196 */       b = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 201 */     if (a != null) v.add(a); 
/* 202 */     if (b != null) v.add(Cloner.clone(b));
/*     */     
/* 204 */     while (iter1.hasMoreElements()) {
/* 205 */       v.add(iter1.nextElement());
/*     */     }
/* 207 */     while (iter2.hasMoreElements()) {
/* 208 */       v.add(Cloner.clone(iter2.nextElement()));
/*     */     }
/* 210 */     this.val = v;
/*     */     
/* 212 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components substract(Components y) {
/* 221 */     if (y instanceof FullComponents) {
/* 222 */       FullComponents fc = generate_full(0.0D);
/* 223 */       return fc.sum(this).substract(y);
/*     */     } 
/* 225 */     Vector v = new Vector();
/* 226 */     SparseValue a = null;
/* 227 */     SparseValue b = null;
/* 228 */     Enumeration iter1 = sparce_values().elements();
/* 229 */     Enumeration iter2 = y.sparce_values().elements();
/* 230 */     while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 231 */       if (a == null) a = iter1.nextElement(); 
/* 232 */       if (b == null) b = iter2.nextElement(); 
/* 233 */       if (a.index == b.index) {
/* 234 */         a.value -= b.value;
/* 235 */         v.add(a);
/* 236 */         a = null; continue;
/*     */       } 
/* 238 */       if (a.index < b.index) {
/* 239 */         v.add(a);
/* 240 */         a = null; continue;
/*     */       } 
/* 242 */       b = (SparseValue)Cloner.clone(b);
/* 243 */       b.value *= -1.0D;
/* 244 */       v.add(b);
/* 245 */       b = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     if (a != null) v.add(a); 
/* 252 */     if (b != null) {
/* 253 */       b = (SparseValue)Cloner.clone(b);
/* 254 */       b.value *= -1.0D;
/* 255 */       v.add(b);
/*     */     } 
/*     */     
/* 258 */     while (iter1.hasMoreElements()) {
/* 259 */       v.add(iter1.nextElement());
/*     */     }
/* 261 */     while (iter2.hasMoreElements()) {
/* 262 */       SparseValue z = (SparseValue)Cloner.clone(iter2.nextElement());
/* 263 */       z.value *= -1.0D;
/* 264 */       v.add(z);
/*     */     } 
/* 266 */     this.val = v;
/*     */     
/* 268 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Components direct_product(Components y) {
/* 277 */     if (y instanceof FullComponents) {
/* 278 */       FullComponents z = (FullComponents)y;
/*     */       
/* 280 */       Enumeration iter = sparce_values().elements();
/* 281 */       while (iter.hasMoreElements()) {
/* 282 */         SparseValue a = iter.nextElement();
/* 283 */         a.value *= z.val[a.index];
/*     */       } 
/*     */     } else {
/* 286 */       Vector v = new Vector();
/* 287 */       SparseValue a = null;
/* 288 */       SparseValue b = null;
/* 289 */       Enumeration iter1 = sparce_values().elements();
/* 290 */       Enumeration iter2 = y.sparce_values().elements();
/* 291 */       while (iter1.hasMoreElements() && iter2.hasMoreElements()) {
/* 292 */         if (a == null) a = iter1.nextElement(); 
/* 293 */         if (b == null) b = iter2.nextElement(); 
/* 294 */         if (a.index == b.index) {
/* 295 */           a.value *= b.value;
/* 296 */           v.add(a);
/* 297 */           a = null;
/* 298 */           b = null; continue;
/*     */         } 
/* 300 */         if (a.index < b.index) {
/* 301 */           a = null; continue;
/*     */         } 
/* 303 */         b = null;
/*     */       } 
/*     */ 
/*     */       
/* 307 */       this.val = v;
/*     */     } 
/* 309 */     return this;
/*     */   }
/*     */   
/*     */   public double max() {
/* 313 */     double x = Double.NaN;
/* 314 */     int m = this.val.size();
/* 315 */     if (m > 0) {
/* 316 */       Enumeration iter = sparce_values().elements();
/* 317 */       x = ((SparseValue)iter.nextElement()).value;
/* 318 */       while (iter.hasMoreElements()) {
/* 319 */         double xn = ((SparseValue)iter.nextElement()).value;
/* 320 */         if (x < xn) x = xn; 
/*     */       } 
/*     */     } 
/* 323 */     return x;
/*     */   }
/*     */   
/*     */   public double min() {
/* 327 */     double x = Double.NaN;
/* 328 */     int m = this.val.size();
/* 329 */     if (m > 0) {
/* 330 */       Enumeration iter = sparce_values().elements();
/* 331 */       x = ((SparseValue)iter.nextElement()).value;
/* 332 */       while (iter.hasMoreElements()) {
/* 333 */         double xn = ((SparseValue)iter.nextElement()).value;
/* 334 */         if (x > xn) x = xn; 
/*     */       } 
/*     */     } 
/* 337 */     return x;
/*     */   }
/*     */   
/*     */   public double summation() {
/* 341 */     double x = 0.0D;
/* 342 */     Enumeration iter = sparce_values().elements();
/* 343 */     while (iter.hasMoreElements()) {
/* 344 */       x += ((SparseValue)iter.nextElement()).value;
/*     */     }
/* 346 */     return x;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 350 */     StringBuffer sb = new StringBuffer();
/* 351 */     Enumeration iter = sparce_values().elements();
/* 352 */     while (iter.hasMoreElements()) {
/* 353 */       sb.append(iter.nextElement().toString());
/* 354 */       sb.append(" ");
/*     */     } 
/* 356 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\SparseComponents.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
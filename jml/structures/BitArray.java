/*     */ package jml.structures;
/*     */ 
/*     */ import jml.random.UniformNumberGenerator;
/*     */ import jml.util.IntUtil;
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
/*     */ public class BitArray
/*     */   implements Cloneable
/*     */ {
/*  40 */   protected int[] data = null;
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected int n = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitArray(int _n, boolean randomly) {
/*  52 */     this.n = _n;
/*  53 */     int m = get_index(this.n) + 1;
/*  54 */     this.data = new int[m];
/*  55 */     if (randomly)
/*  56 */     { UniformNumberGenerator g = new UniformNumberGenerator((IntUtil.HIGHEST_BIT >>> 1));
/*  57 */       for (int i = 0; i < m; i++) {
/*  58 */         if (g.newBoolean()) { this.data[i] = g.newInt(); }
/*  59 */         else { this.data[i] = -g.newInt(); }
/*     */       
/*     */       }  }
/*  62 */     else { for (int i = 0; i < m; i++) {
/*  63 */         this.data[i] = 0;
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitArray(BitArray source) {
/*  73 */     if (source.data != null) {
/*  74 */       this.n = source.n;
/*  75 */       this.data = new int[source.data.length];
/*  76 */       for (int i = 0; i < source.data.length; i++) {
/*  77 */         this.data[i] = source.data[i];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitArray(boolean[] source) {
/*  87 */     this.n = source.length;
/*  88 */     int m = get_index(this.n) + 1;
/*  89 */     this.data = new int[m];
/*  90 */     for (int i = 0; i < this.n; i++) {
/*  91 */       set(i, source[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitArray(String source) {
/* 100 */     this.n = source.length();
/* 101 */     int m = get_index(this.n) + 1;
/* 102 */     this.data = new int[m];
/* 103 */     for (int i = 0; i < this.n; i++) {
/* 104 */       set(i, (source.charAt(i) == '1'));
/*     */     }
/*     */   }
/*     */   
/*     */   public static BitArray random(int limit) {
/* 109 */     int size = IntUtil.getBitsNumber(limit);
/* 110 */     UniformNumberGenerator g = new UniformNumberGenerator(limit);
/* 111 */     int[] d = { g.newInt() };
/* 112 */     BitArray a = new BitArray(size, true);
/* 113 */     a.data = d;
/* 114 */     return a;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 118 */     return new BitArray(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int get_index(int m) {
/* 128 */     return m >>> IntUtil.DIV_MASK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int get_bit(int m) {
/* 138 */     return m & IntUtil.MOD_MASK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int i, boolean v) {
/* 147 */     int m = get_index(i);
/* 148 */     int p = get_bit(i);
/* 149 */     int v_mask = IntUtil.HIGHEST_BIT >>> p;
/* 150 */     int d_mask = v_mask & this.data[m];
/* 151 */     if (v)
/* 152 */     { if (d_mask == 0) this.data[m] = this.data[m] | v_mask;
/*     */        }
/* 154 */     else if (d_mask != 0) { this.data[m] = this.data[m] ^ v_mask; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean get(int i) {
/* 164 */     int m = get_index(i);
/* 165 */     int p = get_bit(i);
/* 166 */     return ((IntUtil.HIGHEST_BIT >>> p & this.data[m]) != 0);
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
/*     */   public BitArray subBitArray(int start) {
/* 179 */     BitArray subArray = new BitArray(this);
/* 180 */     subArray.left_shift(start);
/* 181 */     subArray.n -= start;
/* 182 */     if (subArray.n < 0) subArray.n = 0; 
/* 183 */     return subArray;
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
/*     */   public BitArray subBitArray(int start, int end) {
/* 198 */     int length = end - start;
/* 199 */     BitArray subArray = subBitArray(start);
/* 200 */     if (subArray.n > length) subArray.n = length; 
/* 201 */     subArray.rightSetToZero(subArray.n);
/* 202 */     return subArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void left_shift(int k) {
/* 212 */     if (this.data != null) {
/* 213 */       rightSetToZero(this.n);
/* 214 */       int start_index = get_index(k);
/* 215 */       k = get_bit(k);
/* 216 */       if (k > 0)
/* 217 */       { int sup_k = IntUtil.INTEGER_SIZE - k;
/* 218 */         int m = this.data.length - 1;
/* 219 */         int j = 0; int i;
/* 220 */         for (i = start_index; i < m; i++) {
/* 221 */           this.data[j] = this.data[i] << k | this.data[i + 1] >>> sup_k;
/* 222 */           j++;
/*     */         } 
/* 224 */         this.data[j] = this.data[m] << k;
/* 225 */         for (i = j + 1; i < m; ) { this.data[i] = 0; i++; }
/*     */          }
/* 227 */       else { int j = 0; int i;
/* 228 */         for (i = start_index; i < this.data.length; i++) {
/* 229 */           this.data[j] = this.data[i];
/* 230 */           j++;
/*     */         } 
/* 232 */         for (i = j; i < this.data.length; ) { this.data[i] = 0; i++; }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void right_shift(int k) {
/* 244 */     if (this.data != null) {
/* 245 */       rightSetToZero(this.n);
/* 246 */       int start_index = get_index(k);
/* 247 */       k = get_bit(k);
/* 248 */       if (k > 0)
/* 249 */       { int sup_k = IntUtil.INTEGER_SIZE - k;
/* 250 */         int m = this.data.length - 1;
/* 251 */         int j = this.data.length - 1 - start_index; int i;
/* 252 */         for (i = this.data.length - 1; i > start_index; i--) {
/* 253 */           this.data[i] = this.data[j] >>> k | this.data[j - 1] << sup_k;
/* 254 */           j--;
/*     */         } 
/* 256 */         this.data[start_index] = this.data[0] >>> k;
/* 257 */         for (i = 0; i < start_index; ) { this.data[i] = 0; i++; }
/*     */          }
/* 259 */       else { int j = this.data.length - 1 - start_index; int i;
/* 260 */         for (i = this.data.length - 1; i >= start_index; i--) {
/* 261 */           this.data[i] = this.data[j];
/* 262 */           j--;
/*     */         } 
/* 264 */         for (i = 0; i < start_index; ) { this.data[i] = 0; i++; }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(BitArray source) {
/* 277 */     BitArray newBitArray = new BitArray(this.n + source.n, false);
/* 278 */     newBitArray.or(source);
/* 279 */     newBitArray.right_shift(this.n);
/* 280 */     newBitArray.or(this);
/* 281 */     this.data = newBitArray.data;
/* 282 */     this.n = newBitArray.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(boolean v) {
/* 290 */     if (this.data.length * IntUtil.INTEGER_SIZE == this.n) {
/* 291 */       int[] new_data = new int[this.data.length + 1];
/* 292 */       for (int i = 0; i < this.data.length; i++) {
/* 293 */         new_data[i] = this.data[i];
/*     */       }
/* 295 */       if (v) { new_data[this.data.length] = IntUtil.HIGHEST_BIT; }
/* 296 */       else { new_data[this.data.length] = 0; }
/* 297 */        this.data = new_data;
/*     */     } else {
/* 299 */       set(this.n, v);
/*     */     } 
/* 301 */     this.n++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void del() {
/* 308 */     del(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void del(int k) {
/* 316 */     this.n -= k;
/* 317 */     if (this.n > 0) {
/* 318 */       int m = get_index(this.n);
/* 319 */       if (m + 1 < this.data.length) {
/* 320 */         int[] new_data = new int[m + 1];
/* 321 */         for (int i = 0; i <= m; i++) {
/* 322 */           new_data[i] = this.data[i];
/*     */         }
/* 324 */         this.data = new_data;
/*     */       } 
/*     */     } else {
/* 327 */       this.n = 0;
/* 328 */       this.data = new int[1];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 336 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void zero() {
/* 342 */     for (int i = 0; i < this.data.length; i++) {
/* 343 */       this.data[i] = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rightSetToZero(int start) {
/* 354 */     int m = get_index(start);
/* 355 */     if (this.data != null && 0 <= m && m < this.data.length) {
/* 356 */       int mask, r = get_bit(start);
/*     */       
/* 358 */       if (r > 0) { mask = IntUtil.ONE_BITS << IntUtil.INTEGER_SIZE - r; } else { mask = 0; }
/* 359 */        this.data[m] = this.data[m] & mask;
/* 360 */       for (int i = m + 1; i < this.data.length; i++) {
/* 361 */         this.data[i] = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rightSetToOne(int start) {
/* 373 */     int m = get_index(start);
/* 374 */     if (0 <= m && m < this.data.length) {
/* 375 */       int r = get_bit(start);
/* 376 */       int mask = IntUtil.ONE_BITS >>> r;
/* 377 */       this.data[m] = this.data[m] | mask;
/* 378 */       for (int i = m + 1; i < this.data.length; i++) {
/* 379 */         this.data[i] = IntUtil.ONE_BITS;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void leftSetToZero(int end) {
/* 392 */     int m = get_index(end);
/* 393 */     if (0 <= m && m < this.data.length) {
/* 394 */       int r = get_bit(end);
/* 395 */       int mask = IntUtil.ONE_BITS >>> r;
/* 396 */       this.data[m] = this.data[m] & mask;
/* 397 */       for (int i = 0; i < m; i++) {
/* 398 */         this.data[i] = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void leftSetToOne(int end) {
/* 410 */     int m = get_index(end);
/* 411 */     if (0 <= m && m < this.data.length) {
/* 412 */       int r = get_bit(end);
/* 413 */       int mask = IntUtil.ONE_BITS << IntUtil.INTEGER_SIZE - r;
/* 414 */       this.data[m] = this.data[m] | mask;
/* 415 */       for (int i = 0; i < m; i++) {
/* 416 */         this.data[i] = IntUtil.ONE_BITS;
/*     */       }
/*     */     } 
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
/*     */   public void and(BitArray arg2) {
/* 430 */     arg2.rightSetToOne(arg2.n);
/* 431 */     int m = arg2.data.length;
/* 432 */     if (this.data.length < m) m = this.data.length; 
/* 433 */     for (int i = 0; i < m; i++) {
/* 434 */       this.data[i] = this.data[i] & arg2.data[i];
/*     */     }
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
/*     */   public void or(BitArray arg2) {
/* 447 */     arg2.rightSetToZero(arg2.n);
/* 448 */     int m = arg2.data.length;
/* 449 */     if (this.data.length < m) m = this.data.length; 
/* 450 */     for (int i = 0; i < m; i++) {
/* 451 */       this.data[i] = this.data[i] | arg2.data[i];
/*     */     }
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
/*     */   public void xor(BitArray arg2) {
/* 464 */     int m = arg2.data.length;
/* 465 */     if (this.data.length < m) m = this.data.length; 
/* 466 */     for (int i = 0; i < m; i++) {
/* 467 */       this.data[i] = this.data[i] ^ arg2.data[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void not() {
/* 477 */     for (int i = 0; i < this.data.length; i++) {
/* 478 */       this.data[i] = this.data[i] ^ IntUtil.ONE_BITS;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void not(int bit) {
/* 489 */     int m = get_index(bit);
/* 490 */     int p = get_bit(bit);
/* 491 */     this.data[m] = this.data[m] ^ IntUtil.HIGHEST_BIT >>> p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 501 */     String text = "";
/* 502 */     for (int i = 0; i < this.n; i++) {
/* 503 */       if (get(i)) { text = text + "1"; } else { text = text + "0"; }
/*     */     
/* 505 */     }  return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 515 */     boolean flag = obj instanceof BitArray;
/* 516 */     if (flag) {
/* 517 */       BitArray other = (BitArray)obj;
/* 518 */       int s = size();
/* 519 */       flag = (other.size() == s);
/* 520 */       if (flag && s > 0) {
/* 521 */         int i; for (i = 0; i < this.data.length - 1 && flag; i++) {
/* 522 */           flag = (this.data[i] == other.data[i]);
/*     */         }
/* 524 */         if (flag) {
/* 525 */           for (i = (this.data.length - 1) * IntUtil.INTEGER_SIZE; i < s && flag; i++) {
/* 526 */             flag = (get(i) == other.get(i));
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 531 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean useGrayCode = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(int i) {
/* 546 */     int x = this.data[i];
/* 547 */     if (useGrayCode) x = IntUtil.grayToBinary(x); 
/* 548 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int i, int value) {
/* 557 */     if (useGrayCode) {
/* 558 */       this.data[i] = IntUtil.binaryToGray(value);
/*     */     } else {
/* 560 */       this.data[i] = value;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\structures\BitArray.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
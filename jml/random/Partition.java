/*     */ package jml.random;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.parser.SimpleStreamTokenizer;
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
/*     */ public class Partition
/*     */   implements Serializable
/*     */ {
/*  46 */   protected int n = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected int[] start = new int[] { 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected Vector index = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Partition() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Partition(int _n, int m, boolean random) {
/*  72 */     this.n = _n;
/*  73 */     if (random) this.index = permutation(this.n); 
/*  74 */     create(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Partition(Vector _index, int m) {
/*  83 */     this.index = _index;
/*  84 */     this.n = this.index.size();
/*  85 */     create(m);
/*     */   }
/*     */   
/*     */   public Partition(Vector _index, int[] groups_size) {
/*  89 */     this.index = _index;
/*  90 */     this.n = this.index.size();
/*  91 */     create(groups_size);
/*     */   }
/*     */   
/*     */   public Partition(int[] groups_size, boolean random) {
/*  95 */     create(groups_size);
/*  96 */     if (random) this.index = permutation(this.n);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Partition(int _n, int m, SimpleStreamTokenizer tok) {
/* 108 */     this.n = _n;
/* 109 */     for (int i = 0; i < this.n; i++) {
/* 110 */       this.index.add(new Integer((int)(tok.nextToken()).nval));
/*     */     }
/* 112 */     create(m);
/*     */   }
/*     */   
/*     */   protected void create(int[] groups_size) {
/* 116 */     int m = groups_size.length;
/* 117 */     this.start = new int[m + 1];
/* 118 */     this.start[0] = 0;
/* 119 */     for (int i = 0; i < m; i++) {
/* 120 */       this.start[i + 1] = this.start[i] + groups_size[i];
/*     */     }
/* 122 */     this.n = this.start[m];
/*     */   }
/*     */   
/*     */   protected void create(int m) {
/* 126 */     this.start = new int[m + 1];
/* 127 */     this.start[0] = 0;
/* 128 */     this.start[m] = this.n;
/* 129 */     for (int k = 1; k < m; k++) {
/* 130 */       this.start[k] = this.n * k / m;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void permutation() {
/* 138 */     permutation(this.index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void permutation(Vector set) {
/* 147 */     int n = set.size();
/*     */     
/* 149 */     UniformNumberGenerator g = new UniformNumberGenerator(n);
/* 150 */     for (int i = 0; i < n; i++) {
/* 151 */       int j = g.newInt();
/* 152 */       int k = g.newInt();
/* 153 */       Object temp = set.get(j);
/* 154 */       set.set(j, set.get(k));
/* 155 */       set.set(k, temp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vector permutation(int n) {
/* 165 */     Vector perm = new Vector();
/* 166 */     for (int i = 0; i < n; ) { perm.add(new Integer(i)); i++; }
/* 167 */      permutation(perm);
/* 168 */     return perm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration skipGroup(int pos) {
/* 177 */     return new PartitionEnumeration(this, pos, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration getGroup(int pos) {
/* 186 */     return new PartitionEnumeration(this, pos, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int groupSize(int k) {
/* 194 */     return this.start[k + 1] - this.start[k];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupsNumber(int m) {
/* 202 */     create(m);
/*     */   }
/*     */   
/*     */   public void setGroups(int[] groups_size) {
/* 206 */     this.n = 0;
/* 207 */     for (int i = 0; i < groups_size.length; ) { this.n += groups_size[i]; i++; }
/* 208 */      if (this.index == null || this.n == this.index.size()) create(groups_size);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 216 */     return this.start.length - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save(String fileName) {
/*     */     
/* 225 */     try { FileWriter file = new FileWriter(fileName);
/* 226 */       Enumeration iter = this.index.elements();
/* 227 */       while (iter.hasMoreElements()) {
/* 228 */         file.write((new StringBuffer()).append(iter.nextElement()).append(" ").toString());
/*     */       }
/* 230 */       file.close(); }
/* 231 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 239 */     return this.index.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class PartitionEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected int group;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean selected_group;
/*     */ 
/*     */ 
/*     */     
/*     */     protected int pos;
/*     */ 
/*     */ 
/*     */     
/*     */     private final Partition this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public PartitionEnumeration(Partition this$0, int _group, boolean _selected_group) {
/* 265 */       this.this$0 = this$0; this.group = 0; this.selected_group = false; this.pos = 0;
/* 266 */       this.group = _group;
/* 267 */       this.selected_group = _selected_group;
/* 268 */       if (this.selected_group) {
/* 269 */         this.pos = this$0.start[this.group];
/*     */       }
/* 271 */       else if (this.group != 0) {
/* 272 */         this.pos = 0;
/*     */       } else {
/* 274 */         this.pos = this$0.start[1];
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 285 */       if (this.selected_group) {
/* 286 */         return (this.pos < this.this$0.start[this.group + 1]);
/*     */       }
/* 288 */       return (this.pos < this.this$0.n);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/*     */       Object d;
/* 298 */       if (this.this$0.index != null) { d = this.this$0.index.get(this.pos); }
/* 299 */       else { d = new Integer(this.pos); }
/* 300 */        this.pos++;
/* 301 */       if (!this.selected_group && this.pos == this.this$0.start[this.group]) {
/* 302 */         this.pos = this.this$0.start[this.group + 1];
/*     */       }
/* 304 */       return d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\random\Partition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
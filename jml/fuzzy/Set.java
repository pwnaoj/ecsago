/*     */ package jml.fuzzy;
/*     */ 
/*     */ import jml.fuzzy.operators.And;
/*     */ import jml.fuzzy.operators.MinAnd;
/*     */ import jml.fuzzy.operators.Not;
/*     */ import jml.fuzzy.operators.Or;
/*     */ import jml.fuzzy.sets.PolyLineSet;
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
/*     */ public abstract class Set
/*     */ {
/*  37 */   public static int SAMPLE_SIZE = 101;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String id;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected double size = -1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set(String _id) {
/*  56 */     this.id = _id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  72 */     return "{" + this.id + " " + getClass().getName() + " " + parameters(false) + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String parameters(boolean paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/*  87 */     return "<set id=\"" + this.id + "\" shape=\"" + getClass().getName() + "\" " + parameters(true) + "/>\n";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double evaluate(double paramDouble);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] sample(int S, double start, double end) {
/* 103 */     double length = end - start;
/* 104 */     int DIV = S - 1;
/* 105 */     double[][] x = new double[2][S];
/* 106 */     for (int i = 0; i < (x[0]).length; i++) {
/* 107 */       x[0][i] = start + i * length / DIV;
/* 108 */       x[1][i] = evaluate(x[0][i]);
/*     */     } 
/* 110 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] sample() {
/* 119 */     return sample(SAMPLE_SIZE, 0.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSize() {
/* 129 */     if (this.size < 0.0D) {
/* 130 */       double[][] x = sample();
/* 131 */       this.size = 0.0D;
/* 132 */       for (int i = 0; i < (x[0]).length - 1; i++) {
/* 133 */         this.size += (x[1][i + 1] + x[1][i]) * (x[0][i + 1] - x[0][i]) / 2.0D;
/*     */       }
/*     */     } 
/* 136 */     return this.size;
/*     */   }
/*     */   
/*     */   public double getSize(double start, double end) {
/* 140 */     this.size = 0.0D;
/* 141 */     if (start < end) {
/* 142 */       double[][] x = sample(SAMPLE_SIZE, start, end);
/* 143 */       this.size = 0.0D;
/* 144 */       for (int i = 0; i < (x[0]).length - 1; i++) {
/* 145 */         this.size += (x[1][i + 1] + x[1][i]) * (x[0][i + 1] - x[0][i]) / 2.0D;
/*     */       }
/*     */     } 
/* 148 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set complement(Not not) {
/* 157 */     double[][] x = sample(SAMPLE_SIZE, 0.0D, 1.0D);
/* 158 */     for (int i = 0; i < (x[0]).length; i++) {
/* 159 */       x[1][i] = not.evaluate(x[1][i]);
/*     */     }
/* 161 */     return (Set)new PolyLineSet("not " + this.id, x[0], x[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set joint(Set s, And and) {
/* 171 */     double[][] x = sample(SAMPLE_SIZE, 0.0D, 1.0D);
/* 172 */     for (int i = 0; i < (x[0]).length; i++) {
/* 173 */       x[1][i] = and.evaluate(x[1][i], s.evaluate(x[0][i]));
/*     */     }
/* 175 */     return (Set)new PolyLineSet("I" + this.id + "-and-" + s.id + "I", x[0], x[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set union(Set s, Or or) {
/* 185 */     double[][] x = sample(SAMPLE_SIZE, 0.0D, 1.0D);
/* 186 */     for (int i = 0; i < (x[0]).length; i++) {
/* 187 */       x[1][i] = or.evaluate(x[1][i], s.evaluate(x[0][i]));
/*     */     }
/* 189 */     return (Set)new PolyLineSet("I" + this.id + "-or-" + s.id + "I", x[0], x[1]);
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
/*     */   public double subset(Set s, And and, Not not) {
/* 201 */     double s1 = getSize();
/* 202 */     double s2 = getSize();
/* 203 */     if (s1 != 0.0D) {
/* 204 */       if (s2 != 0.0D) {
/* 205 */         return joint(s, and).getSize() / s1;
/*     */       }
/* 207 */       return not.evaluate(s1);
/*     */     } 
/*     */     
/* 210 */     return 1.0D;
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
/*     */   public double subset(Set s) {
/* 222 */     return subset(s, (And)new MinAnd(), new Not());
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
/*     */   public double distance(Set s, Not not, And and) {
/* 234 */     return not.evaluate(subset(s, and, not));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distance(Set s) {
/* 245 */     Not not = new Not();
/* 246 */     MinAnd minAnd = new MinAnd();
/* 247 */     return distance(s, not, (And)minAnd);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Set.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
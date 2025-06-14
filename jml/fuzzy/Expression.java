/*     */ package jml.fuzzy;
/*     */ 
/*     */ import jml.algebra.RealVector;
/*     */ import jml.fuzzy.operators.Not;
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
/*     */ public class Expression
/*     */ {
/*  40 */   public static int ATOMIC = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int token_type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Atomic token;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression left;
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression right;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression(int tt, Expression l, Expression r) {
/*  65 */     this.token_type = tt;
/*  66 */     this.token = null;
/*  67 */     this.left = l;
/*  68 */     this.right = r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression(Atomic t) {
/*  76 */     this.token_type = ATOMIC;
/*  77 */     this.token = t;
/*  78 */     this.left = null;
/*  79 */     this.right = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  87 */     String text = "";
/*  88 */     if (this.token_type == ATOMIC) {
/*  89 */       text = this.token.toString();
/*     */     } else {
/*  91 */       switch (this.token_type) {
/*     */         case 7:
/*  93 */           if (this.left.token_type == ATOMIC) {
/*  94 */             text = "not " + this.left.toString() + " "; break;
/*     */           } 
/*  96 */           text = "not(" + this.left.toString() + ") ";
/*     */           break;
/*     */         
/*     */         case 8:
/* 100 */           text = text + "(" + this.left.toString() + ") and  ( " + this.right.toString() + ") ";
/*     */           break;
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
/*     */         case 9:
/* 113 */           text = text + "(" + this.left.toString() + ") or  ( " + this.right.toString() + ") ";
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 118 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(RealVector data, Logic logic) {
/* 128 */     double l, r, v = 0.0D;
/*     */     
/* 130 */     switch (this.token_type) {
/*     */       case 0:
/* 132 */         v = this.token.evaluate(data, logic.not);
/*     */         break;
/*     */       case 7:
/* 135 */         l = this.left.evaluate(data, logic);
/* 136 */         if (l < 0.0D) { v = -1.0D; break; }
/* 137 */          v = logic.not.evaluate(l);
/*     */         break;
/*     */       case 8:
/* 140 */         l = this.left.evaluate(data, logic);
/* 141 */         r = this.right.evaluate(data, logic);
/* 142 */         if (l < 0.0D || r < 0.0D) { v = -1.0D; break; }
/* 143 */          v = logic.and.evaluate(l, r);
/*     */         break;
/*     */       case 9:
/* 146 */         l = this.left.evaluate(data, logic);
/* 147 */         r = this.right.evaluate(data, logic);
/* 148 */         if (l < 0.0D || r < 0.0D) { v = -1.0D; break; }
/* 149 */          v = logic.or.evaluate(l, r);
/*     */         break;
/*     */     } 
/* 152 */     return v;
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
/*     */   public double volume() {
/* 165 */     double l, r, v = 0.0D;
/*     */     
/* 167 */     switch (this.token_type) {
/*     */       case 0:
/* 169 */         v = this.token.volume();
/*     */         break;
/*     */       case 7:
/* 172 */         v = 1.0D - this.left.volume();
/*     */         break;
/*     */       case 8:
/* 175 */         l = this.left.volume();
/* 176 */         r = this.right.volume();
/* 177 */         v = l * r;
/*     */         break;
/*     */       case 9:
/* 180 */         l = this.left.volume();
/* 181 */         r = this.right.volume();
/* 182 */         v = l + r - l * r;
/*     */         break;
/*     */     } 
/* 185 */     return v;
/*     */   }
/*     */   
/*     */   public double or_distance(Expression e) {
/* 189 */     double l, r, d = 0.0D;
/*     */     
/* 191 */     switch (this.token_type) {
/*     */       case 0:
/* 193 */         l = distance(e.left);
/* 194 */         r = distance(e.right);
/* 195 */         d = Math.min(l, r);
/*     */         break;
/*     */       case 7:
/* 198 */         d = 1.0D - this.left.or_distance(e);
/*     */         break;
/*     */       case 8:
/* 201 */         l = this.left.distance(e);
/* 202 */         r = this.right.distance(e);
/* 203 */         d = Math.min(l, r);
/*     */         break;
/*     */       case 9:
/* 206 */         l = this.left.distance(e);
/* 207 */         r = this.right.distance(e);
/* 208 */         d = Math.max(l, r);
/*     */         break;
/*     */     } 
/* 211 */     return d;
/*     */   }
/*     */   
/*     */   public double distance(Expression e) {
/* 215 */     double l, r, d = 0.0D;
/*     */ 
/*     */     
/* 218 */     switch (e.token_type) {
/*     */       case 0:
/* 220 */         switch (this.token_type) {
/*     */           case 0:
/* 222 */             if (this.token.variable == e.token.variable) {
/* 223 */               Set s1 = this.token.set;
/* 224 */               if (!this.token.positive) s1 = s1.complement(new Not()); 
/* 225 */               Set s2 = e.token.set;
/* 226 */               if (!e.token.positive) s2 = s2.complement(new Not()); 
/* 227 */               d = s1.distance(s2); break;
/*     */             } 
/* 229 */             if (this.token.positive) { l = this.token.set.getSize(); }
/* 230 */             else { l = 1.0D - this.token.set.getSize(); }
/*     */             
/* 232 */             if (e.token.positive) { r = e.token.set.getSize(); }
/* 233 */             else { r = 1.0D - e.token.set.getSize(); }
/* 234 */              d = l * r;
/* 235 */             if (d == 0.0D) d = 1.0E8D;
/*     */             
/*     */             break;
/*     */           case 7:
/* 239 */             d = 1.0D - this.left.distance(e);
/*     */             break;
/*     */           case 8:
/* 242 */             l = this.left.distance(e);
/* 243 */             r = this.right.distance(e);
/* 244 */             d = Math.min(l, r);
/*     */             break;
/*     */           case 9:
/* 247 */             l = this.left.distance(e);
/* 248 */             r = this.right.distance(e);
/* 249 */             d = Math.max(l, r);
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case 7:
/* 254 */         d = 1.0D - distance(e.left);
/*     */         break;
/*     */       case 8:
/* 257 */         l = distance(e.left);
/* 258 */         r = distance(e.right);
/* 259 */         d = Math.max(l, r);
/*     */         break;
/*     */       case 9:
/* 262 */         d = or_distance(e);
/*     */         break;
/*     */     } 
/* 265 */     return d;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Expression.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.fuzzy.util;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.data.Attribute;
/*     */ import jml.data.CategoricAttribute;
/*     */ import jml.data.DataSourceInfo;
/*     */ import jml.data.NumericAttribute;
/*     */ import jml.fuzzy.Model;
/*     */ import jml.fuzzy.Space;
/*     */ import jml.fuzzy.Variable;
/*     */ import jml.fuzzy.sets.CrispSet;
/*     */ import jml.fuzzy.sets.TrapezoidSet;
/*     */ import jml.fuzzy.sets.TriangularSet;
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
/*     */ public class ModelGenerator
/*     */ {
/*     */   public static final int CRISP = 0;
/*     */   public static final int TRIANGULAR = 1;
/*     */   public static final int TRAPEZOID = 2;
/*     */   public static final int TRAPEZOIDTRIANGULAR = 3;
/*     */   
/*     */   public static Space getNumericalSpace(int setsPerNumericalAttribute, int sets_shape) {
/*     */     int j;
/*  71 */     if (setsPerNumericalAttribute < 2) setsPerNumericalAttribute = 2; 
/*  72 */     double delta = 0.0D;
/*  73 */     double[] points = null;
/*  74 */     Vector sets = new Vector();
/*  75 */     switch (sets_shape) {
/*     */       case 0:
/*  77 */         delta = 1.0D / setsPerNumericalAttribute;
/*  78 */         points = new double[setsPerNumericalAttribute + 1];
/*  79 */         points[0] = 0.0D;
/*  80 */         for (j = 1; j < setsPerNumericalAttribute; j++) {
/*  81 */           points[j] = points[j - 1] + delta;
/*     */         }
/*  83 */         points[0] = -1.0D;
/*  84 */         points[points.length - 1] = 2.0D;
/*  85 */         for (j = 0; j < setsPerNumericalAttribute; j++) {
/*  86 */           sets.add(new CrispSet("int-" + j, points[j], points[j + 1]));
/*     */         }
/*     */         break;
/*     */       case 1:
/*  90 */         delta = 1.0D / (setsPerNumericalAttribute - 1);
/*  91 */         points = new double[setsPerNumericalAttribute + 2];
/*  92 */         points[1] = 0.0D;
/*  93 */         for (j = 2; j <= setsPerNumericalAttribute; j++) {
/*  94 */           points[j] = points[j - 1] + delta;
/*     */         }
/*  96 */         points[0] = -1.0D;
/*  97 */         points[points.length - 1] = 2.0D;
/*  98 */         for (j = 0; j < setsPerNumericalAttribute; j++) {
/*  99 */           sets.add(new TriangularSet("trian-" + j, points[j], points[j + 1], points[j + 2]));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 2:
/* 104 */         delta = 1.0D / (2 * setsPerNumericalAttribute - 1);
/* 105 */         points = new double[2 * (setsPerNumericalAttribute + 1)];
/* 106 */         points[1] = 0.0D;
/* 107 */         for (j = 2; j < 2 * setsPerNumericalAttribute + 1; j++) {
/* 108 */           points[j] = points[j - 1] + delta;
/*     */         }
/* 110 */         points[0] = -1.0D;
/* 111 */         points[points.length - 1] = 2.0D;
/* 112 */         for (j = 0; j < 2 * setsPerNumericalAttribute; j += 2) {
/* 113 */           sets.add(new TrapezoidSet("trap-" + (j / 2), points[j], points[j + 1], points[j + 2], points[j + 3]));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 3:
/* 118 */         delta = 1.0D / (setsPerNumericalAttribute + 1);
/* 119 */         points = new double[setsPerNumericalAttribute];
/* 120 */         points[0] = delta;
/* 121 */         for (j = 1; j < setsPerNumericalAttribute; j++) {
/* 122 */           points[j] = points[j - 1] + delta;
/*     */         }
/* 124 */         sets.add(new TrapezoidSet("low", 0.0D, 0.0D, points[0], points[1]));
/* 125 */         for (j = 0; j < setsPerNumericalAttribute - 2; j++) {
/* 126 */           sets.add(new TriangularSet("level-" + j, points[j], points[j + 1], points[j + 2]));
/*     */         }
/*     */         
/* 129 */         sets.add(new TrapezoidSet("high", points[points.length - 2], points[points.length - 1], 1.0D, 1.0D));
/*     */         break;
/*     */     } 
/* 132 */     return new Space("numeric", sets);
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
/*     */   
/*     */   public static Model generate(int[] categories, int setsPerNumericalAttribute, int sets_shape) {
/* 148 */     Vector features = new Vector();
/* 149 */     for (int i = 0; i < categories.length; i++) {
/* 150 */       if (categories[i] > 1) {
/* 151 */         Vector cat = new Vector();
/* 152 */         for (int j = 0; j < categories[i]; j++) {
/* 153 */           cat.add("cat-" + j);
/*     */         }
/* 155 */         features.add(new CategoricAttribute("x-" + i, cat));
/*     */       } else {
/* 157 */         features.add(new NumericAttribute("x-" + i));
/*     */       } 
/*     */     } 
/* 160 */     return generate(new DataSourceInfo(0, features, null), setsPerNumericalAttribute, sets_shape);
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
/*     */   public static Model generate(DataSourceInfo data_info, int setsPerNumericalAttribute, int sets_shape) {
/* 174 */     Model system = new Model();
/* 175 */     boolean flag = false;
/* 176 */     int n = data_info.dimension();
/* 177 */     Vector features = data_info.getFeatures();
/* 178 */     for (int i = 0; i < n && !flag; i++) {
/* 179 */       flag = features.get(i) instanceof NumericAttribute;
/*     */     }
/* 181 */     Space n_space = null;
/* 182 */     if (flag) {
/* 183 */       n_space = getNumericalSpace(setsPerNumericalAttribute, sets_shape);
/* 184 */       system.addSpace(n_space);
/*     */     } 
/* 186 */     for (int j = 0; j < n; j++) {
/* 187 */       Vector sets = new Vector();
/* 188 */       Space space = null;
/* 189 */       if (features.get(j) instanceof CategoricAttribute) {
/* 190 */         CategoricAttribute attr = features.get(j);
/* 191 */         for (int k = 0; k < attr.size(); k++) {
/* 192 */           sets.add(new TriangularSet(attr.getCategory(k), (k - 1), (k + 1)));
/*     */         }
/* 194 */         space = new Space("space-" + attr.getLabel(), sets);
/* 195 */         system.addSpace(space);
/*     */       } else {
/* 197 */         space = n_space;
/*     */       } 
/* 199 */       Variable v = new Variable(((Attribute)features.get(j)).getLabel(), space);
/* 200 */       v.position = j;
/* 201 */       system.addVariable(v);
/*     */     } 
/* 203 */     return system;
/*     */   }
/*     */   
/*     */   public static void main(String[] argv) {
/* 207 */     int[] categories = { 0, 4, 3, 0, 0, 2, 0 };
/*     */     
/* 209 */     int m = 5;
/* 210 */     Model system = generate(categories, m, 0);
/* 211 */     System.out.println("[Crisp]" + system.toString());
/* 212 */     system = generate(categories, m, 1);
/* 213 */     System.out.println("[Triangular]" + system.toString());
/* 214 */     system = generate(categories, m, 2);
/* 215 */     System.out.println("[Trapezoid]" + system.toString());
/* 216 */     system = generate(categories, m, 3);
/* 217 */     System.out.println("[Mixed]" + system.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzz\\util\ModelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
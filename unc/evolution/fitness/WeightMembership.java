/*     */ package unc.evolution.fitness;
/*     */ 
/*     */ import jml.fuzzy.Set;
/*     */ import unc.sets.SqrGaussianSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightMembership
/*     */   extends Weight
/*     */ {
/*  17 */   public double T = 0.0D;
/*     */ 
/*     */   
/*     */   public WeightMembership(double _T, boolean _binarized) {
/*  21 */     super(_binarized);
/*  22 */     this.T = _T;
/*     */   }
/*     */ 
/*     */   
/*     */   public double centerOfSquareDistaces(double sigma, double left, double right) {
/*  27 */     double two_sigma_square = 2.0D * sigma * sigma;
/*  28 */     double exp_left = Math.exp(-left / two_sigma_square);
/*  29 */     double exp_right = Math.exp(-right / two_sigma_square);
/*  30 */     return two_sigma_square + (right * exp_right - left * exp_left) / (exp_right - exp_left);
/*     */   }
/*     */   
/*     */   public void apply(double[] distances, double[] size, Set[] sets, Set set) {
/*  34 */     reset();
/*     */     
/*  36 */     double K = 2.512864D;
/*  37 */     double sigma_prot = ((SqrGaussianSet)set).getSqrSigma();
/*  38 */     double Td = -2.0D * Math.log(this.T) * sigma_prot;
/*     */     
/*  40 */     double K_sigma_prot = K * sigma_prot;
/*  41 */     double sqrt_K_sigma_prot = Math.sqrt(K_sigma_prot);
/*  42 */     int n = distances.length;
/*  43 */     for (int j = 0; j < n; j++) {
/*  44 */       double weight = distances[j];
/*     */ 
/*     */       
/*  47 */       if (sets[j] == null) {
/*     */         
/*  49 */         if (weight <= Td) {
/*  50 */           if (this.binarized) {
/*  51 */             this.sumWeight += size[j];
/*  52 */             this.sumWeidis += distances[j] * size[j];
/*     */           } else {
/*  54 */             this.sumWeight += weight * size[j];
/*  55 */             this.sumWeidis += distances[j] * size[j] * weight;
/*     */           } 
/*     */         }
/*     */       } else {
/*  59 */         double sigma_datum_square = ((SqrGaussianSet)sets[j]).getSqrSigma();
/*  60 */         double sigma_datum = Math.sqrt(sigma_datum_square);
/*  61 */         double sqrt_K_sigma_datum = Math.sqrt(K) * sigma_datum;
/*  62 */         double datum_area = 2.0D * sets[j].getSize(0.0D, K * sigma_datum_square);
/*     */         
/*  64 */         double d = Math.sqrt(distances[j]);
/*     */         
/*  66 */         double left = Math.max(d - sqrt_K_sigma_prot, -sqrt_K_sigma_datum);
/*  67 */         double right = Math.min(d + sqrt_K_sigma_prot, sqrt_K_sigma_datum);
/*  68 */         if (left < right) {
/*     */           
/*  70 */           double intersection_area = 0.0D;
/*  71 */           if (left < 0.0D) {
/*  72 */             intersection_area = sets[j].getSize(0.0D, left * left);
/*     */           }
/*  74 */           double inf = Math.max(0.0D, left);
/*  75 */           intersection_area += sets[j].getSize(inf * inf, right * right);
/*     */           
/*  77 */           double ratio = intersection_area / datum_area;
/*  78 */           if (ratio > 1.0D) System.out.println("ration=" + ratio);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  83 */           double wdis = 0.0D;
/*  84 */           if (left < 0.0D) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  89 */             double left_area = sets[j].getSize(0.0D, left * left);
/*  90 */             double right_area = sets[j].getSize(0.0D, right * right);
/*  91 */             double cm_left = centerOfSquareDistaces(sigma_datum, 0.0D, left * left);
/*  92 */             double cm_right = centerOfSquareDistaces(sigma_datum, 0.0D, right * right);
/*  93 */             double d_left = d + Math.sqrt(cm_left);
/*  94 */             double d_right = d - Math.sqrt(cm_right);
/*  95 */             wdis = (d_left * d_left * left_area + d_right * d_right * right_area) * size[j] / datum_area;
/*     */           } else {
/*  97 */             double cm = centerOfSquareDistaces(sigma_datum, left * left, right * right);
/*  98 */             d -= Math.sqrt(cm);
/*  99 */             wdis = d * d * ratio * size[j];
/*     */           } 
/*     */           
/* 102 */           if (this.binarized) {
/* 103 */             this.sumWeight += size[j] * ratio;
/* 104 */             this.sumWeidis += wdis;
/*     */           } else {
/* 106 */             this.sumWeight += weight * size[j] * ratio;
/* 107 */             this.sumWeidis += wdis * weight;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\fitness\WeightMembership.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
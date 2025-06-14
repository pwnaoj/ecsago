/*     */ package jml.fuzzy;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.classification.binarization.BinaryRecognizer;
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
/*     */ public class RuleBinaryRecognizer
/*     */   extends BinaryRecognizer
/*     */ {
/*     */   protected Vector rules;
/*  53 */   protected Logic logic = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean positiveRules = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleBinaryRecognizer(Vector _rules, Logic _logic, boolean _positiveRules) {
/*  70 */     this.rules = _rules;
/*  71 */     this.logic = _logic;
/*  72 */     this.positiveRules = _positiveRules;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleBinaryRecognizer(Vector _rules, Logic _logic) {
/*  82 */     this(_rules, _logic, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] confidence(Object data) {
/*  93 */     RealVector r = (RealVector)data;
/*  94 */     double[] conf = new double[2];
/*  95 */     double c = 0.0D;
/*  96 */     Enumeration iter = this.rules.elements();
/*  97 */     while (iter.hasMoreElements()) {
/*  98 */       double d = ((Rule)iter.nextElement()).evaluate(r, this.logic);
/*  99 */       if (c < d) c = d; 
/*     */     } 
/* 101 */     if (this.positiveRules) {
/* 102 */       conf[1] = c;
/* 103 */       conf[0] = this.logic.not.evaluate(conf[1]);
/*     */     } else {
/* 105 */       conf[0] = c;
/* 106 */       conf[1] = this.logic.not.evaluate(conf[0]);
/*     */     } 
/* 108 */     return conf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     StringBuffer sb = new StringBuffer();
/* 117 */     Enumeration iter = this.rules.elements();
/* 118 */     while (iter.hasMoreElements()) {
/* 119 */       sb.append(iter.nextElement().toString());
/* 120 */       sb.append("\n");
/*     */     } 
/* 122 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\RuleBinaryRecognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
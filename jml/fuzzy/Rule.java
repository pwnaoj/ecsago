/*     */ package jml.fuzzy;
/*     */ 
/*     */ import jml.algebra.RealVector;
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
/*     */ public class Rule
/*     */ {
/*     */   public Expression condition;
/*     */   public Atomic conclusion;
/*  46 */   public double confidence = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule(Expression cond, Atomic conc, double _confidence) {
/*  60 */     this.condition = cond;
/*  61 */     this.conclusion = conc;
/*  62 */     this.confidence = _confidence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  70 */     return "if " + this.condition.toString() + " then " + this.conclusion.toString() + " [" + this.confidence + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/*  78 */     String text_act = this.conclusion.toString();
/*  79 */     String text = "<rule>\n";
/*  80 */     text = text + " <if>" + this.condition.toString() + "</if>\n";
/*  81 */     text = text + " <then>" + text_act + "</then>\n";
/*  82 */     text = text + " <confidence>" + this.confidence + "</confidence>\n";
/*  83 */     text = text + "</rule>\n";
/*  84 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double evaluate(RealVector data, Logic logic) {
/*  94 */     if (this.condition != null) {
/*  95 */       return this.confidence * this.condition.evaluate(data, logic);
/*     */     }
/*  97 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClassLabel() {
/* 107 */     return this.conclusion.variable.getSpace().getSetPosition(this.conclusion.set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double volume() {
/* 116 */     if (this.condition != null) return this.condition.volume(); 
/* 117 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Rule.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
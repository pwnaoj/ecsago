/*     */ package jml.evolution.operators;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.basics.Cloner;
/*     */ import jml.evolution.Environment;
/*     */ import jml.evolution.Individual;
/*     */ import jml.evolution.Operator;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.Selection;
/*     */ import jml.evolution.selections.Tournament;
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
/*     */ public abstract class ArityTwo
/*     */   extends Operator
/*     */ {
/*     */   protected Selection selection;
/*     */   
/*     */   public ArityTwo(Environment _environment) {
/*  54 */     super(_environment);
/*  55 */     this.selection = (Selection)new Tournament(this.environment, 2, true, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArityTwo(Environment _environment, Selection _selection) {
/*  62 */     super(_environment);
/*  63 */     this.selection = _selection;
/*  64 */     if (this.selection != null) this.selection.setSize(2);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelection(Selection _selection) {
/*  73 */     this.selection = _selection;
/*     */   }
/*     */   public Selection getSelection() {
/*  76 */     return this.selection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object apply(Object paramObject1, Object paramObject2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object apply(Individual one, Individual two) {
/*  91 */     return apply(one.getGenome(), two.getGenome());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector apply(Population population, int x) {
/* 101 */     Vector children = null;
/* 102 */     if (population != null && this.selection != null) {
/* 103 */       Vector parents = this.selection.choose(population, x);
/* 104 */       Individual child1 = (Individual)Cloner.clone(parents.elementAt(0));
/* 105 */       Individual child2 = (Individual)Cloner.clone(parents.elementAt(1));
/*     */       
/* 107 */       apply(child1, child2);
/* 108 */       children = new Vector();
/* 109 */       children.add(child1);
/* 110 */       children.add(child2);
/*     */     } 
/* 112 */     return children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArity() {
/* 119 */     return 2;
/*     */   }
/*     */   public void setEnvironment(Environment _environment) {
/* 122 */     super.setEnvironment(_environment);
/* 123 */     this.selection.setEnvironment(this.environment);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\operators\ArityTwo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
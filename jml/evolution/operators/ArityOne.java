/*    */ package jml.evolution.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.basics.Cloner;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Operator;
/*    */ import jml.evolution.Population;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ArityOne
/*    */   extends Operator
/*    */ {
/*    */   public ArityOne(Environment _environment) {
/* 47 */     super(_environment);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Object apply(Object paramObject);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Individual obj) {
/* 64 */     return apply(obj.getGenome());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector apply(Population population, int x) {
/* 74 */     Individual c = null;
/* 75 */     if (population != null) {
/* 76 */       c = (Individual)Cloner.clone(population.get(x));
/* 77 */       apply(c);
/*    */     } 
/* 79 */     Vector v = new Vector();
/* 80 */     v.add(c);
/* 81 */     return v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getArity() {
/* 88 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\operators\ArityOne.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
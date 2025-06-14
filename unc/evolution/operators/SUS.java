/*    */ package unc.evolution.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Selection;
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
/*    */ public class SUS
/*    */   extends Selection
/*    */ {
/*    */   public SUS(Environment env, int n, boolean c) {
/* 27 */     super(env, n, c);
/*    */   }
/*    */   
/*    */   public Vector choose(Population population, int index) {
/* 31 */     return choose(population);
/*    */   }
/*    */   
/*    */   public Vector choose(Population population) {
/* 35 */     Vector sel = null;
/* 36 */     if (population != null) {
/* 37 */       int n = population.size();
/* 38 */       double total = 0.0D;
/* 39 */       for (int i = 0; i < n; i++) {
/* 40 */         total += population.get(i).getFitness();
/*    */       }
/*    */       
/* 43 */       double v = Math.random() * total;
/*    */       
/* 45 */       int arity = getArity();
/*    */       
/* 47 */       double delta = total / arity;
/*    */       
/* 49 */       sel = new Vector();
/* 50 */       double start = 0.0D;
/* 51 */       int j = 0;
/* 52 */       for (int k = 0; k < arity; k++) {
/* 53 */         while (start + population.get(j).getFitness() <= v) {
/* 54 */           start += population.get(j).getFitness();
/* 55 */           j++;
/*    */         } 
/* 57 */         sel.add(population.get(j));
/* 58 */         v += delta;
/* 59 */         if (v >= total) v -= total; 
/* 60 */         if (start > v) {
/* 61 */           start = 0.0D;
/* 62 */           j = 0;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     return sel;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\operators\SUS.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
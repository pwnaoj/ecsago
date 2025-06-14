/*    */ package jml.evolution.algorithms;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Individual;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.Selection;
/*    */ import jml.random.UniformNumberGenerator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Sequence
/*    */   extends Selection
/*    */ {
/*    */   public Sequence(Environment _environment, int _n, boolean _includeX) {
/* 51 */     super(_environment, _n, _includeX);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector choose(Population population, int x) {
/* 60 */     Vector sel = null;
/* 61 */     if (population != null) {
/* 62 */       sel = new Vector();
/* 63 */       if (x < 0 || x >= population.size()) {
/* 64 */         UniformNumberGenerator g = new UniformNumberGenerator(population.size());
/* 65 */         x = g.newInt();
/*    */       } 
/* 67 */       for (int i = 0; i < this.n; i++) {
/* 68 */         sel.add(population.get(x));
/* 69 */         x++;
/* 70 */         if (x == population.size()) x = 0; 
/*    */       } 
/*    */     } 
/* 73 */     return sel;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\algorithms\Sequence.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.evolution.selections;
/*    */ 
/*    */ import java.util.Enumeration;
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
/*    */ public class Uniform
/*    */   extends Selection
/*    */ {
/*    */   public Uniform(Environment _environment, int _n, boolean _includeX) {
/* 49 */     super(_environment, _n, _includeX);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector choose(Population population, int x) {
/* 58 */     Vector sel = null;
/* 59 */     if (population != null) {
/* 60 */       sel = new Vector();
/* 61 */       UniformNumberGenerator generator = new UniformNumberGenerator(population.size());
/* 62 */       int arity = this.n;
/* 63 */       if (x >= 0 && x < population.size()) {
/* 64 */         sel.add(population.get(x));
/* 65 */         this.n--;
/*    */       } 
/* 67 */       Vector index = generator.getVectorInteger(this.n);
/* 68 */       Enumeration iter = index.elements();
/* 69 */       while (iter.hasMoreElements()) {
/* 70 */         sel.add(population.get(((Integer)iter.nextElement()).intValue()));
/*    */       }
/* 72 */       this.n = arity;
/*    */     } 
/* 74 */     return sel;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\selections\Uniform.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jml.fuzzy.operators;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.classification.Prediction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaxDeFuzzy
/*    */   extends DeFuzzy
/*    */ {
/*    */   public Prediction apply(Object data, double[] memberships) {
/* 53 */     double best_value = memberships[0];
/* 54 */     Vector v = new Vector();
/* 55 */     v.add(new Integer(0));
/* 56 */     for (int i = 1; i < memberships.length; i++) {
/* 57 */       if (best_value < memberships[i]) {
/* 58 */         best_value = memberships[i];
/* 59 */         v = new Vector();
/* 60 */         v.add(new Integer(i));
/*    */       }
/* 62 */       else if (best_value == memberships[i]) {
/* 63 */         v.add(new Integer(i));
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     Prediction p = null;
/* 68 */     if (v.size() > 0) {
/* 69 */       UniformNumberGenerator g = new UniformNumberGenerator(v.size());
/* 70 */       int label = ((Integer)v.elementAt(g.newInt())).intValue();
/* 71 */       p = new Prediction(data, label, memberships[label]);
/*    */     } 
/* 73 */     return p;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\operators\MaxDeFuzzy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
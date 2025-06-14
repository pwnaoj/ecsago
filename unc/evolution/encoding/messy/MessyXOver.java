/*    */ package unc.evolution.encoding.messy;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.evolution.Environment;
/*    */ import jml.evolution.Selection;
/*    */ import jml.evolution.operators.ArityTwo;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessyXOver
/*    */   extends ArityTwo
/*    */ {
/*    */   public MessyXOver(Environment env) {
/* 19 */     super(env);
/*    */   }
/*    */   public MessyXOver(Environment env, Selection _selection) {
/* 22 */     super(env, _selection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object apply(Object one, Object two) {
/* 31 */     Vector g_one = (Vector)one;
/* 32 */     Vector g_two = (Vector)two;
/* 33 */     int k1 = (int)(UniformNumberGenerator.random() * (g_one.size() - 2)) + 1;
/* 34 */     int k2 = (int)(UniformNumberGenerator.random() * (g_two.size() - 2) + 1.0D);
/*    */     
/* 36 */     int k = Math.min(k1, k2);
/* 37 */     k2 = k1 = k;
/*    */     
/* 39 */     Vector child_one = new Vector();
/* 40 */     Vector child_two = new Vector();
/*    */     
/* 42 */     while (k1 < g_one.size()) {
/* 43 */       child_two.add(g_one.get(k1));
/* 44 */       g_one.remove(k1);
/*    */     } 
/*    */     
/* 47 */     while (k2 < g_two.size()) {
/* 48 */       child_one.add(g_two.get(k2));
/* 49 */       g_two.remove(k2);
/*    */     } 
/*    */     
/*    */     int i;
/* 53 */     for (i = 0; i < child_one.size() && g_one.size() < 15; i++) {
/* 54 */       g_one.add(child_one.get(i));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 59 */     for (i = 0; i < child_two.size() && g_two.size() < 15; i++) {
/* 60 */       g_two.add(child_two.get(i));
/*    */     }
/*    */ 
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\messy\MessyXOver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
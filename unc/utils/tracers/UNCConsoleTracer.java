/*    */ package unc.utils.tracers;
/*    */ 
/*    */ import jml.basics.Algorithm;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.PopulationStatistics;
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
/*    */ public class UNCConsoleTracer
/*    */   extends UNCTracer
/*    */ {
/*    */   public UNCConsoleTracer(int[] _save_evos, int[] _save_refs) {
/* 20 */     super(_save_evos, _save_refs);
/*    */   }
/*    */   
/*    */   public void add(Algorithm alg, Object obj) {
/* 24 */     super.add(alg, obj);
/* 25 */     if (this.evo_updated || this.ref_updated || this.ext_updated)
/* 26 */       if (this.evo_updated) {
/* 27 */         System.out.println("POPULATION");
/* 28 */         Population p = ((PopulationStatistics)obj).getPopulation();
/* 29 */         for (int i = 0; i < p.size(); i++) {
/* 30 */           System.out.println(p.get(i));
/*    */         }
/*    */       }
/* 33 */       else if (this.ext_updated) {
/* 34 */         System.out.println("EXTRACTION");
/* 35 */         System.out.println(obj.toString());
/*    */       } else {
/* 37 */         System.out.println("REFINEMENT");
/* 38 */         System.out.println(obj.toString());
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\tracers\UNCConsoleTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
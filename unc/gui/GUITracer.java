/*    */ package unc.gui;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import jml.basics.Algorithm;
/*    */ import jml.evolution.Population;
/*    */ import jml.evolution.PopulationStatistics;
/*    */ import unc.Prototype;
/*    */ import unc.utils.PopulationLoader;
/*    */ import unc.utils.tracers.UNCTracer;
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
/*    */ public class GUITracer
/*    */   extends UNCTracer
/*    */ {
/* 23 */   protected int curPos = 0;
/*    */   protected UNCMainPanel mainPanel;
/*    */   
/*    */   public GUITracer(UNCMainPanel _mainPanel, int[] _save_evos, int[] _save_refs, int[] _save_groups) {
/* 27 */     super(_save_evos, _save_refs, _save_groups);
/* 28 */     this.mainPanel = _mainPanel;
/*    */   }
/*    */   
/*    */   public void add(Algorithm alg, Object obj) {
/* 32 */     if (obj instanceof PopulationStatistics) {
/* 33 */       PopulationStatistics stat = (PopulationStatistics)obj;
/* 34 */       Population p = stat.getPopulation();
/* 35 */       Vector v = new Vector();
/* 36 */       int size = p.size();
/* 37 */       for (int i = 0; i < size; i++) {
/* 38 */         v.add(p.get(i).clone());
/*    */       }
/* 40 */       p = new Population(p.getEnvironment(), v);
/* 41 */       obj = new PopulationStatistics(p);
/*    */     } 
/* 43 */     super.add(alg, obj);
/* 44 */     if (this.evo_updated || this.ref_updated || this.ext_updated) {
/* 45 */       boolean draw_radii = this.mainPanel.showRadii.isSelected();
/* 46 */       if (this.evo_updated) {
/* 47 */         Population p = ((PopulationStatistics)obj).getPopulation();
/* 48 */         Prototype prot = PopulationLoader.generate(p);
/* 49 */         this.mainPanel.drawPanel.setPrototype(prot, draw_radii, this.mainPanel.getSqrtK());
/*    */       }
/* 51 */       else if (this.ext_updated) {
/* 52 */         this.mainPanel.drawPanel.setPrototype((Prototype)obj, draw_radii, this.mainPanel.getSqrtK());
/*    */       } else {
/* 54 */         this.mainPanel.drawPanel.setPrototype((Prototype)obj, draw_radii, this.mainPanel.getSqrtK());
/*    */       } 
/*    */       
/* 57 */       this.mainPanel.jSlider1.setValue(this.curPos);
/* 58 */       this.curPos++;
/*    */     }
/* 60 */     else if (this.inc_updated) {
/* 61 */       this.curPos = 0;
/* 62 */       this.mainPanel.jSlider1.setValue(this.curPos);
/* 63 */       this.mainPanel.groupSlider.setValue(this.group);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProgress(int pos) {
/* 69 */     int n_evos = this.evos.size();
/* 70 */     int n_refs = this.refs.size();
/* 71 */     if (0 <= pos && pos < n_evos + 1 + n_refs) {
/* 72 */       boolean draw_radii = this.mainPanel.showRadii.isSelected();
/* 73 */       if (pos < n_evos) {
/*    */         
/* 75 */         Object obj = this.evos.get(pos);
/* 76 */         Population p = ((PopulationStatistics)obj).getPopulation();
/* 77 */         Prototype prot = PopulationLoader.generate(p);
/* 78 */         this.mainPanel.iterShowLabel.setText("Evolution [" + pos + "]");
/* 79 */         this.mainPanel.drawPanel.setPrototype(prot, draw_radii, this.mainPanel.getSqrtK());
/*    */       }
/* 81 */       else if (pos == n_evos) {
/*    */         
/* 83 */         this.mainPanel.iterShowLabel.setText("Extraction");
/* 84 */         this.mainPanel.drawPanel.setPrototype(this.extracted, draw_radii, this.mainPanel.getSqrtK());
/*    */       } else {
/*    */         
/* 87 */         pos -= n_evos + 1;
/* 88 */         Prototype prot = this.refs.get(pos);
/* 89 */         this.mainPanel.iterShowLabel.setText("Refinement [" + pos + "]");
/* 90 */         this.mainPanel.drawPanel.setPrototype(prot, draw_radii, this.mainPanel.getSqrtK());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\GUITracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
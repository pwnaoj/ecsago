/*    */ package unc.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.JPanel;
/*    */ import jml.data.DataSource;
/*    */ import jml.data.util.paint.RealVectorDataSourcePaint;
/*    */ import unc.Prototype;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawPanel
/*    */   extends JPanel
/*    */ {
/* 22 */   protected DataSource data_set = null;
/* 23 */   protected Prototype prototype = null;
/*    */   protected boolean draw_radii = false;
/* 25 */   protected double K = 1.0D;
/*    */   
/*    */   public DrawPanel() {
/*    */     try {
/* 29 */       jbInit();
/* 30 */     } catch (Exception ex) {
/* 31 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setData(DataSource _data_set) {
/* 36 */     this.data_set = _data_set;
/* 37 */     repaint();
/*    */   }
/*    */   
/*    */   public void setPrototype(Prototype _prototype, boolean _draw_radii, double _K) {
/* 41 */     this.prototype = _prototype;
/* 42 */     this.draw_radii = _draw_radii;
/* 43 */     this.K = _K;
/* 44 */     repaint();
/*    */   }
/*    */   
/*    */   public void paint(Graphics g) {
/* 48 */     super.paint(g);
/* 49 */     if (this.data_set != null) {
/* 50 */       Dimension d = getSize();
/* 51 */       Rectangle roi = new Rectangle(0, d.height, d.width, d.height);
/* 52 */       RealVectorDataSourcePaint painter = new RealVectorDataSourcePaint(roi, this.data_set, Color.black, 0, 1, 1, 0);
/*    */ 
/*    */       
/* 55 */       painter.draw(g);
/*    */     } 
/* 57 */     if (this.prototype != null) {
/* 58 */       Dimension d = getSize();
/* 59 */       Rectangle roi = new Rectangle(0, d.height, d.width, d.height);
/* 60 */       PrototypePaint painter = new PrototypePaint(roi, this.prototype, this.draw_radii, this.K, Color.red, 0, 1, 1, 3);
/*    */ 
/*    */       
/* 63 */       painter.draw(g);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void jbInit() throws Exception {}
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\DrawPanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
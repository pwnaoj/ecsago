/*    */ package unc.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.util.Enumeration;
/*    */ import jml.algebra.Components;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.fuzzy.sets.GaussianSet;
/*    */ import jml.util.gui.DrawRegion;
/*    */ import unc.Cluster;
/*    */ import unc.Prototype;
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
/*    */ public class PrototypePaint
/*    */   extends DrawRegion
/*    */ {
/* 27 */   protected Color color = Color.black;
/*    */   
/*    */   protected int X;
/*    */   
/*    */   protected int Y;
/*    */   
/*    */   protected int point_type;
/*    */   
/*    */   protected int point_size;
/*    */   
/*    */   protected Prototype prototype;
/*    */   
/*    */   protected boolean draw_radii = false;
/*    */   
/* 41 */   protected double K = 1.0D;
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
/*    */   public PrototypePaint(Rectangle _roi, Prototype _prototype, boolean _draw_radii, double _K, Color _color, int _X, int _Y, int _point_type, int _point_size) {
/* 53 */     super(_roi);
/* 54 */     this.prototype = _prototype;
/* 55 */     this.color = _color;
/* 56 */     this.X = _X;
/* 57 */     this.Y = _Y;
/* 58 */     this.point_type = _point_type;
/* 59 */     this.point_size = _point_size;
/* 60 */     this.draw_radii = _draw_radii;
/* 61 */     this.K = _K;
/*    */   }
/*    */   
/*    */   public void draw(Graphics g) {
/* 65 */     g.setColor(this.color);
/* 66 */     Enumeration iter = this.prototype.elements();
/* 67 */     while (iter.hasMoreElements()) {
/* 68 */       Cluster cluster = iter.nextElement();
/* 69 */       Components rec = ((RealVector)cluster.getCenter()).get();
/* 70 */       int x = scaleX(rec.get(this.X));
/* 71 */       int y = scaleY(rec.get(this.Y));
/* 72 */       drawPoint(g, x, y, this.point_type, this.point_size);
/* 73 */       if (this.draw_radii) {
/* 74 */         double sigma = ((GaussianSet)cluster.getSet()).getSigma();
/* 75 */         int rx = (int)(this.roi.width * this.K * sigma + 0.5D);
/* 76 */         int ry = (int)(this.roi.height * this.K * sigma + 0.5D);
/* 77 */         g.drawOval(x - rx, y - ry, 2 * rx, 2 * ry);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\PrototypePaint.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
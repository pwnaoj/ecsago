/*    */ package jml.data.util.paint;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.util.Enumeration;
/*    */ import jml.algebra.Components;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.util.gui.DrawRegion;
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
/*    */ public class RealVectorDataSourcePaint
/*    */   extends DrawRegion
/*    */ {
/* 39 */   protected DataSource data = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   protected Color color = Color.black;
/*    */ 
/*    */ 
/*    */   
/*    */   protected int X;
/*    */ 
/*    */ 
/*    */   
/*    */   protected int Y;
/*    */ 
/*    */   
/*    */   protected int point_type;
/*    */ 
/*    */   
/*    */   protected int point_size;
/*    */ 
/*    */ 
/*    */   
/*    */   public RealVectorDataSourcePaint(Rectangle _roi, DataSource _data, Color _color, int _X, int _Y, int _point_type, int _point_size) {
/* 63 */     super(_roi);
/* 64 */     this.data = _data;
/* 65 */     this.color = _color;
/* 66 */     this.X = _X;
/* 67 */     this.Y = _Y;
/* 68 */     this.point_type = _point_type;
/* 69 */     this.point_size = _point_size;
/*    */   }
/*    */   
/*    */   public void draw(Graphics g) {
/* 73 */     g.setColor(this.color);
/* 74 */     Enumeration iter = this.data.elements();
/* 75 */     while (iter.hasMoreElements()) {
/* 76 */       Components rec = ((RealVector)((Data)iter.nextElement()).get()).get();
/* 77 */       int x = scaleX(rec.get(this.X));
/* 78 */       int y = scaleY(rec.get(this.Y));
/* 79 */       drawPoint(g, x, y, this.point_type, this.point_size);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\paint\RealVectorDataSourcePaint.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
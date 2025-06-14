/*    */ package jml.util.gui;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
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
/*    */ public abstract class DrawRegion
/*    */ {
/*    */   public static final int SIMPLE_POINT = 0;
/*    */   public static final int X_POINT = 1;
/*    */   public static final int PLUS_POINT = 2;
/*    */   public static final int TRIANGLE_POINT = 3;
/*    */   public static final int OVAL_POINT = 4;
/*    */   public static final int SQUARE_POINT = 5;
/*    */   protected Rectangle roi;
/*    */   
/*    */   public DrawRegion(Rectangle _roi) {
/* 28 */     this.roi = _roi;
/*    */   }
/*    */   
/*    */   public int scaleX(double x) {
/* 32 */     return this.roi.x + (int)(this.roi.width * x + 0.5D);
/*    */   }
/*    */   
/*    */   public int scaleY(double y) {
/* 36 */     return this.roi.y - (int)(this.roi.height * y + 0.5D);
/*    */   }
/*    */   
/*    */   public void drawOval(Graphics g, double CX, double CY, double RX, double RY) {
/* 40 */     int x = scaleX(CX);
/* 41 */     int y = scaleY(CY);
/* 42 */     int rx = (int)(this.roi.width * RX + 0.5D);
/* 43 */     int ry = (int)(this.roi.height * RY + 0.5D);
/* 44 */     g.drawOval(x - rx, y - ry, 2 * rx, 2 * ry);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawPoint(Graphics g, int x, int y, int type, int size) {
/* 49 */     switch (type) {
/*    */       case 0:
/* 51 */         g.drawLine(x, y, x, y);
/*    */         break;
/*    */       case 1:
/* 54 */         g.drawLine(x - size, y + size, x + size, y - size);
/* 55 */         g.drawLine(x - size, y - size, x + size, y + size);
/*    */         break;
/*    */       case 2:
/* 58 */         g.drawLine(x, y + size, x, y - size);
/* 59 */         g.drawLine(x - size, y, x + size, y);
/*    */         break;
/*    */       case 3:
/* 62 */         g.drawLine(x - size, y + size, x, y - size);
/* 63 */         g.drawLine(x, y - size, x + size, y + size);
/* 64 */         g.drawLine(x + size, y + size, x - size, y + size);
/*    */         break;
/*    */       case 4:
/* 67 */         g.drawOval(x - size, y - size, 2 * size, 2 * size);
/*    */         break;
/*    */       case 5:
/* 70 */         g.drawLine(x - size, y + size, x - size, y - size);
/* 71 */         g.drawLine(x - size, y + size, x + size, y + size);
/* 72 */         g.drawLine(x + size, y + size, x + size, y - size);
/* 73 */         g.drawLine(x + size, y + size, x - size, y - size);
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract void draw(Graphics paramGraphics);
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jm\\util\gui\DrawRegion.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package unc.gui;
/*    */ 
/*    */ import javax.swing.JApplet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UNCMainApplet
/*    */   extends JApplet
/*    */ {
/*    */   UNCMainPanel mainPanel;
/*    */   
/*    */   public UNCMainApplet() {
/*    */     try {
/* 18 */       jbInit();
/* 19 */     } catch (Exception ex) {
/* 20 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void jbInit() throws Exception {
/* 26 */     this.mainPanel = new UNCMainPanel(true);
/* 27 */     setContentPane(this.mainPanel);
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {
/* 31 */     UNCMainApplet frame = new UNCMainApplet();
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\UNCMainApplet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
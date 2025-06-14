/*    */ package unc.gui;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GaussianMutationPanel
/*    */   extends JPanel
/*    */ {
/*    */   public GaussianMutationPanel() {
/*    */     try {
/* 21 */       jbInit();
/* 22 */     } catch (Exception ex) {
/* 23 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void jbInit() throws Exception {
/* 28 */     this.jLabel1.setText("Sigma:");
/* 29 */     this.jTextField1.setMaximumSize(new Dimension(50, 20));
/* 30 */     this.jTextField1.setMinimumSize(new Dimension(50, 20));
/* 31 */     this.jTextField1.setPreferredSize(new Dimension(50, 20));
/* 32 */     this.jTextField1.setText("0.1");
/* 33 */     add(this.jLabel1);
/* 34 */     add(this.jTextField1);
/*    */   }
/*    */   
/* 37 */   protected JLabel jLabel1 = new JLabel();
/* 38 */   protected JTextField jTextField1 = new JTextField();
/*    */   
/*    */   public String toString() {
/* 41 */     return "GaussianMutation";
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\GaussianMutationPanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
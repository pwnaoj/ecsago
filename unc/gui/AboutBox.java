/*     */ package unc.gui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.SystemColor;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ public class AboutBox
/*     */   extends JDialog
/*     */ {
/*  23 */   JPanel panel1 = new JPanel();
/*  24 */   JPanel panel2 = new JPanel();
/*  25 */   JPanel insetsPanel1 = new JPanel();
/*  26 */   JPanel insetsPanel2 = new JPanel();
/*  27 */   JPanel insetsPanel3 = new JPanel();
/*  28 */   JButton button1 = new JButton();
/*  29 */   JLabel imageControl1 = new JLabel();
/*     */   
/*     */   ImageIcon imageIcon;
/*  32 */   JLabel label1 = new JLabel();
/*  33 */   JLabel label2 = new JLabel();
/*  34 */   JLabel label3 = new JLabel();
/*  35 */   JLabel label4 = new JLabel();
/*  36 */   JLabel label5 = new JLabel();
/*     */   
/*  38 */   BorderLayout borderLayout1 = new BorderLayout();
/*  39 */   BorderLayout borderLayout2 = new BorderLayout();
/*  40 */   FlowLayout flowLayout1 = new FlowLayout();
/*  41 */   FlowLayout flowLayout2 = new FlowLayout();
/*  42 */   FlowLayout flowLayout3 = new FlowLayout();
/*  43 */   String product = "ECSAGO";
/*  44 */   String version = "Version 1.0";
/*  45 */   String copyright = "Copyright (c) 2006";
/*  46 */   String comments = "Prepared by Elizabeth Leon";
/*  47 */   String comments2 = "Dr. Olfa Nasraoui - University of Louisville";
/*     */   
/*  49 */   JPanel jPanel1 = new JPanel();
/*  50 */   JLabel jLabel1 = new JLabel();
/*     */   
/*     */   public AboutBox(Frame parent) {
/*  53 */     super(parent);
/*  54 */     enableEvents(64L);
/*     */     try {
/*  56 */       jbInit();
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  62 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jbInit() throws Exception {
/*  67 */     setTitle("About");
/*  68 */     setResizable(false);
/*  69 */     this.panel1.setLayout(this.borderLayout1);
/*  70 */     this.panel2.setLayout(this.borderLayout2);
/*  71 */     this.insetsPanel1.setLayout(this.flowLayout1);
/*  72 */     this.insetsPanel2.setLayout(this.flowLayout1);
/*  73 */     this.label1.setFont(new Font("Dialog", 1, 12));
/*  74 */     this.label1.setForeground(SystemColor.textHighlight);
/*  75 */     this.label1.setMaximumSize(new Dimension(200, 15));
/*  76 */     this.label1.setPreferredSize(new Dimension(350, 15));
/*  77 */     this.label1.setHorizontalAlignment(10);
/*  78 */     this.label1.setIconTextGap(4);
/*  79 */     this.label1.setText("Evolutionary Clstering with Self Adaptive Genetic Operators");
/*     */     
/*  81 */     this.label1.setVerticalAlignment(0);
/*  82 */     this.label1.setVerticalTextPosition(0);
/*  83 */     this.label2.setHorizontalAlignment(10);
/*  84 */     this.label2.setText(this.version);
/*  85 */     this.label3.setHorizontalAlignment(10);
/*  86 */     this.label3.setText("Copyright (c) 2006");
/*  87 */     this.label4.setHorizontalAlignment(10);
/*  88 */     this.label4.setText(this.comments);
/*  89 */     this.label5.setHorizontalAlignment(10);
/*  90 */     this.label5.setText("Dr. Olfa Nasraoui - University of Louisville");
/*  91 */     this.insetsPanel3.setLayout(this.flowLayout3);
/*  92 */     this.insetsPanel3.setAlignmentX(0.5F);
/*  93 */     this.insetsPanel3.setBorder((Border)null);
/*  94 */     this.insetsPanel3.setMinimumSize(new Dimension(51, 130));
/*  95 */     this.insetsPanel3.setPreferredSize(new Dimension(51, 170));
/*  96 */     this.button1.setText("Ok");
/*  97 */     this.button1.addActionListener(new AboutBox_button1_actionAdapter(this));
/*  98 */     this.panel1.setPreferredSize(new Dimension(339, 164));
/*  99 */     this.panel2.setPreferredSize(new Dimension(51, 150));
/* 100 */     this.imageControl1.setFont(new Font("SansSerif", 1, 14));
/* 101 */     this.imageControl1.setForeground(UIManager.getColor("PasswordField.selectionBackground"));
/* 102 */     this.imageControl1.setText("ECSAGO");
/* 103 */     this.flowLayout3.setAlignment(1);
/* 104 */     this.flowLayout3.setHgap(150);
/* 105 */     this.flowLayout3.setVgap(0);
/* 106 */     this.insetsPanel2.setBorder((Border)null);
/* 107 */     this.insetsPanel2.setPreferredSize(new Dimension(424, 50));
/* 108 */     this.jPanel1.setPreferredSize(new Dimension(100, 10));
/* 109 */     this.jLabel1.setText("Web Mining and E-commerce Lab");
/* 110 */     this.insetsPanel2.add(this.imageControl1, (Object)null);
/* 111 */     this.insetsPanel2.add(this.label1);
/* 112 */     this.panel2.add(this.insetsPanel3, "Center");
/* 113 */     this.insetsPanel3.add(this.jPanel1, (Object)null);
/* 114 */     this.insetsPanel3.add(this.label2, (Object)null);
/* 115 */     this.insetsPanel3.add(this.label3, (Object)null);
/* 116 */     this.insetsPanel3.add(this.label4, (Object)null);
/* 117 */     this.insetsPanel3.add(this.label5, (Object)null);
/* 118 */     this.insetsPanel3.add(this.jLabel1);
/* 119 */     this.panel2.add(this.insetsPanel2, "North");
/* 120 */     this.panel1.add(this.insetsPanel1, "South");
/* 121 */     this.insetsPanel1.add(this.button1, (Object)null);
/* 122 */     this.panel1.add(this.panel2, "Center");
/* 123 */     getContentPane().add(this.panel1, "Center");
/*     */   }
/*     */   
/*     */   protected void processWindowEvent(WindowEvent e) {
/* 127 */     if (e.getID() == 201) {
/* 128 */       cancel();
/*     */     }
/* 130 */     super.processWindowEvent(e);
/*     */   }
/*     */   
/*     */   void cancel() {
/* 134 */     dispose();
/*     */   }
/*     */   
/*     */   public void button1_actionPerformed(ActionEvent e) {
/* 138 */     if (e.getSource() == this.button1)
/* 139 */       cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\AboutBox.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
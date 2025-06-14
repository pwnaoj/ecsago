/*     */ package unc.gui;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import unc.utils.ParameterParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UNCMainFrame
/*     */   extends JFrame
/*     */ {
/*  25 */   public static String NONAME = "noname.unc";
/*  26 */   String fileName = NONAME;
/*     */   
/*     */   UNCMainPanel mainPanel;
/*  29 */   JMenuBar jMenuBar1 = new JMenuBar();
/*  30 */   JMenu jMenu1 = new JMenu();
/*  31 */   JMenuItem jMenuItem1 = new JMenuItem();
/*  32 */   JMenuItem jMenuItem2 = new JMenuItem();
/*  33 */   JMenuItem jMenuItem3 = new JMenuItem();
/*  34 */   JMenuItem jMenuItem4 = new JMenuItem();
/*  35 */   JMenuItem jMenuItem5 = new JMenuItem();
/*  36 */   JMenu jMenu2 = new JMenu();
/*  37 */   JMenuItem jMenuItem6 = new JMenuItem();
/*     */   
/*     */   public UNCMainFrame() {
/*     */     try {
/*  41 */       jbInit();
/*  42 */     } catch (Exception ex) {
/*  43 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jbInit() throws Exception {
/*  48 */     setSize(1000, 525);
/*  49 */     this.mainPanel = new UNCMainPanel(false);
/*  50 */     setContentPane(this.mainPanel);
/*  51 */     setJMenuBar(this.jMenuBar1);
/*  52 */     addWindowListener(new UNCMainFrame_this_windowAdapter(this));
/*  53 */     this.jMenu1.setToolTipText("");
/*  54 */     this.jMenu1.setActionCommand("");
/*  55 */     this.jMenu1.setText("File[Config]");
/*  56 */     this.jMenuItem1.setText("New");
/*  57 */     this.jMenuItem1.addActionListener(new UNCMainFrame_jMenuItem1_actionAdapter(this));
/*  58 */     this.jMenuItem2.setBorder(null);
/*  59 */     this.jMenuItem2.setText("Open");
/*  60 */     this.jMenuItem2.addActionListener(new UNCMainFrame_jMenuItem2_actionAdapter(this));
/*  61 */     this.jMenuItem3.setToolTipText("");
/*  62 */     this.jMenuItem3.setText("Save");
/*  63 */     this.jMenuItem3.addActionListener(new UNCMainFrame_jMenuItem3_actionAdapter(this));
/*  64 */     this.jMenuItem4.setText("Save As");
/*  65 */     this.jMenuItem4.addActionListener(new UNCMainFrame_jMenuItem4_actionAdapter(this));
/*  66 */     this.jMenuItem5.setText("Exit");
/*  67 */     this.jMenuItem5.addActionListener(new UNCMainFrame_jMenuItem5_actionAdapter(this));
/*  68 */     this.jMenu2.setText("Help");
/*  69 */     this.jMenuItem6.setText("About");
/*  70 */     this.jMenuBar1.add(this.jMenu1);
/*  71 */     this.jMenuBar1.add(this.jMenu2);
/*  72 */     this.jMenu1.add(this.jMenuItem1);
/*  73 */     this.jMenu1.add(this.jMenuItem2);
/*  74 */     this.jMenu1.add(this.jMenuItem3);
/*  75 */     this.jMenu1.add(this.jMenuItem4);
/*  76 */     this.jMenu1.add(this.jMenuItem5);
/*  77 */     this.jMenu2.add(this.jMenuItem6);
/*     */   }
/*     */   
/*     */   public static void main(String[] argv) {
/*  81 */     UNCMainFrame frame = new UNCMainFrame();
/*  82 */     frame.show();
/*     */   }
/*     */   
/*     */   public void this_windowClosing(WindowEvent e) {
/*  86 */     System.exit(0);
/*     */   }
/*     */   
/*     */   public void jMenuItem1_actionPerformed(ActionEvent e) {
/*  90 */     this.fileName = NONAME;
/*     */   }
/*     */   
/*     */   public void jMenuItem2_actionPerformed(ActionEvent e) {
/*  94 */     JFileChooser chooser = new JFileChooser();
/*  95 */     if (chooser.showOpenDialog(this) == 0) {
/*  96 */       this.fileName = chooser.getSelectedFile().getAbsolutePath();
/*     */       
/*  98 */       String cmd = null;
/*     */       try {
/* 100 */         BufferedReader is = new BufferedReader(new FileReader(this.fileName));
/* 101 */         StringBuffer sb = new StringBuffer();
/* 102 */         String line = is.readLine();
/* 103 */         while (line != null) {
/* 104 */           sb.append(line);
/* 105 */           line = is.readLine();
/*     */         } 
/* 107 */         is.close();
/* 108 */         cmd = sb.toString();
/* 109 */       } catch (Exception ex) {
/* 110 */         ex.printStackTrace();
/*     */       } 
/* 112 */       Hashtable table = ParameterParser.get(cmd);
/* 113 */       Hashtable trun = ParameterParser.getHash(table, "runner");
/* 114 */       Hashtable ttrace = ParameterParser.getHash(table, "tracer");
/*     */       
/* 116 */       this.mainPanel.fileName = (String)trun.get("data");
/* 117 */       this.mainPanel.popTextField.setText((String)trun.get("pop_size"));
/* 118 */       this.mainPanel.genTextField.setText((String)trun.get("iterations"));
/*     */       
/* 120 */       String enc = (String)table.get("encoding");
/* 121 */       if (enc == null) enc = "real"; 
/* 122 */       int encoding = this.mainPanel.getIndex(new Object[] { "real", "binary", "gray" }, enc);
/* 123 */       this.mainPanel.encoding = encoding;
/* 124 */       switch (encoding) {
/*     */         case 0:
/* 126 */           this.mainPanel.jRadioButton1.setSelected(true);
/* 127 */           this.mainPanel.jListOperators.setModel(this.mainPanel.getDefaultModel(this.mainPanel.choicesRealOperators));
/* 128 */           this.mainPanel.jListSelectedOper.setModel(this.mainPanel.getDefaultModel());
/*     */           break;
/*     */         case 1:
/* 131 */           this.mainPanel.jRadioButton2.setSelected(true);
/* 132 */           this.mainPanel.jListOperators.setModel(this.mainPanel.getDefaultModel(this.mainPanel.choicesBinaryOperators));
/* 133 */           this.mainPanel.jListSelectedOper.setModel(this.mainPanel.getDefaultModel());
/*     */           break;
/*     */         case 2:
/* 136 */           this.mainPanel.jRadioButton3.setSelected(true);
/* 137 */           this.mainPanel.jListOperators.setModel(this.mainPanel.getDefaultModel(this.mainPanel.choicesBinaryOperators));
/* 138 */           this.mainPanel.jListSelectedOper.setModel(this.mainPanel.getDefaultModel());
/*     */           break;
/*     */       } 
/* 141 */       DefaultListModel model = (DefaultListModel)this.mainPanel.jListSelectedOper.getModel();
/*     */ 
/*     */       
/* 144 */       model = (DefaultListModel)this.mainPanel.jListSelectedOper.getModel();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/* 150 */     Hashtable run = this.mainPanel.getUNCTest();
/* 151 */     Hashtable tracer = this.mainPanel.getTracer();
/* 152 */     Hashtable table = new Hashtable();
/* 153 */     table.put("runner", run);
/* 154 */     table.put("tracer", tracer);
/*     */     try {
/* 156 */       FileWriter writer = new FileWriter(this.fileName);
/* 157 */       String txt = table.toString();
/* 158 */       writer.write(txt);
/* 159 */       writer.close();
/* 160 */     } catch (Exception ex) {
/* 161 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void jMenuItem3_actionPerformed(ActionEvent e) {
/* 166 */     if (this.mainPanel.checkInputs()) {
/* 167 */       if (this.fileName.equals(NONAME)) {
/* 168 */         jMenuItem4_actionPerformed(e);
/*     */       } else {
/* 170 */         save();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void jMenuItem4_actionPerformed(ActionEvent e) {
/* 176 */     JFileChooser chooser = new JFileChooser();
/* 177 */     if (this.mainPanel.checkInputs() && chooser.showSaveDialog(this) == 0) {
/*     */       
/* 179 */       this.fileName = chooser.getSelectedFile().getAbsolutePath();
/* 180 */       save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void jMenuItem5_actionPerformed(ActionEvent e) {
/* 185 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\UNCMainFrame.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
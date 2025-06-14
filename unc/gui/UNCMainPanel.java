/*      */ package unc.gui;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.io.File;
/*      */ import java.io.InputStream;
/*      */ import java.util.Hashtable;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.DefaultListModel;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSlider;
/*      */ import javax.swing.JSplitPane;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.TitledBorder;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import jml.data.DataSource;
/*      */ import jml.data.sources.FileDataSource;
/*      */ import unc.Prototype;
/*      */ import unc.utils.test.UNCParameterizedTest;
/*      */ 
/*      */ public class UNCMainPanel extends JPanel {
/*   36 */   GUITracer tracer = null; boolean appletMode = true;
/*      */   String fileName;
/*   38 */   int encoding = 0;
/*      */   
/*   40 */   Object[] choicesDataSet = new Object[] { "Three_Close_Clust.txt", "Three_Close_Clust_nois_pt01.txt", "Three_Close_Clust_nois_pt02.txt", "Three_Close_Clust_nois_pt005.txt", "Five_Clust.txt", "Five_Clust_nois_pt01.txt", "Five_Clust_nois_pt02.txt", "Five_Clust_nois_pt005.txt", "Six_Clust.txt", "Six_Clust_nois_pt01.txt", "Six_Clust_nois_pt02.txt", "Six_Clust_nois_pt005.txt", "cluster-2-0.txt", "cluster-2-1.txt", "cluster-2-2.txt", "cluster-5-0.txt", "cluster-5-1.txt", "cluster-5-2.txt", "cluster-10-0.txt", "cluster-10-1.txt", "cluster-10-2.txt", "cluster-15-0.txt", "cluster-15-1.txt", "cluster-15-2.txt", "t7.10k.txt" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   48 */   Object[] choicesAlgorithm = new Object[] { "UNC", "ECSAGO", "SCALABLE ECSAGO" };
/*   49 */   Object[] choicesBinaryOperators = new Object[] { "BitMutation", "SimpleXover" };
/*   50 */   Object[] choicesRealOperators = new Object[] { "LinearXover", "LCD", "GaussianMutation", "AdaptiveGaussianMutation" };
/*   51 */   String[] selectedOperators = new String[] { "LCD", "AdaptiveGaussianMutation" };
/*   52 */   String[] choicesDistance = new String[] { "Euclidean", "Minkowski", "Jaccard", "Cosine" };
/*   53 */   String[] choicesExtraction = new String[] { "average", "maximum" };
/*      */   
/*   55 */   Object[] binaryMutations = new Object[] { "BitMutation" };
/*   56 */   Object[] binaryXOvers = new Object[] { "SimpleXover" };
/*   57 */   Object[] realMutations = new Object[] { "GaussianMutation", "AdaptiveGaussianMutation" };
/*   58 */   Object[] realXOvers = new Object[] { "LC", "LCD" };
/*      */ 
/*      */   
/*   61 */   JPanel jPanel1 = new JPanel();
/*   62 */   JComboBox jComboUNC = new JComboBox(new String[] { "Classic UNC", "HAEA+UNC", "ECSAGO" });
/*   63 */   JPanel jPanel2 = new JPanel();
/*   64 */   JComboBox jComboData = new JComboBox(this.choicesDataSet);
/*   65 */   JLabel jLabel1 = new JLabel();
/*   66 */   JPanel Data = new JPanel();
/*   67 */   BorderLayout borderLayout2 = new BorderLayout();
/*   68 */   JPanel Evolution = new JPanel();
/*   69 */   TitledBorder titledBorder1 = new TitledBorder("");
/*   70 */   Border border1 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
/*      */   
/*   72 */   Border border2 = new TitledBorder(this.border1, "Evolutionary parameters");
/*   73 */   JPanel ExtracRefinement = new JPanel();
/*   74 */   Border border3 = BorderFactory.createEmptyBorder();
/*   75 */   Border border4 = new TitledBorder(this.border3, "Extraction");
/*   76 */   JPanel Refinement = new JPanel();
/*   77 */   BoxLayout verticalFlowLayout1 = null;
/*   78 */   Border border5 = BorderFactory.createEmptyBorder();
/*   79 */   Border border6 = new TitledBorder(this.border5, "Refinement");
/*   80 */   Border border7 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
/*      */   
/*   82 */   Border border8 = new TitledBorder(this.border7, "Evolution");
/*   83 */   JPanel Population = new JPanel();
/*   84 */   JTextField popTextField = new JTextField();
/*   85 */   JLabel jLabel3 = new JLabel();
/*   86 */   JPanel Incremental = new JPanel();
/*   87 */   JTextField genTextField = new JTextField();
/*   88 */   JLabel jLabel4 = new JLabel();
/*   89 */   JPanel PopGen = new JPanel();
/*   90 */   JPanel Operators = new JPanel();
/*   91 */   JPanel UNCOperators = new JPanel();
/*   92 */   JPanel ECSAGOOperators = new JPanel();
/*      */   
/*   94 */   JList jListOperators = new JList();
/*   95 */   JList jListSelectedOper = new JList();
/*   96 */   JScrollPane jScrollPane1 = new JScrollPane();
/*   97 */   JScrollPane jScrollPane2 = new JScrollPane();
/*      */   
/*   99 */   JList jMutationList = new JList();
/*  100 */   JScrollPane jMutationScrollPanel = new JScrollPane();
/*  101 */   JPanel jMutationProbPane = new JPanel();
/*  102 */   JLabel jMutationProbLabel = new JLabel("Probability:");
/*  103 */   JTextField jMutationProbText = new JTextField("0.1");
/*  104 */   JPanel jMutationPane = new JPanel();
/*      */   
/*  106 */   JList jXOverList = new JList();
/*  107 */   JScrollPane jXOverScrollPanel = new JScrollPane();
/*  108 */   JPanel jXOverProbPane = new JPanel();
/*  109 */   JLabel jXOverProbLabel = new JLabel("Probability:");
/*  110 */   JTextField jXOverProbText = new JTextField("0.9");
/*  111 */   JPanel jXOverPane = new JPanel();
/*      */   
/*  113 */   JButton jButton2 = new JButton();
/*  114 */   JButton jButton3 = new JButton();
/*  115 */   JPanel operButtonsPanel = new JPanel();
/*  116 */   BoxLayout verticalFlowLayout2 = null;
/*  117 */   BoxLayout verticalFlowLayout3 = null;
/*  118 */   Border border9 = BorderFactory.createLineBorder(Color.white, 2);
/*  119 */   Border border10 = new TitledBorder(this.border9, "Operators");
/*  120 */   JPanel Encoding = new JPanel();
/*  121 */   JRadioButton jRadioButton1 = new JRadioButton();
/*  122 */   ButtonGroup buttonGroup1 = new ButtonGroup();
/*  123 */   JRadioButton jRadioButton2 = new JRadioButton();
/*  124 */   JRadioButton jRadioButton3 = new JRadioButton();
/*  125 */   Border border11 = BorderFactory.createLineBorder(SystemColor.controlHighlight, 2);
/*      */   
/*  127 */   Border border12 = new TitledBorder(this.border11, "Encoding");
/*  128 */   JRadioButton jRadioButton4 = new JRadioButton();
/*  129 */   JRadioButton MDERadioButton = new JRadioButton();
/*  130 */   JPanel jPanel6 = new JPanel();
/*  131 */   JTextField refIterTextField = new JTextField();
/*  132 */   JLabel jLabel6 = new JLabel();
/*  133 */   ButtonGroup buttonGroup2 = new ButtonGroup();
/*  134 */   BoxLayout verticalFlowLayout4 = null;
/*  135 */   BoxLayout verticalFlowLayout5 = null;
/*  136 */   JPanel Parameters = new JPanel();
/*  137 */   JPanel Distance = new JPanel();
/*  138 */   JComboBox jComboBoxDistance = new JComboBox(this.choicesDistance);
/*  139 */   JLabel jLabel7 = new JLabel();
/*  140 */   JPanel UncParameters = new JPanel();
/*  141 */   JPanel Weight = new JPanel();
/*  142 */   JPanel jPanelWThold = new JPanel();
/*  143 */   JPanel jPanelWcardinality = new JPanel();
/*  144 */   JCheckBox jCheckBoxCardin = new JCheckBox();
/*  145 */   JCheckBox jCheckBoxThold = new JCheckBox();
/*  146 */   JTextField cardinalityTextField = new JTextField();
/*  147 */   JTextField thresholdTextField = new JTextField();
/*      */ 
/*      */   
/*  150 */   BoxLayout verticalFlowLayout6 = null;
/*  151 */   BoxLayout verticalFlowLayout7 = null;
/*  152 */   Border border17 = BorderFactory.createLineBorder(Color.white, 2);
/*  153 */   Border border18 = new TitledBorder(this.border17, "Weight");
/*  154 */   JPanel Extraction = new JPanel();
/*  155 */   BorderLayout borderLayout1 = new BorderLayout();
/*  156 */   JComboBox jComboBoxExt = new JComboBox(this.choicesExtraction);
/*  157 */   JLabel jLabel8 = new JLabel();
/*  158 */   JTextField extTextField = new JTextField();
/*  159 */   JLabel jLabel9 = new JLabel();
/*  160 */   FlowLayout flowLayout1 = new FlowLayout();
/*  161 */   JPanel OperParameters = new JPanel();
/*  162 */   Border border19 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
/*      */   
/*  164 */   Border border20 = new TitledBorder(this.border19, "Parameters");
/*      */   
/*  166 */   GaussianMutationPanel gmp = new GaussianMutationPanel();
/*      */   
/*  168 */   JPanel runPanel = new JPanel();
/*  169 */   JButton jButton4 = new JButton();
/*  170 */   JLabel jLabel10 = new JLabel();
/*  171 */   JTextField KTextField = new JTextField();
/*      */   
/*  173 */   JSplitPane mainPanel = new JSplitPane();
/*  174 */   JPanel leftPanel = new JPanel();
/*  175 */   JPanel rightPanel = new JPanel();
/*  176 */   DrawPanel drawPanel = new DrawPanel();
/*  177 */   JPanel drawOptionPanel = new JPanel();
/*  178 */   JSlider jSlider1 = new JSlider();
/*  179 */   JLabel iterShowLabel = new JLabel();
/*  180 */   JCheckBox showRadii = new JCheckBox();
/*      */   BoxLayout flowLayout2;
/*  182 */   JButton jButton5 = new JButton();
/*  183 */   TitledBorder titledBorder4 = new TitledBorder("Incremental");
/*  184 */   JCheckBox incrementalCheckBox = new JCheckBox();
/*  185 */   JLabel jLabel5 = new JLabel();
/*  186 */   JTextField memoryTextField = new JTextField();
/*  187 */   JPanel jPanel3 = new JPanel();
/*  188 */   JCheckBox randomCheckBox = new JCheckBox();
/*  189 */   JPanel jPanel5 = new JPanel();
/*  190 */   JLabel jLabel12 = new JLabel();
/*  191 */   JTextField groupsTextField = new JTextField();
/*  192 */   FlowLayout flowLayout3 = new FlowLayout();
/*  193 */   FlowLayout flowLayout4 = new FlowLayout();
/*  194 */   Border border13 = BorderFactory.createLineBorder(Color.white, 2);
/*  195 */   Border border14 = new TitledBorder(this.border13, "Incremental");
/*  196 */   Border border15 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
/*      */   
/*  198 */   Border border16 = new TitledBorder(this.border15, "Scalable Dynamic Clustering");
/*  199 */   Border border21 = BorderFactory.createLineBorder(Color.white, 2);
/*  200 */   Border border22 = new TitledBorder(this.border21, "Scalable Dynamic Clustering");
/*  201 */   JLabel groupLabel = new JLabel();
/*  202 */   JSlider groupSlider = new JSlider();
/*  203 */   FlowLayout flowLayout5 = new FlowLayout();
/*  204 */   BorderLayout borderLayout3 = new BorderLayout();
/*  205 */   BorderLayout borderLayout4 = new BorderLayout();
/*  206 */   BorderLayout borderLayout5 = new BorderLayout();
/*      */   
/*      */   public UNCMainPanel(boolean _appletMode) {
/*  209 */     this.appletMode = _appletMode;
/*      */     try {
/*  211 */       jbInit();
/*  212 */     } catch (Exception ex) {
/*  213 */       ex.printStackTrace();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jbInit() throws Exception {
/*  218 */     this.Data.setLayout(this.borderLayout2);
/*  219 */     this.jLabel1.setText("Data Set:");
/*  220 */     this.jButton5.setText("Choose ");
/*  221 */     this.jButton5.addActionListener(new UNCMainPanel_jButton5_actionAdapter(this));
/*  222 */     if (this.appletMode) { this.jButton5.setVisible(false); } else { this.jComboData.setVisible(false); }
/*  223 */      this.jComboData.addItemListener(new UNCMainPanel_jComboData_itemAdapter(this));
/*  224 */     this.iterShowLabel.setAlignmentX(0.0F);
/*  225 */     this.iterShowLabel.setMaximumSize(new Dimension(80, 15));
/*  226 */     this.iterShowLabel.setMinimumSize(new Dimension(80, 15));
/*  227 */     this.iterShowLabel.setPreferredSize(new Dimension(70, 15));
/*  228 */     this.iterShowLabel.setHorizontalAlignment(4);
/*  229 */     this.jSlider1.setMinimumSize(new Dimension(120, 32));
/*  230 */     this.jSlider1.setPreferredSize(new Dimension(120, 32));
/*  231 */     this.groupLabel.setMinimumSize(new Dimension(53, 15));
/*  232 */     this.groupLabel.setPreferredSize(new Dimension(53, 15));
/*  233 */     this.groupLabel.setToolTipText("");
/*  234 */     this.groupLabel.setHorizontalAlignment(4);
/*  235 */     this.groupLabel.setText("Group:");
/*  236 */     this.groupSlider.setMaximum(10);
/*  237 */     this.groupSlider.setMinorTickSpacing(1);
/*  238 */     this.groupSlider.setPaintTicks(true);
/*  239 */     this.groupSlider.setMinimumSize(new Dimension(50, 24));
/*  240 */     this.groupSlider.setPreferredSize(new Dimension(50, 24));
/*  241 */     this.groupSlider.addChangeListener(new UNCMainPanel_groupSlider_changeAdapter(this));
/*      */     
/*  243 */     this.incrementalCheckBox.addChangeListener(new UNCMainPanel_incrementalCheckBox_changeAdapter(this));
/*      */     
/*  245 */     this.drawOptionPanel.setLayout(this.flowLayout5);
/*  246 */     this.flowLayout5.setHgap(2);
/*  247 */     this.jPanel5.setLayout(this.borderLayout3);
/*  248 */     this.jPanel5.setMinimumSize(new Dimension(180, 30));
/*  249 */     this.jPanel5.setPreferredSize(new Dimension(180, 43));
/*  250 */     this.jPanel3.setLayout(this.borderLayout5);
/*  251 */     this.jPanel3.setPreferredSize(new Dimension(186, 20));
/*  252 */     this.jPanel3.setToolTipText("");
/*  253 */     this.memoryTextField.setMinimumSize(new Dimension(15, 20));
/*  254 */     this.memoryTextField.setPreferredSize(new Dimension(40, 20));
/*  255 */     this.jComboBoxExt.setSelectedIndex(1);
/*  256 */     this.Encoding.setMaximumSize(new Dimension(120, 96));
/*  257 */     this.jComboUNC.addItemListener(new UNCMainPanel_jComboUNC_itemAdapter(this));
/*  258 */     this.jPanel1.add(this.jLabel1);
/*  259 */     this.jPanel1.add(this.jButton5);
/*  260 */     this.jPanel1.add(this.jComboData);
/*  261 */     this.Data.add(this.jPanel1, "West");
/*      */     
/*  263 */     this.jPanel2.add(this.jComboUNC);
/*  264 */     this.Data.add(this.jPanel2, "East");
/*      */     
/*  266 */     this.Evolution.setBorder(this.border8);
/*  267 */     this.verticalFlowLayout3 = new BoxLayout(this.Evolution, 1);
/*  268 */     this.Evolution.setLayout(this.verticalFlowLayout3);
/*      */     
/*  270 */     this.randomCheckBox.setToolTipText("");
/*  271 */     this.randomCheckBox.setText("Random");
/*  272 */     this.jLabel12.setText("# of incremental data batches:");
/*  273 */     this.groupsTextField.setMinimumSize(new Dimension(40, 20));
/*  274 */     this.groupsTextField.setPreferredSize(new Dimension(40, 20));
/*  275 */     this.groupsTextField.setText("10");
/*  276 */     this.Population.setMinimumSize(new Dimension(100, 30));
/*  277 */     this.Population.setToolTipText("");
/*  278 */     this.Population.setLayout(this.flowLayout3);
/*  279 */     this.Parameters.setLayout(this.flowLayout4);
/*  280 */     this.PopGen.setPreferredSize(new Dimension(130, 150));
/*  281 */     this.flowLayout2 = new BoxLayout(this.PopGen, 1);
/*  282 */     this.PopGen.setLayout(this.flowLayout2);
/*  283 */     this.jRadioButton2.setPreferredSize(new Dimension(60, 23));
/*  284 */     this.Weight.setPreferredSize(new Dimension(153, 108));
/*  285 */     this.jCheckBoxCardin.setMaximumSize(new Dimension(85, 23));
/*  286 */     this.jCheckBoxCardin.setPreferredSize(new Dimension(85, 23));
/*  287 */     this.cardinalityTextField.setPreferredSize(new Dimension(32, 20));
/*  288 */     this.genTextField.setPreferredSize(new Dimension(32, 20));
/*  289 */     this.jLabel3.setMaximumSize(new Dimension(100, 15));
/*  290 */     this.jLabel3.setPreferredSize(new Dimension(70, 15));
/*  291 */     this.Population.setPreferredSize(new Dimension(120, 30));
/*  292 */     this.jLabel4.setMaximumSize(new Dimension(100, 15));
/*  293 */     this.jLabel4.setPreferredSize(new Dimension(70, 15));
/*  294 */     this.popTextField.setPreferredSize(new Dimension(32, 20));
/*  295 */     this.popTextField.setText("100");
/*  296 */     this.jLabel3.setText("Population");
/*  297 */     this.Population.add(this.jLabel3);
/*  298 */     this.Population.add(this.popTextField);
/*  299 */     this.genTextField.setText("30");
/*  300 */     this.jLabel4.setText("Generations");
/*      */ 
/*      */ 
/*      */     
/*  304 */     this.Population.add(this.jLabel4);
/*  305 */     this.Population.add(this.genTextField);
/*      */     
/*  307 */     this.Incremental.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Scalable Dynamic Clustering"));
/*      */     
/*  309 */     this.Incremental.setLayout(this.borderLayout4);
/*  310 */     this.jLabel5.setText("Memorization Factor:");
/*  311 */     this.memoryTextField.setText("1.0");
/*  312 */     this.incrementalCheckBox.setToolTipText("");
/*  313 */     this.incrementalCheckBox.setText("Enable");
/*  314 */     this.groupLabel.setVisible(false);
/*  315 */     this.groupSlider.setVisible(false);
/*  316 */     this.PopGen.add(this.Population, (Object)null);
/*  317 */     this.PopGen.add(this.Encoding);
/*  318 */     this.Encoding.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Encoding"));
/*      */     
/*  320 */     this.Encoding.setPreferredSize(new Dimension(120, 95));
/*      */     
/*  322 */     this.verticalFlowLayout5 = new BoxLayout(this.Encoding, 1);
/*  323 */     this.Encoding.setLayout(this.verticalFlowLayout5);
/*  324 */     this.jRadioButton1.setText("Real");
/*  325 */     this.jRadioButton3.setText("Gray");
/*  326 */     this.jRadioButton2.setText("Binary");
/*  327 */     this.jRadioButton1.setSelected(true);
/*  328 */     this.jRadioButton1.addChangeListener(new UNCMainPanel_jRadioButton_changeAdapter(this));
/*      */     
/*  330 */     this.jRadioButton2.addChangeListener(new UNCMainPanel_jRadioButton_changeAdapter(this));
/*      */     
/*  332 */     this.jRadioButton3.addChangeListener(new UNCMainPanel_jRadioButton_changeAdapter(this));
/*      */     
/*  334 */     this.buttonGroup1.add(this.jRadioButton1);
/*  335 */     this.buttonGroup1.add(this.jRadioButton2);
/*  336 */     this.buttonGroup1.add(this.jRadioButton3);
/*  337 */     this.Encoding.add(this.jRadioButton1);
/*  338 */     this.Encoding.add(this.jRadioButton2);
/*  339 */     this.Encoding.add(this.jRadioButton3);
/*      */     
/*  341 */     this.verticalFlowLayout7 = new BoxLayout(this.UncParameters, 1);
/*  342 */     this.UncParameters.setLayout(this.verticalFlowLayout7);
/*  343 */     this.verticalFlowLayout6 = new BoxLayout(this.Weight, 1);
/*  344 */     this.Weight.setLayout(this.verticalFlowLayout6);
/*      */     
/*  346 */     this.Weight.setBorder(this.border18);
/*  347 */     this.jCheckBoxThold.setMaximumSize(new Dimension(85, 23));
/*  348 */     this.jCheckBoxThold.setMinimumSize(new Dimension(75, 23));
/*  349 */     this.jCheckBoxThold.setPreferredSize(new Dimension(85, 23));
/*  350 */     this.jCheckBoxThold.setSelected(true);
/*  351 */     this.jCheckBoxThold.setText("Threshold");
/*  352 */     this.jCheckBoxCardin.setText("Cardinality");
/*  353 */     this.thresholdTextField.setPreferredSize(new Dimension(32, 20));
/*  354 */     this.thresholdTextField.setText("0.3");
/*  355 */     this.cardinalityTextField.setText("100");
/*  356 */     this.jPanelWThold.add(this.jCheckBoxThold);
/*  357 */     this.jPanelWThold.add(this.thresholdTextField);
/*  358 */     this.jPanelWcardinality.add(this.jCheckBoxCardin);
/*  359 */     this.jPanelWcardinality.add(this.cardinalityTextField);
/*  360 */     this.Weight.add(this.jPanelWThold);
/*  361 */     this.Weight.add(this.jPanelWcardinality);
/*      */     
/*  363 */     this.jLabel7.setText("Distance");
/*  364 */     this.Distance.add(this.jLabel7);
/*  365 */     this.Distance.add(this.jComboBoxDistance);
/*      */ 
/*      */     
/*  368 */     this.UncParameters.add(this.Weight);
/*  369 */     this.UncParameters.add(this.Distance);
/*      */ 
/*      */ 
/*      */     
/*  373 */     this.border4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151)), "Extraction");
/*      */     
/*  375 */     this.Extraction.setBorder(this.border4);
/*  376 */     this.jLabel8.setText("relative to");
/*  377 */     this.extTextField.setText("25");
/*  378 */     this.jLabel9.setText("%");
/*  379 */     this.jLabel10.setText("K:");
/*  380 */     this.KTextField.setMaximumSize(new Dimension(32, 20));
/*  381 */     this.KTextField.setMinimumSize(new Dimension(32, 20));
/*  382 */     this.KTextField.setToolTipText("");
/*  383 */     this.KTextField.setText("13.8");
/*  384 */     this.Extraction.add(this.extTextField);
/*  385 */     this.Extraction.add(this.jLabel9);
/*  386 */     this.Extraction.add(this.jLabel8);
/*  387 */     this.Extraction.add(this.jComboBoxExt);
/*  388 */     this.Extraction.add(this.jLabel10);
/*  389 */     this.Extraction.add(this.KTextField);
/*      */     
/*  391 */     this.border6 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151)), "Refinement");
/*      */     
/*  393 */     this.Refinement.setBorder(this.border6);
/*  394 */     this.jRadioButton4.setToolTipText("");
/*  395 */     this.jRadioButton4.setText("None");
/*  396 */     this.MDERadioButton.setSelected(true);
/*  397 */     this.MDERadioButton.setText("MDE");
/*  398 */     this.buttonGroup2.add(this.jRadioButton4);
/*  399 */     this.buttonGroup2.add(this.MDERadioButton);
/*      */     
/*  401 */     this.refIterTextField.setText("10");
/*  402 */     this.jLabel6.setText("Iterations");
/*  403 */     this.jPanel6.add(this.jLabel6);
/*  404 */     this.jPanel6.add(this.refIterTextField);
/*      */     
/*  406 */     this.Refinement.add(this.jRadioButton4);
/*  407 */     this.Refinement.add(this.MDERadioButton);
/*  408 */     this.Refinement.add(this.jPanel6);
/*      */ 
/*      */     
/*  411 */     this.ExtracRefinement.setLayout(this.borderLayout1);
/*  412 */     this.ExtracRefinement.add(this.Extraction, "West");
/*  413 */     this.ExtracRefinement.add(this.Refinement, "Center");
/*      */ 
/*      */ 
/*      */     
/*  417 */     this.jMutationScrollPanel.setMaximumSize(new Dimension(180, 42));
/*  418 */     this.jMutationScrollPanel.setPreferredSize(new Dimension(180, 42));
/*  419 */     this.jMutationList.setMaximumSize(new Dimension(150, 40));
/*  420 */     this.jMutationList.setMinimumSize(new Dimension(150, 40));
/*  421 */     this.jMutationList.setPreferredSize(new Dimension(150, 40));
/*  422 */     this.jMutationList.setModel(getDefaultModel(this.realMutations));
/*  423 */     this.jMutationList.setSelectedIndex(0);
/*  424 */     this.jMutationScrollPanel.getViewport().add(this.jMutationList);
/*  425 */     this.jMutationProbPane.setLayout(new FlowLayout());
/*  426 */     this.jMutationProbPane.add(this.jMutationProbLabel);
/*  427 */     this.jMutationProbText.setMaximumSize(new Dimension(70, 19));
/*  428 */     this.jMutationProbText.setMinimumSize(new Dimension(70, 19));
/*  429 */     this.jMutationProbText.setPreferredSize(new Dimension(70, 19));
/*  430 */     this.jMutationProbPane.add(this.jMutationProbText);
/*      */     
/*  432 */     BoxLayout mutationLayout = new BoxLayout(this.jMutationPane, 1);
/*  433 */     this.jMutationPane.setLayout(mutationLayout);
/*  434 */     this.jMutationPane.setBorder(new TitledBorder("Mutation"));
/*  435 */     this.jMutationPane.add(this.jMutationScrollPanel, (Object)null);
/*  436 */     this.jMutationPane.add(this.jMutationProbPane, (Object)null);
/*      */     
/*  438 */     this.jXOverScrollPanel.setMaximumSize(new Dimension(180, 42));
/*  439 */     this.jXOverScrollPanel.setPreferredSize(new Dimension(180, 42));
/*  440 */     this.jXOverList.setMaximumSize(new Dimension(150, 40));
/*  441 */     this.jXOverList.setMinimumSize(new Dimension(150, 40));
/*  442 */     this.jXOverList.setPreferredSize(new Dimension(150, 40));
/*  443 */     this.jXOverList.setModel(getDefaultModel(this.realXOvers));
/*  444 */     this.jXOverList.setSelectedIndex(0);
/*  445 */     this.jXOverScrollPanel.getViewport().add(this.jXOverList);
/*      */     
/*  447 */     this.jXOverProbPane.setLayout(new FlowLayout());
/*  448 */     this.jXOverProbPane.add(this.jXOverProbLabel);
/*  449 */     this.jXOverProbText.setMaximumSize(new Dimension(70, 19));
/*  450 */     this.jXOverProbText.setMinimumSize(new Dimension(70, 19));
/*  451 */     this.jXOverProbText.setPreferredSize(new Dimension(70, 19));
/*  452 */     this.jXOverProbPane.add(this.jXOverProbText);
/*      */     
/*  454 */     BoxLayout xoverLayout = new BoxLayout(this.jXOverPane, 1);
/*  455 */     this.jXOverPane.setLayout(xoverLayout);
/*  456 */     this.jXOverPane.setBorder(new TitledBorder("XOver"));
/*  457 */     this.jXOverPane.add(this.jXOverScrollPanel, (Object)null);
/*  458 */     this.jXOverPane.add(this.jXOverProbPane, (Object)null);
/*      */     
/*  460 */     this.UNCOperators.setLayout(new GridLayout(1, 2));
/*  461 */     this.UNCOperators.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Operators"));
/*      */     
/*  463 */     this.UNCOperators.add(this.jMutationPane, (Object)null);
/*  464 */     this.UNCOperators.add(this.jXOverPane, (Object)null);
/*      */ 
/*      */ 
/*      */     
/*  468 */     this.jButton2.setText(">>");
/*  469 */     this.jButton3.setText("<<");
/*  470 */     this.jButton2.addActionListener(new UNCMainPanel_jButton2_actionAdapter(this));
/*  471 */     this.jButton3.addActionListener(new UNCMainPanel_jButton3_actionAdapter(this));
/*  472 */     this.operButtonsPanel.setMaximumSize(new Dimension(55, 61));
/*      */     
/*  474 */     this.verticalFlowLayout2 = new BoxLayout(this.operButtonsPanel, 1);
/*  475 */     this.operButtonsPanel.setLayout(this.verticalFlowLayout2);
/*  476 */     this.operButtonsPanel.add(this.jButton2);
/*  477 */     this.operButtonsPanel.add(this.jButton3);
/*      */     
/*  479 */     this.jScrollPane1.setMaximumSize(new Dimension(175, 60));
/*  480 */     this.jScrollPane1.setPreferredSize(new Dimension(175, 60));
/*  481 */     this.jListOperators.setMaximumSize(new Dimension(145, 68));
/*  482 */     this.jListOperators.setMinimumSize(new Dimension(145, 68));
/*  483 */     this.jListOperators.setPreferredSize(new Dimension(145, 68));
/*  484 */     this.jListOperators.setModel(getDefaultModel(this.choicesRealOperators));
/*  485 */     this.jScrollPane1.getViewport().add(this.jListOperators);
/*      */     
/*  487 */     this.jScrollPane2.setMaximumSize(new Dimension(175, 60));
/*  488 */     this.jScrollPane2.setPreferredSize(new Dimension(175, 60));
/*  489 */     this.jListSelectedOper.setSelectionMode(0);
/*  490 */     this.jListSelectedOper.addListSelectionListener(new UNCMainPanel_jListSelectedOper_listSelectionAdapter(this));
/*      */     
/*  492 */     this.jListSelectedOper.setBorder((Border)null);
/*  493 */     this.jListSelectedOper.setMinimumSize(new Dimension(170, 72));
/*  494 */     this.jListSelectedOper.setPreferredSize(new Dimension(145, 68));
/*  495 */     this.jListSelectedOper.setModel(getDefaultModel());
/*  496 */     this.jScrollPane2.getViewport().add(this.jListSelectedOper);
/*      */     
/*  498 */     this.OperParameters.setBorder(this.border20);
/*  499 */     this.OperParameters.setMaximumSize(new Dimension(95, 80));
/*  500 */     this.OperParameters.setMinimumSize(new Dimension(95, 80));
/*  501 */     this.OperParameters.setPreferredSize(new Dimension(95, 80));
/*  502 */     this.OperParameters.add(this.gmp);
/*  503 */     this.gmp.setVisible(false);
/*      */     
/*  505 */     this.ECSAGOOperators.setLayout(this.flowLayout1);
/*  506 */     this.ECSAGOOperators.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Operators"));
/*      */     
/*  508 */     this.ECSAGOOperators.add(this.jScrollPane1, (Object)null);
/*  509 */     this.ECSAGOOperators.add(this.operButtonsPanel, (Object)null);
/*  510 */     this.ECSAGOOperators.add(this.jScrollPane2, (Object)null);
/*  511 */     this.ECSAGOOperators.add(this.OperParameters, (Object)null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  516 */     this.Operators.setLayout(new BorderLayout());
/*  517 */     this.ECSAGOOperators.setVisible(false);
/*  518 */     this.Operators.add(this.ECSAGOOperators, "Center");
/*  519 */     this.Operators.add(this.UNCOperators, "Center");
/*      */ 
/*      */     
/*  522 */     this.Parameters.add(this.PopGen);
/*  523 */     this.Parameters.add(this.UncParameters);
/*  524 */     this.Parameters.add(this.Incremental);
/*      */ 
/*      */     
/*  527 */     this.Evolution.add(this.Parameters);
/*  528 */     this.Evolution.add(this.Operators);
/*      */     
/*  530 */     this.jButton4.setText("Run");
/*  531 */     this.jButton4.addActionListener(new UNCMainPanel_jButton4_actionAdapter(this));
/*  532 */     this.runPanel.add(this.jButton4);
/*      */ 
/*      */     
/*  535 */     this.verticalFlowLayout1 = new BoxLayout(this.leftPanel, 1);
/*  536 */     this.leftPanel.setLayout(this.verticalFlowLayout1);
/*  537 */     this.leftPanel.add(this.Data, (Object)null);
/*  538 */     this.leftPanel.add(this.Evolution, (Object)null);
/*  539 */     this.leftPanel.add(this.ExtracRefinement, (Object)null);
/*  540 */     this.leftPanel.add(this.runPanel, (Object)null);
/*      */     
/*  542 */     this.rightPanel.setLayout(new BorderLayout());
/*  543 */     this.rightPanel.add(this.drawPanel, "Center");
/*  544 */     this.rightPanel.add(this.drawOptionPanel, "South");
/*  545 */     this.drawPanel.setBackground(Color.white);
/*      */     
/*  547 */     this.drawPanel.setMinimumSize(new Dimension(400, 400));
/*  548 */     this.drawPanel.setPreferredSize(new Dimension(400, 400));
/*  549 */     this.jSlider1.setMajorTickSpacing(10);
/*  550 */     this.jSlider1.setMinorTickSpacing(1);
/*  551 */     this.jSlider1.setPaintTicks(true);
/*  552 */     this.jSlider1.addChangeListener(new UNCMainPanel_jSlider1_changeAdapter(this));
/*  553 */     this.iterShowLabel.setToolTipText("");
/*  554 */     this.iterShowLabel.setText("Iteration:");
/*  555 */     this.showRadii.setToolTipText("");
/*  556 */     this.showRadii.setText("Show Radii");
/*  557 */     this.drawOptionPanel.setBorder(BorderFactory.createEtchedBorder());
/*  558 */     this.rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
/*  559 */     this.drawOptionPanel.add(this.iterShowLabel);
/*  560 */     this.drawOptionPanel.add(this.jSlider1);
/*  561 */     this.drawOptionPanel.add(this.groupLabel);
/*  562 */     this.drawOptionPanel.add(this.groupSlider);
/*  563 */     this.drawOptionPanel.add(this.showRadii);
/*  564 */     this.mainPanel.setOrientation(1);
/*      */ 
/*      */     
/*  567 */     this.mainPanel.setLeftComponent(this.leftPanel);
/*  568 */     this.mainPanel.setRightComponent(this.rightPanel);
/*  569 */     this.jPanel5.add(this.jLabel12, "Center");
/*  570 */     this.jPanel5.add(this.groupsTextField, "East");
/*  571 */     this.jPanel5.add(this.randomCheckBox, "South");
/*  572 */     this.Incremental.add(this.incrementalCheckBox, "North");
/*  573 */     this.Incremental.add(this.jPanel3, "Center");
/*  574 */     this.Incremental.add(this.jPanel5, "South");
/*  575 */     this.jPanel3.add(this.jLabel5, "Center");
/*  576 */     this.jPanel3.add(this.memoryTextField, "East");
/*  577 */     add(this.mainPanel, (Object)null);
/*      */   }
/*      */   
/*      */   int getIndex(Object[] values, Object obj) {
/*  581 */     int i = 0;
/*  582 */     for (; i < values.length && values[i] != obj; i++);
/*  583 */     return i;
/*      */   }
/*      */   DefaultListModel getDefaultModel() {
/*  586 */     return getDefaultModel((Object[])null);
/*      */   }
/*      */   DefaultListModel getDefaultModel(Object[] values) {
/*  589 */     DefaultListModel listModel = new DefaultListModel();
/*  590 */     if (values != null) {
/*  591 */       for (int i = 0; i < values.length; i++) {
/*  592 */         listModel.addElement(values[i]);
/*      */       }
/*      */     }
/*  595 */     return listModel;
/*      */   }
/*      */   
/*      */   public void jButton2_actionPerformed(ActionEvent e) {
/*  599 */     Object[] selectedOper = this.jListOperators.getSelectedValues();
/*  600 */     DefaultListModel model = (DefaultListModel)this.jListOperators.getModel();
/*  601 */     DefaultListModel model_sel = (DefaultListModel)this.jListSelectedOper.getModel(); int i;
/*  602 */     for (i = 0; i < selectedOper.length; i++) {
/*  603 */       model_sel.addElement(selectedOper[i]);
/*      */     }
/*  605 */     for (i = 0; i < selectedOper.length; i++)
/*  606 */       model.removeElement(selectedOper[i]); 
/*      */   }
/*      */   
/*      */   public void jListSelectedOper_valueChanged(ListSelectionEvent e) {
/*      */     int index;
/*  611 */     Object obj = this.jListSelectedOper.getSelectedValue();
/*  612 */     switch (this.encoding) {
/*      */       case 0:
/*  614 */         index = getIndex(this.choicesRealOperators, obj);
/*  615 */         if (index == 2) {
/*  616 */           this.gmp.setVisible(true); break;
/*      */         } 
/*  618 */         this.gmp.setVisible(false);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void jButton3_actionPerformed(ActionEvent e) {
/*  630 */     Object[] selectedOper = this.jListSelectedOper.getSelectedValues();
/*  631 */     DefaultListModel model = (DefaultListModel)this.jListOperators.getModel();
/*  632 */     DefaultListModel model_sel = (DefaultListModel)this.jListSelectedOper.getModel(); int i;
/*  633 */     for (i = 0; i < selectedOper.length; i++) {
/*  634 */       model.addElement(selectedOper[i]);
/*      */     }
/*  636 */     for (i = 0; i < selectedOper.length; i++) {
/*  637 */       model_sel.removeElement(selectedOper[i]);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Hashtable getUNCTest() {
/*  643 */     Hashtable table = null;
/*  644 */     if (checkInputs()) {
/*  645 */       Hashtable muttable, xovtable; int i; table = new Hashtable();
/*  646 */       if (this.appletMode) {
/*  647 */         String dataset = (String)this.jComboData.getSelectedItem();
/*  648 */         InputStream is = UNCMainPanel.class.getResourceAsStream("/datasets/Synthetic2/" + dataset);
/*      */         
/*  650 */         checkParameters();
/*  651 */         table.put("data", is);
/*      */       } else {
/*  653 */         table.put("data", this.fileName);
/*      */       } 
/*      */ 
/*      */       
/*  657 */       table.put("pop_size", this.popTextField.getText());
/*  658 */       table.put("iterations", this.genTextField.getText());
/*      */       
/*  660 */       DefaultListModel model = (DefaultListModel)this.jListSelectedOper.getModel();
/*      */       
/*  662 */       int n = model.getSize();
/*      */       
/*  664 */       int alg = this.jComboUNC.getSelectedIndex();
/*  665 */       Hashtable algTable = new Hashtable();
/*  666 */       algTable.put("name", "ecsago");
/*  667 */       switch (alg) {
/*      */         case 0:
/*  669 */           algTable.put("name", "unc");
/*  670 */           muttable = new Hashtable();
/*  671 */           muttable.put("name", this.jMutationList.getSelectedValue());
/*  672 */           algTable.put("mutation", muttable);
/*  673 */           algTable.put("mutation_probability", this.jMutationProbText.getText());
/*      */           
/*  675 */           xovtable = new Hashtable();
/*  676 */           xovtable.put("name", this.jXOverList.getSelectedValue());
/*  677 */           algTable.put("xover", xovtable);
/*  678 */           algTable.put("xover_probability", this.jXOverProbText.getText());
/*      */           
/*  680 */           algTable.put("sigma", this.KTextField.getText());
/*  681 */           switch (this.encoding) {
/*      */             case 1:
/*  683 */               table.put("encoding", "binary");
/*      */               break;
/*      */             case 2:
/*  686 */               table.put("encoding", "gray");
/*      */               break;
/*      */           } 
/*      */           break;
/*      */         case 1:
/*  691 */           algTable.put("name", "haea");
/*      */         case 2:
/*  693 */           switch (this.encoding) {
/*      */             case 0:
/*  695 */               for (i = 0; i < n; i++) {
/*  696 */                 Hashtable opertable = new Hashtable();
/*  697 */                 Object obj = model.get(i);
/*  698 */                 int index = getIndex(this.choicesRealOperators, obj);
/*  699 */                 switch (index) {
/*      */                   case 0:
/*  701 */                     opertable.put("name", "LinearXOver");
/*      */                     break;
/*      */                   case 1:
/*  704 */                     opertable.put("name", "LinearXOverPerDimension");
/*      */                     break;
/*      */                   
/*      */                   case 2:
/*  708 */                     opertable.put("name", "GaussianMutation");
/*      */                     
/*  710 */                     opertable.put("sigma", this.gmp.jTextField1.getText());
/*      */                     break;
/*      */                   
/*      */                   case 3:
/*  714 */                     opertable.put("name", "HybridGaussianMutation");
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  720 */                 algTable.put("operator_" + i, opertable);
/*      */               } 
/*      */               break;
/*      */             case 1:
/*  724 */               table.put("encoding", "binary");
/*  725 */               for (i = 0; i < n; i++) {
/*  726 */                 Hashtable opertable = new Hashtable();
/*  727 */                 Object obj = model.get(i);
/*  728 */                 int index = getIndex(this.choicesBinaryOperators, obj);
/*  729 */                 switch (index) {
/*      */                   case 0:
/*  731 */                     opertable.put("name", "BitMutation");
/*      */                     break;
/*      */                   case 1:
/*  734 */                     opertable.put("name", "BitXOver");
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */                 
/*  739 */                 algTable.put("operator_" + i, opertable);
/*      */               } 
/*      */               break;
/*      */             case 2:
/*  743 */               table.put("encoding", "gray");
/*  744 */               for (i = 0; i < n; i++) {
/*  745 */                 Hashtable opertable = new Hashtable();
/*  746 */                 Object obj = model.get(i);
/*  747 */                 int index = getIndex(this.choicesBinaryOperators, obj);
/*  748 */                 switch (index) {
/*      */                   case 0:
/*  750 */                     opertable.put("name", "BitMutation");
/*      */                     break;
/*      */                   case 1:
/*  753 */                     opertable.put("name", "BitXOver");
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */                 
/*  758 */                 algTable.put("operator_" + i, opertable);
/*      */               } 
/*      */               break;
/*      */           } 
/*      */           
/*      */           break;
/*      */       } 
/*  765 */       table.put("transformation", algTable);
/*      */       
/*  767 */       table.put("metric", this.jComboBoxDistance.getSelectedItem());
/*      */ 
/*      */       
/*  770 */       Hashtable weightTable = new Hashtable();
/*  771 */       if (this.jCheckBoxThold.isSelected()) {
/*  772 */         weightTable.put("name", "Membership");
/*  773 */         weightTable.put("binarized", "true");
/*  774 */         weightTable.put("threshold", this.thresholdTextField.getText());
/*      */       } 
/*      */       
/*  777 */       if (this.jCheckBoxCardin.isSelected()) {
/*  778 */         weightTable.put("name", "Cardinality");
/*  779 */         weightTable.put("cardinality", this.cardinalityTextField.getText());
/*      */       } 
/*      */       
/*  782 */       table.put("weight", weightTable);
/*      */       
/*  784 */       Hashtable fitnessTable = new Hashtable();
/*  785 */       fitnessTable.put("name", "ClusterExp");
/*  786 */       table.put("fitness", fitnessTable);
/*      */       
/*  788 */       table.put("tunning", "Sigma");
/*      */       
/*  790 */       Hashtable defaultSetTable = new Hashtable();
/*  791 */       defaultSetTable.put("min_sigma", "0.0005");
/*  792 */       defaultSetTable.put("max_sigma", "0.05");
/*  793 */       defaultSetTable.put("initial_sigma", "0.0005");
/*  794 */       table.put("default_set", defaultSetTable);
/*      */       
/*  796 */       Hashtable firstExtractionTable = new Hashtable();
/*  797 */       firstExtractionTable.put("name", "fitness");
/*  798 */       firstExtractionTable.put("type", this.jComboBoxExt.getSelectedItem());
/*      */       
/*  800 */       double perc = Double.parseDouble(this.extTextField.getText()) / 100.0D;
/*  801 */       firstExtractionTable.put("threshold", "" + perc);
/*      */       
/*  803 */       Hashtable secondExtractionTable = new Hashtable();
/*  804 */       secondExtractionTable.put("name", "niche");
/*  805 */       secondExtractionTable.put("K", this.KTextField.getText());
/*      */ 
/*      */       
/*  808 */       Hashtable extractionTable = new Hashtable();
/*  809 */       extractionTable.put("name", "compose");
/*  810 */       extractionTable.put("first", firstExtractionTable);
/*  811 */       extractionTable.put("second", secondExtractionTable);
/*      */       
/*  813 */       table.put("extraction", extractionTable);
/*      */       
/*  815 */       if (this.MDERadioButton.isSelected()) {
/*  816 */         Hashtable refTable = new Hashtable();
/*  817 */         refTable.put("name", "MDE");
/*  818 */         refTable.put("K", this.KTextField.getText());
/*      */         
/*  820 */         refTable.put("iterations", this.refIterTextField.getText());
/*  821 */         table.put("refinement", refTable);
/*      */       } 
/*      */       
/*  824 */       if (this.incrementalCheckBox.isSelected()) {
/*  825 */         Hashtable incTable = new Hashtable();
/*  826 */         incTable.put("groups", this.groupsTextField.getText());
/*  827 */         if (this.randomCheckBox.isSelected()) {
/*  828 */           incTable.put("randomized", "true");
/*      */         } else {
/*  830 */           incTable.put("randomized", "false");
/*      */         } 
/*  832 */         incTable.put("memory_factor", this.memoryTextField.getText());
/*      */         
/*  834 */         table.put("incremental", incTable);
/*      */       } 
/*      */     } 
/*      */     
/*  838 */     return table;
/*      */   }
/*      */   
/*      */   public Hashtable getTracer() {
/*  842 */     Hashtable table = null;
/*  843 */     if (checkInputs()) {
/*  844 */       StringBuffer sb = new StringBuffer();
/*  845 */       table = new Hashtable();
/*  846 */       int gen = Integer.parseInt(this.genTextField.getText());
/*  847 */       int[] evo_iter = new int[gen + 1];
/*  848 */       sb.append("0");
/*  849 */       for (int i = 1; i <= gen; i++) {
/*  850 */         sb.append(";" + i);
/*      */       }
/*  852 */       table.put("evolution_trace", sb.toString());
/*      */       
/*  854 */       sb = new StringBuffer();
/*  855 */       int ref = Integer.parseInt(this.refIterTextField.getText());
/*  856 */       sb.append("1");
/*  857 */       for (int j = 1; j < ref; j++) {
/*  858 */         sb.append(";" + (j + 1));
/*      */       }
/*  860 */       table.put("refinement_trace", sb.toString());
/*      */       
/*  862 */       sb = new StringBuffer();
/*  863 */       sb.append("0");
/*  864 */       if (this.incrementalCheckBox.isSelected()) {
/*  865 */         int gr = Integer.parseInt(this.groupsTextField.getText());
/*  866 */         for (int k = 1; k < gr; k++) {
/*  867 */           sb.append(";" + k);
/*      */         }
/*      */       } 
/*  870 */       table.put("incremental_trace", sb.toString());
/*      */     } 
/*  872 */     return table;
/*      */   }
/*      */ 
/*      */   
/*      */   public void jButton4_actionPerformed(ActionEvent e) {
/*  877 */     Hashtable table = getUNCTest();
/*  878 */     if (table != null) {
/*  879 */       UNCParameterizedTest test = new UNCParameterizedTest(table);
/*  880 */       DataSource data = (test.getData()).data;
/*  881 */       this.drawPanel.setData(data);
/*  882 */       int gen = Integer.parseInt(this.genTextField.getText());
/*  883 */       int[] evo_iter = new int[gen + 1];
/*  884 */       for (int i = 0; i <= gen; i++) {
/*  885 */         evo_iter[i] = i;
/*      */       }
/*      */       
/*  888 */       int ref = Integer.parseInt(this.refIterTextField.getText());
/*  889 */       int[] ref_iter = new int[ref];
/*  890 */       for (int j = 0; j < ref; j++) {
/*  891 */         ref_iter[j] = j + 1;
/*      */       }
/*      */       
/*  894 */       this.jSlider1.setMinimum(0);
/*      */       
/*  896 */       int[] groups = null;
/*  897 */       if (this.incrementalCheckBox.isSelected()) {
/*  898 */         this.jSlider1.setMaximum(gen + 1);
/*  899 */         int gr = Integer.parseInt(this.groupsTextField.getText());
/*  900 */         this.groupSlider.setMinimum(0);
/*  901 */         this.groupSlider.setMaximum(gr - 1);
/*  902 */         groups = new int[gr];
/*  903 */         for (int k = 0; k < gr; k++) {
/*  904 */           groups[k] = k;
/*      */         }
/*      */       } else {
/*  907 */         this.jSlider1.setMaximum(gen + ref + 1);
/*  908 */         groups = new int[] { 0 };
/*      */       } 
/*  910 */       this.tracer = new GUITracer(this, evo_iter, ref_iter, groups);
/*  911 */       test.addTracer((Tracer)this.tracer);
/*      */ 
/*      */       
/*  914 */       test.start();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void checkParameters() {
/*  919 */     int indexData = this.jComboData.getSelectedIndex();
/*  920 */     if (indexData > 8) {
/*  921 */       this.extTextField.setText("10");
/*  922 */       this.KTextField.setText("10.3");
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean checkInputs() {
/*  927 */     String msg = "";
/*  928 */     if ((this.appletMode && this.jComboData.getSelectedIndex() < 0) || (!this.appletMode && this.fileName == null)) {
/*      */       
/*  930 */       msg = "A data set should be choosed";
/*  931 */       showMessage(msg);
/*  932 */       return false;
/*      */     } 
/*      */     
/*  935 */     if (this.jComboUNC.getSelectedIndex() == 2 && this.jListSelectedOper.getModel().getSize() <= 0) {
/*  936 */       msg = "Genetic operators should be selected";
/*  937 */       showMessage(msg);
/*  938 */       return false;
/*      */     } 
/*  940 */     if (this.jComboBoxDistance.getSelectedIndex() != 0) {
/*  941 */       msg = "The distance is not implemented yet";
/*  942 */       showMessage(msg);
/*  943 */       return false;
/*      */     } 
/*      */     
/*      */     try {
/*  947 */       Integer.parseInt(this.popTextField.getText());
/*  948 */     } catch (NumberFormatException ex) {
/*  949 */       msg = ex.getMessage() + ", at population size field";
/*  950 */       showMessage(msg);
/*  951 */       return false;
/*      */     } 
/*      */     try {
/*  954 */       Integer.parseInt(this.genTextField.getText());
/*  955 */     } catch (NumberFormatException ex) {
/*  956 */       msg = ex.getMessage() + ", at generations field";
/*  957 */       showMessage(msg);
/*  958 */       return false;
/*      */     } 
/*      */     try {
/*  961 */       double w = Double.parseDouble(this.thresholdTextField.getText());
/*  962 */       if (w < 0.0D || w > 1.0D) {
/*  963 */         msg = "Weight threshold should be between 0 and 1";
/*  964 */         showMessage(msg);
/*  965 */         return false;
/*      */       } 
/*  967 */     } catch (NumberFormatException ex) {
/*  968 */       msg = ex.getMessage() + ", at weight threshold field";
/*  969 */       showMessage(msg);
/*  970 */       return false;
/*      */     } 
/*      */     try {
/*  973 */       Integer.parseInt(this.cardinalityTextField.getText());
/*  974 */     } catch (NumberFormatException ex) {
/*  975 */       msg = ex.getMessage() + ", at weight cardinality field";
/*  976 */       showMessage(msg);
/*  977 */       return false;
/*      */     } 
/*      */     try {
/*  980 */       Integer.parseInt(this.refIterTextField.getText());
/*  981 */     } catch (NumberFormatException ex) {
/*  982 */       msg = ex.getMessage() + ", at refinement iterations field";
/*  983 */       showMessage(msg);
/*  984 */       return false;
/*      */     } 
/*      */     try {
/*  987 */       Integer.parseInt(this.extTextField.getText());
/*  988 */     } catch (NumberFormatException ex) {
/*  989 */       msg = ex.getMessage() + ", at extraction threshold field";
/*  990 */       showMessage(msg);
/*  991 */       return false;
/*      */     } 
/*      */     try {
/*  994 */       Double.parseDouble(this.KTextField.getText());
/*  995 */     } catch (NumberFormatException ex) {
/*  996 */       msg = ex.getMessage() + ", at K field";
/*  997 */       showMessage(msg);
/*  998 */       return false;
/*      */     } 
/*      */     
/*      */     try {
/* 1002 */       Integer.parseInt(this.groupsTextField.getText());
/* 1003 */     } catch (NumberFormatException ex) {
/* 1004 */       msg = ex.getMessage() + ", at number of groups for incremental algorithm field";
/* 1005 */       showMessage(msg);
/* 1006 */       return false;
/*      */     } 
/*      */     try {
/* 1009 */       double m = Double.parseDouble(this.memoryTextField.getText());
/* 1010 */       if (m < 0.0D || m > 1.0D) {
/* 1011 */         msg = "Memorization factor should be between 0 and 1";
/* 1012 */         showMessage(msg);
/* 1013 */         return false;
/*      */       } 
/* 1015 */     } catch (NumberFormatException ex) {
/* 1016 */       msg = ex.getMessage() + ", at memorization factor field";
/* 1017 */       showMessage(msg);
/* 1018 */       return false;
/*      */     } 
/* 1020 */     return true;
/*      */   }
/*      */   
/*      */   public void showMessage(String msg) {
/* 1024 */     JOptionPane.showMessageDialog(this, "Error: " + msg, "Error", 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getSqrtK() {
/* 1029 */     double K = Double.parseDouble(this.KTextField.getText());
/* 1030 */     return Math.sqrt(K);
/*      */   }
/*      */ 
/*      */   
/*      */   public void jRadioButton_stateChanged(ChangeEvent e) {
/* 1035 */     int new_encoding = this.encoding;
/* 1036 */     if (this.jRadioButton1.isSelected()) new_encoding = 0; 
/* 1037 */     if (this.jRadioButton2.isSelected()) new_encoding = 1; 
/* 1038 */     if (this.jRadioButton3.isSelected()) new_encoding = 2; 
/* 1039 */     if (this.encoding != new_encoding) {
/* 1040 */       this.gmp.setVisible(false);
/* 1041 */       this.encoding = new_encoding;
/* 1042 */       switch (this.encoding) {
/*      */         case 0:
/* 1044 */           this.jListOperators.setModel(getDefaultModel(this.choicesRealOperators));
/* 1045 */           this.jListSelectedOper.setModel(getDefaultModel());
/*      */           
/* 1047 */           this.jMutationList.setModel(getDefaultModel(this.realMutations));
/* 1048 */           this.jXOverList.setModel(getDefaultModel(this.realXOvers));
/* 1049 */           this.jMutationList.setSelectedIndex(0);
/* 1050 */           this.jXOverList.setSelectedIndex(0);
/*      */           break;
/*      */         case 1:
/* 1053 */           this.jListOperators.setModel(getDefaultModel(this.choicesBinaryOperators));
/* 1054 */           this.jListSelectedOper.setModel(getDefaultModel());
/*      */           
/* 1056 */           this.jMutationList.setModel(getDefaultModel(this.binaryMutations));
/* 1057 */           this.jXOverList.setModel(getDefaultModel(this.binaryXOvers));
/* 1058 */           this.jMutationList.setSelectedIndex(0);
/* 1059 */           this.jXOverList.setSelectedIndex(0);
/*      */           break;
/*      */         case 2:
/* 1062 */           this.jListOperators.setModel(getDefaultModel(this.choicesBinaryOperators));
/* 1063 */           this.jListSelectedOper.setModel(getDefaultModel());
/*      */           
/* 1065 */           this.jMutationList.setModel(getDefaultModel(this.binaryMutations));
/* 1066 */           this.jXOverList.setModel(getDefaultModel(this.binaryXOvers));
/* 1067 */           this.jMutationList.setSelectedIndex(0);
/* 1068 */           this.jXOverList.setSelectedIndex(0);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void jSlider1_stateChanged(ChangeEvent e) {
/* 1075 */     if (this.tracer != null) {
/* 1076 */       this.tracer.setProgress(this.jSlider1.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */   public void jButton5_actionPerformed(ActionEvent e) {
/* 1081 */     JFileChooser chooser = new JFileChooser();
/* 1082 */     if (chooser.showOpenDialog(this) == 0) {
/* 1083 */       File file = chooser.getSelectedFile();
/* 1084 */       if (file != null) {
/* 1085 */         this.fileName = file.getAbsolutePath();
/* 1086 */         DataSource source = (new FileDataSource(this.fileName, -1)).optimize();
/* 1087 */         this.drawPanel.setData(source);
/* 1088 */         this.drawPanel.setPrototype((Prototype)null, false, getSqrtK());
/* 1089 */         this.tracer = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void jComboData_itemStateChanged(ItemEvent e) {
/* 1095 */     this.tracer = null;
/* 1096 */     if (this.appletMode) {
/* 1097 */       String dataset = (String)this.jComboData.getSelectedItem();
/* 1098 */       InputStream is = UNCMainPanel.class.getResourceAsStream("/datasets/Synthetic2/" + dataset);
/*      */       
/* 1100 */       checkParameters();
/* 1101 */       DataSource source = (new FileDataSource(is, -1)).optimize();
/* 1102 */       this.drawPanel.setData(source);
/* 1103 */       this.drawPanel.setPrototype((Prototype)null, false, getSqrtK());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void incrementalCheckBox_stateChanged(ChangeEvent e) {
/* 1108 */     this.groupLabel.setVisible(this.incrementalCheckBox.isSelected());
/* 1109 */     this.groupSlider.setVisible(this.incrementalCheckBox.isSelected());
/*      */   }
/*      */   
/*      */   public void groupSlider_stateChanged(ChangeEvent e) {
/* 1113 */     if (this.tracer != null) {
/* 1114 */       this.tracer.setGroup(this.groupSlider.getValue());
/* 1115 */       this.jSlider1.setValue(1);
/* 1116 */       this.jSlider1.setValue(0);
/* 1117 */       this.groupLabel.setText("Batch [" + this.groupSlider.getValue() + "]");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void jComboUNC_itemStateChanged(ItemEvent e) {
/* 1122 */     switch (this.jComboUNC.getSelectedIndex()) {
/*      */       case 0:
/* 1124 */         this.ECSAGOOperators.setVisible(false);
/* 1125 */         this.UNCOperators.setVisible(true);
/*      */         
/* 1127 */         this.Operators.add(this.UNCOperators, "Center");
/*      */         break;
/*      */       case 1:
/* 1130 */         this.ECSAGOOperators.setVisible(true);
/* 1131 */         this.UNCOperators.setVisible(false);
/* 1132 */         this.Operators.add(this.ECSAGOOperators, "Center");
/*      */         break;
/*      */       
/*      */       case 2:
/* 1136 */         this.ECSAGOOperators.setVisible(true);
/* 1137 */         this.UNCOperators.setVisible(false);
/* 1138 */         this.Operators.add(this.ECSAGOOperators, "Center");
/*      */         break;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\gui\UNCMainPanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.data.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.FullComponents;
/*     */ import jml.data.Attribute;
/*     */ import jml.data.CategoricAttribute;
/*     */ import jml.data.Data;
/*     */ import jml.data.DataSourceInfo;
/*     */ import jml.data.NumericAttribute;
/*     */ import jml.parser.SimpleStreamTokenizer;
/*     */ import jml.parser.Token;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataFileConfig
/*     */ {
/*  62 */   public static String UNKNOWN = "?";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   protected int m = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   protected int n = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public int class_feature = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public boolean[] goodFeature = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public Vector features = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataFileConfig(Vector _features, int size, int _class_feature) {
/*  98 */     this(_features, size, _class_feature, (int[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataFileConfig(Vector _features, int size, int _class_feature, int[] dummyFeatures) {
/* 111 */     this.features = _features;
/* 112 */     this.m = size;
/* 113 */     this.class_feature = _class_feature;
/* 114 */     setDummyFeatures(dummyFeatures);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataFileConfig(int size, int _n, int c, int _class_feature) {
/* 126 */     init(size, _n, c, _class_feature);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(int size, int _n, int c, int _class_feature) {
/* 138 */     this.features.clear();
/* 139 */     this.m = size;
/* 140 */     this.n = _n;
/* 141 */     for (int i = 0; i < this.n; ) { this.features.add(new NumericAttribute("x-" + i)); i++; }
/* 142 */      Vector cat = new Vector(); int j;
/* 143 */     for (j = 0; j < c; ) { cat.add("c-" + j); j++; }
/* 144 */      this.class_feature = _class_feature;
/* 145 */     if (0 <= this.class_feature && this.class_feature < this.n) {
/* 146 */       this.features.set(this.class_feature, new CategoricAttribute("class", cat));
/* 147 */       this.n--;
/*     */     } 
/* 149 */     this.goodFeature = new boolean[this.features.size()];
/* 150 */     for (j = 0; j < this.goodFeature.length; ) { this.goodFeature[j] = true; j++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dimension() {
/* 158 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 165 */     return this.m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean withClassAttribute() {
/* 172 */     return (this.class_feature >= 0 && this.class_feature < this.features.size() && this.features.get(this.class_feature) instanceof CategoricAttribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int numberOfClasses() {
/* 181 */     int c = 0;
/* 182 */     if (withClassAttribute()) {
/* 183 */       c = ((CategoricAttribute)this.features.get(this.class_feature)).size();
/*     */     }
/* 185 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDummyFeatures(int[] dummyFeature) {
/* 193 */     this.goodFeature = new boolean[this.features.size()]; int i;
/* 194 */     for (i = 0; i < this.goodFeature.length; ) { this.goodFeature[i] = true; i++; }
/* 195 */      if (dummyFeature != null) {
/* 196 */       for (i = 0; i < dummyFeature.length; i++) {
/* 197 */         this.goodFeature[dummyFeature[i]] = false;
/*     */       }
/*     */     }
/* 200 */     this.n = 0;
/* 201 */     for (i = 0; i < this.goodFeature.length; i++) {
/* 202 */       if (this.goodFeature[i] && i != this.class_feature) this.n++;
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSourceInfo getInfo() {
/* 211 */     Vector f = new Vector();
/* 212 */     CategoricAttribute class_attr = null;
/* 213 */     for (int i = 0; i < this.features.size(); i++) {
/* 214 */       if (i == this.class_feature)
/* 215 */       { class_attr = this.features.get(i); }
/*     */       
/* 217 */       else if (this.goodFeature[i]) { f.add(this.features.get(i)); }
/*     */     
/*     */     } 
/* 220 */     return new DataSourceInfo(size(), f, class_attr);
/*     */   }
/*     */   
/*     */   public Data readDataSample(String line) {
/* 224 */     StringReader sr = new StringReader(line);
/* 225 */     SimpleStreamTokenizer tok = new SimpleStreamTokenizer(sr);
/* 226 */     double[] rec = new double[this.n];
/* 227 */     int k = this.features.size();
/* 228 */     int j = 0;
/* 229 */     int label = 0;
/*     */     
/* 231 */     for (int i = 0; i < k; i++) {
/* 232 */       Token token = tok.nextToken();
/* 233 */       if (i == this.class_feature) {
/* 234 */         label = (int)token.nval;
/*     */       }
/* 236 */       else if (this.goodFeature[i]) {
/* 237 */         rec[j] = token.nval;
/* 238 */         j++;
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     return new Data(new FullComponents(rec), label);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataFileConfig getMinimumFileConfig(String inputFile) {
/* 258 */     int m = 0;
/* 259 */     int n = 0;
/* 260 */     Vector attribute = new Vector();
/*     */     try {
/* 262 */       FileReader reader = new FileReader(inputFile);
/* 263 */       BufferedReader is = new BufferedReader(reader);
/* 264 */       String line = is.readLine();
/* 265 */       StringReader sr = new StringReader(line);
/* 266 */       SimpleStreamTokenizer tok = new SimpleStreamTokenizer(sr);
/* 267 */       Vector args = tok.getTokens();
/* 268 */       n = args.size();
/* 269 */       for (int i = 0; i < n; ) { attribute.add(new NumericAttribute("x-" + i)); i++; }
/* 270 */        while (line != null) {
/* 271 */         m++;
/* 272 */         line = is.readLine();
/*     */       } 
/* 274 */       reader.close();
/* 275 */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/*     */     } 
/* 278 */     return new DataFileConfig(attribute, m, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataFileConfig getBasicFileConfig(String inputFile) {
/* 297 */     int m = 0;
/* 298 */     int n = 0;
/* 299 */     Vector attribute = new Vector();
/*     */     try {
/* 301 */       FileReader reader = new FileReader(inputFile);
/* 302 */       BufferedReader is = new BufferedReader(reader);
/* 303 */       String line = is.readLine();
/* 304 */       StringReader sr = new StringReader(line);
/* 305 */       SimpleStreamTokenizer tok = new SimpleStreamTokenizer(sr);
/* 306 */       Vector args = tok.getTokens();
/*     */       
/* 308 */       n = args.size(); int i;
/* 309 */       for (i = 0; i < n; ) { attribute.add(new NumericAttribute("x-" + i)); i++; }
/* 310 */        while (line != null) {
/* 311 */         for (i = 0; i < n; i++) {
/* 312 */           Token token = args.get(i);
/* 313 */           if (token.type == 2 && attribute.get(i) instanceof NumericAttribute && !token.sval.equals(UNKNOWN))
/*     */           {
/*     */             
/* 316 */             attribute.set(i, new CategoricAttribute("x-" + i, new Vector()));
/*     */           }
/*     */         } 
/* 319 */         m++;
/* 320 */         line = is.readLine();
/* 321 */         if (line != null) {
/* 322 */           sr = new StringReader(line);
/* 323 */           tok = new SimpleStreamTokenizer(sr);
/* 324 */           args = tok.getTokens();
/*     */         } 
/*     */       } 
/* 327 */       reader.close();
/* 328 */     } catch (Exception e) {
/* 329 */       e.printStackTrace();
/*     */     } 
/* 331 */     return new DataFileConfig(attribute, m, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataFileConfig formatFile(String inputFile, String outputFile) {
/* 351 */     DataFileConfig config = getBasicFileConfig(inputFile);
/* 352 */     int n = config.dimension();
/* 353 */     Vector attribute = config.features;
/*     */     try {
/* 355 */       FileWriter writer = new FileWriter(outputFile);
/* 356 */       FileReader reader = new FileReader(inputFile);
/* 357 */       BufferedReader is = new BufferedReader(reader);
/* 358 */       String line = is.readLine();
/* 359 */       while (line != null) {
/* 360 */         StringReader sr = new StringReader(line);
/* 361 */         SimpleStreamTokenizer tok = new SimpleStreamTokenizer(sr);
/* 362 */         Vector args = tok.getTokens();
/*     */ 
/*     */         
/* 365 */         for (int i = 0; i < n; i++) {
/* 366 */           double val; Token token = args.get(i);
/* 367 */           if (token.type == 2) {
/* 368 */             if (token.sval.equals(UNKNOWN)) {
/* 369 */               val = -1.0E108D;
/*     */             } else {
/* 371 */               val = ((CategoricAttribute)attribute.get(i)).addCategory(token.sval);
/*     */             }
/*     */           
/* 374 */           } else if (attribute.get(i) instanceof NumericAttribute) {
/* 375 */             val = token.nval;
/*     */           } else {
/* 377 */             val = ((CategoricAttribute)attribute.get(i)).addCategory("" + token.nval);
/*     */           } 
/*     */           
/* 380 */           writer.write(" " + val);
/*     */         } 
/* 382 */         writer.write("\n");
/* 383 */         line = is.readLine();
/*     */       } 
/* 385 */       reader.close();
/* 386 */       writer.close();
/* 387 */     } catch (Exception e) {
/* 388 */       e.printStackTrace();
/*     */     } 
/* 390 */     return config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/* 399 */     StringBuffer sb = new StringBuffer();
/* 400 */     sb.append("<DataFileConfig");
/* 401 */     sb.append(" size=\"" + this.m + "\"");
/* 402 */     sb.append(" dimension=\"" + dimension() + "\"");
/* 403 */     sb.append(" classes=\"" + numberOfClasses() + "\"");
/* 404 */     sb.append(" classId=\"" + this.class_feature + "\"");
/* 405 */     sb.append(">\n");
/* 406 */     if (this.features.size() > 0) {
/* 407 */       Enumeration iter = this.features.elements();
/* 408 */       sb.append(" <Features>\n");
/* 409 */       while (iter.hasMoreElements()) {
/* 410 */         Attribute attr = iter.nextElement();
/* 411 */         sb.append(attr.toXML());
/*     */       } 
/* 413 */       sb.append(" </Features>\n");
/*     */     } 
/* 415 */     sb.append("</DataFileConfig>");
/* 416 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save(String fileName) {
/*     */     
/* 425 */     try { FileWriter output = new FileWriter(fileName);
/* 426 */       output.write(toXML());
/* 427 */       output.close(); }
/* 428 */     catch (Exception e) { e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataFileConfig(InputStream is) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataFileConfig read(String fileName) {
/* 466 */     DataFileConfig config = null;
/*     */     
/* 468 */     try { FileInputStream is = new FileInputStream(fileName);
/* 469 */       config = new DataFileConfig(is);
/* 470 */       is.close(); }
/* 471 */     catch (Exception e) { e.printStackTrace(); }
/* 472 */      return config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class XMLReaderHandler
/*     */     extends DefaultHandler
/*     */   {
/*     */     DataFileConfig config;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String text;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String errorMessage;
/*     */ 
/*     */ 
/*     */     
/*     */     int attr_index;
/*     */ 
/*     */ 
/*     */     
/*     */     int val_index;
/*     */ 
/*     */ 
/*     */     
/*     */     String[] labels;
/*     */ 
/*     */ 
/*     */     
/*     */     private final DataFileConfig this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public XMLReaderHandler(DataFileConfig this$0, DataFileConfig _config) {
/* 514 */       this.this$0 = this$0; this.config = null; this.text = null; this.errorMessage = null; this.attr_index = 0; this.val_index = 0; this.labels = new String[] { "DataFileConfig", "Features", "Categorical", "Numerical", "Value" };
/* 515 */       this.config = _config;
/* 516 */       this.errorMessage = null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLabel(String label) {
/* 537 */       int i = 0;
/* 538 */       for (; i < this.labels.length && !this.labels[i].equals(label); i++);
/* 539 */       if (i == this.labels.length) i = -1; 
/* 540 */       return i;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void datafileconfig(Attributes attributes) {
/*     */       try {
/* 549 */         this.config.init(Integer.parseInt(attributes.getValue("size")), 1 + Integer.parseInt(attributes.getValue("dimension")), Integer.parseInt(attributes.getValue("classes")), Integer.parseInt(attributes.getValue("classId")));
/*     */ 
/*     */       
/*     */       }
/* 553 */       catch (NumberFormatException e) {
/* 554 */         if (this.errorMessage == null) {
/* 555 */           this.errorMessage = "Invalid number format in numerical attribute DataFileConfig TAG";
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void numerical(Attributes attributes) {
/* 566 */       this.config.features.set(this.attr_index, new NumericAttribute(attributes));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void categorical(Attributes attributes) {
/* 574 */       this.config.features.set(this.attr_index, new CategoricAttribute(attributes));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 582 */       int option = getLabel(qName);
/* 583 */       switch (option) {
/*     */         case 0:
/* 585 */           datafileconfig(attributes);
/*     */         
/*     */         case 1:
/* 588 */           this.attr_index = 0;
/*     */         
/*     */         case 2:
/* 591 */           if (this.attr_index <= this.config.dimension()) { categorical(attributes); }
/* 592 */           else { this.errorMessage = "Extra attribute declared..."; }
/*     */         
/*     */         case 3:
/* 595 */           if (this.attr_index <= this.config.dimension()) { numerical(attributes); }
/* 596 */           else { this.errorMessage = "Extra attribute declared..."; }
/*     */         
/*     */         case 4:
/*     */           return;
/*     */       } 
/* 601 */       if (this.errorMessage == null) this.errorMessage = "Invalid element <" + qName + ">"; 
/* 602 */       throw new SAXException(this.errorMessage);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endElement(String uri, String localName, String name) throws SAXException {
/*     */       CategoricAttribute cat;
/* 610 */       int option = getLabel(name);
/* 611 */       switch (option) {
/*     */         case 0:
/*     */         case 1:
/*     */           return;
/*     */         
/*     */         case 2:
/*     */         case 3:
/* 618 */           this.attr_index++;
/*     */         
/*     */         case 4:
/* 621 */           cat = this.config.features.get(this.attr_index);
/* 622 */           cat.addCategory(this.text);
/*     */       } 
/*     */       
/* 625 */       if (this.errorMessage == null) this.errorMessage = "Invalid element <" + name + ">"; 
/* 626 */       throw new SAXException(this.errorMessage);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void processingInstruction(String target, String value) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void characters(char[] buffer, int offset, int length) throws SAXException {
/* 642 */       this.text = new String(buffer, offset, length);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void ignorableWhitespace(char[] buffer, int offset, int length) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void error(SAXParseException spe) throws SAXParseException {
/* 658 */       throw spe;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void warning(SAXParseException spe) throws SAXParseException {
/* 666 */       System.err.println("[Fuzzy XML Reader] Warning." + spe.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\DataFileConfig.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.fuzzy;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.algebra.RealVector;
/*     */ import jml.classification.Recognizer;
/*     */ import jml.fuzzy.parser.Grammar;
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
/*     */ public class Engine
/*     */   extends Recognizer
/*     */ {
/*  44 */   public Vector rules = null;
/*     */ 
/*     */ 
/*     */   
/*  48 */   public Model system = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected Space space = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected Logic logic = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Engine() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Engine(Model fs, Vector rs) {
/*  73 */     this.rules = rs;
/*  74 */     this.system = fs;
/*  75 */     this.space = this.system.getSpace(this.system.getNumberOfSpaces() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Engine(Vector rs, Model fs) {
/*  84 */     this.rules = rs;
/*  85 */     this.system = fs;
/*  86 */     this.space = this.system.getSpace(this.system.getNumberOfSpaces() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Engine(String system_file, String rules_file) {
/*  95 */     String text = "";
/*     */ 
/*     */     
/*     */     try {
/*  99 */       FileReader file = new FileReader(system_file);
/* 100 */       int c = file.read();
/* 101 */       while (c != -1) {
/* 102 */         text = text + "" + (char)c;
/* 103 */         c = file.read();
/*     */       } 
/* 105 */       file.close();
/* 106 */       file = new FileReader(rules_file);
/* 107 */       c = file.read();
/* 108 */       while (c != -1) {
/* 109 */         text = text + "" + (char)c;
/* 110 */         c = file.read();
/*     */       } 
/* 112 */       file.close();
/* 113 */       Grammar g = new Grammar(text);
/* 114 */       Engine engine = g.createEngine();
/* 115 */       if (g.error == null)
/* 116 */       { this.system = engine.system;
/* 117 */         this.rules = engine.rules;
/* 118 */         this.space = this.system.getSpace(this.system.getNumberOfSpaces() - 1); }
/* 119 */       else { System.err.print(g.error.toString()); } 
/* 120 */     } catch (Exception ex) {
/* 121 */       if (ex instanceof java.io.FileNotFoundException) {
/* 122 */         System.err.print(ex.getMessage());
/*     */       } else {
/* 124 */         System.err.print(ex.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogic(Logic _logic) {
/* 134 */     this.logic = _logic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int classesNumber() {
/* 142 */     int n = 0;
/* 143 */     if (this.space != null) n = this.space.getNumberOfSets(); 
/* 144 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] confidence(Object data) {
/* 154 */     RealVector rv = (RealVector)data;
/* 155 */     int n = classesNumber();
/* 156 */     double[] memberships = new double[n]; int i;
/* 157 */     for (i = 0; i < n; ) { memberships[i] = 0.0D; i++; }
/*     */     
/* 159 */     for (i = 0; i < this.rules.size(); i++) {
/* 160 */       Rule r = this.rules.elementAt(i);
/* 161 */       int j = this.space.getSetPosition(r.conclusion.set);
/* 162 */       memberships[j] = r.evaluate(rv, this.logic);
/*     */     } 
/*     */     
/* 165 */     return memberships;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 173 */     String result = this.system.toString();
/* 174 */     Enumeration xenum = this.rules.elements();
/* 175 */     while (xenum.hasMoreElements()) {
/* 176 */       Rule r = xenum.nextElement();
/* 177 */       result = result + r.toString() + "\n";
/*     */     } 
/* 179 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String rulesToString() {
/* 187 */     String text = "";
/* 188 */     Enumeration xenum = this.rules.elements();
/* 189 */     while (xenum.hasMoreElements()) {
/* 190 */       Rule r = xenum.nextElement();
/* 191 */       text = text + r.toString() + "\n";
/*     */     } 
/* 193 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String rulesToXML() {
/* 201 */     String text = "<knowledge>\n";
/* 202 */     Enumeration xenum = this.rules.elements();
/* 203 */     while (xenum.hasMoreElements()) {
/* 204 */       Rule r = xenum.nextElement();
/* 205 */       text = text + r.toXML();
/*     */     } 
/* 207 */     text = text + "</knowledge>\n";
/* 208 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/* 216 */     String text = "<engine>\n";
/* 217 */     text = text + this.system.toXML();
/* 218 */     text = text + rulesToXML();
/* 219 */     text = text + "</engine>\n";
/* 220 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRulesConfidence(double[] v) {
/* 228 */     if (v != null && this.rules != null && v.length == this.rules.size())
/* 229 */     { int i = 0;
/* 230 */       Enumeration iter = this.rules.elements();
/* 231 */       while (iter.hasMoreElements()) {
/* 232 */         Rule r = iter.nextElement();
/* 233 */         r.confidence = v[i];
/* 234 */         i++;
/*     */       }  }
/* 236 */     else { System.err.println("Error trying to set the confidence values to the fuzzy rules"); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Engine.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
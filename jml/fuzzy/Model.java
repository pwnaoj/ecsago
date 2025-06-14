/*     */ package jml.fuzzy;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public class Model
/*     */ {
/*  41 */   protected Vector spaces = null;
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected Vector variables = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Model() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Model(Vector _spaces, Vector _vars) {
/*  59 */     this.spaces = _spaces;
/*  60 */     this.variables = _vars;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  67 */     this.spaces = null;
/*  68 */     this.variables = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initVariables() {
/*  75 */     this.variables = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Space getSpace(String space) {
/*  84 */     Space s = null;
/*  85 */     if (this.spaces != null) {
/*  86 */       Enumeration iter = this.spaces.elements();
/*  87 */       boolean flag = false;
/*  88 */       while (iter.hasMoreElements() && !flag) {
/*  89 */         s = iter.nextElement();
/*  90 */         flag = s.getName().equals(space);
/*     */       } 
/*  92 */       if (!flag) s = null; 
/*     */     } 
/*  94 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getVariable(String variable) {
/* 103 */     Variable v = null;
/* 104 */     if (this.variables != null) {
/* 105 */       Enumeration iter = this.variables.elements();
/* 106 */       boolean flag = false;
/* 107 */       while (iter.hasMoreElements() && !flag) {
/* 108 */         v = iter.nextElement();
/* 109 */         flag = v.getName().equals(variable);
/*     */       } 
/* 111 */       if (!flag) v = null; 
/*     */     } 
/* 113 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getVariable(int index) {
/* 122 */     Variable v = null;
/* 123 */     if (this.variables != null && index >= 0 && index < this.variables.size()) {
/* 124 */       v = this.variables.elementAt(index);
/*     */     }
/* 126 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Space getSpace(int index) {
/* 135 */     Space s = null;
/* 136 */     if (this.spaces != null && index >= 0 && index < this.spaces.size()) {
/* 137 */       s = this.spaces.elementAt(index);
/*     */     }
/* 139 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpacePosition(Space space) {
/* 148 */     int index = -1;
/* 149 */     if (this.spaces != null) {
/* 150 */       Enumeration iter = this.spaces.elements();
/* 151 */       index = 0;
/* 152 */       while (iter.hasMoreElements() && (Space)iter.nextElement() != space) {
/* 153 */         index++;
/*     */       }
/* 155 */       if (index == this.spaces.size()) index = -1; 
/*     */     } 
/* 157 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVariablePosition(Variable variable) {
/* 166 */     int index = -1;
/* 167 */     if (this.variables != null) {
/* 168 */       Enumeration iter = this.variables.elements();
/* 169 */       index = 0;
/* 170 */       while (iter.hasMoreElements() && (Variable)iter.nextElement() != variable) {
/* 171 */         index++;
/*     */       }
/* 173 */       if (index == this.variables.size()) index = -1; 
/*     */     } 
/* 175 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfVariables() {
/* 183 */     int n = 0;
/* 184 */     if (this.variables != null) {
/* 185 */       n = this.variables.size();
/*     */     }
/* 187 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSpaces() {
/* 195 */     int n = 0;
/* 196 */     if (this.spaces != null) {
/* 197 */       n = this.spaces.size();
/*     */     }
/* 199 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSpace(Space space) {
/* 207 */     if (this.spaces == null) this.spaces = new Vector(); 
/* 208 */     this.spaces.add(space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delSpace(Space space) {
/* 217 */     int index = getSpacePosition(space);
/* 218 */     if (index >= 0) {
/* 219 */       this.spaces.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delSpace(int index) {
/* 228 */     if (this.spaces != null && index >= 0 && index < this.spaces.size()) {
/* 229 */       this.spaces.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delVariable(Variable variable) {
/* 238 */     int index = getVariablePosition(variable);
/* 239 */     if (index >= 0) {
/* 240 */       this.variables.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delVariable(int index) {
/* 249 */     if (this.variables != null && index >= 0 && index < this.variables.size()) {
/* 250 */       this.variables.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVariable(Variable variable) {
/* 259 */     if (this.variables == null) this.variables = new Vector(); 
/* 260 */     this.variables.add(variable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVariablesPosition(Vector position) {
/* 268 */     if (this.variables != null && position != null && this.variables.size() == position.size()) {
/* 269 */       Enumeration iter = this.variables.elements();
/* 270 */       Enumeration iter2 = position.elements();
/* 271 */       while (iter.hasMoreElements()) {
/* 272 */         int pos = ((Integer)iter2.nextElement()).intValue();
/* 273 */         Variable v = iter.nextElement();
/* 274 */         v.position = pos;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getVariables() {
/* 284 */     return this.variables;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String spacesToXML() {
/* 291 */     String text = "";
/* 292 */     if (this.spaces != null) {
/* 293 */       text = "<spaces>\n";
/* 294 */       Enumeration iter = this.spaces.elements();
/* 295 */       while (iter.hasMoreElements()) {
/* 296 */         Space space = iter.nextElement();
/* 297 */         text = text + space.toXML();
/*     */       } 
/* 299 */       text = text + "</spaces>\n";
/*     */     } 
/* 301 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String variablesToXML() {
/* 309 */     String text = "";
/* 310 */     if (this.variables != null) {
/* 311 */       text = "<variables>\n";
/* 312 */       Enumeration iter = this.variables.elements();
/* 313 */       while (iter.hasMoreElements()) {
/* 314 */         Variable variable = iter.nextElement();
/* 315 */         text = text + variable.toXML();
/*     */       } 
/* 317 */       text = text + "</variables>\n";
/*     */     } 
/* 319 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/* 327 */     String text = "<model>\n";
/* 328 */     text = text + spacesToXML();
/* 329 */     text = text + variablesToXML();
/* 330 */     text = text + "</model>\n";
/* 331 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String spacesToString() {
/* 339 */     String text = "";
/* 340 */     if (this.spaces != null) {
/* 341 */       Enumeration iter = this.spaces.elements();
/* 342 */       while (iter.hasMoreElements()) {
/* 343 */         Space space = iter.nextElement();
/* 344 */         text = text + space.toString() + "\n";
/*     */       } 
/*     */     } 
/* 347 */     text = text + "";
/* 348 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String variablesToString() {
/* 356 */     String text = "";
/* 357 */     if (this.variables != null) {
/* 358 */       Enumeration iter = this.variables.elements();
/* 359 */       while (iter.hasMoreElements()) {
/* 360 */         Variable variable = iter.nextElement();
/* 361 */         text = text + variable.toString() + "\n";
/*     */       } 
/*     */     } 
/* 364 */     text = text + "";
/* 365 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 373 */     return spacesToString() + variablesToString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String read(String fileName) {
/* 382 */     String text = "";
/*     */     try {
/* 384 */       FileReader file = new FileReader(fileName);
/* 385 */       int c = file.read();
/* 386 */       while (c != -1) {
/* 387 */         text = text + "" + (char)c;
/* 388 */         c = file.read();
/*     */       } 
/* 390 */       file.close();
/*     */       
/* 392 */       Grammar g = new Grammar(text);
/* 393 */       Model system = g.createModelWithVariables();
/* 394 */       if (g.error == null) {
/* 395 */         this.spaces = system.spaces;
/* 396 */         this.variables = system.variables;
/* 397 */         text = null;
/*     */       } else {
/* 399 */         text = g.error.toString();
/*     */       } 
/* 401 */     } catch (Exception ex) {
/* 402 */       if (ex instanceof java.io.FileNotFoundException) {
/* 403 */         text = ex.getMessage();
/*     */       } else {
/* 405 */         text = ex.getMessage();
/*     */       } 
/*     */     } 
/* 408 */     return text;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Model.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.fuzzy.parser;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jml.fuzzy.Atomic;
/*     */ import jml.fuzzy.Engine;
/*     */ import jml.fuzzy.Expression;
/*     */ import jml.fuzzy.Model;
/*     */ import jml.fuzzy.Rule;
/*     */ import jml.fuzzy.Set;
/*     */ import jml.fuzzy.Space;
/*     */ import jml.fuzzy.Variable;
/*     */ import jml.fuzzy.sets.TrapezoidSet;
/*     */ import jml.fuzzy.sets.TriangularSet;
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
/*     */ public class Grammar
/*     */ {
/*     */   protected Tokenizer tokenizer;
/*  48 */   public ParserError error = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Grammar(String buffer) {
/*  56 */     this.tokenizer = new Tokenizer(buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getTokenType() {
/*  63 */     return this.tokenizer.getTokenType();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToken() {
/*  68 */     return this.tokenizer.getToken();
/*     */   }
/*     */   
/*     */   public void nextToken() {
/*  72 */     this.tokenizer.nextToken();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  77 */     return this.tokenizer.getInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/*  82 */     return this.tokenizer.getDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void makeError(String text) {
/*  90 */     if (this.tokenizer.getTokenType() == Tokenizer.ERROR) {
/*  91 */       this.error = this.tokenizer.error;
/*     */     }
/*  93 */     else if (getTokenType() != Tokenizer.EOF) {
/*  94 */       this.error = new ParserError(this.tokenizer.pos - this.tokenizer.token.length(), "Unexpected symbol " + getToken() + ". Expecting " + text);
/*     */     } else {
/*     */       
/*  97 */       this.error = new ParserError(this.tokenizer.pos, "Unexpected end of file.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void makeSemanticError(String text) {
/* 107 */     this.error = new ParserError(this.tokenizer.pos - this.tokenizer.token.length(), "Semantic error. " + text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String id() {
/* 117 */     String text = null;
/* 118 */     if (this.tokenizer.getTokenType() == Tokenizer.STRING || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */     
/* 120 */     { text = this.tokenizer.getToken();
/* 121 */       this.tokenizer.nextToken(); }
/* 122 */     else { makeError("variable name or id number"); }
/* 123 */      return text;
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
/*     */   public Atomic atomic(Model system) {
/* 135 */     Atomic atom = null;
/* 136 */     int tt = getTokenType();
/* 137 */     String variableId = id();
/* 138 */     if (this.error == null) {
/* 139 */       tt = getTokenType();
/* 140 */       if (tt == Tokenizer.IS)
/* 141 */       { nextToken();
/* 142 */         tt = getTokenType();
/* 143 */         boolean negation = (tt == Tokenizer.NOT);
/* 144 */         if (negation) nextToken(); 
/* 145 */         tt = getTokenType();
/* 146 */         if (tt == Tokenizer.STRING)
/* 147 */         { String fuzzySet = getToken();
/* 148 */           Variable v = system.getVariable(variableId);
/* 149 */           if (v != null)
/* 150 */           { Space space = v.getSpace();
/* 151 */             Set set = space.getSet(fuzzySet);
/* 152 */             if (set != null)
/* 153 */             { atom = new Atomic(v, !negation, set);
/* 154 */               nextToken(); }
/* 155 */             else { makeSemanticError("Undefined fuzzy set " + fuzzySet + " in fuzzy space " + space.getName()); }  }
/* 156 */           else { makeSemanticError("Undefined fuzzy variable " + variableId); }  }
/* 157 */         else { makeError("fuzzy set name"); }  }
/* 158 */       else { makeError("is"); }
/*     */     
/* 160 */     }  return atom;
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
/*     */   Expression factor(Model system) {
/* 172 */     Expression node = null;
/* 173 */     Expression term1 = null;
/* 174 */     Expression term2 = null;
/* 175 */     int tt = this.tokenizer.getTokenType();
/* 176 */     boolean negation = (tt == Tokenizer.NOT);
/* 177 */     if (negation) this.tokenizer.nextToken(); 
/* 178 */     tt = this.tokenizer.getTokenType();
/* 179 */     if (tt == Tokenizer.OPENPARENTHESIS)
/* 180 */     { this.tokenizer.nextToken();
/* 181 */       term1 = exp(system);
/* 182 */       if (this.tokenizer.getTokenType() == Tokenizer.CLOSEPARENTHESIS)
/* 183 */       { this.tokenizer.nextToken();
/* 184 */         if (negation) {
/* 185 */           node = new Expression(Tokenizer.NOT, term1, null);
/*     */         } else {
/* 187 */           node = term1;
/*     */         }  }
/* 189 */       else { makeError(")"); }
/*     */        }
/* 191 */     else { Atomic atom = atomic(system);
/* 192 */       if (this.error == null) {
/* 193 */         term1 = new Expression(atom);
/* 194 */         if (negation) {
/* 195 */           node = new Expression(Tokenizer.NOT, term1, null);
/*     */         } else {
/* 197 */           node = term1;
/*     */         } 
/*     */       }  }
/*     */     
/* 201 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Expression term(Model system) {
/* 211 */     Expression node = null;
/* 212 */     Expression fact = factor(system);
/* 213 */     if (fact != null) {
/* 214 */       int tt = this.tokenizer.getTokenType();
/* 215 */       if (tt == Tokenizer.AND) {
/* 216 */         this.tokenizer.nextToken();
/* 217 */         Expression fact2 = term(system);
/* 218 */         if (fact2 != null) {
/* 219 */           node = new Expression(tt, fact, fact2);
/*     */         }
/*     */       } else {
/* 222 */         node = fact;
/*     */       } 
/*     */     } 
/* 225 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression exp(Model system) {
/* 235 */     Expression node = null;
/* 236 */     Expression term1 = term(system);
/* 237 */     if (term1 != null) {
/* 238 */       int tt = this.tokenizer.getTokenType();
/* 239 */       if (tt == Tokenizer.OR) {
/* 240 */         this.tokenizer.nextToken();
/* 241 */         Expression term2 = exp(system);
/* 242 */         if (term2 != null) {
/* 243 */           node = new Expression(tt, term1, term2);
/*     */         }
/*     */       } else {
/* 246 */         node = term1;
/*     */       } 
/*     */     } 
/* 249 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule rule(Model system) {
/* 259 */     Rule r = null;
/* 260 */     int tt = this.tokenizer.getTokenType();
/* 261 */     if (tt == Tokenizer.IF)
/* 262 */     { this.tokenizer.nextToken();
/* 263 */       Expression expresion = exp(system);
/* 264 */       if (expresion != null) {
/* 265 */         tt = this.tokenizer.getTokenType();
/* 266 */         if (tt == Tokenizer.THEN)
/* 267 */         { this.tokenizer.nextToken();
/* 268 */           Atomic action = atomic(system);
/* 269 */           if (this.error == null) {
/* 270 */             tt = this.tokenizer.getTokenType();
/* 271 */             double confidence = 1.0D;
/* 272 */             if (tt == Tokenizer.OPEN_SQUARE_BRACKET) {
/* 273 */               nextToken();
/* 274 */               tt = this.tokenizer.getTokenType();
/* 275 */               if (tt == Tokenizer.REAL || tt == Tokenizer.INTEGER)
/* 276 */               { confidence = Double.parseDouble(getToken());
/* 277 */                 nextToken();
/* 278 */                 tt = this.tokenizer.getTokenType();
/* 279 */                 if (tt == Tokenizer.CLOSE_SQUARE_BRACKET)
/* 280 */                 { nextToken();
/* 281 */                   if (confidence < 0.0D || confidence > 1.0D)
/* 282 */                     makeSemanticError("The confidence value is a number between 0.0 and 1.0");  }
/*     */                 else
/* 284 */                 { makeError("]"); }  }
/* 285 */               else { makeError("a number between 0.0 and 1.0"); }
/*     */             
/* 287 */             }  r = new Rule(expresion, action, confidence);
/*     */           }  }
/* 289 */         else { makeError("then"); } 
/*     */       }  }
/* 291 */     else { makeError("if"); }
/* 292 */      return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Vector knowledge(Model system) {
/* 302 */     Vector v = new Vector();
/* 303 */     Rule r = rule(system);
/* 304 */     while (this.error == null && this.tokenizer.getTokenType() == Tokenizer.IF) {
/* 305 */       v.add(r);
/* 306 */       r = rule(system);
/*     */     } 
/*     */     
/* 309 */     if (this.error == null) { v.add(r); } else { v = null; }
/* 310 */      return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Set triangular(String name, Space space) {
/*     */     TriangularSet triangularSet;
/* 320 */     Set fuzzySet = null;
/* 321 */     if (this.tokenizer.getTokenType() == Tokenizer.TRIANGULAR)
/* 322 */     { this.tokenizer.nextToken();
/* 323 */       if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 324 */       { this.tokenizer.nextToken();
/* 325 */         if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */         
/* 327 */         { double inf = this.tokenizer.getDouble();
/* 328 */           this.tokenizer.nextToken();
/* 329 */           if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 330 */           { this.tokenizer.nextToken();
/* 331 */             if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */             
/* 333 */             { double half = this.tokenizer.getDouble();
/* 334 */               this.tokenizer.nextToken();
/* 335 */               if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 336 */               { this.tokenizer.nextToken();
/* 337 */                 if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */                 
/* 339 */                 { double sup = this.tokenizer.getDouble();
/* 340 */                   this.tokenizer.nextToken();
/* 341 */                   Set s = space.getSet(name);
/* 342 */                   if (s == null)
/* 343 */                   { triangularSet = new TriangularSet(name, inf, half, sup); }
/* 344 */                   else { makeSemanticError("Redefined fuzzy set " + name + " in fuzzy space " + space); }  }
/* 345 */                 else { makeError("number"); }  }
/* 346 */               else { makeError(","); }  }
/* 347 */             else { makeError("number"); }  }
/* 348 */           else { makeError(","); }  }
/* 349 */         else { makeError("number"); }  }
/* 350 */       else { makeError(","); }  }
/* 351 */     else { makeError("triangular"); }
/* 352 */      return (Set)triangularSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Set trapezoid(String name, Space space) {
/*     */     TrapezoidSet trapezoidSet;
/* 362 */     Set fuzzySet = null;
/* 363 */     if (this.tokenizer.getTokenType() == Tokenizer.TRAPEZOID)
/* 364 */     { this.tokenizer.nextToken();
/* 365 */       if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 366 */       { this.tokenizer.nextToken();
/* 367 */         if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */         
/* 369 */         { double minB = this.tokenizer.getDouble();
/* 370 */           this.tokenizer.nextToken();
/* 371 */           if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 372 */           { this.tokenizer.nextToken();
/* 373 */             if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */             
/* 375 */             { double minT = this.tokenizer.getDouble();
/* 376 */               this.tokenizer.nextToken();
/* 377 */               if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 378 */               { this.tokenizer.nextToken();
/* 379 */                 if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */                 
/* 381 */                 { double maxT = this.tokenizer.getDouble();
/* 382 */                   this.tokenizer.nextToken();
/* 383 */                   if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 384 */                   { this.tokenizer.nextToken();
/* 385 */                     if (this.tokenizer.getTokenType() == Tokenizer.REAL || this.tokenizer.getTokenType() == Tokenizer.INTEGER)
/*     */                     
/* 387 */                     { double maxB = this.tokenizer.getDouble();
/* 388 */                       this.tokenizer.nextToken();
/* 389 */                       Set s = space.getSet(name);
/* 390 */                       if (s == null)
/* 391 */                       { trapezoidSet = new TrapezoidSet(name, minB, minT, maxT, maxB); }
/* 392 */                       else { makeSemanticError("Redefined fuzzy set " + name + " in fuzzy space " + space); }  }
/* 393 */                     else { makeError("number"); }  }
/* 394 */                   else { makeError(","); }  }
/* 395 */                 else { makeError("number"); }  }
/* 396 */               else { makeError(","); }  }
/* 397 */             else { makeError("number"); }  }
/* 398 */           else { makeError(","); }  }
/* 399 */         else { makeError("number"); }  }
/* 400 */       else { makeError(","); }  }
/* 401 */     else { makeError("trapezoid"); }
/* 402 */      return (Set)trapezoidSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Set set(Space space) {
/* 412 */     Set fuzzySet = null;
/* 413 */     if (this.tokenizer.getTokenType() == Tokenizer.OPENBRACKET)
/* 414 */     { this.tokenizer.nextToken();
/* 415 */       if (this.tokenizer.getTokenType() == Tokenizer.STRING)
/* 416 */       { String name = this.tokenizer.getToken();
/* 417 */         this.tokenizer.nextToken();
/* 418 */         if (this.tokenizer.getTokenType() == Tokenizer.COMMA)
/* 419 */         { this.tokenizer.nextToken();
/* 420 */           switch (this.tokenizer.getTokenType()) {
/*     */             case 15:
/* 422 */               fuzzySet = trapezoid(name, space);
/*     */               break;
/*     */             case 16:
/* 425 */               fuzzySet = triangular(name, space);
/*     */               break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             default:
/* 433 */               makeError("invalid fuzzy set name");
/*     */               break;
/*     */           } 
/* 436 */           if (this.error == null)
/* 437 */             if (this.tokenizer.getTokenType() == Tokenizer.CLOSEBRACKET)
/* 438 */             { nextToken(); }
/* 439 */             else { makeError("}"); }
/*     */               }
/* 441 */         else { makeError(","); }  }
/* 442 */       else { makeError("fuzzy set name"); }  }
/* 443 */     else { makeError("{"); }
/* 444 */      return fuzzySet;
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
/*     */   Space space(Model system) {
/* 457 */     Space s = null;
/* 458 */     String name = id();
/* 459 */     if (this.error == null) {
/* 460 */       Space spa = system.getSpace(name);
/* 461 */       if (spa == null)
/* 462 */       { s = new Space(name, new Vector());
/* 463 */         if (getTokenType() == Tokenizer.IS)
/* 464 */         { nextToken();
/* 465 */           if (getTokenType() == Tokenizer.OPENBRACKET)
/* 466 */           { nextToken();
/* 467 */             if (getTokenType() != Tokenizer.CLOSEBRACKET)
/*     */             
/* 469 */             { Set fuzzySet = set(s);
/* 470 */               while (this.error == null && getTokenType() == Tokenizer.COMMA) {
/* 471 */                 s.addSet(fuzzySet);
/* 472 */                 nextToken();
/* 473 */                 fuzzySet = set(s);
/*     */               } 
/* 475 */               if (this.error == null) {
/* 476 */                 s.addSet(fuzzySet);
/*     */               }
/* 478 */               if (getTokenType() == Tokenizer.CLOSEBRACKET)
/* 479 */               { nextToken(); }
/* 480 */               else { makeError("}"); }
/*     */                }
/* 482 */             else { makeSemanticError("Empty fuzzy space"); }
/*     */              }
/* 484 */           else { makeError("{"); }  }
/* 485 */         else { makeError(" is "); }  }
/* 486 */       else { makeSemanticError("Redefined space " + name); }
/*     */     
/* 488 */     }  return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void spaces(Model system) {
/* 497 */     system.init();
/* 498 */     int max = -1;
/* 499 */     Space s = space(system);
/* 500 */     while (this.error == null && (getTokenType() == Tokenizer.STRING || getTokenType() == Tokenizer.INTEGER)) {
/*     */       
/* 502 */       system.addSpace(s);
/* 503 */       s = space(system);
/*     */     } 
/* 505 */     if (this.error == null) {
/* 506 */       system.addSpace(s);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector createKnowledge(Model system) {
/* 516 */     nextToken();
/* 517 */     return knowledge(system);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createSpaces(Model system) {
/* 525 */     nextToken();
/* 526 */     spaces(system);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Model createModel() {
/* 534 */     Model system = new Model();
/* 535 */     createSpaces(system);
/*     */     
/* 537 */     if (this.error != null) system = null; 
/* 538 */     return system;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Model createModelWithVariables() {
/* 546 */     Model system = createModel();
/* 547 */     if (this.error == null) {
/* 548 */       variables(system);
/*     */     }
/* 550 */     return system;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Engine createEngine() {
/* 558 */     Engine engine = null;
/* 559 */     Model system = createModelWithVariables();
/* 560 */     if (this.error == null) {
/* 561 */       Vector rules = knowledge(system);
/* 562 */       if (this.error == null) {
/* 563 */         engine = new Engine(rules, system);
/*     */       }
/*     */     } 
/* 566 */     return engine;
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
/*     */   Variable variable(Model system) {
/* 579 */     Variable v = null;
/* 580 */     if (getTokenType() == Tokenizer.OPENPARENTHESIS)
/* 581 */     { nextToken();
/* 582 */       String name = id();
/* 583 */       if (this.error == null)
/* 584 */         if (getTokenType() == Tokenizer.IS)
/* 585 */         { nextToken();
/* 586 */           String space = id();
/* 587 */           if (this.error == null)
/* 588 */             if (getTokenType() == Tokenizer.CLOSEPARENTHESIS)
/* 589 */             { Variable varId = system.getVariable(name);
/* 590 */               Space spaceId = system.getSpace(space);
/* 591 */               if (varId == null && spaceId != null)
/* 592 */               { v = new Variable(name, spaceId);
/* 593 */                 nextToken(); }
/*     */               
/* 595 */               else if (varId != null) { makeSemanticError(name + " variable symbol defined "); }
/* 596 */               else { makeSemanticError(space + " space symbol undefined "); }
/*     */                }
/* 598 */             else { makeError(")"); }
/*     */               }
/* 600 */         else { makeError("is or ="); }
/*     */           }
/* 602 */     else { makeError("("); }
/* 603 */      return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void variables(Model system) {
/* 612 */     system.initVariables();
/* 613 */     Variable v = variable(system);
/* 614 */     while (this.error == null && getTokenType() == Tokenizer.OPENPARENTHESIS) {
/* 615 */       v.position = system.getNumberOfVariables();
/* 616 */       system.addVariable(v);
/* 617 */       v = variable(system);
/*     */     } 
/* 619 */     if (this.error == null) {
/* 620 */       v.position = system.getNumberOfVariables();
/* 621 */       system.addVariable(v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createVariables(Model system) {
/* 631 */     nextToken();
/* 632 */     variables(system);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\parser\Grammar.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
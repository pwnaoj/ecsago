/*     */ package jml.fuzzy.parser;
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
/*     */ public class Tokenizer
/*     */ {
/*  39 */   public static int ERROR = -1;
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static int INTEGER = 1;
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static int REAL = 2;
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static int STRING = 3;
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static int OPENPARENTHESIS = 4;
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static int CLOSEPARENTHESIS = 5;
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static int IS = 6;
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static int NOT = 7;
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static int AND = 8;
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static int OR = 9;
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static int IF = 10;
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static int THEN = 11;
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static int OPENBRACKET = 12;
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static int CLOSEBRACKET = 13;
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static int COMMA = 14;
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static int TRAPEZOID = 15;
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static int TRIANGULAR = 16;
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static int STRING_LITERAL = 30;
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static int OPEN_SQUARE_BRACKET = 40;
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static int CLOSE_SQUARE_BRACKET = 41;
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static int EOF = 100;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int pos;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String buffer;
/*     */ 
/*     */ 
/*     */   
/*     */   public String token;
/*     */ 
/*     */ 
/*     */   
/*     */   public int token_type;
/*     */ 
/*     */ 
/*     */   
/*     */   public ParserError error;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tokenizer(String _buffer) {
/* 147 */     this.buffer = _buffer;
/* 148 */     this.pos = 0;
/* 149 */     this.token_type = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpace(char c) {
/* 158 */     return (c == ' ' || c == '\r' || c == '\n' || c == '\t');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eof() {
/* 165 */     return (this.pos == this.buffer.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextToken() {
/* 172 */     this.token_type = ERROR;
/* 173 */     for (; this.pos < this.buffer.length() && isSpace(this.buffer.charAt(this.pos)); this.pos++);
/* 174 */     if (this.pos < this.buffer.length()) {
/* 175 */       switch (this.buffer.charAt(this.pos)) {
/*     */         case '(':
/* 177 */           this.pos++;
/* 178 */           this.token_type = OPENPARENTHESIS;
/*     */           return;
/*     */         case ')':
/* 181 */           this.pos++;
/* 182 */           this.token_type = CLOSEPARENTHESIS;
/*     */           return;
/*     */         case '[':
/* 185 */           this.pos++;
/* 186 */           this.token_type = OPEN_SQUARE_BRACKET;
/*     */           return;
/*     */         case ']':
/* 189 */           this.pos++;
/* 190 */           this.token_type = CLOSE_SQUARE_BRACKET;
/*     */           return;
/*     */         case '-':
/* 193 */           this.pos++;
/* 194 */           this.token_type = NOT;
/* 195 */           initpos = this.pos;
/* 196 */           if (Character.isDigit(this.buffer.charAt(this.pos))) {
/* 197 */             while (this.pos < this.buffer.length() && Character.isDigit(this.buffer.charAt(this.pos)))
/* 198 */               this.pos++; 
/* 199 */             if (this.pos < this.buffer.length() && this.buffer.charAt(this.pos) == '.') {
/* 200 */               this.pos++;
/* 201 */               while (this.pos < this.buffer.length() && Character.isDigit(this.buffer.charAt(this.pos)))
/* 202 */                 this.pos++; 
/* 203 */               this.token_type = REAL;
/* 204 */               this.token = this.buffer.substring(initpos - 1, this.pos);
/*     */             } else {
/* 206 */               this.token_type = INTEGER;
/* 207 */               this.token = this.buffer.substring(initpos - 1, this.pos);
/*     */             } 
/*     */           } 
/*     */           return;
/*     */         case '=':
/* 212 */           this.pos++;
/* 213 */           this.token_type = IS;
/*     */           return;
/*     */         case '&':
/* 216 */           this.pos++;
/* 217 */           this.token_type = AND;
/*     */           return;
/*     */         case '|':
/* 220 */           this.pos++;
/* 221 */           this.token_type = OR;
/*     */           return;
/*     */         case '{':
/* 224 */           this.pos++;
/* 225 */           this.token_type = OPENBRACKET;
/*     */           return;
/*     */         case '}':
/* 228 */           this.pos++;
/* 229 */           this.token_type = CLOSEBRACKET;
/*     */           return;
/*     */         case ',':
/* 232 */           this.pos++;
/* 233 */           this.token_type = COMMA;
/*     */           return;
/*     */         case '"':
/* 236 */           initpos = this.pos;
/* 237 */           this.pos++;
/* 238 */           for (; this.pos < this.buffer.length() && this.buffer.charAt(this.pos) != '"'; this.pos++);
/* 239 */           if (this.pos < this.buffer.length()) {
/* 240 */             this.pos++;
/* 241 */             this.token_type = STRING_LITERAL;
/* 242 */             this.token = this.buffer.substring(initpos, this.pos);
/*     */           } else {
/* 244 */             this.token_type = ERROR;
/* 245 */             this.error = new ParserError(this.pos, "Unexpected end of file... Expecting \"");
/*     */           } 
/*     */           return;
/*     */       } 
/* 249 */       int initpos = this.pos;
/* 250 */       if (Character.isDigit(this.buffer.charAt(this.pos))) {
/* 251 */         while (this.pos < this.buffer.length() && Character.isDigit(this.buffer.charAt(this.pos)))
/* 252 */           this.pos++; 
/* 253 */         if (this.pos < this.buffer.length() && this.buffer.charAt(this.pos) == '.') {
/* 254 */           this.pos++;
/* 255 */           while (this.pos < this.buffer.length() && Character.isDigit(this.buffer.charAt(this.pos)))
/* 256 */             this.pos++; 
/* 257 */           this.token_type = REAL;
/* 258 */           this.token = this.buffer.substring(initpos, this.pos);
/*     */         } else {
/* 260 */           this.token_type = INTEGER;
/* 261 */           this.token = this.buffer.substring(initpos, this.pos);
/*     */         }
/*     */       
/* 264 */       } else if (Character.isLetter(this.buffer.charAt(this.pos))) {
/* 265 */         while (this.pos < this.buffer.length() && (Character.isLetterOrDigit(this.buffer.charAt(this.pos)) || this.buffer.charAt(this.pos) == '_' || this.buffer.charAt(this.pos) == '-'))
/* 266 */           this.pos++; 
/* 267 */         this.token_type = STRING;
/* 268 */         this.token = this.buffer.substring(initpos, this.pos);
/* 269 */         if (this.token.equals("not")) { this.token_type = NOT; }
/* 270 */         else if (this.token.equals("or")) { this.token_type = OR; }
/* 271 */         else if (this.token.equals("and")) { this.token_type = AND; }
/* 272 */         else if (this.token.equals("if")) { this.token_type = IF; }
/* 273 */         else if (this.token.equals("then")) { this.token_type = THEN; }
/* 274 */         else if (this.token.equals("is")) { this.token_type = IS; }
/* 275 */         else if (this.token.equals("triangular")) { this.token_type = TRIANGULAR; }
/* 276 */         else if (this.token.equals("trapezoid")) { this.token_type = TRAPEZOID; }
/*     */       
/*     */       } else {
/* 279 */         this.token_type = ERROR;
/* 280 */         this.error = new ParserError(this.pos, "Unexpected symbol..." + this.buffer.substring(this.pos - 5, this.pos + 2) + this.buffer.charAt(this.pos));
/*     */       } 
/*     */     } else {
/*     */       
/* 284 */       this.token_type = EOF;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt() {
/* 291 */     return Integer.parseInt(this.token);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/* 296 */     return Double.parseDouble(this.token);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToken() {
/* 301 */     return this.token;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTokenType() {
/* 306 */     return this.token_type;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\parser\Tokenizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
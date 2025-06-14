/*     */ package jml.parser;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.Vector;
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
/*     */ public class SimpleStreamTokenizer
/*     */ {
/*     */   public boolean comma_separator = false;
/*  46 */   Reader is = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleStreamTokenizer(Reader _is) {
/*  53 */     this.is = _is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleStreamTokenizer(String[] argv) {
/*  62 */     String text = "";
/*  63 */     if (argv != null) {
/*  64 */       for (int i = 0; i < argv.length; i++) {
/*  65 */         text = text + " " + argv[i];
/*     */       }
/*     */     }
/*  68 */     this.is = new StringReader(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleStreamTokenizer(String buffer) {
/*  76 */     this.is = new StringReader(buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use_comma_separator(boolean _comma_separator) {
/*  85 */     this.comma_separator = _comma_separator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is_blank(int c) {
/*  93 */     return ((0 <= c && c <= 32) || (this.comma_separator && c == 44));
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
/*     */   public Token nextToken() {
/* 105 */     Token token = new Token();
/* 106 */     int ttype = -1;
/*     */ 
/*     */     
/*     */     try {
/* 110 */       int c = this.is.read();
/* 111 */       if (c != -1) {
/* 112 */         while (is_blank(c)) {
/* 113 */           c = this.is.read();
/*     */         }
/* 115 */         String sval = "";
/* 116 */         while (!is_blank(c) && c != -1) {
/* 117 */           sval = sval + "" + (char)c;
/* 118 */           c = this.is.read();
/*     */         } 
/*     */         try {
/* 121 */           double nval = Double.parseDouble(sval);
/* 122 */           token = new Token(nval);
/*     */         }
/* 124 */         catch (NumberFormatException e) {
/* 125 */           token = new Token(sval);
/*     */         } 
/*     */       } else {
/*     */         
/* 129 */         token = new Token(true);
/*     */       }
/*     */     
/* 132 */     } catch (Exception e) {
/* 133 */       System.err.println("[SimpleStreamTokenizer]" + e.getMessage());
/*     */     } 
/* 135 */     return token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getTokens() {
/* 144 */     Vector tokens = new Vector();
/* 145 */     Token token = nextToken();
/* 146 */     while (token.type != 3) {
/* 147 */       tokens.add(token);
/* 148 */       token = nextToken();
/*     */     } 
/* 150 */     return tokens;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/* 158 */       if (this.is != null) {
/* 159 */         this.is.close();
/* 160 */         this.is = null;
/*     */       }
/*     */     
/* 163 */     } catch (Exception e) {
/* 164 */       System.err.println("[SimpleStreamTokenizer]" + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\parser\SimpleStreamTokenizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
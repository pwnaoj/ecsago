/*     */ package jml.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import jml.basics.Cloner;
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
/*     */ public class Data
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final int DOUBLE_BYTES = 8;
/*  58 */   protected int label = 0;
/*     */   
/*  60 */   protected Object data = null;
/*     */   
/*     */   public Data(Object _data) {
/*  63 */     this.data = _data;
/*     */   }
/*     */   
/*     */   public Data(Object _data, int _label) {
/*  67 */     this.data = _data;
/*  68 */     this.label = _label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  76 */     return new Data(Cloner.clone(this.data));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLabel() {
/*  83 */     return this.label;
/*     */   } public Object get() {
/*  85 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabel(int _label) {
/*  91 */     this.label = _label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean PRINT_LABEL) {
/* 100 */     StringBuffer sb = new StringBuffer();
/* 101 */     sb.append(this.data.toString());
/* 102 */     if (PRINT_LABEL) {
/* 103 */       sb.append(" ");
/* 104 */       sb.append(this.label);
/*     */     } 
/* 106 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     return toString(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\Data.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
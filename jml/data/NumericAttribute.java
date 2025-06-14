/*     */ package jml.data;
/*     */ 
/*     */ import org.xml.sax.Attributes;
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
/*     */ public class NumericAttribute
/*     */   extends Attribute
/*     */   implements Cloneable
/*     */ {
/*  40 */   public double min = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public double max = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public double average = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public double std_deviation = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public double median = -1.0E108D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericAttribute(String _label) {
/*  68 */     super(_label);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericAttribute(String _label, double _min, double _max) {
/*  78 */     super(_label);
/*  79 */     this.min = _min;
/*  80 */     this.max = _max;
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
/*     */   public NumericAttribute(String _label, double _min, double _max, double _average, double _std_deviation) {
/*  93 */     super(_label);
/*  94 */     this.min = _min;
/*  95 */     this.max = _max;
/*  96 */     this.average = _average;
/*  97 */     this.std_deviation = _std_deviation;
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
/*     */   public NumericAttribute(String _label, double _min, double _max, double _average, double _std_deviation, double _median) {
/* 111 */     super(_label);
/* 112 */     this.min = _min;
/* 113 */     this.max = _max;
/* 114 */     this.average = _average;
/* 115 */     this.std_deviation = _std_deviation;
/* 116 */     this.median = _median;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericAttribute(NumericAttribute original) {
/* 124 */     super(original.label);
/* 125 */     this.min = original.min;
/* 126 */     this.max = original.max;
/* 127 */     this.average = original.average;
/* 128 */     this.std_deviation = original.std_deviation;
/* 129 */     this.median = original.median;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/* 137 */     StringBuffer sb = new StringBuffer();
/* 138 */     sb.append("  <Numerical id=\"" + this.label + "\"");
/* 139 */     if (this.min != -1.0E108D) {
/* 140 */       sb.append(" min=\"" + this.min + "\"");
/* 141 */       sb.append(" max=\"" + this.max + "\"");
/*     */     } 
/* 143 */     if (this.average != -1.0E108D) {
/* 144 */       sb.append(" avg=\"" + this.average + "\"");
/* 145 */       sb.append(" std=\"" + this.std_deviation + "\"");
/*     */     } 
/* 147 */     if (this.median != -1.0E108D) {
/* 148 */       sb.append(" med=\"" + this.median + "\"");
/*     */     }
/* 150 */     sb.append("/>\n");
/* 151 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericAttribute(Attributes attributes) {
/* 159 */     super(attributes.getValue("id"));
/*     */     
/* 161 */     String val = attributes.getValue("min");
/* 162 */     if (val != null) { this.min = Double.parseDouble(val); } else { this.min = -1.0E108D; }
/* 163 */      val = attributes.getValue("max");
/* 164 */     if (val != null) { this.max = Double.parseDouble(val); } else { this.max = -1.0E108D; }
/* 165 */      val = attributes.getValue("avg");
/* 166 */     if (val != null) { this.average = Double.parseDouble(val); } else { this.average = -1.0E108D; }
/* 167 */      val = attributes.getValue("std");
/* 168 */     if (val != null) { this.std_deviation = Double.parseDouble(val); } else { this.std_deviation = -1.0E108D; }
/* 169 */      val = attributes.getValue("med");
/* 170 */     if (val != null) { this.median = Double.parseDouble(val); } else { this.median = -1.0E108D; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 178 */     return new NumericAttribute(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\NumericAttribute.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
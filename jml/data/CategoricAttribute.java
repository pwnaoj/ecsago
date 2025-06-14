/*     */ package jml.data;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public class CategoricAttribute
/*     */   extends Attribute
/*     */   implements Cloneable
/*     */ {
/*  41 */   protected Vector values = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CategoricAttribute(String _label, Vector _values) {
/*  49 */     super(_label);
/*  50 */     this.values = _values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CategoricAttribute(CategoricAttribute original) {
/*  58 */     super(original.label);
/*  59 */     this.values = new Vector();
/*  60 */     Enumeration iter = original.values.elements();
/*  61 */     while (iter.hasMoreElements()) {
/*  62 */       this.values.add(new String(iter.nextElement()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/*  71 */     StringBuffer sb = new StringBuffer();
/*  72 */     sb.append("  <Categorical id=\"" + this.label + "\">\n");
/*  73 */     Enumeration iter = this.values.elements();
/*  74 */     while (iter.hasMoreElements()) {
/*  75 */       sb.append("   <Value>" + iter.nextElement() + "</Value>\n");
/*     */     }
/*  77 */     sb.append("  </Categorical>\n");
/*  78 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CategoricAttribute(Attributes attributes) {
/*  86 */     super(attributes.getValue("id"));
/*  87 */     this.values = new Vector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*  95 */     return new CategoricAttribute(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addCategory(String category) {
/* 105 */     int i = 0;
/* 106 */     for (; i < this.values.size() && !category.equals(this.values.get(i)); i++);
/* 107 */     if (i == this.values.size()) this.values.add(category); 
/* 108 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 115 */     return this.values.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCategory(int index) {
/* 123 */     return this.values.get(index).toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\CategoricAttribute.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
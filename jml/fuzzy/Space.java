/*     */ package jml.fuzzy;
/*     */ 
/*     */ import java.util.Enumeration;
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
/*     */ 
/*     */ 
/*     */ public class Space
/*     */ {
/*     */   protected String id;
/*     */   protected Vector sets;
/*     */   
/*     */   public Space(String _id, Vector _sets) {
/*  50 */     this.sets = _sets;
/*  51 */     this.id = _id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  59 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getSet(String set) {
/*  68 */     Set s = null;
/*  69 */     if (this.sets != null) {
/*  70 */       Enumeration iter = this.sets.elements();
/*  71 */       boolean flag = false;
/*  72 */       while (iter.hasMoreElements() && !flag) {
/*  73 */         s = iter.nextElement();
/*  74 */         flag = s.getName().equals(set);
/*     */       } 
/*  76 */       if (!flag) s = null; 
/*     */     } 
/*  78 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getSet(double x) {
/*  87 */     Set s = null;
/*  88 */     double max = 0.0D;
/*  89 */     double val = 0.0D;
/*  90 */     if (this.sets != null) {
/*  91 */       Enumeration iter = this.sets.elements();
/*  92 */       while (iter.hasMoreElements()) {
/*  93 */         Set set = iter.nextElement();
/*  94 */         val = set.evaluate(x);
/*  95 */         if (val > max) {
/*  96 */           max = val;
/*  97 */           s = set;
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getSet(int index) {
/* 110 */     Set set = null;
/* 111 */     if (this.sets != null && index >= 0 && index < this.sets.size()) {
/* 112 */       set = this.sets.elementAt(index);
/*     */     }
/* 114 */     return set;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSetPosition(String set) {
/* 123 */     int index = -1;
/* 124 */     if (this.sets != null) {
/* 125 */       Enumeration iter = this.sets.elements();
/* 126 */       index = 0;
/* 127 */       while (iter.hasMoreElements() && !((Set)iter.nextElement()).getName().equals(set)) {
/* 128 */         index++;
/*     */       }
/* 130 */       if (index == this.sets.size()) index = -1; 
/*     */     } 
/* 132 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSetPosition(Set set) {
/* 141 */     int index = -1;
/* 142 */     if (this.sets != null) {
/* 143 */       Enumeration iter = this.sets.elements();
/* 144 */       index = 0;
/* 145 */       while (iter.hasMoreElements() && (Set)iter.nextElement() != set) {
/* 146 */         index++;
/*     */       }
/* 148 */       if (index == this.sets.size()) index = -1; 
/*     */     } 
/* 150 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSets() {
/* 158 */     int n = 0;
/* 159 */     if (this.sets != null) n = this.sets.size(); 
/* 160 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSet(Set set) {
/* 168 */     if (this.sets == null) {
/* 169 */       this.sets = new Vector();
/*     */     }
/* 171 */     this.sets.add(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceSet(int index, Set set) {
/* 180 */     if (index >= 0 && index < getNumberOfSets()) {
/* 181 */       this.sets.set(index, set);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delSet(Set set) {
/* 190 */     int index = getSetPosition(set);
/* 191 */     if (index >= 0) {
/* 192 */       this.sets.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delSet(int index) {
/* 201 */     if (this.sets != null && index >= 0 && index < this.sets.size()) {
/* 202 */       this.sets.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toXML() {
/* 211 */     String text = "<space>\n";
/* 212 */     text = text + "<id>" + this.id + "</id>\n";
/* 213 */     if (this.sets != null && this.sets.size() > 0) {
/* 214 */       Enumeration iter = this.sets.elements();
/* 215 */       while (iter.hasMoreElements()) {
/* 216 */         Set set = iter.nextElement();
/* 217 */         text = text + set.toXML();
/*     */       } 
/*     */     } 
/* 220 */     text = text + "</space>\n";
/* 221 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 229 */     String text = this.id + " is {";
/* 230 */     if (this.sets != null && this.sets.size() > 0) {
/* 231 */       Enumeration iter = this.sets.elements();
/* 232 */       if (iter.hasMoreElements()) {
/* 233 */         Set set = iter.nextElement();
/* 234 */         text = text + set.toString();
/*     */       } 
/* 236 */       while (iter.hasMoreElements()) {
/* 237 */         Set set = iter.nextElement();
/* 238 */         text = text + "," + set.toString();
/*     */       } 
/*     */     } 
/* 241 */     text = text + "}\n";
/* 242 */     return text;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\fuzzy\Space.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
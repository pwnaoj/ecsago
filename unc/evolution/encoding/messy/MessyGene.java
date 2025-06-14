/*    */ package unc.evolution.encoding.messy;
/*    */ 
/*    */ import jml.util.sort.SortableObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessyGene
/*    */   extends SortableObject
/*    */ {
/*    */   protected int locus;
/* 15 */   protected int intValue = 0;
/*    */   protected boolean boolValue = false;
/* 17 */   protected double realValue = 0.0D;
/*    */   
/*    */   protected MessyGene(int _locus, boolean _boolValue, int _intValue, double _realValue) {
/* 20 */     this.locus = _locus;
/* 21 */     this.intValue = _intValue;
/* 22 */     this.boolValue = _boolValue;
/* 23 */     this.realValue = _realValue;
/*    */   }
/*    */   
/*    */   public MessyGene(int _locus, int _value) {
/* 27 */     this.locus = _locus;
/* 28 */     this.intValue = _value;
/*    */   }
/*    */   
/*    */   public MessyGene(int _locus, boolean _value) {
/* 32 */     this.locus = _locus;
/* 33 */     this.boolValue = _value;
/*    */   }
/*    */   
/*    */   public MessyGene(int _locus, double _value) {
/* 37 */     this.locus = _locus;
/* 38 */     this.realValue = _value;
/*    */   }
/*    */   
/*    */   public Object copy() {
/* 42 */     return new MessyGene(this.locus, this.boolValue, this.intValue, this.realValue);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean lessThan(SortableObject x) {
/* 51 */     return (this.locus < ((MessyGene)x).locus);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equalTo(SortableObject x) {
/* 60 */     return (this.locus == ((MessyGene)x).locus);
/*    */   }
/*    */   
/* 63 */   public static int type = 0;
/*    */   
/*    */   public String toString() {
/* 66 */     String text = "(" + this.locus + ",";
/* 67 */     switch (type) {
/*    */       case 0:
/* 69 */         if (this.boolValue) { text = text + "1"; break; }  text = text + "0";
/*    */         break;
/*    */       case 2:
/* 72 */         text = text + this.realValue;
/*    */         break;
/*    */     } 
/* 75 */     return text + ")";
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\evolution\encoding\messy\MessyGene.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
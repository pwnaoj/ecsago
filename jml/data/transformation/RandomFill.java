/*    */ package jml.data.transformation;
/*    */ 
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.random.UniformNumberGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomFill
/*    */   extends MissedValuesFill
/*    */ {
/*    */   protected DataSource source;
/*    */   protected RealVector copy;
/*    */   protected UniformNumberGenerator g;
/*    */   
/*    */   public RandomFill(DataSource _source) {
/* 62 */     this.source = _source;
/* 63 */     this.g = new UniformNumberGenerator(this.source.size());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double fill(int i) {
/* 72 */     return this.copy.get(i);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMissedValues() {
/* 80 */     int i = 0;
/* 81 */     int n = this.copy.dimension();
/* 82 */     for (; i < n && this.copy.get(i) != -1.0E108D; i++);
/* 83 */     return (i < n);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data apply(Data data) {
/*    */     while (true) {
/* 96 */       Data d = this.source.get(this.g.newInt());
/* 97 */       this.copy = (RealVector)d.get();
/* 98 */       if (!hasMissedValues())
/* 99 */         return super.apply(data); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\RandomFill.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
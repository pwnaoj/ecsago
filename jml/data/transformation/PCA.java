/*    */ package jml.data.transformation;
/*    */ 
/*    */ import jml.algebra.Eigen;
/*    */ import jml.algebra.RealMatrix;
/*    */ import jml.algebra.RealVector;
/*    */ import jml.data.Data;
/*    */ import jml.data.DataSource;
/*    */ import jml.data.util.statistic.Covariance;
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
/*    */ public class PCA
/*    */   extends DataTransformation
/*    */ {
/* 41 */   protected RealMatrix eigenVectors = null;
/* 42 */   protected RealMatrix eigenVectorsT = null;
/*    */   
/*    */   public PCA(DataSource source) {
/* 45 */     Covariance c = new Covariance();
/* 46 */     c.generate(source);
/* 47 */     RealMatrix cov = c.covariance;
/* 48 */     Eigen eigen = new Eigen(cov);
/* 49 */     reduceEigenVectors(eigen);
/* 50 */     this.eigenVectorsT = new RealMatrix(this.eigenVectors);
/* 51 */     this.eigenVectorsT.transpose();
/*    */   }
/*    */   
/*    */   public PCA(RealMatrix covariance) {
/* 55 */     Eigen eigen = new Eigen(covariance);
/* 56 */     reduceEigenVectors(eigen);
/* 57 */     this.eigenVectorsT = new RealMatrix(this.eigenVectors);
/* 58 */     this.eigenVectorsT.transpose();
/*    */   }
/*    */   
/*    */   public PCA(Eigen eigen) {
/* 62 */     reduceEigenVectors(eigen);
/* 63 */     this.eigenVectorsT = new RealMatrix(this.eigenVectors);
/* 64 */     this.eigenVectorsT.transpose();
/*    */   }
/*    */   
/*    */   protected void reduceEigenVectors(Eigen eigen) {
/* 68 */     this.eigenVectors = eigen.getEigenVectors();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data apply(Data data) {
/* 78 */     RealMatrix d = new RealMatrix((RealVector)data.get());
/* 79 */     d.transpose();
/* 80 */     d.multiply(this.eigenVectors);
/* 81 */     return new Data(d.getRow(0), data.getLabel());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Data inverse(Data data) {
/* 91 */     RealMatrix d = new RealMatrix((RealVector)data.get());
/* 92 */     d.transpose();
/* 93 */     d.multiply(this.eigenVectorsT);
/* 94 */     return new Data(d.getRow(0), data.getLabel());
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\PCA.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
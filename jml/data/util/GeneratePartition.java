/*    */ package jml.data.util;
/*    */ 
/*    */ import java.io.FileReader;
/*    */ import jml.data.DataSource;
/*    */ import jml.parser.SimpleStreamTokenizer;
/*    */ import jml.random.Partition;
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
/*    */ public class GeneratePartition
/*    */ {
/*    */   public static void createDataPartition(String path, DataSource source, int m, int n) {
/* 30 */     for (int i = 0; i < m; i++) {
/* 31 */       Partition p = DataSourceUtils.createPartition(source, n);
/* 32 */       p.save(path + "/partition-" + i + ".txt");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static Partition[] getPartition(String path, DataSource source, int n_partitions, int partition_size) {
/* 38 */     Partition[] part = new Partition[n_partitions];
/* 39 */     for (int i = 0; i < n_partitions; i++) {
/*    */       
/* 41 */       try { FileReader file = new FileReader(path + "/partition-" + i + ".txt");
/* 42 */         part[i] = new Partition(source.size(), partition_size, new SimpleStreamTokenizer(file));
/* 43 */         part[i].setGroupsNumber(partition_size); }
/* 44 */       catch (Exception e) { e.printStackTrace(); }
/*    */     
/* 46 */     }  return part;
/*    */   }
/*    */   
/*    */   public static void main(String[] argv) {
/* 50 */     String basic_path = argv[0];
/* 51 */     int n_partitions = Integer.parseInt(argv[1]);
/* 52 */     int partition_size = Integer.parseInt(argv[2]);
/*    */     
/* 54 */     for (int k = 3; k < argv.length; k++) {
/* 55 */       String path = basic_path + "/" + argv[k];
/* 56 */       DataSource source = DataSourceUtils.loadDataSourceFromPath(path);
/* 57 */       createDataPartition(path, source, n_partitions, partition_size);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\dat\\util\GeneratePartition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
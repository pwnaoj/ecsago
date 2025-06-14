/*    */ package unc;
/*    */ 
/*    */ import unc.gui.UNCMainFrame;
/*    */ import unc.utils.test.UNCParameterizedTest;
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
/*    */ public class Runner
/*    */ {
/*    */   public static void main(String[] argv) {
/* 19 */     if (argv.length == 1) {
/* 20 */       UNCParameterizedTest.main(argv);
/*    */     } else {
/* 22 */       UNCMainFrame.main(argv);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\unc\Runner.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
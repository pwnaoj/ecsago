/*    */ package jml.basics;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
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
/*    */ public class Cloner
/*    */ {
/*    */   public static Integer clone(Integer obj) {
/* 20 */     return new Integer(obj.intValue());
/*    */   }
/*    */   
/*    */   public static String clone(String obj) {
/* 24 */     return new String(obj);
/*    */   }
/*    */   
/*    */   public static Vector clone(Vector obj) {
/* 28 */     Vector v = new Vector();
/* 29 */     Enumeration iter = obj.elements();
/* 30 */     while (iter.hasMoreElements()) {
/* 31 */       v.add(clone(iter.nextElement()));
/*    */     }
/* 33 */     return v;
/*    */   }
/*    */   
/*    */   public static Object clone(Object obj) {
/* 37 */     if (obj instanceof Vector) return clone((Vector)obj); 
/* 38 */     if (obj instanceof String) return clone((String)obj); 
/* 39 */     if (obj instanceof Integer) return clone((Integer)obj); 
/* 40 */     Class cl = obj.getClass();
/*    */     try {
/* 42 */       Method m = cl.getMethod("clone", null);
/* 43 */       return m.invoke(obj, null);
/*    */     }
/* 45 */     catch (Exception e) {
/* 46 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\basics\Cloner.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
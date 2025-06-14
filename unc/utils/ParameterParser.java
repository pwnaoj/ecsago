/*     */ package unc.utils;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class ParameterParser
/*     */ {
/*     */   public static boolean getBoolean(Hashtable table, String tag) {
/*   8 */     Object obj = table.get(tag);
/*   9 */     if (obj != null) {
/*     */       try {
/*  11 */         return Boolean.valueOf((String)obj).booleanValue();
/*  12 */       } catch (Exception e) {
/*  13 */         return false;
/*     */       } 
/*     */     }
/*  16 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInt(Hashtable table, String tag) {
/*  21 */     Object obj = table.get(tag);
/*  22 */     if (obj != null) {
/*     */       try {
/*  24 */         return Integer.parseInt((String)obj);
/*  25 */       } catch (Exception e) {
/*  26 */         return Integer.MAX_VALUE;
/*     */       } 
/*     */     }
/*  29 */     return Integer.MAX_VALUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDouble(Hashtable table, String tag) {
/*  34 */     Object obj = table.get(tag);
/*  35 */     if (obj != null) {
/*     */       try {
/*  37 */         return Double.parseDouble((String)obj);
/*  38 */       } catch (Exception e) {
/*  39 */         return Double.NaN;
/*     */       } 
/*     */     }
/*  42 */     return Double.NaN;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getString(Hashtable table, String tag) {
/*  47 */     Object obj = table.get(tag);
/*  48 */     if (obj != null) {
/*     */       try {
/*  50 */         return (String)obj;
/*  51 */       } catch (Exception e) {
/*  52 */         return null;
/*     */       } 
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Hashtable getHash(Hashtable table, String tag) {
/*  60 */     Object obj = table.get(tag);
/*  61 */     if (obj != null) {
/*     */       try {
/*  63 */         return (Hashtable)obj;
/*  64 */       } catch (Exception e) {
/*  65 */         return null;
/*     */       } 
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] get(Hashtable table, String tag) {
/*  73 */     Object obj = table.get(tag);
/*  74 */     if (obj != null) {
/*  75 */       String[] x = ((String)obj).split(";");
/*  76 */       int[] v = new int[x.length];
/*  77 */       for (int i = 0; i < v.length; i++) {
/*  78 */         v[i] = Integer.parseInt(x[i]);
/*     */       }
/*  80 */       return v;
/*  81 */     }  return new int[0];
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean get(String command, int start, int i, int level, Hashtable table) {
/*  86 */     boolean flag = (level == 0);
/*  87 */     if (flag) {
/*  88 */       String tag_value = command.substring(start, i);
/*  89 */       String[] opt = tag_value.split("=", 2);
/*  90 */       if (opt.length == 2) {
/*  91 */         Hashtable table1 = clean_get(opt[1]);
/*  92 */         if (table1 != null) {
/*  93 */           table.put(opt[0], table1);
/*     */         } else {
/*  95 */           table.put(opt[0], opt[1]);
/*     */         } 
/*     */       } else {
/*  98 */         System.err.println("Expecting TAG=VALUE at position " + start);
/*     */       } 
/*     */     } 
/* 101 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Hashtable clean_get(String command) {
/* 111 */     char[] cmd = command.toCharArray();
/* 112 */     int n = cmd.length - 1;
/* 113 */     if (cmd[0] == '{' && cmd[n] == '}') {
/* 114 */       Hashtable table = new Hashtable();
/* 115 */       int level = 0;
/* 116 */       int i = 1;
/* 117 */       int start = 1;
/* 118 */       while (i < n) {
/* 119 */         switch (cmd[i]) { case '{':
/* 120 */             level++; break;
/* 121 */           case '}': level--; break;
/*     */           case ',':
/* 123 */             if (get(command, start, i, level, table)) start = i + 1; 
/*     */             break; }
/*     */         
/* 126 */         i++;
/*     */       } 
/* 128 */       get(command, start, i, level, table);
/* 129 */       return table;
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Hashtable get(String command) {
/* 142 */     StringBuffer cmd = new StringBuffer();
/* 143 */     char[] char_cmd = command.toCharArray();
/* 144 */     for (int i = 0; i < char_cmd.length; i++) {
/* 145 */       if (char_cmd[i] != ' ' && char_cmd[i] != '\t' && char_cmd[i] != '\n') {
/* 146 */         cmd.append(char_cmd[i]);
/*     */       }
/*     */     } 
/* 149 */     return clean_get(cmd.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\ParameterParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
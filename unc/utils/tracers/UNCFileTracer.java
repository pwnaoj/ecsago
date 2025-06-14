/*     */ package unc.utils.tracers;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Hashtable;
/*     */ import jml.basics.Algorithm;
/*     */ import jml.data.sources.PartitionDataSource;
/*     */ import jml.evolution.Population;
/*     */ import jml.evolution.PopulationStatistics;
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
/*     */ public class UNCFileTracer
/*     */   extends UNCTracer
/*     */ {
/*     */   public String out_path;
/*  31 */   public int id_run = 0;
/*     */   public static final String OUT_PATH = "output_path";
/*     */   public static final String MEM_FACTOR = "mem_factor";
/*     */   
/*     */   public UNCFileTracer(int[] _save_evos, int[] _save_refs, String _out_path) {
/*  36 */     super(_save_evos, _save_refs);
/*  37 */     this.out_path = makeDirectory(_out_path);
/*     */   }
/*     */   
/*     */   public UNCFileTracer(int[] _save_evos, int[] _save_refs, String _out_path, double _memFactor) {
/*  41 */     super(_save_evos, _save_refs);
/*  42 */     this.out_path = makeDirectory(_out_path, _memFactor);
/*     */   }
/*     */   
/*     */   public UNCFileTracer(Hashtable table) {
/*  46 */     super(table);
/*  47 */     this.out_path = (String)table.get("output_path");
/*  48 */     if (this.out_path == null) this.out_path = "./results/"; 
/*  49 */     Object obj = table.get("mem_factor");
/*  50 */     if (obj != null) {
/*  51 */       double mem_factor = Double.parseDouble((String)obj);
/*  52 */       makeDirectory(this.out_path, mem_factor);
/*     */     } else {
/*  54 */       this.out_path = makeDirectory(this.out_path);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRun(int run) {
/*  63 */     this.id_run = run;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String makeDirectory(String path) {
/*  71 */     Calendar cal = new GregorianCalendar();
/*  72 */     String dir = path + "/" + (cal.get(2) + 1) + "_" + cal.get(5) + "_" + cal.get(1) + "/";
/*  73 */     System.out.println("Directory of results = " + dir);
/*  74 */     File a = new File(dir);
/*  75 */     a.mkdirs();
/*  76 */     return dir;
/*     */   }
/*     */   
/*     */   public static String makeDirectory(String path, double memFactor) {
/*  80 */     Calendar cal = new GregorianCalendar();
/*  81 */     String dir = path + (cal.get(2) + 1) + "_" + cal.get(5) + "_" + cal.get(1) + "/tw_" + memFactor + "/";
/*  82 */     System.out.println("Directory of results = " + dir);
/*  83 */     File a = new File(dir);
/*  84 */     a.mkdirs();
/*  85 */     return dir;
/*     */   }
/*     */   
/*     */   public void add(Algorithm alg, Object obj) {
/*  89 */     super.add(alg, obj);
/*  90 */     if (this.evo_updated || this.ref_updated || this.ext_updated) {
/*  91 */       String spop = "";
/*  92 */       if (this.evo_updated) {
/*  93 */         System.out.println("Population saved");
/*  94 */         Population p = ((PopulationStatistics)obj).getPopulation();
/*  95 */         for (int i = 0; i < p.size(); i++) {
/*  96 */           spop = spop + p.get(i) + "\n";
/*     */         }
/*  98 */         save(this.out_path + "run-" + this.id_run + "-pop-" + this.evo_counter + ".txt", spop);
/*     */       }
/* 100 */       else if (this.ext_updated) {
/* 101 */         System.out.println("EXTRACTION");
/* 102 */         System.out.println(obj.toString());
/* 103 */         save(this.out_path + "run-" + this.id_run + "-pop-ext.txt", obj.toString());
/*     */       } else {
/* 105 */         System.out.println("REFINEMENT");
/* 106 */         System.out.println(obj.toString());
/* 107 */         save(this.out_path + "run-" + this.id_run + "-pop-ref-" + this.ref_counter + ".txt", obj.toString());
/*     */       }
/*     */     
/*     */     }
/* 111 */     else if (alg instanceof unc.UNC) {
/* 112 */       if (obj instanceof Integer) {
/* 113 */         setRun(((Integer)obj).intValue());
/*     */       }
/* 115 */       else if (obj instanceof PartitionDataSource) {
/* 116 */         ((PartitionDataSource)obj).save(this.out_path + "run-" + this.id_run + "-data.txt", false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save(String fileName, String s) {
/*     */     try {
/* 130 */       FileWriter writer = new FileWriter(fileName);
/* 131 */       writer.write(s);
/* 132 */       writer.close();
/* 133 */     } catch (Exception e) {
/* 134 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\tracers\UNCFileTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
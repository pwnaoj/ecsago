/*     */ package unc.utils.tracers;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Algorithm;
/*     */ import jml.basics.Result;
/*     */ import jml.basics.Tracer;
/*     */ import unc.Prototype;
/*     */ import unc.utils.ParameterParser;
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
/*     */ public class UNCTracer
/*     */   implements Tracer
/*     */ {
/*  23 */   protected int evo_counter = -1;
/*  24 */   protected int ref_counter = 0;
/*  25 */   protected int group = 0;
/*     */   
/*     */   protected int[] save_evos;
/*     */   protected int[] save_refs;
/*  29 */   protected Vector evos = new Vector();
/*  30 */   protected Vector refs = new Vector();
/*  31 */   protected Prototype extracted = null;
/*     */   
/*     */   protected int[] save_groups;
/*  34 */   protected Vector[] inc_evos = null;
/*  35 */   protected Vector[] inc_refs = null;
/*  36 */   protected Prototype[] inc_prototypes = null;
/*     */   
/*     */   public boolean evo_updated = false;
/*     */   
/*     */   public boolean ref_updated = false;
/*     */   public boolean ext_updated = false;
/*     */   public boolean inc_updated = false;
/*     */   public static final String EVOTRACE = "evolution_trace";
/*     */   public static final String REFTRACE = "refinement_trace";
/*     */   public static final String INCTRACE = "incremental_trace";
/*     */   
/*     */   public void reset() {
/*  48 */     this.evo_counter = -1;
/*  49 */     this.ref_counter = 0;
/*  50 */     this.evos.clear();
/*  51 */     this.refs.clear();
/*     */   }
/*     */   
/*     */   protected int[] get(String val) {
/*  55 */     String[] x = val.split(";");
/*  56 */     int[] v = new int[x.length];
/*  57 */     for (int i = 0; i < v.length; i++) {
/*  58 */       v[i] = Integer.parseInt(x[i]);
/*     */     }
/*  60 */     return v;
/*     */   }
/*     */   
/*     */   public UNCTracer(Hashtable table) {
/*  64 */     this.save_evos = ParameterParser.get(table, "evolution_trace");
/*  65 */     this.save_refs = ParameterParser.get(table, "refinement_trace");
/*  66 */     this.save_groups = ParameterParser.get(table, "incremental_trace");
/*  67 */     if (this.save_groups == null || this.save_groups.length == 0) this.save_groups = new int[] { 0 }; 
/*  68 */     int n = this.save_groups.length;
/*  69 */     this.inc_evos = new Vector[n];
/*  70 */     this.inc_refs = new Vector[n];
/*  71 */     this.inc_prototypes = new Prototype[n];
/*  72 */     for (int i = 0; i < n; i++) {
/*  73 */       this.inc_evos[i] = new Vector();
/*  74 */       this.inc_refs[i] = new Vector();
/*  75 */       this.inc_prototypes[i] = null;
/*     */     } 
/*  77 */     setGroup(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public UNCTracer(int[] _save_evos, int[] _save_refs) {
/*  82 */     this(_save_evos, _save_refs, new int[] { 0 });
/*     */   }
/*     */   
/*     */   public UNCTracer(int[] _save_evos, int[] _save_refs, int[] _save_groups) {
/*  86 */     this.save_evos = _save_evos;
/*  87 */     this.save_refs = _save_refs;
/*  88 */     this.save_groups = _save_groups;
/*  89 */     int n = this.save_groups.length;
/*  90 */     this.inc_evos = new Vector[n];
/*  91 */     this.inc_refs = new Vector[n];
/*  92 */     this.inc_prototypes = new Prototype[n];
/*  93 */     for (int i = 0; i < n; i++) {
/*  94 */       this.inc_evos[i] = new Vector();
/*  95 */       this.inc_refs[i] = new Vector();
/*  96 */       this.inc_prototypes[i] = null;
/*     */     } 
/*  98 */     setGroup(0);
/*     */   }
/*     */   
/*     */   public void setGroup(int _group) {
/* 102 */     this.group = _group;
/* 103 */     this.evo_counter = -1;
/* 104 */     this.ref_counter = 0;
/* 105 */     int k = 0;
/* 106 */     for (; k < this.save_groups.length && this.group != this.save_groups[k]; k++);
/* 107 */     if (k < this.save_groups.length) {
/* 108 */       this.evos = this.inc_evos[k];
/* 109 */       this.refs = this.inc_refs[k];
/* 110 */       this.extracted = this.inc_prototypes[k];
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Algorithm alg, Object obj) {
/* 115 */     this.evo_updated = false;
/* 116 */     this.ref_updated = false;
/* 117 */     this.ext_updated = false;
/* 118 */     this.inc_updated = false;
/* 119 */     if (alg instanceof jml.evolution.EvolutionaryAlgorithm) {
/* 120 */       this.evo_counter++;
/* 121 */       int i = 0;
/* 122 */       for (; i < this.save_evos.length && this.save_evos[i] != this.evo_counter; i++);
/* 123 */       if (i < this.save_evos.length) {
/* 124 */         this.evos.add(obj);
/* 125 */         this.evo_updated = true;
/*     */       }
/*     */     
/*     */     }
/* 129 */     else if (alg instanceof unc.refinement.Refinement) {
/* 130 */       this.ref_counter++;
/* 131 */       int i = 0;
/* 132 */       for (; i < this.save_refs.length && this.save_refs[i] != this.ref_counter; i++);
/* 133 */       if (i < this.save_refs.length) {
/* 134 */         this.refs.add(obj);
/* 135 */         this.ref_updated = true;
/*     */       }
/*     */     
/* 138 */     } else if (alg instanceof unc.UNC) {
/* 139 */       if (obj instanceof Prototype) {
/* 140 */         this.extracted = (Prototype)obj;
/* 141 */         this.inc_prototypes[this.group] = this.extracted;
/* 142 */         this.ext_updated = true;
/*     */       }
/* 144 */       else if (obj instanceof Integer) {
/* 145 */         this.inc_updated = true;
/* 146 */         int group = ((Integer)obj).intValue();
/* 147 */         setGroup(group);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Result getStat() {
/* 155 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar\\un\\utils\tracers\UNCTracer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
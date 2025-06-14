/*     */ package jml.classification.anomaly;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.data.DataSource;
/*     */ import jml.parser.SimpleStreamTokenizer;
/*     */ import jml.random.Partition;
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
/*     */ public class AnomalyPartition
/*     */   extends Partition
/*     */ {
/*  46 */   Partition negative_partition = null;
/*     */ 
/*     */ 
/*     */   
/*  50 */   Vector positive = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnomalyPartition(Partition _negative_partition, Vector _positive) {
/*  59 */     this.negative_partition = _negative_partition;
/*  60 */     this.positive = _positive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnomalyPartition(DataSource source, int _m) {
/*  69 */     Vector negative = source.classSamples(0);
/*  70 */     Partition.permutation(negative);
/*  71 */     this.negative_partition = new Partition(negative, _m);
/*  72 */     this.positive = source.classSamples(1);
/*     */   }
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
/*     */   public AnomalyPartition(int n, int _m, SimpleStreamTokenizer tok, DataSource source) {
/*  85 */     super(n, _m, tok);
/*  86 */     int i = 0;
/*  87 */     this.positive = new Vector();
/*  88 */     while (i < this.index.size()) {
/*  89 */       Integer v = this.index.get(i);
/*  90 */       int k = v.intValue();
/*  91 */       if (source.get(k).getLabel() == 1) {
/*  92 */         this.positive.add(v);
/*  93 */         this.index.remove(i); continue;
/*     */       } 
/*  95 */       i++;
/*     */     } 
/*     */     
/*  98 */     this.negative_partition = new Partition(this.index, _m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration skipGroup(int pos) {
/* 108 */     return new AnomalyPartitionEnumeration(this, this, pos, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration getGroup(int pos) {
/* 117 */     return new AnomalyPartitionEnumeration(this, this, pos, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int groupSize(int k) {
/* 125 */     return this.negative_partition.groupSize(k) + this.positive.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupsNumber(int _m) {
/* 133 */     this.negative_partition.setGroupsNumber(_m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 141 */     return this.negative_partition.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration elements() {
/* 149 */     return new AnomalyPartitionEnumeration(this, this, -1, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class AnomalyPartitionEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     protected AnomalyPartition partition;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Enumeration inner_enumeration;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean selected_group;
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean using_positive;
/*     */ 
/*     */ 
/*     */     
/*     */     private final AnomalyPartition this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AnomalyPartitionEnumeration(AnomalyPartition this$0, AnomalyPartition _partition, int _group, boolean _selected_group) {
/* 181 */       this.this$0 = this$0; this.partition = null; this.inner_enumeration = null; this.selected_group = false; this.using_positive = false;
/* 182 */       this.partition = _partition;
/* 183 */       this.selected_group = _selected_group;
/* 184 */       if (_group >= 0) {
/* 185 */         if (this.selected_group) {
/* 186 */           this.inner_enumeration = this.partition.negative_partition.getGroup(_group);
/*     */         } else {
/*     */           
/* 189 */           this.inner_enumeration = this.partition.negative_partition.skipGroup(_group);
/*     */         } 
/*     */       } else {
/* 192 */         this.inner_enumeration = this.partition.negative_partition.elements();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 202 */       return (this.inner_enumeration.hasMoreElements() || (this.selected_group && !this.using_positive && this.partition.positive.size() > 0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 211 */       if (this.inner_enumeration.hasMoreElements()) {
/* 212 */         return this.inner_enumeration.nextElement();
/*     */       }
/* 214 */       if (this.selected_group && !this.using_positive) {
/* 215 */         this.using_positive = true;
/* 216 */         this.inner_enumeration = this.this$0.positive.elements();
/* 217 */         return this.inner_enumeration.nextElement();
/* 218 */       }  return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\anomaly\AnomalyPartition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package jml.structures;
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
/*     */ public class CQueue
/*     */ {
/*     */   class Node
/*     */   {
/*     */     public Node prev;
/*     */     public Node next;
/*     */     public Object data;
/*     */     private final CQueue this$0;
/*     */     
/*     */     public Node(CQueue this$0, Object d) {
/*  64 */       this.this$0 = this$0; this.prev = null; this.next = null; this.data = null; this.data = d;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public Node start = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean empty() {
/*  81 */     return (this.start == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/*  90 */     Object val = null;
/*  91 */     if (!empty()) {
/*  92 */       this.start = this.start.next;
/*  93 */       val = this.start.data;
/*     */     } 
/*  95 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object advance(int n) {
/* 106 */     Object val = null;
/* 107 */     if (!empty()) {
/* 108 */       for (int i = 0; i < n; i++) {
/* 109 */         this.start = this.start.next;
/*     */       }
/* 111 */       val = this.start.data;
/*     */     } 
/* 113 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object prev() {
/* 123 */     Object val = null;
/* 124 */     if (!empty()) {
/* 125 */       this.start = this.start.prev;
/* 126 */       val = this.start.data;
/*     */     } 
/* 128 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Object data) {
/* 138 */     if (empty()) {
/* 139 */       this.start = new Node(this, data);
/* 140 */       this.start.prev = this.start;
/* 141 */       this.start.next = this.start;
/*     */     } else {
/*     */       
/* 144 */       Node aux = new Node(this, data);
/* 145 */       aux.next = this.start.next;
/* 146 */       aux.next.prev = aux;
/* 147 */       this.start.next = aux;
/* 148 */       aux.prev = this.start;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void del() {
/* 159 */     if (!empty()) {
/* 160 */       if (this.start.next == this.start) { this.start = null; }
/*     */       else
/* 162 */       { this.start.prev.next = this.start.next;
/* 163 */         this.start.next.prev = this.start.prev;
/* 164 */         this.start = this.start.prev; }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 175 */     Object data = null;
/* 176 */     if (!empty()) data = this.start.data; 
/* 177 */     return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\structures\CQueue.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
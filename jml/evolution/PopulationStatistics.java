/*     */ package jml.evolution;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jml.basics.Result;
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
/*     */ public class PopulationStatistics
/*     */   extends Result
/*     */   implements Cloneable
/*     */ {
/*  41 */   protected Population population = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected double pop_size;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int bestIndex;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Individual bestIndividual;
/*     */ 
/*     */ 
/*     */   
/*     */   public double best;
/*     */ 
/*     */ 
/*     */   
/*     */   public double worst;
/*     */ 
/*     */ 
/*     */   
/*     */   public double avg;
/*     */ 
/*     */ 
/*     */   
/*     */   public double avg_length;
/*     */ 
/*     */ 
/*     */   
/*     */   public double best_length;
/*     */ 
/*     */ 
/*     */   
/*     */   public double feasible_chroms;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PopulationStatistics() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public PopulationStatistics(Population _population) {
/*  87 */     this.population = _population;
/*  88 */     Genotype genotype = this.population.environment.getGenotype();
/*  89 */     Vector individuals = this.population.individuals;
/*     */     
/*  91 */     Enumeration iter = individuals.elements();
/*     */     
/*  93 */     Individual ind = iter.nextElement();
/*     */     
/*  95 */     this.best = ind.getFitness();
/*  96 */     this.worst = ind.getFitness();
/*  97 */     this.avg = ind.getFitness();
/*  98 */     this.avg_length = genotype.size(ind.getGenome());
/*     */     
/* 100 */     this.feasible_chroms = 0.0D;
/* 101 */     if (ind.isFeasible()) this.feasible_chroms++;
/*     */     
/* 103 */     int best_index = 0;
/* 104 */     int i = 0;
/* 105 */     while (iter.hasMoreElements()) {
/* 106 */       i++;
/* 107 */       ind = iter.nextElement();
/* 108 */       if (this.best < ind.getFitness()) {
/* 109 */         this.best = ind.getFitness();
/* 110 */         best_index = i;
/*     */       }
/* 112 */       else if (this.worst > ind.getFitness()) {
/* 113 */         this.worst = ind.getFitness();
/*     */       } 
/*     */       
/* 116 */       this.avg += ind.getFitness();
/* 117 */       this.avg_length += genotype.size(ind.getGenome());
/* 118 */       if (ind.isFeasible()) this.feasible_chroms++;
/*     */     
/*     */     } 
/* 121 */     this.bestIndividual = individuals.elementAt(best_index);
/* 122 */     this.bestIndex = best_index;
/* 123 */     this.pop_size = individuals.size();
/* 124 */     this.avg /= this.pop_size;
/* 125 */     this.avg_length /= this.pop_size;
/* 126 */     this.feasible_chroms /= this.pop_size;
/* 127 */     this.best_length = genotype.size(this.bestIndividual.getGenome());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PopulationStatistics(PopulationStatistics source) {
/* 135 */     this.population = source.population;
/* 136 */     this.bestIndividual = source.bestIndividual;
/* 137 */     this.bestIndex = source.bestIndex;
/* 138 */     this.best = source.best;
/* 139 */     this.avg = source.avg;
/* 140 */     this.worst = source.worst;
/* 141 */     this.feasible_chroms = source.feasible_chroms;
/* 142 */     this.best_length = source.best_length;
/* 143 */     this.avg_length = source.avg_length;
/* 144 */     this.pop_size = source.pop_size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Result create() {
/* 152 */     return new PopulationStatistics();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 159 */     return new PopulationStatistics(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     StringBuffer sb = new StringBuffer();
/* 167 */     sb.append(' ');
/* 168 */     sb.append(this.bestIndividual);
/* 169 */     sb.append(' ');
/* 170 */     sb.append(this.best);
/* 171 */     sb.append(' ');
/* 172 */     sb.append(this.avg);
/* 173 */     sb.append(' ');
/* 174 */     sb.append(this.worst);
/* 175 */     sb.append(' ');
/* 176 */     sb.append(this.best_length);
/* 177 */     sb.append(' ');
/* 178 */     sb.append(this.avg_length);
/* 179 */     sb.append(' ');
/* 180 */     sb.append(this.pop_size);
/* 181 */     sb.append(' ');
/* 182 */     sb.append(this.feasible_chroms);
/* 183 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sum(Result _other) {
/* 191 */     if (_other instanceof PopulationStatistics) {
/* 192 */       PopulationStatistics other = (PopulationStatistics)_other;
/* 193 */       this.best += other.best;
/* 194 */       this.avg += other.avg;
/* 195 */       this.worst += other.worst;
/* 196 */       this.avg_length += other.avg_length;
/* 197 */       this.best_length += other.best_length;
/* 198 */       this.feasible_chroms += other.feasible_chroms;
/* 199 */       this.pop_size += other.pop_size;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void substract(Result _other) {
/* 208 */     if (_other instanceof PopulationStatistics) {
/* 209 */       PopulationStatistics other = (PopulationStatistics)_other;
/* 210 */       this.best -= other.best;
/* 211 */       this.avg -= other.avg;
/* 212 */       this.worst -= other.worst;
/* 213 */       this.avg_length -= other.avg_length;
/* 214 */       this.best_length -= other.best_length;
/* 215 */       this.feasible_chroms -= other.feasible_chroms;
/* 216 */       this.pop_size -= other.pop_size;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void divide(double n) {
/* 225 */     this.best /= n;
/* 226 */     this.avg /= n;
/* 227 */     this.worst /= n;
/* 228 */     this.avg_length /= n;
/* 229 */     this.best_length /= n;
/* 230 */     this.feasible_chroms /= n;
/* 231 */     this.pop_size /= n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void square() {
/* 238 */     this.best *= this.best;
/* 239 */     this.avg *= this.avg;
/* 240 */     this.worst *= this.worst;
/* 241 */     this.avg_length *= this.avg_length;
/* 242 */     this.best_length *= this.best_length;
/* 243 */     this.feasible_chroms *= this.feasible_chroms;
/* 244 */     this.pop_size *= this.pop_size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sqrt() {
/* 251 */     this.best = Math.sqrt(this.best);
/* 252 */     this.avg = Math.sqrt(this.avg);
/* 253 */     this.worst = Math.sqrt(this.worst);
/* 254 */     this.avg_length = Math.sqrt(this.avg_length);
/* 255 */     this.best_length = Math.sqrt(this.best_length);
/* 256 */     this.feasible_chroms = Math.sqrt(this.feasible_chroms);
/* 257 */     this.pop_size = Math.sqrt(this.pop_size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Individual getBest() {
/* 265 */     return this.bestIndividual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBestIndex() {
/* 273 */     return this.bestIndex;
/*     */   }
/*     */   public Population getPopulation() {
/* 276 */     return this.population;
/*     */   }
/*     */ }


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\evolution\PopulationStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
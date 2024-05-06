import numpy as np
import random


class Individual:
    def __init__(self, genes):
        self.genes = np.array(genes)
        self.fitness = None
        self.operator_rates = {'crossover': 0.33, 'mutation': 0.33, 'recombination': 0.34}  # Suponemos 3 operadores

    def evaluate_fitness(self):
        # Implementa la función de evaluación de fitness específica para tu problema
        self.fitness = -np.sum(self.genes**2)  # Ejemplo simple

class Population:
    def __init__(self, size, gene_length):
        self.individuals = [Individual(np.random.rand(gene_length)) for _ in range(size)]

    def evolve(self, termination_condition):
        t = 0
        while not termination_condition(t, self.individuals):
            next_generation = []
            for ind in self.individuals:
                ind.evaluate_fitness()
                rates = ind.operator_rates
                delta = random.random()
                oper, arity = self.select_operator(rates)
                parents = [ind] + self.extra_selection(arity - 1)
                offspring = self.apply_operator(oper, parents)
                child = max(offspring, key=lambda x: x.fitness)

                if child.fitness > ind.fitness:
                    rates[oper] = (1.0 + delta) * rates[oper]
                else:
                    rates[oper] = (1.0 - delta) * rates[oper]
                
                self.normalize_rates(rates)
                child.operator_rates = rates
                next_generation.append(child)
            
            self.individuals = next_generation
            t += 1

    def select_operator(self, rates):
        # Extraer operadores y sus respectivas probabilidades
        operators, probabilities = zip(*rates.items())
        # Convertir las probabilidades a una lista de flotantes (asegurándose de que sumen 1)
        total = sum(probabilities)
        normalized_probabilities = [float(p) / total for p in probabilities]
        
        # Seleccionar operador basado en las probabilidades normalizadas
        chosen_operator = np.random.choice(operators, p=normalized_probabilities)
        arity = {'crossover': 2, 'mutation': 1, 'recombination': 2}[chosen_operator]
        return chosen_operator, arity

    def extra_selection(self, count):
        # Selecciona 'count' individuos adicionales de la población
        return random.sample(self.individuals, count)

    def apply_operator(self, operator, parents):
        offspring = []

        if operator == 'crossover':
            child = self.crossover(parents[0], parents[1])
            child.evaluate_fitness()  # Asegúrate de evaluar el fitness aquí
            offspring.append(child)
        elif operator == 'mutation':
            child = self.mutate(parents[0])
            child.evaluate_fitness()  # Asegúrate de evaluar el fitness aquí
            offspring.append(child)
        elif operator == 'recombination':
            # Asegúrate de que se aplican correctamente la función de recombinación
            children = self.recombination(parents[0], parents[1])
            offspring.extend(children)  # Añade ambos hijos a la lista de descendientes

        return offspring

    def crossover(self, p1, p2):
        child_genes = (p1.genes + p2.genes) / 2
        return Individual(child_genes)

    def mutate(self, ind):
        mutation_strength = 0.1
        mutated_genes = ind.genes + np.random.normal(0, mutation_strength, ind.genes.shape)
        return Individual(mutated_genes)
    
    def recombination(self, p1, p2):
        weight = np.random.rand()  # Peso para la mezcla
        child1_genes = weight * p1.genes + (1 - weight) * p2.genes
        child2_genes = (1 - weight) * p1.genes + weight * p2.genes

        # Crear nuevos individuos basados en los genes mezclados
        child1 = Individual(child1_genes)
        child2 = Individual(child2_genes)
        
        # Evaluar el fitness de los nuevos individuos
        child1.evaluate_fitness()
        child2.evaluate_fitness()

        return child1, child2

    def normalize_rates(self, rates):
        # Calcular la suma total de los valores numéricos en el diccionario de tasas
        total = sum(rates.values())  # Usa .values() para obtener sólo los valores del diccionario

        # Normalizar cada tasa dividiendo por el total
        for key in rates:
            rates[key] = rates[key] / total

def termination_condition(t, individuals):
    return t > 100  # Ejemplo de condición de terminación después de 100 generaciones

pop_size = 10
gene_length = 5
population = Population(pop_size, gene_length)
population.evolve(termination_condition)

# Imprimir los resultados finales
best_individual = max(population.individuals, key=lambda ind: ind.fitness)
print("Mejor individuo:", best_individual.genes, "Con fitness:", best_individual.fitness)

import numpy as np
import random

class Individual:
    def __init__(self, genes):
        self.genes = np.array(genes)  # candidate center location ci
        self.fitness = None
        self.sigma_squared = 1.0  # initial scale measure
        self.rates = np.random.dirichlet(np.ones(3))  # Asumiendo tres operadores genéticos

    def evaluate_fitness(self, data_points):
        # Calculate distances from all data points to the candidate center
        distances_squared = np.sum((data_points - self.genes) ** 2, axis=1)
        
        # Calculate weights using the current scale
        weights = np.exp(-distances_squared / (2 * self.sigma_squared))
        
        # Update scale measure sigma_squared for the next generation
        if np.sum(weights) > 0:
            self.sigma_squared = np.sum(weights * distances_squared) / np.sum(weights)
        
        # Calculate fitness
        self.fitness = np.sum(weights) / self.sigma_squared
        
        return self.fitness

    def update_rates(self, success):
        # A simple mechanism to update rates based on success or failure of operations
        if success:
            self.rates *= 1.1  # Boost rates
        else:
            self.rates *= 0.9  # Reduce rates
        self.rates /= np.sum(self.rates)  # Normalize rates to maintain them as a valid probability distribution

class Population:
    def __init__(self, size, gene_length, data):
        self.individuals = [Individual(np.random.rand(gene_length)) for _ in range(size)]
        self.data = data  # Almacenar los datos en la población para su uso en fitness

    def shuffle_population(self):
        random.shuffle(self.individuals)

    def crossover(self, p1, p2):
        child_genes = (p1.genes + p2.genes) / 2
        return Individual(child_genes)

    def mutate(self, individual):
        mutation_strength = np.sqrt(individual.sigma_squared)
        individual.genes += np.random.normal(0, mutation_strength, individual.genes.shape)
        return individual

    def generate_offspring(self):
        new_population = []
        self.shuffle_population()
        for i in range(0, len(self.individuals) - 1, 2):
            p1, p2 = self.individuals[i], self.individuals[i+1]
            c1, c2 = self.crossover(p1, p2)
            c1 = self.mutate(c1)
            c2 = self.mutate(c2)
            new_population.extend([c1, c2])
        self.individuals = new_population

    def apply_niching_strategy(self, data, max_iter):
        for _ in range(max_iter):
            self.shuffle_population()
            new_population = []
            for i in range(0, len(self.individuals) - 1, 2):
                p1, p2 = self.individuals[i], self.individuals[i+1]
                c1, c2 = self.crossover(p1, p2)
                c1 = self.mutate(c1)
                c2 = self.mutate(c2)

                # Evaluación del fitness con los datos actuales
                p1.evaluate_fitness(data)
                p2.evaluate_fitness(data)
                c1.evaluate_fitness(data)
                c2.evaluate_fitness(data)

                # Deterministic Crowding
                if np.linalg.norm(p1.genes - c1.genes) + np.linalg.norm(p2.genes - c2.genes) <= np.linalg.norm(p1.genes - c2.genes) + np.linalg.norm(p2.genes - c1.genes):
                    if p1.fitness < c1.fitness:
                        new_population.append(c1)
                        p1.update_rates(True)
                    else:
                        new_population.append(p1)
                        p1.update_rates(False)
                    if p2.fitness < c2.fitness:
                        new_population.append(c2)
                        p2.update_rates(True)
                    else:
                        new_population.append(p2)
                        p2.update_rates(False)
                else:
                    if p1.fitness < c2.fitness:
                        new_population.append(c2)
                        p1.update_rates(True)
                    else:
                        new_population.append(p1)
                        p1.update_rates(False)
                    if p2.fitness < c1.fitness:
                        new_population.append(c1)
                        p2.update_rates(True)
                    else:
                        new_population.append(p2)
                        p2.update_rates(False)
            self.individuals = new_population

    def evaluate_operators(self):
        for individual in self.individuals:
            individual.evaluate_fitness(self.data)
            success = random.choice([True, False])
            individual.update_rates(success)

    def evolve(self, generations):
        for _ in range(generations):
            self.generate_offspring()
            self.apply_niching_strategy()
            self.evaluate_operators()

class EvolutionaryProcess:
    def __init__(self, data, size, gene_length, generations):
        self.population = Population(size, gene_length, data)
        self.generations = generations

    def run(self):
        self.population.evolve(self.generations)
        return self.extract_final_prototypes()

    def extract_final_prototypes(self):
        # Extrae los mejores individuos según algún criterio, por ejemplo, los de mayor fitness
        sorted_individuals = sorted(self.population.individuals, key=lambda x: x.fitness, reverse=True)
        # Podría incluir algún proceso de refinamiento adicional si es necesario
        # return sorted_individuals[:10]  # Retornar los 10 mejores como ejemplo
        return sorted_individuals

# Usar el proceso evolutivo
data = np.random.rand(100, 10)  # ejemplo de datos
evolutionary_process = EvolutionaryProcess(data, 50, 10, 100)
evolutionary_process.run()

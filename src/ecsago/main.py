import numpy as np

from abc import ABC, abstractmethod


class GeneticOperator(ABC):
    @abstractmethod
    def apply(self, population):
        pass

class LinearCrossover(GeneticOperator):
    def apply(self, parent1, parent2):
        alpha = np.random.random(size=parent1.shape)
        return alpha * parent1 + (1 - alpha) * parent2

class GaussianMutation(GeneticOperator):
    def apply(self, individual, scale):
        return individual + np.random.normal(0, scale, size=individual.shape)

def calculate_distance(data_point, center):
    return np.linalg.norm(data_point - center, axis=1)

class Individual:
    def __init__(self, genome, sigma=0.1):
        self.genome = genome
        self.fitness = None
        self.sigma = sigma  # Scale measure for this individual

    def evaluate_fitness(self, data):
        distances = calculate_distance(data, self.genome)
        weights = np.exp(-np.square(distances) / (2 * np.square(self.sigma)))
        
        # To further reduce the effect of outliers, weights are binarized
        weights = np.where(weights > 0.3, weights, 0)
        
        # Update scale measure sigma for the next generation
        self.sigma = np.sqrt(np.sum(weights * np.square(distances)) / np.sum(weights))
        
        # Calculate fitness
        self.fitness = np.sum(weights / np.square(self.sigma))

class ECSAGO:
    def __init__(self, population_size, num_generations, data):
        self.population_size = population_size
        self.num_generations = num_generations
        self.data = data
        self.population = [Individual(np.random.random(data.shape[1])) for _ in range(population_size)]
        self.mutation_scale = 0.1  # Escala de mutación inicial, debería adaptarse dinámicamente

    def run(self):
        for generation in range(self.num_generations):
            for individual in self.population:
                individual.evaluate_fitness(self.data)
            self.population.sort(key=lambda x: x.fitness, reverse=True)
            new_population = []
            while len(new_population) < self.population_size:
                parent1, parent2 = np.random.choice(self.population, 2, replace=False)
                child_genome = LinearCrossover().apply(parent1.genome, parent2.genome)
                child_genome = GaussianMutation().apply(child_genome, self.mutation_scale)
                child = Individual(child_genome)
                child.evaluate_fitness(self.data)
                new_population.append(child)
            self.population = new_population
            print(f"Generation {generation}: Mejor aptitud = {self.population[0].fitness}")

# Ejemplo de uso
data = np.random.random((100, 10))  # 100 puntos de datos, 10 dimensiones
ecsago = ECSAGO(population_size=50, num_generations=100, data=data)
ecsago.run()

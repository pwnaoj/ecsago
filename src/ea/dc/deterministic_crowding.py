import matplotlib.pyplot as plt
import numpy as np
import random


class Individual:
    def __init__(self, genes):
        self.genes = genes

    def fitness(self, data_points):
        # Calculate distances from all data points to the candidate center
        distances_squared = np.sum((data_points - self.genes) ** 2, axis=1)
        
        # Calculate weights using the current scale
        weights = np.exp(-distances_squared / (2 * self.sigma_squared))

        # Binarizar los pesos
        weights = np.where(weights > 0.3, weights, 0)
        
        # Update scale measure sigma_squared for the next generation
        if np.sum(weights) > 0:
            self.sigma_squared = np.sum(weights * distances_squared) / np.sum(weights)
        
        # Calculate fitness
        self.fitness = np.sum(weights) / self.sigma_squared
        
        return self.fitness

    def mutate(self, individual):
        # Gaussian Mutation
        sigma_m = np.sqrt(individual.sigma_squared)  # Usar la medida de escala como base para la mutación
        mutation = np.random.normal(0, sigma_m, individual.genes.shape)
        individual.genes += mutation
        return individual

    @staticmethod
    def crossover(parent1, parent2):
        # Linear Crossover per Dimension (LCD)
        alphas = np.random.rand(parent1.genes.size)
        child1_genes = alphas * parent1.genes + (1 - alphas) * parent2.genes
        child2_genes = (1 - alphas) * parent1.genes + alphas * parent2.genes
        return Individual(child1_genes), Individual(child2_genes)

    @staticmethod
    def distance(ind1, ind2):
        # Función de distancia que debes definir según tu problema
        return np.linalg.norm(ind1 - ind2)

class Population:
    def __init__(self, size):
        self.individuals = [self.create_individual() for _ in range(size)]

    def create_individual(self):
        # Función para crear un nuevo individuo aleatorio        
        genes = [random.random() for _ in range(2)]  # Ejemplo con 2 genes
        return Individual(genes)

    def shuffle(self):
        random.shuffle(self.individuals)

    def visualize(self):
        # Función de visualización de la población
        x = [ind.genes[0] for ind in self.individuals]
        y = [ind.genes[1] for ind in self.individuals]
        fitness = [ind.fitness() for ind in self.individuals]

        plt.figure(figsize=(10, 6))
        plt.scatter(x, y, c=fitness, cmap='viridis', marker='o')
        plt.colorbar(label='Fitness')
        plt.xlabel('Gen 1')
        plt.ylabel('Gen 2')
        plt.title('Visualización de la Población Final')
        plt.show()

class DeterministicCrowding:
    def __init__(self, population_size, max_iter):
        self.population = Population(population_size)
        self.max_iter = max_iter

    def run(self):
        for _ in range(self.max_iter):
            new_population = []
            self.population.shuffle()
            for i in range(0, len(self.population.individuals), 2):
                parent1 = self.population.individuals[i]
                parent2 = self.population.individuals[i+1]
                child1, child2 = Individual.crossover(parent1, parent2)
                child1.mutate()
                child2.mutate()

                d1 = Individual.distance(parent1, child1) + Individual.distance(parent2, child2)
                d2 = Individual.distance(parent1, child2) + Individual.distance(parent2, child1)

                if d1 <= d2:
                    if parent1.fitness() < child1.fitness():
                        new_population.append(parent1)
                    else:
                        new_population.append(child1)

                    if parent2.fitness() < child2.fitness():
                        new_population.append(parent2)
                    else:
                        new_population.append(child2)
                else:
                    if parent1.fitness() < child2.fitness():
                        new_population.append(parent1)
                    else:
                        new_population.append(child2)

                    if parent2.fitness() < child1.fitness():
                        new_population.append(parent2)
                    else:
                        new_population.append(child1)

            self.population.individuals = new_population
        return self.population

# Ejemplo de uso:
# def main():
#     population_size = 100
#     max_iter = 1000
#     dc = DeterministicCrowding(population_size, max_iter)
#     final_population = dc.run()
#     final_population.visualize()
#     # Hacer algo con la población final

# if __name__ == "__main__":
#     main()

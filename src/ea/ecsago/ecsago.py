import matplotlib.pyplot as plt
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

class Individual:
    def __init__(self, genome, sigma=0.1):
        self.genome = genome
        self.fitness = None
        self.sigma = sigma

    @staticmethod
    def calculate_distance(data_point, center):
        return np.linalg.norm(data_point - center, axis=1)

    def evaluate_fitness(self, data):
        distances = Individual.calculate_distance(data, self.genome)
        weights = np.exp(-np.square(distances) / (2 * np.square(self.sigma)))
        
        # Binarizar los pesos
        weights = np.where(weights > 0.3, weights, 0)

        sum_weights = np.sum(weights)
        if sum_weights > 0:
            # Si hay pesos válidos, calcula sigma
            self.sigma = np.sqrt(np.sum(weights * np.square(distances)) / sum_weights)
            # Calcula la aptitud
            self.fitness = np.sum(weights / np.square(self.sigma))
        else:
            # Si todos los pesos son cero, asigna una aptitud mala, como por ejemplo 0 o inf
            # Dependiendo de si quieres minimizar o maximizar la aptitud
            self.fitness = 0  # o np.inf

class ECSAGO:
    def __init__(self, population_size, num_generations, data_path):
        self.population_size = population_size
        self.num_generations = num_generations
        self.data = self.load_data_from_file(data_path) 
        self.population = [Individual(np.random.random(self.data.shape[1])) for _ in range(population_size)]
        self.mutation_scale = 0.1  # Escala de mutación inicial, debería adaptarse dinámicamente

    @staticmethod
    def load_data_from_file(file_path):
        with open(file_path, 'r') as file:
            # Omitir la primera línea que contiene la cantidad de datos y la dimensión
            next(file)
            # Leer los datos y convertirlos en un array de NumPy
            data = np.array([list(map(float, line.split())) for line in file])
        return data

    def plot_clusters(self):
        # Me aseguro de que 'self.data' es bidimensional
        if self.data.shape[1] != 2:
            raise ValueError("La función de visualización sólo puede manejar datos bidimensionales.")

        # Dibuja cada punto de datos
        plt.scatter(self.data[:, 0], self.data[:, 1], color='gray', label='Datos')

        # Dibuja los centros de los clusters
        for individual in self.population:
            plt.scatter(individual.genome[0], individual.genome[1], color='red', edgecolor='black', s=100, label='Centro del Cluster')

        plt.xlabel('Feature 1')
        plt.ylabel('Feature 2')
        plt.title('Visualización de Clusters ECSAGO')
        plt.legend()
        plt.show()

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


if __name__ == "__main__":    
    # Cargar datos del archivo
    file_path = 'src/datasets/Five_Clust.txt'  # Actualizar con la ruta correcta
    ecsago = ECSAGO(population_size=200, num_generations=30, data_path=file_path)  # Inicializar con datos vacíos
    ecsago.data = ecsago.load_data_from_file(file_path)  # Cargar datos en el algoritmo
    ecsago.run()  # Ejecutar el algoritmo
    ecsago.plot_clusters()  # Visualizar los clusters

import matplotlib.pyplot as plt
import numpy as np
import random

class Individual:
    def __init__(self, genes):
        self.genes = np.array(genes)  # candidate center location ci
        self.fitness = None
        self.sigma_squared = 0.1  # initial scale measure
        self.rates = np.random.dirichlet(np.ones(3))  # Asumiendo tres operadores genéticos

    def evaluate_fitness(self, data_points):
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

    def update_rates(self, success):
        # A simple mechanism to update rates based on success or failure of operations
        if success:
            self.rates *= 1.1  # Boost rates
        else:
            self.rates *= 0.9  # Reduce rates
        self.rates /= np.sum(self.rates)  # Normalize rates to maintain them as a valid probability distribution

class Population:
    def __init__(self, size, data):
        self.individuals = [Individual(genes) for genes in data]
        self.data = data  # Almacenar los datos en la población para su uso en fitness
        self.size = size

    def shuffle_population(self):
        random.shuffle(self.individuals)

    def crossover(self, p1, p2):
        # Linear Crossover per Dimension (LCD)
        # Generar coeficientes alpha diferentes para cada dimensión
        alphas = np.random.rand(p1.genes.size)
        child1_genes = alphas * p1.genes + (1 - alphas) * p2.genes
        child2_genes = (1 - alphas) * p1.genes + alphas * p2.genes
        return Individual(child1_genes), Individual(child2_genes)

    def mutate(self, individual):
        # Gaussian Mutation
        sigma_m = np.sqrt(individual.sigma_squared)  # Usar la medida de escala como base para la mutación
        mutation = np.random.normal(0, sigma_m, individual.genes.shape)
        individual.genes += mutation
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

    def deterministic_crowding(self, data, max_iter):
        for _ in range(max_iter):
            self.shuffle_population()
            new_population = []
            for i in range(0, int(self.size / 2), 2):
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
        self.deterministic_crowding(data=self.data, max_iter=generations)

    def visualize(self):
        plt.figure(figsize=(10, 6))
        # Plot de los puntos de datos
        plt.scatter(self.data[:, 0], self.data[:, 1], c='blue', label='Data Points', alpha=0.5)
        # Plot de los centros de los clústeres
        for ind in self.individuals:
            plt.scatter(ind.genes[0], ind.genes[1], c='red', marker='x', s=100, label='Centros' if 'Centros' not in plt.gca().get_legend_handles_labels()[1] else "")
        plt.title('Visualización de Clustering ECSAGO')
        plt.xlabel('Dimensión 1')
        plt.ylabel('Dimensión 2')
        plt.legend()
        plt.grid(True)
        plt.show()

class EvolutionaryProcess:
    def __init__(self, data, size, generations):
        self.population = Population(size, data)
        self.generations = generations

    def run(self):
        self.population.evolve(generations=self.generations)
        self.population.visualize()
        return self.extract_final_prototypes()

    def extract_final_prototypes(self):
        # Extrae los mejores individuos según algún criterio, por ejemplo, los de mayor fitness
        sorted_individuals = sorted(self.population.individuals, key=lambda x: x.fitness, reverse=True)
        # Podría incluir algún proceso de refinamiento adicional si es necesario
        return sorted_individuals[:5]  # Retornar los 10 mejores como ejemplo
        # return sorted_individuals

def load_data_from_file(file_path):
    with open(file_path, 'r') as file:
        # Omitir la primera línea que contiene la cantidad de datos y la dimensión
        next(file)
        # Leer los datos y convertirlos en un array de NumPy
        data = np.array([list(map(float, line.split())) for line in file])
    return data

# Suposiciones de los datos y parámetros
# data_points = np.random.rand(100, 5)  # 100 puntos de datos, 5 dimensiones
file_path = 'src/datasets/Five_Clust.txt'  # Actualizar con la ruta correcta
data_points = load_data_from_file(file_path)
size = 100  # Tamaño de la población
generations = 30  # Número de generaciones

# Crear y ejecutar el proceso evolutivo
evolution_process = EvolutionaryProcess(data=data_points, size=size, generations=generations)
final_prototypes = evolution_process.run()

# Imprimir los resultados finales
# for prototype in final_prototypes:
#     print("Genes:", prototype.genes, "Fitness:", prototype.fitness)

import java.util.*;
class Aresta {
    int destino;
    int peso; // Peso da aresta
    String dificuldade;
    String categoria;

    public Aresta(int destino, int peso, String dificuldade, String categoria) {
        this.destino = destino;
        this.peso = peso;
        this.dificuldade = dificuldade;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Destino: " + destino + ", Dificuldade: " + dificuldade + ", Categoria: " + categoria + ", Peso: " + peso;
    }
}

class Grafo {
    public int vertices; // Número de vértices
    public List<List<Aresta>> listaAdjacencia; // Lista de adjacência de arestas

    // Construtor do grafo
    public Grafo(int vertices) {
        this.vertices = vertices;
        listaAdjacencia = new ArrayList<>(vertices);

        // Inicializa a lista de adjacência para cada vértice
        for (int i = 0; i < vertices; i++) {
            listaAdjacencia.add(new LinkedList<>());
        }
    }

    // Método para adicionar uma aresta no grafo com peso, dificuldade e categoria
    public void adicionarAresta(int origem, int destino, int peso, String dificuldade, String categoria) {
        listaAdjacencia.get(origem).add(new Aresta(destino, peso, dificuldade, categoria)); 
    }

    // Método para exibir uma tabela com todas as arestas do grafo
    public void exibirTabelaArestas() {
        for (int origem = 0; origem < vertices; origem++) {
            for (Aresta aresta : listaAdjacencia.get(origem)) {
                System.out.printf("%-10d %-10d %-10d %-15s %-15s%n", origem, aresta.destino, aresta.peso, aresta.dificuldade, aresta.categoria);
            }
        }
    }

    // Método para encontrar o menor caminho usando o algoritmo de Dijkstra
    public void encontrarMenorCaminho(int inicio) {
        int[] distancias = new int[vertices];
        int[] antecessores = new int[vertices];
        boolean[] visitado = new boolean[vertices];
    
        Arrays.fill(distancias, Integer.MAX_VALUE); // Inicializa todas as distâncias como infinito
        Arrays.fill(antecessores, -1); // Inicializa todos os antecessores como -1
        distancias[inicio] = 0; // Distância do ponto inicial para ele mesmo é 0
    
        PriorityQueue<int[]> fila = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Fila de prioridade para os vértices
        fila.add(new int[] { inicio, 0 }); // Adiciona o vértice inicial
    
        while (!fila.isEmpty()) {
            int[] atual = fila.poll(); // Vértice com a menor distância
            int verticeAtual = atual[0];
    
            if (visitado[verticeAtual]) continue;
            visitado[verticeAtual] = true;
    
            // Para cada aresta adjacente
            for (Aresta aresta : listaAdjacencia.get(verticeAtual)) {
                int vizinho = aresta.destino;
                int peso = aresta.peso;
    
                // Se a distância do vértice atual + peso da aresta for menor que a distância conhecida
                if (distancias[verticeAtual] + peso < distancias[vizinho]) {
                    distancias[vizinho] = distancias[verticeAtual] + peso;
                    antecessores[vizinho] = verticeAtual;
                    fila.add(new int[] { vizinho, distancias[vizinho] });
                }
            }
        }
    
        // Imprime as distâncias calculadas e o caminho apenas para os vértices acessíveis
        for (int i = 0; i < vertices; i++) {
            if (distancias[i] == Integer.MAX_VALUE) {
                continue; // Ignora os vértices inacessíveis
            }
    
            // Contar as dificuldades
            int[] contagemDificuldades = contarDificuldades(antecessores, i);
            
            System.out.print("Menor caminho até o vértice " + i + ": ");
            System.out.print("(Distância: " + distancias[i] + ") - Caminho: ");
            imprimirCaminho(antecessores, i);
            System.out.println();
            
            // Mostra a contagem de dificuldades
            System.out.println("Quantidade de perguntas fáceis: " + contagemDificuldades[0]);
            System.out.println("Quantidade de perguntas médias: " + contagemDificuldades[1]);
            System.out.println("Quantidade de perguntas difíceis: " + contagemDificuldades[2]);
        }
    }
    
    // Método para contar as dificuldades no caminho
    private int[] contarDificuldades(int[] antecessores, int destino) {
        int[] contagem = new int[3]; // [fáceis, médias, difíceis]
        
        while (destino != -1) {
            int antecessor = antecessores[destino];
            if (antecessor != -1) {
                for (Aresta aresta : listaAdjacencia.get(antecessor)) {
                    if (aresta.destino == destino) {
                        switch (aresta.dificuldade) {
                            case "Fácil":
                                contagem[0]++;
                                break;
                            case "Médio":
                                contagem[1]++;
                                break;
                            case "Difícil":
                                contagem[2]++;
                                break;
                        }
                    }
                }
            }
            destino = antecessor; // Avança para o antecessor
        }
        
        return contagem;
    }
    

    // Método para imprimir o caminho a partir do vetor de antecessores
    private void imprimirCaminho(int[] antecessores, int destino) {
        if (destino == -1) return;
        imprimirCaminho(antecessores, antecessores[destino]);
        System.out.print(destino + " ");
    }
}
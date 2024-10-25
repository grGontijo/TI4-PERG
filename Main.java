import java.util.*;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(31); // Número total de vértices de 0 a 30

        // Adicionando arestas com dificuldade e categoria
        grafo.adicionarAresta(0, 1, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(1, 2, 2, "Médio", "História");
        grafo.adicionarAresta(1, 4, 3, "Difícil", "Ciência");
        grafo.adicionarAresta(1, 3, 1, "Fácil", "Arte");
        grafo.adicionarAresta(2, 3, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(3, 5, 4, "Difícil", "Geografia");
        grafo.adicionarAresta(3, 6, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(3, 7, 2, "Médio", "História");
        grafo.adicionarAresta(4, 8, 4, "Difícil", "Ciência");
        grafo.adicionarAresta(4, 9, 1, "Fácil", "Arte");
        grafo.adicionarAresta(5, 10, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(10, 12, 3, "Difícil", "Geografia");
        grafo.adicionarAresta(12, 13, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(6, 11, 2, "Médio", "História");
        grafo.adicionarAresta(11, 13, 3, "Difícil", "Ciência");
        grafo.adicionarAresta(7, 13, 1, "Fácil", "Arte");
        grafo.adicionarAresta(8, 14, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(9, 16, 4, "Difícil", "Geografia");
        grafo.adicionarAresta(14, 15, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(15, 23, 2, "Médio", "História");
        grafo.adicionarAresta(16, 18, 4, "Difícil", "Ciência");
        grafo.adicionarAresta(17, 20, 1, "Fácil", "Arte");
        grafo.adicionarAresta(18, 19, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(20, 21, 4, "Difícil", "Geografia");
        grafo.adicionarAresta(19, 22, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(13, 23, 2, "Médio", "História");
        grafo.adicionarAresta(23, 24, 4, "Difícil", "Ciência");
        grafo.adicionarAresta(24, 25, 1, "Fácil", "Arte");
        grafo.adicionarAresta(21, 22, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(22, 26, 4, "Difícil", "Geografia");
        grafo.adicionarAresta(26, 25, 1, "Fácil", "Esporte");
        grafo.adicionarAresta(25, 27, 2, "Médio", "História");
        grafo.adicionarAresta(25, 28, 4, "Difícil", "Ciência");
        grafo.adicionarAresta(27, 29, 1, "Fácil", "Arte");
        grafo.adicionarAresta(28, 29, 2, "Médio", "Cultura Pop");
        grafo.adicionarAresta(29, 30, 4, "Difícil", "Geografia");

        // Exibir tabela de arestas
        System.out.println("Tabela de Arestas:");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s%n", "Origem", "Destino", "Peso", "Dificuldade", "Categoria");
        grafo.exibirTabelaArestas();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nDigite o vértice de partida (ou 31 para sair): ");
            int verticePartida = scanner.nextInt();

            if (verticePartida == 31) {
                System.out.println("Encerrando o programa...");
                break;
            }

            if (verticePartida < 0 || verticePartida >= 31) {
                System.out.println("Vértice inválido. Por favor, insira um valor entre 0 e 30.");
            } else {
                System.out.println("Menor caminho a partir do vértice " + verticePartida + ":");
                grafo.encontrarMenorCaminho(verticePartida);
            }
        }

        scanner.close();
    }
}

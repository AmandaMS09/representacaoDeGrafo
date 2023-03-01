import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Scanner scGrafo;
		
		System.out.println("Representacao de grafo\n");

		while(true) {
			System.out.print("Nome do arquivo(Ex.: grafo.txt): ");
			File arqGrafo = new File(scan.nextLine());
			try {
				scGrafo = new Scanner(arqGrafo);
				break;
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo não encontrado.\n");
			}
		}
		
		int qtdVertices = scGrafo.nextInt();
		int qtdArestas = scGrafo.nextInt();
		int[] origem = new int[qtdArestas];
		int[] destino = new int[qtdArestas];
		int[] pointer = new int[qtdVertices + 1];
		int[] grau = new int[qtdVertices];
		int vertice;
		
		for(int i = 1; i <= qtdArestas; i++) {
			scGrafo.nextLine();
			
			vertice = scGrafo.nextInt();
			origem[i-1] = vertice;
			grau[vertice-1]++;
			if(pointer[vertice-1] == 0) {
				pointer[vertice-1] = i;
			}

			vertice = scGrafo.nextInt();
			destino[i-1] = vertice;
			grau[vertice-1]++;

		}
		
		int cont = qtdVertices;
		do { 
			pointer[cont] = qtdArestas + 1;
			cont--;
		} while(pointer[cont] == 0);
		
		int inpVertice;
		do {
			System.out.print("\nEscolha um vertice valido desse grafo(1 ate " + qtdVertices + "): ");
			inpVertice = Integer.parseInt(scan.nextLine());
		} while((inpVertice < 1) || (inpVertice > qtdVertices));
		
		// Grau de saída
		int saida;
		saida = pointer[inpVertice] - pointer[inpVertice-1];
		System.out.println("Grau de saida: " + saida);
		// Grau de entrada
		int entrada;
		entrada = grau[inpVertice-1] - saida;
		System.out.println("Grau de entrada: " + entrada);
		// Conjunto de sucessores
		System.out.print("Sucessores: | ");
		for(int i = pointer[inpVertice-1]; i < pointer[inpVertice]; i++) {
			System.out.print(destino[i-1] + " | ");
		}
		// Conjunto de predecessores
		int qtdPred = 0;
		System.out.print("\nPredecessores: | ");
		for(int i = 0; i < destino.length; i++) {
			if(destino[i] == inpVertice) {
				System.out.print(origem[i] + " | ");
				qtdPred++;
			}
			if(qtdPred == entrada) {
				break;
			}
		}
		
		scGrafo.close();
		scan.close();
		
	}

}

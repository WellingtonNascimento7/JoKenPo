package view;

import java.util.concurrent.Semaphore;
import controller.*;

public class Principal {
public static void main(String[] args) {
		Jogador jogA; 
		Jogador jogB;
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		//gera um jogador para cada time e inicializa a Thread
		for(int idjog = 1; idjog <= 5; idjog++){
			
			jogA = new Jogador(idjog, 0, 'A');
			jogB = new Jogador(idjog, 0, 'B');
			Thread tjogador = new ThreadPartida(jogA, jogB, semaforo);
			tjogador.start();
		}
		
	}
}

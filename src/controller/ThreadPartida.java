package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadPartida extends Thread {

	private Jogador jogA;
	private Jogador jogB;
	private Semaphore semaforo;	
	private static int cont = 0;
	private String[] opc = { "Pedra", "Papel", "Tesoura" };
	private static int[] vit = new int[2];

	public ThreadPartida(Jogador jogA, Jogador jogB, Semaphore semaforo) {
		this.jogA = jogA;
		this.jogB = jogB;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		
		// --------P(acquire)-----------
		try {
			semaforo.acquire();
			Batalha();
			
		} catch (InterruptedException e) { 
			e.printStackTrace();  
			
		} finally { 
		// --------V (release)----------
			
			semaforo.release();			
			Duelo();
			if(cont == 5){
				TimeVitoria();
			}
		}

	}

	private void Batalha() {
		Random r = new Random();
		int jog1, jog2;		
		
		//para corrigir o sleep anterior
		try {
			sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n---------Duelo entre Jogador: " + jogA.getid() + " do time A e Jogador: " + jogB.getid()
				+ " Do time B-----------\n");
		
		do {
			//Sleep para as batalhas não passarem tão rapido
			try {
				sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Gera as jogadas aleatoriamene
			jog1 = r.nextInt(3);
			jog2 = r.nextInt(3);
			
			//Switch que funciona atraves das opções geradas acima e com base nelas valida quem venceu a batalha			
			switch (jog1) {
			case 0:
				switch (jog2) {
				case 0:
					System.out.println(
							"Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2] + " ==> Resultado: Empate");
					break;
				case 1:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador B ganhou");
					jogB.setnvitorias(jogB.getnvitorias() + 1);
					break;
				case 2:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador A ganhou");
					jogA.setnvitorias(jogA.getnvitorias() + 1);
					break;
				default:
					System.out.println("Erro");
				}
				break;
			case 1:
				switch (jog2) {
				case 0:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador A ganhou");
					jogA.setnvitorias(jogA.getnvitorias() + 1);
					break;
				case 1:
					System.out.println(
							"Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2] + " ==> Resultado: Empatou");
					break;
				case 2:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador B ganhou");
					jogB.setnvitorias(jogB.getnvitorias() + 1);
					break;
				default:
					System.out.println("Erro");
				}
				break;
			case 2:
				switch (jog2) {
				case 0:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador B ganhou");
					jogB.setnvitorias(jogB.getnvitorias() + 1);
					break;
				case 1:
					System.out.println("Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2]
							+ " ==> Resultado: Jogador A ganhou");
					jogA.setnvitorias(jogA.getnvitorias() + 1);
					break;
				case 2:
					System.out.println(
							"Jogador time A: " + opc[jog1] + "| Jogador time B: " + opc[jog2] + " ==> Resultado: Empate");
					break;
				default:
					System.out.println("Erro");
				}
				break;
			default:
				System.out.println("Erro");

			}
			//um Do While que para quando um dos jogadores alcança 3 vitorias
		} while (jogA.getnvitorias() < 3 && jogB.getnvitorias() < 3);
		

	}

		//este metodo valida de que time sera atribuida a vitorias do duelo feito no metodo batalha
	private void Duelo() {
		if (jogA.getnvitorias() == 3) {
			vit[0]++;
			System.out.println("O vencedor do Duelo foi o Jogador:" + jogA.getid() + " do Time A");
		} else {
			vit[1]++;
			System.out.println("O vencedor do Duelo foi o Jogador:" + jogB.getid() + " do Time B");

		}
		//Contador que incrementa a cada duelo realizado
		cont++;
	}
	
	//Validação para exibir o time vencedor
	private void TimeVitoria() {
		if(vit[0]>vit[1]) {
			System.out.println("\n\n****************O time vitorioso foi o: Time A com "+vit[0]
					+" Duelos Vencidos****************");
		}else {
			System.out.println("\n\n****************O time vitorioso foi o Time B com "+vit[1]
					+" Duelos Vencidos****************");

		}
	}

	
}

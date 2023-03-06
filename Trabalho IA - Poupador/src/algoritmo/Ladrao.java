package algoritmo;


import java.awt.Point;

import controle.Constantes;

public class Ladrao extends ProgramaLadrao {
	public int[][] mapaIndividual = new int[30][30];
	public int paciencia = 2;
	//Tentativas de seguir um padrão
	public int[] padroes = new int[5];
	
	Point posicaoAtual; 
	public int acao() {
		posicaoAtual = sensor.getPosicao();
		mapaIndividual[posicaoAtual.x][posicaoAtual.y]++;
		
		int escolha = escolhaSimples();
		return escolha;
	}
	public int escolhaSimples() {
		//startando com valores que não existem
		int codigoDoPiso = -3;
		int tentativa = -1;
		/*
		 * Antes de uma tentativa aleatoria,
		 * tente procurar pelo padrão de qual possibilidade é melhor
		 * */
		int padraoMaisRepetido = 0;
		for(int i = 1; i < padroes.length;i++) {
			if(padroes[i] > padraoMaisRepetido) {
				padraoMaisRepetido = padroes[i];
				tentativa = i;
			}
		}
		//Verifico se alguma alteração ocorreu
		if(tentativa != -1) {
			//Testando se alguum padrão acerta novamente
			if(codigoDoPiso(tentativa) == 0) {
				padroes[tentativa]++;
				return tentativa;
			}
			//Mata o padrão que estava sendo criado
			else {
				padroes[tentativa] = 0;
			}
		}

		tentativa = (int) (Math.random() * 5);
		padroes[tentativa]++;
		
		if(codigoDoPiso(tentativa) != 0 || tentativa == 0) {
			escolhaSimples();
		}	
		
		return tentativa;
	}
	/*
	 * Printa na tela o mapa percorrido até dado momento pelo agente
	 * 
	 * */
	public boolean pesquisarPor(int codigo) {
		int[] areaVisivel = sensor.getVisaoIdentificacao();
		for(int ponto : areaVisivel) {
			if(ponto == codigo) {
				return true;
			}
		}
		return false;
	}
	/*
	 * Testa o que tem na posição pretendida a ir do agente e diz o que tem lá
	 * 
	 * */
	public int codigoDoPiso(int direcao) {
		Point pos = posicaoAtual.getLocation();
		int[] areaVisivel = sensor.getVisaoIdentificacao();
		int codigo = 0;
		if(direcao == 1) 
			codigo = areaVisivel[7];
		if(direcao == 2)
			codigo = areaVisivel[16];
		if(direcao == 3)
			codigo = areaVisivel[12];
		if(direcao == 4)
			codigo = areaVisivel[16];
		
		return codigo;
	}
	/*
	 * Printa na tela o mapa percorrido até dado momento pelo agente
	 * 
	 * */
	public void printarMapaIndividual() {
		String point = "";
		for(int i = 0; i< mapaIndividual.length;i++) {
			for(int j = 0; j< mapaIndividual.length;j++) {
				point+="["+mapaIndividual[i][j]+"] ";
			}
			System.out.println(point);
			point = "";
		}
	}
}
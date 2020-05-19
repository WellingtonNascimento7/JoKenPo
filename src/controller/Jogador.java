package controller;

public class Jogador {
	private int id;
	private int nvitorias;
	private char time;
	
	public Jogador() {}
	
	public Jogador (int id, int nvitorias, char time){
		this.id = id;
		this.nvitorias = nvitorias;
		this.time = time;
	}
	
	public int getid() {
		return id;	
	}
	public int getnvitorias() {
		return nvitorias;
	}
	public char gettime() {
		return time;
	}
	

	
	public void setid(int id) {
		this.id = id;
	}
	public void setnvitorias(int nvitorias) {
		this.nvitorias = nvitorias;
	}
	public void settime(char time) {
		this.time = time;
	}
	
}

package it.polito.tdp.imdb.model;

public class DirectorAdiacente implements Comparable <DirectorAdiacente> {
	Director d;
	int peso;
	public DirectorAdiacente(Director d, int peso) {
		super();
		this.d = d;
		this.peso = peso;
	}
	public Director getD() {
		return d;
	}
	public void setD(Director d) {
		this.d = d;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(DirectorAdiacente o) {
		// TODO Auto-generated method stub
		return o.getPeso()-this.peso;
	}
	@Override
	public String toString() {
		return  d + " con peso " + peso;
	}
	
	
	

}

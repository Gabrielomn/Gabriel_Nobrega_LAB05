package main;

public class Compra {
	
	String data;
	IdProduto id;
	double valor;
	
	public Compra(String data, IdProduto id, double valor) {
		this.data = data.replaceAll("/", "-");
		this.id = id;
		this.valor = valor;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public String toString() {
		return this.id.getNome() + " - " + this.data;
	}
}

package main;

/**
 * classe que representa compra.
 * @author gabrielomn
 *
 */
public class Compra {
	
	String data;
	IdProduto id;
	double valor;
	
	/**
	 * construtor de compra
	 * @param data
	 * @param id
	 * @param valor
	 */
	public Compra(String data, IdProduto id, double valor) {
		this.data = data.replaceAll("/", "-");
		this.id = id;
		this.valor = valor;
	}
	
	/**
	 * retorna o valor dessa compra
	 * @return
	 */
	public double getValor() {
		return this.valor;
	}
	
	/**
	 * retorna uma representação textual de compra
	 */
	public String toString() {
		return this.id.getNome() + " - " + this.data;
	}
}

package main;

/**
 * interface para produto
 * @author gabrielomn
 *
 */
public interface Produto extends Comparable<Produto> {

	public void setPreco(double novoValor);

	public int compareTo(Produto outro);

	public String getDescricao();

	public String getNome();
	
	public double getValor();
}

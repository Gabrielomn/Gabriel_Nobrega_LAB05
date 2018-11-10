package main;

/**
 * classe que representa compra.
 * @author gabrielomn
 *
 */
public class Compra {
	
	private String data;
	private IdProduto id;
	private double valor;
	private String nomeCliente;
	private String nomeFornecedor;
	/**
	 * construtor de compra
	 * @param data
	 * @param id
	 * @param valor
	 */
	public Compra(String nome, String fornecedor, String data, IdProduto id, double valor) {
		this.data = data;
		this.id = id;
		this.valor = valor;
		this.nomeCliente = nome;
		this.nomeFornecedor = fornecedor;
	}
	
	/**
	 * retorna o valor dessa compra
	 * @return
	 */
	public double getValor() {
		return this.valor;
	}
	
	public String representacaoPorCliente() {
		return this.nomeCliente + ", " + this.nomeFornecedor + ", " + this.id.getDescricao() + ", " + this.data;
	}
	
	public String representacaoPorFornecedor() {
		return this.nomeFornecedor + ", " + this.nomeCliente + ", " + this.id.getDescricao() + ", " + this.data;
	}
	
	public String representacaoPorData() {
		return this.data + ", " + this.nomeCliente + ", " + this.nomeFornecedor + ", " + this.id.getDescricao();
	}
	
	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public String getDescricao() {
		return this.id.getDescricao();
	}
	
	public String getNomeFornecedor() {
		return this.nomeFornecedor;
	}
	
	public String getData() {
		return this.data;
	}
	/**
	 * retorna uma representação textual de compra
	 */
	public String toString() {
		return this.id.getNome() + " - " + this.data.replaceAll("/", "-");
	}
}

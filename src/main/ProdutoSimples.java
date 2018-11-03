package main;

/**
 * Entidade que representa produto
 * @author gabriel
 *
 */
public class ProdutoSimples implements Produto{
	
	private String nome;
	private String descricao;
	private double preco;
	
	/**
	 * construtor de produto
	 * @param nome do objeto
	 * @param descricao do objeto
	 * @param preco do objeto
	 */
	public ProdutoSimples(String nome, String descricao, double preco) {
		if (nome.equals("") || nome == null) {
			throw new IllegalArgumentException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		}
		else if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		}
		if (preco < 0) {
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		}
		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	/**
	 * seta preco, caso preco seja abaixo de 0 lança exceção
	 * @param preco
	 */
	public void setPreco(double preco) {
		if(preco < 0) {
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		}
		this.preco = preco;
	}

	/**
	 * retorna uma String que representa o objeto
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.descricao + " - R$" + String.format("%.2f",this.preco);
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * equals de objeto, leva em conta o nome e a descricao
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoSimples other = (ProdutoSimples) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public int compareTo(Produto outro) {
		
		return this.nome.compareTo(outro.getNome());
	}

	public double getValor() {
		return this.preco;
	}
}

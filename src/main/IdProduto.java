package main;

/**
 * classe que é usada para identificar o produto, seu maior proposito e servir de identificador unico
 * para produto no hashMap
 * @author gabrielomn
 *
 */
public class IdProduto {

	private String nome;
	private String descricao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdProduto other = (IdProduto) obj;
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

	/**
	 * construtor de IdProduto
	 * @param nome
	 * @param descricao
	 */
	public IdProduto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * representação textual do id
	 */
	public String toString() {
		return this.nome + " - " + this.descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public String getNome() {
		return this.nome;
	}
}

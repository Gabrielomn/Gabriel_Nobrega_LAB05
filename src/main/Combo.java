package main;

import java.util.HashSet;
import java.util.Set;

/**
 * entidade que representa produto
 * @author gabrielomn
 *
 */
public class Combo implements Produto {

	private String nome;
	private String descricao;
	private double fator;
	private ProdutoSimples[] produtos;
	
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
		Combo other = (Combo) obj;
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
	 * construtor de Combo
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 */
	public Combo(String nome, String descricao, double fator, ProdutoSimples[] produtos) {
		this.nome = nome;
		this.descricao = descricao;
		this.fator = fator;
		this.produtos = produtos;
	}
	
	/**
	 * @return uma representação textual do combo
	 */
	public String toString() {
		return this.nome + " - " + this.descricao + " - R$" + this.getRepresentacaoPreco();
	}
	
	/**
	 * retorna o preco no formato desejado, com duas casas decimais.
	 * @return
	 */
	private String getRepresentacaoPreco() {
		double soma = this.getValor();
		return String.format("%.2f", soma);
		
	}
	
	/**
	 * soma o valor de todos os produtos no combo.
	 */
	public double getValor() {
		double soma = 0;
		for(ProdutoSimples p: this.produtos) {
			soma+= p.getValor();
		}
		return soma * (1 - this.fator);
	}
	
	/**
	 * muda o fator de desconto do combo.
	 */
	@Override
	public void setPreco(double novoValor) {
		this.fator = novoValor;		
	}

	@Override
	public int compareTo(Produto outro) {

		return this.getNome().compareTo(outro.getNome());
	}

	@Override
	public String getDescricao() {
		
		return this.descricao;
	}

	@Override
	public String getNome() {
		
		return this.nome;
	}

}

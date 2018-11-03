package main;

import java.util.HashSet;
import java.util.Set;

public class Combo implements Produto {

	private String nome;
	private String descricao;
	private double fator;
	private ProdutoSimples[] produtos;
	
	public Combo(String nome, String descricao, double fator, ProdutoSimples[] produtos) {
		this.nome = nome;
		this.descricao = descricao;
		this.fator = fator;
		this.produtos = produtos;
	}
	
	public String toString() {
		return this.nome + " - " + this.descricao + " - " + this.getValor();
	}
	
	private String getValor() {
		double soma = 0;
		for(ProdutoSimples p: this.produtos) {
			soma+= p.getValor();
		}
		return String.format("%.2f", soma *(1 - this.fator));
		
	}

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

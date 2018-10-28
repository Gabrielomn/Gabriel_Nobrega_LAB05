package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fornecedor implements Comparable<Fornecedor> {
	
	private String nome;
	private String email;
	private String numero;
	private List<Produto> produtos;
	
	public Fornecedor(String nome, String email, String numero) {
		this.nome = nome;
		this.email = email;
		this.numero = numero;
		this.produtos = new ArrayList<>();
	}
	
	public void cadastraProduto(String nome, String descricao, double valor) throws ProdutoJaCadastradoException {
		Produto produtoASerAdcionado = new Produto(nome, descricao,valor);
		if (this.produtos.contains(produtoASerAdcionado)) {
			throw new ProdutoJaCadastradoException();
		}
		else {
			this.produtos.add(produtoASerAdcionado);
		}
	}
	
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.numero;
	}
	
	
	public String imprimeProdutos() {
		String saida ="";
		Collections.sort(this.produtos);
		for(Produto p: this.produtos) {
			saida += this.nome + " - " + p + "|";
		}
		
		return saida;
	}
	
	public String exibeProduto(String nome, String descricao) throws ProdutoNaoCadastradoException {
		
		Produto produtoProcurado = this.procuraProduto(nome, descricao);
		if (produtoProcurado == null) {
			throw new ProdutoNaoCadastradoException();
		}
		else {
			return produtoProcurado.toString();
		}
	}
	
	private Produto procuraProduto(String nome, String descricao) {
		for(Produto p: this.produtos) {
			if(p.getDescricao().equals(descricao) && p.getNome().equals(nome)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Fornecedor other = (Fornecedor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int compareTo(Fornecedor outro) {
		return this.nome.compareTo(outro.getNome());	
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNome() {
		return this.nome;
	}
}

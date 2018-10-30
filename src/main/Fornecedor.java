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
		if ("".equals(nome) || nome == null) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if (email.equals("") || email.equals(null)) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		} else if (numero.equals("") || numero.equals(null)) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: numero nao pode ser vazio ou nulo.");
		}
		this.nome = nome;
		this.email = email;
		this.numero = numero;
		this.produtos = new ArrayList<>();
	}

	public void cadastraProduto(String nome, String descricao, double valor) throws ProdutoJaCadastradoException {
		Produto produtoASerAdcionado = new Produto(nome, descricao, valor);
		if (this.produtos.contains(produtoASerAdcionado)) {
			throw new ProdutoJaCadastradoException("Erro no cadastro de produto: produto ja existe.");
		} else {
			this.produtos.add(produtoASerAdcionado);
		}
	}

	public String toString() {
		return this.nome + " - " + this.email + " - " + this.numero;
	}

	public String imprimeProdutos() {
		String saida = "";
		Collections.sort(this.produtos);
		for (Produto p : this.produtos) {
			saida += this.nome + " - " + p + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

	public String exibeProduto(String nome, String descricao) throws ProdutoNaoCadastradoException {
		if (nome.equals("") || nome == null){
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if (descricao.equals("") || descricao == null){
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}		
		
		Produto produtoProcurado = this.procuraProduto(nome, descricao);
		if (produtoProcurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na exibicao de produto: produto nao existe.");
		} else {
			return produtoProcurado.toString();
		}
	}

	private Produto procuraProduto(String nome, String descricao) {
		for (Produto p : this.produtos) {
			if (p.getDescricao().equals(descricao) && p.getNome().equals(nome)) {
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

	public void editaProduto(String nome2, String descricao, double novoPreco) throws ProdutoNaoCadastradoException {
		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		}
		
		if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		}		
		Produto procurado = this.procuraProduto(nome2, descricao);
		if (procurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na edicao de produto: produto nao existe.");
		}
		else {
			procurado.setPreco(novoPreco);
		}
	}

	public void removeProduto(String nome2, String descricao) throws ProdutoNaoCadastradoException {
		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		}
		else if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		}
		Produto procurado = this.procuraProduto(nome2, descricao);
		if (procurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na remocao de produto: produto nao existe.");
		}
		else {
			this.produtos.remove(procurado);
		}
	}
}

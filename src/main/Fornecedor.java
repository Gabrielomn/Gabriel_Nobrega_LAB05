package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * classe que representa a entidade Fornecedor!
 * 
 * @author gabriel
 *
 */
public class Fornecedor implements Comparable<Fornecedor> {

	private String nome;
	private String email;
	private String numero;
	private Set<Produto> produtos;
	
	/**
	 * construtor de fornecedor
	 * @param nome do fornecedor
	 * @param email do fornecedor
	 * @param numero do fornecedor
	 */
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
		this.produtos = new HashSet<>();
	}

	/**
	 * 
	 * @param nome do produto a ser cadastrado
	 * @param descricao do produto a ser cadastrado
	 * @param valor do produto a ser cadastrado
	 * @throws ProdutoJaCadastradoException caso ja tenha sido cadastrado esse produto
	 */
	public void cadastraProduto(String nome, String descricao, double valor) throws ProdutoJaCadastradoException {
		Produto produtoASerAdcionado = new Produto(nome, descricao, valor);
		if (this.produtos.contains(produtoASerAdcionado)) {
			throw new ProdutoJaCadastradoException("Erro no cadastro de produto: produto ja existe.");
		} else {
			this.produtos.add(produtoASerAdcionado);
		}
	}

	/**
	 * @return uma representação do fornecedor
	 */
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.numero;
	}

	/**
	 * retorna uma lista contendo os produtos
	 * @return
	 */
	private List<Produto> fazListaProdutos() {

		List<Produto> saida = new ArrayList<>();
		for (Produto p : this.produtos) {
			saida.add(p);
		}
		return saida;
	}

	/**
	 * imprime todos os produtos que estão cadastrados nesse fornecedor
	 * @return
	 */
	public String imprimeProdutos() {
		String saida = "";
		List<Produto> lista = this.fazListaProdutos();
		Collections.sort(lista);
		for (Produto p : lista) {
			saida += this.nome + " - " + p + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}
	
	/**
	 * 
	 * @param nome do produto a ser exibido
	 * @param descricao do produto a ser exibido
	 * @return o toString desse produto
	 * @throws ProdutoNaoCadastradoException caso esse produto nao tenha sido cadastrado
	 */
	public String exibeProduto(String nome, String descricao) throws ProdutoNaoCadastradoException {
		if (nome.equals("") || nome == null) {
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}

		Produto produtoProcurado = this.procuraProduto(nome, descricao);
		if (produtoProcurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na exibicao de produto: produto nao existe.");
		} else {
			return produtoProcurado.toString();
		}
	}

	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @return o produto que tem o nome e a descricao passada como parametro, null caso nao exista
	 */
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

	/**
	 * equals, retorna true caso seja igual ou false quando nao, leva em conta apenas o nome. 
	 */
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

	/**
	 * 
	 * @param nome2 do produto a ser editado
	 * @param descricao do produto a ser editado
	 * @param novoPreco do produto a ser editado
	 * @throws ProdutoNaoCadastradoException caso nao exista esse produto
	 */
	public void editaProduto(String nome2, String descricao, double novoPreco) throws ProdutoNaoCadastradoException {
		if (novoPreco < 0) {
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		}

		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		}

		if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		}
		Produto procurado = this.procuraProduto(nome2, descricao);
		if (procurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na edicao de produto: produto nao existe.");
		} else {
			procurado.setPreco(novoPreco);
		}
	}

	/**
	 * 
	 * @param nome2 do produto a ser removido
	 * @param descricao do produto a ser removido
	 * @throws ProdutoNaoCadastradoException caso nao exista esse produto
	 */
	public void removeProduto(String nome2, String descricao) throws ProdutoNaoCadastradoException {
		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		}
		Produto procurado = this.procuraProduto(nome2, descricao);
		if (procurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na remocao de produto: produto nao existe.");
		} else {
			this.produtos.remove(procurado);
		}
	}
}

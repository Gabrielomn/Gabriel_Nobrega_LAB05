package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
	private HashMap<IdProduto, Produto> produtos;

	/**
	 * metodo que cadastra um combo
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 * @throws ProdutoNaoCadastradoException
	 * @throws ProdutoJaCadastradoException
	 */
	public void cadastraCombo(String nome, String descricao, double fator, String produtos)
			throws ProdutoNaoCadastradoException, ProdutoJaCadastradoException {

		if ("".equals(nome) || nome == null) {
			throw new IllegalArgumentException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		} else if (descricao.equals("") || descricao.equals(null)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");
		} else if (fator >= 1 || fator < 0) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fator invalido.");
		} else if (produtos.length() == 0) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo deve ter produtos.");
		}
		ProdutoSimples[] temp = this.recuperaProdutos(produtos);
		IdProduto id = new IdProduto(nome, descricao);
		if (this.produtos.containsKey(id)) {
			throw new ProdutoJaCadastradoException("Erro no cadastro de combo: combo ja existe.");
		} else {
			this.produtos.put(id, new Combo(nome, descricao, fator, temp));
		}
	}

	/**
	 * metodo que retorna um array de produtos, usado para construir o combo, visto que esse apenas passa
	 * apenas as strings que tem nome e descricao do produto
	 * @param produtos
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	private ProdutoSimples[] recuperaProdutos(String produtos) throws ProdutoNaoCadastradoException {

		List<String[]> a = this.getArrayStringsProdutos(produtos);
		List<ProdutoSimples> prods = this.verificaPertinencia(a);
		ProdutoSimples[] temp = new ProdutoSimples[prods.size()];
		int cont = 0;
		for (ProdutoSimples p : prods) {
			if (p instanceof ProdutoSimples) {
				temp[cont] = (ProdutoSimples) p;
				cont++;
			}

		}
		return temp;
	}

	/**
	 * tbm usado para pegar um array de produtos
	 * @param all
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	private List<ProdutoSimples> verificaPertinencia(List<String[]> all) throws ProdutoNaoCadastradoException {
		List<ProdutoSimples> p = new ArrayList<>();
		for (String[] small : all) {
			ProdutoSimples a = this.procuraProdutoSimples(small[0], small[1]);
			if (a == null) {
				throw new ProdutoNaoCadastradoException("Erro no cadastro de combo: produto nao existe.");
			} else {
				p.add(a);
			}

		}
		return p;
	}

	/**
	 * separa em strings e trata os espaços em brancos das strings de produtos
	 * @param produtos
	 * @return
	 */
	private List<String[]> getArrayStringsProdutos(String produtos) {
		String[] aux = produtos.split(",");
		List<String[]> a = new ArrayList<>();
		for (String p : aux) {
			String[] aux2 = p.split(" - ");
			aux2[0] = aux2[0].trim();
			aux2[1] = aux2[1].trim();
			a.add(aux2);
		}

		return a;
	}

	/**
	 * construtor de fornecedor
	 * 
	 * @param nome   do fornecedor
	 * @param email  do fornecedor
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
		this.produtos = new HashMap<>();
	}

	/**
	 * 
	 * @param nome      do produto a ser cadastrado
	 * @param descricao do produto a ser cadastrado
	 * @param valor     do produto a ser cadastrado
	 * @throws ProdutoJaCadastradoException caso ja tenha sido cadastrado esse
	 *                                      produto
	 */
	public void cadastraProdutoSimples(String nome, String descricao, double valor)
			throws ProdutoJaCadastradoException {
		IdProduto id = new IdProduto(nome, descricao);
		Produto produtoASerAdcionado = new ProdutoSimples(nome, descricao, valor);
		if (this.produtos.containsKey(id)) {
			throw new ProdutoJaCadastradoException("Erro no cadastro de produto: produto ja existe.");
		} else {
			this.produtos.put(id, produtoASerAdcionado);
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
	 * 
	 * @return
	 */
	private List<Produto> fazListaProdutos() {

		List<Produto> saida = new ArrayList<>();
		for (IdProduto id : this.produtos.keySet()) {
			saida.add(this.produtos.get(id));
		}
		return saida;
	}

	/**
	 * imprime todos os produtos que estão cadastrados nesse fornecedor
	 * 
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
	 * @param nome      do produto a ser exibido
	 * @param descricao do produto a ser exibido
	 * @return o toString desse produto
	 * @throws ProdutoNaoCadastradoException caso esse produto nao tenha sido
	 *                                       cadastrado
	 */
	public String exibeProduto(String nome, String descricao) throws ProdutoNaoCadastradoException {
		if (nome.equals("") || nome == null) {
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}
		Produto produtoProcurado;
		if (this.produtos.containsKey(new IdProduto(nome, descricao))) {
			produtoProcurado = this.produtos.get(new IdProduto(nome, descricao));
		} else {
			throw new ProdutoNaoCadastradoException("Erro na exibicao de produto: produto nao existe.");
		}
		return produtoProcurado.toString();

	}


	/**
	 * procura um produto simples
	 * @param nome
	 * @param descricao
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	private ProdutoSimples procuraProdutoSimples(String nome, String descricao) throws ProdutoNaoCadastradoException {
		boolean achouCombo = false;
		for (IdProduto id : this.produtos.keySet()) {
			if (this.produtos.get(id).getDescricao().equals(descricao) && this.produtos.get(id).getNome().equals(nome)
					&& this.produtos.get(id) instanceof ProdutoSimples) {
				return (ProdutoSimples) this.produtos.get(id);
			} else if (this.produtos.get(id).getDescricao().equals(descricao)
					&& this.produtos.get(id).getNome().equals(nome)) {
				achouCombo = true;
			}
		}
		if (achouCombo) {
			throw new ProdutoNaoCadastradoException(
					"Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
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
	 * equals, retorna true caso seja igual ou false quando nao, leva em conta
	 * apenas o nome.
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
	 * @param nome2     do produto a ser editado
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
		IdProduto id = new IdProduto(nome2, descricao);
		if (this.produtos.containsKey(id)) {
			this.produtos.get(id).setPreco(novoPreco);
		} else {
			throw new ProdutoNaoCadastradoException("Erro na edicao de produto: produto nao existe.");
		}
	}

	/**
	 * 
	 * @param nome2     do produto a ser removido
	 * @param descricao do produto a ser removido
	 * @throws ProdutoNaoCadastradoException caso nao exista esse produto
	 */
	public void removeProduto(String nome2, String descricao) throws ProdutoNaoCadastradoException {
		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		}
		IdProduto id = new IdProduto(nome2, descricao);
		if(this.produtos.containsKey(id)) {
			this.produtos.remove(id);
		}
		else {
			throw new ProdutoNaoCadastradoException("Erro na remocao de produto: produto nao existe.");

		}

	}

	public void editaCombo(String nome2, String descricao, double novoFator) throws ProdutoNaoCadastradoException {
		if (novoFator <= 0 || novoFator >= 1) {
			throw new IllegalArgumentException("Erro na edicao de combo: fator invalido.");
		}
		if (nome2.equals("") || nome2 == null) {
			throw new IllegalArgumentException("Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		}
		if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		}
		Combo procurado = this.procuraCombo(nome2, descricao);
		if (procurado == null) {
			throw new ProdutoNaoCadastradoException("Erro na edicao de combo: produto nao existe.");
		} else {
			procurado.setPreco(novoFator);
		}
	}

	private Combo procuraCombo(String nome2, String descricao) {
		IdProduto id = new IdProduto(nome2, descricao);
		if (this.produtos.containsKey(id)) {
			if (this.produtos.get(id) instanceof Combo) {
				return (Combo) this.produtos.get(id);
			}
		}
		return null;
	}



	public double getValorCompra(String nome, String descricao) throws ProdutoNaoCadastradoException {
		if (nome.equals("") || nome == null){
			throw new IllegalArgumentException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		}else if (descricao.equals("") || descricao == null) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula");
		}
		IdProduto id = new IdProduto(nome, descricao);
		if (!this.produtos.containsKey(id)) {
			throw new ProdutoNaoCadastradoException("Erro ao cadastrar compra: produto nao existe.");
		}
		
		return this.produtos.get(id).getValor();
	}
}

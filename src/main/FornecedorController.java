package main;

import java.util.Map;
import java.util.TreeMap;

/**
 * entidade responsavel por manipular os fornecedores
 * 
 * @author gabriel
 *
 */
public class FornecedorController {
	/**
	 * eda que guarda os fornecedores, liga o nome deles ao objeto
	 */
	private Map<String, Fornecedor> fornecedores;

	public FornecedorController() {
		this.fornecedores = new TreeMap<>();
	}

	public void cadastraCombo(String nomeDoFornecedor, String nome, String descricao, double fator, String produtos)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException, ProdutoJaCadastradoException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			this.fornecedores.get(nomeDoFornecedor).cadastraCombo(nome, descricao, fator, produtos);
		} else if (nomeDoFornecedor.equals("") || nomeDoFornecedor == null) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");
		} else {
			throw new FornecedorNaoExistenteException("Erro no cadastro de combo: fornecedor nao existe.");
		}
	}

	/**
	 * cadastra um Produto de acordo com os parametros
	 * 
	 * @param nomeDoFornecedor que vai guardar o produto
	 * @param nome             do produto
	 * @param descricao        do produto
	 * @param valor            do produto
	 * @throws FornecedorNaoExistenteException caso nao exista o fornecedor
	 * @throws ProdutoJaCadastradoException    caso o fornecedor ja tenha esse
	 *                                         produto
	 */
	public void cadastraProdutoSimples(String nomeDoFornecedor, String nome, String descricao, double valor)
			throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			this.fornecedores.get(nomeDoFornecedor).cadastraProdutoSimples(nome, descricao, valor);
		} else if (nomeDoFornecedor.equals("") || nomeDoFornecedor == null) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		} else {
			throw new FornecedorNaoExistenteException("Erro no cadastro de produto: fornecedor nao existe.");
		}
	}

	/**
	 * @return retorna uma representacao de todos os fornecedores
	 *
	 */
	public String exibeFornecedores() {
		String saida = "";
		for (String f : this.fornecedores.keySet()) {
			saida += this.fornecedores.get(f).toString() + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

	/**
	 * 
	 * @return uma representacao de todos os produtos de todos os fornecedores
	 */
	public String exibeTodosProdutosDeTodosFornecedores() {
		String saida = "";
		for (String f : this.fornecedores.keySet()) {
			saida += this.fornecedores.get(f).imprimeProdutos() + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

	/**
	 * 
	 * @param nomeDoFornecedor que contem o produto desejado
	 * @param nome             do produto desjeado
	 * @param descricao        do produto desejado
	 * @return toString desse produto
	 * @throws FornecedorNaoExistenteException caso esse fornecedor nao exista
	 * @throws ProdutoNaoCadastradoException   caso esse produto nao esteja
	 *                                         cadastrado nesse fornecedor
	 */
	public String exibeProduto(String nomeDoFornecedor, String nome, String descricao)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			return this.fornecedores.get(nomeDoFornecedor).exibeProduto(nome, descricao);
		} else if (nomeDoFornecedor.equals("") || nomeDoFornecedor == null) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else {
			throw new FornecedorNaoExistenteException("Erro na exibicao de produto: fornecedor nao existe.");
		}
	}

	/**
	 *
	 * @param nomeDoFornecedor desejado
	 * @return uma representacao de todos os produtos desse fornecedor
	 * @throws FornecedorNaoExistenteException caso nao exista esse fornecedor
	 */
	public String exibeTodosProdutos(String nomeDoFornecedor) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			return this.fornecedores.get(nomeDoFornecedor).imprimeProdutos();
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	/**
	 * cadastra um fornecedor
	 * 
	 * @param nome   do fornecedor a ser cadastrado
	 * @param numero do fornecedor a ser cadastrado
	 * @param email  do fornecedor a ser cadastrado
	 * @return o nome do fornecedor cadastrado
	 * @throws FornecedorJaExistenteException caso ja exista esse fornecedor
	 */
	public String cadastraFornecedor(String nome, String numero, String email) throws FornecedorJaExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			throw new FornecedorJaExistenteException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		} else {
			this.fornecedores.put(nome, new Fornecedor(nome, email, numero));
			return nome;
		}
	}

	/**
	 * 
	 * @param nome do fornecedor desejado
	 * @return o toString de fornecedor
	 * @throws FornecedorNaoExistenteException caso nao tenha esse fornecedor
	 */
	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			return this.fornecedores.get(nome).toString();
		} else {
			throw new FornecedorNaoExistenteException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}
	}

	/**
	 * 
	 * @param nome do fornecedor a ser deletado
	 * @throws FornecedorNaoExistenteException caso nao exista esse fornecedor
	 */
	public void deletaFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (nome.equals("")) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		} else if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.remove(nome);
		} else {
			throw new FornecedorNaoExistenteException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}
	}

	/**
	 * altera o email
	 * 
	 * @param nome      do fornecedor a ser editado
	 * @param novoEmail do fornecedor
	 * @throws FornecedorNaoExistenteException caso nao existaa
	 */
	private void alteraEmail(String nome, String novoEmail) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setEmail(novoEmail);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	/**
	 * altera o numero do fornecedor
	 * 
	 * @param nome       do fornecedor a ser editado
	 * @param novoNumero do fornecedor
	 * @throws FornecedorNaoExistenteException caso nao exista o fornecedor
	 */
	private void alteraNumero(String nome, String novoNumero) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setNumero(novoNumero);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	/**
	 * 
	 * @param nome      do fornecedor
	 * @param atributo  a ser editado
	 * @param novoValor ddo atributo
	 * @throws FornecedorNaoExistenteException caso o fornecedor nao exista
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) throws FornecedorNaoExistenteException {
		if (novoValor.equals("") || novoValor.equals(null)) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} else if (atributo.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		} else if (atributo.equals("nome")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		} else if (atributo.equals("email")) {
			this.alteraEmail(nome, novoValor);
		} else if (atributo.equals("telefone")) {
			this.alteraNumero(nome, novoValor);
		} else {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		}
	}

	/**
	 * 
	 * @param nome       do produto a ser editado
	 * @param descricao  do produto a ser editado
	 * @param fornecedor do produto a ser editado
	 * @param novoPreco  do produto a ser editado
	 * @throws ProdutoNaoCadastradoException   caso nao esteja cadastrado
	 * @throws FornecedorNaoExistenteException caso nao exista o fornecedor
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco)
			throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		if (fornecedor.equals("") || fornecedor == null) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (this.fornecedores.containsKey(fornecedor)) {
			this.fornecedores.get(fornecedor).editaProduto(nome, descricao, novoPreco);
		} else {
			throw new FornecedorNaoExistenteException("Erro na edicao de produto: fornecedor nao existe.");
		}
	}

	/**
	 * 
	 * @param nome       do produto a ser removido
	 * @param descricao  do produto a ser removido
	 * @param fornecedor nome do fornecedor que tem o produto
	 * @throws ProdutoNaoCadastradoException   caso nao esteja cadastrado
	 * @throws FornecedorNaoExistenteException caso nao exista esse fornecedor
	 */
	public void removeProduto(String nome, String descricao, String fornecedor)
			throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		if (fornecedor.equals("") || fornecedor == null) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else {
			if (this.fornecedores.containsKey(fornecedor)) {
				this.fornecedores.get(fornecedor).removeProduto(nome, descricao);
			} else {
				throw new FornecedorNaoExistenteException("Erro na remocao de produto: fornecedor nao existe.");
			}
		}
	}

	public void editaCombo(String nome, String descricao, String fornecedor, double novoFator)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		if (fornecedor.equals("") || fornecedor == null) {
			throw new IllegalArgumentException("Erro na edicao de combo: fornecedor nao pode ser vazio ou nulo.");
		}
		if (this.fornecedores.containsKey(fornecedor)) {
			this.fornecedores.get(fornecedor).editaCombo(nome, descricao, novoFator);
		} else {
			throw new FornecedorNaoExistenteException("Erro na edicao de combo: fornecedor nao existe.");
		}
	}
	
	public double getValorCompra(String fornecedor, String nome, String descricao) throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		if (fornecedor.equals("") || fornecedor == null) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		}else if (!this.fornecedores.containsKey(fornecedor)) {
			throw new FornecedorNaoExistenteException("Erro ao cadastrar compra: fornecedor nao existe.");
		}else {
			return this.fornecedores.get(fornecedor).getValorCompra(nome, descricao);
		}
	}

	public boolean verificaExistenciaFornecedor(String fornecedor) throws FornecedorNaoExistenteException {
		if(fornecedor.equals("")) {
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		}
		if(!this.fornecedores.containsKey(fornecedor)){
			return false;
		}
		return true;
	}



}

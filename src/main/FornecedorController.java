package main;

import java.util.Map;
import java.util.TreeMap;

public class FornecedorController {
	private Map<String, Fornecedor> fornecedores;

	public FornecedorController() {
		this.fornecedores = new TreeMap<>();
	}

	public void cadastraProduto(String nomeDoFornecedor, String nome, String descricao, double valor)
			throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			this.fornecedores.get(nomeDoFornecedor).cadastraProduto(nome, descricao, valor);
		} else if (nomeDoFornecedor.equals("") || nomeDoFornecedor == null) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		} else {
			throw new FornecedorNaoExistenteException("Erro no cadastro de produto: fornecedor nao existe.");
		}
	}

	public String exibeFornecedores() {
		String saida = "";
		for (String f : this.fornecedores.keySet()) {
			saida += this.fornecedores.get(f).toString() + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

	public String exibeTodosProdutosDeTodosFornecedores() {
		String saida = "";
		for (String f : this.fornecedores.keySet()) {
			saida += this.fornecedores.get(f).imprimeProdutos() + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

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

	public String exibeTodosProdutos(String nomeDoFornecedor)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		if (this.fornecedores.containsKey(nomeDoFornecedor)) {
			return this.fornecedores.get(nomeDoFornecedor).imprimeProdutos();
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	public String cadastraFornecedor(String nome, String numero, String email) throws FornecedorJaExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			throw new FornecedorJaExistenteException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		} else {
			this.fornecedores.put(nome, new Fornecedor(nome, email, numero));
			return nome;
		}
	}

	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			return this.fornecedores.get(nome).toString();
		} else {
			throw new FornecedorNaoExistenteException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}
	}

	public void deletaFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (nome.equals("")) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		} else if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.remove(nome);
		} else {
			throw new FornecedorNaoExistenteException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}
	}

	private void alteraEmail(String nome, String novoEmail) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setEmail(novoEmail);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	private void alteraNumero(String nome, String novoNumero) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setNumero(novoNumero);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

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

}

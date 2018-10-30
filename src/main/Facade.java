package main;

import easyaccept.EasyAccept;

public class Facade {
	
	private ClienteController clienteController;
	private FornecedorController fornecedorController;

	public Facade() {
		this.clienteController = new ClienteController();
		this.fornecedorController = new FornecedorController();
	}
	
	public void adicionaCliente(String nome, String cpf, String email, String localizacao) throws ClienteJaExistenteException, IllegalArgumentException {
		this.clienteController.cadastraCliente(nome, cpf, email, localizacao);
	}
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		this.clienteController.removeCliente(cpf);
	}
	
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		return this.clienteController.exibeCliente(cpf);
	}
	
	public String imprimeClientes() {
		return this.clienteController.imprimeClientes();
	}
	public void setNome(String cpf, String nome) throws ClienteNaoExistenteException {
		this.clienteController.setNome(cpf, nome);
	}
	
	public void setEmail(String cpf, String email) throws ClienteNaoExistenteException {
		this.clienteController.setEmail(cpf, email);
	}

	public void setLocalizacao(String cpf, String localizacao) throws ClienteNaoExistenteException {
		this.clienteController.setLocalizacao(cpf, localizacao);
	}
	
	public void cadastraProduto(String nomeDoFornecedor, String nome, String descricao, double valor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		this.fornecedorController.cadastraProduto(nomeDoFornecedor, nome, descricao, valor);
	}

	public String exibeFornecedores() {
		return this.fornecedorController.exibeFornecedores();
	}
	
	public String exibeTodosProdutosDeTodosFornecedores() {
		return this.fornecedorController.exibeTodosProdutosDeTodosFornecedores();
	}
	
	public String exibeProduto(String nomeDoFornecedor, String nome, String descricao) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeProduto(nomeDoFornecedor, nome, descricao);
	}

	public String exibeTodosProdutos(String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeTodosProdutos(nomeDoFornecedor);
	}

	public void cadastraFornecedor(String nome, String numero, String email) throws FornecedorJaExistenteException {
		this.fornecedorController.cadastraFornecedor(nome, numero, email);
	}

	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		return this.fornecedorController.exibeFornecedor(nome);
	}

	public void deletaFornecedor(String nome) throws FornecedorNaoExistenteException {
		this.fornecedorController.deletaFornecedor(nome);
	}
	
	public void alteraEmail(String nome, String novoEmail) throws FornecedorNaoExistenteException {
		this.fornecedorController.alteraEmail(nome, novoEmail);
	}

	public void alteraNumero(String nome, String novoNumero) throws FornecedorNaoExistenteException {
		this.fornecedorController.alteraNumero(nome, novoNumero);
	}
	
	public static void main(String[] args) {
		args = new String[] {"main.Facade","acceptance_test/use_case_1.txt" };
		EasyAccept.main(args);
	}
}

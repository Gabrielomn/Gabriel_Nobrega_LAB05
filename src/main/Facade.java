package main;

import easyaccept.EasyAccept;

public class Facade {
	
	private ClienteController clienteController;
	private FornecedorController fornecedorController;

	public Facade() {
		this.clienteController = new ClienteController();
		this.fornecedorController = new FornecedorController();
	}
	
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) throws IllegalArgumentException, ClienteJaExistenteException{
		return this.clienteController.cadastraCliente(nome, cpf, email, localizacao);
	}
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		this.clienteController.removeCliente(cpf);
	}
	
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		return this.clienteController.exibeCliente(cpf);
	}
	
	public String exibeClientes() {
		return this.clienteController.imprimeClientes();
	}
	
	public void editaCliente(String cpf, String atributo, String novoValor) throws ClienteNaoExistenteException {
		this.clienteController.editaCliente(cpf, atributo, novoValor);
	}
	
	public void adicionaProduto(String nomeDoFornecedor, String nome, String descricao, double valor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		this.fornecedorController.cadastraProduto(nomeDoFornecedor, nome, descricao, valor);
	}

	public String exibeFornecedores() {
		return this.fornecedorController.exibeFornecedores();
	}
	
	public String exibeProdutos() {
		return this.fornecedorController.exibeTodosProdutosDeTodosFornecedores();
	}
	
	public String exibeProduto(String nome, String descricao,String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeProduto(nomeDoFornecedor, nome, descricao);
	}

	public String exibeProdutosFornecedor(String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeTodosProdutos(nomeDoFornecedor);
	}

	public String adicionaFornecedor(String nome, String email, String numero) throws FornecedorJaExistenteException {
		return this.fornecedorController.cadastraFornecedor(nome, numero, email);
	}

	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		return this.fornecedorController.exibeFornecedor(nome);
	}

	public void removeFornecedor(String nome) throws FornecedorNaoExistenteException {
		this.fornecedorController.deletaFornecedor(nome);
	}
	
	public void editaFornecedor (String nome, String atributo, String novoValor) throws FornecedorNaoExistenteException {
		this.fornecedorController.editaFornecedor(nome, atributo, novoValor);
	}
	
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.fornecedorController.editaProduto(nome, descricao,fornecedor,novoPreco);
	}
	
	public void removeProduto(String nome, String descricao, String fornecedor) throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.fornecedorController.removeProduto(nome,descricao,fornecedor);
	}
	
	public static void main(String[] args) {
		args = new String[] {"main.Facade","acceptance_test/use_case_1.txt","acceptance_test/use_case_2.txt","acceptance_test/use_case_3.txt"};
		EasyAccept.main(args);
	}
}

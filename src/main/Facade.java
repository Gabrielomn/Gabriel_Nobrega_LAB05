package main;

import easyaccept.EasyAccept;

public class Facade {
	
	private SagaController saga;
	
	
	public Facade() {
		this.saga = new SagaController();
	}
	
	public String exibeContas(String cpf, String fornecedor) throws FornecedorNaoExistenteException, ClienteNaoExistenteException {
		return this.saga.exibeContas(cpf, fornecedor);
	}
	
	public String getDebito(String cpf, String fornecedor) throws ClienteNaoExistenteException, FornecedorNaoExistenteException {
		return this.saga.getDebito(cpf, fornecedor);
	}
	
	public void realizaPagamento(String cpf, String fornecedor) throws FornecedorNaoExistenteException, ClienteNaoExistenteException {
		this.saga.quitaDebito(cpf,fornecedor);
	}
	
	public void adicionaCompra(String cpf, String fornecedor, String data,String nomeProduto, String descricaoProduto) throws ClienteNaoExistenteException, FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		this.saga.adicionaCompra(cpf, fornecedor, data, nomeProduto, descricaoProduto);
	}
	
	public void adicionaCombo(String fornecedor, String nome, String descricao, double fator, String produtos) throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException, ProdutoJaCadastradoException {
		this.saga.adicionaCombo(fornecedor, nome, descricao, fator, produtos);
	}
	
	public void editaCombo(String nome, String descricao, String fornecedor, double novoFator) throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		this.saga.editaCombo(nome, descricao,fornecedor,novoFator);
	}
	
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) throws IllegalArgumentException, ClienteJaExistenteException{
		return this.saga.adicionaCliente(cpf, nome, email, localizacao);
	}
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		this.saga.removeCliente(cpf);
	}
	
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		return this.saga.exibeCliente(cpf);
	}
	
	public String exibeClientes() {
		return this.saga.exibeClientes();
	}
	
	public void editaCliente(String cpf, String atributo, String novoValor) throws ClienteNaoExistenteException {
		this.saga.editaCliente(cpf, atributo, novoValor);
	}
	
	public void adicionaProduto(String nomeDoFornecedor, String nome, String descricao, double valor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		this.saga.adicionaProduto(nomeDoFornecedor, nome, descricao, valor);
	}

	public String exibeFornecedores() {
		return this.saga.exibeFornecedores();
	}
	
	public String exibeProdutos() {
		return this.saga.exibeProdutos();
	}
	
	public String exibeProduto(String nome, String descricao,String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.saga.exibeProduto(nome, descricao, nomeDoFornecedor);
	}

	public String exibeProdutosFornecedor(String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.saga.exibeProdutosFornecedor(nomeDoFornecedor);
	}

	public String adicionaFornecedor(String nome, String email, String numero) throws FornecedorJaExistenteException {
		return this.saga.adicionaFornecedor(nome, email, numero);
	}

	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		return this.saga.exibeFornecedor(nome);
	}

	public void removeFornecedor(String nome) throws FornecedorNaoExistenteException {
		this.saga.removeFornecedor(nome);
	}
	
	public void editaFornecedor (String nome, String atributo, String novoValor) throws FornecedorNaoExistenteException {
		this.saga.editaFornecedor(nome, atributo, novoValor);
	}
	
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.saga.editaProduto(nome, descricao,fornecedor,novoPreco);
	}
	
	public void removeProduto(String nome, String descricao, String fornecedor) throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.saga.removeProduto(nome,descricao,fornecedor);
	}
	
	public String exibeContasClientes(String cpf) throws Exception {
		return this.saga.exibeContasClientes(cpf);
	}
	
	public String listarCompras() {
		return saga.listarCompras();
	}
	
	public void ordenaPor(String criterio) {
		this.saga.ordenaPor(criterio);
	}
	
	public static void main(String[] args) {
		args = new String[] {"main.Facade","acceptance_test/use_case_1.txt","acceptance_test/use_case_2.txt","acceptance_test/use_case_3.txt","acceptance_test/use_case_4.txt","acceptance_test/use_case_5.txt", "acceptance_test/use_case_6.txt","acceptance_test/use_case_7.txt"};
		EasyAccept.main(args);
	}
}

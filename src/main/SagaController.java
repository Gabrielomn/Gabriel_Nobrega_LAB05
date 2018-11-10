package main;

import easyaccept.EasyAccept;

/**
 * controlador acima de cliente e fornecedor controller, surgiu da necessidade de se adaptar aos testes de acei
 * tacao que tinham um comportamento esquisito.
 * @author gabrielomn
 *
 */
public class SagaController {
	private ClienteController clienteController;
	private FornecedorController fornecedorController;

	/**
	 * construtor do controller
	 */
	public SagaController() {
		this.clienteController = new ClienteController();
		this.fornecedorController = new FornecedorController();
	}

	/**
	 * classe que retorna o debito de um cliente com um fornecedor determinado
	 * @param cpf
	 * @param fornecedor
	 * @return
	 * @throws ClienteNaoExistenteException
	 * @throws FornecedorNaoExistenteException
	 */
	public String getDebito(String cpf, String fornecedor)
			throws ClienteNaoExistenteException, FornecedorNaoExistenteException {
		String err = "Erro ao recuperar debito: ";
		boolean cliente = this.clienteController.verificaCliente(cpf, err);
		boolean forn = this.fornecedorController.verificaExistenciaFornecedor(fornecedor, err);
		String saida = "";
		saida = this.clienteController.getDebito(cpf, fornecedor);
		if (!cliente) {
			throw new IllegalArgumentException("Erro ao recuperar debito: cliente nao existe.");
		} else if (!forn) {
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao existe.");
		}

		return saida;
	}

	/**
	 * adiciona a compra de um produto simples em um dado cliente
	 * @param cpf
	 * @param fornecedor
	 * @param data
	 * @param nomeProduto
	 * @param descricaoProduto
	 * @throws ClienteNaoExistenteException
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoNaoCadastradoException
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nomeProduto, String descricaoProduto)
			throws ClienteNaoExistenteException, FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		this.clienteController.verificaDadosCompra(cpf, data);
		this.clienteController.cadastraCompra(nomeProduto, fornecedor, cpf, descricaoProduto,
				this.fornecedorController.getValorCompra(fornecedor, nomeProduto, descricaoProduto), data);
	}

	/**
	 * adiciona um combo para um fornecedor
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoNaoCadastradoException
	 * @throws ProdutoJaCadastradoException
	 */
	public void adicionaCombo(String fornecedor, String nome, String descricao, double fator, String produtos)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException, ProdutoJaCadastradoException {
		this.fornecedorController.cadastraCombo(fornecedor, nome, descricao, fator, produtos);
	}

	/**
	 * edita o fator de desconto de um combo de dado fornecedor
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoFator
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoNaoCadastradoException
	 */
	public void editaCombo(String nome, String descricao, String fornecedor, double novoFator)
			throws FornecedorNaoExistenteException, ProdutoNaoCadastradoException {
		this.fornecedorController.editaCombo(nome, descricao, fornecedor, novoFator);
	}

	/**
	 * cadastra um novo cliente
	 * @param cpf
	 * @param nome
	 * @param email
	 * @param localizacao
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ClienteJaExistenteException
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao)
			throws IllegalArgumentException, ClienteJaExistenteException {
		return this.clienteController.cadastraCliente(nome, cpf, email, localizacao);
	}

	/**
	 * remove o cliente com dado cpf
	 * @param cpf
	 * @throws ClienteNaoExistenteException
	 */
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		this.clienteController.removeCliente(cpf);
	}

	/**
	 * retorna uma representação textual do cliente com esse cpf.
	 * @param cpf
	 * @return
	 * @throws ClienteNaoExistenteException
	 */
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		return this.clienteController.exibeCliente(cpf);
	}

	/**
	 * retorna uma representação textual de todos os clientes
	 * @return
	 */
	public String exibeClientes() {
		return this.clienteController.imprimeClientes();
	}

	/**
	 * edita um atributo passado como parametro para um novo valor.
	 * @param cpf
	 * @param atributo
	 * @param novoValor
	 * @throws ClienteNaoExistenteException
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) throws ClienteNaoExistenteException {
		this.clienteController.editaCliente(cpf, atributo, novoValor);
	}

	/**
	 * adiciona um produto para o fornecedor
	 * @param nomeDoFornecedor
	 * @param nome
	 * @param descricao
	 * @param valor
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoJaCadastradoException
	 */
	public void adicionaProduto(String nomeDoFornecedor, String nome, String descricao, double valor)
			throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		this.fornecedorController.cadastraProdutoSimples(nomeDoFornecedor, nome, descricao, valor);
	}

	/**
	 * retorna uma representação textual de todos os fornecedores
	 * @return
	 */
	public String exibeFornecedores() {
		return this.fornecedorController.exibeFornecedores();
	}

	/**
	 * retorna uma representação textual de todos os produtos de todos os fornecedores
	 * @return
	 */
	public String exibeProdutos() {
		return this.fornecedorController.exibeTodosProdutosDeTodosFornecedores();
	}

	/**
	 * exibe a representação textual do produto com o nome e descricao passado como parametro em tal fornecedor.
	 * @param nome
	 * @param descricao
	 * @param nomeDoFornecedor
	 * @return
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoJaCadastradoException
	 * @throws ProdutoNaoCadastradoException
	 */
	public String exibeProduto(String nome, String descricao, String nomeDoFornecedor)
			throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeProduto(nomeDoFornecedor, nome, descricao);
	}

	/**
	 * retorna uma representação textual de todos os produtos do fornecedor passado como parametro
	 * @param nomeDoFornecedor
	 * @return
	 * @throws FornecedorNaoExistenteException
	 * @throws ProdutoJaCadastradoException
	 * @throws ProdutoNaoCadastradoException
	 */
	public String exibeProdutosFornecedor(String nomeDoFornecedor)
			throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		return this.fornecedorController.exibeTodosProdutos(nomeDoFornecedor);
	}

	/**
	 * cadastra um novo fornecedor
	 * @param nome
	 * @param email
	 * @param numero
	 * @return
	 * @throws FornecedorJaExistenteException
	 */
	public String adicionaFornecedor(String nome, String email, String numero) throws FornecedorJaExistenteException {
		return this.fornecedorController.cadastraFornecedor(nome, numero, email);
	}

	/**
	 * retorna uma representação textual do fornecedor com esse nome.
	 * @param nome
	 * @return
	 * @throws FornecedorNaoExistenteException
	 */
	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		return this.fornecedorController.exibeFornecedor(nome);
	}

	/**
	 * remove o fornecedor com tal nome.
	 * @param nome
	 * @throws FornecedorNaoExistenteException
	 */
	public void removeFornecedor(String nome) throws FornecedorNaoExistenteException {
		this.fornecedorController.deletaFornecedor(nome);
	}

	/**
	 * edita um atributo do fornecedor com o valor passado com parametro
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 * @throws FornecedorNaoExistenteException
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) throws FornecedorNaoExistenteException {
		this.fornecedorController.editaFornecedor(nome, atributo, novoValor);
	}

	/**
	 * edita o produto para o novoPreco/fator passado como parametro.
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoPreco
	 * @throws ProdutoNaoCadastradoException
	 * @throws FornecedorNaoExistenteException
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco)
			throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.fornecedorController.editaProduto(nome, descricao, fornecedor, novoPreco);
	}

	/**
	 * remove o produto com esse nome e descricao de tal fornecedor.
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @throws ProdutoNaoCadastradoException
	 * @throws FornecedorNaoExistenteException
	 */
	public void removeProduto(String nome, String descricao, String fornecedor)
			throws ProdutoNaoCadastradoException, FornecedorNaoExistenteException {
		this.fornecedorController.removeProduto(nome, descricao, fornecedor);
	}

	/**
	 * exibe todos os debitos de um cpf com um fornecedor.
	 * @param cpf
	 * @param fornecedor
	 * @return
	 * @throws ClienteNaoExistenteException 
	 * @throws FornecedorNaoExistenteException 
	 * @throws Exception 
	 */
	public String exibeContas(String cpf, String fornecedor) throws ClienteNaoExistenteException, FornecedorNaoExistenteException {
		String err = "Erro ao exibir conta do cliente: ";
		this.fornecedorController.verificaExistenciaFornecedor(fornecedor, err);
		return this.clienteController.exibeContas(cpf, fornecedor, err);
	}

	/**
	 * exibe todos os debitos de um cpf.
	 * @param cpf
	 * @return
	 * @throws Exception 
	 */
	public String exibeContasClientes(String cpf) throws Exception {
		String err = "Erro ao exibir contas do cliente: ";
		return this.clienteController.exibeContasClientes(cpf, err);
	}

	/**
	 * quita o debito de um cliente com um fornecedor
	 * @param cpf
	 * @param fornecedor
	 * @throws FornecedorNaoExistenteException
	 * @throws ClienteNaoExistenteException
	 */
	public void quitaDebito(String cpf, String fornecedor) throws FornecedorNaoExistenteException, ClienteNaoExistenteException {
		String err = "Erro no pagamento de conta: ";
		this.fornecedorController.verificaExistenciaFornecedor(fornecedor, err);
		this.clienteController.verificaCliente(cpf, err);
		this.clienteController.quitaDebito(cpf, fornecedor, err);
	}

	/**
	 * define o criterio para qual as compras serao ordenadas.
	 * @param criterio
	 */
	public void ordenaPor(String criterio) {
		this.clienteController.ordenaPor(criterio);
	}

	/**
	 * lista todas as compras de acordo com o criterio previamente definido.
	 * @return
	 */
	public String listarCompras() {
		return this.clienteController.listarCompras();
	}


}

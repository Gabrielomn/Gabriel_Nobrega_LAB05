package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * entidade responsavel por administrar clientes
 * @author gabriel
 *
 */
public class ClienteController {

	/**
	 * eda responsavel por guardar clientes, linka cpf a cliente
	 */
	private Map<String, Cliente> clientes;

	/**
	 * construtor de cliente
	 */
	public ClienteController() {
		this.clientes = new HashMap<>();
	}

	/**
	 * 
	 * @param nome do cliente a ser cadastrado
	 * @param cpf do cliente a ser cadastrado
	 * @param email do cliente a ser cadastrado
	 * @param localizacao do cliente a ser cadastrado
	 * @return o cpf do cliente
	 * @throws IllegalArgumentException caso o nome, email, ou localizacao estejam vazios, ou cpf nao tenha 11 digitos
	 * @throws ClienteJaExistenteException caso ja exista esse cliente
	 */
	public String cadastraCliente(String nome, String cpf, String email, String localizacao)
			throws IllegalArgumentException, ClienteJaExistenteException {
		if (this.clientes.containsKey(cpf)) {
			throw new ClienteJaExistenteException("Erro no cadastro do cliente: cliente ja existe.");
		} else {
			this.clientes.put(cpf, new Cliente(nome, cpf, email, localizacao));
			return cpf;
		}
	}
	
	public void cadastraCompra(String nome, String fornecedor, String cpf, String descricao, double valor, String data) throws ClienteNaoExistenteException {
		
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: cpf invalido.");
		}if (!this.clientes.containsKey(cpf)) {
			throw new ClienteNaoExistenteException("Erro ao cadastrar compra: cliente nao existe");
		}
		this.clientes.get(cpf).cadastraCompra(fornecedor, data, nome, descricao, valor);
		
	}

	/**
	 * 
	 * @param cpf do cliente a ser removido
	 * @throws ClienteNaoExistenteException caso nao exista esse cliente
	 */
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.remove(cpf);
		} else {
			throw new ClienteNaoExistenteException("Erro na exibicao do cliente: cliente nao existe.");
		}
	}

	/**
	 * 
	 * @param cpf do cliente desejado
	 * @return o toString do cliente procurado
	 * @throws ClienteNaoExistenteException caso o cliente procurado nao exista
	 */
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).toString();
		} else {
			throw new ClienteNaoExistenteException("Erro na exibicao do cliente: cliente nao existe.");
		}
	}

	/**
	 * retorna uma lista com todos os clientes
	 * @return
	 */
	private List<Cliente> pegaListaClientes() {
		List<Cliente> listaDeClientes = new ArrayList<>();
		for (String e : this.clientes.keySet()) {
			listaDeClientes.add(this.clientes.get(e));
		}
		Collections.sort(listaDeClientes);
		return listaDeClientes;
	}

	/**
	 * 
	 * @return String que representa todos os clientes no set
	 */
	public String imprimeClientes() {
		String saida = "";
		List<Cliente> listaDeClientes = this.pegaListaClientes();
		for (Cliente c : listaDeClientes) {
			saida += c + " | ";
		}
		saida = saida.substring(0, saida.length() - 3);
		return saida;
	}

	private void setNome(String cpf, String nome) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setNome(nome);
		} else {
			throw new ClienteNaoExistenteException("Erro na edicao do cliente: cliente nao existe.");
		}
	}

	private void setEmail(String cpf, String email) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setEmail(email);
		} else {
			throw new ClienteNaoExistenteException("Erro na edicao do cliente: cliente nao existe.");
		}
	}

	private void setLocalizacao(String cpf, String localizacao) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setLocalizacao(localizacao);
		} else {
			throw new ClienteNaoExistenteException("Erro na edicao do cliente: cliente nao existe.");
		}
	}

	/**
	 * 
	 * @param cpf do cliente a ser editado
	 * @param atributo a ser editado
	 * @param novoValor do atributi
	 * @throws ClienteNaoExistenteException caso nao exista o cliente com esse cpf
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) throws ClienteNaoExistenteException {
		if (novoValor.equals("") || novoValor.equals(null)) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		} else if (atributo.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		} else if (atributo.equals("nome")) {
			this.setNome(cpf, novoValor);
		} else if (atributo.equals("localizacao")) {
			this.setLocalizacao(cpf, novoValor);
		} else if (atributo.equals("email")) {
			this.setEmail(cpf, novoValor);
		} else {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
		}
	}

	public boolean verificaCliente(String cpf, String err) throws ClienteNaoExistenteException {
		if(cpf.length() != 11) {
			throw new IllegalArgumentException(err + "cpf invalido.");
		}
		
		if(!this.clientes.containsKey(cpf)) {
			throw new ClienteNaoExistenteException(err + "cliente nao existe.");
		}
		return true;
	}
	
	public boolean verificaData(String data, String err) throws ClienteNaoExistenteException {
		if(data.length() != 10) {
			throw new IllegalArgumentException(err + "data invalida.");
		}

		return true;
	}
	
	public void verificaDadosCompra(String cpf, String data) throws ClienteNaoExistenteException {
		String err = "Erro ao cadastrar compra: ";
		this.verificaCliente(cpf, err);
		
		if(data.equals("") || data == null) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		}
		if(data.length() != 10) {
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
		}
	}

	public String getDebito(String cpf, String fornecedor) throws ClienteNaoExistenteException, FornecedorNaoExistenteException {
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro ao recuperar debito: cpf invalido.");
		}
		if(fornecedor.equals("") || fornecedor == null) {
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		}
		if (!this.clientes.containsKey(cpf)) {
			throw new ClienteNaoExistenteException("Erro ao recuperar debito: cliente nao existe.");
		}
		
		return String.format("%.2f",this.clientes.get(cpf).getDebito(fornecedor)).replace(",", ".");
	}

	public String exibeContas(String cpf, String fornecedor, String err) throws ClienteNaoExistenteException {
		this.verificaCliente(cpf, err);
		return this.clientes.get(cpf).exibeContas(fornecedor, err);
	}

	public String exibeContasClientes(String cpf, String err) throws ClienteNaoExistenteException {
		this.verificaCliente(cpf, err);
		return this.clientes.get(cpf).exibeContas();
	}

	public void quitaDebito(String cpf, String fornecedor, String err) throws FornecedorNaoExistenteException {
		this.clientes.get(cpf).quitaDebito(fornecedor, err);		
	}
}

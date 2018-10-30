package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteController {

	private Map<String, Cliente> clientes;

	public ClienteController() {
		this.clientes = new HashMap<>();
	}

	public String cadastraCliente(String nome, String cpf, String email, String localizacao)
			throws IllegalArgumentException, ClienteJaExistenteException {
		if (this.clientes.containsKey(cpf)) {
			throw new ClienteJaExistenteException("Erro no cadastro do cliente: cliente ja existe.");
		} else {
			this.clientes.put(cpf, new Cliente(nome, cpf, email, localizacao));
			return cpf;
		}
	}

	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.remove(cpf);
		} else {
			throw new ClienteNaoExistenteException("Erro na exibicao do cliente: cliente nao existe.");
		}
	}

	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).toString();
		} else {
			throw new ClienteNaoExistenteException("Erro na exibicao do cliente: cliente nao existe.");
		}
	}

	private List<Cliente> pegaListaClientes() {
		List<Cliente> listaDeClientes = new ArrayList<>();
		for (String e : this.clientes.keySet()) {
			listaDeClientes.add(this.clientes.get(e));
		}
		Collections.sort(listaDeClientes);
		return listaDeClientes;
	}

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
}

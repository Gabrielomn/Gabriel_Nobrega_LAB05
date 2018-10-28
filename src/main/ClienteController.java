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

	public void cadastraCliente(String nome, String cpf, String email, String localizacao) throws ClienteJaExistenteException {
		if (this.clientes.containsKey(cpf)) {
			throw new ClienteJaExistenteException();
		} else {
			this.clientes.put(cpf, new Cliente(nome, cpf, email, localizacao));
		}
	}
	
	public void removeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			this.clientes.remove(cpf);
		}
		else {
			throw new ClienteNaoExistenteException();
		}
	}
	
	public String exibeCliente(String cpf) throws ClienteNaoExistenteException {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).toString();
		}	
		else {
			throw new ClienteNaoExistenteException();
		}
	}
	private List<Cliente> pegaListaClientes() {
		List <Cliente> listaDeClientes = new ArrayList<>();
		for (String e: this.clientes.keySet()) {
			listaDeClientes.add(this.clientes.get(e));
		}
		Collections.sort(listaDeClientes);
		return listaDeClientes;
	}
	
	public String imprimeClientes() {
		String saida = "";
		List <Cliente> listaDeClientes = this.pegaListaClientes();
		for (Cliente c: listaDeClientes) {
			saida += c + "|";
		}		
		
		return saida;
	}
	
	public void setNome(String cpf, String nome) throws ClienteNaoExistenteException {
		if(this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setNome(nome);
		}
		else {
			throw new ClienteNaoExistenteException();
		}
	}
	
	public void setEmail(String cpf, String email) throws ClienteNaoExistenteException {
		if(this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setEmail(email);
		}
		else {
			throw new ClienteNaoExistenteException();
		}
	}
	public void setLocalizacao(String cpf, String localizacao) throws ClienteNaoExistenteException {
		if(this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).setLocalizacao(localizacao);
		}
		else {
			throw new ClienteNaoExistenteException();
		}
	}	
}

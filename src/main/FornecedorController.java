package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FornecedorController {
	private Map<String, Fornecedor> fornecedores;

	public FornecedorController() {
		this.fornecedores = new HashMap<>();
	}
	
	public void cadastraProduto(String nomeDoFornecedor, String nome, String descricao, double valor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException {
		if(this.fornecedores.containsKey(nomeDoFornecedor)) {
			this.fornecedores.get(nomeDoFornecedor).cadastraProduto(nome, descricao, valor);
		}
		else {
			throw new FornecedorNaoExistenteException();
		}
	}
	
	private List<Fornecedor> pegaListaFornecedores() {
		List <Fornecedor> listaDeFornecedores = new ArrayList<>();
		for (String e: this.fornecedores.keySet()) {
			listaDeFornecedores.add(this.fornecedores.get(e));
		}
		Collections.sort(listaDeFornecedores);
		return listaDeFornecedores;
	}
	
	public String exibeFornecedores() {
		String saida = "";
		List<Fornecedor> fornecedores = this.pegaListaFornecedores();
		for (Fornecedor f: fornecedores) {
			saida += f.toString() + "|";
		}
		return saida;
	}
	
	public String exibeTodosProdutosDeTodosFornecedores() {
		String saida = "";
		List<Fornecedor> fornecedores = this.pegaListaFornecedores();
		for (Fornecedor f: fornecedores) {
			saida += f.imprimeProdutos() + "|";
		}
		return saida;
	}
	
	public String exibeProduto(String nomeDoFornecedor, String nome, String descricao) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		if(this.fornecedores.containsKey(nomeDoFornecedor)) {
			return this.fornecedores.get(nomeDoFornecedor).exibeProduto(nome, descricao);
		}
		else {
			throw new FornecedorNaoExistenteException();
		}
	}
	
	public String exibeTodosProdutos(String nomeDoFornecedor) throws FornecedorNaoExistenteException, ProdutoJaCadastradoException, ProdutoNaoCadastradoException {
		if(this.fornecedores.containsKey(nomeDoFornecedor)) {
			return this.fornecedores.get(nomeDoFornecedor).imprimeProdutos();
		}
		else {
			throw new FornecedorNaoExistenteException();
		}
	}
	
	
	public void cadastraFornecedor(String nome, String numero, String email) throws FornecedorJaExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			throw new FornecedorJaExistenteException();
		} else {
			this.fornecedores.put(nome, new Fornecedor(nome, email, numero));
		}
	}

	public String exibeFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			return this.fornecedores.get(nome).toString();
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	public void deletaFornecedor(String nome) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.remove(nome);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	public void alteraEmail(String nome, String novoEmail) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setEmail(novoEmail);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

	public void alteraNumero(String nome, String novoNumero) throws FornecedorNaoExistenteException {
		if (this.fornecedores.containsKey(nome)) {
			this.fornecedores.get(nome).setNumero(novoNumero);
		} else {
			throw new FornecedorNaoExistenteException();
		}
	}

}

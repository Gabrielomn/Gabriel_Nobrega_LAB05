package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe que representa a entidade CLIENTE
 * @author gabriel
 *
 */
public class Cliente implements Comparable<Cliente> {

	private String nome;
	private String cpf;
	private String email;
	private String localizacao;
	private Map<String, List<Compra>> compras;

	/**
	 * construtor de cliente
	 * @param nome
	 * @param cpf
	 * @param email
	 * @param localizacao
	 * @throws IllegalArgumentException
	 */
	public Cliente(String nome, String cpf, String email, String localizacao) throws IllegalArgumentException {
		if ("".equals(nome) || nome == null) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		} else if (email.equals("") || email.equals(null)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		} else if (localizacao.equals("") || localizacao.equals(null)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		}
		else if (cpf.length() != 11) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf invalido.");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.localizacao = localizacao;
		this.compras = new TreeMap<>();
	}
	
	public void cadastraCompra(String fornecedor, String data, String nomeDoProduto, String descricao, double valor) {
		if (this.compras.containsKey(fornecedor)) {
			this.compras.get(fornecedor).add(new Compra(data, new IdProduto(nomeDoProduto, descricao), valor));
		}
		else {
			this.compras.put(fornecedor, new ArrayList<>());
			this.compras.get(fornecedor).add(new Compra(data, new IdProduto(nomeDoProduto, descricao), valor));

		}
	}

	/**
	 * retorna representação textual do Cliente
	 */
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	/**
	 * equals de cliente, leva em conta o cpf do cliente
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getNome() {
		return this.nome;
	}

	@Override
	public int compareTo(Cliente outro) {
		return this.nome.compareTo(outro.getNome());
	}

	/**
	 * retorna os debitos do cliente para dado fornecedor
	 * @param fornecedor
	 * @return
	 * @throws FornecedorNaoExistenteException
	 */
	public double getDebito(String fornecedor) throws FornecedorNaoExistenteException {
		if (!this.compras.containsKey(fornecedor)) {
			throw new FornecedorNaoExistenteException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		
		double soma = 0;
		for(Compra p : this.compras.get(fornecedor)) {
			soma += p.getValor();
		}
		return soma;
	}

	/**
	 * pega todas as representacao de contas com dado fornecedor
	 * @param fornecedor
	 * @return
	 */
	private String pegaContas(String fornecedor) {
		String saida = "";
		for(Compra c: this.compras.get(fornecedor)) {
			saida += c + " | ";
		}
		return saida;
	}
	
	/**
	 * chama os metodos responsaveis por pegar a representação de strings de contas, verificar a existencia
	 * e trata a string para ficar de acordo com os testes de aceitacao.
	 * @param fornecedor
	 * @param err
	 * @return
	 */
	public String exibeContas(String fornecedor, String err) {
		this.verificaExistenciaFornecedor(fornecedor, err);
		String saida = "Cliente: " + this.nome + " | " + fornecedor + " | ";
		saida += this.pegaContas(fornecedor);
		return saida.substring(0,saida.length() - 3);
	}

	/**
	 * exibe todas as contas com todos os fornecedores
	 * @return
	 */
	public String exibeContas() {
		String saida = "Cliente: " + this.nome + " | ";
		for (String f :this.compras.keySet()) {
			saida += f + " | " + this.pegaContas(f);
		}
		return saida.substring(0, saida.length() - 3);
	}
	
	/**
	 * verifica a existencia do fornecedor e lança a exceção caso nao haja o fornecedor.
	 * @param fornecedor
	 * @param err
	 */
	private void verificaExistenciaFornecedor(String fornecedor, String err) {
		if (!this.compras.containsKey(fornecedor)) {
			throw new IllegalArgumentException(err + "cliente nao tem nenhuma conta com o fornecedor.");
		}
	}

	public void quitaDebito(String fornecedor, String err) throws FornecedorNaoExistenteException {
		if (!this.compras.containsKey(fornecedor)) {
			throw new FornecedorNaoExistenteException(err + "nao ha debito do cliente associado a este fornecedor.");
		}else {
			this.compras.remove(fornecedor);
		}
		
	}

}

package main;

public class Cliente implements Comparable<Cliente> {

	private String nome;
	private String cpf;
	private String email;
	private String localizacao;

	public Cliente(String nome, String cpf, String email, String localizacao) throws IllegalArgumentException{
		if (nome.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		}
		
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.localizacao = localizacao;
	}
	
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
	
}

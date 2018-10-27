package main;

public class FornecedorNaoExistenteException extends Exception {
	
	public FornecedorNaoExistenteException(String msg) {
		super(msg);
	}
	
	public FornecedorNaoExistenteException() {
		super("Fornecedor nao existente");
	}	

}

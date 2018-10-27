package main;

public class FornecedorJaExistenteException extends Exception {
	
	public FornecedorJaExistenteException(String msg) {
		super(msg);
	}
	
	public FornecedorJaExistenteException() {
		super("Fornecedor nao existente");
	}	

}

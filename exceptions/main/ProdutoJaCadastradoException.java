package main;

public class ProdutoJaCadastradoException extends Exception {
	
	public ProdutoJaCadastradoException(String msg) {
		super(msg);
	}
	
	public ProdutoJaCadastradoException() {
		super("Produto ja cadastrado");
	}	

}

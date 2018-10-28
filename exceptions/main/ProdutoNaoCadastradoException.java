package main;

public class ProdutoNaoCadastradoException extends Exception {

	public ProdutoNaoCadastradoException(String msg) {
			super(msg);
		}

	public ProdutoNaoCadastradoException() {
			super("Fornecedor nao existente");
		}

}

package main;

public class ClienteNaoExistenteException extends Exception {
	
	public ClienteNaoExistenteException(String msg) {
		super(msg);
	}
	
	public ClienteNaoExistenteException() {
		super("Cliente nao existente");
	}	

}

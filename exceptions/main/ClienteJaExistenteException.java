package main;

public class ClienteJaExistenteException extends Exception {
	
	public ClienteJaExistenteException(String msg) {
		super(msg);
	}
	
	public ClienteJaExistenteException() {
		super("Cliente ja existente");
	}	
	
}

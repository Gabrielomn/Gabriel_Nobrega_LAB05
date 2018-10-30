package main;

public class Main {

	public static void main(String[] args) {

		System.out.println("19418510068".length());
		Cliente c = new Cliente("gabriel", "872361873", "@ccc", "ccj");
		System.out.println(c);
		Facade f = new Facade();
		
		try {
			try {
				f.adicionaCliente("","12314143100","@ccc", "dsc");
			} catch (ClienteJaExistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}

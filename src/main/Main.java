package main;

public class Main {

	public static void main(String[] args) {

		Cliente c = new Cliente("gabriel", "872361873", "@ccc", "ccj");
		System.out.println(c);
		Facade f = new Facade();
		
		try {
			try {
				f.adicionaCliente("","12312341","@ccc", "dsc");
			} catch (ClienteJaExistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}

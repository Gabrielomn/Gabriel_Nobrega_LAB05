package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClienteTest {

	@Test
	void testClienteNomeVazio() {
		try {
			Cliente c = new Cliente("", "81237182371", "8329U42", "9824221784");
		}
		catch (IllegalArgumentException iae) {
			String msg = "Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}
	
	@Test
	void testClienteCpfInvalido() {
		try {
			Cliente c = new Cliente("Gabriel", "142141", "8329U42", "9824221784");
		}
		catch (IllegalArgumentException iae) {
			String msg = "Erro no cadastro do cliente: cpf invalido.";
			assertEquals(msg, iae.getMessage());
		}
	}
	
	@Test
	void testClienteEmailVazio() {
		try {
			Cliente c = new Cliente("Gabriel", "14214132123", "", "9824221784");
		}
		catch (IllegalArgumentException iae) {
			String msg = "Erro no cadastro do cliente: email nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testClienteLocalizacaoVazia() {
		try {
			Cliente c = new Cliente("Gabriel", "14214132123", "@ccc", "");
		}
		catch (IllegalArgumentException iae) {
			String msg = "Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.";
			assertEquals(msg, iae.getMessage());
		}
	}
	
	@Test
	void testToString() {
		Cliente c = new Cliente("Gabriel", "21412319231", "@ccc", "LCC2");
		String msg = "Gabriel - LCC2 - @ccc";
		assertEquals(msg, c.toString());
	}
	
	@Test
	void testEquals() {
		Cliente c = new Cliente("Gabriel", "21412319231", "@ccc", "LCC2");
		Cliente b = new Cliente("Biel", "21412319231", "@gmail", "Sweethomealabama");
		assertTrue(c.equals(b));
	}
	
	@Test
	void testEqualsFalse() {
		Cliente c = new Cliente("Gabriel", "21412319231", "@ccc", "LCC2");
		Cliente b = new Cliente("Gabriel", "12121212112", "@ccc", "LCC2");
		assertFalse(c.equals(b));
	}
	
}

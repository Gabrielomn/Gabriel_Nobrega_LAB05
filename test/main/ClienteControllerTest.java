package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteControllerTest {

	private ClienteController c;

	@BeforeEach
	void setup() {
		this.c = new ClienteController();
	}

	@Test
	void testCadastraNomeVazio() {

		try {
			this.c.cadastraCliente("", "12314143100", "victor@ccc.ufcg.edu.br", "victor@ccc.ufcg.edu.br");
			fail("nops");
		} catch (ClienteJaExistenteException e) {

		}

		catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}

	}
}

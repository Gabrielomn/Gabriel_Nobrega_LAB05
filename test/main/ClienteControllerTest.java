package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
	}

	@Test
	void testCadastraClienteRepetido() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.cadastraCliente("Biel", "21412319231", "@gmail", "Sweethomealabama");
			fail("nao deveria chegar aqui");
		} catch (ClienteJaExistenteException cjee) {
			String msg = "Erro no cadastro do cliente: cliente ja existe.";
			assertEquals(msg, cjee.getMessage());
		}
	}

	@Test
	void testRemoveCliente() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.removeCliente("21412319231");
			this.c.exibeCliente("21412319231");
		} catch (ClienteJaExistenteException e) {
			fail("cliente nao deveria existir antes do teste");
		} catch (ClienteNaoExistenteException cnee) {
			String msg = "Erro na exibicao do cliente: cliente nao existe.";
			assertEquals(msg, cnee.getMessage());
		}
	}

	@Test
	void testRemoveClienteNaoExistente() {
		try {
			this.c.removeCliente("21412319231");
		} catch (ClienteNaoExistenteException cnee) {
			String msg = "Erro na exibicao do cliente: cliente nao existe.";
			assertEquals(msg, cnee.getMessage());
		}
	}

	@Test
	void testExibeCliente() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			String msg = "Gabriel - LCC2 - @ccc";
			assertEquals(msg, this.c.exibeCliente("21412319231"));
		} catch (ClienteJaExistenteException e) {
			fail("cliente nao deveria existir antes do teste");
		} catch (ClienteNaoExistenteException cnee) {
			fail("cliente deveria existir");
		}
	}

	@Test
	void testImprimeClientes() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.cadastraCliente("Felipe", "13239231121", "@ccc", "LCC2");
			this.c.cadastraCliente("Matheus", "21412312923", "@ccc", "LSD");
			String msg = "Felipe - LCC2 - @ccc | Gabriel - LCC2 - @ccc | Matheus - LSD - @ccc";
			assertEquals(msg, this.c.imprimeClientes());
		} catch (ClienteJaExistenteException e) {
			fail("clientes nao deveriam existir antes do teste");
		}
	}

	@Test
	void testEditaClienteNome() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.editaCliente("21412319231", "nome", "biel");
			String msg = "biel - LCC2 - @ccc";
			assertEquals(msg, this.c.exibeCliente("21412319231"));
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}
	}
	
	@Test
	void testEditaClienteEmail() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@gmail", "LCC2");
			this.c.editaCliente("21412319231", "email", "@ccc");
			String msg = "Gabriel - LCC2 - @ccc";
			assertEquals(msg, this.c.exibeCliente("21412319231"));
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}
	}
	
	@Test
	void testEditaClienteLocalizacao() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.editaCliente("21412319231", "localizacao", "LCC3");
			String msg = "Gabriel - LCC3 - @ccc";
			assertEquals(msg, this.c.exibeCliente("21412319231"));
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}
	}
	
	@Test
	void testEditaClienteNaoExistente() {
		try {
			this.c.editaCliente("21412319231", "localizacao", "LCC3");
			fail("cliente nao deveria existir");
		} catch (ClienteNaoExistenteException e) {
			String msg = "Erro na edicao do cliente: cliente nao existe.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test
	void testEditaClienteAtributoVazio() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.editaCliente("21412319231", "", "LCC3");
			fail("atributo vazio, deveria ter chamado a exceção");
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}catch(IllegalArgumentException iae){
			String msg = "Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());

		}
	}
	
	@Test
	void testEditaClienteNovoValorVazio() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.editaCliente("21412319231", "localizacao", "");
			fail("atributo vazio, deveria ter chamado a exceção");
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}catch(IllegalArgumentException iae){
			String msg = "Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());

		}
	}
	
	@Test
	void testEditaClienteAtributoNaoExistente() {
		try {
			this.c.cadastraCliente("Gabriel", "21412319231", "@ccc", "LCC2");
			this.c.editaCliente("21412319231", "projeto", "splab");
			fail("atributo nao existente, deveria ter chamado a exceção");
		} catch (ClienteJaExistenteException cjee) {
			fail("cliente nao existia antes");
		} catch (ClienteNaoExistenteException e) {
			fail("cliente existe no momento da edicao");
		}catch(IllegalArgumentException iae){
			String msg = "Erro na edicao do cliente: atributo nao existe.";
			assertEquals(msg, iae.getMessage());

		}
	}
	
}

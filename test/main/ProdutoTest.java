package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoTest {
	private Produto p;
	
	@BeforeEach
	void setup() {
		this.p = new Produto("ps4","melhorquexcaixa", 1399.99);
	}
	
	@Test
	void testSetPrecoNegativo() {
		try {
			this.p.setPreco(-1);
		}catch(IllegalArgumentException iae) {
			String msg = "Erro na edicao de produto: preco invalido.";
			assertEquals(msg, iae.getMessage());
		}
	}
	
	@Test
	void testSetPreco() {
		try {
			this.p.setPreco(2);
		}catch(IllegalArgumentException iae) {
			fail("deveria ter passado");
		}
		
		try {
			String msg = "ps4 - melhorquexcaixa - R$2,00";
			assertEquals(msg, this.p.toString());
		}catch(Exception e) {
			fail("nao deveria lançar excecao");
		}
	}
	
	@Test
	void testEqualsTrue() {
		assertTrue(this.p.equals(new Produto("ps4", "melhorquexcaixa", 999.99)));
	}
	
	@Test
	void testEqualsFalse() {
		assertFalse(this.p.equals(new Produto("xcaixa", "ps4 e pior", 2999.99)));
	}
}

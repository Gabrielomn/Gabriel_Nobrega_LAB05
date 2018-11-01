package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FornecedorTest {
	private Fornecedor a;
	
	@BeforeEach
	void setup() {
		this.a = new Fornecedor("Gabriel", "@ccc", "9999999");

	}
	
	@Test
	void testImprimeProdutos() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.cadastraProduto("borracha", "borracha default", 1.2);
			this.a.cadastraProduto("alien", "EXATAMENTE, UM ALIEN", 99999999999.99);
			this.a.cadastraProduto("xbox", "xcaixa", 1249.99);
			String msg = "Gabriel - alien - EXATAMENTE, UM ALIEN - R$99999999999,99 | Gabriel - borracha - borracha default - R$1,20 | Gabriel - lapis - lapis default - R$0,99 | Gabriel - xbox - xcaixa - R$1249,99";
			assertEquals(msg, this.a.imprimeProdutos());
		} catch (ProdutoJaCadastradoException pjce) {
			fail("produto deveria nao estar cadastrado");
		}
	}
	
	@Test
	void testToString() {
		String msg = "Gabriel - @ccc - 9999999";
		assertEquals(msg, this.a.toString());
	}
	
	@Test
	void testCadastraProdutoEexibe() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			String msg = "lapis - lapis default - R$0,99";
			assertEquals(msg, this.a.exibeProduto("lapis", "lapis default"));
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao deveria existir antes");
		} catch (ProdutoNaoCadastradoException e) {
			fail("no momento de exibir deveria existir o produto");
		}
		
	}
	
	@Test
	void testCadastraProdutoRepetido() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.cadastraProduto("lapis", "lapis default", 3.99);
		} catch (ProdutoJaCadastradoException e) {
			String msg = "Erro no cadastro de produto: produto ja existe.";
			assertEquals(msg, e.getMessage());
		}
		
	}
	
	@Test
	void testExibeProdutoNaoExistente() {
		try {	
			this.a.exibeProduto("lapis", "lapis default");
		} catch (ProdutoNaoCadastradoException e) {
			String msg = "Erro na exibicao de produto: produto nao existe.";
			assertEquals(msg, e.getMessage());
		}
		
	}
	
	@Test
	void testCadastraExibeProdutoDescricaoVazia() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
		this.a.exibeProduto("lapis", "");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao deveria existir antes");
		} catch (ProdutoNaoCadastradoException e) {
			fail("no momento de exibir deveria existir o produto");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.";
			assertEquals(msg, iae.getMessage());
		}
		
	}
	
	@Test
	void testCadastraExibeProdutoNomeVazia() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
		this.a.exibeProduto("", "lapis default");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao deveria existir antes");
		} catch (ProdutoNaoCadastradoException e) {
			fail("no momento de exibir deveria existir o produto");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
		
	}
	
	@Test
	void testCadastraFornecedorNomeVazio() {
		try {
			Fornecedor fail = new Fornecedor("", "@ccc", "2492512");
		}
		catch(IllegalArgumentException iae) {
			String msg = "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}
	
	@Test
	void testEquals() {
		Fornecedor f1 = new Fornecedor("gabriel", "@ccc", "2492512");
		Fornecedor f2 = new Fornecedor("gabriel", "@gmail", "123151512");
		assertTrue(f1.equals(f2));
	}
	
	@Test
	void testEqualsFalse() {
		Fornecedor f1 = new Fornecedor("gabriel", "@ccc", "2492512");
		Fornecedor f2 = new Fornecedor("biel", "@ccc", "2492512");
		assertFalse(f1.equals(f2));
	}
	
	@Test
	void testEditaProdutoDescricaoVazio() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.editaProduto("lapis", "", 9213);
			fail("descricao vazio, deveria ter chamado a exceção");
		}catch(IllegalArgumentException iae){
			String msg = "Erro na edicao de produto: descricao nao pode ser vazia ou nula.";
			assertEquals(msg, iae.getMessage());
		} catch (ProdutoNaoCadastradoException e) {
			fail("produto existe no momento da edicao");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao existe antes da sua instanciacao no test");
		}
	}
	
	@Test
	void testEditaProdutoNomeVazio() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.editaProduto("", "lapis default", 9213);
			fail("nome vazio, deveria ter chamado a exceção");
		}catch(IllegalArgumentException iae){
			String msg = "Erro na edicao de produto: nome nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		} catch (ProdutoNaoCadastradoException e) {
			fail("produto existe no momento da edicao");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao existe antes da sua instanciacao no test");
		}
	}
	
	@Test
	void testEditaProdutoNaoExistente() {
		try {
			this.a.editaProduto("lapis", "lapis default", 9213);
			fail("produto nao existente, deveria ter chamado a exceção");
		}catch(IllegalArgumentException iae){
			fail("argumentos passaram da forma correta");

		} catch (ProdutoNaoCadastradoException e) {
			String msg = "Erro na edicao de produto: produto nao existe.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test
	void testEditaProduto() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.editaProduto("lapis", "lapis default", 9213);
			String msg = "lapis - lapis default - R$9213,00";
			assertEquals(msg, this.a.exibeProduto("lapis","lapis default"));
		}catch(IllegalArgumentException iae){
			fail("argumentos passaram da forma correta");

		} catch (ProdutoNaoCadastradoException e) {
			fail("produto existe no momento da edicao");

		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao existe antes da sua instanciacao no test");

		}
	}
	
	@Test
	void testRemoveProduto() {
		try {
			this.a.cadastraProduto("lapis", "lapis default", 0.99);
			this.a.removeProduto("lapis", "lapis default");
			
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao existe antes da sua instanciacao no test");
		} catch (ProdutoNaoCadastradoException e) {
			fail("produto existe no momento da remocao");
		}
		try {
			this.a.exibeProduto("lapis", "lapis default");
		} catch (ProdutoNaoCadastradoException e) {
			String msg = "Erro na exibicao de produto: produto nao existe.";
			assertEquals(msg, e.getMessage());
		}

	}
}

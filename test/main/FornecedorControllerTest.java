package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FornecedorControllerTest {

	private FornecedorController f;

	@BeforeEach
	void setup() {
		this.f = new FornecedorController();
	}

	@Test
	void testCadastraFornecedorRepetido() {
		try {
			this.f.cadastraFornecedor("Gabriel", "123124", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lançar nenhuma exceção");
		}

		try {
			this.f.cadastraFornecedor("Gabriel", "29393939", "@gmail");
		} catch (FornecedorJaExistenteException fjee) {
			String msg = "Erro no cadastro de fornecedor: fornecedor ja existe.";
			assertEquals(msg, fjee.getMessage());
		}
	}

	@Test
	void testExibeFornecedor() {
		try {
			this.f.cadastraFornecedor("Gabriel", "123124", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lançar nenhuma exceção");
		}

		try {
			String msg = "Gabriel - @ccc - 123124";
			assertEquals(msg, this.f.exibeFornecedor("Gabriel"));
		} catch (Exception e) {
			fail("nao deveria lançar exceção");
		}

	}

	@Test
	void testExibeFornecedorNaoExistente() {
		try {
			this.f.exibeFornecedor("Gabriel");
			fail("deveria lançar exceção");
		} catch (FornecedorNaoExistenteException fnee) {
			String msg = "Erro na exibicao do fornecedor: fornecedor nao existe.";
			assertEquals(msg, fnee.getMessage());
		}

	}

	@Test
	void testImprimirFornecedores() {
		FornecedorController a = new FornecedorController();
		try {
			a.cadastraFornecedor("Thaynnara", "333333333", "@ccc2");
			a.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			a.cadastraFornecedor("Felipe", "222222222", "@ccc3");
			a.cadastraFornecedor("Joao", "24242424242", "@ccc4");
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedores nao deveriam existir");
		}
		String msg = "Felipe - @ccc3 - 222222222 | Gabriel - @ccc - 999999999 | Joao - @ccc4 - 24242424242 | Thaynnara - @ccc2 - 333333333";
		assertEquals(msg, a.exibeFornecedores());

	}

	@Test
	void testImprimeTodosProdutos() {
		FornecedorController a = new FornecedorController();
		try {
			a.cadastraFornecedor("Thaynnara", "@ccc2", "33333333");
			a.cadastraFornecedor("Gabriel", "@ccc", "9999999");
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedores nao deveriam existir");
		}

		try {
			a.cadastraProdutoSimples("Gabriel", "lapis", "lapis default", 0.99);
			a.cadastraProdutoSimples("Gabriel", "borracha", "borracha default", 1.2);
			a.cadastraProdutoSimples("Gabriel", "alien", "EXATAMENTE, UM ALIEN", 99999999999.99);
			a.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
			a.cadastraProdutoSimples("Thaynnara", "naruto e1", "manga de naruto primeira edicao", 20.99);
			a.cadastraProdutoSimples("Thaynnara", "naruto ultimate battle", "Ciro uzumaki vs Bolsonaro Uchiha",
					Integer.MAX_VALUE);
			String msg = "Gabriel - alien - EXATAMENTE, UM ALIEN - R$99999999999,99 | Gabriel - borracha - borracha default - R$1,20 | Gabriel - lapis - lapis default - R$0,99 | Gabriel - xbox - xcaixa - R$1249,99 | Thaynnara - naruto e1 - manga de naruto primeira edicao - R$20,99 | Thaynnara - naruto ultimate battle - Ciro uzumaki vs Bolsonaro Uchiha - R$2147483647,00";
			assertEquals(msg, a.exibeTodosProdutosDeTodosFornecedores());
		} catch (ProdutoJaCadastradoException pjce) {
			fail("produto deveria nao estar cadastrado");
		} catch (FornecedorNaoExistenteException fnee) {
			fail("fornecedor deveria existir");
		}
	}

	@Test
	void testExibeProduto() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedor nao deveria existir antes da instanciacao");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir no momento do cadastro do produto");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto nao estava cadastrado antes");
		}

		String msg = "xbox - xcaixa - R$1249,99";
		try {
			assertEquals(msg, this.f.exibeProduto("Gabriel", "xbox", "xcaixa"));
		} catch (FornecedorNaoExistenteException e) {
			fail("");
		} catch (ProdutoNaoCadastradoException e) {
			fail("");
		}

	}

	@Test
	void testExibeProdutoFornecedorVazio() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
		} catch (FornecedorJaExistenteException e1) {
			fail("");
		} catch (FornecedorNaoExistenteException e) {
			fail("");
		} catch (ProdutoJaCadastradoException e) {
			fail("");
		}

		try {
			this.f.exibeProduto("", "xbox", "xcaixa");
		} catch (FornecedorNaoExistenteException e) {
			fail("");
		} catch (ProdutoNaoCadastradoException e) {
			fail("");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testExibeProdutoFornecedorNaoExistente() {
		try {
			this.f.exibeProduto("gabriel", "ttofaz", "ttofaz");
		} catch (FornecedorNaoExistenteException fnee) {
			String msg = "Erro na exibicao de produto: fornecedor nao existe.";
			assertEquals(msg, fnee.getMessage());
		} catch (ProdutoNaoCadastradoException e) {
			fail("not the point");
		}
	}

	@Test
	void testExibeTodosOsProdutos() {
		try {
			this.f.cadastraFornecedor("Thaynnara", "@ccc2", "33333333");
			this.f.cadastraFornecedor("Gabriel", "@ccc", "9999999");
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedores nao deveriam existir");
		}

		try {
			this.f.cadastraProdutoSimples("Gabriel", "lapis", "lapis default", 0.99);
			this.f.cadastraProdutoSimples("Gabriel", "borracha", "borracha default", 1.2);
			this.f.cadastraProdutoSimples("Gabriel", "alien", "EXATAMENTE, UM ALIEN", 99999999999.99);
			this.f.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} catch (ProdutoJaCadastradoException e) {
			fail("produto deveria nao estar cadastrado");
		}
		String msg = "Gabriel - alien - EXATAMENTE, UM ALIEN - R$99999999999,99 | Gabriel - borracha - borracha default - R$1,20 | Gabriel - lapis - lapis default - R$0,99 | Gabriel - xbox - xcaixa - R$1249,99";

		try {
			assertEquals(msg, this.f.exibeTodosProdutos("Gabriel"));
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} 
	}

	@Test
	void testRemoveFornecedor() {
		try {
			this.f.cadastraFornecedor("Thaynnara", "@ccc2", "33333333");
			this.f.cadastraFornecedor("Gabriel", "@ccc", "9999999");
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedores nao deveriam existir");
		}
		try {
			this.f.deletaFornecedor("Gabriel");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir nesse momento");
		}
		try {
			this.f.exibeFornecedor("Gabriel");
			fail("fornecedor nao existe");
		} catch (FornecedorNaoExistenteException fnee) {
			String msg = "Erro na exibicao do fornecedor: fornecedor nao existe.";
			assertEquals(msg, fnee.getMessage());
		}
	}

	@Test
	void testRemoveFornecedorVazio() {
		try {
			this.f.deletaFornecedor("");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir nesse momento");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testRemoveFornecedorNaoExistente() {
		try {
			this.f.deletaFornecedor("Gabriel");
		} catch (FornecedorNaoExistenteException e) {
			String msg = "Erro na remocao do fornecedor: fornecedor nao existe.";
			assertEquals(msg, e.getMessage());
		} catch (IllegalArgumentException iae) {
			fail("fornecedor deveria existir nesse momento");
		}
	}

	@Test
	void testEditaFornecedorEmail() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.editaFornecedor("Gabriel", "email", "@gmail");
			String msg = "Gabriel - @gmail - 999999999";
			assertEquals(msg, this.f.exibeFornecedor("Gabriel"));
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
	}

	@Test
	void testEditaFornecedorTelefone() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.editaFornecedor("Gabriel", "telefone", "314123");
			String msg = "Gabriel - @ccc - 314123";
			assertEquals(msg, this.f.exibeFornecedor("Gabriel"));
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
	}

	@Test
	void testEditaFornecedorAtributoVazio() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		try {
			this.f.editaFornecedor("Gabriel", "", "314123");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testEditaFornecedorNovoValorVazio() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		try {
			this.f.editaFornecedor("Gabriel", "telefone", "");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testEditaFornecedorNome() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		try {
			this.f.editaFornecedor("Gabriel", "nome", "onlok");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na edicao do fornecedor: nome nao pode ser editado.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testEditaAtributoNaoExistente() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		try {
			this.f.editaFornecedor("Gabriel", "apelido", "onlok");
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor deveria existir");
		} catch (IllegalArgumentException iae) {
			String msg = "Erro na edicao do fornecedor: atributo nao existe.";
			assertEquals(msg, iae.getMessage());
		}
	}

	@Test
	void testEditaProduto() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
			this.f.editaProduto("xbox", "xcaixa", "Gabriel", 999.99);
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		String msg = "xbox - xcaixa - R$999,99";
		try {
			assertEquals(msg, this.f.exibeProduto("Gabriel", "xbox", "xcaixa"));
		} catch (FornecedorNaoExistenteException | ProdutoNaoCadastradoException e) {
			fail("nao deveria lancar nenhuma excecao");
		}
	}

	
	@Test
	void testRemoveProduto() {
		try {
			this.f.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			this.f.cadastraProdutoSimples("Gabriel", "xbox", "xcaixa", 1249.99);
			this.f.removeProduto("xbox", "xcaixa", "Gabriel");
		} catch (Exception e) {
			fail("nao deveria lancar nenhuma exceção");
		}
		String msg = "Erro na exibicao de produto: produto nao existe.";
		try {
			this.f.exibeProduto("Gabriel", "xbox", "xcaixa");
		} catch (ProdutoNaoCadastradoException pnce) {
			assertEquals(msg, pnce.getMessage());
		} catch (FornecedorNaoExistenteException e) {
			fail("fornecedor existe");
		}
	}
}
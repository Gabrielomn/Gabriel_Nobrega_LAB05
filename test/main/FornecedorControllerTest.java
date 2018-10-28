package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FornecedorControllerTest {
	
	@Test
	void testImprimirFornecedores() {
		FornecedorController a = new FornecedorController();
		try {
			a.cadastraFornecedor("Thaynnara", "333333333", "@ccc2");
			a.cadastraFornecedor("Gabriel", "999999999", "@ccc");
			a.cadastraFornecedor("Felipe", "222222222" , "@ccc3");
			a.cadastraFornecedor("Joao", "24242424242" , "@ccc4");
		} catch (FornecedorJaExistenteException e) {
			fail("fornecedores nao deveriam existir");
		}
		
		assertEquals("Felipe - @ccc3 - 222222222|Gabriel - @ccc - 999999999|Joao - @ccc4 - 24242424242|Thaynnara - @ccc2 - 333333333|",a.exibeFornecedores());
		
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
			a.cadastraProduto("Gabriel","lapis", "lapis default", 0.99);
			a.cadastraProduto("Gabriel","borracha", "borracha default", 1.2);
			a.cadastraProduto("Gabriel","alien", "EXATAMENTE, UM ALIEN", 99999999999.99);
			a.cadastraProduto("Gabriel", "xbox", "xcaixa", 1249.99);
			a.cadastraProduto("Thaynnara", "naruto e1", "manga de naruto primeira edicao", 20.99);
			a.cadastraProduto("Thaynnara","naruto ultimate battle", "Ciro uzumaki vs Bolsonaro Uchiha", Integer.MAX_VALUE);
			String msg = "Gabriel - alien - EXATAMENTE, UM ALIEN - R$99999999999,99|Gabriel - borracha - borracha default - R$1,20|Gabriel - lapis - lapis default - R$0,99|Gabriel - xbox - xcaixa - R$1249,99||Thaynnara - naruto e1 - manga de naruto primeira edicao - R$20,99|Thaynnara - naruto ultimate battle - Ciro uzumaki vs Bolsonaro Uchiha - R$2147483647,00||";
			assertEquals(msg, a.exibeTodosProdutosDeTodosFornecedores());
		} catch (ProdutoJaCadastradoException pjce) {
			fail("produto deveria nao estar cadastrado");
		} catch (FornecedorNaoExistenteException fnee) {
			fail("fornecedor deveria existir");
		}
	}
}

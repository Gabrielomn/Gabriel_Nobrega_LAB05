package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FornecedorTest {

	@Test
	void testImprimeProdutos() {
		Fornecedor a = new Fornecedor("Gabriel", "@ccc", "9999999");
		try {
			a.cadastraProduto("lapis", "lapis default", 0.99);
			a.cadastraProduto("borracha", "borracha default", 1.2);
			a.cadastraProduto("alien", "EXATAMENTE, UM ALIEN", 99999999999.99);
			a.cadastraProduto("xbox", "xcaixa", 1249.99);
			String msg = "Gabriel - alien - EXATAMENTE, UM ALIEN - R$99999999999,99|Gabriel - borracha - borracha default - R$1,20|Gabriel - lapis - lapis default - R$0,99|Gabriel - xbox - xcaixa - R$1249,99|";
			assertEquals(msg, a.imprimeProdutos());
		} catch (ProdutoJaCadastradoException pjce) {
			fail("produto deveria nao estar cadastrado");
		}
	}
}

package main;

import java.util.Comparator;

/**
 * classe responsavel por comparar duas compras levando o fornecedor como criterio.
 * @author gabriel
 *
 */
public class FornecedorComparator implements Comparator<Compra> {

	public int compare(Compra c1, Compra c2) {
		if (!c1.getNomeFornecedor().equals(c2.getNomeFornecedor())) {
			return c1.getNomeFornecedor().compareTo(c2.getNomeFornecedor());
		} else {
			String v1 = (c1.getNomeCliente() + c1.getDescricao() + c1.getData());
			String v2 = (c2.getNomeCliente() + c2.getDescricao() + c2.getData());
			return v1.compareTo(v2);
		}
	}
}

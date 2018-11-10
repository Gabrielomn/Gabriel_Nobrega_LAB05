package main;

import java.util.Comparator;

public class NomeComparator implements Comparator<Compra> {

	public int compare(Compra c1, Compra c2) {
		if (!c1.getNomeCliente().equals(c2.getNomeCliente())) {
			return c1.getNomeCliente().compareTo(c2.getNomeCliente());
		}else {
			String v1 = (c1.getNomeFornecedor() + c1.getDescricao() + c1.getData());
			String v2 = (c2.getNomeFornecedor() + c2.getDescricao() + c2.getData());
			return v1.compareTo(v2);
		}
	}
}

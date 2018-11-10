package main;

import java.util.Comparator;

public class DataComparator implements Comparator<Compra> {

	public int compare(Compra c1, Compra c2) {
		String[] data1 = c1.getData().split("/");
		String[] data2 = c2.getData().split("/");

		if (!data1[2].equals(data2[2])) {
			return data1[2].compareTo(data2[2]);
		} else if (!data1[1].equals(data2[1])) {
			return data1[1].compareTo(data2[1]);
		} else if (!data1[0].equals(data2[0])) {
			return data1[0].compareTo(data2[0]);
		}else {
			String v1 = (c1.getNomeCliente() + c1.getNomeFornecedor() + c1.getDescricao());
			String v2 = (c2.getNomeCliente() + c2.getNomeFornecedor() + c2.getDescricao());
			return v1.compareTo(v2);
		}
	}
}

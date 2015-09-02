package com.sistema.orientacao;

import java.util.Comparator;

public class OrientacaoComparator implements Comparator<Orientacao> {

	@Override
	public int compare(Orientacao one, Orientacao another) {
		return another.getData().compareTo(one.getData());
	}
}

package com.sistema.atividade;

import java.util.Comparator;

public class AtividadeComparator implements Comparator<Atividade> {

	@Override
	public int compare(Atividade one, Atividade another) {
		return one.getData().compareTo(another.getData());
	}

}

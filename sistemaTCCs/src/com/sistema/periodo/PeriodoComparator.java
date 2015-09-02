package com.sistema.periodo;

import java.util.Comparator;

public class PeriodoComparator implements Comparator<Periodo> {

	@Override
	public int compare(Periodo one, Periodo another) {
		return another.getNome().compareTo(one.getNome());
	}

}

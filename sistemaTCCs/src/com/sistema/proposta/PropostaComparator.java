package com.sistema.proposta;

import java.util.Comparator;

public class PropostaComparator implements Comparator<Proposta>{

	@Override
	public int compare(Proposta one, Proposta another) {
		return another.getDataCadastro().compareTo(one.getDataCadastro());
	}

}

package com.sistema.mensagem;

import java.util.Comparator;

public class MensagemComparator implements Comparator<Mensagem>{

	@Override
	public int compare(Mensagem one, Mensagem another) {
		return another.getData().compareTo(one.getData());
	}

}

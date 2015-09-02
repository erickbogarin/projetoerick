package com.sistema.orientacao;

import java.util.List;

public interface OrientacaoDAO {

	void salvar(Orientacao orientacao);

	List<Orientacao> listar(Integer aluno);
	
}

package com.sistema.quadro;

import java.util.List;

public interface QuadroDAO {

	void salvar(Quadro quadro);

	void excluir(Quadro quadro);

	List<Quadro> listar(Integer proposta);

}

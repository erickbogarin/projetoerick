package com.sistema.usuario.orientador;

import java.util.List;

public interface OrientadorDAO {

	public void salvar(Orientador professor);

	public List<Orientador> listar();

	public void excluir(Orientador professor);

	public void alterar(Orientador professor);

	public List<Orientador> findUserByName(String nome);

	public Orientador findById(Integer id);
	
}

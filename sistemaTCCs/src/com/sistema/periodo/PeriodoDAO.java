package com.sistema.periodo;

import java.util.List;

public interface PeriodoDAO {
	
	public void salvar(Periodo cronograma);
	
	public void excluir(Periodo cronograma);
	
	public void alterar(Periodo cronograma);
	
	public List<Periodo> listar();

	public Periodo cronogramaAtual();

	Periodo pesquisarPorCodigo(Integer codigo);

}

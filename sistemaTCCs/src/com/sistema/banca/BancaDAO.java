package com.sistema.banca;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;

public interface BancaDAO {

	public void salvar(Banca banca);

	public void excluir(Banca banca);

	public Banca banca(Integer aluno);
	
	public List<Banca> apresentacoes(Coordenacao coordenacao, Integer periodo);

	public List<Banca> orientados(Integer orientador, Integer periodo);

	public List<Banca> listar(Periodo periodo);
	
}

package com.sistema.atividade;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;

public interface AtividadeDAO {
	
	public void salvar(Atividade atividade);
	
	public void excluir(Atividade atividade);
	
	public void alterar(Atividade atividade);
	
	public List<Atividade> listar(Integer id, Coordenacao coordenacao, Disciplina disciplina);
	
}

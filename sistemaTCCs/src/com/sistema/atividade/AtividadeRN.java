package com.sistema.atividade;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;
import com.sistema.util.DAOFactory;

public class AtividadeRN {
	
	private AtividadeDAO atividadeDAO;
	
	public AtividadeRN() {
		this.atividadeDAO = DAOFactory.criaAtividadeDAO();
	}

	public void salvar(Atividade atividade) {
		this.atividadeDAO.salvar(atividade);	
	}

	public List<Atividade> listar(Integer id, Coordenacao coordenacao, Disciplina disciplina) {
		return this.atividadeDAO.listar(id, coordenacao, disciplina);
	}

	public void excluir(Atividade atividade) {
		this.atividadeDAO.excluir(atividade);	
	}
}

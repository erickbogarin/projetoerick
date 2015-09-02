package com.sistema.disciplina;

import java.util.List;

import com.sistema.util.DAOFactory;

public class DisciplinaRN {
	
	private DisciplinaDAO disciplinaDAO;
	
	public DisciplinaRN() {
		this.disciplinaDAO = DAOFactory.criaDisciplinaDAO();
	}
	
	public void salvar(Disciplina disciplina) {
		this.disciplinaDAO.salvar(disciplina);
	}
	
	public List<Disciplina> disciplinas() {
		return this.disciplinaDAO.disciplinas();
	}

	public Disciplina buscaPorId(Integer disciplinaId) {
		return this.disciplinaDAO.consultarDisciplina(disciplinaId);
	}

	public Disciplina findById(Integer disciplinaId) {
		return this.disciplinaDAO.consultarDisciplina(disciplinaId);
	}
	
}
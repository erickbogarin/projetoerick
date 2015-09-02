package com.sistema.curso;

import java.util.List;

import com.sistema.util.DAOFactory;

public class CursoRN {
	
	CursoDAO cursoDAO;
	
	public CursoRN() {
		this.cursoDAO = DAOFactory.criaCursoDAO();
	}

	public void salvar(Curso curso) {
		this.cursoDAO.salvar(curso);
	}

	public Curso findById(Integer cursoId) {
		return this.cursoDAO.findById(cursoId);
	}

	public List<Curso> cursos() {
		return this.cursoDAO.cursos();
	}

	public void alterar(Curso curso) {
		this.cursoDAO.alterar(curso);
	}

	public void deletar(Curso curso) {
		this.cursoDAO.excluir(curso);
	}

	public void desassociar(Curso curso) {
		this.cursoDAO.desassociar(curso);
	}
	
}

package com.sistema.usuario.orientador;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.proposta.Proposta;
import com.sistema.util.DAOFactory;

public class OrientadorRN {
	
	private OrientadorDAO professorDAO;
	
	public OrientadorRN() {
		this.professorDAO = DAOFactory.criaProfessorDAO();
	}
	
	public void salvar(Orientador orientador) {
		this.professorDAO.salvar(orientador);
	}
	
	public List<Orientador> listar() {
		return this.professorDAO.listar();
	}
	
	public void excluir(Orientador professor) {
		this.professorDAO.excluir(professor);
	}
	
	public List<Orientador> findUser(String nome){
		return this.professorDAO.findUserByName(nome);
	}
	
	public void alterar(Orientador professor) {		
		this.professorDAO.alterar(professor);
	}
	
	public Orientador findById(Integer id) {
		return this.professorDAO.findById(id);
	}
	
	public List<Proposta> orientados(Integer id) {
		return this.professorDAO.orientados(id);
	}

	public List<Orientador> orientadoresDaCoordenacao(Coordenacao coordenacao) {
		return this.professorDAO.orientadoresDaCoordenacao(coordenacao);
	}
	
}

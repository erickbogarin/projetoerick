package com.sistema.usuario.coordenador;

import java.util.List;

import com.sistema.util.DAOFactory;

public class CoordenadorRN {
	
	private CoordenadorDAO coordenadorDAO;
	
	public CoordenadorRN() {
		this.coordenadorDAO = DAOFactory.criaCoordenadorDAO();
	}
	
	public Coordenador findById(Integer id) {
		return this.coordenadorDAO.findById(id);
	}

	public List<Coordenador> findUser(String nome) {
		return this.coordenadorDAO.findUserByName(nome);
	}

	public List<Coordenador> listar() {
		return this.coordenadorDAO.listar();
	}

	public void alterar(Coordenador coordenadorSelecionado) {
		this.coordenadorDAO.alterar(coordenadorSelecionado);
	}

	public void excluir(Coordenador coordenador) {
		this.coordenadorDAO.excluir(coordenador);
	}

	public List<Coordenador> coordenadores(String coordenacao, String disciplina) {
		return this.coordenadorDAO.coordenadores(coordenacao, disciplina);
	}

	public void desassociar(Coordenador coordenador) {
		this.coordenadorDAO.desassociar(coordenador);
	}

}

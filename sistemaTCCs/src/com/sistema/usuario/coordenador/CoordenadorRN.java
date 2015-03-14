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

}

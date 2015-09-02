package com.sistema.quadro;

import java.util.List;

import com.sistema.util.DAOFactory;

public class QuadroRN {
	
	private QuadroDAO quadroDAO;
	
	public QuadroRN() {
		this.quadroDAO = DAOFactory.criaQuadroDAO();
	}

	public void salvar(Quadro quadro) {
		this.quadroDAO.salvar(quadro);
	}

	public void excluir(Quadro quadro) {
		this.quadroDAO.excluir(quadro);
	}

	public List<Quadro> listar(Integer proposta) {
		return this.quadroDAO.listar(proposta);
	}
	
	
	
}

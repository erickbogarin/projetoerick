package com.sistema.orientacao;

import java.util.List;

import com.sistema.util.DAOFactory;

public class OrientacaoRN {
	
	private OrientacaoDAO orientacaoDAO;
	
	public OrientacaoRN() {
		this.orientacaoDAO = DAOFactory.criaOrientacaoDAO();
	}

	public void salvar(Orientacao orientacao) {
		this.orientacaoDAO.salvar(orientacao);
	}

	public List<Orientacao> lista(Integer aluno) {
		return this.orientacaoDAO.listar(aluno);
	}
	
	
}

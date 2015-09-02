package com.sistema.coordenacao;

import java.util.List;

import com.sistema.util.DAOFactory;

public class CoordenacaoRN {
	
	private CoordenacaoDAO coordenacaoDAO;
	
	public CoordenacaoRN() {
		this.coordenacaoDAO = DAOFactory.criaCoordenacaoDAO();
	}

	public Coordenacao buscaPorId(Integer coordenacaoId) {
		return this.coordenacaoDAO.pesquisaPorId(coordenacaoId);
	}

	public void salvar(Coordenacao coordenacao) {
		this.coordenacaoDAO.salvar(coordenacao);
	}

	public List<Coordenacao> listar() {
		return this.coordenacaoDAO.coordenacoes();
	}

	public void deletar(Coordenacao coordenacao) {
		this.coordenacaoDAO.exclui(coordenacao);	
	}

	public void alterar(Coordenacao coordenacao) {
		this.coordenacaoDAO.alterar(coordenacao);
	}

	public Coordenacao findById(Integer coordenacaoId) {
		return this.coordenacaoDAO.pesquisaPorId(coordenacaoId);
	}
	
}

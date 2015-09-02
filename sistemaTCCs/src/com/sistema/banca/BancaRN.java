package com.sistema.banca;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;
import com.sistema.util.DAOFactory;

public class BancaRN {
	
	private BancaDAO bancaDAO;
	
	public BancaRN() {
		this.bancaDAO = DAOFactory.criaBancoDAO();
	}

	public void salvar(Banca banca) {
		this.bancaDAO.salvar(banca);
	}

	public void excluir(Banca banca) {
		this.bancaDAO.excluir(banca);
	}

	public Banca banca(Integer aluno) {
		return this.bancaDAO.banca(aluno);
	}

	public List<Banca> apresentacoes(Coordenacao coordenacao, Integer periodo) {
		return this.bancaDAO.apresentacoes(coordenacao, periodo);
	}

	public List<Banca> orientados(Integer orientador, Integer periodo) {
		return this.bancaDAO.orientados(orientador, periodo);
	}

	public List<Banca> listar(Periodo periodo) {
		return this.bancaDAO.listar(periodo);
	}
	
}

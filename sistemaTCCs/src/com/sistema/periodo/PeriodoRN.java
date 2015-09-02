package com.sistema.periodo;

import java.util.List;

import com.sistema.util.DAOFactory;

public class PeriodoRN {
	
	private PeriodoDAO cronogramaDAO;
	
	public PeriodoRN() {
		this.cronogramaDAO = DAOFactory.criaCronogramaDAO();
	}

	public void salvar(Periodo cronograama) {
		this.cronogramaDAO.salvar(cronograama);
	}

	public List<Periodo> listar() {
		return this.cronogramaDAO.listar();
	}

	public Periodo cronogramaAtual() {
		return this.cronogramaDAO.cronogramaAtual();
	}

	public void excluir(Periodo cronograma) {
		this.cronogramaDAO.excluir(cronograma);
	}

	public Periodo pesquisarPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return this.cronogramaDAO.pesquisarPorCodigo(codigo);
	}
	
}

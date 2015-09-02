package com.sistema.reuniao;

import java.util.List;

import com.sistema.util.DAOFactory;

public class ReuniaoRN {
	
	private ReuniaoDAO reuniaoDAO;
	
	public ReuniaoRN() {
		this.reuniaoDAO = DAOFactory.criaReuniaoDAO();
	}
	
	public void agendarReuniao(Reuniao reuniao) {
		this.reuniaoDAO.agendarReuniao(reuniao);
	}

	public void atualizarReuniao(Reuniao reuniao) {
		this.reuniaoDAO.atualizarReuniao(reuniao);
	}

	public void cancelarReuniao(Reuniao reuniao) {
		this.reuniaoDAO.cancelarReuniao(reuniao);
	}

	public List<Reuniao> listarReuniao(Integer proposta) {
		return this.reuniaoDAO.listarReuniao(proposta);
	}

	public List<Reuniao> listarAgendamento(Integer proposta) {
		return this.reuniaoDAO.listarSolicitacao(proposta);
	}
	
	public List<Reuniao> listarAgendamentoGeral(Integer orientador, Integer aluno) {
		return this.reuniaoDAO.listaSolicitacaoGeral(orientador, aluno);
	}
	

	public List<Reuniao> listarReuniaoGeral(Integer orientador) {
		return this.reuniaoDAO.listarReuniaoGeral(orientador);
	}
	
	public List<Reuniao> listaReuniaoPerido(Integer proposta, String periodo) {
		return this.reuniaoDAO.listaReuniaoPeriodo(proposta, periodo);
	}
}

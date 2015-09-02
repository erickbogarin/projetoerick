package com.sistema.reuniao;

import java.util.List;

public interface ReuniaoDAO {

	public void agendarReuniao(Reuniao reuniao);
	
	public List<Reuniao> listarSolicitacao(Integer proposta);
	
	public List<Reuniao> listaSolicitacaoGeral(Integer orientador, Integer aluno);
	
	public List<Reuniao> listarReuniao(Integer proposta);
	
	public List<Reuniao> listarReuniaoGeral(Integer orientador);
	
	public List<Reuniao> listaReuniaoPeriodo(Integer proposta, String periodo);
	
	public void atualizarReuniao(Reuniao reuniao);

	public void cancelarReuniao(Reuniao reuniao);
	
}

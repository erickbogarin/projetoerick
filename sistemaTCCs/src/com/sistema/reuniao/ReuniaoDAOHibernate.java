package com.sistema.reuniao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ReuniaoDAOHibernate implements ReuniaoDAO {
	
	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void agendarReuniao(Reuniao reuniao) {
		this.sessao.save(reuniao);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reuniao> listarSolicitacao(Integer proposta) {
		Query select = sessao.createQuery("from Reuniao r where r.proposta =:proposta and r.status =:status");
		select.setInteger("proposta", proposta);
		select.setString("status", "Cadastrado");
		
		return (List<Reuniao>)select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reuniao> listaSolicitacaoGeral(Integer orientador, Integer aluno) {
		Query select = sessao.createQuery("from Reuniao r where r.orientador =:orientador and r.status =:status"
				+ " and r.proposta.aluno.id = :aluno");
		select.setInteger("orientador", orientador);
		select.setInteger("aluno", aluno);
		select.setString("status", "Cadastrado");
		
		return (List<Reuniao>)select.list();
	}
	
	@Override
	public void atualizarReuniao(Reuniao reuniao) {
		this.sessao.update(reuniao);
	}

	@Override
	public void cancelarReuniao(Reuniao reuniao) {
		this.sessao.delete(reuniao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reuniao> listarReuniao(Integer proposta) {
		Query select = sessao.createQuery("from Reuniao r where r.proposta =:proposta and r.status =:status");
		select.setInteger("proposta", proposta);
		select.setString("status", "Confirmado");
		
		return (List<Reuniao>)select.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reuniao> listarReuniaoGeral(Integer orientador) {
		Query select = sessao.createQuery("from Reuniao r where r.orientador =:orientador and r.status =:status");
		select.setInteger("orientador", orientador);
		select.setString("status", "Confirmado");
		
		return (List<Reuniao>)select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reuniao> listaReuniaoPeriodo(Integer proposta, String periodo) {
		Query select = sessao.createQuery("from Reuniao r where r.proposta =:proposta and r.periodo =:periodo");
		select.setInteger("proposta", proposta);
		select.setString("periodo", periodo);
		
		return (List<Reuniao>) select.list();
	}
	
}

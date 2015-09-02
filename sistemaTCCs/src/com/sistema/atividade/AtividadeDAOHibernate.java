package com.sistema.atividade;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;

public class AtividadeDAOHibernate implements AtividadeDAO{
	
	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Atividade atividade) {
		this.sessao.save(atividade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atividade> listar(Integer id, Coordenacao coordenacao, Disciplina disciplina) {
		Query select = sessao.createQuery("FROM Atividade a "
				+ "WHERE a.periodo.id = :periodo and a.coordenacao = :coordenacao and a.disciplina = :disciplina "
				+ "ORDER BY a.data");
		
		select.setParameter("periodo", id);
		select.setParameter("coordenacao", coordenacao);
		select.setParameter("disciplina", disciplina);
		
		return (List<Atividade>) select.list();
	}

	@Override
	public void excluir(Atividade atividade) {
		this.sessao.delete(atividade);	
	}

	@Override
	public void alterar(Atividade atividade) {
		this.sessao.update(atividade);
	}
}

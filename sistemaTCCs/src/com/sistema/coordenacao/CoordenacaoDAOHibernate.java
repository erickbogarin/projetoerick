package com.sistema.coordenacao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CoordenacaoDAOHibernate implements CoordenacaoDAO {

	private Session sessao;

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Coordenacao coordenacao) {
		this.sessao.save(coordenacao);
	}

	@Override
	public void exclui(Coordenacao coordenacao) {
		this.sessao.delete(coordenacao);
	}

	@Override
	public void alterar(Coordenacao coordenacao){
		this.sessao.merge(coordenacao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenacao> coordenacoes() {
		Query query = this.sessao.createQuery("select c from Coordenacao c ");
		
		return query.list();
	}

	@Override
	public Coordenacao pesquisaPorId(Integer disciplinaId) {
		Query query =  this.sessao.createQuery("from Coordenacao c where c.id = :id");
		query.setParameter("id", disciplinaId);
		
		return (Coordenacao) query.uniqueResult();
	}

}

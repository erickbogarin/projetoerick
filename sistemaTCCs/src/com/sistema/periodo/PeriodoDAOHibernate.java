package com.sistema.periodo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class PeriodoDAOHibernate implements PeriodoDAO {
	
	private Session sessao;

	public Session getSession() {
		return sessao;
	}

	public void setSession(Session session) {
		this.sessao = session;
	}

	@Override
	public void salvar(Periodo cronograma) {
		this.sessao.save(cronograma);
	}

	@Override
	public void excluir(Periodo cronograma) {
		this.sessao.delete(cronograma);
	}

	@Override
	public void alterar(Periodo cronograma) {
		this.sessao.update(cronograma);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Periodo> listar() {
		return sessao.createCriteria(Periodo.class).addOrder( Order.desc("nome")).list();		
	}

	@Override
	public Periodo cronogramaAtual() {
		return (Periodo) sessao.createCriteria(Periodo.class).uniqueResult();		
	}
	
	@Override
	public Periodo pesquisarPorCodigo(Integer codigo) {
		return (Periodo) this.sessao.get(Periodo.class, codigo);
	}
	
}

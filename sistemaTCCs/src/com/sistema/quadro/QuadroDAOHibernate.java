package com.sistema.quadro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class QuadroDAOHibernate implements QuadroDAO {
	
	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Quadro quadro) {
		this.sessao.save(quadro);	
	}

	@Override
	public void excluir(Quadro quadro) {
		this.sessao.delete(quadro);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quadro> listar(Integer proposta) {
		Query select = sessao.createQuery("from Quadro q where q.proposta =:proposta");
		select.setInteger("proposta", proposta);
		
		return (List<Quadro>) select.list();
	}
	
}

package com.sistema.orientacao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class OrientacaoDAOHibernate implements OrientacaoDAO {

	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Orientacao orientacao) {
		this.sessao.save(orientacao);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orientacao> listar(Integer aluno) {
		Query select  = sessao.createQuery("from Orientacao o where o.proposta.aluno =:aluno");
		select.setInteger("aluno", aluno);
		
		return (List<Orientacao>) select.list();
	}

}

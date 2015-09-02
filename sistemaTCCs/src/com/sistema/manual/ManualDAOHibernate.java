package com.sistema.manual;

import org.hibernate.Session;

public class ManualDAOHibernate implements ManualDAO {
	
	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Manual manual) {
		this.sessao.save(manual);
	}
	
}

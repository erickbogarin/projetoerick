package com.sistema.disciplina;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class DisciplinaDAOHibernate implements DisciplinaDAO {

	private Session sessao;

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Disciplina disciplina) {
		this.sessao.save(disciplina);
	}

	@Override
	public void excluir(Disciplina disciplina) {
		this.sessao.delete(disciplina);
	}

	@Override
	public void alterar(Disciplina disciplina) {
		this.sessao.merge(disciplina);
	}

	@Override
	public Disciplina consultarDisciplina(Integer disciplinaId) {
		return (Disciplina) this.sessao.get(Disciplina.class, disciplinaId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> disciplinas() {
		return this.sessao.createCriteria(Disciplina.class)
				.addOrder(Order.asc("nome")).list();
	}
	
}

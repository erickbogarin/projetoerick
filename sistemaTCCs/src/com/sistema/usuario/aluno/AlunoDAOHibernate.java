package com.sistema.usuario.aluno;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class AlunoDAOHibernate implements AlunoDAO {

	private Session sessao;
	
	@Override
	public void salvar(Aluno aluno) {
		this.sessao.save(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listar() {
		Criteria lista = sessao.createCriteria(Aluno.class);
		return lista.list();
	}

	@Override
	public void excluir(Aluno aluno) {
		this.sessao.delete(aluno);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByName(String nome) {
		Query select = sessao.createQuery("from Aluno p where p.nome like :nome");
		select.setString("nome", "%" + nome + "%");
		
		return (List<Aluno>) select.list();
	}

	@Override
	public void alterarDados(Aluno aluno) {
		this.sessao.update(aluno);
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

}

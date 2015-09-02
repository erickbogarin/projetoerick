package com.sistema.curso;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CursoDAOHibernate implements CursoDAO{
	
	private Session sessao;
	
	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Curso curso) {
		this.sessao.save(curso);
	}

	@Override
	public void excluir(Curso curso) {
		this.sessao.delete(curso);
	}
	
	@Override
	public void alterar(Curso curso) {
		this.sessao.merge(curso);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> cursos() {		
		List<Curso> cursos = null;
		
		Query query = this.sessao.createQuery("from Curso c order by c.nome");
		cursos = query.list();
		
		return cursos;
	}

	@Override
	public Curso findById(Integer cursoId) {
		Query query = this.sessao.createQuery("from Curso c where c.id = :id");
		query.setParameter("id", cursoId);
		
		return (Curso) query.uniqueResult();
	}

	@Override
	public void desassociar(Curso curso) {
		this.sessao.saveOrUpdate(curso);
	}
	
}

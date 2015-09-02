package com.sistema.usuario.coordenador;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class CoordenadoDAOHibernate implements CoordenadorDAO {

	private Session sessao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> listar() {
		Criteria lista = sessao.createCriteria(Coordenador.class).addOrder( Order.asc("nome") );
		return lista.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> findUserByName(String nome) {
		Query select = sessao
				.createQuery("from Coordenador p where p.nome like :nome");
		select.setString("nome", "%" + nome + "%");
		return select.list();
	}

	@Override
	public Coordenador findById(Integer id) {
		Query select = sessao.createQuery("from Coordenador p where p.id =:id");
		select.setInteger("id", id);

		return (Coordenador) select.uniqueResult();
	}

	@Override
	public void alterar(Coordenador coordenador) {
		this.sessao.merge(coordenador);
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void excluir(Coordenador coordenador) {
		this.sessao.delete(coordenador);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> coordenadores(String coordenacao, String disciplina) {
		String status = "Ativo";

		Query select = sessao
				.createQuery("from Coordenador c where c.coordenacao =:coordenacao and disciplina =:disciplina and c.status =:status");
		select.setString("coordenacao", coordenacao);
		select.setString("disciplina", disciplina);
		select.setString("status", status);

		return (List<Coordenador>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> coodenacao() {
		Criteria lista = sessao.createCriteria(Coordenador.class);
		return lista.list();
	}

	@Override
	public void desassociar(Coordenador coordenador) {
		this.sessao.saveOrUpdate(coordenador);
	}

}

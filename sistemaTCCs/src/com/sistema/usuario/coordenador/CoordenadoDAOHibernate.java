package com.sistema.usuario.coordenador;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class CoordenadoDAOHibernate implements CoordenadorDAO {
	
	private Session sessao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> listar() {
		Criteria lista = sessao.createCriteria(Coordenador.class);
		return lista.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordenador> findUserByName(String nome) {
		Query select = sessao.createQuery("from Coordenador p where p.nome like :nome");
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
		this.sessao.update(coordenador);
	}
	
	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

}


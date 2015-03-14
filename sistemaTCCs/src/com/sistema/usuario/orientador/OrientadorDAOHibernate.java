package com.sistema.usuario.orientador;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorDAO;

public class OrientadorDAOHibernate implements OrientadorDAO {

	private Session sessao;
	
	@Override
	public void salvar(Orientador professor) {
		this.sessao.save(professor);	
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orientador> listar() {
		Criteria lista = sessao.createCriteria(Orientador.class);
		return lista.list();
	}

	@Override
	public void excluir(Orientador professor) {
		this.sessao.delete(professor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orientador> findUserByName(String nome) {
		Query select = sessao.createQuery("from Orientador p where p.nome like :nome");
		select.setString("nome", "%" + nome + "%");
		return select.list();
	}


	@Override
	public void alterar(Orientador professor) {
		this.sessao.update(professor);
	}

	@Override
	public Orientador findById(Integer id) {
		// TODO Auto-generated method stub
		Query select = sessao.createQuery("from Orientador p where p.id =:id");
		select.setInteger("id", id);
		
		Orientador orientador = (Orientador) select.uniqueResult();
		return orientador;
	}

}

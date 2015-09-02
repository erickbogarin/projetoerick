package com.sistema.usuario.orientador;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.proposta.Proposta;
import com.sistema.status.PropostaStatus;

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
		Criteria lista = sessao.createCriteria(Orientador.class).addOrder(
				Order.asc("nome"));

		return lista.list();
	}

	@Override
	public void excluir(Orientador professor) {
		this.sessao.delete(professor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orientador> findUserByName(String nome) {
		Query select = sessao
				.createQuery("from Orientador p where p.nome like :nome");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> orientados(Integer id) {
		PropostaStatus status = PropostaStatus.VALIDANDO;

		Query select = sessao
				.createQuery("select p from Orientador o inner join o.propostas as p where o.id = :pId and p.status != :status");
		select.setParameter("pId", id);
		select.setParameter("status", status);

		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orientador> orientadoresDaCoordenacao(Coordenacao coordenacao) {
		Query select = this.sessao
				.createQuery("SELECT orientadores FROM Coordenacao c JOIN c.orientadores orientadores "
						+ "WHERE c = :coordenacao");
		select.setParameter("coordenacao", coordenacao);
		
		return (List<Orientador>) select.list();
	}
}

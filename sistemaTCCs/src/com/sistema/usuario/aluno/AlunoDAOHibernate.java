package com.sistema.usuario.aluno;

import java.util.List;

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
	public List<Aluno> listar(Integer periodoId, Integer disciplinaId,
			Integer coordenacaoId) {
		Query query = sessao
				.createQuery("SELECT a FROM Aluno a"
						+ " WHERE EXISTS (FROM a.disciplinas d, a.periodos p WHERE d.id = :disciplinaId and p.id =:periodoId and a.coordenacao.id = :coordenacaoId)"
						+ " ORDER BY a.nome");

		query.setParameter("periodoId", periodoId);
		query.setParameter("disciplinaId", disciplinaId);
		query.setParameter("coordenacaoId", coordenacaoId);

		return (List<Aluno>) query.list();
	}

	@Override
	public void excluir(Aluno aluno) {
		sessao.flush();
		sessao.clear();
		this.sessao.delete(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findByName(String nome) {
		Query select = sessao
				.createQuery("from Aluno p where p.nome like :nome");
		select.setString("nome", "%" + nome + "%");

		return (List<Aluno>) select.list();
	}

	@Override
	public void alterarDados(Aluno aluno) {
		this.sessao.flush();
		this.sessao.clear();
		this.sessao.update(aluno);
	}

	@Override
	public Aluno findById(Integer id) {
		return (Aluno) this.sessao.get(Aluno.class, id);
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
	
}

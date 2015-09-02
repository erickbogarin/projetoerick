package com.sistema.banca;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;

public class BancaDAOHibernate implements BancaDAO {

	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Banca banca) {
		this.sessao.save(banca);
	}

	@Override
	public void excluir(Banca banca) {
		this.sessao.delete(banca);
	}

	@Override
	public Banca banca(Integer aluno) {
		Query select = sessao
				.createQuery("from Banca b where b.proposta.aluno =:aluno");
		select.setInteger("aluno", aluno);

		return (Banca) select.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banca> apresentacoes(Coordenacao coordenacao, Integer periodo) {
		Query select = sessao
				.createQuery("from Banca b where b.proposta.aluno.coordenacao =:coordenacao and b.proposta.periodo.id =:periodo order by b.proposta.aluno.nome");
		select.setParameter("coordenacao", coordenacao);
		select.setParameter("periodo", periodo);

		return (List<Banca>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banca> orientados(Integer orientador, Integer periodo) {

		Query select = sessao
				.createQuery("from Banca b where b.proposta.orientador =:orientador and b.proposta.periodo =:periodo order by b.proposta.aluno.nome");
		select.setInteger("orientador", orientador);
		select.setInteger("periodo", periodo);

		return ((List<Banca>) select.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banca> listar(Periodo periodo) {
		String disciplina = "Trabalho de Conclusão de Curso";
		
		Query select = this.sessao.createQuery("FROM Banca b "
				+ "WHERE b.proposta.disciplina.nome = :disciplina and b.proposta.periodo = :periodo "
				+ "ORDER BY b.dataApresentacao");
		select.setParameter("disciplina", disciplina);
		select.setParameter("periodo", periodo);
		
		return select.list();
	}

}

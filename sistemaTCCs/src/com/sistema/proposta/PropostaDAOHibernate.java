package com.sistema.proposta;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;
import com.sistema.status.PropostaStatus;

public class PropostaDAOHibernate implements PropostaDAO {

	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void solicitarProposta(Proposta proposta) {
		this.sessao.save(proposta);
	}

	@Override
	public void cadastrarProposta(Proposta proposta) {
		this.sessao.update(proposta);
	}

	@Override
	public Proposta cadastroAluno(Integer id) {
		Boolean atual = true;

		Query select = sessao
				.createQuery("from Proposta p where p.aluno =:aluno and p.atual =:atual");
		select.setInteger("aluno", id);
		select.setBoolean("atual", atual);

		if (select.list().isEmpty())
			return null;

		return (Proposta) select.uniqueResult();
	}

	@Override
	public void excluir(Proposta proposta) {
		this.sessao.delete(proposta);
	}

	@Override
	public void alterar(Proposta proposta) {
		this.sessao.update(proposta);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listaAndamento(Integer periodo) {

		Query select = sessao
				.createQuery("from Proposta p where p.periodo.id = :periodo and p.status =:status ORDER BY p.disciplina.nome DESC, p.tema ASC");
		select.setInteger("periodo", periodo);
		select.setParameter("status", PropostaStatus.ANDAMENTO);

		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listarOrientados(Integer orientador, Integer periodo,
			String disciplina) {
		Query select = sessao
				.createQuery("FROM Proposta p"
						+ " WHERE  p.orientador.id = :orientador and p.periodo.id = :periodoId and p.disciplina.nome = :disciplina"
						+ " and p.status != :status)"
						+ " ORDER BY a.nome");

		select.setInteger("orientador", orientador);
		select.setInteger("periodoId", periodo);
		select.setParameter("status", PropostaStatus.VALIDANDO);
		select.setString("disciplina", disciplina);

		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listarSolicitacao(Integer orientador) {
		Query select = sessao
				.createQuery("from Proposta p where p.orientador =:orientador and p.status =:validando");
		select.setInteger("orientador", orientador);
		select.setString("validando", "Validando");
		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listarCoordenados(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		Query query = sessao
				.createQuery("FROM Proposta p"
						+ " WHERE p.disciplina.nome = :disciplina and p.periodo = :periodo and p.aluno.coordenacao = :coordenacao"
						+ " ORDER BY p.aluno.nome");

		query.setParameter("coordenacao", coordenacao);
		query.setParameter("periodo", periodo);
		query.setParameter("disciplina", disciplina);

		return (List<Proposta>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> aprovados() {
		String status = "Aprovado";
		String statusBiblioteca = "Cadastrar";

		Query select = sessao
				.createQuery("from Proposta p where p.status =:status and p.statusBiblioteca =:statusBiblioteca");
		select.setString("status", status);
		select.setString("statusBiblioteca", statusBiblioteca);

		return ((List<Proposta>) select.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> tccs(String filtro) {

		Query select = sessao
				.createQuery("from Proposta p where (p.status =:filtro) or (p.modalidade =:filtro)"
						+ " ORDER BY p.periodo.nome, p.tema");

		select.setString("filtro", filtro);

		return ((List<Proposta>) select.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> consulta(String aluno, String tema) {
		String status = "Aprovado";

		Query select = sessao
				.createQuery("from Proposta p where p.aluno.nome like :aluno and p.tema like :tema and p.status=:status order by p.aluno.nome");
		select.setString("aluno", "%" + aluno + "%");
		select.setString("tema", "%" + tema + "%");
		select.setString("status", status);

		return ((List<Proposta>) select.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> listaApresentacoes(Integer coordenacao,
			Integer periodo, String disciplina) {
		String statusApresentacao = "Agendar";

		Query select = sessao
				.createQuery("from Proposta p where p.aluno.coordenacao.id =:coordenacao and p.periodo.id =:periodo and p.disciplina.nome =:disciplina and "
						+ "p.apresentacaoStatus =:statusApresentacao order by p.aluno.nome");
		select.setParameter("coordenacao", coordenacao);
		select.setInteger("periodo", periodo);
		select.setString("disciplina", disciplina);
		select.setString("statusApresentacao", statusApresentacao);

		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> projetos(Integer aluno) {
		Boolean atual = true;

		Query select = sessao
				.createQuery("from Proposta p where p.aluno.id =:aluno and p.atual != :atual order by p.dataCadastro DESC");
		select.setInteger("aluno", aluno);
		select.setBoolean("atual", atual);

		return (List<Proposta>) select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> aprovadosITCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		Query query = sessao
				.createQuery("FROM Proposta p"
						+ " WHERE p.disciplina.nome = :disciplina and p.periodo = :periodo and p.aluno.coordenacao = :coordenacao and p.status = :status"
						+ " ORDER BY p.aluno.nome");

		query.setParameter("coordenacao", coordenacao);
		query.setParameter("periodo", periodo);
		query.setParameter("disciplina", disciplina);
		query.setParameter("status", PropostaStatus.APROVADO);
		return (List<Proposta>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> reprovadosTCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		Query query = sessao
				.createQuery("FROM Proposta p"
						+ " WHERE p.disciplina.nome = :disciplina and p.periodo = :periodo and p.aluno.coordenacao = :coordenacao and p.status = :status"
						+ " ORDER BY p.aluno.nome");

		query.setParameter("coordenacao", coordenacao);
		query.setParameter("periodo", periodo);
		query.setParameter("disciplina", disciplina);
		query.setParameter("status", PropostaStatus.REPROVADO);
		return (List<Proposta>) query.list();
	}

}

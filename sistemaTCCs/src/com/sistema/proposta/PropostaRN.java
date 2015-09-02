package com.sistema.proposta;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;
import com.sistema.util.DAOFactory;

public class PropostaRN {

	private PropostaDAO propostaDAO;

	public PropostaRN() {
		this.propostaDAO = DAOFactory.criaPropostaDAO();
	}

	public void cadastrarProposta(Proposta proposta) {
		this.propostaDAO.cadastrarProposta(proposta);
	}

	public void solicitarProposta(Proposta proposta) {
		this.propostaDAO.solicitarProposta(proposta);
	}

	public Proposta verificarCadastro(Integer id) {
		return this.propostaDAO.cadastroAluno(id);
	}

	public List<Proposta> listarOrientados(Integer orientador, Integer periodo,
			String disciplina) {
		return this.propostaDAO.listarOrientados(orientador, periodo,
				disciplina);
	}

	public List<Proposta> listarSolicitacao(Integer orientador) {
		return this.propostaDAO.listarSolicitacao(orientador);
	}

	public void excluir(Proposta proposta) {
		this.propostaDAO.excluir(proposta);
	}

	public void alterar(Proposta proposta) {
		this.propostaDAO.alterar(proposta);
	}

	public List<Proposta> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proposta> listarCoordenados(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		return this.propostaDAO.listarCoordenados(coordenacao, periodo,
				disciplina);
	}

	public List<Proposta> aprovados() {
		return this.propostaDAO.aprovados();
	}

	public List<Proposta> tccs(String filtro) {
		return this.propostaDAO.tccs(filtro);
	}

	public List<Proposta> consulta(String aluno, String tema) {
		return this.propostaDAO.consulta(aluno, tema);
	}

	public List<Proposta> listaApresentacoes(Integer coordenacao,
			Integer periodo, String disciplina) {
		return this.propostaDAO.listaApresentacoes(coordenacao, periodo,
				disciplina);
	}

	public List<Proposta> listaAndamento(Integer periodo) {
		return this.propostaDAO.listaAndamento(periodo);
	}

	public List<Proposta> projetos(Integer aluno) {
		return this.propostaDAO.projetos(aluno);
	}

	public List<Proposta> aprovadosITCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		return this.propostaDAO.aprovadosITCC(coordenacao, periodo, disciplina);
	}

	public List<Proposta> reprovadosTCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina) {
		return this.propostaDAO.reprovadosTCC(coordenacao, periodo, disciplina);
	}

}

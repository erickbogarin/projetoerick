package com.sistema.proposta;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.periodo.Periodo;

public interface PropostaDAO {
	
	public void solicitarProposta(Proposta proposta);
	
	public void cadastrarProposta(Proposta proposta);
	
	public Proposta cadastroAluno(Integer id);
	
	public List<Proposta> listarOrientados(Integer orientador, Integer periodo, String disciplina) ;

	public List<Proposta> listarSolicitacao(Integer orientador);
	
	public List<Proposta> listaAndamento(Integer periodo);
	
	public void excluir(Proposta proposta);
	
	public void alterar(Proposta proposta);

	public List<Proposta> listarCoordenados(Coordenacao coordenacao, Periodo periodo, String disciplina);

	public List<Proposta> aprovados();

	public List<Proposta> tccs(String filtro);

	public List<Proposta> consulta(String aluno, String tema);

	public List<Proposta> listaApresentacoes(Integer coordenacao,
			Integer periodo, String disciplina);

	public List<Proposta> projetos(Integer aluno);

	public List<Proposta> aprovadosITCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina);
	
	public List<Proposta> reprovadosTCC(Coordenacao coordenacao,
			Periodo periodo, String disciplina);
	
}

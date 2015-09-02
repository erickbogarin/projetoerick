package com.sistema.proposta;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sistema.periodo.Periodo;
import com.sistema.periodo.PeriodoBean;
import com.sistema.usuario.coordenador.CoordenadorBean;

@ManagedBean(name = "propostaPageNavigation")
public class PropostaPageNavigation {

	@ManagedProperty(value = "#{propostaBean}")
	private PropostaBean propostaBean = new PropostaBean();

	@ManagedProperty(value = "#{periodoBean}")
	private PeriodoBean periodoBean = new PeriodoBean();
	
	@ManagedProperty(value = "#{coordenadorBean}")
	private CoordenadorBean coordenadorBean = new CoordenadorBean();
	
	public String onListaChangeTCCs(Periodo periodo) {
		
		periodoBean.setPeriodoSelecionado(periodo);
		propostaBean.setCoordenados(null);
		
		return "alunosTCC";
	}
	
	public void preparaTCCs(){
		propostaBean.setFiltro("APROVADO");
		propostaBean.setTccs(null);
		propostaBean.setPropostaSelecionada(new Proposta());
	}
	
	public String rematriculas() {
			Periodo periodo = periodoBean.getPeriodoAtual();
			propostaBean.setPeriodoSelecionado(periodo);
		
			propostaBean.setPeriodo(periodoBean.getPeriodoAtual());
			propostaBean.setDisciplina(this.coordenadorBean.getCoordenadorSelecionado().getDisciplina().getNome());
			propostaBean.onListaChange();
		
		return "/pages/protected/coordenador/rematricula/alunos-tcc.xhtml?faces-redirect=true";
	}
	
	public String apresentacoesTCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);
		propostaBean.setCoordenados(null);

		return "alunosTCC";
	}
	
	public String quadroTCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);
		propostaBean.setCoordenados(null);

		return "alunosTCC";
	}
	
	public String quadroITCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Introdução ao Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);
		propostaBean.setCoordenados(null);
		
		return "alunosITCC";
	}
	
	public String reunioesTCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);

		return "alunosTCC";
	}
	
	public String reunioesITCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Introdução ao Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);

		return "alunosITCC";
	}
	
	
	public String orientacoesTCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);

		return "alunosTCC";
	}
	
	public String orientacoesITCC() {
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Introdução ao Trabalho de Conclusão de Curso");
		propostaBean.setOrientados(null);

		return "alunosITCC";
	}
	
	public String alunosTCC() {
		
		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Trabalho de Conclusão de Curso");
		propostaBean.setCoordenados(null);
		propostaBean.setOrientados(null);

		return "alunosTCC";
	}

	public String alunosITCC() {

		if (!periodoBean.getLista().isEmpty())
			propostaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		propostaBean.setDisciplina("Introdução ao Trabalho de Conclusão de Curso");
		propostaBean.setCoordenados(null);
		propostaBean.setOrientados(null);

		return "alunosITCC";
	}

	public void setPropostaBean(PropostaBean propostaBean) {
		this.propostaBean = propostaBean;
	}

	public void setPeriodoBean(PeriodoBean periodoBean) {
		this.periodoBean = periodoBean;
	}
	
	public void setCoordenadorBean(CoordenadorBean coordenadorBean) {
		this.coordenadorBean = coordenadorBean;
	}
}

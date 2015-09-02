package com.sistema.atividade;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;
import com.sistema.periodo.Periodo;
import com.sistema.periodo.PeriodoBean;
import com.sistema.usuario.UsuarioLogin;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.aluno.AlunoBean;
import com.sistema.usuario.coordenador.Coordenador;

@ManagedBean(name = "atividadeBean")
@SessionScoped
public class AtividadeBean {

	private Atividade atividadeSelecionada = new Atividade();

	private PeriodoBean periodoBean = new PeriodoBean();

	private Periodo cronogramaSelecionado = new Periodo();

	private Aluno alunoSelecionado = new Aluno();

	private Coordenador coordenadorSelecionado = new Coordenador();
	
	private Disciplina disciplina = new Disciplina();
	
	private Coordenacao coordenacao = new Coordenacao();
	
	private List<Atividade> lista = null;
	private List<Atividade> atividadesPeriodo = null;

	@ManagedProperty(value = "#{usuarioLogin}")
	UsuarioLogin usuarioLogado = new UsuarioLogin();

	public String salvar() {
		AtividadeRN atividadeRN = new AtividadeRN();

		Coordenador coordenador = ((Coordenador) usuarioLogado.getUser());

		atividadeSelecionada.setPeriodo(cronogramaSelecionado);
		atividadeSelecionada.setCoordenacao(coordenador.getCoordenacao());
		atividadeSelecionada.setDisciplina(coordenador.getDisciplina());

		atividadeRN.salvar(atividadeSelecionada);

		atividadeSelecionada = new Atividade();

		this.lista = null;

		return "manterAtividades.xhtml";
	}

	public String prepararCronograma(Aluno aluno) {
		AlunoBean bean = new AlunoBean();
		bean.prepararAluno(aluno);
		Periodo periodo = bean.getPeriodoAtual();
		this.cronogramaSelecionado = periodo;

		return "Cronograma";
	}

	public List<Atividade> getLista() {
		AtividadeRN atividadeRN = new AtividadeRN();

		if (lista == null)
			lista = atividadeRN.listar(cronogramaSelecionado.getId(), this.coordenacao, this.disciplina);

		Collections.sort(lista, new AtividadeComparator());

		return lista;
	}

	public void cadastrarAtividade() {
		RequestContext.getCurrentInstance().openDialog("cadastrarAtividade");
	}

	public List<Atividade> getAtividadesPeriodo() {

		if (atividadesPeriodo == null)
			atividadesPeriodo = new AtividadeRN().listar(periodoBean.getLista()
					.get(0).getId(), this.coordenacao, this.disciplina);

		return atividadesPeriodo;
	}

	public String prepararEdicao() {
		lista = null;
		return "editarAtividades";
	}

	public void excluir() {
		AtividadeRN atividadeRN = new AtividadeRN();
		atividadeRN.excluir(atividadeSelecionada);
		atividadeSelecionada = new Atividade();
		this.lista = null;
	}

	public Atividade getAtividadeSelecionada() {
		return atividadeSelecionada;
	}

	public void setAtividadeSelecionada(Atividade atividadeSelecionada) {
		this.atividadeSelecionada = atividadeSelecionada;
	}

	public Periodo getCronogramaSelecionado() {
		return cronogramaSelecionado;
	}

	public void setCronogramaSelecionado(Periodo cronogramaSelecionado) {
		this.cronogramaSelecionado = cronogramaSelecionado;
	}

	public void setListaAtividade(List<Atividade> lista) {
		this.lista = lista;
	}

	public void setPeriodoBean(PeriodoBean periodoBean) {
		this.periodoBean = periodoBean;
	}

	public String novaAtividade(Periodo periodo) {
		this.cronogramaSelecionado = periodo;
		return "cadastrarAtividade.xhtml";
	}
	
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Coordenador getCoordenadorSelecionado() {
		return coordenadorSelecionado;
	}

	public void setCoordenadorSelecionado(Coordenador coordenadorSelecionado) {
		this.coordenadorSelecionado = coordenadorSelecionado;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public void carregaCoordenador() {
		this.coordenadorSelecionado = ((Coordenador) usuarioLogado.getUser());
		this.coordenacao = coordenadorSelecionado.getCoordenacao();
		this.disciplina = coordenadorSelecionado.getDisciplina();
		
	}
	
	public void carregaAluno() {
		this.alunoSelecionado = ((Aluno) usuarioLogado.getUser());
		this.coordenacao = this.alunoSelecionado.getCoordenacao();
		
		for (Disciplina d : this.alunoSelecionado.getDisciplinas()) {
			this.disciplina = d;
		}

	}
	
}

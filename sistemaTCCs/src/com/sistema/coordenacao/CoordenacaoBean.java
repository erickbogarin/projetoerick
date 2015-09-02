package com.sistema.coordenacao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sistema.curso.Curso;
import com.sistema.curso.CursoRN;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.coordenador.CoordenadorRN;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorRN;

@ManagedBean
@ViewScoped
public class CoordenacaoBean {

	Coordenacao coordenacao = new Coordenacao();

	private List<Coordenacao> coordenacoes = null;

	private Integer cursoId;
	private Integer coordenadorId;
	private Integer orientadorId;

	public List<Coordenacao> getCoordenacoes() {

		if (coordenacoes == null)
			coordenacoes = new CoordenacaoRN().listar();

		return coordenacoes;
	}

	public void adicionarCoordenacao() {
		new CoordenacaoRN().salvar(this.coordenacao);

		alterarCursoNoBanco();
		alterarCoordenadorNoBanco();

		this.coordenacao = new Coordenacao();
		this.coordenacoes = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Coordenação cadastrada com sucesso!"));
	}

	public void adicionarCoordenador() {
		Coordenador coordenador = new CoordenadorRN().findById(coordenadorId);
		
		for (Coordenador cdn : this.coordenacao.getCoordenadores()) {
			if (coordenador.getId() == cdn.getId()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"O coordenador selecionado já está associado a esta coordenação"));
				return;
			}
		}
		
		this.coordenacao.adicionaCoordenador(coordenador);
	}

	public List<Coordenador> getCoordenadoresdaCoordenacao() {
		return this.coordenacao.getCoordenadores();
	}

	public List<Coordenador> getCoordenadores() {
		return new CoordenadorRN().listar();
	}

	public void adicionarOrientador() {
		Orientador orientador = new OrientadorRN().findById(orientadorId);

		for (Orientador o : this.coordenacao.getOrientadores()) {
			if (o.getId() == orientador.getId()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"O orientador selecionado já está associado a esta coordenação"));
				return;
			}
		}

		this.coordenacao.adicionaOrientador(orientador);
	}

	public void alterarCoordenacao(ActionEvent event) {
		new CoordenacaoRN().alterar(this.coordenacao);

		alterarCursoNoBanco();
		alterarCoordenadorNoBanco();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Coordenação alterada com sucesso!"));
	}

	public void deletarCoordenacao() {
		for (Curso curso : this.coordenacao.getCursos()) {
			curso.setCoordenacao(null);
			new CursoRN().desassociar(curso);
		}

		for (Coordenador coordenador : this.coordenacao.getCoordenadores()) {
			coordenador.setCoordenacao(null);
			new CoordenadorRN().desassociar(coordenador);
		}

		new CoordenacaoRN().deletar(this.coordenacao);

		this.coordenacoes = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Coordenação excluída com sucesso!"));
	}

	public List<Orientador> getOrientadoresdaCoordenacao() {
		return this.coordenacao.getOrientadores();
	}

	public List<Orientador> getOrientadores() {
		return new OrientadorRN().listar();
	}

	public void adicionarCurso() {
		Curso curso = new CursoRN().findById(cursoId);

		for (Curso cs : this.coordenacao.getCursos()) {
			if (curso.getId() == cs.getId()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"O curso selecionado já está associado a esta coordenação"));
				return;
			}
		}

		this.coordenacao.adicionaCurso(curso);
	}

	private void alterarCursoNoBanco() {
		for (Curso curso : this.coordenacao.getCursos()) {
			curso.setCoordenacao(this.coordenacao);
			new CursoRN().alterar(curso);
		}
	}

	private void alterarCoordenadorNoBanco() {
		for (Coordenador coordenador : this.coordenacao.getCoordenadores()) {
			coordenador.setCoordenacao(this.coordenacao);
			new CoordenadorRN().alterar(coordenador);
		}
	}

	public List<Curso> getCursosDaCoordenacao() {
		return this.coordenacao.getCursos();
	}

	public List<Curso> getCursos() {
		return new CursoRN().cursos();
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public Integer getCoordenadorId() {
		return coordenadorId;
	}

	public void setCoordenadorId(Integer coordenadorId) {
		this.coordenadorId = coordenadorId;
	}

	public Integer getOrientadorId() {
		return orientadorId;
	}

	public void setOrientadorId(Integer orientadorId) {
		this.orientadorId = orientadorId;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

}

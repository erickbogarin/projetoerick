package com.sistema.curso;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.coordenacao.CoordenacaoRN;
import com.sistema.disciplina.Disciplina;
import com.sistema.disciplina.DisciplinaRN;

@ManagedBean
@ViewScoped
public class CursoBean {
	
	Curso curso = new Curso();
	List<Curso> cursos = null;
	
	private Integer coordenacaoId;
	
	public List<Curso> getCursos() {
		
		if(cursos == null)
			cursos = new CursoRN().cursos();
		
		return cursos;
	}
	
	public void adicionarCurso() throws Exception {
		
		Coordenacao coordenacao = new CoordenacaoRN().buscaPorId(this.coordenacaoId);
		this.curso.setCoordenacao(coordenacao);
		
		for(Disciplina disciplina : getDisciplinas()) {
			this.curso.adicionaDisciplina(disciplina);
		}
		
		new CursoRN().salvar(this.curso);
		
		this.curso = new Curso();
	}
	
	public void alterarCurso(ActionEvent event) {
		Coordenacao coordenacao = new CoordenacaoRN().buscaPorId(this.coordenacaoId);
		this.curso.setCoordenacao(coordenacao);
		
		new CursoRN().alterar(this.curso);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Curso alterado com sucesso!"));
	}
	
	public void deletarCurso() {
		new CursoRN().deletar(this.curso);
		
		this.cursos = null;
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Curso deletado com sucesso!"));
	}
	
	public List<Disciplina> getDisciplinas() throws Exception {
		return new DisciplinaRN().disciplinas();
	}
	
	public List<Coordenacao> getCoordenacoes() {
		return new CoordenacaoRN().listar();
	}
	
	public Integer getCoordenacaoId() {
		return coordenacaoId;
	}

	public void setCoordenacaoId(Integer coordenacaoId) {
		this.coordenacaoId = coordenacaoId;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}

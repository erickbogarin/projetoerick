package com.sistema.usuario.aluno;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioBean;

@ManagedBean(name="alunoBean")
@RequestScoped
public class AlunoBean {
	
	private Aluno alunoSelecionado = new Aluno();
	private UsuarioBean user = new UsuarioBean();
	
	public void preparaConsulta() {
		user.getConsultarAluno();
	}
	
	public String prepararConta(Usuario usuario) {
		this.alunoSelecionado = (Aluno) usuario;
		return "/conta/consultarDados.xhtml";
	}

	public String prepararEdicao(Aluno aluno) {
		this.alunoSelecionado =  aluno;
		return "../edicao/alterarAluno.xhtml";
	}
	
	public void excluir() {
		AlunoRN alunoRN = new AlunoRN();
		alunoRN.excluir(this.alunoSelecionado);
		user.setListaOrientador(null);
	}
	
	public String alterar() {
		AlunoRN alunoRN = new AlunoRN();
		this.alunoSelecionado.setDataCadastro(new Date());
		alunoRN.alterar(this.alunoSelecionado);
		FacesMessage faces = new FacesMessage("Usuario alterado com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "../listagem/listarAluno.xhtml";
	}
	
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
}

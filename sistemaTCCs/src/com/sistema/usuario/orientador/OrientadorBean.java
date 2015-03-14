package com.sistema.usuario.orientador;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioBean;

@ManagedBean(name = "orientadorBean")
@RequestScoped
public class OrientadorBean {
	private Orientador orientadorSelecionado = new Orientador();
	private Usuario usuarioSelecionado = new Usuario();
	private UsuarioBean usuarioBean = new UsuarioBean();

	public void excluir() {
		OrientadorRN orientadorRN = new OrientadorRN();
		orientadorRN.excluir(this.orientadorSelecionado);
		usuarioBean.setListaOrientador(null);
	}

	public String alterar() {
		OrientadorRN professorRN = new OrientadorRN();
		this.orientadorSelecionado.setDataCadastro(new Date());
		professorRN.alterar(this.orientadorSelecionado);
		FacesMessage faces = new FacesMessage("Usuario alterado com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "../listagem/listarOrientador.xhtml";
	}

	public void novo() {
		this.orientadorSelecionado = new Orientador();
	}

	public void prepararConsulta() {
		usuarioBean.getConsultarOrientador();
		FacesMessage faces = new FacesMessage("Usuario não encontrado!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

	}

	public String prepararConta(Usuario usuario) {
		OrientadorRN usuarioRN = new OrientadorRN();
		this.orientadorSelecionado = usuarioRN.findById(usuario.getId());

		return "conta/consultarDados.xhtml";
	}
	
	public String prepararPrimeiroAcesso(Usuario usuario) {
		OrientadorRN usuarioRN = new OrientadorRN();
		this.orientadorSelecionado = usuarioRN.findById(usuario.getId());

		return "/pages/protected/coordenador/primeiroAcesso.xhtml";
	}
	
	public String solicitarEditarConta(Usuario orientador) {
		OrientadorRN usuarioRN = new OrientadorRN();
		this.orientadorSelecionado = usuarioRN.findById(orientador.getId());

		return "alterarConta.xhtml";
	}

	public String prepararEdicao(Orientador orientador) {
		this.orientadorSelecionado = orientador;
		return "../edicao/alterarOrientador.xhtml";
	}

	public Orientador getOrientadorSelecionado() {
		return orientadorSelecionado;
	}

	public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
		this.orientadorSelecionado = orientadorSelecionado;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}

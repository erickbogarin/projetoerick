package com.sistema.navegacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.sistema.usuario.UsuarioLogin;

@ManagedBean (name = "navigationBean")
@SessionScoped

public class NavigationRule {
	
	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();
	
	public String paginaInicial() {
		
		if(usuarioLogado.getUser().isAluno())
			return "aluno"; 
		
		if(usuarioLogado.getUser().isOrientador())
			return "orientador";
		
		if(usuarioLogado.getUser().isCoordenador())
			return "coordenador";
		
		if(usuarioLogado.getUser().isAdministrador())
			return "admin";
		
		if(usuarioLogado.getUser().isBibliotecario())
			return "bibliotecario";
		
		return "";
	}
	
	public String cadastrarNoticias() {
		return "cadastrarNoticias";
	}
	
	public String atualizaLista(){
		return "atualizaLista";
	}
	
	public String andamentoProposta() {
		return "andamentoProposta";
	}
	
	public String noticias() {
		return "noticias";
	}
	
	public String tccs() {
		return "tccs";
	}
	
	public String sugestoes() {
		return "sugestoes";
	}
	
	public String manuais() {
		return "manuais";
	}
	
	public String sobre() {
		return "sobre";
	}
	
	public String solicitarProposta(){
		return "solicitarProposta";
	}
	
	public String arquivos() {
		return "documentos";
	}
	
	public String cancelar() {
		return "../listagem/listarAluno.xhtml";
	}
	
	public String cancelarOrientador() {
		return "../listagem/listarOrientador.xhtml";
	}
	
	public String cadastrarBanca() {
		return "cadastrarBanca";
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}	

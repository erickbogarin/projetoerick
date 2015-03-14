package com.sistema.usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import com.sistema.status.Status;
import com.sistema.usuario.orientador.OrientadorBean;
import com.sistema.util.SessionContext;

@ManagedBean(name = "usuarioLogin")
@SessionScoped
public class UsuarioLogin {

	private static Logger logger = Logger.getLogger(UsuarioLogin.class);
	Usuario usuarioSelecionado = new Usuario();
	OrientadorBean orientadorBean = new OrientadorBean();
	
	public Usuario getUser() {
		return (Usuario) SessionContext.getInstance().getUsuarioLogado();
	}
	
	public String doLogin() {
		UsuarioRN usuarioRN = new UsuarioRN();
		
		try {
			logger.info("Tentando logar com usuário " + usuarioSelecionado.getEmail());
			Usuario user = usuarioRN.isUsuarioReady(usuarioSelecionado.getEmail(), 
					usuarioSelecionado.getSenha());
			
			if(user == null) {
				FacesMessage faces = new FacesMessage(
						"Email ou senha errado, tente novamente!");
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, faces);
				return "/pages/public/login.xhtml";
			} else {
				logger.info("Login efetuado com sucesso!");
				SessionContext.getInstance().setAttribute("usuarioLogado", user);
				if (user.isCoordenador()) {
					return "/pages/protected/coordenador/paginaInicial.xhtml";
				}
				else if(user.isAluno())
					return "/pages/protected/aluno/paginaInicial.xhtml";
	
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/pages/public/login.xhtml";
		}
	}
	
	public String doLogout() {
		 logger.info("Fazendo logout com usuário "
                 + SessionContext.getInstance().getUsuarioLogado().getEmail());
		
		 SessionContext.getInstance().encerrarSessao();
		 FacesMessage faces = new FacesMessage(
					"Logout realizado com sucesso!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
			return "/pages/public/login.xhtml";
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}

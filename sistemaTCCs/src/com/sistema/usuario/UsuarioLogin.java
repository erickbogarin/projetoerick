package com.sistema.usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sistema.external.ConversorMD5;

import com.sistema.usuario.aluno.AlunoBean;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.coordenador.CoordenadorBean;
import com.sistema.usuario.orientador.OrientadorBean;
import com.sistema.util.SessionContext;

@ManagedBean(name = "usuarioLogin")
@SessionScoped
public class UsuarioLogin {

	Usuario usuarioSelecionado = new Usuario();
	OrientadorBean orientadorBean = new OrientadorBean();
	
	@ManagedProperty(value = "#{coordenadorBean}")
	CoordenadorBean coordenadorBean = new CoordenadorBean();
	
	public Usuario getUser() {
		return (Usuario) SessionContext.getInstance().getUsuarioLogado();
	}

	public String doLogin() {
		UsuarioRN usuarioRN = new UsuarioRN();

		try {

			Usuario user = usuarioRN.isUsuarioReady(usuarioSelecionado
					.getEmail(), ConversorMD5
					.convertStringToMd5(usuarioSelecionado.getSenha()));

			if (user == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro! Usuário ou senha incorreto.",
								"messages"));
				return "/pages/public/login.xhtml";
			} else {
				SessionContext.getInstance()
						.setAttribute("usuarioLogado", user);
				if (user.isCoordenador()) {
					this.coordenadorBean.setCoordenadorSelecionado((Coordenador) user);
					return this.coordenadorBean.controleInicial(user);
				} else if (user.isAluno()) {
					AlunoBean alunoBean = new AlunoBean();
					return alunoBean.controleInicial(user);
				} else if (user.isOrientador()) {
					OrientadorBean orientadorBean = new OrientadorBean();
					return orientadorBean.controleInicial(user);
				} else if (user.isAdministrador())
					return "administrador";
				else if (user.isBibliotecario())
					return "Bibliotecario";

				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "/pages/public/login.xhtml";
		}
	}

	public String doLogout() {

		SessionContext.getInstance().encerrarSessao();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout! Sessão encerrada com sucesso.",
						""));

		return "/pages/public/login?faces-redirect=true";
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public void setCoordenadorBean(CoordenadorBean coordenadorBean) {
		this.coordenadorBean = coordenadorBean;
	}
	
}

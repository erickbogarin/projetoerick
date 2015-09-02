package com.sistema.mensagem;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sistema.usuario.UsuarioLogin;

@ManagedBean(name = "mensagemBean")
@SessionScoped
public class MensagemBean {

	private Mensagem mensagemSelecionada = new Mensagem();
	private List<Mensagem> lista = null;
	private List<Mensagem> noticias = null;

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();

	public void enviarMensagem(Mensagem mensagemSelecionada) {
		MensagemRN mensagemRN = new MensagemRN();

		mensagemSelecionada.setData(new Date());

		mensagemRN.salvar(mensagemSelecionada);
	}

	public String enviarNoticias() {
		
		mensagemSelecionada.setData(new Date());
		mensagemSelecionada.setTipo("noticias");
		
		new MensagemRN().salvar(mensagemSelecionada);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Notícia adicionada!", ""));
		
		noticias = null;
		
		mensagemSelecionada = new Mensagem();
		
		return "noticias";
	}

	public List<Mensagem> getLista() {
		MensagemRN mensagemRN = new MensagemRN();

		if (lista == null)
			lista = mensagemRN.listarMensagem(usuarioLogado.getUser().getId());

		Collections.sort(lista, new MensagemComparator());

		return lista;
	}

	public List<Mensagem> getNoticias() {

		if (noticias == null)
			noticias = new MensagemRN().noticias();

		return noticias;
	}

	public Mensagem getMensagemSelecionada() {
		return mensagemSelecionada;
	}

	public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
		this.mensagemSelecionada = mensagemSelecionada;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}

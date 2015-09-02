package com.sistema.orientacao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sistema.proposta.PropostaBean;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioLogin;

@ManagedBean(name = "orientacaoBean")
@SessionScoped
public class OrientacaoBean {

	private Orientacao orientacaoSelecionada = new Orientacao();

	private List<Orientacao> lista = null;

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();

	@ManagedProperty(value = "#{propostaBean}")
	private PropostaBean propostaBean = new PropostaBean();
	
	private Usuario usuarioSelecionado = new Usuario();
	
	public void salvar(ActionEvent actionEvent) {

		try {
			System.out.println(propostaBean.getPropostaSelecionada().getTema());
			orientacaoSelecionada.setProposta(propostaBean.getPropostaSelecionada());
			orientacaoSelecionada.setAutor(usuarioLogado.getUser().getNome());
			orientacaoSelecionada.setData(new Date());

			new OrientacaoRN().salvar(orientacaoSelecionada);

			FacesMessage faces = new FacesMessage(
					"Mensagem enviada com sucesso!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);

			lista = null;

			orientacaoSelecionada = new Orientacao();

		} catch (Exception e) {
			FacesMessage faces = new FacesMessage("Erro ao enviar a mensagem!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
			System.out.println("Outro");
		}

		System.out.println("Ultimo");
	}

	public List<Orientacao> getLista() {
		
		if (lista == null) {
			lista = new OrientacaoRN().lista(usuarioSelecionado.getId());
			Collections.sort(lista, new OrientacaoComparator());
		}
		
		return lista;
	}

	public String preparaOrientacoes() {
		lista = null;
		
		return "orientacoes";
	}
	
	public Orientacao getOrientacaoSelecionada() {
		return orientacaoSelecionada;
	}

	public void setOrientacaoSelecionada(Orientacao orientacaoSelecionada) {
		this.orientacaoSelecionada = orientacaoSelecionada;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public void setPropostaBean(PropostaBean propostaBean) {
		this.propostaBean = propostaBean;
	}
	
}

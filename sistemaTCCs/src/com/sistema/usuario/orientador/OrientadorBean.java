package com.sistema.usuario.orientador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistema.external.ConversorMD5;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.coordenacao.CoordenacaoRN;
import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.proposta.Proposta;
import com.sistema.status.UsuarioStatus;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.coordenador.Coordenador;

@ManagedBean
@SessionScoped
public class OrientadorBean {

	private Orientador orientadorSelecionado = new Orientador();

	private List<SelectItem> orientadorSelect;

	private List<Proposta> orientados = null;

	private List<Orientador> lista = null;

	private List<Orientador> orientadoresCoordenacao = null;

	private Coordenacao coordenacao;
	
	public List<Orientador> getLista() {
		OrientadorRN orientadorRN = new OrientadorRN();

		if (lista == null) {
			lista = orientadorRN.listar();
		}

		return lista;
	}

	public List<Orientador> getOrientadoresCoordenacao() {

		if (orientadoresCoordenacao == null)
			orientadoresCoordenacao = new OrientadorRN()
					.orientadoresDaCoordenacao(this.coordenacao);

		return orientadoresCoordenacao;
	}

	public void adicionaOrientador() {
		this.coordenacao.adicionaOrientador(this.orientadorSelecionado);
		new CoordenacaoRN().alterar(this.coordenacao);
		
		this.orientadoresCoordenacao = null;
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Orientador adicionado!", ""));

	}

	public String excluir() {
		OrientadorRN orientadorRN = new OrientadorRN();
		orientadorRN.excluir(this.orientadorSelecionado);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Orientador excluido!", ""));

		lista = null;

		return "../listagem/listarOrientador.xhtml";
	}

	public void alterar() {
		new OrientadorRN().alterar(orientadorSelecionado);
	}

	public String alterarLista() {
		this.alterar();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado!",
						"Dados do orientador alterados com sucessos"));

		return "../listagem/listarOrientador.xhtml";
	}

	public void alterarSenha() {
		orientadorSelecionado.setSenha(ConversorMD5
				.convertStringToMd5(orientadorSelecionado.getSenha()));

		new OrientadorRN().alterar(orientadorSelecionado);

		FacesMessage faces = new FacesMessage("Senha alterada com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);
	}

	public String passo2(Orientador orientador) {
		orientadorSelecionado = orientador;
		orientadorSelecionado.setStatus(UsuarioStatus.Ativo);

		alterarSenha();

		new OrientadorRN().alterar(orientadorSelecionado);

		MensagemBean mensagemBean = new MensagemBean();

		String mensagem = "O status do orientador "
				+ orientadorSelecionado.getNome()
				+ " foi alterado para Ativo no sistema.";

		List<Coordenacao> coordenacoes = orientadorSelecionado.getCoodenacoes();

		int i = 0;
		for (Coordenacao coordenacao : coordenacoes) {
			Mensagem mensagemSelecionada = new Mensagem();

			mensagemSelecionada.setMensagem(mensagem);
			mensagemSelecionada.setUsuario(coordenacao.getCoordenadores()
					.get(i));

			mensagemBean.enviarMensagem(mensagemSelecionada);
			i++;
		}

		FacesMessage faces = new FacesMessage("Dados alterados com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "/pages/protected/orientador/paginaInicial?faces-redirect=true";
	}

	public String passo1(Orientador orientador) {
		this.orientadorSelecionado = orientador;

		new OrientadorRN().alterar(orientadorSelecionado);

		return "alterarSenha.xhtml";
	}

	public String atualizarDados(Orientador orientador) {
		this.orientadorSelecionado = orientador;

		new OrientadorRN().alterar(orientadorSelecionado);

		FacesMessage faces = new FacesMessage("Dados alterados com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "consultarDados.xhtml";
	}

	public void novo() {
		this.orientadorSelecionado = new Orientador();
	}

	public String prepararConta(Usuario usuario) {
		OrientadorRN usuarioRN = new OrientadorRN();
		this.orientadorSelecionado = usuarioRN.findById(usuario.getId());

		return "redirecionaConta";
	}

	public String prepararPrimeiroAcesso(Usuario usuario) {
		OrientadorRN usuarioRN = new OrientadorRN();
		this.orientadorSelecionado = usuarioRN.findById(usuario.getId());

		return "/pages/protected/orientador/primeiroAcesso.xhtml";
	}

	public String prepararEdicao() {
		return "editarOrientador";
	}

	public String prepararCronograma() {
		return "Cronograma";
	}

	public Orientador carregarOrientador(Usuario usuario) {
		OrientadorRN orientadorRN = new OrientadorRN();
		return orientadorRN.findById(usuario.getId());
	}

	public String primeiroAcesso(Usuario usuario) {
		this.orientadorSelecionado = carregarOrientador(usuario);
		return "atualizarDados.xhtml";
	}

	public String controleInicial(Usuario usuario) {
		this.orientadorSelecionado = carregarOrientador(usuario);

		if (orientadorSelecionado.getStatus() == UsuarioStatus.Cadastrado)
			return "/pages/protected/orientador/primeiroAcesso/passoInicial?faces-redirect=true";

		return "/pages/protected/orientador/paginaInicial?faces-redirect=true";
	}

	public List<SelectItem> getOrientadorSelect() {

		if (orientadorSelect == null) {

			orientadorSelect = new ArrayList<SelectItem>();

			if (lista == null)
				lista = this.getLista();

			if (lista != null && !lista.isEmpty()) {
				SelectItem item;
				for (Orientador orientadorLista : lista) {
					item = new SelectItem(orientadorLista,
							orientadorLista.getNome());
					orientadorSelect.add(item);
				}
			}

		}

		return orientadorSelect;
	}

	public void prepararOrientador(Orientador orientador) {
		this.orientadorSelecionado = orientador;
	}

	public void setLista(List<Orientador> lista) {
		this.lista = lista;
	}

	public void setListaOrientador(List<Orientador> listaOrientador) {
		this.lista = listaOrientador;
	}

	public Orientador getOrientadorSelecionado() {
		return orientadorSelecionado;
	}

	public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
		this.orientadorSelecionado = orientadorSelecionado;
	}

	public List<Proposta> orientados(Orientador usuario) {

		if (orientados == null)
			orientados = new OrientadorRN().orientados(usuario.getId());

		return orientados;
	}

	public List<Proposta> getOrientados() {
		return orientados;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}
	
	public void setOrientadoresCoordenacao(
			List<Orientador> orientadoresCoordenacao) {
		this.orientadoresCoordenacao = orientadoresCoordenacao;
	}
	
	public Coordenacao coordenacaoUsuario(Usuario user) {
		Coordenacao coordenacao = null;

		if (user.isCoordenador())
			coordenacao = ((Coordenador) user).getCoordenacao();
		else
			coordenacao = ((Aluno) user).getCoordenacao();

		return coordenacao;
	}

	
}

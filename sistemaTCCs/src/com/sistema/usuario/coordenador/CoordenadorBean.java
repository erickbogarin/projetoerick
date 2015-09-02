package com.sistema.usuario.coordenador;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.sistema.external.ConversorMD5;
import br.com.sistema.external.PasswordGenerator;
import br.com.sistema.external.SendMail;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.coordenacao.CoordenacaoRN;
import com.sistema.disciplina.Disciplina;
import com.sistema.disciplina.DisciplinaRN;
import com.sistema.status.UsuarioStatus;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioRN;

@ManagedBean(name = "coordenadorBean")
@SessionScoped
public class CoordenadorBean {

	private Coordenador coordenadorSelecionado = new Coordenador();
	private List<Coordenador> lista = null;

	private Integer disciplinaId = null;
	private Integer coordenacaoId = null;
	
	public void adicionarCoordenador() {
		if (new UsuarioRN().findByEmail( this.coordenadorSelecionado.getEmail() ) == null) {

			Disciplina disciplina = new DisciplinaRN()
					.findById(this.disciplinaId);
			this.coordenadorSelecionado.setDisciplina(disciplina);

			Coordenacao coordenacao = new CoordenacaoRN()
					.findById(this.coordenacaoId);
			this.coordenadorSelecionado.setCoordenacao(coordenacao);

			this.coordenadorSelecionado.setDataCadastro(new Date());
			this.coordenadorSelecionado.setStatus(UsuarioStatus.Cadastrado);
			final String senha = PasswordGenerator.geraSenha(6);
			this.coordenadorSelecionado.setSenha(ConversorMD5.convertStringToMd5(senha));
			
			new UsuarioRN().salvar(this.coordenadorSelecionado);
			
			final Usuario usuario = this.coordenadorSelecionado;
			
			new Thread() {
				public void run() {
					String assunto = "Bem vindo ao Sistema de Gerenciamento e Controle do TCC: conta de acesso";
					String msg = "Olá " + usuario.getNome()  + ",\n\n"
							+ "Seja bem vindo à nossa plataforma!\n\n"
							+ "Para acessar a sua conta, entre em nosso site (http://localhost:8080/sistemaTCCs/) "
							+ "e utilize  as seguintes informações:\n\n" 
							+ " Usuário: " + usuario.getEmail() + "\n"
							+ " Senha: " + senha + "\n\n" 
							+ "Qualquer dúvida, entre em contato conosco!\n\n"
							+ "Sistema de Gerenciamento e Controle do TCC.";
					SendMail.sendMail(usuario.getEmail(), assunto, msg);
				}
			}.start();
			
			this.coordenadorSelecionado = new Coordenador();
			
			this.lista = null;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado!",
							"O coordenador foi cadastrado com sucesso."));
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro!",
									"O E-Mail deste usuário já está cadastrado."));
		}
	}

	public List<Coordenador> getLista() {
		CoordenadorRN coordenadorRN = new CoordenadorRN();

		if (this.lista == null) {
			this.lista = coordenadorRN.listar();
		}

		return this.lista;
	}

	public void excluir() {

		new CoordenadorRN().excluir(this.coordenadorSelecionado);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Coordenador excluido!", ""));

		this.lista = null;
	}

	public void alterar(ActionEvent event) {
		Disciplina disciplina = new DisciplinaRN().buscaPorId(disciplinaId);
		this.coordenadorSelecionado.setDisciplina(disciplina);
		
		Coordenacao coordenacao = new CoordenacaoRN().buscaPorId(coordenacaoId);
		this.coordenadorSelecionado.setCoordenacao(coordenacao);
		
		new CoordenadorRN().alterar(this.coordenadorSelecionado);
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Dados do coordenador alterado!", null));

	}

	public List<Disciplina> getDisciplinas() {
		return new DisciplinaRN().disciplinas();
	}
	
	public List<Coordenacao> getCoordenacoes() {
		return new CoordenacaoRN().listar();
	}
	
	public String prepararConta(Usuario usuario) {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		this.coordenadorSelecionado = coordenadorRN.findById(usuario.getId());

		return "redirecionaConta";
	}

	public String solicitarEditarConta(Usuario coordenador) {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		this.coordenadorSelecionado = coordenadorRN.findById(coordenador
				.getId());

		return "alterarConta.xhtml";
	}

	public String prepararEdicao(Coordenador coordenador) {
		this.coordenadorSelecionado = coordenador;
		return "edicao/alterarCoordenador.xhtml";
	}

	public String prepararEdicaoConta(Coordenador coordenador) {

		coordenador.setSenha(ConversorMD5.convertStringToMd5(coordenador
				.getSenha()));

		new CoordenadorRN().alterar(coordenador);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informações atualizadas!", ""));

		return "consultarDados.xhtml";
	}

	public Coordenador carregarCoordenador(Usuario usuario) {
		return new CoordenadorRN().findById(usuario.getId());
	}

	public String controleInicial(Usuario usuario) {
		this.coordenadorSelecionado = (Coordenador) usuario;

		if (coordenadorSelecionado.getStatus() == UsuarioStatus.Cadastrado)
			return "/pages/protected/coordenador/primeiroAcesso/passoInicial?faces-redirect=true";

		return "/pages/protected/coordenador/paginaInicial?faces-redirect=true";
	}

	public String primeiroAcesso(Usuario usuario) {
		this.coordenadorSelecionado = carregarCoordenador(usuario);
		return "atualizarDados.xhtml";
	}

	public String atualizarDados(Coordenador coordenador) {
		this.coordenadorSelecionado = coordenador;

		new CoordenadorRN().alterar(coordenadorSelecionado);

		return "alterarSenha.xhtml";
	}

	public String alterarSenha(Coordenador coordenador) {
		coordenadorSelecionado = coordenador;
		coordenadorSelecionado.setSenha(ConversorMD5
				.convertStringToMd5(coordenador.getSenha()));
		coordenadorSelecionado.setStatus(UsuarioStatus.Ativo);

		new CoordenadorRN().alterar(coordenadorSelecionado);

		return "../paginaInicial.xhtml";
	}

	public Coordenador getCoordenadorSelecionado() {
		return coordenadorSelecionado;
	}

	public void setCoordenadorSelecionado(Coordenador coordenadorSelecionado) {
		this.coordenadorSelecionado = coordenadorSelecionado;
	}

	public Integer getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public Integer getCoordenacaoId() {
		return coordenacaoId;
	}

	public void setCoordenacaoId(Integer coordenacaoId) {
		this.coordenacaoId = coordenacaoId;
	}
	
	public Coordenador limpaCoordenador() {
		return new Coordenador();
	}
}

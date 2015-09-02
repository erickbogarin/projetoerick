package com.sistema.usuario;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.sistema.external.ConversorMD5;
import br.com.sistema.external.PasswordGenerator;
import br.com.sistema.external.SendMail;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.coordenacao.CoordenacaoRN;
import com.sistema.role.Role;
import com.sistema.status.UsuarioStatus;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.aluno.AlunoBean;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorBean;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuarioSelecionado = new Usuario();

	private List<Usuario> bibliotecarios = null;

	private Aluno alunoSelecionado = new Aluno();

	private Coordenador coordenadorSelecionado = new Coordenador();

	private Orientador orientadorSelecionado = new Orientador();

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogin = new UsuarioLogin();

	@ManagedProperty(value = "#{alunoBean}")
	private AlunoBean alunoBean = new AlunoBean();

	@ManagedProperty(value = "#{orientadorBean}")
	private OrientadorBean orientadorBean = new OrientadorBean();

	private String senhaAtual;
	private String novaSenha;

	public List<Usuario> validarUsuario(Usuario usuario) {
		UsuarioRN usuarioRN = new UsuarioRN();

		return (List<Usuario>) usuarioRN.validateUsername(usuario.getEmail());
	}

	public String preparaCadastro() {
		alunoSelecionado = new Aluno();

		return "cadastrarAluno";
	}

	public void salvarOrientador() {
		if (validarUsuario(orientadorSelecionado).isEmpty()) {
			UsuarioRN usuarioRN = new UsuarioRN();

			orientadorSelecionado.setDataCadastro(new Date());
			orientadorSelecionado.setStatus(UsuarioStatus.Cadastrado);
			final String senha = PasswordGenerator.geraSenha(6);
			orientadorSelecionado.setSenha(ConversorMD5
					.convertStringToMd5(senha));

			usuarioRN.salvar(orientadorSelecionado);

			Coordenacao coordenacao = ((Coordenador) usuarioLogin.getUser())
					.getCoordenacao();

			coordenacao.adicionaOrientador(orientadorSelecionado);

			new CoordenacaoRN().alterar(coordenacao);

			final Usuario usuario = orientadorSelecionado;

			new Thread() {
				public void run() {
					String assunto = "Bem vindo ao Sistema de Gerenciamento e Controle do TCC: conta de acesso";
					String msg = "Olá "
							+ usuario.getNome()
							+ ",\n\n"
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

			orientadorSelecionado = new Orientador();

			orientadorBean.setLista(null);
			orientadorBean.setOrientadoresCoordenacao(null);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado!",
							"O orientador foi cadastrado com sucesso."));

		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro!",
									"A matrícula deste orientador já está cadastrada."));
		}
	}

	public void salvarBibliotecario() {
		if (validarUsuario(usuarioSelecionado).isEmpty()) {
			UsuarioRN usuarioRN = new UsuarioRN();

			usuarioSelecionado.setDataCadastro(new Date());
			usuarioSelecionado.setSenha("123456");
			usuarioSelecionado.setStatus(UsuarioStatus.Cadastrado);
			usuarioSelecionado.setRole(Role.BIBLIOTECARIO);
			// Email.enviarEmail(orientadorSelecionado);

			usuarioRN.salvar(usuarioSelecionado);
			usuarioSelecionado = new Usuario();

			bibliotecarios = null;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado!",
							"O bibliotecario foi cadastrado com sucesso."));
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro!",
									"A matrícula deste bibliotecario já está cadastrada."));
		}
	}

	public List<Usuario> getBibliotecarios() {

		if (bibliotecarios == null)
			bibliotecarios = new UsuarioRN().bibliotecarios();

		return bibliotecarios;
	}

	public void alterar() {
		UsuarioRN usuarioRN = new UsuarioRN();

		usuarioRN.alterar(this.usuarioSelecionado);

		FacesMessage faces = new FacesMessage("Dados atualizados!", null);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		bibliotecarios = null;
	}

	public String alterarBibliotecario() {
		this.alterar();
		return "Bibliotecarios";
	}

	public void excluir() {

		try {
			new UsuarioRN().excluir(usuarioSelecionado);

			FacesMessage faces = new FacesMessage("Conta deletada!", null);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);

			bibliotecarios = null;
		} catch (Exception ex) {
			FacesMessage faces = new FacesMessage("Erro ao excluir conta!",
					null);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);

			ex.printStackTrace();
		}

	}

	public void alterarSenha() {
		if (!(new UsuarioRN().validadePassword(
				ConversorMD5.convertStringToMd5(this.senhaAtual), this.usuarioLogin.getUser()))) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Senha atual incorreta!", null));

			return;
		}
		
		this.usuarioLogin.getUser().setSenha(ConversorMD5.convertStringToMd5(this.novaSenha));
		new UsuarioRN().alterar(this.usuarioLogin.getUser());

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Senha alterada!"));
	}

	public String prepararConta(Usuario usuario) {
		this.usuarioSelecionado = usuario;

		return "redirecionaConta";
	}

	public String prepararEdicao() {
		return "EditarConta";
	}

	public void recuperarSenha(ActionEvent event) {
		final Usuario usuario = new UsuarioRN().validateEmailMatricula(
				this.usuarioSelecionado.getEmail(),
				this.usuarioSelecionado.getMatricula());

		if (usuario == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"E-Mail ou matrícula incorreto!", null));
			return;
		}

		final String senha = PasswordGenerator.geraSenha(6);
		usuario.setSenha(senha);

		new Thread() {
			public void run() {
				String assunto = "Recuperação de senha - Sistema de Gerenciamento e Controle do TCC";
				String msg = "Olá, foi gerada uma nova senha para a sua conta!\n\n"
						+ " Nova senha: "
						+ senha
						+ "\n\n"
						+ "Acesso ao site: http://localhost:8080/sistemaTCCs/ \n\n"
						+ "Sistema de Gerenciamento e Controle do TCC.";
				SendMail.sendMail(usuario.getEmail(), assunto, msg);
			}
		}.start();

		usuario.setSenha(ConversorMD5.convertStringToMd5(usuario.getSenha()));
		new UsuarioRN().alterar(usuario);

		this.usuarioSelecionado = new Usuario();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(
						"Uma nova senha foi enviada para o e-mail informado!"));
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Coordenador getCoordenadorSelecionado() {
		return coordenadorSelecionado;
	}

	public void setCoordenadorSelecionado(Coordenador coordenadorSelecionado) {
		this.coordenadorSelecionado = coordenadorSelecionado;
	}

	public Orientador getOrientadorSelecionado() {
		return orientadorSelecionado;
	}

	public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
		this.orientadorSelecionado = orientadorSelecionado;
	}

	public void setAlunoBean(AlunoBean alunoBean) {
		this.alunoBean = alunoBean;
	}

	public void setUsuarioLogin(UsuarioLogin usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public void setOrientadorBean(OrientadorBean orientadorBean) {
		this.orientadorBean = orientadorBean;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}

package com.sistema.usuario.aluno;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import br.com.sistema.external.ConversorMD5;
import br.com.sistema.external.PasswordGenerator;
import br.com.sistema.external.SendMail;

import com.sistema.disciplina.Disciplina;
import com.sistema.disciplina.DisciplinaRN;
import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.periodo.Periodo;
import com.sistema.periodo.PeriodoBean;
import com.sistema.periodo.PeriodoRN;
import com.sistema.proposta.Proposta;
import com.sistema.proposta.PropostaRN;
import com.sistema.status.PropostaStatus;
import com.sistema.status.UsuarioStatus;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioLogin;
import com.sistema.usuario.UsuarioRN;
import com.sistema.usuario.coordenador.Coordenador;

@ManagedBean(name = "alunoBean")
@SessionScoped
public class AlunoBean {

	private Aluno alunoSelecionado = new Aluno();

	private List<Aluno> lista = null;

	private PeriodoBean periodoBean = new PeriodoBean();

	private Periodo periodoSelecionado = new Periodo();

	private Proposta proposta = new Proposta();

	private Integer periodoId;

	private Integer disciplinaId;

	private String disciplina;

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();

	@ManagedProperty(value = "#{mensagemBean}")
	private MensagemBean mensagemBean = new MensagemBean();

	private Mensagem mensagemSelecionada = new Mensagem();

	public AlunoBean() {
		if (!periodoBean.getLista().isEmpty())
			periodoSelecionado = new PeriodoRN().listar().get(0);
	}

	public String salvarAluno() {
		if (new UsuarioRN().findByEmail(alunoSelecionado.getEmail()) == null) {
			alunoSelecionado.setDataCadastro(new Date());
			final String senha = PasswordGenerator.geraSenha(6);
			alunoSelecionado.setSenha(ConversorMD5.convertStringToMd5(senha));
			alunoSelecionado.setStatus(UsuarioStatus.Cadastrado);

			if (usuarioLogado.getUser().isCoordenador()) {
				alunoSelecionado.setCoordenacao(((Coordenador) usuarioLogado
						.getUser()).getCoordenacao()); // assosia a coordenação
														// do coordenador ao
														// aluno

				Periodo periodo = new PeriodoRN()
						.pesquisarPorCodigo(this.periodoId);
				alunoSelecionado.adicionaPeriodo(periodo);

				Disciplina disciplina = new DisciplinaRN()
						.buscaPorId(getDisciplinaDoCoordenador().getId());
				alunoSelecionado.adicionaDisciplina(disciplina);

				// cria o primeiro e atual trabalho do aluno ao realizar o
				// cadastro
				Proposta proposta = new Proposta(alunoSelecionado,
						PropostaStatus.SOLICITAR, true);

				proposta.setDataCadastro(new Date());
				proposta.setPeriodo(periodo);
				proposta.setDisciplina(disciplina);

				new PropostaRN().solicitarProposta(proposta); // persiste o
																// trabalho no
																// banco de
																// dados

				alunoSelecionado.adicionaProposta(proposta);

				new UsuarioRN().salvar(alunoSelecionado);
				
				final Usuario usuario = alunoSelecionado;
				
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
				
				alunoSelecionado = new Aluno();
			}

			// cadastro do aluno realizado pelo bibliotecário
			else {
				new UsuarioRN().salvar(alunoSelecionado);
				return "Proposta";
			}

			this.lista = null;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado!",
							"O aluno foi cadastrado com sucesso."));

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"A matrícula deste aluno já está cadastrada."));
		}

		return "";
	}

	public List<Periodo> getPeriodos() {
		return new PeriodoRN().listar();
	}

	public Disciplina getDisciplinaDoCoordenador() {
		return ((Coordenador) this.usuarioLogado.getUser()).getDisciplina();
	}

	public void excluir() {
		try {
			for (Proposta proposta : alunoSelecionado.getPropostas()) {
				new PropostaRN().excluir(proposta);
			}

			new AlunoRN().excluir(alunoSelecionado);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Aluno excluido!", ""));
			lista = null;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Proposta cadastrada!",
							"O Aluno não pode ser excluido"));
		}

	}

	public void alterar() {
		alunoSelecionado.setSenha(ConversorMD5
				.convertStringToMd5(alunoSelecionado.getSenha()));
		new AlunoRN().alterar(this.alunoSelecionado);
	}

	public List<Aluno> getLista() {
		AlunoRN alunoRN = new AlunoRN();

		if (lista == null)
			lista = alunoRN.listar(periodoSelecionado.getId(),
					((Coordenador) usuarioLogado.getUser()).getDisciplina()
							.getId(), ((Coordenador) usuarioLogado.getUser())
							.getCoordenacao().getId());

		return lista;
	}

	public void onListaChange() {
		lista = null;
	}

	public String prepararConta(Usuario usuario) {
		this.alunoSelecionado = (Aluno) usuario;
		return "redirecionaConta";
	}

	public String prepararAtualizacao(Aluno aluno) {
		this.alunoSelecionado = aluno;
		this.alterar();
		return "alterarSenha.xhtml";
	}

	public String prepararEdicao() {
		return "editarAluno";
	}

	public String solicitarEdicaoConta(Usuario aluno) {
		AlunoRN alunoRN = new AlunoRN();

		this.alunoSelecionado = alunoRN.findId(aluno.getId());

		return "alterarConta.xhtml";
	}

	public Aluno carregarAluno(Usuario usuario) {
		AlunoRN alunoRN = new AlunoRN();
		return alunoRN.findId(usuario.getId());
	}

	public String primeiroAcesso(Usuario usuario) {
		this.alunoSelecionado = carregarAluno(usuario);
		return "atualizarDados.xhtml";
	}

	public String controleInicial(Usuario usuario) {
		this.alunoSelecionado = carregarAluno(usuario);

		if (alunoSelecionado.getStatus() == UsuarioStatus.Cadastrado)
			return "/pages/protected/aluno/primeiroAcesso/passoInicial?faces-redirect=true";

		return "/pages/protected/aluno/paginaInicial?faces-redirect=true";
	}

	public String alterarPrimeiroAcesso() {
		this.alunoSelecionado.setDataCadastro(new Date());
		this.alunoSelecionado.setStatus(UsuarioStatus.Ativo);

		this.alterar();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo!", ""));

		return "paginaInicial.xhtml";
	}

	public String alterarLista() {
		this.alterar();
		this.setLista(null);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado!",
						"Dados do aluno alterados com sucessos"));

		return "../listagem/listarAluno.xhtml";
	}

	public String atualizarDados() {

		this.alunoSelecionado.setStatus(UsuarioStatus.Ativo);

		this.alterar();

		String mensagem = "O status do aluno " + alunoSelecionado.getNome()
				+ " foi alterado para Ativo no sistema.";

		List<Coordenador> coordenadores = alunoSelecionado.getCoordenacao()
				.getCoordenadores();

		for (Coordenador coordenador : coordenadores) {
			mensagemSelecionada = new Mensagem();

			mensagemSelecionada.setMensagem(mensagem);
			mensagemSelecionada.setUsuario(coordenador);

			mensagemBean.enviarMensagem(mensagemSelecionada);
		}

		return "../paginaInicial.xhtml";
	}

	public void prepararAluno(Aluno aluno) {
		this.alunoSelecionado = aluno;
	}

	public Disciplina getDisciplinaAtual() {
		Disciplina disciplina = null;

		for (Disciplina d : this.alunoSelecionado.getDisciplinas()) {
			disciplina = d;
		}

		return disciplina;
	}

	public Periodo getPeriodoAtual() {
		Periodo periodo = null;

		for (Periodo p : this.alunoSelecionado.getPeriodos()) {
			periodo = p;
		}

		return periodo;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public void setPeriodoBean(PeriodoBean periodoBean) {
		this.periodoBean = periodoBean;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Mensagem getMensagemSelecionada() {
		return mensagemSelecionada;
	}

	public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
		this.mensagemSelecionada = mensagemSelecionada;
	}

	public void setMensagemBean(MensagemBean mensagemBean) {
		this.mensagemBean = mensagemBean;
	}

	public Integer getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(Integer periodoId) {
		this.periodoId = periodoId;
	}

	public Integer getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Aluno limparAluno() {
		return new Aluno();
	}

	public void carregaAluno(ComponentSystemEvent event) {
		this.alunoSelecionado = (Aluno) usuarioLogado.getUser();

		for (Proposta p : alunoSelecionado.getPropostas())
			if (p.getAtual() == true)
				this.proposta = p;
	}

}

package com.sistema.proposta;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.sistema.external.Cadastro;

import com.sistema.disciplina.Disciplina;
import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.periodo.Periodo;
import com.sistema.periodo.PeriodoBean;
import com.sistema.status.PropostaStatus;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioBean;
import com.sistema.usuario.UsuarioLogin;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.aluno.AlunoBean;
import com.sistema.usuario.aluno.AlunoRN;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorRN;

@ManagedBean
@SessionScoped
public class PropostaBean {

	private Proposta propostaSelecionada = new Proposta();

	private List<Proposta> orientados = null;
	private List<Proposta> solicitacoes = null;
	private List<Proposta> coordenados = null;
	private List<Proposta> coordenacaoITCC = null;
	private List<Proposta> coordenacaoTCC = null;
	private List<Proposta> aprovados = null;
	private List<Proposta> apresentacoes = null;
	private List<Proposta> andamento = null;
	private List<Proposta> projetos = null;
	private List<Proposta> tccs = null;
	private List<Proposta> consulta = null;
	private List<SelectItem> orientadorSelect;

	private PropostaStatus status;

	private Aluno alunoSelecionado = new Aluno();

	private Orientador orientadorSelecionado = new Orientador();

	private Coordenador coordenadorSelecionado = new Coordenador();

	private Periodo periodoSelecionado = new Periodo();
	private Periodo periodo = new Periodo();
	private StreamedContent file;

	private UploadedFile arquivo;

	@ManagedProperty(value = "#{mensagemBean}")
	private MensagemBean mensagemBean = new MensagemBean();

	private Mensagem mensagemSelecionada = new Mensagem();

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();

	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean usuarioBean = new UsuarioBean();

	@ManagedProperty(value = "#{alunoBean}")
	private AlunoBean alunoBean = new AlunoBean();

	private List<Periodo> periodos = null;

	private boolean skip;

	private String filtro = "Aprovado";

	private String disciplina;

	private String troca = null;

	private List<SelectItem> itensFiltro;

	private static Integer tccId = 1;
	private static Integer itccId = 2;

	public PropostaBean() {
		periodos = new PeriodoBean().getLista();

		if (!periodos.isEmpty()) {
			periodoSelecionado = periodos.get(0);
		}
		SelectItemGroup g1 = new SelectItemGroup("Modalidade");
		g1.setSelectItems(new SelectItem[] {
				new SelectItem("Artigo", "Artigo"),
				new SelectItem("Monografia", "Monografia"),
				new SelectItem("Produto Tecnologico", "Produto Tecnologico") });

		itensFiltro = new ArrayList<SelectItem>();
		itensFiltro.add(g1);
	}

	public Proposta verificarCadastro(Usuario aluno) {
		return new PropostaRN().verificarCadastro(aluno.getId());
	}

	public String preparaOrientacao() {
		propostaSelecionada = verificarCadastro(usuarioLogado.getUser());

		return "orientacao";
	}

	public String preparaOrientacoes() {
		periodoSelecionado = periodos.get(0);
		orientados = null;

		return "orientacoes";
	}

	public String prepararOrientados() {
		periodoSelecionado = periodos.get(0);
		orientados = null;

		return "andamento";
	}

	public String controleProposta() {
		this.propostaSelecionada = verificarCadastro(usuarioLogado.getUser());

		if (propostaSelecionada.getStatus() == PropostaStatus.SOLICITAR)
			return "solicitarOrientação";

		else if (propostaSelecionada.getStatus() == PropostaStatus.CADASTRAR)
			return "cadastrarProposta";

		else if (propostaSelecionada.getStatus() == PropostaStatus.REJEITADO)
			return "propostaRejeitada";

		return "emAndamento";
	}

	public void onListaChange() {
		orientados = null;
		coordenados = null;
		tccs = null;
		apresentacoes = null;
		coordenacaoTCC = null;
		coordenacaoITCC = null;
		this.periodo = new PeriodoBean().getPeriodoAtual();
	}

	public List<Proposta> getProjetos() {

		if (projetos == null)
			projetos = new PropostaRN().projetos(usuarioLogado.getUser()
					.getId());

		return projetos;
	}

	public List<Proposta> getAndamento() {

		if (andamento == null)
			andamento = new PropostaRN().listaAndamento(periodoSelecionado
					.getId());

		return andamento;
	}

	public List<Proposta> getApresentacoes() {
		Coordenador coordenador = (Coordenador) usuarioLogado.getUser();

		if (apresentacoes == null)
			apresentacoes = new PropostaRN().listaApresentacoes(coordenador
					.getCoordenacao().getId(), periodoSelecionado.getId(),
					disciplina);

		return apresentacoes;
	}

	public List<Proposta> getOrientados() {
		PropostaRN propostaRN = new PropostaRN();

		if (orientados == null)
			orientados = propostaRN.listarOrientados(usuarioLogado.getUser()
					.getId(), periodoSelecionado.getId(), disciplina);

		return orientados;
	}

	public void preparaConsulta() {
		consulta = null;
		this.getConsulta();
	}

	public List<Proposta> getConsulta() {

		if (consulta == null) {
			consulta = new PropostaRN().consulta(alunoSelecionado.getNome(),
					propostaSelecionada.getTema());
			Collections.sort(consulta, new PropostaComparator());
		}
		return consulta;
	}

	public List<Proposta> getTccs() {

		if (tccs == null) {
			tccs = new PropostaRN().tccs(filtro);
			Collections.sort(tccs, new PropostaComparator());
		}

		return tccs;
	}

	public List<Proposta> getAprovados() {

		if (aprovados == null)
			aprovados = new PropostaRN().aprovados();

		return aprovados;
	}

	public List<Proposta> getSolicitacoes() {
		PropostaRN propostaRN = new PropostaRN();

		if (solicitacoes == null)
			solicitacoes = propostaRN.listarSolicitacao(usuarioLogado.getUser()
					.getId());

		return solicitacoes;
	}

	public List<Proposta> getCoordenados() {

		Coordenador coordenador = (Coordenador) usuarioLogado.getUser();

		if (coordenados == null)
			coordenados = new PropostaRN().listarCoordenados(
					coordenador.getCoordenacao(), periodoSelecionado,
					coordenador.getDisciplina().getNome());

		return coordenados;
	}

	public List<Proposta> getCoordenacaoTCC() {
		Coordenador coordenador = (Coordenador) usuarioLogado.getUser();
		if (periodoSelecionado == this.periodo) {
			coordenacaoTCC = new PropostaRN().listarCoordenados(
					coordenador.getCoordenacao(), periodoSelecionado,
					this.disciplina);
		} else {
			coordenacaoTCC = new PropostaRN().reprovadosTCC(
					coordenador.getCoordenacao(), periodoSelecionado,
					this.disciplina);
		}

		return coordenacaoTCC;
	}

	public List<Proposta> getCoordenacaoITCC() {
		Coordenador coordenador = (Coordenador) usuarioLogado.getUser();
		if (periodoSelecionado == this.periodo) {
			this.coordenacaoITCC = new PropostaRN().listarCoordenados(
					coordenador.getCoordenacao(), periodoSelecionado,
					this.disciplina);
		} else {
			this.coordenacaoITCC = new PropostaRN().aprovadosITCC(
					coordenador.getCoordenacao(), periodoSelecionado,
					this.disciplina);
		}
		return this.coordenacaoITCC;
	}

	public StreamedContent getCorrecao() throws FileNotFoundException,
			SQLException {

		InputStream stream = new ByteArrayInputStream(
				propostaSelecionada.getCorrecao());
		file = new DefaultStreamedContent(stream, "application/doc",
				"proposta.doc");

		return file;
	}

	public void uploadTCC(FileUploadEvent event) {
		try {
			// Cria um arquivo UploadFile, para receber o arquivo do evento
			UploadedFile arq = event.getFile();
			// Transformar o arquivo em bytes para salvar em banco de dados
			byte[] bfile = event.getFile().getContents();

			propostaSelecionada.setTcc(bfile);
			propostaSelecionada.setStatusBiblioteca("Cadastrado");

			new PropostaRN().alterar(propostaSelecionada);

			FacesMessage msg = new FacesMessage("Arquivo ", arq.getFileName()
					+ " salvo em banco de dados.");
			FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);

			aprovados = null;
			tccs = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void prepararDownload(Proposta proposta) {
		this.propostaSelecionada = proposta;
	}

	/*
	 * public StreamedContent getFile() throws FileNotFoundException,
	 * SQLException {
	 * 
	 * InputStream stream = new ByteArrayInputStream(
	 * propostaSelecionada.getTcc()); file = new DefaultStreamedContent(stream,
	 * "application/pdf", "proposta.pdf");
	 * 
	 * return file; }
	 */

	public StreamedContent file(Proposta proposta)
			throws FileNotFoundException, SQLException {

		InputStream stream = new ByteArrayInputStream(proposta.getFile());
		file = new DefaultStreamedContent(stream, "application/pdf",
				"proposta.pdf");

		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public void fileUpload(FileUploadEvent event) throws IOException {
		try {
			// Cria um arquivo UploadFile, para receber o arquivo do evento
			UploadedFile arq = event.getFile();
			// Transformar a imagem em bytes para salvar em banco de dados
			byte[] bfile = event.getFile().getContents();

			propostaSelecionada = verificarCadastro(usuarioLogado.getUser());
			propostaSelecionada.setCorrecao(bfile);

			new PropostaRN().alterar(propostaSelecionada);

			FacesMessage msg = new FacesMessage("O Arquivo ", arq.getFileName()
					+ " salvo em banco de dados.");
			FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void rematricularEmTCC() {
		Aluno aluno = this.propostaSelecionada.getAluno();

		if (validarPeriodoLetivo(aluno)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"O aluno já esta matrículado no período letivo: "
									+ periodo.getNome()));
			return;
		}

		aluno.adicionaPeriodo(this.periodo);

		Proposta proposta = criaProposta(aluno,
				((Coordenador) usuarioLogado.getUser()).getDisciplina());
		aluno.adicionaProposta(proposta);

		new AlunoRN().alterar(aluno);

		this.propostaSelecionada.setAtual(false);
		new PropostaRN().alterar(propostaSelecionada);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("O aluno " + aluno.getNome()
						+ " foi matrículado no período letivo: "
						+ periodo.getNome()));
	}

	public void rematriculaEmITCC() {
		Aluno aluno = this.propostaSelecionada.getAluno();
		Disciplina disciplina = ((Coordenador) usuarioLogado.getUser())
				.getDisciplina();
		if (validarPeriodoLetivo(aluno)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"O aluno já esta matrículado no período letivo: "
									+ periodo.getNome()));
			return;
		}

		aluno.adicionaPeriodo(this.periodo);

		if (disciplina.getId() == tccId) {
			Proposta proposta = criaProposta(aluno, disciplina);
			
			aluno.adicionaDisciplina(disciplina);
			aluno.adicionaProposta(proposta);

			new AlunoRN().alterar(aluno);

		} else {
			criaProposta(aluno, disciplina);
			System.out.println("Hello!");
		}
		this.propostaSelecionada.setAtual(false);
		new PropostaRN().alterar(propostaSelecionada);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("O aluno " + aluno.getNome()
						+ " foi matrículado no período letivo: "
						+ periodo.getNome()));
	}

	private Boolean validarPeriodoLetivo(Aluno aluno) {
		for (Periodo p : aluno.getPeriodos()) {
			if (periodo.getId() == p.getId()) {
				System.out.println("ENTREI!");
				return true;
			}
		}
		return false;
	}

	private Proposta criaProposta(Aluno aluno, Disciplina disciplina) {
		Proposta proposta = new Proposta();
		proposta.setAluno(aluno);
		proposta.setPeriodo(this.periodo);
		proposta.setDisciplina(disciplina);
		proposta.setAtual(true);
		proposta.setDataCadastro(new Date());
		proposta.setStatus(PropostaStatus.SOLICITAR);

		new PropostaRN().solicitarProposta(proposta);

		return proposta;
	}

	public String solicitarProposta() {
		PropostaRN propostaRN = new PropostaRN();

		propostaSelecionada.setAluno(this.alunoSelecionado);
		propostaSelecionada.setOrientador(this.orientadorSelecionado);
		propostaSelecionada.setDataCadastro(new Date());
		propostaSelecionada.setAtual(true);
		propostaSelecionada.setStatus(PropostaStatus.VALIDANDO);
		propostaSelecionada.setStatusBiblioteca("Em andamento");

		propostaRN.cadastrarProposta(this.propostaSelecionada);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"A sua solicitação foi enviada ao orientador!",
						"Acompanhe abaixo as informações da sua solicitação."));

		mensagemSelecionada.setMensagem("O aluno " + alunoSelecionado.getNome()
				+ " enviou uma solicitação de orientação.");
		mensagemSelecionada.setUsuario(this.orientadorSelecionado);

		mensagemBean.enviarMensagem(mensagemSelecionada);

		return "andamento.xhtml?faces-redirect=true";
	}

	public void confirmarSolicitacao(Proposta proposta) throws Exception {
		PropostaRN propostaRN = new PropostaRN();
		proposta.setStatus(PropostaStatus.CADASTRAR);
		proposta.setStatusBiblioteca("Cadastrar");
		
		proposta.getOrientador().adicionaProposta(proposta);
		
		propostaRN.alterar(proposta);

		mensagemSelecionada.setMensagem("O orientador "
				+ proposta.getOrientador().getNome()
				+ " aceitou a sua proposta.");
		mensagemSelecionada.setUsuario(proposta.getAluno());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!",
								"Nova proposta foi adicionada a sua lista de orientados!."));

		solicitacoes = null;
		orientados = null;
	}

	public String salvarProposta() throws Exception {

		usuarioBean.setUsuarioSelecionado(propostaSelecionada.getAluno());
		usuarioBean.alterar();

		PropostaRN propostaRN = new PropostaRN();

		this.propostaSelecionada.setDataCadastro(new Date());
		this.propostaSelecionada.setStatus(PropostaStatus.ANALISE);

		propostaRN.cadastrarProposta(this.propostaSelecionada);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Proposta enviada!",
						"Aguarde a confirmação do orientador solicitado."));

		mensagemSelecionada.setMensagem("O aluno "
				+ propostaSelecionada.getAluno().getNome()
				+ " realizou o cadastro do TCC.");
		mensagemSelecionada.setUsuario(propostaSelecionada.getOrientador());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		mensagemSelecionada.setMensagem("O status do trabalho do aluno "
				+ propostaSelecionada.getAluno().getNome()
				+ " foi alterado para: 'Análise'.");

		List<Coordenador> coordenadores = this.propostaSelecionada.getAluno()
				.getCoordenacao().getCoordenadores();

		for (Coordenador coordenador : coordenadores) {
			mensagemSelecionada.setUsuario(coordenador);
			mensagemBean.enviarMensagem(mensagemSelecionada);
		}

		return "emAndamento";
	}

	public void recusarProposta(Proposta proposta) {

		mensagemSelecionada.setMensagem("O orientador "
				+ proposta.getOrientador().getNome()
				+ " recusou a sua proposta");
		mensagemSelecionada.setUsuario(proposta.getAluno());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		new PropostaRN().excluir(proposta);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Recusado!",
						"Uma notificação foi enviada ao aluno."));

		solicitacoes = null;
	}

	public String cadastrarTCC() {

		try {
			propostaSelecionada.setAluno(usuarioBean.getAlunoSelecionado());
			propostaSelecionada.setDataCadastro(new Date());
			propostaSelecionada.setStatus(PropostaStatus.VALIDANDO);
			propostaSelecionada.setStatusBiblioteca("Cadastrar");

			new PropostaRN().solicitarProposta(propostaSelecionada);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado!",
							null));

			aprovados = null;
			tccs = null;
		} catch (Exception e) {
			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Recusado!",
							"Uma notificação foi enviada ao aluno."));
		}

		return "../paginaInicial.xhtml";
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public List<SelectItem> getOrientadorSelect() {

		if (orientadorSelect == null) {
			orientadorSelect = new ArrayList<SelectItem>();
			OrientadorRN orientadorRN = new OrientadorRN();
			List<Orientador> listaOrientadores = orientadorRN.listar();

			if (listaOrientadores != null && !listaOrientadores.isEmpty()) {
				SelectItem item;
				for (Orientador orientadorLista : listaOrientadores) {
					item = new SelectItem(orientadorLista,
							orientadorLista.getNome());
					orientadorSelect.add(item);
				}
			}

		}

		return orientadorSelect;
	}

	public void prepararAlteracoes(Proposta proposta) {
		this.propostaSelecionada = proposta;
		this.propostaSelecionada.setStatus(this.status);
		this.coordenadorSelecionado = (Coordenador) usuarioLogado.getUser();
		this.setTroca(null);
	}

	public void alterarProposta() throws Exception {

		System.out.println("Alterando status para: "
				+ this.propostaSelecionada.getStatus());

		if (this.propostaSelecionada.getStatus() == PropostaStatus.REMATRICULAR) {
			
			//if(this.propostaSelecionada.getDisciplina())
				//rematriculaEmITCC();
			
		}

		else if (this.propostaSelecionada.getStatus() == PropostaStatus.ANDAMENTO) {

			Cadastro.geradorPDF(this.propostaSelecionada);

			File file = new File("out.pdf");
			byte[] bFile = new byte[(int) file.length()];

			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.propostaSelecionada.setFile(bFile);
			this.propostaSelecionada.setApresentacaoStatus("Agendar");

			apresentacoes = null;

		}

		else if (this.propostaSelecionada.getStatus() == PropostaStatus.CADASTRAR) {
			propostaSelecionada.setStatus(status);
			propostaSelecionada.setFile(null);
		}

		andamento = null;

		new PropostaRN().cadastrarProposta(propostaSelecionada);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Projeto alterado!", ""));
	}

	public void preparaProposta(Proposta proposta) {
		propostaSelecionada = proposta;
	}

	public void setTccs(List<Proposta> tccs) {
		this.tccs = tccs;
	}

	public void setAlunosCoordenacao(List<Proposta> alunosCoordenacao) {
		this.coordenacaoITCC = alunosCoordenacao;
	}

	public void setOrientadorSelect(List<SelectItem> orientadorSelect) {
		this.orientadorSelect = orientadorSelect;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Orientador getOrientadorSelecionado() {
		return orientadorSelecionado;
	}

	public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
		this.orientadorSelecionado = orientadorSelecionado;
	}

	public Proposta getPropostaSelecionada() {
		return propostaSelecionada;
	}

	public void setPropostaSelecionada(Proposta propostaSelecionada) {
		this.propostaSelecionada = propostaSelecionada;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public void setMensagemBean(MensagemBean mensagemBean) {
		this.mensagemBean = mensagemBean;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<SelectItem> getItensFiltro() {
		return itensFiltro;
	}

	public void setItensFiltro(List<SelectItem> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public void setCoordenados(List<Proposta> coordenados) {
		this.coordenados = coordenados;
	}

	public void setOrientados(List<Proposta> orientados) {
		this.orientados = orientados;
	}

	public void setAlunoBean(AlunoBean alunoBean) {
		this.alunoBean = alunoBean;
	}

	public void setApresentacoes(List<Proposta> apresentacoes) {
		this.apresentacoes = apresentacoes;
	}

	public PropostaStatus getStatus() {
		return status;
	}

	public void setStatus(PropostaStatus status) {
		this.status = status;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public PropostaStatus getAnalise() {
		return PropostaStatus.ANALISE;
	}

	public String getTroca() {
		return troca;
	}

	public void setTroca(String troca) {
		this.troca = troca;
	}

	public Coordenador getCoordenadorSelecionado() {
		return coordenadorSelecionado;
	}

	public void setCoordenadorSelecionado(Coordenador coordenadorSelecionado) {
		this.coordenadorSelecionado = coordenadorSelecionado;
	}

	public String disciplinaAtual(Aluno aluno) {
		Disciplina disciplina = null;

		for (Disciplina d : aluno.getDisciplinas()) {
			disciplina = d;
		}

		return disciplina.getNome();
	}

	public SelectItem[] getTiposStatus() {
		SelectItem[] items = new SelectItem[PropostaStatus.values().length];

		int i = 0;
		for (PropostaStatus p : PropostaStatus.values()) {
			items[i++] = new SelectItem(p, p.getStatus());
		}
		return items;
	}
}

package com.sistema.banca;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.sistema.external.Ata;
import br.com.sistema.external.Avaliacao;
import br.com.sistema.external.DateUtils;
import br.com.sistema.external.Declaracao;

import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.periodo.Periodo;
import com.sistema.periodo.PeriodoBean;
import com.sistema.proposta.Proposta;
import com.sistema.proposta.PropostaBean;
import com.sistema.proposta.PropostaRN;
import com.sistema.usuario.UsuarioLogin;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorRN;

@ManagedBean
@SessionScoped
public class BancaBean {

	private Proposta propostaSelecionada = new Proposta();

	private List<Banca> apresentacoes = null;
	private List<Banca> bancaAlunos = null;
	private List<Banca> orientados = null;
	private Banca bancaSelecionada = new Banca();

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin usuarioLogado = new UsuarioLogin();

	@ManagedProperty(value = "#{mensagemBean}")
	private MensagemBean mensagemBean = new MensagemBean();

	@ManagedProperty(value = "#{propostaBean}")
	private PropostaBean propostaBean = new PropostaBean();

	private Mensagem mensagemSelecionada = new Mensagem();

	private List<Periodo> periodos = null;
	private Periodo periodoSelecionado = new Periodo();

	private StreamedContent file;

	public BancaBean() {
		periodos = new PeriodoBean().getLista();

		if (!periodos.isEmpty())
			periodoSelecionado = periodos.get(0);
	}

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<String>();
		List<Orientador> orientadores = new OrientadorRN().listar();

		for (int i = 0; i < orientadores.size(); i++) {
			Orientador orientador = orientadores.get(i);
			results.add(orientador.getNome());
		}

		return results;
	}
	
	public List<Banca> getBancaAlunos() {
		
		if(bancaAlunos == null)
			bancaAlunos = new BancaRN().listar(this.periodoSelecionado);
			
		return bancaAlunos;
	}
	
	public void salvarBanca() throws Exception {

		BancaRN bancaRN = new BancaRN();

		bancaSelecionada.setProposta(propostaSelecionada);

		bancaRN.salvar(bancaSelecionada);

		propostaSelecionada.setApresentacaoStatus("Agendado");
		new PropostaRN().alterar(propostaSelecionada);

		propostaBean.setApresentacoes(null);

		FacesMessage faces = new FacesMessage(
				"Apresentação agendada com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		Mensagem mensagemSelecionada = new Mensagem();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Declaracao.declaracao(bancaSelecionada);

		File declaracao = new File("out.pdf");

		byte[] bFile = new byte[(int) declaracao.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(declaracao);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		bancaSelecionada.setDeclaracao(bFile);

		Ata.ata(propostaSelecionada);

		File proposta = new File("out.pdf");
		bFile = new byte[(int) proposta.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(proposta);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		bancaSelecionada.setAta(bFile);

		Avaliacao.avaliacao(propostaSelecionada);

		File avaliacao = new File("out.pdf");
		bFile = new byte[(int) avaliacao.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(avaliacao);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		bancaSelecionada.setAvaliacao(bFile);

		mensagemSelecionada
				.setMensagem("Foi agendada a sua apresentação final para o dia "
						+ df.format(bancaSelecionada.getDataApresentacao()));
		mensagemSelecionada.setUsuario(propostaSelecionada.getAluno());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		bancaSelecionada = new Banca();

		apresentacoes = null;
		orientados = null;
	}

	public void cancelar() {

		FacesMessage faces = new FacesMessage(
				"Apresentação cancelada com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		String dataFormatada = new SimpleDateFormat("dd/MM/yyy")
				.format(bancaSelecionada.getDataApresentacao());
		String horarioFormatado = new SimpleDateFormat("HH:mm")
				.format(bancaSelecionada.getDataApresentacao());

		mensagemSelecionada
				.setMensagem("Apresentação agendada para o aluno "
						+ propostaSelecionada.getAluno().getNome() + " no dia "
						+ dataFormatada + "as " + horarioFormatado
						+ "h. foi cancelada");
		mensagemSelecionada.setUsuario(propostaSelecionada.getOrientador());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		mensagemSelecionada = new Mensagem();

		mensagemSelecionada.setMensagem("Apresentação agendada para o dia "
				+ dataFormatada + "as " + horarioFormatado + "h.");
		mensagemSelecionada.setUsuario(propostaSelecionada.getOrientador());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		new BancaRN().excluir(bancaSelecionada);

		apresentacoes = null;
		orientados = null;
	}

	public String banca() {

		bancaSelecionada = new BancaRN().banca(usuarioLogado.getUser().getId());

		return "banca";
	}

	public void onListaChange() {
		apresentacoes = null;
		orientados = null;
	}

	public List<Banca> getApresentacoes() {

		Coordenador coordenador = (Coordenador) usuarioLogado.getUser();

		if (apresentacoes == null)
			apresentacoes = new BancaRN().apresentacoes(
					coordenador.getCoordenacao(), periodoSelecionado.getId());

		return apresentacoes;
	}
	
	public void setApresentacoes(List<Banca> apresentacoes) {
		this.apresentacoes = apresentacoes;
	}

	public String prepararOrientados() {
		periodoSelecionado = periodos.get(0);
		orientados = null;

		return "apresentacoes";
	}

	public List<Banca> getOrientados() {

		Orientador orientador = (Orientador) usuarioLogado.getUser();

		orientados = new BancaRN().orientados(orientador.getId(),
				periodoSelecionado.getId());

		return orientados;
	}

	public void preparaApresentacao() {
		bancaSelecionada = new Banca();
	}

	public Proposta getPropostaSelecionada() {
		return propostaSelecionada;
	}

	public void setPropostaSelecionada(Proposta propostaSelecionada) {
		this.propostaSelecionada = propostaSelecionada;
	}

	public Banca getBancaSelecionada() {
		return bancaSelecionada;
	}

	public void setBancaSelecionada(Banca bancaSelecionada) {
		this.bancaSelecionada = bancaSelecionada;
	}

	public void setUsuarioLogado(UsuarioLogin usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void setMensagemBean(MensagemBean mensagemBean) {
		this.mensagemBean = mensagemBean;
	}

	public Mensagem getMensagemSelecionada() {
		return mensagemSelecionada;
	}

	public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
		this.mensagemSelecionada = mensagemSelecionada;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public void setPropostaBean(PropostaBean propostaBean) {
		this.propostaBean = propostaBean;
	}

	public String getSampleDay() {
		if (bancaSelecionada.getDataApresentacao() == null) {
			return ("No date selected.");
		} else {
			String message = String
					.format("You choose '%s'.", DateUtils
							.formatDay(bancaSelecionada.getDataApresentacao()));
			return (message);
		}
	}

	public String getSampleTime() {
		if (bancaSelecionada.getDataApresentacao() == null) {
			return ("Sem data/hora selecionada.");
		} else {
			String message = String.format("Data selecionada: '%s'.", DateUtils
					.formatTime(bancaSelecionada.getDataApresentacao()));
			return (message);
		}
	}

	public StreamedContent getDeclaracao() throws FileNotFoundException,
			SQLException {

		InputStream stream = new ByteArrayInputStream(
				bancaSelecionada.getDeclaracao());
		file = new DefaultStreamedContent(stream, "application/pdf",
				"declaracao.pdf");

		return file;
	}

	public StreamedContent getAta() throws FileNotFoundException, SQLException {

		InputStream stream = new ByteArrayInputStream(bancaSelecionada.getAta());
		file = new DefaultStreamedContent(stream, "application/pdf",
				"ata_avaliacao.pdf");

		return file;
	}

	public StreamedContent getAvaliacao() throws FileNotFoundException, SQLException {

		InputStream stream = new ByteArrayInputStream(bancaSelecionada.getAvaliacao());
		file = new DefaultStreamedContent(stream, "application/pdf",
				"ficha_avaliacao.pdf");

		return file;
	}
}

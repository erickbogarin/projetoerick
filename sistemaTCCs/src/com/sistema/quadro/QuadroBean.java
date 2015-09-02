package com.sistema.quadro;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.showcase.view.message.MessagesView;

import br.com.sistema.external.Reuniao;

import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.proposta.Proposta;
import com.sistema.proposta.PropostaBean;
import com.sistema.reuniao.ReuniaoBean;
import com.sistema.usuario.Usuario;

@ManagedBean(name = "quadroBean")
@SessionScoped
public class QuadroBean {

	@ManagedProperty(value = "#{propostaBean}")
	private PropostaBean propostaBean;

	@ManagedProperty(value = "#{reuniaoBean}")
	private ReuniaoBean reuniaoBean = new ReuniaoBean();

	@ManagedProperty(value = "#{mensagemBean}")
	private MensagemBean mensagemBean = new MensagemBean();

	private Proposta propostaSelecionada = new Proposta();

	private Quadro quadroSelecionado = new Quadro();
	private List<Quadro> lista = null;

	private StreamedContent file;

	public String salvar() throws Exception {
		QuadroRN quadroRN = new QuadroRN();

		quadroSelecionado.setProposta(propostaSelecionada);
		quadroSelecionado.setDataCadastro(new Date());

		reuniaoBean.setQuadroSelecionado(quadroSelecionado);
		reuniaoBean.setPropostaSelecionada(propostaSelecionada);

		Reuniao.reuniao(reuniaoBean);

		File file = new File("out.pdf");
		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		quadroSelecionado.setFile(bFile);

		quadroRN.salvar(quadroSelecionado);

		Mensagem mensagemSelecionada = new Mensagem();

		mensagemSelecionada.setMensagem("o orientador "
				+ propostaSelecionada.getOrientador().getNome()
				+ " registrou uma nova avaliação no Quadro de Acompanhamento.");
		mensagemSelecionada.setUsuario(propostaSelecionada.getAluno());
		
		mensagemBean.enviarMensagem(mensagemSelecionada);
		
		FacesMessage faces = new FacesMessage(
				"Avaliação cadastrada com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		lista = null;
		quadroSelecionado = new Quadro();

		return "listagemQuadro.xhtml";
	}

	public void excluir() {
		QuadroRN quadroRN = new QuadroRN();
		quadroRN.excluir(quadroSelecionado);

		FacesMessage faces = new FacesMessage("Avaliação excluida com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		lista = null;
	}

	public String prepararQuadro(Usuario aluno) {
		this.propostaSelecionada = propostaBean.verificarCadastro(aluno);

		if (propostaSelecionada == null) {
			new MessagesView().infoEmptyProposta();
			return "";
		}

		return "andamentoQuadro";
	}

	public String prepararCadastro(Proposta proposta) {
		System.out.println(proposta.getTema());
		return "cadastroQuadro.xhtml";
	}

	public List<Quadro> getLista() {
		QuadroRN quadroRN = new QuadroRN();

		lista = quadroRN.listar(propostaSelecionada.getId());

		return lista;
	}

	public void preparaQuadro() {
		this.quadroSelecionado = new Quadro();
	}

	public void setLista(List<Quadro> lista) {
		this.lista = lista;
	}

	public Quadro getQuadroSelecionado() {
		return quadroSelecionado;
	}

	public void setQuadroSelecionado(Quadro quadroSelecionado) {
		this.quadroSelecionado = quadroSelecionado;
	}

	public Proposta getPropostaSelecionada() {
		return propostaSelecionada;
	}

	public void setPropostaSelecionada(Proposta propostaSelecionada) {
		this.propostaSelecionada = propostaSelecionada;
	}

	public void setPropostaBean(PropostaBean propostaBean) {
		this.propostaBean = propostaBean;
	}

	public void setReuniaoBean(ReuniaoBean reuniaoBean) {
		this.reuniaoBean = reuniaoBean;
	}

	public void prepararDownload(Quadro quadro) {
		this.quadroSelecionado = quadro;
	}

	public StreamedContent getFile() {

		System.out.println(quadroSelecionado.getAvaliacaoResultados());

		InputStream stream = new ByteArrayInputStream(
				quadroSelecionado.getFile());
		file = new DefaultStreamedContent(stream, "application/pdf",
				"acompanhamento.pdf");

		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public void setMensagemBean(MensagemBean mensagemBean) {
		this.mensagemBean = mensagemBean;
	}

}

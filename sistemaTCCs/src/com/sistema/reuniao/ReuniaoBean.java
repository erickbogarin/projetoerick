package com.sistema.reuniao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sistema.mensagem.Mensagem;
import com.sistema.mensagem.MensagemBean;
import com.sistema.proposta.Proposta;
import com.sistema.proposta.PropostaBean;
import com.sistema.quadro.Quadro;
import com.sistema.status.PropostaStatus;
import com.sistema.status.ReuniaoStatus;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioLogin;

@ManagedBean(name = "reuniaoBean")
@SessionScoped
public class ReuniaoBean {

	@ManagedProperty(value = "#{usuarioLogin}")
	private UsuarioLogin user;

	@ManagedProperty(value = "#{mensagemBean}")
	private MensagemBean mensagemBean;

	@ManagedProperty(value = "#{propostaBean}")
	private PropostaBean propostaBean;
	
	
	private List<Reuniao> listaCadastroAluno = null;
	private List<Reuniao> listaCadastroGeral = null;
	private List<Reuniao> listaReuniaoAluno = null;
	private List<Reuniao> listaReuniaoGeral = null;
	private List<Reuniao> periodos = null;

	private Reuniao reuniaoSelecionada = new Reuniao();

	private Proposta propostaSelecionada = new Proposta();

	private Quadro quadroSelecionado = new Quadro();

	public void cadastrarReuniao(Usuario usuario) {
		ReuniaoRN reuniaoRN = new ReuniaoRN();

		Locale local = new Locale("pt", "BR");
		DateFormat df = new SimpleDateFormat("MMMM", local);

		reuniaoSelecionada.setPeriodo(df.format(reuniaoSelecionada
				.getDataReuniao()));

		reuniaoSelecionada.setProposta(propostaSelecionada);
		reuniaoSelecionada.setStatus(ReuniaoStatus.Cadastrado);

		reuniaoRN.agendarReuniao(reuniaoSelecionada);

		FacesMessage faces = new FacesMessage("Reuniao cadastrada com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		Mensagem mensagemSelecionada = new Mensagem();

		mensagemSelecionada.setMensagem("O aluno "
				+ propostaSelecionada.getAluno().getNome()
				+ " cadastrou uma nova reunião realizada.");
		mensagemSelecionada.setUsuario(propostaSelecionada.getOrientador());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		reuniaoSelecionada = new Reuniao();

		listaCadastroAluno = null;
		listaCadastroGeral = null;
		listaReuniaoGeral = null;
	}

	public List<Reuniao> getListaCadastroAluno() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();

		if (listaCadastroAluno == null)
			listaCadastroAluno = reuniaoRN
					.listarAgendamento(propostaSelecionada.getId());

		return listaCadastroAluno;
	}

	public List<Reuniao> getListaCadastroGeral() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();

		listaCadastroGeral = reuniaoRN.listarAgendamentoGeral(user.getUser()
				.getId(), propostaSelecionada.getAluno().getId());

		return listaCadastroGeral;
	}

	public void confirmarReuniao() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();
		reuniaoSelecionada.setStatus(ReuniaoStatus.Confirmado);
		reuniaoRN.atualizarReuniao(reuniaoSelecionada);

		Mensagem mensagemSelecionada = new Mensagem();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		mensagemSelecionada.setMensagem("O orientador "
				+ reuniaoSelecionada.getProposta().getOrientador().getNome()
				+ " confirmou a reunião registrada no dia "
				+ df.format(reuniaoSelecionada.getDataReuniao()) + ".");
		mensagemSelecionada.setUsuario(propostaSelecionada.getOrientador());

		mensagemBean.enviarMensagem(mensagemSelecionada);

		listaCadastroAluno = null;
		listaCadastroGeral = null;
		listaReuniaoAluno = null;
		listaReuniaoGeral = null;
	}

	public List<Reuniao> getListaReuniaoAluno() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();

		listaReuniaoAluno = reuniaoRN
				.listarReuniao(propostaSelecionada.getId());

		return listaReuniaoAluno;
	}

	public List<Reuniao> getListaReuniaoGeral() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();

		listaReuniaoGeral = reuniaoRN
				.listarReuniaoGeral(user.getUser().getId());

		return listaReuniaoGeral;
	}

	public void cancelarReuniao() {
		ReuniaoRN reuniaoRN = new ReuniaoRN();
		reuniaoSelecionada.setStatus(ReuniaoStatus.Cancelado);
		reuniaoRN.cancelarReuniao(this.reuniaoSelecionada);

		listaReuniaoAluno = null;
		listaCadastroAluno = null;
	}

	public String controleProposta(Usuario aluno) {
		propostaSelecionada = propostaBean.verificarCadastro(aluno);

		if (propostaSelecionada == null
				|| propostaSelecionada.getStatus() == PropostaStatus.VALIDANDO
				|| propostaSelecionada.getStatus() == PropostaStatus.REJEITADO)
			return "emAndamento";

		return "quadroAcompanhamento";
	}

	public List<Reuniao> getPeriodos() {

		periodos = new ReuniaoRN().listaReuniaoPerido(
				propostaSelecionada.getId(), quadroSelecionado.getPeriodo());

		return periodos;
	}

	public String prepararReunioes() {
		return "listagemReuniao.xhtml?faces-redirect=true";
	}

	public List<Proposta> listaOrientados() {
		return this.propostaBean.getOrientados();
	}

	public Proposta getPropostaSelecionada() {
		return propostaSelecionada;
	}

	public void setPropostaSelecionada(Proposta propostaSelecionada) {
		this.propostaSelecionada = propostaSelecionada;
	}

	public Reuniao getReuniaoSelecionada() {
		return reuniaoSelecionada;
	}

	public void setReuniaoSelecionada(Reuniao reuniaoSelecionada) {
		this.reuniaoSelecionada = reuniaoSelecionada;
	}

	public void setListaSolicitacao(List<Reuniao> listaAgendamento) {
		this.listaCadastroAluno = listaAgendamento;
	}

	public void setListaSolicitacaoGeral(List<Reuniao> listaSolicitacaoGeral) {
		this.listaCadastroGeral = listaSolicitacaoGeral;
	}

	public void setListarReuniao(List<Reuniao> listaReuniao) {
		this.listaReuniaoAluno = listaReuniao;
	}

	public void setListaReuniaoGeral(List<Reuniao> listaReuniaoGeral) {
		this.listaReuniaoGeral = listaReuniaoGeral;
	}

	public void setMensagemBean(MensagemBean mensagemBean) {
		this.mensagemBean = mensagemBean;
	}

	public void setPropostaBean(PropostaBean propostaBean) {
		this.propostaBean = propostaBean;
	}

	public void setUser(UsuarioLogin user) {
		this.user = user;
	}

	public Quadro getQuadroSelecionado() {
		return quadroSelecionado;
	}

	public void setQuadroSelecionado(Quadro quadroSelecionado) {
		this.quadroSelecionado = quadroSelecionado;
	}

}

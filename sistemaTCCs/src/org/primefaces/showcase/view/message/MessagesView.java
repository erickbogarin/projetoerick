package org.primefaces.showcase.view.message;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class MessagesView {

	public void info() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Atividade cadastrada com sucesso!."));
	}

	public void warn() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
						"Watch out for PrimeFaces."));
	}

	public void infoEmptyMessage() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Você ainda não possui mensagens.", ""));
	}

	public void infoEmptyOrientado() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Você ainda não possui alunos para orientar neste período letivo.",
								""));
	}

	public void infoEmptyProjetos() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda não foram cadastrados alunos para este período letivo.",
								""));
	}

	public void infoEmptyTCCs() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Nenhum registro encontrado.", ""));
	}

	public void infoEmptyProjetosEmAndamento() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Ainda não foram divulgados os projetos em andamento para este período letivo.",
								""));
	}

	public void infoEmptyNews() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ainda não foram anunciadas novas notícias.", ""));
	}

	public void infoEmptyUsers() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda não foram cadastrados usuários para este perfil.",
								""));
	}

	public void infoEmptyPeriodo() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!",
						"Nenhum período foi cadastrado"));
	}

	public void infoEmptyAtividades() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda não foram definidas atividades para este período",
								""));
	}

	public void infoEmptyProposta() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Você ainda não cadastrou a sua proposta."));
	}

	public void infoEmptyQuadro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ainda não foram realizadas avaliações para este aluno.", ""));
	}

	public void infoEmptyReuniao() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Nenhum registro de reuniões realizadas foi cadastrado.",
								""));
	}

	public void infoEmptyReunioes() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma reunião confirmada.",
						""));
	}

	public void infoEmptyCoordenador() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!",
						"Nenhum coordenador cadastrado."));
	}

	public void infoEmptyBanca() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda não foram definidas as datas das apresentações.",
								""));
	}

	public void infoLogout() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout!",
						"Sessão encerrada com sucesso."));
	}

	public void error() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Contact admin."));
	}

	public void fatal() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!",
						"System Error"));
	}

}

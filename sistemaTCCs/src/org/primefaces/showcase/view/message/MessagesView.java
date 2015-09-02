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
						"Voc� ainda n�o possui mensagens.", ""));
	}

	public void infoEmptyOrientado() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Voc� ainda n�o possui alunos para orientar neste per�odo letivo.",
								""));
	}

	public void infoEmptyProjetos() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda n�o foram cadastrados alunos para este per�odo letivo.",
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
								"Ainda n�o foram divulgados os projetos em andamento para este per�odo letivo.",
								""));
	}

	public void infoEmptyNews() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ainda n�o foram anunciadas novas not�cias.", ""));
	}

	public void infoEmptyUsers() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda n�o foram cadastrados usu�rios para este perfil.",
								""));
	}

	public void infoEmptyPeriodo() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aten��o!",
						"Nenhum per�odo foi cadastrado"));
	}

	public void infoEmptyAtividades() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_WARN,
								"Ainda n�o foram definidas atividades para este per�odo",
								""));
	}

	public void infoEmptyProposta() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!",
						"Voc� ainda n�o cadastrou a sua proposta."));
	}

	public void infoEmptyQuadro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ainda n�o foram realizadas avalia��es para este aluno.", ""));
	}

	public void infoEmptyReuniao() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Nenhum registro de reuni�es realizadas foi cadastrado.",
								""));
	}

	public void infoEmptyReunioes() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma reuni�o confirmada.",
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
								"Ainda n�o foram definidas as datas das apresenta��es.",
								""));
	}

	public void infoLogout() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout!",
						"Sess�o encerrada com sucesso."));
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

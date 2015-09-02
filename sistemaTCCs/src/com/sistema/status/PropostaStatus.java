package com.sistema.status;


public enum PropostaStatus {
	
	SOLICITAR("Solicitar orientador"),
	VALIDANDO("Validando solicitação"),
	CADASTRAR("Cadastrar Projeto Final"),
	ANALISE("Em análise"),
	ANDAMENTO("Em andamento"),
	REJEITADO("Solicitação rejeitada"),
	ACEITO("Aceito"),
	APROVADO("Aprovado"),
	REPROVADO("Reprovado"),
	REMATRICULAR("Rematricular aluno");
	
	private PropostaStatus(String status) {
		this.status = status;
	}
	
	private String status;
	
	public String getStatus() {
		return status;
	}
	
}

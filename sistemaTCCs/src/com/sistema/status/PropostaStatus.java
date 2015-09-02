package com.sistema.status;


public enum PropostaStatus {
	
	SOLICITAR("Solicitar orientador"),
	VALIDANDO("Validando solicita��o"),
	CADASTRAR("Cadastrar Projeto Final"),
	ANALISE("Em an�lise"),
	ANDAMENTO("Em andamento"),
	REJEITADO("Solicita��o rejeitada"),
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

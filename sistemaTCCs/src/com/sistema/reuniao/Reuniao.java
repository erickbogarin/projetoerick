package com.sistema.reuniao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.proposta.Proposta;
import com.sistema.status.ReuniaoStatus;

@Entity
@Table (name = "reuniao")
public class Reuniao {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column (name = "data_reuniao")
	private Date dataReuniao;
	
	private String periodo;
	
	@Column (name = "assunto_discutido")
	private String assuntoDiscutido;
	
	@Column (name = "resultados_obtidos")
	private String resultadosObtidos;
	
	@Enumerated(EnumType.STRING)
	private ReuniaoStatus status;
	
	@ManyToOne
	@JoinColumn(name = "proposta_id", nullable=false)
	private Proposta proposta;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataReuniao() {
		return dataReuniao;
	}

	public void setDataReuniao(Date dataReuniao) {
		this.dataReuniao = dataReuniao;
	}
	
	public ReuniaoStatus getStatus() {
		return status;
	}

	public void setStatus(ReuniaoStatus status) {
		this.status = status;
	}

	public String getAssuntoDiscutido() {
		return assuntoDiscutido;
	}

	public void setAssuntoDiscutido(String assuntoDiscutido) {
		this.assuntoDiscutido = assuntoDiscutido;
	}

	public String getResultadosObtidos() {
		return resultadosObtidos;
	}

	public void setResultadosObtidos(String resultadosObtidos) {
		this.resultadosObtidos = resultadosObtidos;
	}
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

}

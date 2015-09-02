package com.sistema.quadro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.proposta.Proposta;

@Entity
@Table (name = "quadro")
public class Quadro {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	private String periodo;
	
	@Column (name  = "avaliacao_resultados")
	private String avaliacaoResultados;
	
	private String status;
	
	@Lob
	private byte[] file;
	
	@ManyToOne
	@JoinColumn(name = "id_proposta")
	private Proposta proposta;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String dataInicio) {
		this.periodo = dataInicio;
	}

	public String getAvaliacaoResultados() {
		return avaliacaoResultados;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setAvaliacaoResultados(String avaliacaoResultados) {
		this.avaliacaoResultados = avaliacaoResultados;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
}

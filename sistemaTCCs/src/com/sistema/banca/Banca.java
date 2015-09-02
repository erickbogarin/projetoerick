package com.sistema.banca;

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
@Table(name = "banca")
public class Banca {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(name = "data_apresentacao")
	private Date dataApresentacao;
	
	@Column(name = "primeiro_convidado")
	private String primeiroConvidado;
	
	@Column(name = "segundo_convidado")
	private String segundoConvidado;
	
	@ManyToOne
	@JoinColumn(name = "proposta_id")
	private Proposta proposta;
	
	private String sala;
	
	@Lob
	private byte[] declaracao;
	
	@Lob
	private byte[] ata;

	@Lob
	private byte[] avaliacao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}

	public String getPrimeiroConvidado() {
		return primeiroConvidado;
	}

	public void setPrimeiroConvidado(String primeiroConvidado) {
		this.primeiroConvidado = primeiroConvidado;
	}

	public String getSegundoConvidado() {
		return segundoConvidado;
	}

	public void setSegundoConvidado(String segundoConvidado) {
		this.segundoConvidado = segundoConvidado;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public byte[] getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(byte[] declaracao) {
		this.declaracao = declaracao;
	}

	public byte[] getAta() {
		return ata;
	}

	public void setAta(byte[] ata) {
		this.ata = ata;
	}

	public byte[] getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(byte[] avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}
	
}

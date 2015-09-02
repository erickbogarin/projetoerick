package com.sistema.imagem;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="imagem")
public class Imagem {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Lob
	@Column(name="acompanhamento_logo")
	private byte[] acompanhamentoLogo;
	
	@Lob
	@Column(name="ata_assinaturas")
	private byte[] ataAssinaturas;
	
	@Lob
	@Column(name="ata_logo")
	private byte[] ataLogo;
	
	@Lob
	@Column(name="ata_notas")
	private byte[] ataNotas;
	
	@Lob
	@Column(name="ata_rodape")
	private byte[] ataRodape;
	
	@Lob
	@Column(name="avaliacao_assinatura")
	private Blob avaliacaoAssinatura;
	
	@Lob
	@Column(name="avaliacao_escrito")
	private byte[] avaliacaoEscrito;

	@Lob
	@Column(name="avaliacao_defesa")
	private byte[] avaliacaoDefesa;
	
	@Lob
	@Column(name="avaliacao_logo")
	private byte[] avaliacaoLogo;
	
	@Lob
	@Column(name="cadastro_logo")
	private byte[] cadastroLogo;
	
	@Lob
	@Column(name="cadastro_rodape")
	private byte[] cadastroRodape;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getAcompanhamentoLogo() {
		return acompanhamentoLogo;
	}

	public void setAcompanhamentoLogo(byte[] acompanhamentoLogo) {
		this.acompanhamentoLogo = acompanhamentoLogo;
	}

	public byte[] getAtaAssinaturas() {
		return ataAssinaturas;
	}

	public void setAtaAssinaturas(byte[] ataAssinaturas) {
		this.ataAssinaturas = ataAssinaturas;
	}

	public byte[] getAtaLogo() {
		return ataLogo;
	}

	public void setAtaLogo(byte[] ataLogo) {
		this.ataLogo = ataLogo;
	}

	public byte[] getAtaNotas() {
		return ataNotas;
	}

	public void setAtaNotas(byte[] ataNotas) {
		this.ataNotas = ataNotas;
	}

	public byte[] getAtaRodape() {
		return ataRodape;
	}

	public void setAtaRodape(byte[] ataRodape) {
		this.ataRodape = ataRodape;
	}

	public byte[] getAvaliacaoEscrito() {
		return avaliacaoEscrito;
	}

	public void setAvaliacaoEscrito(byte[] avaliacaoEscrito) {
		this.avaliacaoEscrito = avaliacaoEscrito;
	}

	public byte[] getAvaliacaoDefesa() {
		return avaliacaoDefesa;
	}

	public void setAvaliacaoDefesa(byte[] avaliacaoDefesa) {
		this.avaliacaoDefesa = avaliacaoDefesa;
	}

	public byte[] getAvaliacaoLogo() {
		return avaliacaoLogo;
	}

	public void setAvaliacaoLogo(byte[] avaliacaoLogo) {
		this.avaliacaoLogo = avaliacaoLogo;
	}

	public byte[] getCadastroLogo() {
		return cadastroLogo;
	}

	public void setCadastroLogo(byte[] cadastroLogo) {
		this.cadastroLogo = cadastroLogo;
	}

	public byte[] getCadastroRodape() {
		return cadastroRodape;
	}

	public void setCadastroRodape(byte[] cadastroRodape) {
		this.cadastroRodape = cadastroRodape;
	}

	public Blob getAvaliacaoAssinatura() {
		return avaliacaoAssinatura;
	}

	public void setAvaliacaoAssinatura(Blob avaliacaoAssinatura) {
		this.avaliacaoAssinatura = avaliacaoAssinatura;
	}

}

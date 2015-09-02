package com.sistema.proposta;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.disciplina.Disciplina;
import com.sistema.periodo.Periodo;
import com.sistema.status.PropostaStatus;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.orientador.Orientador;

@Entity
@Table (name = "proposta")
public class Proposta {

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Lob
	private byte[] file;
	
	@Lob
	private byte[] correcao; 
	
	@Lob
	private byte[] tcc;
	
	private String tema;
	
	private String descricao;
	
	private String modalidade;
	
	@Column(name = "resultados_esperados")
	
	private String resultadosEsperados;
	
	@Column(name = "area_concentracao")
	private String areaConcentracao;
	
	private Boolean atual;
	
	@Column(name = "apresentacao_status")
	private String apresentacaoStatus;
	
	@Enumerated(EnumType.STRING)
	private PropostaStatus status;
	
	@Column(name = "status_biblioteca")
	private String statusBiblioteca;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "orientador_id")
	private Orientador orientador;
	
	@ManyToOne
	@JoinColumn(name = "coorientador_id")
	private Orientador coOrientador;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "periodo_id")
	private Periodo periodo;
	
	public Proposta(Aluno aluno, PropostaStatus status, Boolean atual) {
		this.aluno = aluno;
		this.status = status;
		this.atual = atual;
	}
	
	public Proposta() {
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public PropostaStatus getStatus() {
		return status;
	}

	public void setStatus(PropostaStatus status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResultadosEsperados() {
		return resultadosEsperados;
	}

	public void setResultadosEsperados(String resultadosEsperados) {
		this.resultadosEsperados = resultadosEsperados;
	}

	public String getAreaConcentracao() {
		return areaConcentracao;
	}

	public void setAreaConcentracao(String areaConcentracao) {
		this.areaConcentracao = areaConcentracao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] bFile) {
		this.file = bFile;
	}

	public byte[] getCorrecao() {
		return correcao;
	}

	public void setCorrecao(byte[] correcao) {
		this.correcao = correcao;
	}

	public String getStatusBiblioteca() {
		return statusBiblioteca;
	}

	public void setStatusBiblioteca(String statusBiblioteca) {
		this.statusBiblioteca = statusBiblioteca;
	}

	public byte[] getTcc() {
		return tcc;
	}

	public void setTcc(byte[] tcc) {
		this.tcc = tcc;
	}

	public Orientador getCoOrientador() {
		return coOrientador;
	}

	public void setCoOrientador(Orientador coOrientador) {
		this.coOrientador = coOrientador;
	}

	public String getApresentacaoStatus() {
		return apresentacaoStatus;
	}

	public void setApresentacaoStatus(String apresentacaoStatus) {
		this.apresentacaoStatus = apresentacaoStatus;
	}

	public Boolean getAtual() {
		return atual;
	}

	public void setAtual(Boolean atual) {
		this.atual = atual;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
}
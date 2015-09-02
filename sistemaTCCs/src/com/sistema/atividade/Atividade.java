package com.sistema.atividade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;
import com.sistema.periodo.Periodo;

@Entity
@Table (name = "atividade")
public class Atividade {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Date data;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "periodo_id", nullable = false)
	private Periodo periodo;
	
	@ManyToOne
	private Coordenacao coordenacao;
	
	@ManyToOne
	private Disciplina disciplina;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}

package com.sistema.usuario.aluno;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.curso.Curso;
import com.sistema.disciplina.Disciplina;
import com.sistema.periodo.Periodo;
import com.sistema.proposta.Proposta;
import com.sistema.role.Role;
import com.sistema.usuario.Usuario;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario {

	@ManyToOne
	private Coordenacao coordenacao;
	
	@ManyToOne
	private Curso curso;

	@OneToMany(mappedBy = "aluno", cascade = (CascadeType.PERSIST), fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Proposta> propostas = new ArrayList<Proposta>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "aluno_disciplina", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "aluno_periodo", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "periodo_id"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Periodo> periodos = new ArrayList<Periodo>();

	private String areaConcetracao;

	public Aluno() {
		this.setRole(Role.ALUNO);
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}
	
	public void adicionaProposta(Proposta proposta) {
		this.propostas.add(proposta);
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void adicionaPeriodo(Periodo periodo) {
		this.periodos.add(periodo);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public void adicionaDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}

	public String getAreaConcetracao() {
		return areaConcetracao;
	}

	public void setAreaConcetracao(String areaConcetracao) {
		this.areaConcetracao = areaConcetracao;
	}

}

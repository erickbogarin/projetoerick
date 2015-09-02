package com.sistema.curso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;
import com.sistema.usuario.aluno.Aluno;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	@OneToMany(mappedBy = "curso", cascade=(CascadeType.PERSIST), fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@ManyToOne
	@JoinColumn(name = "coordenacao")
	private Coordenacao coordenacao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "curso_disciplina", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public Curso(String nome, Coordenacao coordenacao) {
		this.nome = nome;
		this.coordenacao = coordenacao;
	}
	
	public Curso() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Coordenacao getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(Coordenacao coordenacao) {
		this.coordenacao = coordenacao;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void adicionaCurso(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public void adicionaDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}

}

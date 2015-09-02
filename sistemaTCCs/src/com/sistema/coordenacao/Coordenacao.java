package com.sistema.coordenacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sistema.curso.Curso;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.orientador.Orientador;

@Entity
public class Coordenacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@OneToMany(mappedBy = "coordenacao", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	List<Coordenador> coordenadores = new ArrayList<Coordenador>();

	@OneToMany(mappedBy = "coordenacao", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	List<Aluno> alunos = new ArrayList<Aluno>();

	@OneToMany(mappedBy = "coordenacao", cascade = (CascadeType.PERSIST), fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	List<Curso> cursos = new ArrayList<Curso>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "coordenacao_orientador", joinColumns = @JoinColumn(name = "coordenacao_id"), inverseJoinColumns = @JoinColumn(name = "orientador_id"))
	@Fetch(FetchMode.SUBSELECT)
	List<Orientador> orientadores = new ArrayList<Orientador>();

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

	public List<Coordenador> getCoordenadores() {
		return coordenadores;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public List<Orientador> getOrientadores() {
		return orientadores;
	}

	public void adicionaCoordenador(Coordenador coordenador) {
		this.coordenadores.add(coordenador);
	}

	public void adicionaCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void removerCurso(Curso curso) {
		this.cursos.remove(curso);
	}

	public void adicionaOrientador(Orientador orientador) {
		this.orientadores.add(orientador);
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
}

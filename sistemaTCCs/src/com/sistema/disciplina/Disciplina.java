package com.sistema.disciplina;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sistema.usuario.aluno.Aluno;

@Entity
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	
	@ManyToMany(mappedBy = "disciplinas", cascade = CascadeType.PERSIST)
	@Fetch(FetchMode.SUBSELECT)
	private List<Aluno> alunos;

	public Disciplina() {
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

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

}

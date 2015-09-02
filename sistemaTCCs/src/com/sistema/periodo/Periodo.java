package com.sistema.periodo;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sistema.usuario.aluno.Aluno;

@Entity
public class Periodo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="periodo_id", unique=true, nullable=false)
	private Integer id;
	
	private String nome;
	
	@OneToMany(mappedBy="periodos", cascade=CascadeType.PERSIST)
	private List<Aluno> alunos;
	
	public List<Aluno> getAlunos() {
		return Collections.unmodifiableList(this.alunos);
	}
	
	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);
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
	
}

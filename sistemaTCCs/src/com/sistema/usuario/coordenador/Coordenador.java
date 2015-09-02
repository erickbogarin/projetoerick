package com.sistema.usuario.coordenador;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.disciplina.Disciplina;
import com.sistema.role.Role;
import com.sistema.usuario.Usuario;


@Entity
@Table(name="coordenador")
@PrimaryKeyJoinColumn(name = "id")
public class Coordenador extends Usuario {
	
	@ManyToOne
	private Disciplina disciplina  = new Disciplina();
	
	@ManyToOne
	@JoinColumn(name = "coordenacao")
	private Coordenacao coordenacao;
	
	public Coordenador() {
		this.setRole(Role.COORDENADOR);
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

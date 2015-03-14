package com.sistema.usuario.orientador;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.sistema.role.Role;
import com.sistema.status.Status;
import com.sistema.usuario.Usuario;

@Entity
@Table(name = "orientador")
@PrimaryKeyJoinColumn(name = "id")
public class Orientador extends Usuario {
	
	private Integer matricula;

	private String linhaPesquisa;
	
	protected String titulo;
		
	public Orientador() {
		this.setRole(Role.ORIENTADOR);
	}
	
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public void setLinhaPesquisa(String linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}

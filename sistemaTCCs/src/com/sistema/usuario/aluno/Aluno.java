package com.sistema.usuario.aluno;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.sistema.role.Role;
import com.sistema.usuario.Usuario;

//primeira versao


@Entity
@Table(name="aluno")
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario{
	
	private Integer matricula;
	
	private String areaConcetracao;
	
	public Aluno() {
		this.setRole(Role.ALUNO);
	}
	
	public String getAreaConcetracao() {
		return areaConcetracao;
	}

	public void setAreaConcetracao(String areaConcetracao) {
		this.areaConcetracao = areaConcetracao;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	
}

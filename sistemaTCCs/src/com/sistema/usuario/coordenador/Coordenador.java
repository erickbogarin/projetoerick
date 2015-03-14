package com.sistema.usuario.coordenador;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.sistema.role.Role;
import com.sistema.usuario.Usuario;


@Entity
@Table(name="coordenador")
@PrimaryKeyJoinColumn(name = "id")
public class Coordenador extends Usuario {
	
	public Coordenador() {
		this.setRole(Role.COORDENADOR);
	}
}

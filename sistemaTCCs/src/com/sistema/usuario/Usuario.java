package com.sistema.usuario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.sistema.role.Role;
import com.sistema.status.UsuarioStatus;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer matricula;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String celular;
	private String comercial;
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private UsuarioStatus status;
	@Enumerated(EnumType.STRING)
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioStatus getStatus() {
		return status;
	}

	public void setStatus(UsuarioStatus status) {
		this.status = status;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAdministrador() {
		return Role.ADMINISTRADOR.equals(role);
	}

	public boolean isCoordenador() {
		return Role.COORDENADOR.equals(role);
	}

	public boolean isAluno() {
		return Role.ALUNO.equals(role);
	}

	public boolean isOrientador() {
		return Role.Orientador.equals(role);
	}

	public boolean isBibliotecario() {
		return Role.BIBLIOTECARIO.equals(role);
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario user = (Usuario) obj;
			return user.getId() == id;
		}

		return false;
	}

}

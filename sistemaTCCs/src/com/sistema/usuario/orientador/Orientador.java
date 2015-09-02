package com.sistema.usuario.orientador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.proposta.Proposta;
import com.sistema.role.Role;
import com.sistema.usuario.Usuario;

@Entity
@Table(name = "orientador")
@PrimaryKeyJoinColumn(name = "id")
public class Orientador extends Usuario {
	
	private String linhaPesquisa;
	
	protected String titulo;
	
	@OneToMany(mappedBy = "orientador", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Proposta> propostas = new ArrayList<Proposta>();
	
	@OneToMany(mappedBy = "orientadores", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Coordenacao> coordenacoes = new ArrayList<Coordenacao>();
	
	public Orientador() {
		this.setRole(Role.Orientador);
	}
	
	public List<Proposta> getPropostas() {
		return propostas;
	}
	
	public List<Coordenacao> getCoodenacoes() {
		return coordenacoes;
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

	public void adicionaProposta(Proposta proposta) {
		this.propostas.add(proposta);
	}
	
	public void adicionaCoordenacao(Coordenacao coordenacao) {
		this.coordenacoes.add(coordenacao);
	}
}

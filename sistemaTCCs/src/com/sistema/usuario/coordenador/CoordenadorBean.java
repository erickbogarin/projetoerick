package com.sistema.usuario.coordenador;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.sistema.external.PasswordGenerator;

import com.sistema.status.Status;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.UsuarioBean;
import com.sistema.usuario.UsuarioRN;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.aluno.AlunoRN;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorRN;

@ManagedBean (name = "coordenadorBean")
@RequestScoped
public class CoordenadorBean {
	private Orientador orientadorSelecionado = new Orientador();
	private Coordenador coordenadorSelecionado = new Coordenador();
	private Aluno alunoSelecionado = new Aluno();
	private UsuarioBean usuarioBean = new UsuarioBean();
	
	public List<Usuario> validateOrientador() {
		UsuarioRN usuarioRN = new UsuarioRN();
		
		return (List<Usuario>) usuarioRN
				.validateUsername(orientadorSelecionado.getEmail());
	}
	
	public List<Usuario> validateAluno() {
		UsuarioRN usuarioRN = new UsuarioRN();
		
		return (List<Usuario>) usuarioRN
				.validateUsername(alunoSelecionado.getEmail());
	}
	
	public void salvarOrientador() {
		if (validateOrientador().size() == 0) {
			OrientadorRN orientadorRN = new OrientadorRN();		
			orientadorSelecionado.setDataCadastro(new Date());
			orientadorSelecionado.setSenha(PasswordGenerator.geraSenha(6));
			orientadorSelecionado.setStatus(Status.CADASTRADO);
			//Email.enviarEmail(orientadorSelecionado);

			orientadorRN.salvar(orientadorSelecionado);
			orientadorSelecionado = new Orientador();
			
			FacesMessage faces = new FacesMessage(
					"Cliente cadastrado com sucesso!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
			
		} else {
			FacesMessage faces = new FacesMessage("E-mail já cadastrado!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
	}
	
	public void salvarAluno() {
		
		if (validateAluno().size() == 0) {
			AlunoRN alunoRN = new AlunoRN();		
			alunoSelecionado.setDataCadastro(new Date());
			alunoSelecionado.setSenha(PasswordGenerator.geraSenha(6));
			alunoSelecionado.setStatus(Status.CADASTRADO);
			//Email.enviarEmail(orientadorSelecionado);
			
			alunoRN.salvar(alunoSelecionado);
			alunoSelecionado = new Aluno();
			
			FacesMessage faces = new FacesMessage(
					"Aluno cadastrado com sucesso!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		} else {
			FacesMessage faces = new FacesMessage("E-mail já cadastrado!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
		
	}
	
	public String alterar() {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		this.coordenadorSelecionado.setDataCadastro(new Date());
		coordenadorRN.alterar(this.coordenadorSelecionado);
		FacesMessage faces = new FacesMessage("Usuario alterado com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "../listagem/listarCoordenador.xhtml";
	}
	
	public void preparaConsulta() {
		usuarioBean.getConsultarCoordenador();
	}

	public String prepararConta(Usuario usuario) {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		this.coordenadorSelecionado = coordenadorRN.findById(usuario.getId());

		return "conta/consultarDados.xhtml";
	}
	
	public String solicitarEditarConta(Usuario coordenador) {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		this.coordenadorSelecionado = coordenadorRN.findById(coordenador.getId());
		
		return "alterarConta.xhtml";
	}
	
	public String prepararEdicao(Coordenador coordenador) {
		this.coordenadorSelecionado =  coordenador;
		return "../edicao/alterarCoordenador.xhtml";
	}
	
	public Orientador getOrientadorSelecionado() {
		return orientadorSelecionado;
	}

	public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
		this.orientadorSelecionado = orientadorSelecionado;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Coordenador getCoordenadorSelecionado() {
		return coordenadorSelecionado;
	}

	public void setCoordenadorSelecionado(Coordenador coordenadorSelecionado) {
		this.coordenadorSelecionado = coordenadorSelecionado;
	}
	
}

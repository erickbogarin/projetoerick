package com.sistema.usuario;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.aluno.AlunoRN;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.coordenador.CoordenadorRN;
import com.sistema.usuario.orientador.Orientador;
import com.sistema.usuario.orientador.OrientadorRN;



// fiz a funcao que roda a sessao do usuario 

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

	private List<Orientador> listaOrientador = null;
	private List<Aluno> listaAluno = null;
	private List<Coordenador> listaCoordenador = null;
	private Usuario usuarioSelecionado = new Usuario();

	public List<Orientador> getListarOrientador() {
		OrientadorRN orientadorRN = new OrientadorRN();

		if (listaOrientador == null) {
			listaOrientador = orientadorRN.listar();
		}

		return listaOrientador;
	}

	public List<Aluno> getListarAluno() {
		AlunoRN alunoRN = new AlunoRN();

		if (listaAluno == null) {
			listaAluno = alunoRN.listar();
		}

		return listaAluno;
	}
	
	public List<Coordenador> getListarCoordenador() {
		CoordenadorRN coordenadorRN = new CoordenadorRN();

		if (listaCoordenador == null) {
			listaCoordenador = coordenadorRN.listar();
		}

		return listaCoordenador;
	}
	
	public String prepararEdicaoConta(Usuario usuario) {
		this.usuarioSelecionado = usuario;
		return "alterarConta.xhtml";
	}
	

	public List<Orientador> getConsultarOrientador() {
		OrientadorRN orientadorRN = new OrientadorRN();
		if (listaOrientador == null) {
			listaOrientador = orientadorRN.findUser(usuarioSelecionado
					.getNome());
		}

		return listaOrientador;
	}

	public List<Aluno> getConsultarAluno() {
		AlunoRN alunoRN = new AlunoRN();
		if (listaAluno == null) {
			listaAluno = alunoRN.findUser(usuarioSelecionado.getNome());
		}

		return listaAluno;
	}
	
	public List<Coordenador> getConsultarCoordenador() {
		CoordenadorRN coordenadorRN = new CoordenadorRN();
		if (listaCoordenador == null) {
			listaCoordenador = coordenadorRN.findUser(usuarioSelecionado.getNome());
		}
		
		return listaCoordenador;
	}
	
	public String alterar(Usuario usuario) {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioSelecionado.setDataCadastro(new Date());
		System.out.println(usuario.getSenha());
		usuarioSelecionado.setSenha(usuario.getSenha());
		usuarioSelecionado.setRole(usuario.getRole());
		usuarioRN.alterar(this.usuarioSelecionado);
		FacesMessage faces = new FacesMessage("Usuario alterado com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		return "consultarDados.xhtml";
	}
	
	public void setListaOrientador(List<Orientador> listaOrientador) {
		this.listaOrientador = listaOrientador;
	}

	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}
	
	public void setListaCoordenador(List<Coordenador> listaCoordenador) {
		this.listaCoordenador = listaCoordenador;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	
}

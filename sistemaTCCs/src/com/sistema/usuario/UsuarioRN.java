package com.sistema.usuario;

import java.util.List;

import com.sistema.util.DAOFactory;

public class UsuarioRN {

	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criaUsuarioDAO();
	}

	public Usuario isUsuarioReady(String email, String senha) {
		// TODO Auto-generated method stub
		List<Usuario> retorno = this.usuarioDAO.findByEmailSenha(email, senha);
		
		if(retorno.size() == 1) {
			Usuario userFound = (Usuario) retorno.get(0);
			return userFound;
		}
		
		return null;
	}
	
	public List<Usuario> validateUsername(String email) {
		return (List<Usuario>) this.usuarioDAO.findByUsername(email);
	}

	public void excluir(Usuario usuario) {
		this.usuarioDAO.excluir(usuario);
	}
	
	public void alterar(Usuario usuario) {
		this.usuarioDAO.alterarDados(usuario);
	}

	public void salvar(Usuario usuario) {
		this.usuarioDAO.salvar(usuario);
	}

	public List<Usuario> bibliotecarios() {
		return this.usuarioDAO.bibliotecarios();
	}

	public Usuario findByEmail(String email) {
		return this.usuarioDAO.findByEmail(email);
	}

	public boolean validadePassword(String convertStringToMd5, Usuario usuario) {
		return this.usuarioDAO.validateSenha(convertStringToMd5, usuario);
	}

	public Usuario validateEmailMatricula(String email, Integer matricula) {
		return this.usuarioDAO.validadeEmailMatricula(email, matricula);
	}
	
}

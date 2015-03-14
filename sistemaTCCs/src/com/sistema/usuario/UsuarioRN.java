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
	
}

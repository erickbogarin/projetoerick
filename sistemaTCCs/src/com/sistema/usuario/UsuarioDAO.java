package com.sistema.usuario;

import java.util.List;

public interface UsuarioDAO {
		
	public void excluir(Usuario usuario);
	
	public void alterarDados(Usuario usuario);
	
	public List<Usuario> findByUsername(String email);
	
	public List<Usuario> findByEmailSenha(String email, String senha);

}

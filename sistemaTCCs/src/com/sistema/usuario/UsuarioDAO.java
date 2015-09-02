package com.sistema.usuario;

import java.util.List;

public interface UsuarioDAO {
		
	public void excluir(Usuario usuario);
	
	public void alterarDados(Usuario usuario);
	
	public List<Usuario> findByUsername(String email);
	
	public List<Usuario> findByEmailSenha(String email, String senha);

	public void salvar(Usuario usuario);

	public List<Usuario> bibliotecarios();

	public Usuario findByEmail(String email);

	public boolean validateSenha(String convertStringToMd5, Usuario usuario);

	public Usuario validadeEmailMatricula(String email, Integer matricula);

}

package com.sistema.usuario;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByEmailSenha(String email, String senha) {
		Query select = sessao.createQuery("from Usuario p where p.email =:email and p.senha =:senha");
		select.setString("email", email);
		select.setString("senha", senha);
		List<Usuario> lista = select.list();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByUsername(String email) {
		// TODO Auto-generated method stub
		Query select = sessao.createQuery("from Usuario p where p.email =:email");
		select.setString("email", email);
		
		List<Usuario> lista = (List<Usuario>) select.list();
	
		return lista;
	}

	@Override
	public void excluir(Usuario usuario) {
		this.sessao.delete(usuario);
	}

	@Override
	public void alterarDados(Usuario usuario) {
		this.sessao.update(usuario);
	}
	
}

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
		this.sessao.merge(usuario);
	}

	@Override
	public void salvar(Usuario usuario) {
		this.sessao.saveOrUpdate(usuario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> bibliotecarios() {
		String role = "BIBLIOTECARIO";
		Query select = sessao.createQuery("from Usuario u where u.role =:role");
		select.setString("role", role);
		
		return ((List<Usuario>) select.list());
	}

	@Override
	public Usuario findByEmail(String email) {
		Query query = this.sessao.createQuery("from Usuario u where u.email = :email");
		query.setParameter("email", email);
		
		return (Usuario) query.uniqueResult();
	}

	@Override
	public boolean validateSenha(String senha, Usuario usuario) {
		
		Query select  = this.sessao.createQuery("SELECT u FROM Usuario u WHERE u = :usuario and u.senha = :senha");
		select.setParameter("senha", senha);
		select.setParameter("usuario", usuario);
		
		Usuario user = (Usuario) select.uniqueResult();
		
		if(user == null)
			return false;
		
		return true;
	}

	@Override
	public Usuario validadeEmailMatricula(String email, Integer matricula) {
		Query select  = this.sessao.createQuery("FROM Aluno a WHERE a.email = :email and a.matricula =:matricula");
		select.setParameter("email", email);
		select.setParameter("matricula", matricula);
		
		return (Usuario) select.uniqueResult();
	}
	
}

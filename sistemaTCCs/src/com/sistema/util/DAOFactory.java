package com.sistema.util;

import com.sistema.teste.HibernateUtil;
import com.sistema.usuario.UsuarioDAO;
import com.sistema.usuario.UsuarioDAOHibernate;
import com.sistema.usuario.aluno.AlunoDAO;
import com.sistema.usuario.aluno.AlunoDAOHibernate;
import com.sistema.usuario.coordenador.CoordenadoDAOHibernate;
import com.sistema.usuario.coordenador.CoordenadorDAO;
import com.sistema.usuario.orientador.OrientadorDAO;
import com.sistema.usuario.orientador.OrientadorDAOHibernate;

public class DAOFactory {

	public static OrientadorDAO criaProfessorDAO() {
		OrientadorDAOHibernate professorDAOHibernate = new OrientadorDAOHibernate();
		professorDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return professorDAOHibernate;
	}
	
	public static UsuarioDAO criaUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAOHibernate = new UsuarioDAOHibernate();
		usuarioDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return usuarioDAOHibernate;
	}

	public static AlunoDAO criaAlunoDAO() {
		AlunoDAOHibernate alunoDAOHibernate = new AlunoDAOHibernate();
		alunoDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return alunoDAOHibernate;
	}
	
	public static CoordenadorDAO criaCoordenadorDAO() {
		CoordenadoDAOHibernate coordenadorDAOHibernate = new CoordenadoDAOHibernate();
		coordenadorDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return coordenadorDAOHibernate;
	}
}

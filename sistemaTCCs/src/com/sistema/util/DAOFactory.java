package com.sistema.util;

import com.sistema.atividade.AtividadeDAO;
import com.sistema.atividade.AtividadeDAOHibernate;
import com.sistema.banca.BancaDAO;
import com.sistema.banca.BancaDAOHibernate;
import com.sistema.coordenacao.CoordenacaoDAO;
import com.sistema.coordenacao.CoordenacaoDAOHibernate;
import com.sistema.curso.CursoDAO;
import com.sistema.curso.CursoDAOHibernate;
import com.sistema.disciplina.DisciplinaDAO;
import com.sistema.disciplina.DisciplinaDAOHibernate;
import com.sistema.imagem.ImagemDAO;
import com.sistema.imagem.ImagemDAOHibernate;
import com.sistema.manual.ManualDAO;
import com.sistema.manual.ManualDAOHibernate;
import com.sistema.mensagem.MensagemDAO;
import com.sistema.mensagem.MensagemDAOHibernate;
import com.sistema.orientacao.OrientacaoDAO;
import com.sistema.orientacao.OrientacaoDAOHibernate;
import com.sistema.periodo.PeriodoDAO;
import com.sistema.periodo.PeriodoDAOHibernate;
import com.sistema.proposta.PropostaDAO;
import com.sistema.proposta.PropostaDAOHibernate;
import com.sistema.quadro.QuadroDAO;
import com.sistema.quadro.QuadroDAOHibernate;
import com.sistema.reuniao.ReuniaoDAO;
import com.sistema.reuniao.ReuniaoDAOHibernate;
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

	
	public static CursoDAO criaCursoDAO() {
		CursoDAOHibernate  cursoDAOHibernate = new CursoDAOHibernate();
		cursoDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return cursoDAOHibernate;
	}
	
	public static DisciplinaDAO criaDisciplinaDAO() {
		DisciplinaDAOHibernate disciplinaDAOHibernate = new DisciplinaDAOHibernate();
		disciplinaDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return disciplinaDAOHibernate;
	}
	
	public static CoordenacaoDAO criaCoordenacaoDAO() {
		CoordenacaoDAOHibernate coordenacaoDAOHibernate = new CoordenacaoDAOHibernate();
		coordenacaoDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return coordenacaoDAOHibernate;
	}
	
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
	
	public static PropostaDAO criaPropostaDAO() {
		PropostaDAOHibernate propostaDAOHibernate = new PropostaDAOHibernate();
		propostaDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return propostaDAOHibernate;
	}
	
	public static PeriodoDAO criaCronogramaDAO() {
		PeriodoDAOHibernate cronogramaDAOHibernate = new PeriodoDAOHibernate();
		cronogramaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		return cronogramaDAOHibernate;
	}
	
	public static AtividadeDAO criaAtividadeDAO() {
		AtividadeDAOHibernate atividadeDAOHibernate = new AtividadeDAOHibernate();
		atividadeDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return atividadeDAOHibernate;
	}
	
	public static MensagemDAO criaMensagemDAO() {
		MensagemDAOHibernate mensagemDAOHibernate = new MensagemDAOHibernate();
		mensagemDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return mensagemDAOHibernate;
	}
	
	public static ReuniaoDAO criaReuniaoDAO() {
		ReuniaoDAOHibernate reuniaoDAOHibernate = new ReuniaoDAOHibernate();
		reuniaoDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return reuniaoDAOHibernate;
	}
	
	public static QuadroDAO criaQuadroDAO() {
		QuadroDAOHibernate quadroDAOHibernate = new QuadroDAOHibernate();
		quadroDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		return quadroDAOHibernate;
	}
	
	public static BancaDAO criaBancoDAO() {
		BancaDAOHibernate bancaDAOHibernate = new BancaDAOHibernate();
		bancaDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		
		return bancaDAOHibernate;
	}
	
	public static OrientacaoDAO criaOrientacaoDAO() {
		OrientacaoDAOHibernate orientacaoDAOHibernate = new OrientacaoDAOHibernate();
		orientacaoDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		
		return orientacaoDAOHibernate;
	}
	
	public static ManualDAO criaManualDAO() {
		ManualDAOHibernate manualDAOHibernate = new ManualDAOHibernate();
		manualDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		
		return manualDAOHibernate;
	}
	
	public static ImagemDAO criaImagemDAO() {
		ImagemDAOHibernate imagemDAOHibernate = new ImagemDAOHibernate();
		imagemDAOHibernate.setSessao(HibernateUtil.getSession().getCurrentSession());
		
		return imagemDAOHibernate;
	}
	
}

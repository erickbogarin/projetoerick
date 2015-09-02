package com.sistema.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.curso.Curso;
import com.sistema.disciplina.Disciplina;
import com.sistema.periodo.Periodo;
import com.sistema.role.Role;
import com.sistema.usuario.Usuario;
import com.sistema.usuario.aluno.Aluno;
import com.sistema.usuario.coordenador.Coordenador;
import com.sistema.usuario.orientador.Orientador;

public class PopulaBanco {
	
	public static void main(String[] args) throws Exception {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("teste");
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		
		manager.getTransaction().begin();
		
		Disciplina tcc = criaDisciplinas("Trabalho de Conclusão de Curso");
		manager.persist(tcc);
				
		Disciplina itcc = criaDisciplinas("Introdução ao Trabalho de Conclusão de Curso");
		manager.persist(itcc);
		
		@SuppressWarnings("unchecked")
		List<Disciplina> disciplinas = (List<Disciplina>) manager.createQuery("from Disciplina d").getResultList();
		
		Coordenacao coordenacao = new Coordenacao();
		coordenacao.setNome("Sis / CPP");
		manager.persist(coordenacao);
		
		Curso sis = criaCursos("Sistemas de Informação", disciplinas);
		sis.setCoordenacao(coordenacao);
		manager.persist(sis);
		
		Curso ccp = criaCursos("Ciências da Computação", disciplinas);
		ccp.setCoordenacao(coordenacao);
		manager.persist(ccp);
		
		Curso engC = criaCursos("Engenharia da Computação", disciplinas);
		manager.persist(engC);	
																									      // senha = qwe123
		Usuario administrador = criaUsuario("Erick Bogarin", Role.ADMINISTRADOR, "admin@email.com", "200820e3227815ed1756a6b531e7e0d2", new Usuario()); 
		manager.persist(administrador);
		
		Coordenador coordenador = (Coordenador) criaUsuario("Adriana Hoffgen", Role.COORDENADOR, "coordenador@email.com", "200820e3227815ed1756a6b531e7e0d2", new Coordenador());
		coordenador.setCoordenacao(coordenacao);
		coordenador.setDisciplina(tcc);
		manager.persist(coordenador);
		
		Orientador orientador = (Orientador) criaUsuario("Roberto Nogueira", Role.Orientador, "roberto.nogueira@email.com", "200820e3227815ed1756a6b531e7e0d2", new Orientador());
		orientador.adicionaCoordenacao(coordenacao);
		manager.persist(orientador);

		coordenacao.adicionaOrientador(orientador);
		manager.merge(coordenacao);

		orientador = new Orientador(); 
		orientador = (Orientador) criaUsuario("Victor Pacheco", Role.Orientador, "victor.pacheco@email.com", "200820e3227815ed1756a6b531e7e0d2", new Orientador());
		
		orientador.adicionaCoordenacao(coordenacao);
		manager.persist(orientador);

		coordenacao.adicionaOrientador(orientador);
		manager.merge(coordenacao);

		
		orientador = new Orientador(); 
		orientador = (Orientador) criaUsuario("Marcelo Almeida", Role.Orientador, "marcelo.almeida@email.com", "200820e3227815ed1756a6b531e7e0d2", new Orientador());
				
		orientador.adicionaCoordenacao(coordenacao);
		manager.persist(orientador);
		
		coordenacao.adicionaOrientador(orientador);
		manager.merge(coordenacao);
		
		Periodo periodo = new Periodo();
		periodo.setNome("2015 / 1");
		manager.persist(periodo);
		
		Aluno aluno = new Aluno();
		aluno.setNome("Paulo Sérgio");
		aluno.setCoordenacao(coordenacao);
		aluno.setCurso(sis);
		aluno.adicionaDisciplina(tcc);
		aluno.adicionaPeriodo(periodo);
		manager.persist(aluno);
		
		manager.getTransaction().commit();
		
		manager.close();
	}

	private static Usuario criaUsuario(String nome, Role role, String email, String senha, Usuario usuario) {
		Usuario administrador = usuario;

		usuario.setNome(nome);
		usuario.setRole(role);
		usuario.setEmail(email);
		usuario.setSenha(senha); 
		
		return administrador;
	}
		
	public static Disciplina criaDisciplinas(String nome) {	
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(nome);
		
		return disciplina;
	}
	
	public static Curso criaCursos(String nome, List<Disciplina> disciplinas) throws Exception {
		
		Curso curso = new Curso();
		curso.setNome(nome);
			
		for(Disciplina disciplina : disciplinas) {
			curso.adicionaDisciplina(disciplina);
		}
		
		return curso;
	}
	
}

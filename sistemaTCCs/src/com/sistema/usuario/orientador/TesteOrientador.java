package com.sistema.usuario.orientador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.sistema.proposta.Proposta;

public class TesteOrientador {
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("teste");
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		
		Query query = manager.createQuery("select o from Orientador o join fetch o.propostas where o.id = :pId");
        query.setParameter("pId", 3);
		
		Orientador orientador =  (Orientador) query.getSingleResult();
        
		System.out.println("Quantidade de orientados: " + orientador.getPropostas().size());
		
        for(Proposta p : orientador.getPropostas()) {
        	System.out.println("Nome do aluno: " + p.getAluno().getNome());
        }
		
	}
	
}

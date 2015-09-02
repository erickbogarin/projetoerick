package com.sistema.disciplina;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DisciplinaInsertTest {
	

	public static void main(String[] args) throws Exception {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("teste");
		
		EntityManager manager = entityManagerFactory.createEntityManager();
		
		

	}
	
}	

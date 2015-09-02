package com.sistema.disciplina;


public class DisciplinaTest {
	
	public static void main(String[] args) throws Exception {
		
		Disciplina disciplina = new DisciplinaRN().buscaPorId(3);
		
		System.out.println("Nome da disciplina: " + disciplina.getNome());
	}
	
}

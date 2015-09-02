package com.sistema.curso;

import java.util.List;

public interface CursoDAO {
	
	public void salvar(Curso curso);
	
	public void excluir(Curso curso);
	
	public void alterar(Curso curso);
	
	public List<Curso> cursos();

	public Curso findById(Integer cursoId);

	public void desassociar(Curso curso);
	
}

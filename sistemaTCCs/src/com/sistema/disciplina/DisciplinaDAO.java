package com.sistema.disciplina;

import java.util.List;


public interface DisciplinaDAO {
	
	public void salvar(Disciplina disciplina);
	
	public void excluir(Disciplina disciplina);
	
	public void alterar(Disciplina disciplina);
	
	public Disciplina consultarDisciplina(Integer disciplinaId);
	
	public List<Disciplina> disciplinas();
	
}

package com.sistema.usuario.aluno;

import java.util.List;

import com.sistema.usuario.aluno.Aluno;

public interface AlunoDAO {
	
	public void salvar(Aluno aluno);

	public List<Aluno> listar();

	public void excluir(Aluno aluno);

	public List<Aluno> findByName(String string);

	public void alterarDados(Aluno aluno);
}

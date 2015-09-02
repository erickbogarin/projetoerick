package com.sistema.usuario.aluno;

import java.util.List;

public interface AlunoDAO {
	
	public void salvar(Aluno aluno);

	public List<Aluno> listar(Integer periodo, Integer disciplinaId, Integer coordenacaoId);

	public void excluir(Aluno aluno);

	public List<Aluno> findByName(String nome);
	
	public Aluno findById(Integer id);

	public void alterarDados(Aluno aluno);
	
}

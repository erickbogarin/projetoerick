package com.sistema.usuario.aluno;

import java.util.List;

import com.sistema.util.DAOFactory;

public class AlunoRN {
	
	private AlunoDAO alunoDAO;
	
	public AlunoRN(){
		this.alunoDAO = DAOFactory.criaAlunoDAO();
	}

	public List<Aluno> listar() {
		return this.alunoDAO.listar();
	}

	public List<Aluno> findUser(String nome) {
		return this.alunoDAO.findByName(nome);
	}

	public void salvar(Aluno alunoSelecionado) {
		this.alunoDAO.salvar(alunoSelecionado);		
	}
	
	public void alterar(Aluno alunoSelecionado) {
		this.alunoDAO.alterarDados(alunoSelecionado);
	}

	public void excluir(Aluno alunoSelecionado) {
		this.alunoDAO.excluir(alunoSelecionado);
	}
}

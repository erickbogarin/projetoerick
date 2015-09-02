package com.sistema.usuario.coordenador;

import java.util.List;

public interface CoordenadorDAO {
	
	public List<Coordenador> listar();

	public List<Coordenador> findUserByName(String nome);

	public Coordenador findById(Integer id);

	public void alterar(Coordenador coordenador);

	public void excluir(Coordenador coordenador);

	public List<Coordenador> coordenadores(String coordenacao, String disciplina);

	public List<Coordenador> coodenacao();

	public void desassociar(Coordenador coordenador);
}

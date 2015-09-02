package com.sistema.usuario.orientador;

import java.util.List;

import com.sistema.coordenacao.Coordenacao;
import com.sistema.proposta.Proposta;

public interface OrientadorDAO {

	public void salvar(Orientador professor);

	public List<Orientador> listar();

	public void excluir(Orientador professor);

	public void alterar(Orientador professor);

	public List<Orientador> findUserByName(String nome);

	public Orientador findById(Integer id);
	
	public List<Proposta> orientados(Integer id);

	public List<Orientador> orientadoresDaCoordenacao(Coordenacao coordenacao);
	
}

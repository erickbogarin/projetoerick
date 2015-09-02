package com.sistema.coordenacao;

import java.util.List;

public interface CoordenacaoDAO {
	
	public void salvar(Coordenacao coordenacao);
	
	public void exclui(Coordenacao coordenacao);
	
	public void alterar(Coordenacao coordenacao);
	
	public List<Coordenacao> coordenacoes();
	
	public Coordenacao pesquisaPorId(Integer disciplinaId);
	
}

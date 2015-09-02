package com.sistema.mensagem;

import java.util.List;

public interface MensagemDAO {
	
	public void salvar(Mensagem mensagem);
	
	public List<Mensagem> exibitMensagem(Integer id);

	public List<Mensagem> noticias();
	
}

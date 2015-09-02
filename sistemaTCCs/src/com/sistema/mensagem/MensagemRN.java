package com.sistema.mensagem;

import java.util.List;

import com.sistema.util.DAOFactory;

public class MensagemRN {
	
	private MensagemDAO mensagemDAO;
	
	public MensagemRN() {
		this.mensagemDAO = DAOFactory.criaMensagemDAO();
	}

	public void salvar(Mensagem mensagem) {
		this.mensagemDAO.salvar(mensagem);
	}

	public List<Mensagem> listarMensagem(Integer id) {
		return this.mensagemDAO.exibitMensagem(id);
	}

	public List<Mensagem> noticias() {
		return this.mensagemDAO.noticias();
	}
	
}

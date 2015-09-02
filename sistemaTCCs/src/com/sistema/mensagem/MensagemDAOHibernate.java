package com.sistema.mensagem;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class MensagemDAOHibernate implements MensagemDAO {
	
	private Session sessao;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public void salvar(Mensagem mensagem) {
		this.sessao.save(mensagem);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensagem> exibitMensagem(Integer id) {
		Query select = sessao.createQuery("from Mensagem m where m.usuario =:id");
		select.setInteger("id", id);

		return (List<Mensagem>)select.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensagem> noticias() {
		String tipo = "noticias";
		
		Query select = sessao.createQuery("from Mensagem m where m.tipo =:tipo order by m.data DESC");
		select.setString("tipo", tipo);

		return (List<Mensagem>) select.list();
	}

}

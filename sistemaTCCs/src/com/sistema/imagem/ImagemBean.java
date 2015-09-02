package com.sistema.imagem;


import java.sql.Blob;

import javax.faces.bean.RequestScoped;

@RequestScoped
public class ImagemBean {
	
	Imagem imagem = new Imagem();
	
	public byte[] getAndamentoLogo() {
		imagem.setAcompanhamentoLogo(new ImagemRN().acompanhamentoLogo()); 
		
		return imagem.getAcompanhamentoLogo();
	}
	
	public byte[] getAtaAssinaturas() {
		imagem.setAtaAssinaturas(new ImagemRN().ataAssinaturas());
		
		return imagem.getAtaAssinaturas();
	}
	
	public byte[] getAtaLogo() {
		imagem.setAtaLogo(new ImagemRN().ataLogo());
		
		return imagem.getAtaLogo();
	}
	
	public byte[] getAtaNotas() {
		imagem.setAtaLogo(new ImagemRN().ataNotas());
		
		return imagem.getAtaNotas();
	}
	
	public byte[] getAtaRodape() {
		imagem.setAtaLogo(new ImagemRN().ataRodape());
		
		return imagem.getAtaRodape();
	}
	
	public byte[] getAvaliacaoDefesa() {
		imagem.setAvaliacaoDefesa(new ImagemRN().avaliacaoDefesa());
		
		return imagem.getAvaliacaoDefesa();
	}
	
	public Blob getAvaliacaoAssinatura() {
		imagem.setAvaliacaoDefesa(new ImagemRN().avaliacaoAssinatura());
		
		return imagem.getAvaliacaoAssinatura();
	}
	
	public byte[] getAvaliacaoEscrita() {
		imagem.setAvaliacaoDefesa(new ImagemRN().avaliacaoEscrito());
		
		return imagem.getAvaliacaoEscrito();
	}
	
	public byte[] getAvaliacaoLogo() {
		imagem.setAvaliacaoDefesa(new ImagemRN().avaliacaoLogo());
		
		return imagem.getAvaliacaoLogo();
	}
	
	public byte[] getCadastroLogo() {
		imagem.setCadastroLogo(new ImagemRN().cadastroLogo());
		
		return imagem.getCadastroLogo();
	}
	
	public byte[] getCadastroRodape() {
		imagem.setCadastroRodape(new ImagemRN().cadastroRodape());
		
		return imagem.getCadastroRodape();
	}
	
}

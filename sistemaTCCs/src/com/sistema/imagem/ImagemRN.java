package com.sistema.imagem;

import com.sistema.util.DAOFactory;

public class ImagemRN {
	
	private ImagemDAO imagemDAO;
	
	public ImagemRN(){
		this.imagemDAO = DAOFactory.criaImagemDAO();
	}

	public byte[] acompanhamentoLogo() {
		return imagemDAO.acompanhamentoLogo();
	}
	
	public byte[] ataAssinaturas() {
		return imagemDAO.ataAssinaturas();
	}
	
	public byte[] ataLogo() {
		// TODO Auto-generated method stub
		return imagemDAO.ataLogo();
	}

	public byte[] ataNotas() {
		// TODO Auto-generated method stub
		return imagemDAO.ataNotas();
	}
	
	public byte[] ataRodape() {
		// TODO Auto-generated method stub
		return imagemDAO.ataRodape();
	}
	
	public byte[] cadastroLogo() {
		// TODO Auto-generated method stub
		return imagemDAO.cadastroLogo();
	}
	
	public byte[] cadastroRodape() {
		// TODO Auto-generated method stub
		return imagemDAO.cadastroRodape();
	}

	public byte[] avaliacaoAssinatura() {
		// TODO Auto-generated method stub
		return imagemDAO.avaliacaoAssinatura();
	}
	
	public byte[] avaliacaoDefesa() {
		// TODO Auto-generated method stub
		return imagemDAO.avaliacaoDefesa();
	}

	public byte[] avaliacaoEscrito() {
		// TODO Auto-generated method stub
		return imagemDAO.avaliacaoEscrito();
	}
	
	public byte[] avaliacaoLogo() {
		// TODO Auto-generated method stub
		return imagemDAO.avaliacaoLogo();
	}
	
}

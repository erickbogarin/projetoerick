package com.sistema.navegacao;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AdminNavigationRule {
	
	public String coordenador() {
		return "coordenador";
	}
	
	public String bibliotecario() {
		return "bibliotecario";
	}
	
	public String periodoLetivo() {
		return "periodoLetivo";
	}
	
	public String coordenacao() {
		return "coordenacao";
	}
	
	public String curso() {
		return "curso";
	}
	
}

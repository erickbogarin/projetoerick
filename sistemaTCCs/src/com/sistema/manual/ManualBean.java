package com.sistema.manual;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="manualBean")
@SessionScoped
public class ManualBean {
	
	private Manual manualSelecionado = new Manual();
	private List<Manual> manuais = null;
	
	public List<Manual> getManuais() {
		return manuais;
	}

	public Manual getManualSelecionado() {
		return manualSelecionado;
	}

	public void setManualSelecionado(Manual manualSelecionado) {
		this.manualSelecionado = manualSelecionado;
	}
	
}

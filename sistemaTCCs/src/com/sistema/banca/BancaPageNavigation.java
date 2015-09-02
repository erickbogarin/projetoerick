package com.sistema.banca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sistema.periodo.PeriodoBean;

@ManagedBean (name = "bancaPageNavigation")
public class BancaPageNavigation {
	
	@ManagedProperty(value = "#{bancaBean}")
	private BancaBean bancaBean = new BancaBean();
	
	@ManagedProperty(value = "#{periodoBean}")
	private PeriodoBean periodoBean = new PeriodoBean();
	
	public String apresentacoes() {
		if (!periodoBean.getLista().isEmpty())
			bancaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));
		
		bancaBean.setApresentacoes(null);

		return "apresentacoes";
	}
	
	public String documentos() {
		if (!periodoBean.getLista().isEmpty())
			bancaBean.setPeriodoSelecionado(periodoBean.getLista().get(0));
		
		bancaBean.setApresentacoes(null);

		return "documentos";
	}
	
	public void setPeriodoBean(PeriodoBean periodoBean) {
		this.periodoBean = periodoBean;
	}

	public void setBancaBean(BancaBean bancaBean) {
		this.bancaBean = bancaBean;
	}
}

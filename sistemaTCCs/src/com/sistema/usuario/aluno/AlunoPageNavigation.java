package com.sistema.usuario.aluno;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sistema.periodo.PeriodoBean;

@ManagedBean(name = "alunoPageNavigation")
public class AlunoPageNavigation {

	@ManagedProperty(value = "#{alunoBean}")
	private AlunoBean alunoBean = new AlunoBean();

	@ManagedProperty(value = "#{periodoBean}")
	private PeriodoBean periodoBean = new PeriodoBean();

	public String alunosTCC() {

		if (!periodoBean.getLista().isEmpty())
			alunoBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		alunoBean.setDisciplina("TCC");
		alunoBean.setLista(null);

		return "alunosTCC";
	}
	
	public String alunosITCC() {

		if (!periodoBean.getLista().isEmpty())
			alunoBean.setPeriodoSelecionado(periodoBean.getLista().get(0));

		alunoBean.setDisciplina("ITCC");
		alunoBean.setLista(null);

		return "alunosITCC";
	}
	
	public void setAlunoBean(AlunoBean alunoBean) {
		this.alunoBean = alunoBean;
	}

	public void setPeriodoBean(PeriodoBean periodoBean) {
		this.periodoBean = periodoBean;
	}

}

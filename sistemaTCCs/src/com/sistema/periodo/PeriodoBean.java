package com.sistema.periodo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "periodoBean")
@RequestScoped
public class PeriodoBean {

	private Periodo periodoSelecionado = new Periodo();
	private Periodo periodoAtual = new Periodo();
	private List<Periodo> lista = null;
	private List<SelectItem> periodoSelect;

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<String>();

		for (int i = 1; i <= 2; i++) {
			results.add(i + " / " + query);
		}

		return results;
	}

	public String cadastrarPeriodo() {
		PeriodoRN cronogramaRN = new PeriodoRN();
		cronogramaRN.salvar(periodoSelecionado);

		FacesMessage faces = new FacesMessage(
				"Periodo letivo cadastrado com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		lista = null;

		return "selecionarPeriodo.xhtml";
	}

	public void excluir() {
		PeriodoRN cronogramaRN = new PeriodoRN();
		cronogramaRN.excluir(periodoSelecionado);

		FacesMessage faces = new FacesMessage(
				"Periodo letivo excluido com sucesso!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);

		lista = null;
	}

	public List<Periodo> getLista() {
		PeriodoRN cronogramaRN = new PeriodoRN();

		if (lista == null) {
			lista = cronogramaRN.listar();
		}
		
		this.periodoAtual = lista.get(0);

		return lista;
	}

	public Periodo getPeriodoSelecionado() {
		return periodoSelecionado;
	}

	public void setPeriodoSelecionado(Periodo periodoSelecionado) {
		this.periodoSelecionado = periodoSelecionado;
	}

	public void setLista(List<Periodo> lista) {
		this.lista = lista;
	}

	public List<SelectItem> getPeriodoSelect() {

		if (periodoSelect == null) {

			periodoSelect = new ArrayList<SelectItem>();

			if (lista == null)
				lista = this.getLista();

			if (lista != null && !lista.isEmpty()) {
				SelectItem item;
				for (Periodo cronogramaLista : lista) {
					item = new SelectItem(cronogramaLista,
							cronogramaLista.getNome());
					periodoSelect.add(item);
				}
			}

		}

		return periodoSelect;
	}

	public void setPeriodoSelect(List<SelectItem> periodoSelect) {
		this.periodoSelect = periodoSelect;
	}

	public Periodo getPeriodoAtual() {
		if (!this.getLista().isEmpty())
			return periodoAtual;
		
		return new Periodo();
	}

	public void setPeriodoAtual(Periodo periodoAtual) {
		this.periodoAtual = periodoAtual;
	}

}

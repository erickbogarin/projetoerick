package com.sistema.manual;

import com.sistema.util.DAOFactory;

public class ManualRN {
	
	private ManualDAO manualDAO;
	
	public ManualRN() {
		this.manualDAO = DAOFactory.criaManualDAO();
	}
	
	public void salvar(Manual manual) {
		this.manualDAO.salvar(manual);
	}
	
}

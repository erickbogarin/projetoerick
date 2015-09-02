package org.primefaces.showcase.view.df;

import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dfView")
public class DFView {
	
	public void viewCars() {
        RequestContext.getCurrentInstance().openDialog("viewCars");
    }
	
	public void reunioes() {
        RequestContext.getCurrentInstance().openDialog("reunioes");
    }
	
	public void consultaTCCs() {
        RequestContext.getCurrentInstance().openDialog("consultaTCCs");
    }
	
	public void editarTCCs() {
        RequestContext.getCurrentInstance().openDialog("editar");
    }
}

package com.sistema.periodo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Periodo.class)
public class PeriodoConverter implements Converter{
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoString) {
		if(codigoString != null && codigoString.trim().length() > 0){
			Integer codigo =  Integer.valueOf(codigoString);
			PeriodoRN clienteRN = new PeriodoRN();
			
		    return clienteRN.pesquisarPorCodigo(codigo);		
			
		}
		
		
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object clienteObjeto) {
		if(clienteObjeto != null){
			 Periodo cliente = (Periodo) clienteObjeto;
			 return cliente.getId().toString();
		}
		return null;
	}
	
}

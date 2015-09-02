package com.sistema.usuario.orientador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Orientador.class)
public class OrientadorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoString) {
		if(codigoString != null && codigoString.trim().length() > 0){
			Integer codigo =  Integer.valueOf(codigoString);
			OrientadorRN orientadorRN = new OrientadorRN();
			
		    return orientadorRN.findById(codigo);
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object orientadorObjeto) {
		if(orientadorObjeto != null){
			 Orientador orientador = (Orientador) orientadorObjeto;
			 return orientador.getId().toString();
		}
		return null;
	}

}

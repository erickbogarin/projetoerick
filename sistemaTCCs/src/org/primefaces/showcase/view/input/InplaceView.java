package org.primefaces.showcase.view.input;
 
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
 
@SuppressWarnings("serial")
@ManagedBean
public class InplaceView implements Serializable {
     
    private String text = "PrimeFaces";
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }
}

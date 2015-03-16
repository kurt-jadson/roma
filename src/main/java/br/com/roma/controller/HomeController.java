package br.com.roma.controller;

import br.com.roma.entity.Metanalise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@ViewScoped
public class HomeController implements Serializable {
	
	private List<Metanalise> metanalises;
	
	public List<Metanalise> getMetanalises() {
		if(metanalises == null) {
			metanalises = new ArrayList<>();
			
			metanalises.add(new Metanalise("Lorem ipsum dolor sit amet", 
					Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 
					Boolean.FALSE, Boolean.FALSE));
			
			metanalises.add(new Metanalise("Duis molestie luctus risus", 
					Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 
					Boolean.TRUE, Boolean.FALSE));
			
			metanalises.add(new Metanalise("Praesent dictum mauris eget", 
					Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 
					Boolean.FALSE, Boolean.FALSE));
			
			metanalises.add(new Metanalise("Aenean ut hendrerit purus", 
					Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 
					Boolean.TRUE, Boolean.TRUE));
			
		}
		
		return metanalises;
	}
	
}

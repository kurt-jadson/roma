package br.com.roma.controller;

import br.com.roma.boundary.MetanaliseService;
import br.com.roma.entity.Metanalise;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@ViewScoped
public class HomeController implements Serializable {
	
	@Inject
	private MetanaliseService metanaliseService;
	private List<Metanalise> metanalises;
	
	public List<Metanalise> getMetanalises() {
		if(metanalises == null) {
			metanalises = metanaliseService.buscarNaoFinalizadas();
		}
		return metanalises;
	}
	
}

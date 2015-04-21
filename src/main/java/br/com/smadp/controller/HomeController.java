package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
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

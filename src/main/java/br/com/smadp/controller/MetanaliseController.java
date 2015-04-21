package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@RequestScoped
public class MetanaliseController {
	
	@Inject
	private MetanaliseService metanaliseService;
	private List<Metanalise> metanalises;
	
	public List<Metanalise> getMetanalises() {
		if(metanalises == null) {
			metanalises = metanaliseService.buscarTodas();
		}
		return metanalises;
	}	
	
}
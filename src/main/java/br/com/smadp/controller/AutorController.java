package br.com.smadp.controller;

import br.com.smadp.entity.Autor;
import br.com.smadp.framework.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@ViewScoped
public class AutorController implements Serializable {

	private List<Autor> autores;
	
	public List<Autor> getAutores() {
		if (autores == null) {
			autores = new ArrayList<>();
			String atividade = JSFUtils.translate("atividade.jadson");
			autores.add(new Autor(1L, "Jadson Frassetto", 2015, atividade));
		}
		return autores;
	}

}

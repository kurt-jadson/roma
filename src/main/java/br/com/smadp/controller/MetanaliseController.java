package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
import br.com.smadp.exception.SmadpException;
import br.com.smadp.framework.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@ViewScoped
public class MetanaliseController implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MetanaliseController.class.getName());
	private static final String OUTCOME_SUCESSO = "sucesso";

	@Inject
	private MetanaliseService metanaliseService;
	private List<Metanalise> metanalises;
	private Metanalise metanalise;

	public List<Metanalise> getMetanalises() {
		if (metanalises == null) {
			metanalises = metanaliseService.buscarTodas();
		}
		return metanalises;
	}

	public Metanalise getMetanalise() {
		if (metanalise == null) {
			metanalise = new Metanalise();
		}
		return metanalise;
	}

	// Listeners ---------------------------------------------------------------
	public String salvar() {
		try {
			metanaliseService.salvar(getMetanalise());
			return OUTCOME_SUCESSO;
		} catch (SmadpException ex) {
			String mensagem = JSFUtils.translate(ex.getLocalizedMessage());
			LOGGER.warning(mensagem);
			JSFUtils.addMessage(mensagem);
		}
		return null;
	}

}
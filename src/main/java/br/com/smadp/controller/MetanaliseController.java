package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
import br.com.smadp.entity.MetanaliseRow;
import br.com.smadp.exception.SmadpException;
import br.com.smadp.framework.JSFUtils;
import com.lassitercg.faces.components.event.SheetUpdate;
import com.lassitercg.faces.components.sheet.Sheet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
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
	private List<MetanaliseRow> estudos;
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
	
	public List<MetanaliseRow> getEstudos() {
		if(estudos == null) {
			estudos = new ArrayList<>();
			
			MetanaliseRow row = new MetanaliseRow();
			row.setNumero(1L);
			estudos.add(row);
			
			MetanaliseRow row2 = new MetanaliseRow();
			row2.setNumero(2L);
			estudos.add(row2);
		}
		return estudos;
	}

	// Listeners ---------------------------------------------------------------
	public String salvar() {
		try {
			metanaliseService.salvar(getMetanalise());
			return OUTCOME_SUCESSO;
		} catch (SmadpException ex) {
			String mensagem = JSFUtils.translate(ex.getLocalizedMessage());
			LOGGER.warning(mensagem);
			JSFUtils.addErrorMessage(mensagem);
		}
		return null;
	}
	
	public void cellEdit(AjaxBehaviorEvent event) {
		Sheet sheet = (Sheet) event.getComponent();
		List<SheetUpdate> updates = sheet.getUpdates();
		System.out.println("updates: " + updates.size());
	}

}
package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseRowService;
import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
import br.com.smadp.entity.MetanaliseRow;
import br.com.smadp.entity.MetanaliseRowCol;
import br.com.smadp.exception.SmadpException;
import br.com.smadp.framework.JSFUtils;
import com.lassitercg.faces.components.sheet.Column;
import com.lassitercg.faces.components.sheet.Sheet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.el.ValueExpression;
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
	@Inject
	private MetanaliseRowService rowService;
	private List<Metanalise> metanalises;
	private List<MetanaliseRow> estudos;
	private Metanalise metanalise;
	private Sheet sheet;
	private String nomeColuna;

	public void loadMetanalise() {
		metanalise = metanaliseService.buscarPorId(getMetanalise().getId());
	}

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
		if (estudos == null) {
			if (metanalise.isNew()) {
				estudos = new ArrayList<>();
				
				MetanaliseRow row = new MetanaliseRow();
				row.setNumero(1L);
				row.setMetanalise(getMetanalise());
				estudos.add(row);
			} else {
				estudos = rowService.buscarRowsPorMetanalise(getMetanalise().getId());
			}
			getMetanalise().setRows(estudos);
		}
		return estudos;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public String getNomeColuna() {
		return nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}

	// Listeners ---------------------------------------------------------------
	public String salvar() {
		try {
			List<MetanaliseRowCol> dados = new ArrayList<>();
			for (MetanaliseRow row : getEstudos()) {
				MetanaliseRowCol rowCol = new MetanaliseRowCol();
			}

			//getMetanalise().addAllBancoDados(dados);
			metanaliseService.salvar(getMetanalise());
			return OUTCOME_SUCESSO;
		} catch (SmadpException ex) {
			String mensagem = JSFUtils.translate(ex.getLocalizedMessage());
			LOGGER.warning(mensagem);
			JSFUtils.addErrorMessage(mensagem);
		}
		return null;
	}

	public void adicionarLinha() {
		MetanaliseRow linha = new MetanaliseRow();
		linha.setNumero(Long.valueOf(getEstudos().size()) + 1);
		linha.setMetanalise(getMetanalise());
		getEstudos().add(linha);
	}

	public void adicionarColuna() {
		ValueExpression ve = JSFUtils.createValueExpression("#{row.numero}", Long.class);
		Column column = new Column();
		column.setHeaderText(nomeColuna);
		column.setColWidth(15);
		column.setValueExpression("value", ve);
		getSheet().getColumns().add(column);
		nomeColuna = null;
	}

}

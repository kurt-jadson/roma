package br.com.smadp.controller;

import br.com.smadp.boundary.MetanaliseColService;
import br.com.smadp.boundary.MetanaliseService;
import br.com.smadp.entity.Metanalise;
import br.com.smadp.entity.MetanaliseCol;
import br.com.smadp.entity.MetanaliseRow;
import br.com.smadp.entity.MetanaliseRowCol;
import br.com.smadp.exception.SmadpException;
import br.com.smadp.framework.JSFUtils;
import com.lassitercg.faces.components.sheet.Column;
import com.lassitercg.faces.components.sheet.Sheet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.LongConverter;
import javax.faces.validator.ValidatorException;
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
	private MetanaliseColService colService;
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
			List<MetanaliseCol> cols = new ArrayList<>();
			if (metanalise.isNew()) {
				estudos = new ArrayList<>();
				estudos.add(getNovaLinha());
				getMetanalise().setRows(estudos);
			} else {
				estudos = getMetanalise().getRows();
				cols = colService.buscarColsPorMetanalise(getMetanalise().getId());

				for (MetanaliseCol col : cols) {
					Column column = createColumn(col.getNome());
					getSheet().getColumns().add(column);
					getSheet().getChildren().add(column);
				}

				for (MetanaliseRow row : getMetanalise().getRows()) {
					for (MetanaliseRowCol rowCol : row.getColunasDinamicas()) {
						row.getValores().put(rowCol.getCol().getNome(), rowCol.getValor());
					}
				}

			}
			getMetanalise().setCols(cols);
		}
		return estudos;
	}

	public void setEstudos(List<MetanaliseRow> estudos) {
		//Nada há fazer
		//Este método é utilizado no inputHidden simplesmente para carregar as
		//colunas da planilha
	}
	
	private MetanaliseRow getNovaLinha() {
		MetanaliseRow row = new MetanaliseRow();
		row.setNumero(1L);
		row.setMetanalise(getMetanalise());
		return row;
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
			for (MetanaliseRow row : getEstudos()) {
				for (Entry<String, Long> entry : row.getValores().entrySet()) {
					MetanaliseCol col = getColPorNome(entry.getKey());
					Long valor = entry.getValue();
					MetanaliseRowCol rowCol = new MetanaliseRowCol(row, col, valor);
					row.getColunasDinamicas().add(rowCol);
				}
			}

			metanaliseService.salvar(getMetanalise());
			return OUTCOME_SUCESSO;
		} catch (SmadpException ex) {
			String mensagem = JSFUtils.translate(ex.getLocalizedMessage());
			LOGGER.warning(mensagem);
			JSFUtils.addErrorMessage(mensagem);
		}
		return null;
	}

	private MetanaliseCol getColPorNome(String nome) {
		for (MetanaliseCol col : getMetanalise().getCols()) {
			if (col.getNome().equals(nome)) {
				return col;
			}
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
		MetanaliseCol coluna = new MetanaliseCol();
		coluna.setMetanalise(getMetanalise());
		coluna.setNome(nomeColuna);
		getMetanalise().getCols().add(coluna);

		Column column = createColumn(nomeColuna);
		getSheet().getColumns().add(column);
		getSheet().getChildren().add(column);
		nomeColuna = null;
	}

	private Column createColumn(String nome) {
		String expression = String.format("#{row.valores['%s']}", nome);
		ValueExpression ve = JSFUtils.createValueExpression(expression, Long.class);
		Column column = new Column();
		column.setHeaderText(nome);
		column.setColWidth(15);
		column.setColType("numeric");
		column.setValueExpression("value", ve);
		column.setValueExpression("sortBy", ve);
		column.setConverter(new LongConverter());
		return column;
	}

	// Validators --------------------------------------------------------------
	public void validarNomeColuna(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		for (MetanaliseCol col : getMetanalise().getCols()) {
			if (col.getNome().equals(value.toString())) {
				String mensagem = JSFUtils.translate("smadp.mensagens.1001");
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
			}
		}
	}

}

package br.com.smadp.controller;

import br.com.smadp.boundary.PesquisadorService;
import br.com.smadp.boundary.UsuarioService;
import br.com.smadp.entity.MetodoAgrupamento;
import br.com.smadp.entity.MetodoDOR;
import br.com.smadp.entity.ModeloRegressao;
import br.com.smadp.entity.Pesquisador;
import br.com.smadp.framework.JSFUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kurt
 */
@Named
@RequestScoped
public class ConfiguracoesController {

	private static final Logger LOGGER = Logger.getLogger(MetanaliseController.class.getName());
	private static final String OUTCOME_SUCESSO = "sucesso";

	@Inject
	private PesquisadorService pesquisadorService;
	@Inject
	private UsuarioService usuarioService;
	private Pesquisador pesquisador;

	public Pesquisador getPesquisador() {
		if (pesquisador == null) {
			pesquisador = pesquisadorService.buscarVinculadoUsuarioLogado();
		}
		return pesquisador;
	}

	public MetodoAgrupamento[] getMetodosAgrupamento() {
		return MetodoAgrupamento.values();
	}

	public ModeloRegressao[] getModelosRegressao() {
		return ModeloRegressao.values();
	}

	public MetodoDOR[] getMetodosDOR() {
		return MetodoDOR.values();
	}

	// Listeners ---------------------------------------------------------------
	public String salvar() {
		try {
			if (getPesquisador().getId() == null) {
				usuarioService.salvarConfiguracoesUsuario(null);
			} else {
				usuarioService.salvarConfiguracoesPesquisador(getPesquisador());
			}
			
			JSFUtils.addInfoMessage(JSFUtils.translate("smadp.mensagens.0003"));
			return OUTCOME_SUCESSO;
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
	}

}

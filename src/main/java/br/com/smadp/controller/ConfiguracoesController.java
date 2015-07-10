package br.com.smadp.controller;

import br.com.smadp.boundary.PesquisadorService;
import br.com.smadp.boundary.UsuarioService;
import br.com.smadp.entity.Idioma;
import br.com.smadp.entity.MetodoAgrupamento;
import br.com.smadp.entity.Pesquisador;
import br.com.smadp.entity.Usuario;
import br.com.smadp.framework.JSFUtils;
import br.com.smadp.framework.LoggedIn;
import java.io.Serializable;
import java.util.logging.Level;
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
public class ConfiguracoesController implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(MetanaliseController.class.getName());
	private static final String OUTCOME_SUCESSO = "sucesso";

	@Inject
	private PesquisadorService pesquisadorService;
	@Inject
	private UsuarioService usuarioService;
	private Pesquisador pesquisador;
	@Inject
	@LoggedIn
	private Usuario usuarioLogado;

	public Pesquisador getPesquisador() {
		if (pesquisador == null) {
			pesquisador = pesquisadorService.buscarVinculadoUsuarioLogado();
		}
		return pesquisador;
	}

	public MetodoAgrupamento[] getMetodosAgrupamento() {
		return MetodoAgrupamento.values();
	}
	
	public Idioma[] getIdiomas() {
		return Idioma.values();
	}

	// Listeners ---------------------------------------------------------------
	public String salvar() {
		try {
			if (getPesquisador().getId() == null) {
				usuarioService.salvarConfiguracoesUsuario(usuarioLogado);
			} else {
				usuarioService.salvarConfiguracoesPesquisador(getPesquisador());
			}
			
			usuarioLogado.setIdioma(getPesquisador().getUsuario().getIdioma());
			JSFUtils.addInfoMessage(JSFUtils.translate("smadp.mensagens.0003"), true);
			return OUTCOME_SUCESSO;
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return null;
	}

}

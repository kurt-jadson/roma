package br.com.smadp.framework;

import br.com.smadp.entity.Usuario;
import java.util.Locale;
import javax.enterprise.inject.Instance;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

/**
 *
 * @author kurt
 */
public class LanguageListener implements PhaseListener {

	@Inject
	@LoggedIn
	private Instance<Usuario> usuarioLogado;

	@Override
	public void afterPhase(PhaseEvent event) {
		Locale locale = usuarioLogado.get().getIdioma().getLocale();
		event.getFacesContext().getViewRoot().setLocale(locale);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}

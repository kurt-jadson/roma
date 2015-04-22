package br.com.smadp.framework;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author kurt
 */
public class JSFUtils {

	private static final String RESOURCE_BUNDLE_NAME = "br.com.smadp.i18n";
	
	public static String translate(String key) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale).getString(key);
	}
	
	public static void addMessage(String msg, Severity severity) {
		addMessage(msg, null, severity);
	}
	
	public static void addErrorMessage(String msg) {
		addMessage(msg, null, FacesMessage.SEVERITY_ERROR);
	}
	
	public static void addMessage(String msg, String clientId, Severity severity) {
		FacesMessage message = new FacesMessage(severity, msg, null);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	
}

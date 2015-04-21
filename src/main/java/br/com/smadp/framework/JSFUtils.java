package br.com.smadp.framework;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
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
	
	public static void addMessage(String msg) {
		addMessage(msg, null);
	}
	
	public static void addMessage(String msg, String clientId) {
		FacesMessage message = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	
}

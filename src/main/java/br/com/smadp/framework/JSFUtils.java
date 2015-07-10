package br.com.smadp.framework;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
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
	
	public static void addMessage(String msg, Severity severity, Boolean keepMessages) {
		addMessage(msg, null, severity);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessages);
	}
	
	public static void addErrorMessage(String msg) {
		addMessage(msg, null, FacesMessage.SEVERITY_ERROR);
	}
	
	public static void addErrorMessage(String msg, Boolean keep) {
		addMessage(msg, null, FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keep);
	}
	
	public static void addInfoMessage(String msg) {
		addMessage(msg, null, FacesMessage.SEVERITY_INFO);
	}
	
	public static void addInfoMessage(String msg, Boolean keep) {
		addMessage(msg, null, FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keep);
	}
	
	public static void addMessage(String msg, String clientId, Severity severity) {
		FacesMessage message = new FacesMessage(severity, msg, null);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	
	public static ValueExpression createValueExpression(String expression, Class<?> expectedType) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expFactory = context.getApplication().getExpressionFactory();
		return expFactory.createValueExpression(context.getELContext(), expression, expectedType);
	}
	
}

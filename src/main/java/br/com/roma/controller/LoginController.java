package br.com.roma.controller;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author kurt
 */
@Named
@RequestScoped
public class LoginController {

	private String username;
	private String password;
	private boolean rememberMe = false;

	private static final Logger log = Logger.getLogger(LoginController.class
			.getName());

	public String authenticate() {
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(rememberMe);
		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			log.warning(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Usuário ou senha incorretos."));
			return "/login";
		}
		return "/home";
	}

	public String logout() {

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (Exception e) {
			log.warning(e.toString());
		}
		return "/login";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}

package br.com.smadp.controller;

import br.com.smadp.boundary.UsuarioService;
import br.com.smadp.entity.Usuario;
import br.com.smadp.framework.JSFUtils;
import br.com.smadp.framework.LoggedIn;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
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
@SessionScoped
public class LoginController implements Serializable {

	@Inject
	private UsuarioService service;
	private boolean rememberMe = false;
	private Usuario usuario;

	private static final Logger LOGGER = Logger.getLogger(LoginController.class
			.getName());

	public String authenticate() {
		UsernamePasswordToken token = new UsernamePasswordToken(usuario.getUsername(),
				usuario.getPassword());
		token.setRememberMe(rememberMe);
		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(token);
			usuario = service.buscarPorUsername(usuario.getUsername());
		} catch (AuthenticationException e) {
			LOGGER.warning(e.getMessage());
			JSFUtils.addMessage(JSFUtils.translate("smadp.mensagens.0002"));
			return "/login";
		}
		return "/home?faces-redirect=true";
	}

	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (Exception e) {
			LOGGER.warning(e.toString());
		}
		return "/login";
	}

	@Produces
	@LoggedIn
	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}

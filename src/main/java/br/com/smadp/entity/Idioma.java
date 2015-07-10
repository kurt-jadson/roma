package br.com.smadp.entity;

import java.util.Locale;

/**
 *
 * @author kurt
 */
public enum Idioma {
	PORTUGUES("idioma.portugues", new Locale("pt", "BR")),
	INGLES("idioma.ingles", Locale.US),
	ESPANHOL("idioma.espanhol", new Locale("es", "ES"));
	
	private final String descricao;
	private final Locale locale;
	
	private Idioma(String descricao, Locale locale) {
		this.descricao = descricao;
		this.locale = locale;
	}

	public String getDescricao() {
		return descricao;
	}

	public Locale getLocale() {
		return locale;
	}
	
}

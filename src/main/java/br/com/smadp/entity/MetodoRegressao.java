package br.com.smadp.entity;

/**
 *
 * @author kurt
 */
public enum MetodoRegressao {

	A("A"),
	B("B");
	
	private final String descricao;
	
	private MetodoRegressao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

package br.com.smadp.entity;

/**
 *
 * @author kurt
 */
public enum ModeloRegressao {

	A("A"),
	B("B");
	
	private final String descricao;
	
	private ModeloRegressao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

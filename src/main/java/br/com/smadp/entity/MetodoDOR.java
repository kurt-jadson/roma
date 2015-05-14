package br.com.smadp.entity;

/**
 *
 * @author kurt
 */
public enum MetodoDOR {
	
	MANTEL_HAENSZEL("Mantel-Haenszel"),
	DER_SIMONIAN_LAIRD("Der Simonian-Laird"),
	MOSES_CONSTANT("Moses’ constant of linear model");
	
	private final String descricao;
	
	private MetodoDOR(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

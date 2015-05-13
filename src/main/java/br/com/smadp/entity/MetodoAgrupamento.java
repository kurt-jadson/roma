package br.com.smadp.entity;

/**
 *
 * @author kurt
 */
public enum MetodoAgrupamento {
	
	MANTEL_HAENSZEL("Mantel-Haenszel"),
	DER_SIMONIAN_LAIRD("Der Simonian-Laird");
	
	private final String descricao;
	
	private MetodoAgrupamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

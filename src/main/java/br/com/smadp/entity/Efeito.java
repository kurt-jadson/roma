package br.com.smadp.entity;

/**
 *
 * @author kurt
 */
public enum Efeito {
	
	FIXO("metanalise.efeitoFixo"),
	ALEATORIO("metanalise.efeitoAleatorio");
	
	private final String descricao;
	
	private Efeito(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

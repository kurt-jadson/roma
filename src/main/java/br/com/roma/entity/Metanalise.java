package br.com.roma.entity;

/**
 *
 * @author kurt
 */
public class Metanalise {

	private String nome;
	private Boolean etapaBaseDados;
	private Boolean etapaQualidadeMetodologica;
	private Boolean etapaAnaliseTabular;
	private Boolean etapaAnaliseGrafica;
	private Boolean etapaViesPublicacao;

	public Metanalise(String nome, Boolean etapaBaseDados, Boolean etapaQualidadeMetodologica, Boolean etapaAnaliseTabular, Boolean etapaAnaliseGrafica, Boolean etapaViesPublicacao) {
		this.nome = nome;
		this.etapaBaseDados = etapaBaseDados;
		this.etapaQualidadeMetodologica = etapaQualidadeMetodologica;
		this.etapaAnaliseTabular = etapaAnaliseTabular;
		this.etapaAnaliseGrafica = etapaAnaliseGrafica;
		this.etapaViesPublicacao = etapaViesPublicacao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getPercentual() {
		int bd = etapaBaseDados ? 20 : 0;
		int qm = etapaQualidadeMetodologica ? 20 : 0;
		int at = etapaAnaliseTabular ? 20 : 0;
		int ag = etapaAnaliseGrafica ? 20 : 0;
		int vp = etapaViesPublicacao ? 20 : 0;
		return bd + qm + at + ag + vp; 
	}
	
	
}

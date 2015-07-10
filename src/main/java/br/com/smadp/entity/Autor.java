package br.com.smadp.entity;

import java.util.Objects;

/**
 *
 * @author kurt
 */
public class Autor {

	private Long id;
	private String nome;
	private Integer ano;
	private String atividade;

	public Autor(Long id, String nome, Integer ano, String atividade) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.atividade = atividade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Autor other = (Autor) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	
}

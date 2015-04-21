package br.com.smadp.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_ETAPA")
public class MetanaliseEtapa implements Serializable {
	
	public static final MetanaliseEtapa BANCO_DADOS = new MetanaliseEtapa(1L);
	public static final MetanaliseEtapa QUALIDADE_ESTUDOS = new MetanaliseEtapa(2L);
	public static final MetanaliseEtapa ANALISE_TABULAR = new MetanaliseEtapa(3L);
	public static final MetanaliseEtapa ANALISE_GRAFICA = new MetanaliseEtapa(4L);
	public static final MetanaliseEtapa ANALISE_VIES_PUBLICACAO = new MetanaliseEtapa(5L);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public MetanaliseEtapa() {
	}

	public MetanaliseEtapa(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.id);
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
		final MetanaliseEtapa other = (MetanaliseEtapa) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseEtapa{" + "id=" + id + ", nome=" + nome + '}';
	}
	
}

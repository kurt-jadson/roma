package br.com.smadp.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author kurt
 */
@Embeddable
public class MetanaliseMetanaliseEtapaPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "METANALISE_ID", referencedColumnName = "ID")
	private Metanalise metanalise;
	@Basic
	@Column(name = "METANALISEETAPA_ID")
	private long etapa;

	public MetanaliseMetanaliseEtapaPK() {
	}
	
	public MetanaliseMetanaliseEtapaPK(Metanalise metanalise, long etapa) {
		this.metanalise = metanalise;
		this.etapa = etapa;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.metanalise);
		hash = 97 * hash + (int) (this.etapa ^ (this.etapa >>> 32));
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
		final MetanaliseMetanaliseEtapaPK other = (MetanaliseMetanaliseEtapaPK) obj;
		if (!Objects.equals(this.metanalise, other.metanalise)) {
			return false;
		}
		if (this.etapa != other.etapa) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseMetanaliseEtapaId{" + "metanalise=" + metanalise + ", etapa=" + etapa + '}';
	}
	
}

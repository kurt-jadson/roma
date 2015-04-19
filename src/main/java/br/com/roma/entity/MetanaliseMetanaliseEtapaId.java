package br.com.roma.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author kurt
 */
@Embeddable
public class MetanaliseMetanaliseEtapaId implements Serializable {

	@Basic
	@JoinColumn(name = "METANALISE_ID")
	private long metanalise;
	@Basic
	@JoinColumn(name = "METANALISEETAPA_ID")
	private long etapa;

	public MetanaliseMetanaliseEtapaId() {
	}
	
	public MetanaliseMetanaliseEtapaId(long metanalise, long etapa) {
		this.metanalise = metanalise;
		this.etapa = etapa;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 61 * hash + (int) (this.metanalise ^ (this.metanalise >>> 32));
		hash = 61 * hash + (int) (this.etapa ^ (this.etapa >>> 32));
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
		final MetanaliseMetanaliseEtapaId other = (MetanaliseMetanaliseEtapaId) obj;
		if (this.metanalise != other.metanalise) {
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

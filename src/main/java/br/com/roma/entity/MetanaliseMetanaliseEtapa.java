package br.com.roma.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_METANALISE_ETAPA")
public class MetanaliseMetanaliseEtapa implements Serializable {
	
	@EmbeddedId
	private MetanaliseMetanaliseEtapaId id;
	@NotNull
	@ManyToOne
	private Metanalise metanalise;
	@NotNull
	@ManyToOne
	private MetanaliseEtapa metanaliseEtapa;
	private Boolean concluida;

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + Objects.hashCode(this.id);
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
		final MetanaliseMetanaliseEtapa other = (MetanaliseMetanaliseEtapa) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseMetanaliseEtapa{" + "id=" + id + ", metanalise=" + metanalise + ", metanaliseEtapa=" + metanaliseEtapa + ", concluida=" + concluida + '}';
	}
	
}

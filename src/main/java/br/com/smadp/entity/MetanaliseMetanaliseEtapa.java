package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_METANALISE_ETAPA")
public class MetanaliseMetanaliseEtapa implements PersistentEntity {
	
	@EmbeddedId
	private MetanaliseMetanaliseEtapaPK id;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Metanalise metanalise;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private MetanaliseEtapa metanaliseEtapa;
	private Boolean concluida;

	public MetanaliseMetanaliseEtapa() {
	}

	public MetanaliseMetanaliseEtapa(Metanalise metanalise, MetanaliseEtapa etapa) {
		this.id = new MetanaliseMetanaliseEtapaPK(metanalise, etapa.getId());
	}

	@Override
	public boolean isNew() {
		return id == null;
	}
	
	public Boolean getConcluida() {
		return concluida;
	}
	
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

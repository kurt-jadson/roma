package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_COL")
public class MetanaliseCol implements PersistentEntity {

	public static final String NQ_BUSCAR_POR_METANALISE_ID = "MetanaliseCol.buscarPorMetanaliseId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(max = 255)
	private String nome;
	@NotNull
	@ManyToOne
	private Metanalise metanalise;
	
	@Override
	public boolean isNew() {
		return id == null;
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

	public Metanalise getMetanalise() {
		return metanalise;
	}

	public void setMetanalise(Metanalise metanalise) {
		this.metanalise = metanalise;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + Objects.hashCode(this.id);
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
		final MetanaliseCol other = (MetanaliseCol) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseCol{" + "id=" + id + ", nome=" + nome + ", metanalise=" + metanalise + '}';
	}
	
}

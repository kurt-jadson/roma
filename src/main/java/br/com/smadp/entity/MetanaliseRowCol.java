package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_ROW_COL")
public class MetanaliseRowCol implements PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private MetanaliseRow row;
	@ManyToOne
	private MetanaliseCol col;
	private Long valor; 

	public MetanaliseRowCol() {
	}

	public MetanaliseRowCol(MetanaliseRow row, MetanaliseCol col, Long valor) {
		this.row = row;
		this.col = col;
		this.valor = valor;
	}
	
	@Override
	public boolean isNew() {
		return id == null;
	}

	public Long getId() {
		return id;
	}

	public MetanaliseRow getRow() {
		return row;
	}

	public MetanaliseCol getCol() {
		return col;
	}

	public Long getValor() {
		return valor;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
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
		final MetanaliseRowCol other = (MetanaliseRowCol) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	
	
	
}

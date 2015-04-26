package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_ROW_COL")
public class MetanaliseRowCol implements PersistentEntity {

	@EmbeddedId
	private MetanaliseRowColPK id;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private MetanaliseRow row;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private MetanaliseCol col;
	private Long valor;
	//incluído unicamente para conseguir mapear, pois já está no MetanaliseRow e no MetanaliseCol
	@ManyToOne
	@JoinColumn
	private Metanalise metanalise; 

	public MetanaliseRowCol() {
	}

	public MetanaliseRowCol(MetanaliseRow row, MetanaliseCol col, Long valor) {
		this.id = new MetanaliseRowColPK(row, col);
		this.valor = valor;
	}
	
	@Override
	public boolean isNew() {
		return id == null;
	}

	public MetanaliseRowColPK getId() {
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

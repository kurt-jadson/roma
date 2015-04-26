package br.com.smadp.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author kurt
 */
@Embeddable
public class MetanaliseRowColPK implements Serializable {
	
	@ManyToOne
	@JoinColumn(name = "ROW_ID", referencedColumnName = "ID")
	private MetanaliseRow metanaliseRow;
	@ManyToOne
	@JoinColumn(name = "COL_ID", referencedColumnName = "ID")
	private MetanaliseCol metanaliseCol;

	public MetanaliseRowColPK() {
	}

	public MetanaliseRowColPK(MetanaliseRow metanaliseRow, MetanaliseCol metanaliseCol) {
		this.metanaliseRow = metanaliseRow;
		this.metanaliseCol = metanaliseCol;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 61 * hash + Objects.hashCode(this.metanaliseRow);
		hash = 61 * hash + Objects.hashCode(this.metanaliseCol);
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
		final MetanaliseRowColPK other = (MetanaliseRowColPK) obj;
		if (!Objects.equals(this.metanaliseRow, other.metanaliseRow)) {
			return false;
		}
		if (!Objects.equals(this.metanaliseCol, other.metanaliseCol)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseRowColPK{" + "metanaliseRow=" + metanaliseRow + ", metanaliseCol=" + metanaliseCol + '}';
	}

}
package br.com.smadp.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kurt
 */
@Embeddable
public class MetanaliseRowColPK implements Serializable {
	
//	@ManyToOne
//	@JoinColumn(name = "ROW_ID", referencedColumnName = "ID", insertable = false, updatable = false)
//	private MetanaliseRow metanaliseRow;
//	@ManyToOne
//	@JoinColumn(name = "COL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
//	private MetanaliseCol metanaliseCol;
	@Basic
	@Column(name = "ROW_ID", insertable = false, updatable = false)
	private Long metanaliseRow;
	@Basic
	@Column(name = "COL_ID", insertable = false, updatable = false)
	private Long metanaliseCol;

	public MetanaliseRowColPK() {
	}

//	public MetanaliseRowColPK(MetanaliseRow metanaliseRow, MetanaliseCol metanaliseCol) {
//		this.metanaliseRow = metanaliseRow;
//		this.metanaliseCol = metanaliseCol;
//	}
	public MetanaliseRowColPK(Long metanaliseRow, Long metanaliseCol) {
		this.metanaliseRow = metanaliseRow;
		this.metanaliseCol = metanaliseCol;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 17 * hash + Objects.hashCode(this.metanaliseRow);
		hash = 17 * hash + Objects.hashCode(this.metanaliseCol);
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
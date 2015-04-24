package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE_ROW")
public class MetanaliseRow implements PersistentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long numero;
	@NotNull
	@Size(max = 255)
	private String titulo;
	@Size(max = 255)
	private String autor;
	private Long tp;
	private Long tn;
	private Long fp;
	private Long fn;
	

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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Long getTp() {
		return tp;
	}

	public void setTp(Long tp) {
		this.tp = tp;
	}

	public Long getTn() {
		return tn;
	}

	public void setTn(Long tn) {
		this.tn = tn;
	}

	public Long getFp() {
		return fp;
	}

	public void setFp(Long fp) {
		this.fp = fp;
	}

	public Long getFn() {
		return fn;
	}

	public void setFn(Long fn) {
		this.fn = fn;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.id);
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
		final MetanaliseRow other = (MetanaliseRow) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MetanaliseRow{" + "id=" + id + ", numero=" + numero + ", titulo=" + titulo + ", tp=" + tp + ", tn=" + tn + ", fp=" + fp + ", fn=" + fn + '}';
	}
	
}
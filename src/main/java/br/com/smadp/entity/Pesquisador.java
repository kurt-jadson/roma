package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "SYS_PESQUISADOR")
public class Pesquisador implements PersistentEntity<Long> {

	public static final String NQ_BUSCAR_TODOS = "Pesquisador.buscarTodos";
	public static final String NQ_BUSCAR_POR_USUARIO = "Pesquisador.buscarPorUsuario";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	@NotNull
	@Enumerated(EnumType.STRING)
	private MetodoAgrupamento metodoAgrupamento;
	@NotNull
	@Min(0)
	@Max(100)
	private Integer intervaloConfianca;
	@NotNull
	private Boolean preencherCelulasVazias;

	@Override
	public boolean isNew() {
		return id == null;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public MetodoAgrupamento getMetodoAgrupamento() {
		return metodoAgrupamento;
	}

	public void setMetodoAgrupamento(MetodoAgrupamento metodoAgrupamento) {
		this.metodoAgrupamento = metodoAgrupamento;
	}

	public Integer getIntervaloConfianca() {
		return intervaloConfianca;
	}

	public void setIntervaloConfianca(Integer intervaloConfianca) {
		this.intervaloConfianca = intervaloConfianca;
	}

	public Boolean getPreencherCelulasVazias() {
		return preencherCelulasVazias;
	}

	public void setPreencherCelulasVazias(Boolean preencherCelulasVazias) {
		this.preencherCelulasVazias = preencherCelulasVazias;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.id);
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
		final Pesquisador other = (Pesquisador) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pesquisador{" + "id=" + id + ", usuario=" + usuario + '}';
	}
	
}

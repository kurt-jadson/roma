package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE")
public class Metanalise implements PersistentEntity {
	
	public static final String NQ_BUSCAR_NAO_FINALIZADAS = "Metanalise.buscarNaoFinalizadas";
	public static final String NQ_BUSCAR_TODAS = "Metanalise.buscarTodas";
	public static final String NQ_BUSCAR_POR_ID = "Metanalise.buscarPorId";
	public static final String NQ_COUNT_TITULO = "Metanalise.countTitulo";
	private static final int QUANTIDADE_ETAPAS = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String titulo;
	@NotNull
	@ManyToOne
	private Pesquisador pesquisadorInclusao;
	@NotNull
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dataInclusao;
	@OneToMany(mappedBy = "metanalise", cascade = CascadeType.PERSIST)
	private final List<MetanaliseMetanaliseEtapa> etapas;
	@OneToMany(mappedBy = "metanalise", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<MetanaliseRow> rows;
	@OneToMany(mappedBy = "metanalise", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<MetanaliseCol> cols;

	public Metanalise() {
		etapas = new ArrayList<>();
	}

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
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Pesquisador getPesquisadorInclusao() {
		return pesquisadorInclusao;
	}

	public void setPesquisadorInclusao(Pesquisador pesquisadorInclusao) {
		this.pesquisadorInclusao = pesquisadorInclusao;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}
	
	public Integer getPercentual() {
		int concluidas = 0;
		for(MetanaliseMetanaliseEtapa etapa : etapas) {
			if(Boolean.TRUE.equals(etapa.getConcluida())) {
				concluidas += 1;
			}
		}
		return concluidas * 100 / QUANTIDADE_ETAPAS;
	}
	
	public void addAllEtapas(List<MetanaliseMetanaliseEtapa> etapas) {
		this.etapas.addAll(etapas);
	}

	public List<MetanaliseRow> getRows() {
		return rows;
	}

	public void setRows(List<MetanaliseRow> rows) {
		this.rows = rows;
	}
	
	public List<MetanaliseCol> getCols() {
		return cols;
	}

	public void setCols(List<MetanaliseCol> cols) {
		this.cols = cols;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 29 * hash + Objects.hashCode(this.id);
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
		final Metanalise other = (Metanalise) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	
	@PrePersist
	private void prePersist() {
		dataInclusao = new Date();
	}

	@Override
	public String toString() {
		return "Metanalise{" + "id=" + id + ", titulo=" + titulo + '}';
	}
	
}
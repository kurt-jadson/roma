package br.com.smadp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "DG_METANALISE")
public class Metanalise implements Serializable {
	
	public static final String NQ_BUSCAR_NAO_FINALIZADAS = "Metanalise.buscarNaoFinalizadas";
	public static final String NQ_BUSCAR_TODAS = "Metanalise.buscarTodas";
	private static final int QUANTIDADE_ETAPAS = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@ManyToOne
	private Pesquisador pesquisadorInclusao;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dataInclusao;
	@OneToMany(mappedBy = "metanalise")
	private List<MetanaliseMetanaliseEtapa> etapas;

	public String getTitulo() {
		return titulo;
	}

	public Pesquisador getPesquisadorInclusao() {
		return pesquisadorInclusao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}
	
	public Integer getPercentual() {
		int concluidas = 0;
		for(MetanaliseMetanaliseEtapa etapa : etapas) {
			System.out.println(etapa.getConcluida());
			if(Boolean.TRUE.equals(etapa.getConcluida())) {
				concluidas += 1;
			}
		}
		return concluidas * 100 / QUANTIDADE_ETAPAS;
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

	@Override
	public String toString() {
		return "Metanalise{" + "id=" + id + ", titulo=" + titulo + '}';
	}
	
}
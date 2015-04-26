package br.com.smadp.boundary;

import br.com.smadp.entity.MetanaliseRow;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kurt
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MetanaliseRowService {

	@PersistenceContext
	private EntityManager em;

	public List<MetanaliseRow> buscarRowsPorMetanalise(Long id) {
		TypedQuery<MetanaliseRow> rowsQuery = em.createNamedQuery(MetanaliseRow.NQ_BUSCAR_POR_METANALISE_ID,
				MetanaliseRow.class);
		rowsQuery.setParameter(1, id);
		return rowsQuery.getResultList();
	}

}

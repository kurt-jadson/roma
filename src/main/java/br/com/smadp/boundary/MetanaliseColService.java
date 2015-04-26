package br.com.smadp.boundary;

import br.com.smadp.entity.MetanaliseCol;
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
public class MetanaliseColService {

	@PersistenceContext
	private EntityManager em;

	public List<MetanaliseCol> buscarColsPorMetanalise(Long id) {
		TypedQuery<MetanaliseCol> colsQuery = em.createNamedQuery(MetanaliseCol.NQ_BUSCAR_POR_METANALISE_ID,
				MetanaliseCol.class);
		colsQuery.setParameter(1, id);
		return colsQuery.getResultList();
	}	
	
}

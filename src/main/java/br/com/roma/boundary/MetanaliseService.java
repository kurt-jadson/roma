package br.com.roma.boundary;

import br.com.roma.entity.Metanalise;
import br.com.roma.entity.Usuario;
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
public class MetanaliseService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Metanalise> buscarNaoFinalizadas() {
		TypedQuery<Metanalise> query = em.createNamedQuery(Metanalise.NQ_BUSCAR_NAO_FINALIZADAS, 
				Metanalise.class);
		return query.getResultList();
	}
	
}

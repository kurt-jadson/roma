package br.com.smadp.boundary;

import br.com.smadp.entity.Metanalise;
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
	
	public List<Metanalise> buscarTodas() {
		TypedQuery<Metanalise> query = em.createNamedQuery(Metanalise.NQ_BUSCAR_TODAS, 
				Metanalise.class);
		return query.getResultList();
	}
	
	public List<Metanalise> buscarNaoFinalizadas() {
		TypedQuery<Metanalise> query = em.createNamedQuery(Metanalise.NQ_BUSCAR_NAO_FINALIZADAS, 
				Metanalise.class);
		return query.getResultList();
	}
	
}

package br.com.roma.boundary;

import br.com.roma.entity.Usuario;
import java.io.Serializable;
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
public class UsuarioService implements Serializable {
	
	@PersistenceContext
	private EntityManager em;
	
	public Usuario buscarPorUsername(String username) {
		TypedQuery<Usuario> query = em.createQuery("Select u from Usuario u where u.username = ?1", Usuario.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
	
}

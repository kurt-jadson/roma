package br.com.smadp.boundary;

import br.com.smadp.entity.Pesquisador;
import br.com.smadp.entity.Usuario;
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
		TypedQuery<Usuario> query = em.createNamedQuery(Usuario.NQ_BUSCAR_POR_USERNAME, Usuario.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvarConfiguracoesPesquisador(Pesquisador pesquisador) {
		em.merge(pesquisador);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvarConfiguracoesUsuario(Usuario usuario) {
		em.merge(usuario);
	}
	
}

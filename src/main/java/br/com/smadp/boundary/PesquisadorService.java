package br.com.smadp.boundary;

import br.com.smadp.entity.Pesquisador;
import br.com.smadp.entity.Usuario;
import br.com.smadp.framework.LoggedIn;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kurt
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PesquisadorService implements Serializable {

	@PersistenceContext
	private EntityManager em;
	@Inject
	@LoggedIn
	private Instance<Usuario> usuarioLogado;

	public List<Pesquisador> buscarTodos() {
		TypedQuery<Pesquisador> query = em.createNamedQuery(Pesquisador.NQ_BUSCAR_TODOS, Pesquisador.class);
		return query.getResultList();
	}

	public Pesquisador buscarVinculadoUsuarioLogado() {
		try {
			return buscarPorUsuario(usuarioLogado.get());
		} catch (NoResultException ex) {
			return new Pesquisador();
		}
	}

	public Pesquisador buscarPorUsuario(Usuario usuario) {
		TypedQuery<Pesquisador> query = em.createNamedQuery(Pesquisador.NQ_BUSCAR_POR_USUARIO,
				Pesquisador.class);
		query.setParameter(1, usuario);
		return query.getSingleResult();
	}

}

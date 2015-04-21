package br.com.smadp.boundary;

import br.com.smadp.entity.Metanalise;
import br.com.smadp.entity.MetanaliseEtapa;
import br.com.smadp.entity.MetanaliseMetanaliseEtapa;
import br.com.smadp.entity.Pesquisador;
import br.com.smadp.entity.Usuario;
import br.com.smadp.exception.SmadpException;
import br.com.smadp.framework.LoggedIn;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
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
	@Inject
	@LoggedIn
	private Instance<Usuario> usuarioLogado;
	
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvar(Metanalise metanalise) throws SmadpException {
		validarTitulo(metanalise);
		
		TypedQuery<Pesquisador> query = em.createNamedQuery(Pesquisador.NQ_BUSCAR_POR_USUARIO, 
				Pesquisador.class);
		query.setParameter(1, usuarioLogado.get());
		Pesquisador pesquisador = query.getSingleResult();
		
		if(metanalise.isNew()) {
			metanalise.addAll(gerarEtapas(metanalise));
			metanalise.setPesquisadorInclusao(pesquisador);
			em.persist(metanalise);
		} else {
			em.merge(metanalise);
		}
	}
	
	private void validarTitulo(Metanalise metanalise) throws SmadpException {
		TypedQuery<Long> query = em.createNamedQuery(Metanalise.NQ_COUNT_TITULO, Long.class);
		query.setParameter("id", metanalise.getId());
		query.setParameter("titulo", metanalise.getTitulo());
		Long count = query.getSingleResult();
		if(count > 0) {
			throw new SmadpException("smadp.mensagens.1000");
		}
	}
	
	private List<MetanaliseMetanaliseEtapa> gerarEtapas(Metanalise metanalise) {
		List<MetanaliseMetanaliseEtapa> etapas = new ArrayList<>();
		etapas.add(new MetanaliseMetanaliseEtapa(metanalise, MetanaliseEtapa.BANCO_DADOS));
		etapas.add(new MetanaliseMetanaliseEtapa(metanalise, MetanaliseEtapa.QUALIDADE_ESTUDOS));
		etapas.add(new MetanaliseMetanaliseEtapa(metanalise, MetanaliseEtapa.ANALISE_TABULAR));
		etapas.add(new MetanaliseMetanaliseEtapa(metanalise, MetanaliseEtapa.ANALISE_GRAFICA));
		etapas.add(new MetanaliseMetanaliseEtapa(metanalise, MetanaliseEtapa.ANALISE_VIES_PUBLICACAO));
		return etapas;
	}
	
}

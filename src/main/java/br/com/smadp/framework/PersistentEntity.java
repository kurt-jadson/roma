package br.com.smadp.framework;

import java.io.Serializable;

/**
 *
 * @author kurt
 * @param <T> Classe da chave primária da entidade persistente
 */
public interface PersistentEntity<T extends Serializable> extends Serializable {
	
	boolean isNew();
	T getId();
	
}

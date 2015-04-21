package br.com.smadp.framework;

import java.io.Serializable;

/**
 *
 * @author kurt
 */
public interface PersistentEntity extends Serializable {
	
	boolean isNew();
	
}

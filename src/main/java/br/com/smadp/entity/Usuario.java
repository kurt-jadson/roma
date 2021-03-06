package br.com.smadp.entity;

import br.com.smadp.framework.PersistentEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kurt
 */
@Entity
@Table(name = "SYS_USUARIO")
public class Usuario implements PersistentEntity<Long> {

	public static final String NQ_BUSCAR_POR_USERNAME = "Usuario.buscarPorUsername";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String username;
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Idioma idioma;

	@Override
	public boolean isNew() {
		return id == null;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
	}
	
}

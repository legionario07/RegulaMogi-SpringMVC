package br.com.regulamogi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Perfil extends EntidadeDominio{
	
	@NotBlank(message = "Perfil é obrigatório.")
	@Column(nullable=false, unique = true)
	private String tipo;
 
	public Perfil(Long id, String perfil) {
		this(id);
		setTipo(perfil);
	}
	

	public Perfil(Long id) {
		this();
		setId(id);
	}
	
	public Perfil() {
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase().trim();
	}
	
	@Override
	public String toString() {
		return tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}


	

}

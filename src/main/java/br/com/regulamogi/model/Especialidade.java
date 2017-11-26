package br.com.regulamogi.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Especialidade extends EntidadeDominio {
	
	@NotEmpty(message = "A Especialidade deve ser preenchida")
	@Column(unique = true, nullable = false)
	private String nomeEspecialidade;
	
	public Especialidade(Long id) {
		this();
		setId(id);
	}
	
	public Especialidade() {
		
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade.toUpperCase().trim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nomeEspecialidade == null) ? 0 : nomeEspecialidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		if (nomeEspecialidade == null) {
			if (other.nomeEspecialidade != null)
				return false;
		} else if (!nomeEspecialidade.equals(other.nomeEspecialidade))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nomeEspecialidade;
	}

}

package br.com.regulamogi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UnidadeDeSaude extends EntidadeDominio{
	
	@NotEmpty(message = "O nome da Unidade é obrigatório")
	private String nomeUnidade;
	@OneToOne (cascade = CascadeType.DETACH, orphanRemoval = true)
	private Conta conta;	
	
	public UnidadeDeSaude(Long id, String nomeUnidade) {
		this(id);
		this.nomeUnidade = nomeUnidade;
	}
	
	public UnidadeDeSaude(Long id) {
		this();
		setId(id);
	}
	
	public UnidadeDeSaude() {
		conta = new Conta();
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade.toUpperCase().trim();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public String toString() {
		return nomeUnidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((nomeUnidade == null) ? 0 : nomeUnidade.hashCode());
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
		UnidadeDeSaude other = (UnidadeDeSaude) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (nomeUnidade == null) {
			if (other.nomeUnidade != null)
				return false;
		} else if (!nomeUnidade.equals(other.nomeUnidade))
			return false;
		return true;
	}
	

}

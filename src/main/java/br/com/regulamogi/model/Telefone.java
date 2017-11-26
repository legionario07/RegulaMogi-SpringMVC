package br.com.regulamogi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Telefone extends EntidadeDominio {

	private String numero;
	@Enumerated(EnumType.STRING)
	private TelefoneType telefoneType;
	
	public Telefone(Long id) {
		this();
		setId(id);
	}
	
	public Telefone() {
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TelefoneType getTelefoneType() {
		return telefoneType;
	}

	public void setTelefoneType(TelefoneType telefoneType) {
		this.telefoneType = telefoneType;
	}
	
}

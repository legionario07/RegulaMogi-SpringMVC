package br.com.regulamogi.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Paciente extends EntidadeDominio {

	private String nome;
	private String SIS;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:ss:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastLogin;
	@OneToOne
	private Conta conta;

	public Paciente(Long id) {
		this();
		setId(id);
	}
	
	public Paciente() {
		this.conta = new Conta();
		this.lastLogin = Calendar.getInstance();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getSIS() {
		return SIS;
	}

	public void setSIS(String sIS) {
		SIS = sIS;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((SIS == null) ? 0 : SIS.hashCode());
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
		Paciente other = (Paciente) obj;
		if (SIS == null) {
			if (other.SIS != null)
				return false;
		} else if (!SIS.equals(other.SIS))
			return false;
		return true;
	}

}

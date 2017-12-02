package br.com.regulamogi.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Paciente extends EntidadeDominio {

	@NotEmpty(message = "O nome é obrigatório")
	private String nome;
	@NotEmpty(message = "O SIS é obrigatório")
	@Column(unique = true, nullable = false)
	private String SIS;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:ss:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastLogin;
	@OneToOne(orphanRemoval = true)
	private Conta conta;
	@OneToMany(orphanRemoval = true)
	private List<Telefone> telefones;
	@OneToMany()
	private List<Solicitacao> solicitacoes;

	public Paciente(Long id) {
		this();
		setId(id);
	}

	public Paciente() {
		this.conta = new Conta();
		this.lastLogin = Calendar.getInstance();
		telefones = new ArrayList<Telefone>();
		solicitacoes = new ArrayList<Solicitacao>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
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

package br.com.regulamogi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Conta extends EntidadeDominio {

	@NotEmpty(message = "Login deve ser preenchido")
	@Column(unique = true)
	private String login;
	private String senha;
	@NotNull(message = "Perfil deve ser preenchido")
	@OneToOne(cascade = CascadeType.DETACH)
	private Perfil perfil;

	public Conta(String login, String senha, Perfil perfil) {
		this();
		setLogin(login);
		setSenha(senha);
		setPerfil(perfil);
	}

	public Conta(Long id) {
		this();
		setId(id);
	}

	public Conta() {
		perfil = new Perfil();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.toUpperCase().trim();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Conta other = (Conta) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}

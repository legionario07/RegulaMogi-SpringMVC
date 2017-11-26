package br.com.regulamogi.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Solicitacao extends EntidadeDominio {

	@NotEmpty(message = "Data da Consulta é obrigatória")
	@Column(nullable = false)
	private Calendar dataConsulta;
	@NotEmpty(message = "Data do Envio é obrigatória")
	@Column(nullable = false)
	private Calendar dataEnvio;
	@NotEmpty(message = "Número do Memorando é obrigatório")
	@Column(nullable = false)
	private String numeroMemorando;
	@NotBlank(message = "Necessário o identificar o Paciente")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Paciente paciente;
	@NotEmpty(message = "Unidade de Saúde é obrigatória")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UnidadeDeSaude unidadeDeSaude;
	@NotEmpty(message = "Especialidade é obrigatória")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Especialidade especialidade;
	@NotEmpty(message = "Prioridade é obrigatória")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PrioridadeType prioridadeType;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusType statusType;
	
	public Solicitacao() {
		this.paciente = new Paciente();
		this.unidadeDeSaude = new UnidadeDeSaude();
		this.especialidade = new Especialidade();
		
		//toda solicitacação ao ser criada tem o status de Enviado a Secretária da Saude
		this.statusType = StatusType.ENVIADO_A_SMS;
	}
	
	public Calendar getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Calendar dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public Calendar getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Calendar dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public String getNumeroMemorando() {
		return numeroMemorando;
	}
	public void setNumeroMemorando(String numeroMemorando) {
		this.numeroMemorando = numeroMemorando;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public UnidadeDeSaude getUnidadeDeSaude() {
		return unidadeDeSaude;
	}
	public void setUnidadeDeSaude(UnidadeDeSaude unidadeDeSaude) {
		this.unidadeDeSaude = unidadeDeSaude;
	}
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	public PrioridadeType getPrioridadeType() {
		return prioridadeType;
	}
	public void setPrioridadeType(PrioridadeType prioridadeType) {
		this.prioridadeType = prioridadeType;
	}
	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + ((numeroMemorando == null) ? 0 : numeroMemorando.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((unidadeDeSaude == null) ? 0 : unidadeDeSaude.hashCode());
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
		Solicitacao other = (Solicitacao) obj;
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		if (numeroMemorando == null) {
			if (other.numeroMemorando != null)
				return false;
		} else if (!numeroMemorando.equals(other.numeroMemorando))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (unidadeDeSaude == null) {
			if (other.unidadeDeSaude != null)
				return false;
		} else if (!unidadeDeSaude.equals(other.unidadeDeSaude))
			return false;
		return true;
	}
	
	
	
	
}

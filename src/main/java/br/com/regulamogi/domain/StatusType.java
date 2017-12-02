package br.com.regulamogi.domain;

public enum StatusType {
	
	ENVIADO_A_SMS("Enviado a Secretária de Saúde"),
	RECEBIDO_PELA_SMS("Recebido pela Secretária da Saúde"),
	AGUARDANDO_VAGA("Aguardando Vaga"),
	DEVOLVIDA("Devolvida à UBS"),
	AGUARDANDO_DOCUMENTO("Aguardando Documento do Paciente"),
	AGENDADO("Agendado"),
	AGUARDANDO_CONTATO_COM_PACIENTE("Aguardando Contato com Paciente"),
	AGUARDANDO_RETIRADA_GUIA("Aguardando Retirada da Guia"),
	CONCLUIDO("Concluido");
	
	private String descricao;

	private StatusType(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao.toUpperCase();
	}
	

}

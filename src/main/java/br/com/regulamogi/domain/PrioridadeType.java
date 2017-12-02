package br.com.regulamogi.domain;

public enum PrioridadeType {
	
	ROTINA("Rotina"),
	URGENTE("Urgente");
	
	private String descricao;

	private PrioridadeType(String descricao) {
		this.descricao = descricao; 
	}
	
	public String getDescricao() {
		return descricao.toUpperCase();
	}
	

}

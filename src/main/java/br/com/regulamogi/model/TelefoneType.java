package br.com.regulamogi.model;

public enum TelefoneType {
	
	CELULAR("Celular"),
	RESIDENCIAL("Residencial"),
	RECADO("Recado"),
	COMERCIAL("Comercial");
	
	private String descricao;
	
	private TelefoneType(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao.toUpperCase();
	}
	

}

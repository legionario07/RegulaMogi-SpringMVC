package br.com.regulamogi.service;

import br.com.regulamogi.domain.EntidadeDominio;

public abstract class AbstractService<T extends EntidadeDominio> implements IService<T>{

	private static final String MENSAGEM_OPERACAO_REALIZADA_SUCESSO = "Operação realizada com sucesso!";
	private static final String MENSAGEM_ERRO_AO_REALIZAR_OPERACAO = "Erro ao realizar operação!";
	private static final String MENSAGEM_DELETADO_SUCESSO = "Deletado com sucesso!";
	private static final String MENSAGEM_JAH_CADASTRADO = "Objeto já cadastrado!";
	
	public static String getMensagemOperacaoRealizadaSucesso() {
		return MENSAGEM_OPERACAO_REALIZADA_SUCESSO;
	}
	public static String getMensagemErroAoRealizarOperacao() {
		return MENSAGEM_ERRO_AO_REALIZAR_OPERACAO;
	}
	public static String getMensagemDeletadoSucesso() {
		return MENSAGEM_DELETADO_SUCESSO;
	}
	public static String getMensagemJahCadastrado() {
		return MENSAGEM_JAH_CADASTRADO;
	}
	
	
}

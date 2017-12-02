package br.com.regulamogi.service;

import java.util.List;

import br.com.regulamogi.domain.EntidadeDominio;

public interface IService<T extends EntidadeDominio> {
	
	String save(T entidade);
	String edite(T entidade);
	String delete(T entidade);
	T find(T entidade);
	List<T> findAll();

}

package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.domain.repository.PerfilRepository;

@Service
public class PerfilService extends AbstractService<Perfil>{

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public String save(Perfil entidade) {
		try {

			perfilRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Perfil entidade) {
		try {

			perfilRepository.save(entidade);
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Perfil entidade) {
		try {

			perfilRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Perfil find(Perfil entidade) {

		return perfilRepository.findOne(entidade.getId());

	}

	@Override
	public List<Perfil> findAll() {

		return perfilRepository.findAll(new Sort("id"));

	}

}

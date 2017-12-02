package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Telefone;
import br.com.regulamogi.domain.repository.TelefoneRepository;

@Service
public class TelefoneService extends AbstractService<Telefone> {

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Override
	public String save(Telefone entidade) {
		try {

			telefoneRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Telefone entidade) {
		try {

			telefoneRepository.save(entidade);
			
			
			} catch (Exception e) {
				return getMensagemErroAoRealizarOperacao()+e.getMessage();
			}

			return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Telefone entidade) {
		try {

			telefoneRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Telefone find(Telefone entidade) {

		return telefoneRepository.findOne(entidade.getId());

	}

	@Override
	public List<Telefone> findAll() {

		return telefoneRepository.findAll(new Sort("id"));

	}

}

package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Especialidade;
import br.com.regulamogi.domain.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService extends AbstractService<Especialidade> {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@Override
	public String save(Especialidade entidade) {
		try {

			especialidadeRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Especialidade entidade) {
		try {

			especialidadeRepository.save(entidade);
			
			
			} catch (Exception e) {
				return getMensagemErroAoRealizarOperacao()+e.getMessage();
			}

			return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Especialidade entidade) {
		try {

			especialidadeRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Especialidade find(Especialidade entidade) {

		return especialidadeRepository.findOne(entidade.getId());

	}

	@Override
	public List<Especialidade> findAll() {

		return especialidadeRepository.findAll(new Sort("id"));

	}

}

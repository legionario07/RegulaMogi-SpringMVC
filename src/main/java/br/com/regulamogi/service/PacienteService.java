package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.repository.PacienteRepository;

@Service
public class PacienteService extends AbstractService<Paciente> {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public String save(Paciente entidade) {
		try {

			pacienteRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Paciente entidade) {
		try {

			pacienteRepository.save(entidade);
			
			
			} catch (Exception e) {
				return getMensagemErroAoRealizarOperacao()+e.getMessage();
			}

			return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Paciente entidade) {
		try {

			pacienteRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Paciente find(Paciente entidade) {

		return pacienteRepository.findOne(entidade.getId());

	}

	@Override
	public List<Paciente> findAll() {

		return pacienteRepository.findAll(new Sort("id"));

	}

}

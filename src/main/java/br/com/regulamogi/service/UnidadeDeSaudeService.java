package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.UnidadeDeSaude;
import br.com.regulamogi.domain.repository.ContaRepository;
import br.com.regulamogi.domain.repository.UnidadeDeSaudeRepository;
import br.com.regulamogi.utils.EncryptMD5Util;

@Service
public class UnidadeDeSaudeService extends AbstractService<UnidadeDeSaude> {

	@Autowired
	private UnidadeDeSaudeRepository unidadeDeSaudeRepository;
	@Autowired
	private ContaRepository contaRepository;

	@Override
	public String save(UnidadeDeSaude entidade) {
		try {

			entidade.getConta().setSenha((EncryptMD5Util.getEncryptMD5(entidade.getConta().getSenha())));
			entidade.setConta(contaRepository.save(entidade.getConta()));
			unidadeDeSaudeRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(UnidadeDeSaude entidade) {
		try {

			unidadeDeSaudeRepository.save(entidade);
			
			
			} catch (Exception e) {
				return getMensagemErroAoRealizarOperacao()+e.getMessage();
			}

			return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(UnidadeDeSaude entidade) {
		try {

			unidadeDeSaudeRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public UnidadeDeSaude find(UnidadeDeSaude entidade) {

		return unidadeDeSaudeRepository.findOne(entidade.getId());

	}

	@Override
	public List<UnidadeDeSaude> findAll() {

		return unidadeDeSaudeRepository.findAll(new Sort("id"));

	}

}

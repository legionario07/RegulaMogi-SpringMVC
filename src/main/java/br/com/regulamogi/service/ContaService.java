package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.domain.repository.ContaRepository;
import br.com.regulamogi.utils.EncryptMD5Util;

@Service
public class ContaService extends AbstractService<Conta> {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public String save(Conta entidade) {
		try {

			entidade.setSenha(EncryptMD5Util.getEncryptMD5(entidade.getSenha()));
			contaRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() + e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Conta entidade) {
		try {

			contaRepository.save(entidade);

		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() + e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Conta entidade) {
		try {

			contaRepository.delete(entidade.getId());

		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Conta find(Conta entidade) {

		return contaRepository.findOne(entidade.getId());

	}

	@Override
	public List<Conta> findAll() {

		return contaRepository.findAll(new Sort("id"));

	}

	public List<Conta> findByPerfilNotLike(Perfil perfil) {
		return contaRepository.findByPerfilNotLike(perfil);
	}

}

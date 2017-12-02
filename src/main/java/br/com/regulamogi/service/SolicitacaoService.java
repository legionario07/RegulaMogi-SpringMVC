package br.com.regulamogi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.regulamogi.domain.Solicitacao;
import br.com.regulamogi.domain.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService extends AbstractService<Solicitacao> {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Override
	public String save(Solicitacao entidade) {
		try {

			solicitacaoRepository.save(entidade);

		} catch (DataIntegrityViolationException c) {
			return getMensagemJahCadastrado();
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao() +
					e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();

	}

	@Override
	public String edite(Solicitacao entidade) {
		try {

			solicitacaoRepository.save(entidade);
			
			
			} catch (Exception e) {
				return getMensagemErroAoRealizarOperacao()+e.getMessage();
			}

			return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public String delete(Solicitacao entidade) {
		try {

			solicitacaoRepository.delete(entidade.getId());
			
		} catch (Exception e) {
			return getMensagemErroAoRealizarOperacao()+e.getMessage();
		}

		return getMensagemOperacaoRealizadaSucesso();
	}

	@Override
	public Solicitacao find(Solicitacao entidade) {

		return solicitacaoRepository.findOne(entidade.getId());

	}

	@Override
	public List<Solicitacao> findAll() {

		return solicitacaoRepository.findAll(new Sort("id"));

	}

}

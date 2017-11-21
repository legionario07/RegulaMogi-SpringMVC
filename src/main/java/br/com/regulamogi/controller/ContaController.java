package br.com.regulamogi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.regulamogi.model.Conta;
import br.com.regulamogi.model.Perfil;
import br.com.regulamogi.repository.ContaRepository;
import br.com.regulamogi.repository.PerfilRepository;
import br.com.regulamogi.utils.EncryptMD5Util;

@Controller
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	private ModelAndView mv;
	private String mensagem;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		mv = new ModelAndView("conta");
		mv.addObject(new Conta());
		return mv;
		
	}

	@ModelAttribute("perfis")
	public List<Perfil> perfis() {
		return perfilRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Conta conta, Errors errors) {
		
		
		mv = new ModelAndView("conta");
		if (errors.hasErrors()) {
			return mv;
		}

		try {
			conta.setSenha(EncryptMD5Util.getEncryptMD5(conta.getSenha()));
			contaRepository.save(conta);

		} catch (DataIntegrityViolationException c) {
			mensagem = "Item já existe no Banco de Dados";
			mv.addObject("mensagem", mensagem);
			return mv;
		}

		mensagem = "Salvo com sucesso";
		mv.addObject("mensagem", mensagem);
		mv.addObject(new Conta());

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Conta conta) {
		mv = new ModelAndView("conta");
		mv.addObject(conta);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id) {
		contaRepository.delete(id);
		mv = new ModelAndView("redirect:/contas/all");
		
		return mv;
	}
	

	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<Conta> contas = new ArrayList<Conta>();
		contas = contaRepository.findAll(new Sort("id"));
		mv = new ModelAndView("listagemConta");
		mv.addObject("contas", contas);
		return mv;
		
	}
}

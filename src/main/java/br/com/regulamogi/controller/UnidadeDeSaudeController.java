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

import br.com.regulamogi.model.UnidadeDeSaude;
import br.com.regulamogi.model.Perfil;
import br.com.regulamogi.repository.UnidadeDeSaudeRepository;
import br.com.regulamogi.repository.ContaRepository;
import br.com.regulamogi.repository.PerfilRepository;
import br.com.regulamogi.utils.EncryptMD5Util;

@Controller
@RequestMapping("/unidadesDeSaudes")
public class UnidadeDeSaudeController {

	@Autowired
	private UnidadeDeSaudeRepository unidadeDeSaudeRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private ContaRepository contaRepository;
	private ModelAndView mv;
	private String mensagem;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		mv = new ModelAndView("unidadeDeSaude");
		mv.addObject(new UnidadeDeSaude());
		return mv;
		
	}

	@ModelAttribute("perfis")
	public List<Perfil> perfis() {
		return perfilRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated UnidadeDeSaude unidadeDeSaude, Errors errors) {
		
		
		mv = new ModelAndView("unidadeDeSaude");
		if (errors.hasErrors()) {
			return mv;
		}

		try {
			unidadeDeSaude.getConta().setSenha((EncryptMD5Util.getEncryptMD5(unidadeDeSaude.getConta().getSenha())));
			contaRepository.save(unidadeDeSaude.getConta());
			unidadeDeSaudeRepository.save(unidadeDeSaude);

		} catch (DataIntegrityViolationException c) {
			mensagem = "Item já existe no Banco de Dados";
			mv.addObject("mensagem", mensagem);
			return mv;
		}

		mensagem = "Salvo com sucesso";
		mv.addObject("mensagem", mensagem);
		mv.addObject(new UnidadeDeSaude());

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") UnidadeDeSaude unidadeDeSaude) {
		mv = new ModelAndView("unidadeDeSaude");
		mv.addObject(unidadeDeSaude);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id) {
		unidadeDeSaudeRepository.delete(id);
		mv = new ModelAndView("redirect:/unidadesDeSaudes/all");
		mensagem = "Deletado com sucesso";
		mv.addObject("mensagem", mensagem);
		return mv;
	}
	

	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<UnidadeDeSaude> unidadeDeSaudes = new ArrayList<UnidadeDeSaude>();
		unidadeDeSaudes = unidadeDeSaudeRepository.findAll(new Sort("id"));
		mv = new ModelAndView("listagemUnidadeDeSaude");
		mv.addObject("unidadesDeSaudes", unidadeDeSaudes);
		return mv;
		
	}
}

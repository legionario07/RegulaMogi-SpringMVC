package br.com.regulamogi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.regulamogi.model.Perfil;
import br.com.regulamogi.repository.PerfilRepository;

@Controller
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	private ModelAndView mv;
	private String mensagem;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		mv = new ModelAndView("perfil");
		mv.addObject(new Perfil());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Perfil perfil, Errors errors) {
		mv = new ModelAndView("perfil");
		if (errors.hasErrors()) {
			return mv;
		}

		try {
			perfilRepository.save(perfil);

		} catch (DataIntegrityViolationException c) {
			mensagem = "Item já existe no Banco de Dados";
			mv.addObject("mensagem", mensagem);
			return mv;
		}

		mensagem = "Salvo com sucesso";
		mv.addObject("mensagem", mensagem);
		mv.addObject(new Perfil());

		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Perfil perfil) {
		
		mv = new ModelAndView("perfil");
		mv.addObject(perfil);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id) {
		perfilRepository.delete(id);
		mv = new ModelAndView("redirect:/perfis/all");
		
		return mv;
	}
	
	@RequestMapping("/all")
	public ModelAndView all() {
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis = perfilRepository.findAll(new Sort("id"));
		mv = new ModelAndView("listagemPerfil");
		mv.addObject("perfis", perfis);
		return mv;
	}

}

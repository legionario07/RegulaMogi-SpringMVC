package br.com.regulamogi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.service.PerfilService;

@Controller
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	private ModelAndView mv;
	private String mensagem;
	
	private static final String CADASTRO_VIEW = "perfil";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Perfil());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Perfil perfil, Errors errors) {
		mv = new ModelAndView(CADASTRO_VIEW);
		if (errors.hasErrors()) {
			return mv;
		}

		mensagem = perfilService.save(perfil);

		mv.addObject("mensagem", mensagem);
		mv.addObject(new Perfil());

		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Perfil perfil) {

		perfil = perfilService.find(perfil);
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(perfil);
		return mv;

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		Perfil perfil = new Perfil(id);
		mensagem = perfilService.delete(perfil);
		attributes.addFlashAttribute("mensagem", mensagem);
		mv = new ModelAndView("redirect:/perfis/all");
		return mv;
	}

	@RequestMapping("/all")
	public ModelAndView all() {
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis = perfilService.findAll();
		mv = new ModelAndView("listagemPerfil");
		mv.addObject("perfis", perfis);
		return mv;
	}

}

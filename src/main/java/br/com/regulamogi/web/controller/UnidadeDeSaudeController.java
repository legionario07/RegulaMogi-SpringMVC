package br.com.regulamogi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.domain.UnidadeDeSaude;
import br.com.regulamogi.service.PerfilService;
import br.com.regulamogi.service.UnidadeDeSaudeService;

@Controller
@RequestMapping("/unidadesDeSaudes")
public class UnidadeDeSaudeController {

	@Autowired
	private UnidadeDeSaudeService unidadeDeSaudeService;
	@Autowired
	private PerfilService perfilService;
	private ModelAndView mv;
	private String mensagem;
	
	private static final String CADASTRO_VIEW = "unidadeDeSaude";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new UnidadeDeSaude());
		return mv;
		
	}

	@ModelAttribute("perfis")
	public List<Perfil> perfis() {
		return perfilService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated UnidadeDeSaude unidadeDeSaude, Errors errors) {
		
		
		mv = new ModelAndView(CADASTRO_VIEW);
		if (errors.hasErrors()) {
			return mv;
		}

		mensagem = unidadeDeSaudeService.save(unidadeDeSaude);

		mv.addObject("mensagem", mensagem);
		mv.addObject(new Perfil());

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") UnidadeDeSaude unidadeDeSaude) {

		unidadeDeSaude = unidadeDeSaudeService.find(unidadeDeSaude);
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(unidadeDeSaude);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		UnidadeDeSaude unidadeDeSaude = new UnidadeDeSaude(id);
		mensagem = unidadeDeSaudeService.delete(unidadeDeSaude);
		attributes.addFlashAttribute("mensagem", mensagem);
		mv = new ModelAndView("redirect:/unidadesDeSaudes/all");
		return mv;
	}
	

	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<UnidadeDeSaude> unidadeDeSaudes = new ArrayList<UnidadeDeSaude>();
		unidadeDeSaudes = unidadeDeSaudeService.findAll();
		mv = new ModelAndView("listagemUnidadeDeSaude");
		mv.addObject("unidadesDeSaudes", unidadeDeSaudes);
		return mv;
		
	}
}

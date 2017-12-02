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

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.service.ContaService;
import br.com.regulamogi.service.PerfilService;

@Controller
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private PerfilService perfilService;
	private ModelAndView mv;
	private String mensagem;
	
	private static final String CADASTRO_VIEW = "conta";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Conta());
		return mv;
		
	}

	@ModelAttribute("perfis")
	public List<Perfil> perfis() {
		return perfilService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Conta conta, Errors errors) {
		
		
		mv = new ModelAndView(CADASTRO_VIEW);
		if (errors.hasErrors()) {
			return mv;
		}

		mensagem = contaService.save(conta);

		mv.addObject("mensagem", mensagem);
		mv.addObject(new Conta());

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Conta conta) {
		
		conta = contaService.find(conta);
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(conta);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		Conta conta = new Conta(id);
		mensagem = contaService.delete(conta);
		attributes.addFlashAttribute("mensagem", mensagem);
		mv = new ModelAndView("redirect:/contas/all");
		return mv;
	}
	

	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<Conta> contas = new ArrayList<Conta>();
		Perfil perfil = new Perfil(2l, "PACIENTE");
		contas = contaService.findByPerfilNotLike(perfil);
		mv = new ModelAndView("listagemConta");
		mv.addObject("contas", contas);
		return mv;
		
	}
}

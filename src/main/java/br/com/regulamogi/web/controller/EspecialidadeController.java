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

import br.com.regulamogi.domain.Especialidade;
import br.com.regulamogi.service.EspecialidadeService;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService especialidadeService;
	private String mensagem;
	private ModelAndView mv;
	
	private static final String CADASTRO_VIEW = "especialidade";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {

		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Especialidade());
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Especialidade especialidade, Errors errors) {
		mv = new ModelAndView(CADASTRO_VIEW);
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		mensagem = especialidadeService.save(especialidade);

		mv.addObject("mensagem", mensagem);
		mv.addObject(new Especialidade());

		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Especialidade especialidade) {

		especialidade = especialidadeService.find(especialidade);
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(especialidade);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		Especialidade especialidade = new Especialidade(id);
		mensagem = especialidadeService.delete(especialidade);
		attributes.addFlashAttribute("mensagem", mensagem);
		mv = new ModelAndView("redirect:/especialidades/all");
		return mv;
	}
	
	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<Especialidade> especialidades = new ArrayList<Especialidade>();
		especialidades = especialidadeService.findAll();
		mv = new ModelAndView("listagemEspecialidade");
		mv.addObject("especialidades", especialidades);
		return mv;
		
	}
	
}

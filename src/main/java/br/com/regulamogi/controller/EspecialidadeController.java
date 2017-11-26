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

import br.com.regulamogi.model.Especialidade;
import br.com.regulamogi.repository.EspecialidadeRepository;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	private String mensagem;
	private ModelAndView mv;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {

		mv = new ModelAndView("especialidade");
		mv.addObject(new Especialidade());
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Especialidade especialidade, Errors errors) {
		mv = new ModelAndView("especialidade");
		
		if (errors.hasErrors()) {
			return mv;
		}
		
		try {
			especialidadeRepository.save(especialidade);

		} catch (DataIntegrityViolationException c) {
			mensagem = "Item já existe no Banco de Dados";
			mv.addObject("mensagem", mensagem);
			return mv;
		}

		mensagem = "Salvo com sucesso";
		mv.addObject("mensagem", mensagem);
		mv.addObject(new Especialidade());
		
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Especialidade especialidade) {
		mv = new ModelAndView("especialidade");
		mv.addObject(especialidade);
		return mv;
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id) {
		especialidadeRepository.delete(id);
		mv = new ModelAndView("redirect:/especialidades/all");
		
		return mv;
	}
	
	@RequestMapping("/all")
	public ModelAndView all() {
		
		List<Especialidade> especialidades = new ArrayList<Especialidade>();
		especialidades = especialidadeRepository.findAll(new Sort("id"));
		mv = new ModelAndView("listagemEspecialidade");
		mv.addObject("especialidades", especialidades);
		return mv;
		
	}
	
}

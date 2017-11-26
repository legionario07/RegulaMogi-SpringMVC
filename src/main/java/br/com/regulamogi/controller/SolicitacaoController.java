package br.com.regulamogi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.regulamogi.model.Especialidade;
import br.com.regulamogi.model.PrioridadeType;
import br.com.regulamogi.model.Solicitacao;
import br.com.regulamogi.model.StatusType;
import br.com.regulamogi.repository.EspecialidadeRepository;
import br.com.regulamogi.repository.SolicitacaoRepository;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	private ModelAndView mv;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		mv = new ModelAndView("solicitacao");
		mv.addObject(new Solicitacao());
		return mv;
		
	}
	
	@ModelAttribute("especialidades")
	public List<Especialidade> especialidades() {
		return especialidadeRepository.findAll();
	}

	@ModelAttribute("statusTypes")
	public List<StatusType> todosStatusTypes() {
		return Arrays.asList(StatusType.values());
	}
	
	@ModelAttribute("prioridadesTypes")
	public List<PrioridadeType> todasPrioridadesTypes() {
		return Arrays.asList(PrioridadeType.values());
	}
	
	
}

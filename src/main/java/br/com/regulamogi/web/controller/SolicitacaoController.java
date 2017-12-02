package br.com.regulamogi.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import br.com.regulamogi.domain.Especialidade;
import br.com.regulamogi.domain.PrioridadeType;
import br.com.regulamogi.domain.Solicitacao;
import br.com.regulamogi.domain.StatusType;
import br.com.regulamogi.domain.UnidadeDeSaude;
import br.com.regulamogi.service.EspecialidadeService;
import br.com.regulamogi.service.PacienteService;
import br.com.regulamogi.service.SolicitacaoService;
import br.com.regulamogi.service.TelefoneService;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
	
	@Autowired
	private SolicitacaoService solicitacaoService;
	@Autowired
	private EspecialidadeService especialidadeService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private TelefoneService telefoneService;
	private ModelAndView mv;
	private String mensagem;
	private String numMemorando;
	private Calendar dataEnvio;
	private List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	
	private static final String CADASTRO_VIEW = "solicitacao";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {

		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Solicitacao());
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Solicitacao solicitacao) {
		
//		mensagem = solicitacaoService.save(solicitacao);

		mv.addObject("mensagem", mensagem);
		mv.addObject(new Solicitacao());

		return mv;
	}
	
	@ModelAttribute("especialidades")
	public List<Especialidade> especialidades() {
		return especialidadeService.findAll();
	}
	
//	@ModelAttribute("solicitacoes")
//	public List<Solicitacao> solicitacoes() {
//		return solicitacaoRepository.findAll();
//	}
	
	
	@ModelAttribute("statusTypes")
	public List<StatusType> todosStatusTypes() {
		return Arrays.asList(StatusType.values());
	}
	
	@ModelAttribute("prioridadesTypes")
	public List<PrioridadeType> todasPrioridadesTypes() {
		return Arrays.asList(PrioridadeType.values());
	}
	
	@RequestMapping("/adicionar")
	public ModelAndView adicionar(@Validated Solicitacao solicitacao, Errors errors) {
		
		
		mv = new ModelAndView(CADASTRO_VIEW);
		if (errors.hasErrors() || 
				solicitacao.getPaciente().getSIS().length()<1||
				solicitacao.getPaciente().getNome().length()<1) {
			mv.addObject("mensagem", "SIS ou NOME inválido");
			return mv;
		}

		solicitacao.setUnidadeDeSaude(new UnidadeDeSaude(6l));
		solicitacoes.add(solicitacao);
		mv.addObject("solicitacoes", solicitacoes);
		
		//salva o ultimo memorando e a data de Envio para agilizar a digitação
		numMemorando = solicitacao.getNumeroMemorando();
		dataEnvio = solicitacao.getDataEnvio();
		
		solicitacao = new Solicitacao();
		solicitacao.setNumeroMemorando(numMemorando);
		solicitacao.setDataEnvio(dataEnvio);
		
		mv.addObject(solicitacao);
		return mv;
		
	}
	
	@RequestMapping("{SIS}/{especialidade}")
	public ModelAndView edit(@PathVariable("SIS") String SIS, @PathVariable("especialidade") String especialidade ) {

		List<Solicitacao> tempSolicitacoes = new ArrayList<Solicitacao>();
		tempSolicitacoes = solicitacoes;

		for(int i = 0; i<solicitacoes.size();i++) {
			if(solicitacoes.get(i).getEspecialidade().getNomeEspecialidade().equals(especialidade) && 
					solicitacoes.get(i).getPaciente().getSIS().equals(SIS)) {
				tempSolicitacoes.remove(i);
				continue;
			}
		}
		
		solicitacoes = tempSolicitacoes;
		
		mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("solicitacoes", solicitacoes);
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setNumeroMemorando(numMemorando);
		solicitacao.setDataEnvio(dataEnvio);
		
		mv.addObject(solicitacao);
		
		return mv;

	}
	
	@RequestMapping("/criarEspecialidade")
	public String teste() {
		System.out.println("TESTE");
		return CADASTRO_VIEW;
	}
	
}
